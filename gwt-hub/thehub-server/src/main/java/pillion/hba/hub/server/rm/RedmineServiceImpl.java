package pillion.hba.hub.server.rm;

import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import javax.servlet.annotation.WebServlet;

import org.apache.commons.lang3.StringUtils;

import com.taskadapter.redmineapi.RedmineException;
import com.taskadapter.redmineapi.bean.Issue;
import com.taskadapter.redmineapi.bean.Journal;
import com.taskadapter.redmineapi.bean.User;

import pillion.hba.hub.server.HubRemoteServiceServlet;
import pillion.hba.hub.server.wp.UserAvitars;
import pillion.hba.hub.server.wp.UserMetadata;
import pillion.hba.hub.server.wp.WPDataService;
import pillion.hba.hub.server.wp.WPUser;
import pillion.hba.hub.shared.Comment;
import pillion.hba.hub.shared.Comments;
import pillion.hba.hub.shared.RedmineService;
import pillion.hba.hub.shared.Ticket;
import pillion.hba.hub.shared.Tickets;
import pillion.hba.hub.server.rm.RMDataService;


@WebServlet("/barnacle/redmineService")
public class RedmineServiceImpl extends HubRemoteServiceServlet implements RedmineService{

	private Ticket fromIssue(Issue issue){
		Ticket ticket = new Ticket();
		ticket.setLogged(issue.getCreatedOn());
		ticket.setAssignee(issue.getAssigneeName());
		ticket.setCategory(issue.getCustomFieldById(4).getValue());
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
	
	public String getData() {
		WPUser user = getLoggedInUser();
		String userName = user.getDisplayName();
		UserMetadata metadata = user.getMetadata();
		UserAvitars url = metadata.wpUserAvatars;
		int count = StringUtils.countMatches(url.toString(), "=");
		int location = ordinalIndexOf(url.toString(), "=", count);
		String data = url.toString().substring(location + 1, url.toString().length() - 2) + "¯\\_(ツ)_/¯" + userName;
		return data;
	}
	

	public Comments getComments(int issueID) {
//		try {	
			//return new Comments(RM.findJournals(issueID).stream().map(i -> fromJournal(i)).collect(Collectors.toList()));
			//List<Comment> commentsList = RMDataService.getJournals(issueID);
//			return new Comments(RMDataService("journal", issueID));
			return new Comments(RMDataService.getJournals(issueID).stream().collect(Collectors.toList()));
					//.map(i -> fromJournal(i)).collect(Collectors.toList()))
			//return comments;
//		} catch (RedmineException e) {
//			e.printStackTrace();
//		}
		//return null;
	}
	
	private Comment fromJournal(Journal journal) {
		Comment comment = new Comment();
		comment.setLogged(journal.getCreatedOn());
		comment.setComment(journal.getNotes());
		comment.setUser(journal.getUser().getFullName());
		Optional<String> userURL = WPDataService.userImageURL(comment.getUser().replaceAll("\\s+",""));
		if (userURL.isPresent()) {
			String urlSearchParameter = "i:250;";
			int indexStart = ordinalIndexOf(userURL.get(), urlSearchParameter , 1);
			String substring1 = userURL.get().substring(indexStart + urlSearchParameter.length() + 1);
			String urlSearchParameter2 = "-250x250.jpg";
			int indexEnd = ordinalIndexOf(substring1, urlSearchParameter2, 1);
			String substring2 = substring1.substring(0, indexEnd + urlSearchParameter2.length());
			Pattern r = Pattern.compile("((?:http|https)(?::\\/{2}[\\w]+)(?:[\\/|\\.]?)(?:[^\\s\\\"]*))");
			String data = "";
			Matcher m = r.matcher(substring2);
			if (m.find()) {
				 data = m.group(0);
			}	
			comment.setImageUrl(data);
		}
		return comment;
		
	}
	
	//@Override
	public Ticket newTicket(String ticketPriority, String ticketCategory, String ticketShortDescription, String ticketDetails) {
		try {
			WPUser user = getLoggedInUser();
			User redmineUser = RM.findUserByName(user.getUserLogin());
			//String redmineUserString = redmineUser.toString();
			Integer issueID = RM.newTicket(redmineUser, ticketPriority, ticketCategory, ticketShortDescription, ticketDetails);	
			
		} catch (RedmineException e) {
			e.printStackTrace();
		}
		return null;
	
	}
	
	public Comment newComment(String comment, int issueID) {
		try {
			WPUser user = getLoggedInUser();
			RM.newCommentRM(user, comment, issueID);
		} catch (RedmineException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static int ordinalIndexOf(String str, String substr, int n) {
	    int pos = str.indexOf(substr);
	    while (--n > 0 && pos != -1)
	        pos = str.indexOf(substr, pos + 1);
	    return pos;
	}
	
}
