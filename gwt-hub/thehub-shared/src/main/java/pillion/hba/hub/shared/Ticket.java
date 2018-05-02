package pillion.hba.hub.shared;

import java.util.Date;

public class Ticket {
	private Date logged;
	private String priority;
	private String status;
	private String description;
	private String assignee;
	public Date getLogged() {
		return logged;
	}
	public void setLogged(Date logged) {
		this.logged = logged;
	}
	public String getPriority() {
		return priority;
	}
	public void setPriority(String priority) {
		this.priority = priority;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getAssignee() {
		return assignee;
	}
	public void setAssignee(String assignee) {
		this.assignee = assignee;
	}
	@Override
	public String toString() {
		return "Ticket [logged=" + logged + ", priority=" + priority + ", status=" + status + ", description="
				+ description + ", assignee=" + assignee + "]";
	}
	
}
