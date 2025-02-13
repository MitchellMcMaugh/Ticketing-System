package pillion.hba.hub.server;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pillion.hba.hub.server.wp.UserMetadata;
import pillion.hba.hub.server.wp.WPDataService;
import pillion.hba.hub.server.wp.WPUser;

@WebServlet("/barnacle/CookieMonster")
public class CookieMonster extends HttpServlet {
       
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	    resp.setContentType("text/html");
	    req.getSession();
	    PrintWriter out = resp.getWriter();
	    Cookie[] cookies = req.getCookies();
	    out.println("<html>");
	    out.println("<head>");
	    out.println("<style> table, th, td { border: 1px solid blue; border-collapse: collapse; } th, td { padding: 15px; text-align: left; } </style>");
	    out.println("<title>Cookie Monster</title>");
	    out.println("</head>");
	    out.println("<body>");

	    if ((cookies == null) || (cookies.length == 0)) {
	      out.println("<center><h1>No Cookies found</h1>");
	    } else {
	      out.println("<center><img src=\"http://images2.fanpop.com/images/photos/3500000/Cookie-Monster-cookie-monster-3512347-250-224.jpg\"> <table style=\"width:70%\">");
	      out.println("<h1>Cookies!</h1>");
	      out.printf("<tr><th>name</th><th>value</th><th>comment</th><th>max age</th></tr>");
	      for (Cookie c : cookies) {
	        out.printf("<tr><td>%s</td><td>%s</td><td>%s</td><td>%s</td></tr>",c.getName(),URLDecoder.decode(c.getValue(),"UTF-8").replaceAll("\\|", "\n|"), c.getComment(),c.getMaxAge());
	      }
	      out.println("</table></center>");
	      
	    }
	    WPUser user = WPDataService.munchCookies(cookies);
		out.println("<br/> <center> <h1>Wordpress User</h1> <table style=\"width:70%\">" + (user!=null?user.toHtmlRows():"<tr><th>No Login cookie</th><th>Or no user metadata</th></tr>") + "</table></center>");
	    UserMetadata metadata = user.getMetadata();
	    if(metadata != null) {
			out.println("<br/> <center> <h1>Wordpress User Metadata</h1> <table style=\"width:70%\">" + (user!=null?metadata.toHtmlRows():"<tr><th>No Login cookie</th><th>Or no user metadata</th></tr>") + "</table></center>");
	    	
	    }
	    out.println("</body>");
	    out.println("</html>");
	    out.flush();
		
		
	}

}
