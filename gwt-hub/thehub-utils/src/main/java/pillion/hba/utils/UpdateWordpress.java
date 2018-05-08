package pillion.hba.utils;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import javax.naming.NamingException;
import javax.sql.DataSource;

import org.jdbi.v3.core.Handle;
import org.jdbi.v3.core.Jdbi;
import org.jdbi.v3.core.statement.Update;

import pillion.common.server.ad.ADAttribute;
import pillion.common.server.ad.ActiveDirectory;
import pillion.common.server.ad.ActiveDirectory.ADAttributes;
import pillion.common.server.db.DBUtils;
import pillion.hba.hub.server.wp.WPUser;

public class UpdateWordpress {

	
	private static final String SELECT_USERS = "SELECT * FROM wp_users";
	private static final String UPDATE_USERS = "UPDATE wp_users SET user_login=:login, user_nicename=:login, user_pass='' WHERE ID=:userId";
	private static final String INSERT_METADATA_BIT = "INSERT INTO wp_usermeta (user_id,meta_key,meta_value) VALUES (:userId,'wpDirAuthFlag','1')";

	private static Map<String, String> KNOWN_ALIASES = new HashMap<String, String>() {{
		put("Melissa Wroe", "Melissa Hurt");
		put("Nicholas Powe","Nick Powe");
	}};
	
	private static String dbSchema = null;
	
	public static void main(String... args) throws NamingException, SQLException {

		
//		Jdbi jdbi = Jdbi.create(getDatasource("mysql,testwp,testwp,testwp"));
		Jdbi jdbi = Jdbi.create(getDatasource("mysql,wp,wp,wp"));

		jdbi.registerRowMapper(WPUser.class,
			    (rs, ctx) -> {
			    	WPUser u = new WPUser();
			    	u.setId(rs.getInt("ID"));
			    	u.setUserLogin(rs.getString("user_login"));
			    	u.setUserPass(rs.getString("user_pass"));
			    	u.setUserNicename(rs.getString("user_nicename"));
			    	u.setUserEmail(rs.getString("user_email"));
			    	u.setUserUrl(rs.getString("user_url"));
			    	u.setUserRegistered(rs.getDate("user_registered"));
			    	u.setUserActivationKey(rs.getString("user_activation_key"));
			    	u.setUserStatus(rs.getInt("user_status"));
			    	u.setDisplayName(rs.getString("display_name"));
					return u;
			    });
		
		
		Handle handle = null;
		
		try {
			handle = jdbi.open();
			List<WPUser> users = handle.createQuery(SELECT_USERS).mapTo(WPUser.class).list();
			Map<String, WPUser> usersByName = users.stream().collect(Collectors.toMap(u -> u.getUserLogin(), Function.identity()));
			
			List<ADAttributes> adUsers =  ActiveDirectory.getUsers("craiglee", "aihah]V4k",null
					, ADAttribute.NAME
					, ADAttribute.GIVEN_NAME
					, ADAttribute.SN
					, ADAttribute.S_A_M_ACCOUNT_NAME
					, ADAttribute.MAIL
					, ADAttribute.ACCOUNT_EXPIRES);
			
			handle.begin();
			
			for(ADAttributes adUser : adUsers) {
				String firstName = adUser.getString(ADAttribute.GIVEN_NAME);
				String lastName = adUser.getString(ADAttribute.SN);
				String adLogin = adUser.getString(ADAttribute.S_A_M_ACCOUNT_NAME);
				String probableCurrentLogin = firstName + " " + lastName;
				WPUser userToUpdate = usersByName.get(probableCurrentLogin);
				if(firstName==null || lastName == null) continue;
	
				if(userToUpdate == null && KNOWN_ALIASES.get(probableCurrentLogin) != null) {
					userToUpdate = usersByName.get(KNOWN_ALIASES.get(probableCurrentLogin));
				}
				if(userToUpdate != null){
					System.out.printf("UPDATING %s %s\n", firstName, lastName);
					Update u = handle.createUpdate(UPDATE_USERS);
					u.bind("userId", userToUpdate.getId());
					u.bind("login", adLogin);
					u.execute();
					u = handle.createUpdate(INSERT_METADATA_BIT);
					u.bind("userId", userToUpdate.getId());
					u.execute();
				}else{ 
//					System.out.printf("no account for %s %s\n", firstName, lastName);
				}
					
			}
			handle.commit();
			
			users.forEach(u -> System.out.println(u));
		}catch(Exception e) {
			e.printStackTrace();
			handle.rollback();
		}
		finally {
			if (handle != null) {
				handle.close();
			}
		}

	}
	
	private static DataSource getDatasource(String dbDetails) {
		dbSchema = dbDetails.split(",")[1];
		return DBUtils.getMySQLDataSource(dbDetails);
	}
}
