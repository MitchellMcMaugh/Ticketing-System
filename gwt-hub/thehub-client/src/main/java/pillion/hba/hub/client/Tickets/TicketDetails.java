package pillion.hba.hub.client.Tickets;

import java.util.Comparator;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.client.Cookies;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DateLabel;
import com.google.gwt.user.client.ui.FileUpload;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.FormPanel;
import com.google.gwt.user.client.ui.FormPanel.SubmitCompleteEvent;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.TextBox;

import pillion.hba.hub.shared.Comment;
import pillion.hba.hub.shared.Comments;
import pillion.hba.hub.shared.RedmineService;
import pillion.hba.hub.shared.RedmineServiceAsync;
import pillion.hba.hub.shared.Ticket;
import pillion.hba.hub.shared.TicketAttachment;
import pillion.hba.hub.shared.TicketAttachments;

public class TicketDetails {

	static FlowPanel vPanel = new FlowPanel();
	
	static DateTimeFormat sdf = DateTimeFormat.getFormat("dd/MM/yyyy");
	
	static FlexTable ticketDetailsTable = new FlexTable();
	
	
	

	static FlexTable commentsFlex = new FlexTable();
	static FlexTable attachmentsFlex = new FlexTable();
	

	static FileUpload upload = new FileUpload();
	static RedmineServiceAsync redmineService = GWT.create(RedmineService.class);
	static FormPanel form = new FormPanel();
	static TextBox hidden = new TextBox();
	static FlowPanel attachmentPanel = new FlowPanel();
	static Button submit = new Button();
	static FlexTable addAttachment = new FlexTable();
	static FlowPanel viewAttachments = new FlowPanel();
	static Image loadingBar = new Image();
	static TicketAttachments attachmentResults = new TicketAttachments();
	static Comments commentResults = new Comments();
	static Ticket thisTicket;
	static Image minusIconView;
	
	public static FlowPanel ticketDetails(Ticket t, String imageURL, String userName) {
		thisTicket = t;
		// Make sure there's no leftovers.
		RootPanel.get("tablebit").remove(ticketDetailsTable);
		ticketDetailsTable.clear();
		ticketDetailsTable.removeAllRows();
		
		commentsFlex.setStyleName("commentsFlex");
		
		// Back Button //
		Button backButton = new Button();
		backButton.setStyleName("buttonz");
		backButton.setText("Back");
		ticketDetailsTable.getFlexCellFormatter().setStyleName(0, 0, "BackButton");
		ticketDetailsTable.setWidget(0, 0, backButton);
		backButton.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {	
				exit();
				pillion.hba.hub.client.TicketPage.ticketsForm(false);
	        }
	    });
		// End Back Button //
		
		// Title //
		Label ticketTitleLabel = new Label(t.getTitle()); ticketTitleLabel.setStyleName("ticketDetailsTitleLabel");
		ticketDetailsTable.setWidget(0, 1, ticketTitleLabel);
		// End Title //
		
		// Date //
		DateTimeFormat fmt = DateTimeFormat.getFormat("dd/MM/yyyy, hh:mma z");
		String DLog = fmt.format(t.getLogged());
		Label ticketDateLoggedLabel = new Label("Date:"); ticketDateLoggedLabel.setStyleName("ticketDetailsDateLabel");	
		Label ticketDateLogged = new Label(DLog); ticketDateLogged.setStyleName("ticketDetailsResult");
		ticketDetailsTable.setWidget(1, 0, ticketDateLoggedLabel);
		ticketDetailsTable.setWidget(1, 1, ticketDateLogged);
		// End Date //
		
		// Priority //
		Label ticketPriorityLabel = new Label("Priority:"); ticketPriorityLabel.setStyleName("ticketDetailsPriorityLabel");
		Label ticketPriority = new Label(t.getPriority()); ticketPriority.setStyleName("ticketDetailsResult");
		ticketDetailsTable.setWidget(2, 0, ticketPriorityLabel);
		ticketDetailsTable.setWidget(2, 1, ticketPriority);
		// End Priority //
		
		// Category //
		Label ticketCategoryLabel = new Label("Category:"); ticketCategoryLabel.setStyleName("ticketDetailsCategoryLabel");
		Label ticketCategory = new Label(t.getCategory()); ticketCategory.setStyleName("ticketDetailsResult");
		ticketDetailsTable.setWidget(3, 0, ticketCategoryLabel);
		ticketDetailsTable.setWidget(3, 1, ticketCategory);
		// End Category//
		
		// Status //
		Label ticketStatusLabel = new Label("Status:"); ticketStatusLabel.setStyleName("ticketDetailsStatusLabel");
		Label ticketStatus = new Label(t.getStatus()); ticketStatus.setStyleName("ticketDetailsResult");
		ticketDetailsTable.setWidget(4, 0, ticketStatusLabel);
		ticketDetailsTable.setWidget(4, 1, ticketStatus);
		// End Status //
		
		// Assignee //
		Label ticketAssigneeLabel = new Label("Assignee:"); ticketAssigneeLabel.setStyleName("ticketDetailsAssigneeLabel");
		Label ticketAssignee = new Label(t.getAssignee()); ticketAssignee.setStyleName("ticketDetailsResult");
		ticketDetailsTable.setWidget(5, 0, ticketAssigneeLabel);
		ticketDetailsTable.setWidget(5, 1, ticketAssignee);
		//End Assignee //
		
		// Attachments //
		Label ticketAttachmentsLabel = new Label("Attachments:"); ticketAttachmentsLabel.setStyleName("attachmentDetailsLabel"); ticketDetailsTable.getFlexCellFormatter().setStyleName(6, 0, "topAttachments");
		ticketDetailsTable.setWidget(6, 0, ticketAttachmentsLabel);
		
		getAttachments(t);
		
		// End Attachments //
		
		// Add Attachment //
		form.remove(attachmentPanel);

		hidden.setText(String.valueOf(t.getTicketID()));
		hidden.setName("hidden");
		upload.setName("uploadFormElement");
		
		hidden.setVisible(false);

		form.setAction("/b/barnacle/attch");
		form.setEncoding(FormPanel.ENCODING_MULTIPART);
		form.setMethod(FormPanel.METHOD_POST);
		attachmentPanel.add(hidden);
		attachmentPanel.add(upload);
		
		
		form.add(attachmentPanel);
		
		addAttachment.setStyleName("addAttachmentFlowPanel");
		submit.setStyleName("uploadAttachmentButton");
		
		Image plusIconAdd = new Image("https://i.imgur.com/owzjNMv.png"); plusIconAdd.setStyleName("plusIcon");
		plusIconAdd.setPixelSize(20, 20);
		
		Image minusIconAdd = new Image("https://i.imgur.com/E96ThEn.png"); minusIconAdd.setStyleName("minusIcon");
		minusIconAdd.setPixelSize(20, 20);

		Label addNewAttachment = new Label("Add New Attachment"); addNewAttachment.setStyleName("addNewAttachmentLabel");

		
		plusIconAdd.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				addAttachment.remove(plusIconAdd);
				addAttachment.remove(addNewAttachment);
				addAttachment.setWidget(1, 1, minusIconAdd);
				addAttachment.setWidget(1, 2, form);
				addAttachment.setWidget(2, 2, submit);
				
	        }
	    });
		
		minusIconAdd.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				addAttachment.remove(minusIconAdd);
				addAttachment.remove(submit);
				addAttachment.remove(form);
				addAttachment.setWidget(1, 1, plusIconAdd);
				addAttachment.setWidget(1, 2, addNewAttachment);
	        }
	    });
		
		submit.setText("Upload");
		submit.setTitle("Submit selected attachment");
		submit.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				if (upload.getFilename() != null && upload.getFilename() != "") {
					submit.setEnabled(false);
					form.submit();
				}
				else {
					Window.confirm("Please select something to upload first.");
				}
	        }
	    });
		
		form.addSubmitCompleteHandler(new FormPanel.SubmitCompleteHandler() {
			public void onSubmitComplete(SubmitCompleteEvent event) {
				submit.setEnabled(true);
				Image tick = new Image();
				tick.setUrl("https://i.imgur.com/uWAGEhe.png");
				tick.setPixelSize(30, 30);
				addAttachment.setWidget(1, 4, tick);
				Timer timer = new Timer() {
	            @Override
	            public void run() { addAttachment.remove(tick); showAttachments(attachmentResults); attachmentsFlex.setWidget(1, 0, minusIconView);}};
		        timer.schedule(3000);
		        getAttachments(t);
		        form.reset();
		        
		      }
	    });

		addAttachment.setWidget(1, 1, plusIconAdd);
		addAttachment.setWidget(1, 2, addNewAttachment);

		ticketDetailsTable.setWidget(6, 1, addAttachment);
		//End Add Attachment //
		
		// View Attachments //
		Image plusIconView = new Image("https://i.imgur.com/owzjNMv.png"); plusIconView.setStyleName("plusIcon");
		plusIconView.setPixelSize(20, 20);
		
		minusIconView = new Image("https://i.imgur.com/E96ThEn.png"); minusIconView.setStyleName("minusIcon");
		minusIconView.setPixelSize(20, 20);
		
		Label viewAttachmentsLabel = new Label("View Attachments"); viewAttachmentsLabel.setStyleName("addNewAttachmentLabel");
		viewAttachmentsLabel.setVisible(true);
	
		plusIconView.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				showAttachments(attachmentResults);
				attachmentsFlex.remove(plusIconView);
				attachmentsFlex.setWidget(1, 0, minusIconView);
	        }
	    });
		
		minusIconView.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				attachmentsFlex.removeAllRows();
				attachmentsFlex.setWidget(1, 0, plusIconView);
				attachmentsFlex.setWidget(1, 1, viewAttachmentsLabel);
	        }
	    });
		
		attachmentsFlex.setWidget(1, 0, plusIconView);
		attachmentsFlex.setWidget(1, 8, viewAttachmentsLabel);
		
		// End View Attachment //
		
		// Attachments Table //
		attachmentsFlex.setVisible(true);
		ticketDetailsTable.setWidget(8, 1, attachmentsFlex);
		//End Attachments Table //
		
		// Ticket Details //
		Label ticketDetailsLabel = new Label("Description:"); ticketDetailsTable.getFlexCellFormatter().setStyleName(9, 0, "ticketDetailsDetailsLabel");
		Label ticketDetails = new Label(t.getDescription()); ticketDetails.setStyleName("ticketDetailsDetails");
		
		ticketDetailsTable.setWidget(9, 0, ticketDetailsLabel);
		ticketDetailsTable.setWidget(9, 1, ticketDetails);
		// End Ticket Details //
		
		// Add Comment //
		Image userImage2 = new Image(imageURL); userImage2.setStyleName("userImage"); commentsFlex.getFlexCellFormatter().setStyleName(0, 0, "commentsFlexImageTop");
		userImage2.setPixelSize(50, 50);
		commentsFlex.setWidget(0, 0, userImage2);
		commentsFlex.getFlexCellFormatter().setRowSpan(0, 0, 1);
		
		Label nameLabel2 = new Label(userName); commentsFlex.getFlexCellFormatter().setStyleName(1, 0, "commentsFlexName");
		commentsFlex.setWidget(1, 0, nameLabel2);
		commentsFlex.getFlexCellFormatter().setRowSpan(1, 0, 1);
		
		TextArea ticketAddCommentTextArea = new TextArea(); ticketAddCommentTextArea.setStyleName("buttonzFlexTextArea"); commentsFlex.getFlexCellFormatter().setStyleName(0, 1, "commentsFlexComment");
		commentsFlex.setWidget(0, 1, ticketAddCommentTextArea);
		ticketAddCommentTextArea.setVisibleLines(5);
		commentsFlex.getFlexCellFormatter().setRowSpan(0, 1, 2);
		
		Button ticketSubmitCommentButton = new Button("Submit Comment"); 
		ticketSubmitCommentButton.setStyleName("buttonz"); 
		commentsFlex.getFlexCellFormatter().setStyleName(0, 2, "commentsFlexSubmitButton");
		commentsFlex.getFlexCellFormatter().setRowSpan(0, 2, 2);
		ticketSubmitCommentButton.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				String comment = ticketAddCommentTextArea.getText();
				//Submits comment if not blank.
				if (comment != "" && comment != null) {
				redmineService.newComment(comment, t.getTicketID(), new AsyncCallback<Comment>() {
					public void onFailure(Throwable e) { throw new RuntimeException(e); }
					public void onSuccess(Comment result) {							
						ticketAddCommentTextArea.setText(null);
						getComments(t);
						
					}});
				}
				else {Window.alert("Comment must not be blank!");}
	        }
	    });
		commentsFlex.setWidget(0, 2, ticketSubmitCommentButton);
		// End Add Comment //	
		
		// Comments //
		commentsFlex.setStyleName("commentsFlex");
		getComments(t);
		
		// End Comments //
		
		ticketDetailsTable.setStyleName("detailsFlex");
			
		vPanel.add(ticketDetailsTable);

		vPanel.add(commentsFlex);
		
		return vPanel;
	}
	
	public static TicketAttachments getAttachments(Ticket t) {
		redmineService.getAttachments(t.getTicketID(), new AsyncCallback<TicketAttachments>() {
			public void onFailure(Throwable e) { throw new RuntimeException(e); }
			public void onSuccess(TicketAttachments result) {
				attachmentResults = result;
			}
		});
		return attachmentResults;
	}
	
	public static void showAttachments(TicketAttachments result) {
		attachmentsFlex.removeAllRows();
		if (result.size() > 0) {
			result.sort(Comparator.comparing(TicketAttachment::getCreatedOn));
			//Name
			Label ticketAttachmentHeader = new Label("Name"); attachmentsFlex.getFlexCellFormatter().setStyleName(1, 1, "ticketAttachmentHeader");
			attachmentsFlex.setWidget(1, 1, ticketAttachmentHeader);
			//Size
			Label ticketAttachmentSizeHeader = new Label("Size"); attachmentsFlex.getFlexCellFormatter().setStyleName(1, 2, "ticketAttachmentSizeHeader");
			attachmentsFlex.setWidget(1, 2, ticketAttachmentSizeHeader);
			//Date
			Label ticketAttachmentDateHeader = new Label("Date"); attachmentsFlex.getFlexCellFormatter().setStyleName(1, 3, "ticketAttachmentDateHeader");
			attachmentsFlex.setWidget(1, 3, ticketAttachmentDateHeader);

			attachmentsFlex.getFlexCellFormatter().setStyleName(1, 0, "ticketAttachmentNumberHeader");
			
			for (int i = 0; i <= result.size() - 1; i++) {
				
				
				//Number
				Label ticketAttachmentNumber = new Label(String.valueOf(i + 1) + ". "); attachmentsFlex.getFlexCellFormatter().setStyleName(i + 2, 0, "ticketAttachmentNumber"); ticketAttachmentNumber.setStyleName("rightText");
				attachmentsFlex.setWidget(2 + i, 0, ticketAttachmentNumber); 
				
				//Attachment Name
				Label ticketAttachment = new Label(result.get(i).getFileName()); attachmentsFlex.getFlexCellFormatter().setStyleName(i + 2, 1, "ticketAttachment");
				attachmentsFlex.setWidget(2 + i, 1, ticketAttachment); 
				
				//Attachment Size
				Label ticketAttachmentSize = new Label(String.valueOf(result.get(i).getFileSize()/1024) + "KB");  attachmentsFlex.getFlexCellFormatter().setStyleName(i + 2, 2, "ticketAttachmentSize");
				attachmentsFlex.setWidget(2 + i, 2, ticketAttachmentSize);
				
				//Attachment Date Created
				Label ticketAttachmentDate = new Label(DateTimeFormat.getFormat("dd-MM-yyyy").format(result.get(i).getCreatedOn())); attachmentsFlex.getFlexCellFormatter().setStyleName(i + 2, 3, "ticketAttachmentDate");
				attachmentsFlex.setWidget(2 + i, 3, ticketAttachmentDate); 
				
				//Delete Icon
				Image deleteImage = new Image("https://i.imgur.com/VDmbu2t.png"); attachmentsFlex.getFlexCellFormatter().setStyleName(i + 2, 4, "ticketAttachmentBin"); deleteImage.setStyleName("ticketAttachmentBin");
				deleteImage.setPixelSize(15, 15);
				attachmentsFlex.setWidget(2 + i, 4, deleteImage); 
				
				//Hidden Attachment ID
				Label ticketAttachmentID = new Label(String.valueOf(result.get(i).getAttachmentID()));
				attachmentsFlex.setWidget(2 + i, 5, ticketAttachmentID); 
				ticketAttachmentID.setVisible(false);
				
				//Hidden Attachment File Type
				Label ticketAttachmentFileType = new Label(result.get(i).getContentType());
				attachmentsFlex.setWidget(2 + i, 6, ticketAttachmentFileType); 
				ticketAttachmentFileType.setVisible(false);
				
				ticketAttachment.addClickHandler(new ClickHandler() {
					public void onClick(ClickEvent event) {
						//gets the index of the cell you clicked on
			            int cellIndex = attachmentsFlex.getCellForEvent(event).getCellIndex();
			            //gets the index of the row you clicked on
			            int rowIndex = attachmentsFlex.getCellForEvent(event).getRowIndex();

			            //Gets the string and strips any HTML tags
			            String fileid = attachmentsFlex.getFlexCellFormatter().getElement(rowIndex,cellIndex + 4).getInnerHTML().replaceAll("\\<[^>]*>","");
			            String filename = attachmentsFlex.getFlexCellFormatter().getElement(rowIndex,cellIndex).getInnerHTML().replaceAll("\\<[^>]*>","");
			            String type = attachmentsFlex.getFlexCellFormatter().getElement(rowIndex,cellIndex + 5).getInnerHTML().replaceAll("\\<[^>]*>","");
			            
			            //Sets the cookie so the servlet can identify the file.
			        	Cookies.setCookie("fileType", type);						        	
			        	Cookies.setCookie("fileName", filename);
			        	Cookies.setCookie("fileID", fileid);
			        	Window.open(GWT.getModuleBaseURL() + "DownloadServlet", "", "");
			        }
			    });
				
				deleteImage.addClickHandler(new ClickHandler() {
					public void onClick(ClickEvent event) {
						//gets the index of the cell you clicked on
			            int cellIndex = attachmentsFlex.getCellForEvent(event).getCellIndex();
			            //gets the index of the row you clicked on
			            int rowIndex = attachmentsFlex.getCellForEvent(event).getRowIndex();

			            String fileid = attachmentsFlex.getFlexCellFormatter().getElement(rowIndex,cellIndex + 1).getInnerHTML().replaceAll("\\<[^>]*>","");
			            redmineService.deleteAttachment(Integer.valueOf(fileid), new AsyncCallback<Integer>() {
			            	public void onFailure(Throwable e) { throw new RuntimeException(e); }
							
							public void onSuccess(Integer result) {		
								attachmentsFlex.removeRow(rowIndex);
								for(int i=rowIndex; i < attachmentsFlex.getRowCount(); i++) {
									String oldText = attachmentsFlex.getText(i, 0);
									oldText = oldText.replaceAll("(\\.)", "");
									int newText = Integer.valueOf(oldText.trim()) - 1;
									attachmentsFlex.setText(i, 0, String.valueOf(newText) + ". ");
								}
							}
						});
		            }
		    	});
			}
		}
		
		if (result.size() == 0) {
			Label noAttachmentsLabel = new Label("No Attachments Found");
			
			attachmentsFlex.setWidget(1, 8, noAttachmentsLabel); noAttachmentsLabel.setStyleName("addNewAttachmentLabel");
			
			
		}
	}
	
	
	public static void getComments(Ticket t) {
		
		loadingBar.setUrl("https://i.imgur.com/u8dhq7M.gif");
		RootPanel.get("newticketbit").add(loadingBar);
		loadingBar.setStyleName("loadingBar");
		
		redmineService.getComments(t.getTicketID(), new AsyncCallback<Comments>() {
			public void onFailure(Throwable e) { throw new RuntimeException(e);}
			public void onSuccess(Comments result) {
				
				
				result.sort(Comparator.comparing(Comment::getLogged).reversed());
				commentResults = result;
				showComments(commentResults, t);
				RootPanel.get("newticketbit").remove(loadingBar);
			}
		});
	}
	
	public static void showComments(Comments result, Ticket t) {
		
		//Sort by date reversed.
		
		//Flextable position below new comment box.
		int NamePosition = 4;
		int PicPosition = 3;
		if (t == thisTicket) {
			for (int i = 0;i < result.size(); i++) {					
				if (result.get(i).getComment() != null && result.get(i).getComment() != "") {		
					NamePosition = NamePosition + 2;
					PicPosition = PicPosition + 2;
					
					//Image
					Image userImage = new Image(result.get(i).getImageURL()); userImage.addStyleName("userImage"); commentsFlex.getFlexCellFormatter().setStyleName(PicPosition, 0, "commentsFlexImage");
					userImage.setPixelSize(50, 50);
					commentsFlex.setWidget(PicPosition, 0, userImage);
					commentsFlex.getFlexCellFormatter().setRowSpan(PicPosition, 0, 1);
					
					//Name
					Label nameLabel = new Label(result.get(i).getUser()); commentsFlex.getFlexCellFormatter().setStyleName(NamePosition, 0, "commentsFlexName");
					commentsFlex.setWidget(NamePosition, 0, nameLabel);
					commentsFlex.getFlexCellFormatter().setRowSpan(NamePosition, 0, 1);
					
					//Comment
					Label commentLabel = new Label(result.get(i).getComment()); commentsFlex.getFlexCellFormatter().setStyleName(PicPosition, 1, "commentsFlexComment"); commentLabel.setStyleName("commentsFlexComment");
					commentsFlex.setWidget(PicPosition, 1, commentLabel);
					commentsFlex.getFlexCellFormatter().setRowSpan(PicPosition, 1, 2);
					
					//DateTime
					DateTimeFormat fmt = DateTimeFormat.getFormat("hh:mm a dd/MM/yyyy");
					DateLabel dateTimeLabel = new DateLabel(fmt);
					
					dateTimeLabel.setValue(result.get(i).getLogged()); commentsFlex.getFlexCellFormatter().setStyleName(PicPosition, 2,"commentsFlexDate" );
					commentsFlex.setWidget(PicPosition, 2, dateTimeLabel);
					commentsFlex.getFlexCellFormatter().setRowSpan(PicPosition, 2, 2);
	
				}
			}
		}
	}
	
	public static void exit() {
		
		addAttachment.removeAllRows();
		
		attachmentsFlex.removeAllRows();
		
		RootPanel.get("newticketbit").remove(loadingBar);
		
		ticketDetailsTable.removeAllRows();
		
		ticketDetailsTable.remove(addAttachment);
		
		commentsFlex.removeAllRows();
		commentsFlex.clear();
		vPanel.remove(commentsFlex);
		

		vPanel.clear();
	}
	
	public static int ordinalIndexOf(String str, String substr, int n) {
	    int pos = str.indexOf(substr);
	    while (--n > 0 && pos != -1)
	        pos = str.indexOf(substr, pos + 1);
	    return pos;
	}
	
	 
}