package pillion.hba.hub.server;

import java.io.File;
import java.io.FileWriter;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jdbi.v3.core.Handle;
import org.jdbi.v3.core.Jdbi;
import org.jdbi.v3.core.result.ResultIterable;

import pillion.common.server.db.DBUtils;

public class WPDataService {
	public static String shitFromUserName(String userNickname) {

		try {
			Jdbi jdbi = Jdbi.create(DBUtils.getMySQLDataSource(""));
			Handle handle = null;
			handle = jdbi.open();

			Long userId = handle
					.createQuery(
							"SELECT user_id FROM wp.wp_usermeta where meta_key = 'nickname' and meta_value=:nickname")
					.bind("nickname", userNickname).mapTo(Long.class).findOnly();

			List<Map<String, Object>> userMetadata = handle
					.createQuery("SELECT * FROM wp.wp_usermeta where user_id=:userId").bind("userId", userId).mapToMap()
					.list();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}
	
	private UserMetadata toMettadata(List<Map<String, Object>> userMetadata) {
		return new UserMetadata();
	}
	
}
