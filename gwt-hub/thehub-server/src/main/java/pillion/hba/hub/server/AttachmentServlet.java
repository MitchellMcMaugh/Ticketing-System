package pillion.hba.hub.server;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileItemIterator;
import org.apache.commons.fileupload.FileItemStream;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.fileupload.util.Streams;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.taskadapter.redmineapi.RedmineException;

import pillion.hba.hub.server.rm.RM;

//import pillion.paratus.server.mongo.MongoDAO;
//import pillion.paratus.shared.model.Attachment;
//import pillion.paratus.shared.model.Matter;


@WebServlet("/barnacle/attch")
public class AttachmentServlet  extends HttpServlet {
	
	 public static File file;
	 private static final String FILE_SEPERATOR = System.getProperty("file.separator");
	
//	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		// checks if the request actually contains upload file
//		ServletFileUpload upload = new ServletFileUpload();
//		
//		try {
//		    
//		
//		if (!ServletFileUpload.isMultipartContent(request)) {
//		      throw new FileUploadException("error multipart request not found");
//		}
//		
//		FileItemFactory fileItemFactory = new DiskFileItemFactory();
//	    ServletFileUpload servletFileUpload = new ServletFileUpload(fileItemFactory);
//	    
//	    FileItemIterator fileItemIterator = servletFileUpload.getItemIterator(request);
//		
//	    while (fileItemIterator.hasNext()) {
//	        FileItemStream fileItemStream = fileItemIterator.next();
//	   
//	        String filePath = fileItemStream.getName();
//	        String fileName = filePath.substring(filePath.lastIndexOf(FILE_SEPERATOR) + 1);
//	   
//	        String uploadDirectory = new String();
//	        
//	        file = new File(uploadDirectory, fileName);
//	   
//	        System.out.println(String.format("uploaded file %s", file.getAbsolutePath()));
//	      }
//	    
//        try {RM.newAttachment(529, file.getName(), file.getName(), file);}
//    	
//    	catch (RedmineException e){e.printStackTrace();}
//	            	
//		} catch (FileUploadException fue) {
//		      throw new ServletException(fue);
//		    }
//
//		}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Got Here 1");
		ServletFileUpload upload = new ServletFileUpload();
		System.out.println("Got Here 2");
		try {
			System.out.println("Got Here 3");
			FileItemIterator iter = upload.getItemIterator(request);
			System.out.println("Got Here 4");
			while (iter.hasNext()) {
				System.out.println("Got Here 5");
				
			    FileItemStream item = iter.next();
			    System.out.println("Got Here 6");
			    String name = item.getFieldName();
			    System.out.println("Got Here 7");
			    InputStream fileContent = item.openStream();
			    System.out.println("Got Here 8");
			    
			    System.out.println(item.getContentType());
			    System.out.println(item.getFieldName());
			    System.out.println(item.getName());
			    
			    
			    if (item.isFormField()) {
			    	System.out.println("Got Here 9");
			    } else {
			    	System.out.println("Got Here 10");
			    	 try {RM.newAttachment(529, item.getName(), item.getContentType(), fileContent);}
			     	
			     	catch (RedmineException e){e.printStackTrace();}
			    	 System.out.println("Got Here 11");
			    }
			}
		}
		catch (FileUploadException e) {
			System.out.println("Got Here 12");
			throw new ServletException(e);
		}
		System.out.println("Got Here 13");
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
	
	//Remove 1
	
//	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		l.info("doPost");
//				
//		String sandboxId = request.getPathInfo().substring(1);
//		l.info("sandboxId {}", sandboxId);
//		
//	    ServletFileUpload upload = new ServletFileUpload();
//	    try {
//			FileItemIterator iter = upload.getItemIterator(request);
//			while (iter.hasNext()) {
//			    FileItemStream item = iter.next();
//			    String name = item.getFieldName();
//			    InputStream fileContent = item.openStream();
//			    if (item.isFormField()) {
//			        l.trace("Form field {} with value {} detected.", name , Streams.asString(fileContent));
//			    } else {
//			    	l.trace("File field {} with file name {} detected.", name , item.getName());
//			    	mongoDao.saveFile(sandboxId, item.getName(), item.getContentType(), fileContent);
//			    }
//			}
//		} catch (FileUploadException e) {
//			l.error("fuck",e);
//			throw new ServletException(e);
//		}
//	}
	
	//Remove 2
	
}
