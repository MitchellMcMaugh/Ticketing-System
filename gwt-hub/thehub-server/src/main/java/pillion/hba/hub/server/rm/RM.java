package pillion.hba.hub.server.rm;

import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import com.taskadapter.redmineapi.Include;
import com.taskadapter.redmineapi.Params;
import com.taskadapter.redmineapi.RedmineException;
import com.taskadapter.redmineapi.RedmineManager;
import com.taskadapter.redmineapi.RedmineManagerFactory;
import com.taskadapter.redmineapi.bean.Attachment;
import com.taskadapter.redmineapi.bean.Issue;
import com.taskadapter.redmineapi.bean.IssueFactory;
import com.taskadapter.redmineapi.bean.Journal;
import com.taskadapter.redmineapi.bean.User;
import com.taskadapter.redmineapi.internal.ResultsWrapper;

import pillion.hba.hub.server.wp.WPUser;

public class RM {

	static String uri = "http://redmine";
	static String apiAccessKey = "02d0937d07cef5031ccc67e98483fb739c46d5d5";
	static String projectKey = "Support-Test";

	public static void main(String... args) throws RedmineException {	}

	public static List<Issue> findTickets(Integer userId) throws RedmineException {
		RedmineManager mgr = RedmineManagerFactory.createWithApiKey(uri, apiAccessKey);
		 
		Params params = new Params();
		params.add("project_id", "10");
		params.add("cf_1","" + userId);
		params.add("limit", "100");
		ResultsWrapper<Issue> issues = mgr.getIssueManager().getIssues(params);
		List<Issue> results = issues.getResults();
		
		//Ignore Closed Tickets
		results.removeIf(x -> x.getStatusId() == 5);

		return results;
	}
	
	public static Collection<Journal> findJournals(Integer issueID) throws RedmineException {
		RedmineManager mgr = RedmineManagerFactory.createWithApiKey(uri, apiAccessKey);
		Issue issue = mgr.getIssueManager().getIssueById(issueID, Include.journals);
		Collection<Journal> journals = issue.getJournals();
		
		return journals;
	}
	
	public static User findUserByName(String userName) throws RedmineException {
		RedmineManager mgr = RedmineManagerFactory.createWithApiKey(uri, apiAccessKey);
		ResultsWrapper<User> result = mgr.getUserManager().getUsers(Collections.singletonMap("name", userName));
		List<User> users = result.getResults();
		if(users.size() > 0) {
			return users.get(0);
		} else {
			return null;
		}
		
		
	}

	public static Issue newIssue(User ticketCreator, String issuePriority, String issueCategory, String issueSubject, String issueDetails) throws RedmineException {
		
		//Variables
		RedmineManager mgr = RedmineManagerFactory.createWithApiKey(uri, apiAccessKey);
		String issueName = issueSubject;
		Issue issue = IssueFactory.create(10, issueName);
		Issue ticketIssue = mgr.getIssueManager().createIssue(issue);
		
		//Set Priority
		switch (issuePriority) {
		case "Low":
			ticketIssue.setPriorityId(1);
			break;
		case "Normal":
			ticketIssue.setPriorityId(2);
			break;
		case "High":
			ticketIssue.setPriorityId(3);
			break;
		case "Urgent":
			ticketIssue.setPriorityId(4);
			break;
		default: ticketIssue.setPriorityId(1);}

		//Set Details
		ticketIssue.setSubject(issueSubject);
		ticketIssue.setDescription(issueDetails);
		ticketIssue.setSubject(issueName);
		ticketIssue.getCustomFieldById(4).setValue(issueCategory);
		
		Date theDate = new Date();
		
		ticketIssue.setStartDate(theDate);
		
		//Set Logged By
		ticketIssue.getCustomFieldById(1).setValue(ticketCreator.getId().toString());
		
		//Ticket Issue needs to be updated.
		mgr.getIssueManager().update(ticketIssue);
		
		return ticketIssue;
		
	}
	
	public static void newCommentRM(WPUser wpUser, String comment, int issueID) throws RedmineException {
		RedmineManager mgr = RedmineManagerFactory.createWithApiKey(uri, apiAccessKey);
		User rmUser = findUserByName(wpUser.getDisplayName());
		mgr.setOnBehalfOfUser(rmUser.toString());
		Issue issue = mgr.getIssueManager().getIssueById(issueID);
		issue.setNotes(comment);
		
		mgr.getIssueManager().update(issue);

	}

	public static void newAttachment(Integer issueID, String itemName, String itemType, InputStream fileArray)  throws IOException, RedmineException {
		RedmineManager mgr = RedmineManagerFactory.createWithApiKey(uri, apiAccessKey);
		Issue issue = mgr.getIssueManager().getIssueById(issueID, Include.attachments);
		Attachment attachment = mgr.getAttachmentManager().uploadAttachment(itemName, itemType, fileArray);
		issue.addAttachment(attachment);
		mgr.getIssueManager().update(issue);

	}
	
	public static Collection<Attachment> getAttachments(int issueID) throws RedmineException {
		RedmineManager mgr = RedmineManagerFactory.createWithApiKey(uri, apiAccessKey);
		Issue issue = mgr.getIssueManager().getIssueById(issueID, Include.attachments);
		
		Collection<Attachment> attachments = issue.getAttachments();
		return attachments;
	}
	
	public static byte[] downloadAttachment(String tickAttch) throws RedmineException {
		RedmineManager mgr = RedmineManagerFactory.createWithApiKey(uri, apiAccessKey);
		
		return mgr.getAttachmentManager().downloadAttachmentContent(mgr.getAttachmentManager().getAttachmentById(Integer.valueOf(tickAttch)));
	}
	  
	public static void deleteAttachment(int attachmentID) throws RedmineException {
		RedmineManager mgr = RedmineManagerFactory.createWithApiKey(uri, apiAccessKey);
		mgr.getAttachmentManager().delete(attachmentID);
	}

	public static byte[] getAD() throws RedmineException {
		RedmineManager mgr = RedmineManagerFactory.createWithApiKey(uri, apiAccessKey);
		Attachment csv = mgr.getAttachmentManager().getAttachmentById(176);
		return mgr.getAttachmentManager().downloadAttachmentContent(csv);
	}
	
}

