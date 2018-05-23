package pillion.hba.hub.shared;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface RedmineServiceAsync {
	void getTickets(AsyncCallback<Tickets> tickets);
	//void newTicket(String ticketCreator, String issueStatus, String issuePriority, String issueSubject, String issueDetails, AsyncCallback<Tickets> callback);
	 void newTicket(String ticketPriority, String ticketCategory, String ticketShortDescription, String ticketDetails, AsyncCallback<Ticket> ticket);
}
