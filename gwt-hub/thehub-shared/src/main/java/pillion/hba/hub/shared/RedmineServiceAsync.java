package pillion.hba.hub.shared;

import java.util.ArrayList;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface RedmineServiceAsync {
	void getTickets(AsyncCallback<Tickets> tickets);
	void newTicket(String ticketPriority, String ticketCategory, String ticketShortDescription, String ticketDetails, AsyncCallback<Ticket> ticket);
	void getComments(int id, com.google.gwt.user.client.rpc.AsyncCallback<Comments> comments);
	void newComment(String comment, int issueID, com.google.gwt.user.client.rpc.AsyncCallback<Comment> commentAdd);
	void getData(AsyncCallback<String> url);
	void getAttachments(int ticketID, AsyncCallback<TicketAttachments> attachments);
	void deleteAttachment(int attachmentID, AsyncCallback<Integer> nothing);
	void getEmployees(AsyncCallback<Employees> employees);
}
