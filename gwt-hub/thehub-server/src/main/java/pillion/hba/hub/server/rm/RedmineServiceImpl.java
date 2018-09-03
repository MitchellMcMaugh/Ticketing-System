package pillion.hba.hub.server.rm;

import java.io.ByteArrayInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.stream.Collectors;

import javax.servlet.annotation.WebServlet;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;
import org.apache.commons.lang3.StringUtils;

import com.taskadapter.redmineapi.RedmineException;
import com.taskadapter.redmineapi.bean.Attachment;
import com.taskadapter.redmineapi.bean.Issue;
import com.taskadapter.redmineapi.bean.User;

import pillion.hba.hub.server.HubRemoteServiceServlet;
import pillion.hba.hub.server.wp.UserAvitars;
import pillion.hba.hub.server.wp.UserMetadata;
import pillion.hba.hub.server.wp.WPUser;
import pillion.hba.hub.shared.Comment;
import pillion.hba.hub.shared.Comments;
import pillion.hba.hub.shared.Employees;
import pillion.hba.hub.shared.RedmineService;
import pillion.hba.hub.shared.Ticket;
import pillion.hba.hub.shared.TicketAttachment;
import pillion.hba.hub.shared.TicketAttachments;
import pillion.hba.hub.shared.Tickets;


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
	
	private TicketAttachment fromAttachment(Attachment attachment) {
		TicketAttachment attch = new TicketAttachment();
		
		attch.setAttachmentID(attachment.getId());
		attch.setCreatedOn(attachment.getCreatedOn());
		attch.setContentType(attachment.getContentType()) ;
		attch.setDescription(attachment.getDescription()) ;
		attch.setFileName(attachment.getFileName()) ;
		attch.setFileSize(attachment.getFileSize().intValue());
		attch.setToken(attachment.getToken());
		attch.setContentURL(attachment.getContentURL());
		return attch;
	}
	
	@Override
	public Tickets getTickets() {
		try {
			WPUser user = getLoggedInUser();
			System.out.println(user.getUserLogin());
			User redmineUser = RM.findUserByName(user.getUserLogin());			
			System.out.println(redmineUser.getId());
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
			return new Comments(RMDataService.getJournals(issueID).stream().collect(Collectors.toList()));
	}
	
	public Ticket newTicket(String ticketPriority, String ticketCategory, String ticketShortDescription, String ticketDetails) {
		try {
			WPUser user = getLoggedInUser();
			User redmineUser = RM.findUserByName(user.getUserLogin());
			Ticket issueID = fromIssue(RM.newIssue(redmineUser, ticketPriority, ticketCategory, ticketShortDescription, ticketDetails));
			return issueID;
		} catch (RedmineException e) {
			e.printStackTrace();
			return null;
		}
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
	
	public TicketAttachments getAttachments(int ticketID) {
		try {
			return new TicketAttachments(RM.getAttachments(ticketID).stream().map(i -> fromAttachment(i)).collect(Collectors.toList()));
		}
		catch(RedmineException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public Integer deleteAttachment(int attachmentID) {
		try {
			RM.deleteAttachment(attachmentID);
		}
		catch(RedmineException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public Employees getEmployees() {
//		try {
//			byte[] csv = RM.getAD();
//			CSVParser parser = CSVFormat.newFormat(',').parse(
//				    new InputStreamReader(new ByteArrayInputStream(csv), "UTF8"));
//				CSVPrinter printer = CSVFormat.newFormat(',').print(out);
//				for (CSVRecord record : parser) {
//				  try {
//				    printer.printRecord(record);
//				  } catch (Exception e) {
//				    throw new RuntimeException("Error at line "
//				      + parser.getCurrentLineNumber(), e);
//				  }
//				}
//				parser.close();
//				printer.close();
//		}
//		catch(RedmineException e) {
//			e.printStackTrace();
		
		return null;
	}
		
//		public void givenCSVFile_whenRead_thenContentsAsExpected() throws IOException {
//		    Reader in = new FileReader("book.csv");
//		    new FileReader();
//		    Iterable<CSVRecord> records = CSVFormat.DEFAULT
//		      .withHeader(HEADERS)
//		      .withFirstRecordAsHeader()
//		      .parse(in);
//		    for (CSVRecord record : records) {
//		        String author = record.get("author");
//		        String title = record.get("title");
//		        assertEquals(AUTHOR_BOOK_MAP.get(author), title);
//		    }
//		}
	}

