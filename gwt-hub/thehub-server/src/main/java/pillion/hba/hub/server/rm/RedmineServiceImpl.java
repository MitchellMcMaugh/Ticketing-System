package pillion.hba.hub.server.rm;

//import java.util.List;
import java.util.stream.Collectors;
import java.io.File;
import java.io.IOException;
//import java.util.*;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.taskadapter.redmineapi.RedmineException;
import com.taskadapter.redmineapi.bean.Issue;
import com.taskadapter.redmineapi.bean.User;
import com.taskadapter.redmineapi.bean.Journal;
import com.taskadapter.redmineapi.bean.JournalDetail;
import com.google.gwt.user.client.ui.Image;

import pillion.hba.hub.server.HubRemoteServiceServlet;
import pillion.hba.hub.server.wp.WPUser;
import pillion.hba.hub.server.wp.WPDataService;
import pillion.hba.hub.server.wp.UserAvitars;
import pillion.hba.hub.server.wp.UserMetadata;
import pillion.hba.hub.shared.Comment;
import pillion.hba.hub.shared.Comments;
import pillion.hba.hub.shared.RedmineService;
import pillion.hba.hub.shared.Ticket;
import pillion.hba.hub.shared.Tickets;


@WebServlet("/barnacle/redmineService")
public class RedmineServiceImpl extends HubRemoteServiceServlet implements RedmineService{

	private Ticket fromIssue(Issue issue){
		Ticket ticket = new Ticket();
		ticket.setLogged(issue.getCreatedOn());
		ticket.setAssignee(issue.getAssigneeName());
		ticket.setDescription(issue.getDescription());
		ticket.setTitle(issue.getSubject());
		ticket.setPriority(issue.getPriorityText());
		ticket.setStatus(issue.getStatusName());
		ticket.setTicketID(issue.getId());
		return ticket;
	};
	
	@Override
	public Tickets getTickets() {
		try {
			WPUser user = getLoggedInUser();
			User redmineUser = RM.findUserByName(user.getUserLogin());
			return new Tickets(RM.findTickets(redmineUser.getId()).stream().map(i -> fromIssue(i)).collect(Collectors.toList()));
		} catch (RedmineException e) {
			e.printStackTrace();
		}
		return null;
	}
	
//	private Image getAvatar() {
//		WPUser user = getLoggedInUser();
//		UserAvitars avatar = doStuff(user);
//		avatar.
//	}
	
	private Comment fromJournal(Journal journal) {
		Comment comment = new Comment();
		comment.setLogged(journal.getCreatedOn());
		comment.setComment(journal.getNotes());
		comment.setUser(journal.getUser().getFullName());
		return comment;
		
	}
	
	public Comments getComments(int issueID) {
		try {
			//int issueID = 388;
			return new Comments(RM.findJournals(issueID).stream().map(i -> fromJournal(i)).collect(Collectors.toList()));
		} catch (RedmineException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	//@Override
	public Ticket newTicket(String ticketPriority, String ticketCategory, String ticketShortDescription, String ticketDetails) {
		try {
			WPUser user = getLoggedInUser();
			User redmineUser = RM.findUserByName(user.getUserLogin());
			String redmineUserString = redmineUser.toString();
			Integer issueID = RM.newTicket(redmineUserString, ticketPriority, ticketCategory, ticketShortDescription, ticketDetails);	
			
		} catch (RedmineException e) {
			e.printStackTrace();
		}
		return null;
	
	}
	
	public Comment newComment(String comment, int issueID) {
		try {
			WPUser user = getLoggedInUser();
			User redmineUser = RM.findUserByName(user.getUserLogin());
			//String redmineUserString = redmineUser.toString();
			RM.newCommentRM(redmineUser, comment, issueID);
		} catch (RedmineException e) {
			e.printStackTrace();
		}
		return null;
	}
	
}
