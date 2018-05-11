package pillion.hba.hub.server.wp;

import org.jdbi.v3.core.mapper.RowMapper;

public class WPRowMappers{

	public static RowMapper<WPUser> wpUserRowMapper = (rs, ctx) -> {
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
    };


}
