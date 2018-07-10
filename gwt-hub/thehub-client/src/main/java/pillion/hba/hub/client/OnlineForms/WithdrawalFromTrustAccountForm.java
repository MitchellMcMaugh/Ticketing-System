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

public class WithdrawalFromTrustAccountForm {
	public static FlexTable withdrawalFromTrustAccountForm(String status, FlexTable gridWFTA) {
		if (status != "Delete") {
			//Withdrawal From Trust Account Form Labels
			Label WFTADateOfWithdrawalLabel = new Label();
			WFTADateOfWithdrawalLabel.setText("Date Of Withdrawal");
			
			Label WFTAClientNameLabel = new Label();
			WFTAClientNameLabel.setText("Client Name");
			
			Label WFTAMatterNumberLabel = new Label();
			WFTAMatterNumberLabel.setText("Matter Number");
			
			Label WFTAMatterNameLabel = new Label();
			WFTAMatterNameLabel.setText("Matter Name");
			
			Label WFTAPayeeLabel = new Label();
			WFTAPayeeLabel.setText("Payee");
			
			Label WFTAAmountLabel = new Label();
			WFTAAmountLabel.setText("Amount");
			
			Label WFTAPurposeOfWithdrawalLabel = new Label();
			WFTAPurposeOfWithdrawalLabel.setText("Purpose Of Withdrawal");
			
			//Cheque
			Label WFTAChequeNumberLabel = new Label();
			WFTAChequeNumberLabel.setText("Cheque Number");
			
			Label WFTASignatoriesToChequeLabel = new Label();
			WFTASignatoriesToChequeLabel.setText("Signatories to cheque");
			
			//EFTPOS
			Label WFTABSBLabel = new Label();
			WFTABSBLabel.setText("BSB");
			
			Label WFTAAccountNumberLabel = new Label();
			WFTAAccountNumberLabel.setText("Account Number");
			
			Label WFTAAccountNameLabel = new Label();
			WFTAAccountNameLabel.setText("Account Name");
			
			Label WFTAReferenceLabel = new Label();
			WFTAReferenceLabel.setText("Reference");
			
			Label WFTASignatoriesToEFTLabel = new Label();
			WFTASignatoriesToEFTLabel.setText("Signatories to EFT");
			
			
			//Withdrawal From Trust Account Form Widgets
			
			DateBox WFTADateOfWithdrawal = new DateBox();
			WFTADateOfWithdrawal.setValue(new Date());
			WFTADateOfWithdrawal.setFormat(new DateBox.DefaultFormat (DateTimeFormat.getFormat("dd MMMM, yyyy"))); 
			
			TextBox WFTAClientName = new TextBox();
			
			TextBox WFTAMatterNumber = new TextBox();
			
			TextBox WFTAMatterName = new TextBox();
			
			TextBox WFTAPayee = new TextBox();
			
			TextBox WFTAAmount = new TextBox();
			
			TextBox WFTAPurposeOfWithdrawal = new TextBox();
			
			//Cheque Widgets
			
			TextBox WFTAChequeNumber = new TextBox();
			TextBox WFTASignatoriesToCheque = new TextBox();

			//EFTPOS Widgets
			
			TextBox WFTABSB = new TextBox();
			
			TextBox WFTAAccountNumber = new TextBox();
			
			TextBox WFTAAccountName = new TextBox();
			
			TextBox WFTAReference = new TextBox();
			
			TextBox WFTASignatoriesToEFT = new TextBox();
			
			Button WFTAIfPaidByChequeButton = new Button();
			WFTAIfPaidByChequeButton.setText("Paid by Cheque");
			WFTAIfPaidByChequeButton.addClickHandler(new ClickHandler() {
				public void onClick(ClickEvent event) {
					gridWFTA.remove(WFTABSBLabel);
					gridWFTA.remove(WFTABSB);

					gridWFTA.remove(WFTAAccountNumberLabel);
					gridWFTA.remove(WFTAAccountNumber);

					gridWFTA.remove(WFTAAccountNameLabel);
					gridWFTA.remove(WFTAAccountName);

					gridWFTA.remove(WFTAReferenceLabel);
					gridWFTA.remove(WFTAReference);

					gridWFTA.remove(WFTASignatoriesToEFTLabel);
					gridWFTA.remove(WFTASignatoriesToEFT);
					
					//Creating

					gridWFTA.setWidget(9, 1, WFTAChequeNumberLabel);
					gridWFTA.setWidget(9, 2, WFTAChequeNumber);
					
					gridWFTA.setWidget(10, 1, WFTASignatoriesToChequeLabel);
					gridWFTA.setWidget(10, 2, WFTASignatoriesToCheque);
				
		}
		});
			
			Button WFTAIfPaidByEftpos = new Button();
			WFTAIfPaidByEftpos.setText("Paid by EFTPOS");
			WFTAIfPaidByEftpos.addClickHandler(new ClickHandler() {
				public void onClick(ClickEvent event) {
					gridWFTA.remove(WFTASignatoriesToEFTLabel);
					gridWFTA.remove(WFTASignatoriesToEFT);
					
					gridWFTA.remove(WFTASignatoriesToChequeLabel);
					gridWFTA.remove(WFTASignatoriesToCheque);
					
					
					//Creating
					gridWFTA.setWidget(9, 1, WFTABSBLabel);
					gridWFTA.setWidget(9, 2, WFTABSB);
					
					gridWFTA.setWidget(10, 1, WFTAAccountNumberLabel);
					gridWFTA.setWidget(10, 2, WFTAAccountNumber);
					
					gridWFTA.setWidget(11, 1, WFTAAccountNameLabel);
					gridWFTA.setWidget(11, 2, WFTAAccountName);
					
					gridWFTA.setWidget(12, 1, WFTAReferenceLabel);
					gridWFTA.setWidget(12, 2, WFTAReference);
					
					gridWFTA.setWidget(13, 1, WFTASignatoriesToEFTLabel);
					gridWFTA.setWidget(13, 2, WFTASignatoriesToEFT);
		}
		});
			
			
			// (int row, int column)
			
			gridWFTA.setWidget(1, 1, WFTADateOfWithdrawalLabel);
			gridWFTA.setWidget(1, 2, WFTADateOfWithdrawal);
			
			gridWFTA.setWidget(2, 1, WFTAClientNameLabel);
			gridWFTA.setWidget(2, 2, WFTAClientName);
			
			gridWFTA.setWidget(3, 1, WFTAMatterNumberLabel);
			gridWFTA.setWidget(3, 2, WFTAMatterNumber);
			
			gridWFTA.setWidget(4, 1, WFTAMatterNameLabel);
			gridWFTA.setWidget(4, 2, WFTAMatterName);
			
			gridWFTA.setWidget(5, 1, WFTAPayeeLabel);
			gridWFTA.setWidget(5, 2, WFTAPayee);
			
			gridWFTA.setWidget(6, 1, WFTAAmountLabel);
			gridWFTA.setWidget(6, 2, WFTAAmount);
			
			gridWFTA.setWidget(7, 1, WFTAPurposeOfWithdrawalLabel);
			gridWFTA.setWidget(7, 2, WFTAPurposeOfWithdrawal);
			
			gridWFTA.setWidget(8, 1, WFTAIfPaidByChequeButton);
			gridWFTA.setWidget(8, 2, WFTAIfPaidByEftpos);
			

			
		}
		if (status == "Delete") {
			//Remove Grid
			gridWFTA.clear();
			RootPanel.get("two").remove(gridWFTA);
			RootPanel.get("two").clear();
				}
			return gridWFTA;
		}
	}

