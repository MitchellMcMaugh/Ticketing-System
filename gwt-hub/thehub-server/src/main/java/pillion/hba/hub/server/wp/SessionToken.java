package pillion.hba.hub.server.wp;

import java.util.Date;

public class SessionToken {

	public String tokenSha;
	public Date expiration;
	public String ip;
	public String ua;
	public Date loginTime;
	@Override
	public String toString() {
		return String.format("SessionToken [tokenSha=%s, expiration=%s, ip=%s, ua=%s, loginTime=%s]", tokenSha,
				expiration, ip, ua, loginTime);
	}

	
	
}
