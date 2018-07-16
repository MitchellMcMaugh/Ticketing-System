package pillion.hba.hub.shared;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("redmineService")
public interface RedmineService extends RemoteService {
	Tickets getTickets();
	Ticket newTicket(String ticketPriority, String ticketCategory, String ticketShortDescription, String ticketDetails);
	Comments getComments(int id);
	Comment newComment(String comment, int issueID);
}
