package pillion.hba.hub.shared;

import java.io.Serializable;
import java.util.Date;

public class Comment implements Serializable {
	private Date logged;

	private String commentID;
	private String comment;
	private String user;
	
	public String getCommentID() {
		return commentID;
	}
	
	public void setComment(String comment) {
		this.comment = comment;
	}
	
	public String getComment() {
		return comment;
	}
	
	public Date getLogged() {
		return logged;
	}
	public void setLogged(Date logged) {
		this.logged = logged;
	}

	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}


}
