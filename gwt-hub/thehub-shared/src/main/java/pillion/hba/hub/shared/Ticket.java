package pillion.hba.hub.shared;

import java.io.Serializable;
import java.util.Date;

public class Ticket implements Serializable{
	private Date logged;
	private String priority;
	private String status;
	private String description;
	private String title;
	private String assignee;
	private int ticketID;
	private String category;
	
	public int getTicketID() {
		return ticketID;
	}
	
	public void setTicketID(int ticketID) {
		this.ticketID = ticketID;
	}
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
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getCategory() {
		return category;
	}
	
	public void setCategory(String category) {
		this.category = category;
	}
	
	
	@Override
	public String toString() {
		return "Ticket [ticketID=" + ticketID + ",logged=" + logged + ", priority=" + priority + ", status=" + status + ", description="
				+ description + ", title=" + title + ", assignee=" + assignee + "]";
	}
	
	
	
}
