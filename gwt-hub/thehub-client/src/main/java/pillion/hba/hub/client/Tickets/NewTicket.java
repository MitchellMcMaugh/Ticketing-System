package pillion.hba.hub.client.Tickets;

import com.google.gwt.core.client.GWT;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;

import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Label;

import pillion.hba.hub.shared.RedmineService;
import pillion.hba.hub.shared.RedmineServiceAsync;
import pillion.hba.hub.shared.Ticket;
import com.google.gwt.user.client.ui.FileUpload;

import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.FormPanel;

public class NewTicket {
	
	static final RedmineServiceAsync redmineService = GWT.create(RedmineService.class);
	
	static FlowPanel newTicketFlex = new FlowPanel();
	
	static FileUpload upload = new FileUpload();
	
	static FormPanel form = new FormPanel();
	
	static byte[] data;
	
	public static FlowPanel newTicket() {
		
		Button submitTicketButton = new Button();
		Button cancelTicketButton = new Button();
		Button attachmentButton = new Button();
		
		Label titleTextBoxLabel = new Label();
		TextBox titleTextBox = new TextBox();
		titleTextBoxLabel.setStyleName("titleTextBoxLabel");
		titleTextBox.setStyleName("titleTextBox");
		
		Label detailsTextAreaLabel = new Label();
		TextArea detailsTextArea = new TextArea();
		detailsTextArea.setVisibleLines(10);
		detailsTextAreaLabel.setStyleName("detailsTextAreaLabel");
		detailsTextArea.setStyleName("detailsTextArea");
		
		ListBox newTicketPriorityListBox = new ListBox();
		ListBox newTicketCategoryListBox = new ListBox();
		newTicketPriorityListBox.setStyleName("listBoxez");
		newTicketCategoryListBox.setStyleName("listBoxez");
		
		// L I S T   B O X E S
		
		String[] ticketFilterPriorities = {"Priority", "Low", "Normal", "High", "Urgent"};
		
		for (int i = 0; i < ticketFilterPriorities.length; i++) {
			newTicketPriorityListBox.addItem(ticketFilterPriorities[i]);
		}
		RootPanel.get("newtickettopbit").add(newTicketPriorityListBox);
		newTicketPriorityListBox.setSelectedIndex(0);
		
		
		
		String[] ticketFilterCategories = {"Category", "Affinity Issue", "Big Hand Issue", "Hardware Issue", "Lost File Issue", "Microsoft Office 365 Issue", "Microsoft Windows Issue",
				"Printing Issue", "Remote Desktop Issue", "Software Issue", "User Login Issue", "Other Issue"};
		for (int i = 0; i < ticketFilterCategories.length; i++) {
			newTicketCategoryListBox.addItem(ticketFilterCategories[i]);
		}
		RootPanel.get("newtickettopbit").add(newTicketCategoryListBox);
		newTicketCategoryListBox.setSelectedIndex(0);
		
		// S U B J E C T
		titleTextBoxLabel.setText("Subject");
		newTicketFlex.add(titleTextBoxLabel);
		newTicketFlex.add(titleTextBox);
		
		// D E T A I L S
		detailsTextAreaLabel.setText("Details");
		newTicketFlex.add(detailsTextAreaLabel);
		newTicketFlex.add(detailsTextArea);
		
		// B U T T O N S
		//Attachment
		attachmentButton.setText("Add Attachment");
		attachmentButton.setStyleName("newTicketAttachmentButton");
		newTicketFlex.add(attachmentButton);
		attachmentButton.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				
			    upload.setName("uploadFormElement");
			    upload.click();
			    
			    form.setAction("/b/barnacle/attch");
				
				form.setEncoding(FormPanel.ENCODING_MULTIPART);
			    form.setMethod(FormPanel.METHOD_POST);
			    
			    newTicketFlex.add(form);
		
			}});
		
		//Cancel
		cancelTicketButton.setText("Cancel");
		cancelTicketButton.setStyleName("newTicketCancelButton");
		newTicketFlex.add(cancelTicketButton);
		cancelTicketButton.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				String detailsTextAreaValue = detailsTextArea.getText();
				boolean cancelTrueFalse = true;
				if (detailsTextAreaValue != "") {
					cancelTrueFalse = Window.confirm("Are you sure you want to cancel?");
				}
				if (cancelTrueFalse == true){
					
					//Clears Short Description Textbox and Details TextArea.
					titleTextBox.setValue(null);
					detailsTextArea.setValue(null);
					
		
					
					RootPanel.get("newticketticketbit").remove(newTicketFlex);
					newTicketFlex.clear();
					newTicketCategoryListBox.removeFromParent();
					newTicketPriorityListBox.removeFromParent();
					pillion.hba.hub.client.TicketPage.ticketsForm();
					}
				}
			}
		);
		
		//Submit
		submitTicketButton.setText("Submit");
		submitTicketButton.setStyleName("newTicketSubmitButton");
		newTicketFlex.add(submitTicketButton);
		submitTicketButton.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				
				form.submit();
				
				String titleTextValue = titleTextBox.getText();
				String detailsTextAreaValue = detailsTextArea.getText();
				String ticketPriorityListBoxValue = newTicketPriorityListBox.getSelectedItemText().toString();
				String ticketCategoryListBoxValue = newTicketCategoryListBox.getSelectedItemText().toString();
				
				if (titleTextValue == "") {Window.alert("Title must not be blank!");}
				
				else if (detailsTextAreaValue == "") {Window.alert("Details must not be blank!");}
				
				else if (ticketPriorityListBoxValue == "" || ticketPriorityListBoxValue == "Priority") {Window.alert("Priority must not be blank!");}
				
				else if (ticketCategoryListBoxValue == "" || ticketCategoryListBoxValue == "Category") {Window.alert("Category must not be blank!");}
				
				else  {
					redmineService.newTicket(ticketPriorityListBoxValue, ticketCategoryListBoxValue, titleTextValue, detailsTextAreaValue,  new AsyncCallback<Ticket>() {
						public void onFailure(Throwable e) { throw new RuntimeException(e); }
						public void onSuccess(Ticket result) {	
							//Clears Short Description Textbox and Details TextArea.
							titleTextBox.setValue(null);
							detailsTextArea.setValue(null);
							
							
							
							RootPanel.get("newticketticketbit").remove(newTicketFlex);
							newTicketFlex.clear();
							newTicketCategoryListBox.removeFromParent();
							newTicketPriorityListBox.removeFromParent();
							pillion.hba.hub.client.TicketPage.ticketsForm();
						}});
					}
				}
			});

		return newTicketFlex;
	}
}

