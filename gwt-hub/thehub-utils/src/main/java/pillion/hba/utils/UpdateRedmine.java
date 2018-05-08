package pillion.hba.utils;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import javax.naming.AuthenticationException;
import javax.naming.NamingException;
import javax.naming.directory.Attribute;
import javax.naming.directory.SearchControls;
import javax.naming.directory.SearchResult;
import javax.naming.ldap.LdapContext;
import javax.sql.DataSource;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jdbi.v3.core.Handle;
import org.jdbi.v3.core.Jdbi;
import org.jdbi.v3.core.statement.Query;
import org.jdbi.v3.core.statement.Update;

import com.google.common.base.CaseFormat;
import com.mysql.cj.jdbc.MysqlDataSource;

import pillion.common.server.ad.ADAttribute;
import pillion.common.server.ad.ActiveDirectory;
import pillion.common.server.ad.ActiveDirectory.ADAttributes;
import pillion.common.server.db.DBUtils;

//TODO - move to HBA project!!
@SuppressWarnings("deprecation")
public class UpdateRedmine {

	private static Logger l = LogManager.getLogger(UpdateRedmine.class);
	
	private static final int[] SUPPORT_PROJECTS = {7,10};
	private static final int SUPPORT_PROJECT_ROLE = 5;

	private static final String EXISTING_USERS = "SELECT * FROM users u, email_addresses e where e.user_id = u.id";

	private static final String NEXT_ID = "SELECT AUTO_INCREMENT FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_SCHEMA = 'redmine' AND TABLE_NAME   = :table";
	private static final String LAST_ID = "SELECT LAST_INSERT_ID()";

	private static final String INSERT_EMAIL = "INSERT INTO email_addresses "
			+ "(user_id,address,is_default,notify,created_on,updated_on) " + "VALUES (:userId,:email,1,1,now(),now())";

	private static final String INSERT_TOKEN = "INSERT INTO tokens " 
			+ "(user_id,action,value,created_on,updated_on) "
			+ "VALUES " + "(:userId,'feeds',:value,now(),now())";

	private static final String INSERT_PREFS = "INSERT INTO user_preferences " + "(user_id,others,hide_mail,time_zone) "
			+ "VALUES " + "(:userId,'---\n:no_self_notified: \\'1\\'\n:my_page_layout:\n  left:\n"
			+ "  - issuesassignedtome\n  right:\n  "
			+ "- issuesreportedbyme\n:my_page_settings: {}\n"
			+ ":comments_sorting: asc\n:warn_on_leaving_unsaved: "
			+ "\\'1\\'\n:textarea_font: \\'\\'\n',1,:timezone)";

	private static final String INSERT_USER = "INSERT INTO users "
			+ "(login,hashed_password,firstname,lastname,admin,status,last_login_on,language,auth_source_id,created_on,updated_on,type,identity_url,mail_notification,salt,must_change_passwd,passwd_changed_on) "
			+ "VALUES "
			+ "(:login,'',:firstname,:lastname,0,1,NULL,'en-GB',1,now(),now(),'User',NULL,'only_my_events',NULL,0,NULL)";
	
	private static final String[] OFFICE_TZ = { "Brisbane Staff,brisbane", "Paratus Claims Staff,melbourne",
			"Perth Staff,perth", "Pillion Staff,sydney", "Sydney Staff,sydney", };
	
	private static final String CHECK_MEMBER = "select project_id from members where user_id = :userId";
	private static final String INSERT_MEMBER = "INSERT INTO members (user_id,project_id,created_on,mail_notification) VALUES (:userId,:projectId,now(),0)";
	private static final String INSERT_MEMBER_ROLE = "INSERT INTO member_roles (member_id,role_id,inherited_from) VALUES (:memberId,:projectRole,NULL)";
	
	
	public static void main(String... args) throws NamingException, SQLException {

		Jdbi jdbi = Jdbi.create(DBUtils.getMySQLDataSource("mysql,testredmine,testredmine,testredmine"));
//		Jdbi jdbi = Jdbi.create(DBUtils.getMySQLDataSource("mysql,redmine,redmine,ieR_ee7dahKi"));

		Handle handle = null;
		
		Map<String,Map<String, Object>> userDetails = null;
		try {
			handle = jdbi.open();
			List<Map<String, Object>> users = findUsers(handle);
			userDetails = users.stream().collect(Collectors.toMap(u -> u.get("login").toString().toLowerCase(), u -> u,  (dup1, dup2) -> dup2));
		} finally {
			if (handle != null) {
				handle.close();
			}
		}

		List<ADAttributes> newUsers = new ArrayList<>(); 
		List<ADAttributes> existingUsers = new ArrayList<>(); 
		
		
		for (String office : OFFICE_TZ) {
			String[] splut = office.split(",");
			
			List<ADAttributes> locationUsers =  ActiveDirectory.getUsers("craiglee", "aihah]V4k", splut[0] //getUsers("craiglee", "aihah]V4k", splut[0]
					, ADAttribute.NAME
					, ADAttribute.GIVEN_NAME
					, ADAttribute.SN
					, ADAttribute.S_A_M_ACCOUNT_NAME
					, ADAttribute.MAIL
					, ADAttribute.ACCOUNT_EXPIRES
					
					);
			for (ADAttributes atts : locationUsers) {
				long expires = Long.parseLong((String) (((List<?>)atts.get(ADAttribute.ACCOUNT_EXPIRES)).get(0)));
				Date expired = adToNormal((long) expires);
				if(
						(expired.after(new Date()) 
						|| expired.before(new Date("1/1/1900"))) 
						&& isHuman(atts)
				) {
					atts.put(ADAttribute.TZ, splut[1]);
					String adUName = atts.getLowerString(ADAttribute.S_A_M_ACCOUNT_NAME);
					if(userDetails.keySet().contains(adUName)) {
						existingUsers.add(atts);
					}else {
						newUsers.add(atts);
					}
				}
			}
		}
		
		try {
			handle = jdbi.open();
			handle.begin();
			for(ADAttributes atts : newUsers) {
				System.out.println("new " + atts);
				String firstName = atts.getString(ADAttribute.GIVEN_NAME);
				String lastName = atts.getString(ADAttribute.SN);
				String email = atts.getString(ADAttribute.MAIL);
				// user
				Update u = handle.createUpdate(INSERT_USER);
				u.bind("login", atts.getLowerString(ADAttribute.S_A_M_ACCOUNT_NAME));
				u.bind("firstname", firstName);
				u.bind("lastname", lastName);
				u.execute();
				int newUserId = getLastId(handle);
				
				addEmail(newUserId, email, handle);
				addToken(newUserId, handle);
				addPrefs(newUserId, atts, handle);
				for(int projectId : SUPPORT_PROJECTS) {
					addSupportMembership(newUserId, projectId, atts, handle);
				}
			}
			for(ADAttributes atts : existingUsers) {
				System.out.println("existing " + atts);
				String loginName = atts.getLowerString(ADAttribute.S_A_M_ACCOUNT_NAME);
				Map<String, Object> dbUser = userDetails.get(loginName);
				Integer userId = (Integer) dbUser.get("id");
				// check membership
				List<Integer> projectIds = handle.createQuery(CHECK_MEMBER)
						.bind("userId",userId)
						.mapTo(Integer.class).list();
				
				for(int projectId : SUPPORT_PROJECTS) {
					if(!projectIds.contains(projectId)) {
						addSupportMembership(userId, projectId, atts, handle);
					}
				}
			}

			handle.commit();
			
		}catch(Throwable t) {
			handle.rollback();
			t.printStackTrace();
		}finally {
			if (handle != null) {
				handle.close();
			}
		}

	}
	
	private static boolean isHuman(ADAttributes atts) {
		String firstName = atts.getString(ADAttribute.GIVEN_NAME);
		String lastName = atts.getString(ADAttribute.SN);
		String email = atts.getString(ADAttribute.MAIL);
		boolean h = ! (StringUtils.isBlank(firstName) 
				|| StringUtils.isBlank(lastName) 
				|| StringUtils.isBlank(email));
//		System.out.println("isHuman? " + atts.getString(ADAttribute.S_A_M_ACCOUNT_NAME) + " " + h);
		return h;
	}
	
	private static void addEmail(int userId, String email, Handle handle ) {
		Update u = handle.createUpdate(INSERT_EMAIL);
		u.bind("userId", userId);
		u.bind("email", email);
		u.execute();
	}

	private static void addToken(int userId,Handle handle ) {
		Update u = handle.createUpdate(INSERT_TOKEN);
		u.bind("userId", userId);
		u.bind("value", RandomStringUtils.random(40, "0123456789abcdef"));
		u.execute();
	}

	private static void addPrefs(int userId, ADAttributes atts,Handle handle ) {
		Update u = handle.createUpdate(INSERT_PREFS);
		u.bind("userId", userId);
		u.bind("timezone", atts.getString(ADAttribute.TZ));		
		u.execute();
	}
	private static void addSupportMembership(int userId, int projectId, ADAttributes atts,Handle handle ) {
		Update u = handle.createUpdate(INSERT_MEMBER);
		u.bind("userId", userId);
		u.bind("projectId", projectId);
		u.execute();
		int newMemberId = getLastId(handle);
		u = handle.createUpdate(INSERT_MEMBER_ROLE);
		u.bind("memberId", newMemberId);
		u.bind("projectRole", SUPPORT_PROJECT_ROLE);
		u.execute();
	}

	private static List<Map<String, Object>> findUsers(Handle handle) {
		Query query = handle.createQuery(EXISTING_USERS);
		return query.mapToMap().list();
}

	@SuppressWarnings("unused")
	private static int getNextId(Handle handle, String table) {
		Query query = handle.createQuery(NEXT_ID);
		query.bind("table", table);
		return query.mapTo(Integer.class).findOnly();
	}

	private static int getLastId(Handle handle) {
		Query query = handle.createQuery(LAST_ID);
		return query.mapTo(Integer.class).findOnly();
	}

	private static Date adToNormal(long adshitcunt) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date("1/1/1601"));
		long base_1601_time = calendar.getTimeInMillis();
		calendar.setTime(new Date("1/1/1970"));
		long base_1970_time = calendar.getTimeInMillis();
		long ms_offset = base_1970_time - base_1601_time;
		calendar.setTimeInMillis(adshitcunt / 10000 - ms_offset);
		return calendar.getTime();
		
	}


/*	public static DataSource getTestRedmineDataSource() throws SQLException {
		MysqlDataSource ods = new MysqlDataSource();
		ods.setURL("jdbc:mysql://192.168.2.160:3306/testredmine?user=testredmine");
		ods.setPassword("testredmine");
		return ods;
	}

	public static DataSource getLiveRedmineDataSource() throws SQLException {
		MysqlDataSource ods = new MysqlDataSource();
		ods.setURL("jdbc:mysql://192.168.2.160:3306/redmine?user=redmine");
		ods.setPassword("ieR_ee7dahKi");
		return ods;
	}*/
}
