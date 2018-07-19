package pillion.hba.hub.server.rm;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;

import com.taskadapter.redmineapi.Params;
import com.taskadapter.redmineapi.RedmineException;
import com.taskadapter.redmineapi.RedmineManager;
import com.taskadapter.redmineapi.RedmineManagerFactory;
import com.taskadapter.redmineapi.bean.CustomFieldDefinition;
import com.taskadapter.redmineapi.bean.CustomFieldFactory;
import com.taskadapter.redmineapi.bean.Issue;
import com.taskadapter.redmineapi.bean.IssueFactory;
import com.taskadapter.redmineapi.bean.User;
import com.taskadapter.redmineapi.internal.ResultsWrapper;

import pillion.hba.hub.server.wp.WPUser;

import com.taskadapter.redmineapi.bean.Attachment;
import com.taskadapter.redmineapi.bean.CustomField;
import com.taskadapter.redmineapi.bean.Project;

import com.taskadapter.redmineapi.Include;
import com.taskadapter.redmineapi.bean.Journal;

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
//		List<Project> projects = mgr.getProjectManager().getProjects();
//		for (int i = 0; i <= projects.size(); i++) {
//			Project x = projects.get(i);
//			System.out.println(x.getId());
//			System.out.println(x.getName());
//		}
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
	public static Integer newTicket(User ticketCreator, String issuePriority, String issueCategory, String issueSubject, String issueDetails) throws RedmineException {
		
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
		
		ticketCreator.getId();
		
		
		//Try to set LoggedBy field.
		Integer issueID = ticketIssue.getId();
		String loggedBy = ticketCreator.getFullName();
		System.out.println(loggedBy);
		
		
		ticketIssue.getCustomFieldById(1).setValue(ticketCreator.getId().toString());
		
		

//		CustomField field = ticketIssue.getCustomFieldById(1);
//		ticketIssue.addCustomField(CustomFieldFactory.create(field.getId(), field.getName(), loggedBy));
		
		//Ticket Issue needs to be updated.
		mgr.getIssueManager().update(ticketIssue);
		
		//Testing to see if loggedBy worked.
//		System.out.println(ticketCreator.toString());
//		System.out.println(loggedBy);
		
		return issueID;
		
	}
	
	public static void newCommentRM(User redmineUser, String comment, int issueID) throws RedmineException {
		RedmineManager mgr = RedmineManagerFactory.createWithApiKey(uri, apiAccessKey);
		Issue issue = mgr.getIssueManager().getIssueById(issueID);
		Date date = new Date();

		issue.setNotes(date + "\n" + "###" + " " + redmineUser.getFullName() + ":" + "\n \n" +  comment);
		mgr.getIssueManager().update(issue);

	}

	public static void newAttachment(Integer issueID, String itemName, String itemType, InputStream fileArray)  throws IOException, RedmineException {
		RedmineManager mgr = RedmineManagerFactory.createWithApiKey(uri, apiAccessKey);
		
		System.out.println("GOT HERE 1");
		
		Attachment attachmentFile = mgr.getAttachmentManager().uploadAttachment(itemName, itemType, fileArray);
		
		System.out.println("GOT HERE 2");

		mgr.getIssueManager().getIssueById(issueID).addAttachment(attachmentFile);
		
		System.out.println("GOT HERE 3");
		
		
	}
	
	  

	
}

