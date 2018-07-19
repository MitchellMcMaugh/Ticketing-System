package pillion.hba.hub.server;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Objects;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItemIterator;
import org.apache.commons.fileupload.FileItemStream;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.fileupload.util.Streams;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
 
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;


import com.taskadapter.redmineapi.RedmineException;

import pillion.hba.hub.server.rm.RM;

//import pillion.paratus.server.mongo.MongoDAO;
//import pillion.paratus.shared.model.Attachment;
//import pillion.paratus.shared.model.Matter;


@WebServlet("/barnacle/attch")
public class AttachmentServlet  extends HttpServlet {
	

//	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		System.out.println("GOT HERE 5");	
//		System.out.println("GOT HERE 5.2");
//		//l.info("sandboxId {}", sandboxId);
//		
//		System.out.println("GOT HERE 6");
//		
//	    ServletFileUpload upload = new ServletFileUpload();
//	    try {
//	    	System.out.println("GOT HERE 7");
//			FileItemIterator iter = upload.getItemIterator(request);
//			
//			
//			
//			boolean isMultipart = ServletFileUpload.isMultipartContent(request);
//			System.out.println(isMultipart);
//			
//			System.out.println("GOT HERE 8");
//			while (iter.hasNext()) {
//					System.out.println("GOT HERE 9");
//			    FileItemStream item = iter.next();
//			    	System.out.println("GOT HERE 10");
//			    String name = item.getFieldName();
//			    	System.out.println("GOT HERE 11");
//			    InputStream fileContent = item.openStream();
//			    	System.out.println("GOT HERE 12");
//			    if (item.isFormField()) {
//			        //l.trace("Form field {} with value {} detected.", name , Streams.asString(fileContent));
//			        	System.out.println("GOT HERE 13");
//			    } else {
//			    	//l.trace("File field {} with file name {} detected.", name , item.getName());
//			    		System.out.println("GOT HERE 14");
//			    	try {RM.newAttachment(408, item.getName(), item.getContentType(), fileContent);}
//			    	
//			    	catch (RedmineException e){e.printStackTrace();}
//			    	
//			    }
//			    
//			    System.out.println("GOT HERE 15");
//			}
//		} catch (FileUploadException e) {
//			//l.error("fuck",e);
//			throw new ServletException(e);
//		}
//	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// checks if the request actually contains upload file
		
		ServletFileUpload upload = new ServletFileUpload();
		
        if (!ServletFileUpload.isMultipartContent(request)) {
            // if not, we stop here
            System.out.println("Error: Form must has enctype=multipart/form-data.");
            return;
        }
        System.out.println("Got Here Test 1");
        System.out.println("Got Here Test 2");
        FileItemIterator iter;
        System.out.println("Got Here Test 3");
		try {
			System.out.println("Got Here Test 4");
			iter = upload.getItemIterator(request);
			System.out.println(iter.toString());
			System.out.println("Got Here Test 5");
        while (iter.hasNext()) {
        	System.out.println("Got Here Test 6");
            FileItemStream item = iter.next();
            System.out.println("Got Here Test 7");
            String name = item.getFieldName();
            System.out.println("Got Here Test 8");
            InputStream stream = item.openStream();
            System.out.println("Got Here Test 9");
            if (item.isFormField()) {
            	System.out.println("Got Here Test 10");
                System.out.println("Form field " + name + " with value " + Streams.asString(stream) + " detected.");
                System.out.println("Got Here Test 11");
            } else {
            	System.out.println("Got Here Test 12");
                System.out.println("File field " + name + " with file name " + item.getName() + " detected.");
                System.out.println("Got Here Test 13");
                try {RM.newAttachment(408, item.getName(), item.getContentType(), stream);}
		    	
		    	catch (RedmineException e){e.printStackTrace();}
            	}
            }
        System.out.println("Got Here Test 14");
		} catch (FileUploadException e) {
			e.printStackTrace();
		}
		System.out.println("Got Here Test 15");
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
