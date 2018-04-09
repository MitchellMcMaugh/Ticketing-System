package pillion.hba.hub.server;

import java.util.Map;

/**
 * list of urls to pictures
 * 
 */
public class UserAvitars {
	Map<Long,String> avatars;

	@Override
	public String toString() {
		return String.format("UserAvitars [avatars=%s]", avatars);
	}
	
}
