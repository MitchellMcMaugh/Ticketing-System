package pillion.hba.hub.server.rm;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections4.map.HashedMap;

import com.taskadapter.redmineapi.Params;
import com.taskadapter.redmineapi.RedmineException;
import com.taskadapter.redmineapi.RedmineManager;
import com.taskadapter.redmineapi.RedmineManagerFactory;
import com.taskadapter.redmineapi.bean.CustomFieldDefinition;
import com.taskadapter.redmineapi.bean.Issue;
import com.taskadapter.redmineapi.bean.IssueFactory;
import com.taskadapter.redmineapi.bean.User;
import com.taskadapter.redmineapi.internal.ResultsWrapper;

public class RM {

	static String uri = "http://redmine";
	// String apiAccessKey = "2f8b4385922ae534781d500c6b52932f4f605a2b";
	static String apiAccessKey = "02d0937d07cef5031ccc67e98483fb739c46d5d5";
//	static String projectKey = "Support-Test";
	static String projectKey = "Support";

	public static void main(String... args) throws RedmineException {
//		newTicket();
		
		for (Issue issue : findTickets(54)) {
			System.out.println(issue.toString());
		}
		
	}

	public static List<Issue> findTickets(Integer userId) throws RedmineException {
		Integer queryId = null; // any

		RedmineManager mgr = RedmineManagerFactory.createWithApiKey(uri, apiAccessKey);
//		List<Issue> issues = mgr.getIssueManager().getIssues(projectKey, queryId);
		
//		Map<String,String> params = new HashedMap<>();
//		params.put("project_id", "7");
//		params.put("LoggedBy","craiglee");
//		ResultsWrapper<Issue> issues = mgr.getIssueManager().getIssues(params);
		
		
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

	public static void newTicket() throws RedmineException {
		RedmineManager mgr = RedmineManagerFactory.createWithApiKey(uri, apiAccessKey);
		Issue issue = IssueFactory.create(10, "test123");
//		Version ver = VersionFactory.create(512);
//		issue.setTargetVersion(ver);
		issue.setStatusId(8);
//		IssueCategory cat = IssueCategoryFactory.create(673);
//		issue.setCategory(cat);
//		ProjectManager projectManager = mgr.getProjectManager();
		// Project projectByKey = projectManager.getProjectByKey("testid");
		issue.setProjectId(10);
		mgr.getIssueManager().createIssue(issue);
	}
}
