package pillion.hba.hub.server.wp;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;

import javax.servlet.http.Cookie;

import org.apache.commons.codec.digest.DigestUtils;
import org.jdbi.v3.core.Handle;
import org.jdbi.v3.core.Jdbi;

import de.ailis.pherialize.Mixed;
import de.ailis.pherialize.MixedArray;
import de.ailis.pherialize.Pherialize;
import pillion.common.server.db.DBUtils;

public class WPDataService {
	
	private static final String LOGGED_IN_COOKIE = "wordpress_logged_in_";

	public static Optional<WPUser> userFromUserName(String userName) {
		
		//svr_db_user_pw
		Jdbi jdbi = Jdbi.create(DBUtils.getMySQLDataSource("192.168.2.160,mitchwp,mitchwp,mitchwp"));
		

		jdbi.registerRowMapper(WPUser.class,WPRowMappers.wpUserRowMapper);
		
		Handle handle = jdbi.open();

		return handle.createQuery("SELECT * FROM wp_users where user_login = :loginName")
				.bind("loginName", userName)
				.mapTo(WPUser.class).findFirst();
		
	}
	
	public static Optional<String> userImageURL(String userName) {
		System.out.println(userName);
		Jdbi jdbi = Jdbi.create(DBUtils.getMySQLDataSource("192.168.2.160,mitchwp,mitchwp,mitchwp"));
		
		jdbi.registerRowMapper(WPUser.class,WPRowMappers.wpUserRowMapper);
		Handle handle = jdbi.open();
		
		Optional<String> imageURL = Optional.empty();
		
		Optional<Integer> ID = handle.createQuery("SELECT ID FROM mitchwp.wp_users WHERE user_login = :loginName")
				.bind("loginName", userName)
				.mapTo(Integer.class).findFirst();
		if (ID.isPresent()) {
			imageURL =  handle.createQuery("SELECT meta_value FROM mitchwp.wp_usermeta WHERE meta_key = 'wp_user_avatars' AND user_id = :ID")
				.bind("ID", ID.get())
				.mapTo(String.class).findFirst();
			if (imageURL.isPresent()) {
			    return imageURL;
			}
		}
		return imageURL;
	}
	
	public static UserMetadata userMetadataFromUserName(Integer userId) {
		UserMetadata umd = new UserMetadata();

		try {
			Jdbi jdbi = Jdbi.create(DBUtils.getMySQLDataSource("192.168.2.160,mitchwp,mitchwp,mitchwp"));

			Handle handle = jdbi.open();
			
			//Changed this query
			List<Map<String, Object>> rows = handle
					.createQuery("SELECT * FROM wp_usermeta where user_id=:userId").bind("userId", userId).mapToMap()
					.list();

			for(Map<String, Object> row: rows) {
				String value = (String) row.get("meta_value");
				switch((String)row.get("meta_key")) {
				case "nickname":
					umd.nickname = value; break;
				case "first_name":
					umd.firstName = value; break;
				case "last_name":
					umd.lastName = value; break;
				case "description":
					umd.description = value; break;
				case "rich_editing":
					umd.richEditing = value; break;
				case "comment_shortcuts":
					umd.commentShortcuts = value; break;
				case "admin_color":
					umd.adminColor = value; break;
				case "use_ssl":
					umd.useSsl = value; break;
				case "show_admin_bar_front":
					umd.showAdminBarFront = value; break;
				case "locale":
					umd.locale = value; break;
				case "wp_capabilities":
					umd.wpCapabilities = toWpCapabilities(value); break;
				case "wp_user_level":
					umd.wpUserLevel = value; break;
				case "pm_profile_bio":
					umd.pmProfileBio = value; break;
				case "dismissed_wp_pointers":
					umd.dismissedWpPointers = value; break;
				case "ja_disable_user":
					umd.jaDisableUser = value; break;
				case "home":
					umd.home = value; break;
				case "position":
					umd.position = value; break;
				case "division":
					umd.division = value; break;
				case "phone":
					umd.phone = value; break;
				case "mobile":
					umd.mobile = value; break;
				case "city":
					umd.city = value; break;
				case "aioi_last_activity_time":
					umd.aioiLastActivityTime = value; break;
				case "wp_user-settings":
					umd.wpUserSettings = value; break;
				case "wp_user-settings-time":
					umd.wpUserSettingsTime = value; break;
				case "acf_user_settings":
					umd.acfUserSettings = value; break;
				case "wp_user_avatars":
					umd.wpUserAvatars = toUserAvatars(value); break;
				case "wp_user_avatars_rating":
					umd.wpUserAvatarsRating = value; break;
				case "session_tokens":
					umd.sessionTokens = toSessionTokens(value); break;
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return umd;
	}
	
	private static WPCapabilities toWpCapabilities(String value) {
		MixedArray list = Pherialize.unserialize(value).toArray();
		WPCapabilities c = null;
		for(Entry<Object, Object> kv : list.entrySet()) {
			Mixed key = (Mixed) kv.getKey();
			Mixed val  = (Mixed) kv.getValue();
			if(val.toBoolean()) {
				c = WPCapabilities.valueOf(key.toString().toUpperCase());
			}
		}
		return c;
	}

	private static List<SessionToken> toSessionTokens(String value) {
		MixedArray list = Pherialize.unserialize(value).toArray();
		List<SessionToken> sts = new ArrayList<>();
		list.forEach((k, v) -> {
			Mixed key = (Mixed) k;
			Mixed val  = (Mixed) v;
			
			SessionToken  st = new SessionToken();
			st.tokenSha = key.toString();
			MixedArray bits = val.toArray();
			st.ip = bits.getString("ip");
			st.loginTime = new Date(bits.getLong("login") * 1000);
			st.expiration = new Date(bits.getLong("expiration") * 1000);
			st.ua = bits.getString("ua");
			sts.add(st);
		});
		return sts;
	}

	private static UserAvitars toUserAvatars(String value) {
		UserAvitars uas = new UserAvitars();
		uas.avatars = new HashMap<>();
		MixedArray list = Pherialize.unserialize(value).toArray();
		list.forEach((k, v) -> {
			Mixed key = (Mixed) k;
			Mixed val  = (Mixed) v;
			if("full".equals(key.toString())){
				uas.avatars.put(0l, val.toString());
			}else {
				uas.avatars.put(key.toLong(), val.toString());
			}
		});
		return uas;
	}
	
	public static void main(String... args) {
		System.out.println(
				userFromUserName("Craig Lee").toString().replaceAll("\\[","\\[\n   ").replaceAll(", ",",\n   ")
		);
	}

	public static WPUser munchCookies(Cookie[] cookies) {
		String urlMd5 = null;
		String[] cookieBits = null;
		WPUser user = null;
		if(cookies != null) {
			for (Cookie c : cookies) {
				if (c.getName().startsWith(LOGGED_IN_COOKIE)) {
					urlMd5 = c.getName().substring(LOGGED_IN_COOKIE.length());
					try {
						cookieBits = URLDecoder.decode(c.getValue(), "UTF-8").split("\\|");
					} catch (UnsupportedEncodingException e) {throw new RuntimeException(e);}
				}
			}
			if (cookieBits != null) {
				String shaedToken = DigestUtils.sha256Hex(cookieBits[2]);
				user = userFromUserName(cookieBits[0]).orElse(null);
				
				if(user != null) {
					user.setMetadata(userMetadataFromUserName(user.getId()));
					user.getMetadata().loggedIn = user.getMetadata().sessionTokens.stream()
							.filter(st -> new Date().before(st.expiration))
							.anyMatch(st -> st.tokenSha.equals(shaedToken));
				}
			}
		}
		return user;
	}
}
