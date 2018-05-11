package pillion.hba.hub.server.rm;

import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.annotation.WebServlet;

import com.taskadapter.redmineapi.RedmineException;
import com.taskadapter.redmineapi.bean.Issue;
import com.taskadapter.redmineapi.bean.User;

import pillion.hba.hub.server.HubRemoteServiceServlet;
import pillion.hba.hub.server.wp.WPUser;
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

}
