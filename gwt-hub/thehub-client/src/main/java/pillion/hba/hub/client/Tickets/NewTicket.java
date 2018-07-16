package pillion.hba.hub.client.Tickets;

import com.google.gwt.core.client.GWT;

//import java.io.File;
import java.io.IOException;
//import java.nio.file.Path;
//import java.nio.file.Files;

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
import com.google.gwt.user.client.ui.FlexTable;

import pillion.hba.hub.shared.RedmineService;
import pillion.hba.hub.shared.RedmineServiceAsync;
import pillion.hba.hub.shared.Ticket;
import pillion.hba.hub.client.TicketPage;
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
		
		newTicketPriorityListBox.addItem("Priority");
		newTicketPriorityListBox.addItem("Low");
		newTicketPriorityListBox.addItem("Normal");
		newTicketPriorityListBox.addItem("High");
		newTicketPriorityListBox.addItem("Urgent");
		//newTicketFlex.setWidget(startRow + spacing, columnStart, newTicketPriorityListBox);
		RootPanel.get("newtickettopbit").add(newTicketPriorityListBox);
		newTicketPriorityListBox.setSelectedIndex(0);
		
		newTicketCategoryListBox.addItem("Category");
		newTicketCategoryListBox.addItem("Affinity Issue");
		newTicketCategoryListBox.addItem("Big Hand");
		newTicketCategoryListBox.addItem("Hardware Issue");
		newTicketCategoryListBox.addItem("Lost File Issue");
		newTicketCategoryListBox.addItem("Microsoft Office 365 Issue");
		newTicketCategoryListBox.addItem("Microsoft Windows Issue");
		newTicketCategoryListBox.addItem("Remote Desktop Issue");
		newTicketCategoryListBox.addItem("Software Issue");
		newTicketCategoryListBox.addItem("User Login Issue");
		newTicketCategoryListBox.addItem("Other Issue");
		//newTicketFlex.setWidget(startRow + spacing, columnStart + 1, newTicketCategoryListBox);
		RootPanel.get("newtickettopbit").add(newTicketCategoryListBox);
		//newTicketFlex.add(newTicketCategoryListBox);
		newTicketCategoryListBox.setSelectedIndex(0);
		
		// S U B J E C T
		titleTextBoxLabel.setText("Subject");
		//newTicketFlex.setWidget(startRow + 1 + spacing, columnStart, titleTextBoxLabel);
		//newTicketFlex.setWidget(startRow + 2 + spacing, columnStart, titleTextBox);
		//RootPanel.get("newticketbit").add(titleTextBoxLabel);
		//RootPanel.get("newticketbit").add(titleTextBox);
		newTicketFlex.add(titleTextBoxLabel);
		newTicketFlex.add(titleTextBox);
		
		// D E T A I L S
		detailsTextAreaLabel.setText("Details");
		//newTicketFlex.setWidget(startRow + 3 + spacing, columnStart, detailsTextAreaLabel);
		//newTicketFlex.setWidget(startRow + 4 + spacing, columnStart, detailsTextArea);
		//newTicketFlex.getFlexCellFormatter().setRowSpan(startRow + 4 + spacing, columnStart, 5);
//		RootPanel.get("newticketbit").add(detailsTextAreaLabel);
//		RootPanel.get("newticketbit").add(detailsTextArea);
		newTicketFlex.add(detailsTextAreaLabel);
		newTicketFlex.add(detailsTextArea);
		
		// B U T T O N S
		attachmentButton.setText("Add Attachment");
		attachmentButton.setStyleName("newTicketAttachmentButton");
		//newTicketFlex.setWidget(startRow + 10 + spacing, columnStart, attachmentButton);
		//RootPanel.get("newticketbit").add(attachmentButton);
		newTicketFlex.add(attachmentButton);
		attachmentButton.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				
			    upload.setName("uploadFormElement");
			    upload.click();
			    //String filename = upload.getFilename();
			    
				
			    form.setAction("/b/barnacle/attch");
				
				form.setEncoding(FormPanel.ENCODING_MULTIPART);
			    form.setMethod(FormPanel.METHOD_POST);
			    
			    Window.confirm("brancle attch");
			    
			    
			    newTicketFlex.add(form);
		
			}});
		
		
		cancelTicketButton.setText("Cancel");
		cancelTicketButton.setStyleName("newTicketCancelButton");
		//newTicketFlex.setWidget(startRow + 10 + spacing, columnStart + 1, cancelTicketButton);
		//RootPanel.get("newticketbit").add(cancelTicketButton);
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
					
					
					
					//RootPanel.get("tickets-ticket-table").clear();
					//newTicketFlex.removeAllRows();
					
					pillion.hba.hub.client.TicketPage.ticketsForm();
					}
			}
		}
		);
		
		submitTicketButton.setText("Submit");
		submitTicketButton.setStyleName("newTicketSubmitButton");
		//newTicketFlex.setWidget((startRow + 10 + spacing), columnStart + 2, submitTicketButton);
		//RootPanel.get("newticketbit").add(submitTicketButton);
		newTicketFlex.add(submitTicketButton);
		submitTicketButton.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				
				//String fileName = upload.getFilename();
									//File file = fileName;
	
			
				//String path = fileName;
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
							detailsTextArea.setValue(null);}});
						}
			}
			
		});

		return newTicketFlex;
	}
}

