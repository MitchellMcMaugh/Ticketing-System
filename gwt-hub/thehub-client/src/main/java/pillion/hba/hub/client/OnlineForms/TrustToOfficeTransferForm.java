package pillion.hba.hub.client.OnlineForms;

import java.util.Date;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.datepicker.client.DateBox;
import com.google.gwt.event.dom.client.ClickHandler;

public class TrustToOfficeTransferForm {
	public static FlexTable trustToOfficeTransferForm(String status, FlexTable gridTTOT) {
		if (status != "Delete") {
			//Trust To Office Transfer Form Labels
			Label TTOTDateOfTransferLabel = new Label();
			TTOTDateOfTransferLabel.setText("Date Of Transfer");
			
			Label TTOTClientNameLabel = new Label();
			TTOTClientNameLabel.setText("Client Name");
			
			Label TTOTMatterNumberLabel = new Label();
			TTOTMatterNumberLabel.setText("Matter Number");
			
			Label TTOTMatterNameLabel = new Label();
			TTOTMatterNameLabel.setText("Matter Name");
			
			Label TTOTAmountLabel = new Label();
			TTOTAmountLabel.setText("Total Amount");
			
			Label TTOTPurposeInvoiceNumbersLabel = new Label();
			TTOTPurposeInvoiceNumbersLabel.setText("Purpose/ Invoice Numbers");
			
			
			//Withdrawal From Trust Account Form Widgets
			
			DateBox TTOTDateOfTransfer = new DateBox();
			TTOTDateOfTransfer.setValue(new Date());
			TTOTDateOfTransfer.setFormat(new DateBox.DefaultFormat (DateTimeFormat.getFormat("dd MMMM, yyyy"))); 
			
			TextBox TTOTClientName = new TextBox();
			
			TextBox TTOTMatterNumber = new TextBox();
			
			TextBox TTOTMatterName = new TextBox();
			
			TextBox TTOTAmount = new TextBox();
			
			TextBox TTOTPurposeInvoiceNumbers = new TextBox();
			

		
			
			
			// (int row, int column)
			
			gridTTOT.setWidget(1, 1, TTOTDateOfTransferLabel);
			gridTTOT.setWidget(1, 2, TTOTDateOfTransfer);
			
			gridTTOT.setWidget(2, 1, TTOTClientNameLabel);
			gridTTOT.setWidget(2, 2, TTOTClientName);
			
			gridTTOT.setWidget(3, 1, TTOTMatterNumberLabel);
			gridTTOT.setWidget(3, 2, TTOTMatterNumber);
			
			gridTTOT.setWidget(4, 1, TTOTMatterNameLabel);
			gridTTOT.setWidget(4, 2, TTOTMatterName);
			
			gridTTOT.setWidget(6, 1, TTOTAmountLabel);
			gridTTOT.setWidget(6, 2, TTOTAmount);
			
			gridTTOT.setWidget(7, 1, TTOTPurposeInvoiceNumbersLabel);
			gridTTOT.setWidget(7, 2, TTOTPurposeInvoiceNumbers);

			
		}
		if (status == "Delete") {
			//Remove Grid
			gridTTOT.clear();
			RootPanel.get("two").remove(gridTTOT);
			RootPanel.get("two").clear();
				}
			return gridTTOT;
		}
}
