package pillion.hba.hub.server.wp;

import java.util.Date;

public class WPUser {
	private Integer id;
	private String userLogin;
	private String userPass;
	private String userNicename;
	private String userEmail;
	private String userUrl;
	private Date userRegistered;
	private String userActivationKey;
	private Integer userStatus;
	private String displayName;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getUserLogin() {
		return userLogin;
	}
	public void setUserLogin(String userLogin) {
		this.userLogin = userLogin;
	}
	public String getUserPass() {
		return userPass;
	}
	public void setUserPass(String userPass) {
		this.userPass = userPass;
	}
	public String getUserNicename() {
		return userNicename;
	}
	public void setUserNicename(String userNicename) {
		this.userNicename = userNicename;
	}
	public String getUserEmail() {
		return userEmail;
	}
	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}
	public String getUserUrl() {
		return userUrl;
	}
	public void setUserUrl(String userUrl) {
		this.userUrl = userUrl;
	}
	public Date getUserRegistered() {
		return userRegistered;
	}
	public void setUserRegistered(Date userRegistered) {
		this.userRegistered = userRegistered;
	}
	public String getUserActivationKey() {
		return userActivationKey;
	}
	public void setUserActivationKey(String userActivationKey) {
		this.userActivationKey = userActivationKey;
	}
	public Integer getUserStatus() {
		return userStatus;
	}
	public void setUserStatus(Integer userStatus) {
		this.userStatus = userStatus;
	}
	public String getDisplayName() {
		return displayName;
	}
	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}
	@Override
	public String toString() {
		return "WPUser [id=" + id + ", userLogin=" + userLogin + ", userPass=" + userPass + ", userNicename="
				+ userNicename + ", userEmail=" + userEmail + ", userUrl=" + userUrl + ", userRegistered="
				+ userRegistered + ", userActivationKey=" + userActivationKey + ", userStatus=" + userStatus
				+ ", displayName=" + displayName + "]";
	}
	
	
}
