package pillion.hba.hub.client;

import com.google.gwt.dom.client.Document;
import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.Style.Display;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Grid;

public class TicketPage {
	
	private static final String[] TICKET_TABLE_HEADINGS = {
			"Logged",
			"Priority",
			"Status",
			"Description",
			"Assignee"
	};
	
	private Button newTicketButton;
	private Grid ticketTable;
	private Element listTicketsPanel, newTicketPanel, viewTicketPanel;
	
	public void go() {
		Element newTicketButtonElement = Document.get().getElementById("tickets-new-ticket");
		newTicketButton = Button.wrap(newTicketButtonElement);
		listTicketsPanel = Document.get().getElementById("tickets-list-tickets-panel");
		viewTicketPanel = Document.get().getElementById("tickets-view-ticket-panel");
		newTicketPanel = Document.get().getElementById("tickets-new-ticket-panel");
		Element ticketTableElement = Document.get().getElementById("tickets-ticket-table");
		
		
		
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
//		ticketTeble.w
		
	}
	
	private void newTicket() {
		listTicketsPanel.getStyle().setDisplay(Display.NONE);
		newTicketPanel.getStyle().setDisplay(Display.BLOCK);
	}
	
	private void addHeader() {
		for(int i=0;i<5;i++) ticketTable.setText(0, i, TICKET_TABLE_HEADINGS[i]);
		ticketTable.getRowFormatter().setStyleName(0, "tbl-head");
	}

}
