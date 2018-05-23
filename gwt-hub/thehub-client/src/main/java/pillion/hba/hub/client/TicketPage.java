package pillion.hba.hub.client;

import com.google.gwt.core.client.GWT;

import com.google.gwt.dom.client.Document;
import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.Style.Display;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FileUpload;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.TextBox;

import pillion.hba.hub.shared.RedmineService;
import pillion.hba.hub.shared.RedmineServiceAsync;
import pillion.hba.hub.shared.Ticket;
import pillion.hba.hub.shared.Tickets;



public class TicketPage {
	
	private final RedmineServiceAsync redmineService = GWT.create(RedmineService.class);
	
	DateTimeFormat sdf = DateTimeFormat.getFormat("dd/MM/yyyy");
	
	private static final String[] TICKET_TABLE_HEADINGS = {
			"Logged",
			"Priority",
			"Status",
			"Title",
			"Assignee"
	};
	
	private Button newTicketButton;
	private Grid ticketTable;
	private Element listTicketsPanel, newTicketPanel, viewTicketPanel;
	
	//Added by Mitchell McMaugh 22/05/2018
	//Function: Add submit button.
	private Button submitTicketButton;
	
	//Added by Mitchell McMaugh 16/05/2018
	//Function: Add cancel button.
	private Button cancelTicketButton;
	
	//Added by Mitchell McMaugh 17/05/2018
	//Function: Add Add Attachment button.
	//private Button addAttachmentButton;
	
	//Added by Mitchell McMaugh 21/05/2018
	//Function: Add textboxes.
	private TextBox shortDescriptionText;
	private TextArea detailsText;
	
	//Added by Mitchell McMaugh 21/05/2018
	//Function: Cancel Button Confirmation Value
	private boolean cancelTrueFalse;
	
	//Added by Mitchell McMaugh 21/05/2018
	//Function: Add Priority Drop Down
	private ListBox newTicketPriority;
	
	//Added by Mitchell McMaugh 21/05/2018
	//Function: Add Category Drop Down
	private ListBox newTicketCategory;
	
	//Added by Mitchell McMaugh 21/05/2018
	//Function: Add Attachment
	private FileUpload newTicketAttachment;
	
	//Added by Mitchell McMaugh 21/05/2018
	//Function: Add filter Drop Down
	private ListBox filterTickets;
	
	public void go() {
		Element newTicketButtonElement = Document.get().getElementById("tickets-new-ticket");
		newTicketButton = Button.wrap(newTicketButtonElement);
		listTicketsPanel = Document.get().getElementById("tickets-list-tickets-panel");
		viewTicketPanel = Document.get().getElementById("tickets-view-ticket-panel");
		newTicketPanel = Document.get().getElementById("tickets-new-ticket-panel");
		Element ticketTableElement = Document.get().getElementById("tickets-ticket-table");
		
		//Added by Mitchell McMaugh 22/05/2018
		//Function: Add submit button.
		Element submitButtonElement = Document.get().getElementById("tickets-new-submit");
		submitTicketButton = Button.wrap(submitButtonElement);
		submitTicketButton.addClickHandler((ClickEvent event) -> submitTicket());
		
		
		//Added by Mitchell McMaugh 15/05/2018
		//Function: Add cancel button.
		Element cancelButtonElement = Document.get().getElementById("tickets-cancel");
		cancelTicketButton = Button.wrap(cancelButtonElement);
		cancelTicketButton.addClickHandler((ClickEvent event) -> cancelTicket());
		
		//Added by Mitchell McMaugh 17/05/2018
		//Function: Add Add Attachment button.
//		Element attachmentButtonElement = Document.get().getElementById("tickets-add-attachment");
//		addAttachmentButton = Button.wrap(attachmentButtonElement);
//		addAttachmentButton.addClickHandler((ClickEvent event) -> addAttachment());
		
		//Added by Mitchell McMaugh 21/05/2018
		//Function: Add textboxes.
		//Short Description TextBox
		Element shortDescriptionElement = Document.get().getElementById("tickets-short-description");
		shortDescriptionText = TextBox.wrap(shortDescriptionElement);
		//Details TextArea
		Element detailsTextElement = Document.get().getElementById("tickets-details-text");
		detailsText = TextArea.wrap(detailsTextElement);
		
		//Added by Mitchell McMaugh 21/05/2018
		//Function: Add listboxes
		//Priority Listbox
		Element newTicketPriorityElement = Document.get().getElementById("tickets-new-priority");
		newTicketPriority = ListBox.wrap(newTicketPriorityElement);
		//Category Listbox
		Element newTicketCategoryElement = Document.get().getElementById("tickets-new-category");
		newTicketCategory = ListBox.wrap(newTicketCategoryElement);
		//Filter Listbox
		Element filterTicketsElement = Document.get().getElementById("tickets-filter");
		filterTickets = ListBox.wrap(filterTicketsElement);
		
		//Added by Mitchell McMaugh 21/05/2018
		//Function: Add attachments.
		//Element newTicketAttachmentElement = Document.get().getElementById("tickets-new-file-upload");
		//newTicketAttachment = FileUpload.wrap(newTicketAttachmentElement);
		
		newTicketButton.addClickHandler((ClickEvent event) -> newTicket());
		ticketTable = new Grid(1,5);
		ticketTable.setStyleName("tbl-ticket-list");
		ticketTable.setWidth("100%");
		ticketTable.setCellPadding(0);
		ticketTable.setCellSpacing(0);
		ticketTable.setBorderWidth(0);
		ticketTableElement.appendChild(ticketTable.getElement());
		ticketTable.setText(0, 0, "Boo");
		addHeader();
		redmineService.getTickets(new AsyncCallback<Tickets>() {
			public void onSuccess(Tickets result) { populateTickets(result);}
			public void onFailure(Throwable e) { throw new RuntimeException(e); }
		});
	}

	private void newTicket() {
		listTicketsPanel.getStyle().setDisplay(Display.NONE);
		newTicketPanel.getStyle().setDisplay(Display.BLOCK);
	}
	
	private void populateTickets(Tickets tickets) {
		int startRow = ticketTable.getRowCount();
		int noOfTickets = tickets.size();
		ticketTable.resizeRows(startRow + noOfTickets);
		for(int i = 0;	i < noOfTickets; i++) {
			addRow(tickets.get(i), i + startRow);
		}
	}
	
	//Added by Mitchell McMaugh 22/05/2018
	//Function: Submit new Issue/ Ticket
	public void submitTicket()  {
		String shortDescriptionTextValue = shortDescriptionText.getText();
		String detailsTextValue = detailsText.getText();
		String ticketPriorityValue = newTicketPriority.getSelectedItemText().toString().toUpperCase();
		String ticketCategoryValue = newTicketCategory.getSelectedItemText().toString().toUpperCase();
		redmineService.newTicket(ticketPriorityValue, ticketCategoryValue, shortDescriptionTextValue, detailsTextValue, new AsyncCallback<Ticket>() {

			public void onFailure(Throwable e) { throw new RuntimeException(e); }

			public void onSuccess(Ticket result) {	}}
			
	);}

	
	//Added by Mitchell McMaugh 15/05/2018
	//Function: Cancel current new ticket form and return to ticket overview.
	private void cancelTicket() {
		cancelTrueFalse = Window.confirm("Are you sure you want to cancel?");
		if (cancelTrueFalse == true){
			listTicketsPanel.getStyle().setDisplay(Display.BLOCK);
			newTicketPanel.getStyle().setDisplay(Display.NONE);
			//Clears Short Description Textbox and Details TextArea.
			shortDescriptionText.setValue(null);
			detailsText.setValue(null);
			}
		}
	
	//Added by Mitchell McMaugh 17/05/2018
	//Function: Add attachment to ticket.
	private void addAttachment() {

		
	}
	
	private void addHeader() {
		for(int i=0;i<5;i++) ticketTable.setText(0, i, TICKET_TABLE_HEADINGS[i]);
		ticketTable.getRowFormatter().setStyleName(0, "tbl-head");
	}

	private void addRow(Ticket t, int rowNo) {
		ticketTable.setText(rowNo, 0, sdf.format(t.getLogged()));
		ticketTable.setText(rowNo, 1, t.getPriority());
		ticketTable.setText(rowNo, 2, t.getStatus());
		ticketTable.setText(rowNo, 3, t.getTitle());
		ticketTable.setText(rowNo, 4, t.getAssignee());
//		ticketTable.setText(rowNo, , t.get));
		
	}

}