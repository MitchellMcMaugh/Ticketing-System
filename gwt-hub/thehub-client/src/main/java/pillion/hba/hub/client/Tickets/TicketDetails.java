package pillion.hba.hub.client.Tickets;

import java.util.Comparator;

import com.google.gwt.core.client.GWT;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.DateLabel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.DecoratorPanel;
import com.google.gwt.user.client.ui.DockLayoutPanel.Direction;
import com.google.gwt.user.client.ui.DockPanel;
import com.google.gwt.user.client.ui.LayoutPanel;


import pillion.hba.hub.shared.RedmineService;
import pillion.hba.hub.shared.RedmineServiceAsync;
import pillion.hba.hub.shared.Ticket;
import pillion.hba.hub.shared.Comment;
import pillion.hba.hub.shared.Comments;

public class TicketDetails {

	static FlowPanel vPanel = new FlowPanel();
	//static LayoutPanel vPanel = new LayoutPanel();
	//static DockPanel vPanel = new DecoratorPanel();
	//static VerticalPanel vPanel = new VerticalPanel();
	static FlexTable ticketDetailsTable = new FlexTable();
	static FlexTable buttonsFlex = new FlexTable();
	static FlexTable titleFlex = new FlexTable();
	static FlexTable dateFlex = new FlexTable();
	static FlexTable detailsFlex = new FlexTable();
	static FlexTable assigneeFlex = new FlexTable();
	static FlexTable commentsFlex = new FlexTable();
	static TextArea ticketAddCommentTextArea = new TextArea();
	private static RedmineServiceAsync redmineService = GWT.create(RedmineService.class);
	
	public static FlowPanel ticketDetails(Ticket t) {
		
		RootPanel.get("tablebit").remove(ticketDetailsTable);
		ticketDetailsTable.clear();
		ticketDetailsTable.removeAllRows();
		
		int TitleStart = ordinalIndexOf(t.getTitle(), "/", 2) + 1;
		int CategoryEnd = ordinalIndexOf(t.getTitle(), "/", 1);
		//Window.confirm(String.valueOf(CategoryEnd));
		
		// C R E A T I N G     D I S P L A Y

		
		
		//Title
		FlowPanel hPanelTitle = new FlowPanel();
		hPanelTitle.setStyleName("hPanelTitle");
		
		//Date
		dateFlex.setStyleName("dateFlex");
		
		//Description Label
		VerticalPanel hPanelDetailsLabel = new VerticalPanel();
		hPanelDetailsLabel.setStyleName("hPanelDetailsLabel");

		
		
		
		//Assignee
		assigneeFlex.setStyleName("assigneeFlex");
		
		
		// C R E A T I N G     D E T A I L S
		
		String title = t.getTitle().substring(TitleStart);
		String category = t.getTitle().substring(0, CategoryEnd);
		
		// T I T L E
		Label ticketTitleLabel = new Label();
		
		
		
		ticketTitleLabel.setText(title);
		titleFlex.setWidget(0, 1, ticketTitleLabel);
		
		ticketTitleLabel.setStyleName("ticketDetailsTitleLabel");
		
		
		
		// D A T E     L O G G E D
		Label ticketDateLoggedLabel = new Label();
		Label ticketDateLogged = new Label();
		
		ticketDateLoggedLabel.setText("Date:");
		DateTimeFormat fmt = DateTimeFormat.getFormat("dd/MM/yyyy, hh:mma z");
		String DLog = fmt.format(t.getLogged());
		ticketDateLogged.setText(DLog);
		
		dateFlex.setWidget(0, 0, ticketDateLoggedLabel);
		dateFlex.setWidget(0, 1, ticketDateLogged);

		ticketDateLogged.setStyleName("ticketDetailsResult");
		ticketDateLoggedLabel.setStyleName("ticketDetailsDateLabel");	
		
		
		
		// P R I O R I T Y
		Label ticketPriorityLabel = new Label();
		Label ticketPriority = new Label();
		
		ticketPriorityLabel.setText("Priority:");
		ticketPriority.setText(t.getPriority());
		
		ticketDetailsTable.setWidget(2, 0, ticketPriorityLabel);
		ticketDetailsTable.setWidget(2, 1, ticketPriority);
		
		ticketPriorityLabel.setStyleName("ticketDetailsPriorityLabel");
		ticketPriority.setStyleName("ticketDetailsResult");
		
		
		
		// C A T E G O R Y
		Label ticketCategoryLabel = new Label();
		Label ticketCategory = new Label();
		
		ticketCategoryLabel.setText("Category:");
		ticketCategory.setText(category);
		
		ticketDetailsTable.setWidget(2, 2, ticketCategoryLabel);
		ticketDetailsTable.setWidget(2, 3, ticketCategory);
		
		ticketCategoryLabel.setStyleName("ticketDetailsCategoryLabel");
		ticketCategory.setStyleName("ticketDetailsResult");
		
		
		
		// S T A T U S
		Label ticketStatusLabel = new Label();
		Label ticketStatus = new Label();
		
		ticketStatusLabel.setText("Status:");
		ticketStatus.setText(t.getStatus());
		
		ticketDetailsTable.setWidget(2, 4, ticketStatusLabel);
		ticketDetailsTable.setWidget(2, 5, ticketStatus);
		
		ticketStatusLabel.setStyleName("ticketDetailsStatusLabel");
		ticketStatus.setStyleName("ticketDetailsResult");
		
		
		
		// A S S I G N E E
		Label ticketAssigneeLabel = new Label();
		Label ticketAssignee = new Label();
		
		ticketAssigneeLabel.setText("Assignee:");
		ticketAssignee.setText(t.getAssignee());
		
		assigneeFlex.setWidget(0, 0, ticketAssigneeLabel);
		assigneeFlex.setWidget(0, 1, ticketAssignee);
		
		ticketAssigneeLabel.setStyleName("ticketDetailsAssigneeLabel");
		ticketAssignee.setStyleName("ticketDetailsResult");
		
		
		
		// D E T A I L S
		Label ticketDetailsLabel = new Label();
		Label ticketDetails = new Label();
		
		ticketDetailsLabel.setText("Description:");
		ticketDetails.setText(t.getDescription());
		
		hPanelDetailsLabel.add(ticketDetailsLabel);
		hPanelDetailsLabel.add(ticketDetails);
		
		ticketDetailsLabel.setStyleName("ticketDetailsDetailsLabel");
		ticketDetails.setStyleName("ticketDetailsTextResult");
		
		
		
		redmineService.getComments(t.getTicketID(), new AsyncCallback<Comments>() {
	
			public void onFailure(Throwable e) { throw new RuntimeException(e);}
	
			public void onSuccess(Comments result) {
				int NamePosition = 0;
				int PicPosition = -1;
				result.sort(Comparator.comparing(Comment::getLogged).reversed());
				
				for (int i = 0;i <= result.size(); i++) {					
					if (result.get(i).getComment() != null && result.get(i).getComment() != "") {
						Image userImage = new Image();
						userImage.addStyleName("userImage");
						userImage.setUrl("http://www.weirdhut.com/wp-content/uploads/2013/01/Funny-Cat-tongue.jpg");
						NamePosition = NamePosition + 2;
						PicPosition = PicPosition + 2;
						userImage.setPixelSize(50, 50);
						commentsFlex.setWidget(PicPosition, 0, userImage);
						commentsFlex.setStyleName("commentsFlex");
						String comment = result.get(i).getComment();
						
						//Name
						Label nameLabel = new Label();
						nameLabel.setText(result.get(i).getUser());
						commentsFlex.setWidget(NamePosition, 0, nameLabel);
						
						//Comment
						Label commentLabel = new Label();
						commentLabel.setText(result.get(i).getComment());
						commentsFlex.setWidget(PicPosition, 1, commentLabel);
						
						//DateTime
						DateTimeFormat fmt = DateTimeFormat.getFormat("hh:mma dd/MM/yyyy");
						DateLabel dateTimeLabel = new DateLabel(fmt);
						dateTimeLabel.setValue(result.get(i).getLogged());
						commentsFlex.setWidget(PicPosition, 2, dateTimeLabel);
						
						//rowCount = commentsFlex.getRowCount();
	
						int CommentStart = ordinalIndexOf(comment, ":", 3) + 2;
						int NameStart = ordinalIndexOf(comment, "#", 3) + 2;
						
						String nameActual = comment.substring(NameStart, CommentStart - 2);
						String commentActual = comment.substring(CommentStart);
						
						commentLabel.setText(commentActual);
						nameLabel.setText(nameActual);
						
						//Comment 2 RowSpan
						commentsFlex.getFlexCellFormatter().setRowSpan(PicPosition, 1, 2);
						
						//Date 2 RowSpan
						commentsFlex.getFlexCellFormatter().setRowSpan(PicPosition, 2, 2);
						
						//Image 1 RowSpan
						commentsFlex.getFlexCellFormatter().setRowSpan(PicPosition, 0, 1);
						
						//Name 1 Rowspan
						commentsFlex.getFlexCellFormatter().setRowSpan(NamePosition, 0, 1);
								
						commentsFlex.getFlexCellFormatter().setStyleName(PicPosition, 2,"commentsFlexDate" );
						commentsFlex.getFlexCellFormatter().setStyleName(PicPosition, 1, "commentsFlexComment");
						commentsFlex.getFlexCellFormatter().setStyleName(PicPosition, 0, "commentsFlexImage");
						commentsFlex.getFlexCellFormatter().setStyleName(NamePosition, 0, "commentsFlexName");
						
						if (!result.get(i).getComment().contains("###")) {
							nameLabel.setText(result.get(i).getUser());
							commentLabel.setText(result.get(i).getComment());
							
						}
						
						
						
					}
					if (i == result.size() - 1) {
						
						Button ticketSubmitCommentButton = new Button();
						ticketSubmitCommentButton.setStyleName("buttonz");
						ticketSubmitCommentButton.setText("Submit Comment");
						ticketSubmitCommentButton.addClickHandler(new ClickHandler() {
							public void onClick(ClickEvent event) {
								
								String comment = ticketAddCommentTextArea.getText();
								
								//String comment = "FIX";
								Window.confirm(comment + String.valueOf(t.getTicketID()));
								if (comment != "" && comment != null) {
								redmineService.newComment(comment, t.getTicketID(), new AsyncCallback<Comment>() {
					
									public void onFailure(Throwable e) { throw new RuntimeException(e); }
					
									public void onSuccess(Comment result) {							
										hPanelTitle.clear();
										hPanelDetailsLabel.clear();
										//fPanelDetails.clear();
										
										dateFlex.removeAllRows();
										dateFlex.clear();
										
										assigneeFlex.removeAllRows();
										assigneeFlex.clear();
										
										buttonsFlex.removeAllRows();
										buttonsFlex.clear();
										vPanel.remove(buttonsFlex);
										
										titleFlex.removeAllRows();
										titleFlex.clear();
										
										commentsFlex.removeAllRows();
										commentsFlex.clear();
										vPanel.remove(commentsFlex);

										vPanel.clear();
										
										pillion.hba.hub.client.TicketPage.ticketsForm();
										
									}}
								
							);
								
								}
								else {
									Window.alert("Comment must not be blank!");
								}
								ticketAddCommentTextArea.setText(null);
					        }
							
					    });
						
						//Window.confirm("Ack1");
						Image userImage2 = new Image();
						userImage2.addStyleName("userImage");
						userImage2.setUrl("http://www.weirdhut.com/wp-content/uploads/2013/01/Funny-Cat-tongue.jpg");

						
						commentsFlex.insertRow(0);
						commentsFlex.insertRow(1);
						
						commentsFlex.getFlexCellFormatter().setStyleName(0, 2,"commentsFlexDate" );
						commentsFlex.getFlexCellFormatter().setStyleName(0, 1, "commentsFlexComment");
						commentsFlex.getFlexCellFormatter().setStyleName(0, 0, "commentsFlexImage");
						commentsFlex.getFlexCellFormatter().setStyleName(1, 0, "commentsFlexName");
						
						userImage2.setPixelSize(50, 50);
						
						Label nameLabel2 = new Label();
						nameLabel2.setText("Name");
						
						Label dateLabel2 = new Label();
						dateLabel2.setText("AHH");
						
						//TextArea ticketAddCommentTextArea = new TextArea();
						
						commentsFlex.setWidget(0, 0, userImage2);
						commentsFlex.setWidget(1, 0, nameLabel2);
						commentsFlex.setWidget(0, 1, ticketAddCommentTextArea);
						commentsFlex.setWidget(0, 2, ticketSubmitCommentButton);
						
						//buttonsFlex.getColumnFormatter().setWidth(0, "15%");
						
						//buttonsFlex.getFlexCellFormatter().setColSpan(2, 0, 20);
						ticketAddCommentTextArea.setStyleName("buttonzFlexTextArea");
						
						ticketAddCommentTextArea.setVisibleLines(5);
						
						//Comment 2 RowSpan
						commentsFlex.getFlexCellFormatter().setRowSpan(0, 1, 2);
						
						//Date 2 RowSpan
						commentsFlex.getFlexCellFormatter().setRowSpan(0, 2, 2);
						
						//Image 1 RowSpan
						commentsFlex.getFlexCellFormatter().setRowSpan(0, 0, 1);
						
						//Name 1 Rowspan
						commentsFlex.getFlexCellFormatter().setRowSpan(1, 0, 1);
						
						//Window.confirm("Ack2");
					}
				}
				
				
		
			}
		});
		
		
		
		
		
		Button ticketAddCommentButton = new Button();
		ticketAddCommentButton.setStyleName("buttonz");
		ticketAddCommentButton.setText("Add Comment");
		
		Button ticketCancelCommentButton = new Button();
		ticketCancelCommentButton.setStyleName("buttonz");
		ticketCancelCommentButton.setText("Cancel");
		ticketCancelCommentButton.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				
				//Clear Submit Comment Button
				buttonsFlex.clearCell(1, 0);
				
				//Clear Cancel Button
				buttonsFlex.clearCell(1, 1);
				
				//Clear Text Area
				buttonsFlex.clearCell(2, 0);
				
				//Set Add Comment Button
				buttonsFlex.setWidget(0, 0, ticketAddCommentButton);

	        }
	    });
		
		buttonsFlex.setSize("90%", "100%");
		buttonsFlex.setStyleName("buttonzflex");
		
		//buttonsFlex.getFlexCellFormatter().setWidth(1, 1, "10%");
		//TextArea ticketAddCommentTextArea = new TextArea();

	
				
		buttonsFlex.setStyleName("buttonsFlexz");
		
		
	
	
		
		Button backButton = new Button();
		backButton.setStyleName("buttonz");
		backButton.setText("Back");
		titleFlex.setWidget(0, 0, backButton);
		backButton.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {	
				dateFlex.removeAllRows();
				dateFlex.clear();
				
				assigneeFlex.removeAllRows();
				assigneeFlex.clear();
				
				hPanelTitle.clear();
				
				hPanelDetailsLabel.clear();
			//	fPanelDetails.clear();
				
				
				buttonsFlex.removeAllRows();
				buttonsFlex.clear();
				vPanel.remove(buttonsFlex);
				
				titleFlex.removeAllRows();
				titleFlex.clear();
				
				commentsFlex.removeAllRows();
				commentsFlex.clear();
				vPanel.remove(commentsFlex);

				vPanel.clear();

				pillion.hba.hub.client.TicketPage.ticketsForm();
				
	        	
	        }
	    });
		ticketDetailsTable.setStyleName("detailsFlex");
			
		vPanel.add(titleFlex);
		vPanel.add(hPanelTitle);
		vPanel.add(dateFlex);
		
		vPanel.add(ticketDetailsTable);
		
		vPanel.add(assigneeFlex);
		vPanel.add(hPanelDetailsLabel);
//		vPanel.add(fPanelDetails);
		//vPanel.add(detailsFlex);
		vPanel.add(buttonsFlex);
		vPanel.add(commentsFlex);
		
		return vPanel;
	
	}
	
	public static int ordinalIndexOf(String str, String substr, int n) {
	    int pos = str.indexOf(substr);
	    while (--n > 0 && pos != -1)
	        pos = str.indexOf(substr, pos + 1);
	    return pos;
	}
}