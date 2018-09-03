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
		ServletFileUpload upload = new ServletFileUpload();
		try {
			FileItemIterator iter = upload.getItemIterator(request);
			while (iter.hasNext()) {
			    FileItemStream item = iter.next();
			    InputStream fileContent = item.openStream();
			    if (item.isFormField()) {
			    	byte[] str = new byte[fileContent.available()];
		            fileContent.read(str);
		            issueID = new String(str,"UTF8");
			    } else {
			    	if (item.getName() != null && item.getName() != "") {
				    	try {
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
	
}
