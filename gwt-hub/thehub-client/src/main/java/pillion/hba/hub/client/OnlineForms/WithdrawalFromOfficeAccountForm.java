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

public class WithdrawalFromOfficeAccountForm {
	public static FlexTable withdrawalFromOfficeAccountForm(String status, FlexTable gridWFOA) {
		if (status != "Delete") {
			//Withdrawal From Trust Account Form Labels
			Label WFOADateOfWithdrawalLabel = new Label();
			WFOADateOfWithdrawalLabel.setText("Date Of Withdrawal");
			
			Label WFOAClientNameLabel = new Label();
			WFOAClientNameLabel.setText("Client Name");
			
			Label WFOAMatterNumberLabel = new Label();
			WFOAMatterNumberLabel.setText("Matter Number");
			
			Label WFOAMatterNameLabel = new Label();
			WFOAMatterNameLabel.setText("Matter Name");
			
			Label WFOAPayeeLabel = new Label();
			WFOAPayeeLabel.setText("Payee");
			
			Label WFOAAmountLabel = new Label();
			WFOAAmountLabel.setText("Amount");
			
			Label WFOAPurposeOfWithdrawalLabel = new Label();
			WFOAPurposeOfWithdrawalLabel.setText("Purpose Of Withdrawal");
			
			//Cheque
			Label WFOANameOfPayeeOnChequeLabel = new Label();
			WFOANameOfPayeeOnChequeLabel.setText("Name of payee on cheque");
			
			Label WFOAChequeNumberLabel = new Label();
			WFOAChequeNumberLabel.setText("Cheque Number");
			
			Label WFOASignatoriesToChequeLabel = new Label();
			WFOASignatoriesToChequeLabel.setText("Signatories to cheque");
			
			//EFTPOS
			Label WFOABSBLabel = new Label();
			WFOABSBLabel.setText("BSB");
			
			Label WFOAAccountNumberLabel = new Label();
			WFOAAccountNumberLabel.setText("Account Number");
			
			Label WFOAAccountNameLabel = new Label();
			WFOAAccountNameLabel.setText("Account Name");
			
			Label WFOAReferenceLabel = new Label();
			WFOAReferenceLabel.setText("Reference");
			
			//Withdrawal From Trust Account Form Widgets
			
			DateBox WFOADateOfWithdrawal = new DateBox();
			WFOADateOfWithdrawal.setValue(new Date());
			WFOADateOfWithdrawal.setFormat(new DateBox.DefaultFormat (DateTimeFormat.getFormat("dd MMMM, yyyy"))); 
			
			TextBox WFOAClientName = new TextBox();
			
			TextBox WFOAMatterNumber = new TextBox();
			
			TextBox WFOAMatterName = new TextBox();
			
			TextBox WFOAPayee = new TextBox();
			
			TextBox WFOAAmount = new TextBox();
			
			TextBox WFOAPurposeOfWithdrawal = new TextBox();
			
			//Cheque Widgets
			
			TextBox WFOANameOfPayeeOnCheque = new TextBox();
			TextBox WFOAChequeNumber = new TextBox();
			TextBox WFOASignatoriesToCheque = new TextBox();

			//EFTPOS Widgets
			
			TextBox WFOABSB = new TextBox();
			
			TextBox WFOAAccountNumber = new TextBox();
			
			TextBox WFOAAccountName = new TextBox();
			
			TextBox WFOAReference = new TextBox();
			
			Button WFOAIfPaidByChequeButton = new Button();
			WFOAIfPaidByChequeButton.setText("Paid by Cheque");
			WFOAIfPaidByChequeButton.addClickHandler(new ClickHandler() {
				public void onClick(ClickEvent event) {
					
					
					gridWFOA.remove(WFOABSBLabel);
					gridWFOA.remove(WFOABSB);

					gridWFOA.remove(WFOAAccountNumberLabel);
					gridWFOA.remove(WFOAAccountNumber);

					gridWFOA.remove(WFOAAccountNameLabel);
					gridWFOA.remove(WFOAAccountName);

					gridWFOA.remove(WFOAReferenceLabel);
					gridWFOA.remove(WFOAReference);

					
					//Creating
					gridWFOA.setWidget(9,1,WFOANameOfPayeeOnChequeLabel);
					gridWFOA.setWidget(9,2,WFOANameOfPayeeOnCheque);
					
					gridWFOA.setWidget(10, 1, WFOAChequeNumberLabel);
					gridWFOA.setWidget(10, 2, WFOAChequeNumber);
					
					gridWFOA.setWidget(11, 1, WFOASignatoriesToChequeLabel);
					gridWFOA.setWidget(11, 2, WFOASignatoriesToCheque);
				
		}
		});
			
			Button WFOAIfPaidByEftpos = new Button();
			WFOAIfPaidByEftpos.setText("Paid by EFTPOS");
			WFOAIfPaidByEftpos.addClickHandler(new ClickHandler() {
				public void onClick(ClickEvent event) {
					gridWFOA.remove(WFOANameOfPayeeOnChequeLabel);
					gridWFOA.remove(WFOANameOfPayeeOnCheque);
					
					gridWFOA.remove(WFOASignatoriesToChequeLabel);
					gridWFOA.remove(WFOASignatoriesToCheque);
					
					gridWFOA.remove(WFOASignatoriesToChequeLabel);
					gridWFOA.remove(WFOASignatoriesToCheque);
					
					
					//Creating
					gridWFOA.setWidget(9, 1, WFOABSBLabel);
					gridWFOA.setWidget(9, 2, WFOABSB);
					
					gridWFOA.setWidget(10, 1, WFOAAccountNumberLabel);
					gridWFOA.setWidget(10, 2, WFOAAccountNumber);
					
					gridWFOA.setWidget(11, 1, WFOAAccountNameLabel);
					gridWFOA.setWidget(11, 2, WFOAAccountName);
					
					gridWFOA.setWidget(12, 1, WFOAReferenceLabel);
					gridWFOA.setWidget(12, 2, WFOAReference);
					
		}
		});
			
			
			// (int row, int column)
			
			gridWFOA.setWidget(1, 1, WFOADateOfWithdrawalLabel);
			gridWFOA.setWidget(1, 2, WFOADateOfWithdrawal);
			
			gridWFOA.setWidget(2, 1, WFOAClientNameLabel);
			gridWFOA.setWidget(2, 2, WFOAClientName);
			
			gridWFOA.setWidget(3, 1, WFOAMatterNumberLabel);
			gridWFOA.setWidget(3, 2, WFOAMatterNumber);
			
			gridWFOA.setWidget(4, 1, WFOAMatterNameLabel);
			gridWFOA.setWidget(4, 2, WFOAMatterName);
			
			gridWFOA.setWidget(5, 1, WFOAPayeeLabel);
			gridWFOA.setWidget(5, 2, WFOAPayee);
			
			gridWFOA.setWidget(6, 1, WFOAAmountLabel);
			gridWFOA.setWidget(6, 2, WFOAAmount);
			
			gridWFOA.setWidget(7, 1, WFOAPurposeOfWithdrawalLabel);
			gridWFOA.setWidget(7, 2, WFOAPurposeOfWithdrawal);
			
			gridWFOA.setWidget(8, 1, WFOAIfPaidByChequeButton);
			gridWFOA.setWidget(8, 2, WFOAIfPaidByEftpos);
			

			
		}
		if (status == "Delete") {
			//Remove Grid
			gridWFOA.clear();
			RootPanel.get("two").remove(gridWFOA);
			RootPanel.get("two").clear();
				}
			return gridWFOA;
		}
}


