package pillion.hba.hub.server;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItemIterator;
import org.apache.commons.fileupload.FileItemStream;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.taskadapter.redmineapi.RedmineException;

import pillion.hba.hub.server.rm.RM;

@WebServlet("/barnacle/attch")
public class AttachmentServlet  extends HttpServlet {
	
	public static String issueID = new String();
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("1");
		ServletFileUpload upload = new ServletFileUpload();
		System.out.println("2");
		try {
			System.out.println("3");
			FileItemIterator iter = upload.getItemIterator(request);
			System.out.println("4");
			while (iter.hasNext()) {
				System.out.println("5");
			    FileItemStream item = iter.next();
			    System.out.println("6");
			    InputStream fileContent = item.openStream();
			    System.out.println("7");
			    if (item.isFormField()) {
			    	System.out.println("8");
			    	byte[] str = new byte[fileContent.available()];
			    	System.out.println("9");
		            fileContent.read(str);
		            System.out.println("10");
		            issueID = new String(str,"UTF8");
		            System.out.println("11");
			    } else {
			    	System.out.println("12");
			    	System.out.println(item.getName());
			    	System.out.println("13");
			    	System.out.println(item.getContentType());
			    	System.out.println("14");
			    	//&& item.getContentType() != "application/octet-stream"
			    	if (item.getName() != null && item.getName() != "") {
			    		System.out.println("15");
				    	try {System.out.println("16");
				    		RM.newAttachment(Integer.parseInt(issueID), item.getName(), item.getContentType(), fileContent);}
				     	catch (RedmineException e){e.printStackTrace();}
			    	}
			    }
			}
		}
		catch (FileUploadException e) {
			throw new ServletException(e);
		}
	}
	
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	      resp.setContentType("text/html");
	      req.getSession();
	      PrintWriter out = resp.getWriter();
	      out.println("<html>");
	      out.println("<head>");
	        out.println("Hello from servlet!");
	      out.println("</head>");
	      out.println("<body>");
	}
}
