package pillion.hba.hub.client.Tickets;

import java.util.Arrays;

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
import com.google.gwt.user.client.ui.FormPanel.SubmitCompleteEvent;


public class NewTicket {
	
	public static final RedmineServiceAsync redmineService = GWT.create(RedmineService.class);
	public static FlowPanel newTicketFlex = new FlowPanel();
	public static FlowPanel attachmentPanel = new FlowPanel();
	public static FileUpload upload = new FileUpload();
	public static FormPanel form = new FormPanel();
	public static TextBox hidden = new TextBox();

	public static FlowPanel newTicket() {
		form.remove(attachmentPanel);
		
		hidden.setText("0");
		hidden.setName("hidden");
		upload.setName("uploadFormElement");
		hidden.setVisible(false);
		upload.setVisible(true);
		form.setAction("/b/barnacle/attch");
		form.setEncoding(FormPanel.ENCODING_MULTIPART);
		form.setMethod(FormPanel.METHOD_POST);
		attachmentPanel.add(hidden);
		attachmentPanel.add(upload);
		form.add(attachmentPanel);
		
		// Priority List Box //
		ListBox newTicketPriorityListBox = new ListBox(); newTicketPriorityListBox.setStyleName("listBoxez");
		
		String[] ticketFilterPriorities = {"Priority", 
				"Low", 
				"Normal", 
				"High", 
				"Urgent"};
		
		for (int i = 0; i < ticketFilterPriorities.length; i++) {
			newTicketPriorityListBox.addItem(ticketFilterPriorities[i]);
		}
		
		RootPanel.get("newtickettopbit").add(newTicketPriorityListBox);
		newTicketPriorityListBox.setSelectedIndex(0);
		// End Priority List Box //
		
		// Category List Box //
		ListBox newTicketCategoryListBox = new ListBox(); newTicketCategoryListBox.setStyleName("listBoxez");
		
		String[] ticketFilterCategories = {
				"Category", 
				"Affinity Issue", 
				"Big Hand Issue", 
				"Hardware Issue", 
				"Lost File Issue", 
				"Microsoft Office 365 Issue", 
				"Microsoft Windows Issue",
				"Printing Issue", 
				"Remote Desktop Issue", 
				"Software Issue", 
				"User Login Issue", 
				"Other Issue"};
		
		for (int i = 0; i < ticketFilterCategories.length; i++) {
			newTicketCategoryListBox.addItem(ticketFilterCategories[i]);
		}
		
		RootPanel.get("newtickettopbit").add(newTicketCategoryListBox);
		newTicketCategoryListBox.setSelectedIndex(0);
		// End Category List Box //
		
		// Subject //
		Label titleTextBoxLabel = new Label("Subject"); titleTextBoxLabel.setStyleName("titleTextBoxLabel");
		TextBox titleTextBox = new TextBox(); titleTextBox.setStyleName("titleTextBox");
		
		newTicketFlex.add(titleTextBoxLabel);
		newTicketFlex.add(titleTextBox);
		// End Subject //
		
		
		
		// Details //
		Label detailsTextAreaLabel = new Label("Details"); detailsTextAreaLabel.setStyleName("detailsTextAreaLabel");
		
		TextArea detailsTextArea = new TextArea(); detailsTextArea.setStyleName("detailsTextArea");
		detailsTextArea.setVisibleLines(10);
		
		newTicketFlex.add(detailsTextAreaLabel);
		newTicketFlex.add(detailsTextArea);
		// End Details //
		
		// Attachment Button //
		newTicketFlex.add(form); upload.addStyleName("uploadTicketFile");
		// End Attachment Button //
		
		// Cancel Button // 
		Button cancelTicketButton = new Button("Cancel");
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
					form.remove(upload);
					RootPanel.get("newticketticketbit").remove(newTicketFlex);
					newTicketFlex.clear();
					RootPanel.get("newtickettopbit").remove(newTicketCategoryListBox);
			    	RootPanel.get("newtickettopbit").remove(newTicketPriorityListBox);
					pillion.hba.hub.client.TicketPage.ticketsForm(false);
					}
				}
			}
		);
		// End Cancel Button //
		
		// Submit Button //
		Button submitTicketButton = new Button("Submit");
		submitTicketButton.setStyleName("newTicketSubmitButton");
		newTicketFlex.add(submitTicketButton);
		submitTicketButton.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				String titleTextValue = titleTextBox.getText();
				String detailsTextAreaValue = detailsTextArea.getText();
				String ticketPriorityListBoxValue = newTicketPriorityListBox.getSelectedItemText().toString();
				String ticketCategoryListBoxValue = newTicketCategoryListBox.getSelectedItemText().toString();
				
				if (titleTextValue == "") {
					Window.alert("Please enter a subject for your issue.");
				}
				else if (detailsTextAreaValue == "") {
					Window.alert("Please enter a description for your issue.");
				}
				else if (ticketPriorityListBoxValue == "" || ticketPriorityListBoxValue == "Priority") {
					Window.alert("Please select category.");
				}
				else if (ticketCategoryListBoxValue == "" || ticketCategoryListBoxValue == "Category") {
					Window.alert("Please select category.");
				}
				else  {
					redmineService.newTicket(ticketPriorityListBoxValue, ticketCategoryListBoxValue, titleTextValue, detailsTextAreaValue,  new AsyncCallback<Ticket>() {
						public void onFailure(Throwable e) { throw new RuntimeException(e); }
						public void onSuccess(Ticket result) {	
							hidden.setText(String.valueOf(result.getTicketID()));
							form.submit();
							form.addSubmitCompleteHandler(new FormPanel.SubmitCompleteHandler() {
								public void onSubmitComplete(SubmitCompleteEvent event) {
							    	  RootPanel.get("newtickettopbit").remove(newTicketCategoryListBox);
							    	  RootPanel.get("newtickettopbit").remove(newTicketPriorityListBox);
							    	  RootPanel.get("newticketticketbit").remove(newTicketFlex);
							    	  newTicketFlex.clear();
							    	  
							    	  pillion.hba.hub.client.TicketPage.ticketsForm(true);
							      }
						    });
							titleTextBox.setValue(null);
							detailsTextArea.setValue(null);
						}});
					}
				}
			});
		// End Submit Button //
		
		return newTicketFlex;
	}
}

