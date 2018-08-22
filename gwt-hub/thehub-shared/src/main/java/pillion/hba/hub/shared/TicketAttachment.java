package pillion.hba.hub.shared;

import java.io.Serializable;
import java.util.Date;

import com.google.gwt.user.client.ui.Image;

public class TicketAttachment implements Serializable{
	private Date createdOn;
	private String contentType;
	private String description;
	private String fileName;
	private Integer fileSize;
	private String token;
	private Integer attachmentID;
	private String contentURL;

	public int getAttachmentID() {return attachmentID;}
	public void setAttachmentID(int attachmentID) {this.attachmentID = attachmentID;}
	
	public Date getCreatedOn() {return createdOn;}
	public void setCreatedOn(Date createdOn) {this.createdOn = createdOn;}
	
	public String getContentType() {return contentType;}
	public void setContentType(String contentType) {this.contentType = contentType;}
	
	public String getDescription() {return description;}
	public void setDescription(String description) {this.description = description;}
	
	public String getFileName() {return fileName;}
	public void setFileName(String fileName) {this.fileName = fileName;}

	public int getFileSize() {return fileSize;}
	public void setFileSize(int fileSize) {this.fileSize = fileSize;}

	public String getToken() {return token;}
	public void setToken(String token) {this.token = token;}
	
	public String getContentURL() {return contentURL;}
	public void setContentURL(String contentURL ) {this.contentURL = contentURL;}
}
