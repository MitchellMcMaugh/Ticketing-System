package pillion.hba.hub.server;

import java.util.Date;

public class SessionTokens {
	
	public String tokenSha;

	public static class SessionToken{
		public Date expiration;
		public String ip;
		public String ua;
		public Date loginTime;
	}
	
}
