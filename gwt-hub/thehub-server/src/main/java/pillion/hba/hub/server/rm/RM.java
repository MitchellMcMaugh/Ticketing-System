package pillion.hba.hub.server.rm;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.ArrayList;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.*;

import com.taskadapter.redmineapi.IssueManager;
import com.taskadapter.redmineapi.Params;
import com.taskadapter.redmineapi.RedmineException;
import com.taskadapter.redmineapi.RedmineManager;
import com.taskadapter.redmineapi.RedmineManagerFactory;
import com.taskadapter.redmineapi.bean.CustomFieldDefinition;
import com.taskadapter.redmineapi.bean.Issue;
import com.taskadapter.redmineapi.bean.IssueFactory;
import com.taskadapter.redmineapi.bean.User;
import com.taskadapter.redmineapi.internal.ResultsWrapper;
import com.taskadapter.redmineapi.AttachmentManager;
import com.taskadapter.redmineapi.bean.Attachment;

import pillion.hba.hub.shared.Comments;

import com.taskadapter.redmineapi.IssueManager;
import com.taskadapter.redmineapi.Include;
import com.taskadapter.redmineapi.bean.Journal;
import com.taskadapter.redmineapi.bean.JournalFactory;

public class RM {

	static String uri = "http://redmine";
	// String apiAccessKey = "2f8b4385922ae534781d500c6b52932f4f605a2b";
	static String apiAccessKey = "02d0937d07cef5031ccc67e98483fb739c46d5d5";
//	static String projectKey = "Support-Test";
	static String projectKey = "Support-Test";

	public static void main(String... args) throws RedmineException {
//		newTicket();
		
		for (Issue issue : findTickets(54)) {
			System.out.println(issue.toString());
		}
		
		
	}

	public static List<Issue> findTickets(Integer userId) throws RedmineException {
		Integer queryId = null; // any

		RedmineManager mgr = RedmineManagerFactory.createWithApiKey(uri, apiAccessKey);
		
		System.out.println("findTickets userId " + userId);
		
		for(CustomFieldDefinition cfd :	mgr.getCustomFieldManager().getCustomFieldDefinitions()) {
			System.out.println(cfd);
		}
		 
		 
		Params params = new Params();
		params.add("project_id", "10");
		params.add("cf_1","" + userId);
//		params.add("LoggedBy","craiglee");
		ResultsWrapper<Issue> issues = mgr.getIssueManager().getIssues(params);
		List<Issue> results = issues.getResults();
		System.out.println("findTickets results " + results.size());
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
		}else {
			return null;
		}
	}

	//Added By Mitchell McMaugh 22/05/2018
	//Function: Create New Ticket/ Issue
	public static Integer newTicket(String ticketCreator, String issuePriority, String issueCategory, String issueSubject, String issueDetails) throws RedmineException {
		//Variables
		
		RedmineManager mgr = RedmineManagerFactory.createWithApiKey(uri, apiAccessKey);
		String issueName = issueCategory + "// " + issueSubject;
		Issue issue = IssueFactory.create(10, issueName);
		Issue ticketIssue = mgr.getIssueManager().createIssue(issue);
		
		ticketIssue.setAuthorName(ticketCreator);
		
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
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		ticketIssue.setSubject(issueSubject);
		ticketIssue.setDescription(issueDetails);
		ticketIssue.setSubject(issueName);
		
		Integer issueID = ticketIssue.getId();
		
		mgr.getIssueManager().update(ticketIssue);
		
		return issueID;
		
	}
	
	public static void newCommentRM(User redmineUser, String comment, int issueID) throws RedmineException {
		RedmineManager mgr = RedmineManagerFactory.createWithApiKey(uri, apiAccessKey);
		Issue issue = mgr.getIssueManager().getIssueById(issueID);
		Date date = new Date();

		issue.setNotes(date + "\n" + "###" + " " + redmineUser.getFullName() + ":" + "\n \n" +  comment);
		mgr.getIssueManager().update(issue);

	}

	public static void newAttachment(Integer issueID, byte[] fileArray)  throws IOException, RedmineException {
		RedmineManager mgr = RedmineManagerFactory.createWithApiKey(uri, apiAccessKey);
		
		//Attachment attachmentFile = mgr.getAttachmentManager().uploadAttachment(String.valueOf(issueID), "Image", fileArray);
		
		Attachment attachmentFile = mgr.getAttachmentManager().uploadAttachment("Test", "Type", fileArray);

		mgr.getIssueManager().getIssueById(issueID).addAttachment(attachmentFile);
		
//		try (FileOutputStream fos = new FileOutputStream("/var/lib/redmine/default/files")) {
//			   File myFile = fos.write(fileArray);
//			   mgr.getAttachmentManager().addAttachmentToIssue(issueID, myFile, "Image");
//			   
//			   //fos.close(); There is no more need for this line since you had created the instance of "fos" inside the try. And this will automatically close the OutputStream
//			}
		
		
	}
	
}

