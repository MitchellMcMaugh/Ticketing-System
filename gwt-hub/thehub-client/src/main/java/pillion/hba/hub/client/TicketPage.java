package pillion.hba.hub.client;

import com.google.gwt.dom.client.Document;
import com.google.gwt.dom.client.Element;
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
	private Grid ticketTeble;
	
	public void go() {
		Element newTicketButtonElement = Document.get().getElementById("tickets-new-ticket");
		newTicketButton = Button.wrap(newTicketButtonElement);
		newTicketButton.addClickHandler((ClickEvent event) -> newTicket());

		Element ticketTableElement = Document.get().getElementById("tickets-ticket-table");
		ticketTeble = new Grid(1,5);
		ticketTeble.setStyleName("tbl-ticket-list");
		ticketTeble.setWidth("100%");
		ticketTeble.setCellPadding(0);
		ticketTeble.setCellSpacing(0);
		ticketTeble.setBorderWidth(0);
		ticketTableElement.appendChild(ticketTeble.getElement());
		ticketTeble.setText(0, 0, "Boo");
		addHeader();
//		ticketTeble.w
		
	}
	
	private void newTicket() {
		
	}
	
	private void addHeader() {
		for(int i=0;i<5;i++) ticketTeble.setText(0, i, TICKET_TABLE_HEADINGS[i]);
		ticketTeble.getRowFormatter().setStyleName(0, "tbl-head");
	}

}
