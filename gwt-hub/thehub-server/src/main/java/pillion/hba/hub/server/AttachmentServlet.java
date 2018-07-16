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
	
	private static Logger l = LogManager.getLogger(AttachmentServlet.class);
	
	private static final long serialVersionUID = 1L;
    
    // location to store file uploaded
    private static final String UPLOAD_DIRECTORY = "upload";
 
    // upload settings
    private static final int MEMORY_THRESHOLD   = 1024 * 1024 * 3;  // 3MB
    private static final int MAX_FILE_SIZE      = 1024 * 1024 * 40; // 40MB
    private static final int MAX_REQUEST_SIZE   = 1024 * 1024 * 50; // 50MB

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
        if (!ServletFileUpload.isMultipartContent(request)) {
            // if not, we stop here
            PrintWriter writer = response.getWriter();
            writer.println("Error: Form must has enctype=multipart/form-data.");
            writer.flush();
            return;
        }
 
     // configures upload settings
        DiskFileItemFactory factory = new DiskFileItemFactory();
        // sets memory threshold - beyond which files are stored in disk
        factory.setSizeThreshold(MEMORY_THRESHOLD);
        // sets temporary location to store files
        factory.setRepository(new File(System.getProperty("java.io.tmpdir")));
 
        ServletFileUpload upload = new ServletFileUpload(factory);
         
        // sets maximum size of upload file
        upload.setFileSizeMax(MAX_FILE_SIZE);
         
        // sets maximum size of request (include file + form data)
        upload.setSizeMax(MAX_REQUEST_SIZE);
 
        // constructs the directory path to store upload file
        // this path is relative to application's directory
        String uploadPath = getServletContext().getRealPath("") + File.separator + UPLOAD_DIRECTORY;
         
        // creates the directory if it does not exist
        File uploadDir = new File(uploadPath);
        if (!uploadDir.exists()) {
            uploadDir.mkdir();
        }
 
        try {
            // parses the request's content to extract file data
            @SuppressWarnings("unchecked")
            List<FileItem> formItems = upload.parseRequest(request);
 
            if (formItems != null && formItems.size() > 0) {
                // iterates over form's fields
                for (FileItem item : formItems) {
                    // processes only fields that are not form fields
                    if (!item.isFormField()) {
                        String fileName = new File(item.getName()).getName();
                        String filePath = uploadPath + File.separator + fileName;
                        File storeFile = new File(filePath);
 
                        // saves the file on disk
                        item.write(storeFile);
                        request.setAttribute("message",
                            "Upload has been done successfully!");
                    }
                }
            }
        } catch (Exception ex) {
            request.setAttribute("message",
                    "There was an error: " + ex.getMessage());
        }
        // redirects client to message page
        getServletContext().getRequestDispatcher("/message.jsp").forward(
                request, response);
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
