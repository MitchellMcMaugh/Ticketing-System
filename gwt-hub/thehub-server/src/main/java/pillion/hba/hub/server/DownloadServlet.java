package pillion.hba.hub.server;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pillion.hba.hub.server.rm.RM;

@WebServlet("/barnacle/DownloadServlet")
public class DownloadServlet extends HttpServlet{
	static String fileName;
	static String fileType;
	static String fileID;
	
    protected void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {
        Cookie[] cookies = request.getCookies();
        try {
            // get cookies, generate PDF.
        	if(cookies != null) {
    			for (Cookie c : cookies) {
    				if (c.getName().startsWith("fileName")) {
    					fileName = c.getValue();
    				}
    				if (c.getName().startsWith("fileType")) {
    					fileType = c.getValue();
    				}
    				if (c.getName().startsWith("fileID")) {
    					fileID = c.getValue();
    				}
    			}
    		}
            byte[] bytes = getFile(fileName, fileID);
         sendFile(response, bytes, fileName, fileType);
        } catch (Exception ex) {
        	 ex.printStackTrace();
        }
    }

    byte[] getFile(String filename, String ID) {
        byte[] bytes = null;
        try {
            bytes = RM.downloadAttachment(ID);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bytes;
    }

    void sendFile(HttpServletResponse response, byte[] bytes, String name, String type) throws IOException {
        ServletOutputStream stream = null;
        stream = response.getOutputStream();
        response.setContentType(type);
        response.addHeader("Content-Type", type);
        response.addHeader("Content-Disposition", "inline; filename=" + name);
        response.setContentLength((int) bytes.length);
        stream.write(bytes);
        stream.close();
    }
}


