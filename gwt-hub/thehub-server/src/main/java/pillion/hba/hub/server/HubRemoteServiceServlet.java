package pillion.hba.hub.server;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.IOUtils;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.google.gwt.user.server.rpc.SerializationPolicy;
import com.google.gwt.user.server.rpc.SerializationPolicyLoader;

import pillion.hba.hub.server.wp.WPDataService;
import pillion.hba.hub.server.wp.WPUser;
import pillion.hba.hub.server.wp.UserAvitars;
import pillion.hba.hub.server.wp.UserMetadata;;

public class HubRemoteServiceServlet extends RemoteServiceServlet {

	@Override
	protected SerializationPolicy doGetSerializationPolicy(HttpServletRequest request, String moduleBaseURL,
			String strongName) {
		
	    String contextPath = request.getContextPath();

	    String modulePath = null;
	    if (moduleBaseURL != null) {
	      try {
	        modulePath = new URL(moduleBaseURL).getPath();
	      } catch (MalformedURLException ex) {
	        // log the information, we will default
	        System.out.println("Malformed moduleBaseURL: " + moduleBaseURL + "\n" +  ex);
	      }
	    }
	    
		String contextRelativePath = modulePath.substring(contextPath.length());
		String serializationPolicyFilePath = SerializationPolicyLoader.getSerializationPolicyFileName(contextRelativePath  + strongName);
		
		System.out.printf("doGetSerializationPolicy \n    moduleBaseURL:%s, \n    strongName:%s, \n    modulePath:%s \n    serializationPolicyFilePath:%s\n", moduleBaseURL, strongName, modulePath, serializationPolicyFilePath);
	      
		InputStream is = getServletContext().getResourceAsStream(
	              serializationPolicyFilePath);
		
		try {
			List<String> readLines = IOUtils.readLines(is);
			System.out.println("doGetSerializationPolicy read file");
			for(String s : readLines) {
				System.err.println("        " + s);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return super.doGetSerializationPolicy(request, moduleBaseURL, strongName);
	}
	protected UserAvitars doStuff(WPUser user) {
		//WPUser user = WPDataService.munchCookies( getThreadLocalRequest().getCookies());
		int uid = user.getId();
		UserMetadata data = WPDataService.userMetadataFromUserName(uid);
		return data.wpUserAvatars;
	}
	
	protected WPUser getLoggedInUser() {
	
		return WPDataService.munchCookies( getThreadLocalRequest().getCookies());
	}

}
