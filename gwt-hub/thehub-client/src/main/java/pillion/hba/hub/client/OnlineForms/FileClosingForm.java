package pillion.hba.hub.client.OnlineForms;

import com.google.gwt.core.client.GWT;
import java.util.Date;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlexTable;

import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;

import com.google.gwt.user.client.ui.CheckBox;

import com.google.gwt.user.datepicker.client.DateBox;


import pillion.hba.hub.client.OnlineFormMenu.OnlineFormMenu;
import com.google.gwt.user.cellview.client.SimplePager;
import com.google.gwt.user.client.ui.DockPanel;
import com.google.gwt.event.dom.client.ClickHandler;

public class FileClosingForm {

	public static FlexTable fileClosingForm(String status, FlexTable gridFCF) {
		
		if (status != "Delete") {
			//File Closing Form Labels
			Label FCFDateLabel = new Label();
			FCFDateLabel.setText("Date:");
			
			Label FCFMatterNumberLabel = new Label();
			FCFMatterNumberLabel.setText("Matter Number:");
			
			Label FCFMatterNameLabel = new Label();
			FCFMatterNameLabel.setText("Matter Name:");
			
			Label FCFToBeCompletedBySolicitorLabel = new Label();
			FCFToBeCompletedBySolicitorLabel.setText("To be Completed by Solicitor:");
			
			Label FCFToBeCompletedBySecretaryLabel = new Label();
			FCFToBeCompletedBySecretaryLabel.setText("To be Completed by Secretary:");
			
			//File Closing Form Widgets
			
			CheckBox FCFSettlementAllOutstandingTasksCompletedCheckBox = new CheckBox("Settlement/all outstanding tasks completed");
		    CheckBox FCFFinalInvoiceSentCheckBox = new CheckBox("Final invoice sent");
			CheckBox FCFArchiveLetterEmailSentToClientCheckBox 	= new CheckBox("Archive letter/email sent to client");
			CheckBox FCFAllClientDocumentsSentBackToClientCheckBox = new CheckBox("All client documents sent back to client");
			CheckBox FCFReviewFileToEnsureAllFinalInvoicesFromExpertsHaveBeenReceivedAndBilledToClientCheckBox = new CheckBox("Review file to ensure all final invoices from experts etc. have been received\r\n" + 
					"and billed to client\r\n" + 
					"");
			CheckBox FCFTrustAccountUsedCheckBox = new CheckBox("Trust Account used? If so, end of matter trust statement sent to client?");
			CheckBox FCFFinalInvoicePaidCheckBox = new CheckBox("Final invoice paid");
			CheckBox FCFAnyOutstandingWIPDisbursementsClearedCheckBox = new CheckBox("Any outstanding WIP & disbursements cleared");
			CheckBox FCFFilingUpdatedCheckBox = new CheckBox("Filing updated");
			
			TextBox FCFMatterNumber = new TextBox();
			TextBox FCFMatterName = new TextBox();
			
			gridFCF.setWidget(0, 1, FCFDateLabel);
			gridFCF.setWidget(1, 1, FCFMatterNumberLabel);
			gridFCF.setWidget(2, 1, FCFMatterNameLabel);
			
			gridFCF.setWidget(3, 1, FCFToBeCompletedBySolicitorLabel);
			gridFCF.getFlexCellFormatter().setColSpan(3, 1, 10);
			
			gridFCF.setWidget(4, 1, FCFSettlementAllOutstandingTasksCompletedCheckBox);
			gridFCF.getFlexCellFormatter().setColSpan(4, 1, 10);
			
			gridFCF.setWidget(5, 1, FCFFinalInvoiceSentCheckBox);
			gridFCF.getFlexCellFormatter().setColSpan(5, 1, 10);
			
			gridFCF.setWidget(6, 1, FCFArchiveLetterEmailSentToClientCheckBox);
			gridFCF.getFlexCellFormatter().setColSpan(6, 1, 10);
			
			gridFCF.setWidget(7, 1, FCFAllClientDocumentsSentBackToClientCheckBox);
			gridFCF.getFlexCellFormatter().setColSpan(7, 1, 10);
			
			gridFCF.setWidget(8, 1, FCFReviewFileToEnsureAllFinalInvoicesFromExpertsHaveBeenReceivedAndBilledToClientCheckBox);
			gridFCF.getFlexCellFormatter().setColSpan(8, 1, 10);
			
			gridFCF.setWidget(9, 1, FCFToBeCompletedBySecretaryLabel);
			gridFCF.getFlexCellFormatter().setColSpan(9, 1, 10);
			
			gridFCF.setWidget(10, 1, FCFTrustAccountUsedCheckBox);
			gridFCF.getFlexCellFormatter().setColSpan(10, 1, 10);
			
			gridFCF.setWidget(11, 1, FCFFinalInvoicePaidCheckBox);
			gridFCF.getFlexCellFormatter().setColSpan(11, 1, 10);
			
			gridFCF.setWidget(12, 1, FCFAnyOutstandingWIPDisbursementsClearedCheckBox);
			gridFCF.getFlexCellFormatter().setColSpan(12, 1, 10);
			
			gridFCF.setWidget(13, 1, FCFFilingUpdatedCheckBox);
			gridFCF.getFlexCellFormatter().setColSpan(13, 1, 10);
			
			gridFCF.setWidget(1, 2, FCFMatterNumber);
			gridFCF.getFlexCellFormatter().setColSpan(5, 1, 10);
			
			gridFCF.setWidget(2, 2, FCFMatterName);
			gridFCF.getFlexCellFormatter().setColSpan(5, 1, 10);
			
		}
		if (status == "Delete") {
			//Remove Grid
			gridFCF.clear();
			RootPanel.get("two").remove(gridFCF);
			RootPanel.get("two").clear();
			

			
			}
		return gridFCF;
	}
	
}
