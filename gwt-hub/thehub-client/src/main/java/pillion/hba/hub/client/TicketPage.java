package pillion.hba.hub.client;

import com.google.gwt.core.client.GWT;

import com.google.gwt.dom.client.Document;
import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.Style.Display;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FileUpload;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.DateLabel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.DecoratorPanel;
import com.google.gwt.user.client.ui.DockPanel;
import com.google.gwt.user.client.ui.LayoutPanel;
import com.google.gwt.user.client.ui.FlowPanel;

import java.util.Comparator;
import java.util.Date;

import pillion.hba.hub.shared.RedmineService;
import pillion.hba.hub.shared.RedmineServiceAsync;
import pillion.hba.hub.shared.Ticket;
import pillion.hba.hub.shared.Tickets;
import pillion.hba.hub.client.Tickets.NewTicket;
import pillion.hba.hub.shared.Comment;
import pillion.hba.hub.shared.Comments;
import pillion.hba.hub.client.Tickets.TicketDetails;



public class TicketPage {
	
	private static RedmineServiceAsync redmineService = GWT.create(RedmineService.class);
	
	static DateTimeFormat sdf = DateTimeFormat.getFormat("dd/MM/yyyy");
	
	private static final String[] TICKET_TABLE_HEADINGS = {
			"Logged",
			"Priority",
			"Status",
			"Title",
			"Assignee"
	};
	
	private static int rowCount;
	public static Button newTicketButton = new Button();
	public static ListBox ticketFilterPriorityListBox = new ListBox();
	public static ListBox ticketFilterCategoryListBox = new ListBox();
	
	//private Button newTicketButton;
	static FlexTable ticketTable = new FlexTable();;
	private Element listTicketsPanel, newTicketPanel, viewTicketPanel;
	
	
	//Added by Mitchell McMaugh 21/05/2018
	//Function: Add filter Drop Down
	//private ListBox filterTickets;
	
	public void go() {
		//RootPanel.get("rhs-content").clear();
		
		
		
		//listTicketsPanel = Document.get().getElementById("one");
		//viewTicketPanel = Document.get().getElementById("tickets-view-ticket-panel");
		//newTicketPanel = Document.get().getElementById("tickets-new-ticket-panel");
		//Element ticketTableElement = Document.get().getElementById("two");
		
		
		
		//ticketTableElement.appendChild(ticketTable.getElement());
		
		
		
		
		
		
		
		newTicketButton.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				FlowPanel newTicketFlex = new FlowPanel();
				newTicketFlex = pillion.hba.hub.client.Tickets.NewTicket.newTicket();
				RootPanel.get("tablebit").remove(ticketTable);
				ticketTable.clear();
				//RootPanel.get("two").clear();
				ticketTable.removeAllRows();
				RootPanel.get("newticketticketbit").add(newTicketFlex);
				RootPanel.get("topbit").remove(newTicketButton);
				RootPanel.get("topbit").remove(ticketFilterCategoryListBox);
				RootPanel.get("topbit").remove(ticketFilterPriorityListBox);
				
				
			}
		
		});
		
		ticketsForm();
		
		
		
	}
	
	public static void ticketsForm() {
		ticketFilterCategoryListBox.clear();
		ticketFilterPriorityListBox.clear();
		
		redmineService.getTickets(new AsyncCallback<Tickets>() {
			public void onSuccess(Tickets result) { populateTickets(result);}
			public void onFailure(Throwable e) { throw new RuntimeException(e); }
		});
		
		//New Ticket Button
		RootPanel.get("topbit").add(newTicketButton);
		newTicketButton.setText("New Ticket");
		newTicketButton.setStyleName("buttonz");
		
		//Filter Tickets
		ticketFilterCategoryListBox.addItem("Category Filter");
		ticketFilterCategoryListBox.addItem("Affinity Issue");
		ticketFilterCategoryListBox.addItem("Big Hand");
		ticketFilterCategoryListBox.addItem("Hardware Issue");
		ticketFilterCategoryListBox.addItem("Lost File Issue");
		ticketFilterCategoryListBox.addItem("Microsoft Office 365 Issue");
		ticketFilterCategoryListBox.addItem("Microsoft Windows Issue");
		ticketFilterCategoryListBox.addItem("Remote Desktop Issue");
		ticketFilterCategoryListBox.addItem("Software Issue");
		ticketFilterCategoryListBox.addItem("User Login Issue");
		ticketFilterCategoryListBox.addItem("Other Issue");
		ticketFilterCategoryListBox.setSelectedIndex(0);
		ticketFilterCategoryListBox.setStyleName("listBoxez");
		RootPanel.get("topbit").add(ticketFilterPriorityListBox);
		
				
		ticketFilterPriorityListBox.addItem("Priority Filter");
		ticketFilterPriorityListBox.addItem("Low");
		ticketFilterPriorityListBox.addItem("Normal");
		ticketFilterPriorityListBox.addItem("High");
		ticketFilterPriorityListBox.addItem("Urgent");
		ticketFilterPriorityListBox.setSelectedIndex(0);
		ticketFilterPriorityListBox.setStyleName("listBoxez");
		RootPanel.get("topbit").add(ticketFilterCategoryListBox);
		
		ticketTable.setStyleName("tbl-ticket-list");
		ticketTable.setWidth("100%");
		ticketTable.setCellPadding(0);
		ticketTable.setCellSpacing(0);
		ticketTable.setBorderWidth(0);
		
		
		
		//Adds Header to Table
		for(int i=0;i<5;i++) ticketTable.setText(0, i, TICKET_TABLE_HEADINGS[i]);
		ticketTable.getRowFormatter().setStyleName(0, "tbl-head");
		RootPanel.get("tablebit").add(ticketTable);
	}
	
	public static void populateTickets(Tickets tickets) {
		int startRow = ticketTable.getRowCount();
		int noOfTickets = tickets.size();
		//ticketTable.resizeRows(startRow + noOfTickets);
		String labelName;
		for(int i = 0;	i < noOfTickets; i++) {
			//addRow(tickets.get(i), i + startRow);
			Ticket t = tickets.get(i);
			labelName = new String();
			labelName = String.valueOf(i) + tickets.get(i).getTitle();
			int rowNo = i + startRow;
			ticketTable.setText(rowNo, 0, sdf.format(t.getLogged()));
			ticketTable.setText(rowNo, 1, t.getPriority());
			ticketTable.setText(rowNo, 2, t.getStatus());		
			ticketTable.setText(rowNo, 4, t.getAssignee());
			

			Label ticketDetailLabel = new Label();
			ticketTable.setWidget(rowNo, 3, ticketDetailLabel);
			ticketDetailLabel.setStyleName("title");
			ticketDetailLabel.setText(t.getTitle());
			ticketDetailLabel.addClickHandler(new ClickHandler() {
				public void onClick(ClickEvent event) {
					FlowPanel tDetails = pillion.hba.hub.client.Tickets.TicketDetails.ticketDetails(t);
	            	RootPanel.get("tablebit").remove(ticketTable);
					ticketTable.clear();
					ticketTable.removeAllRows();
					RootPanel.get("topbit").remove(newTicketButton);
					RootPanel.get("topbit").remove(ticketFilterCategoryListBox);
					RootPanel.get("topbit").remove(ticketFilterPriorityListBox);
	            	RootPanel.get("detailbit").add(tDetails);
	            	
	            	
	            }
	        });
		}
	}
	
	private void addHeader() {
		for(int i=0;i<5;i++) ticketTable.setText(0, i, TICKET_TABLE_HEADINGS[i]);
		ticketTable.getRowFormatter().setStyleName(0, "tbl-head");
	}

	public static void addRow(Ticket t, int rowNo) {
		
		ticketTable.setText(rowNo, 0, sdf.format(t.getLogged()));
		ticketTable.setText(rowNo, 1, t.getPriority());
		ticketTable.setText(rowNo, 2, t.getStatus());
		//ticketTable.setText(rowNo, 3, t.getTitle());
				
		ticketTable.setText(rowNo, 4, t.getAssignee());
//		ticketTable.setText(rowNo, , t.get));
		
	}	
	
}