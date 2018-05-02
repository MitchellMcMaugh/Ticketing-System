package pillion.hba.hub.server.rm;

import java.util.List;

import com.taskadapter.redmineapi.RedmineException;
import com.taskadapter.redmineapi.RedmineManager;
import com.taskadapter.redmineapi.RedmineManagerFactory;
import com.taskadapter.redmineapi.bean.Issue;
import com.taskadapter.redmineapi.bean.IssueFactory;

public class RM {

	static String uri = "http://redmine";
	// String apiAccessKey = "2f8b4385922ae534781d500c6b52932f4f605a2b";
	static String apiAccessKey = "02d0937d07cef5031ccc67e98483fb739c46d5d5";
	static String projectKey = "Support-Test";

	public static void main(String... args) throws RedmineException {
		newTicket();
		findTickets();
	}

	public static List<Issue> findTickets() throws RedmineException {
		Integer queryId = null; // any

		RedmineManager mgr = RedmineManagerFactory.createWithApiKey(uri, apiAccessKey);
		List<Issue> issues = mgr.getIssueManager().getIssues(projectKey, queryId);
		for (Issue issue : issues) {
			System.out.println(issue.toString());
		}
		return issues;
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
