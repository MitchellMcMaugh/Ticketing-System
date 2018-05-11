package pillion.hba.hub.server.wp;

import java.util.Date;
import java.util.List;

import org.apache.commons.codec.digest.DigestUtils;

public class UserMetadata {
	
	public boolean loggedIn = false;
	
	public String nickname;
	public String firstName;
	public String lastName;
	public String description;
	public String richEditing;
	public String commentShortcuts;
	public String adminColor;
	public String useSsl;
	public String showAdminBarFront;
	public String locale;
	public WPCapabilities wpCapabilities;
	public String wpUserLevel;
	public String pmProfileBio;
	public String PmProfileBio;
	public String dismissedWpPointers;
	public String jaDisableUser;
	public String home;
	public String position;
	public String division;
	public String phone;
	public String mobile;
	public String city;
	public String aioiLastActivityTime;
	public String wpUserSettings;
	public String wpUserSettingsTime;
	public String acfUserSettings;
	public UserAvitars wpUserAvatars;
	public String wpUserAvatarsRating;
	public List<SessionToken> sessionTokens;

	public UserMetadata(){
		
	}
	
	public UserMetadata(String token) {
		String shaedToken = DigestUtils.sha256Hex(token);
		loggedIn =  sessionTokens.stream().filter(st -> new Date().before(st.expiration))
		.anyMatch(st -> st.tokenSha.equals(shaedToken));
	}
	
	@Override
	public String toString() {
		return String.format(
				"UserMetadata [loggedIn=%s, nickname=%s, firstName=%s, lastName=%s, description=%s, richEditing=%s, commentShortcuts=%s, "
				+ "adminColor=%s, useSsl=%s, showAdminBarFront=%s, locale=%s, wpCapabilities=%s, wpUserLevel=%s, pmProfileBio=%s, PmProfileBio=%s, "
				+ "dismissedWpPointers=%s, jaDisableUser=%s, home=%s, position=%s, division=%s, phone=%s, mobile=%s, city=%s, aioiLastActivityTime=%s, "
				+ "wpUserSettings=%s, wpUserSettingsTime=%s, acfUserSettings=%s, wpUserAvatars=%s, wpUserAvatarsRating=%s, sessionTokens=%s]",
				loggedIn, nickname, firstName, lastName, description, richEditing, commentShortcuts, adminColor, useSsl,
				showAdminBarFront, locale, wpCapabilities, wpUserLevel, pmProfileBio, PmProfileBio, dismissedWpPointers,
				jaDisableUser, home, position, division, phone, mobile, city, aioiLastActivityTime, wpUserSettings,
				wpUserSettingsTime, acfUserSettings, wpUserAvatars, wpUserAvatarsRating, sessionTokens);
	}

	public String toHtmlRows() {
		return String.format(
				"<tr><td>loggedIn</td><td>%s</td></tr><tr><td>nickname</td><td>%s</td></tr><tr><td>firstName</td><td>%s</td></tr><tr><td>lastName</td><td>%s</td></tr>"
				+ "<tr><td>description</td><td>%s</td></tr><tr><td>richEditing</td><td>%s</td></tr><tr><td>commentShortcuts</td><td>%s</td></tr>"
				+ "<tr><td>adminColor</td><td>%s</td></tr><tr><td>useSsl</td><td>%s</td></tr><tr><td>showAdminBarFront</td><td>%s</td></tr><tr><td>locale</td><td>%s</td></tr>"
				+ "<tr><td>wpCapabilities</td><td>%s</td></tr><tr><td>wpUserLevel</td><td>%s</td></tr><tr><td>pmProfileBio</td><td>%s</td></tr><tr><td>PmProfileBio</td><td>%s</td></tr>"
				+ "<tr><td>dismissedWpPointers</td><td>%s</td></tr><tr><td>jaDisableUser</td><td>%s</td></tr><tr><td>home</td><td>%s</td></tr><tr><td>position</td><td>%s</td></tr>"
				+ "<tr><td>division</td><td>%s</td></tr><tr><td>phone</td><td>%s</td></tr><tr><td>mobile</td><td>%s</td></tr><tr><td>city</td><td>%s</td></tr><tr><td>aioiLastActivityTime</td><td>%s</td></tr>"
				+ "<tr><td>wpUserSettings</td><td>%s</td></tr><tr><td>wpUserSettingsTime</td><td>%s</td></tr><tr><td>acfUserSettings</td><td>%s</td></tr><tr><td>wpUserAvatars</td><td>%s</td></tr>"
				+ "<tr><td>wpUserAvatarsRating</td><td>%s</td></tr><tr><td>sessionTokens</td><td>%s</td></tr>",
				loggedIn, nickname, firstName, lastName, description, richEditing, commentShortcuts, adminColor, useSsl,
				showAdminBarFront, locale, wpCapabilities, wpUserLevel, pmProfileBio, PmProfileBio, dismissedWpPointers,
				jaDisableUser, home, position, division, phone, mobile, city, aioiLastActivityTime, wpUserSettings,
				wpUserSettingsTime, acfUserSettings, wpUserAvatars, wpUserAvatarsRating, sessionTokens);
	}

	
	
}
