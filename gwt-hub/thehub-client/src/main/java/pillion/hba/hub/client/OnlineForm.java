package pillion.hba.hub.client;

import pillion.hba.hub.client.OnlineFormMenu.OnlineFormMenu;
import pillion.hba.hub.client.OnlineForms.TravelExpenseForm;
import pillion.hba.hub.client.OnlineForms.UseOfPersonalMotorVehicleForWorkRelatedTravelForm;
import pillion.hba.hub.client.OnlineForms.WithdrawalFromTrustAccountForm;
import pillion.hba.hub.client.OnlineForms.GoogleChart;

import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.cellview.client.SimplePager;
import com.google.gwt.user.client.ui.DockPanel;


public class OnlineForm {
	
	static DockPanel docker = new DockPanel();
	static String pageNumber;
	static String currentPage;
	static String status = "Keep";
	static VerticalPanel vPanel2 = new VerticalPanel();
	static VerticalPanel vPanel3 = new VerticalPanel();
	static FlexTable grid = new FlexTable();
	
	public void go() {
		VerticalPanel vPanel = OnlineFormMenu.OnlineFormMenuCreate();
	    RootPanel.get("one").add(vPanel);   
		//FlexTable grid = new FlexTable();
	}
	
	public static String getPageNumber(SimplePager pagerName) {
		int pageNumber = pagerName.getPage();
		String pageString = String.valueOf(pageNumber);
		return pageString;
		
	}
	
	public static void doStuffWithPageNumber(String selected) {

		if (selected == "Online Forms") {ClearForms();}
		else if (selected == "Google Chart") {ClearForms(); GoogleCharts(status);}
		else if (selected == "Consent for Photo and Video Recording and Release") {ClearForms();ConsentForPhotoAndVideoRecordingAndRelease(status);}
		else if (selected == "Courier Approval Form") {ClearForms();CourierApprovalForm(status);}
		else if (selected == "Deposit to Trust Account" ) {ClearForms();DepositToTrustAccount(status);}
		else if (selected == "File Closing Form") {ClearForms();FileClosingForm(status);}
		else if (selected == "File Opening Form") {ClearForms();FileOpeningForm(status);}
		else if (selected == "HBA Giving Program Application Form") {ClearForms();HBAGivingProgramApplicationForm(status);}
		else if (selected == "New Client Form") {ClearForms();NewClientForm(status);}
		else if (selected == "Petty Cash Form") {ClearForms();PettyCashForm(status);}
		else if (selected == "Travel Booking Form") {ClearForms();TravelBookingForm(status);}
		else if (selected == "Travel Expenses Form") {ClearForms();TravelExpensesForm(status);}
		else if (selected == "Trust to Office Transfer Form") {ClearForms();TrustToOfficeTransferForm(status);}
		else if (selected == "Use of Personal Motor Vehicle Claim Form") {ClearForms();UseOfPersonalMotorVehicleClaimForm(status);}
		else if (selected == "Withdrawal from Office Account Form") {ClearForms();WithdrawalFromOfficeAccountForm(status);}
		else if (selected == "Withdrawal from Trust Account Form") {ClearForms();WithdrawalFromTrustAccountForm(status);}
	}
	
	
	public static void ClearForms() {
		status = "Delete";
			ConsentForPhotoAndVideoRecordingAndRelease(status);
			CourierApprovalForm(status);
			DepositToTrustAccount(status);
			FileClosingForm(status);
			FileOpeningForm(status);
			HBAGivingProgramApplicationForm(status);
			NewClientForm(status);
			PettyCashForm(status);
			TravelBookingForm(status);
			TravelExpensesForm(status);
			TrustToOfficeTransferForm(status);
			UseOfPersonalMotorVehicleClaimForm(status);
			WithdrawalFromOfficeAccountForm(status);
			WithdrawalFromTrustAccountForm(status);
		status = "Keep";
	}
	
	public static void ConsentForPhotoAndVideoRecordingAndRelease(String status) {
		
	}
	
	public static void CourierApprovalForm(String status) {
		
		
	}
	
	public static void DepositToTrustAccount(String status) {
	}
	
	public static void FileClosingForm(String status) {
		//grid = pillion.hba.hub.client.OnlineForms.FileClosingForm.fileClosingForm(status, grid);
		RootPanel.get("two").add(grid);
	}
	
	
	
	public static void FileOpeningForm(String status) {
	}
	
	public static void GoogleCharts(String status) {
		grid = pillion.hba.hub.client.OnlineForms.GoogleChart.GoogleChartMethod(status, grid);
		RootPanel.get("two").add(grid);
	}
	
	public static void HBAGivingProgramApplicationForm(String status) {
	}
	
	public static void NewClientForm(String status) {
	}
	
	public static void PettyCashForm(String status) {
		grid = pillion.hba.hub.client.OnlineForms.PettyCashForm.trustToOfficeTransferForm(status, grid);
		RootPanel.get("two").add(grid);
	}
	
	public static void TravelBookingForm(String status) {
		grid = pillion.hba.hub.client.OnlineForms.TravelBookingForm.travelBookingForm(status, grid);
		RootPanel.get("two").add(grid);
	}
	
	public static void TravelExpensesForm(String status) {
		grid = TravelExpenseForm.travelExpenseForm(status, grid);
	    RootPanel.get("two").add(grid);
	}
	
	public static void TrustToOfficeTransferForm(String status) {
		grid = pillion.hba.hub.client.OnlineForms.TrustToOfficeTransferForm.trustToOfficeTransferForm(status, grid);
		RootPanel.get("two").add(grid);
	}
	
	public static void UseOfPersonalMotorVehicleClaimForm(String status) {
		grid = UseOfPersonalMotorVehicleForWorkRelatedTravelForm.fileClosingForm(status, grid);
		RootPanel.get("two").add(grid);
	}
	
	public static void WithdrawalFromOfficeAccountForm(String status) {
		//grid = UseOfPersonalMotorVehicleForWorkRelatedTravelForm.fileClosingForm(status, grid);
		grid = pillion.hba.hub.client.OnlineForms.WithdrawalFromOfficeAccountForm.withdrawalFromOfficeAccountForm(status, grid);
		RootPanel.get("two").add(grid);
	}
	
	public static void WithdrawalFromTrustAccountForm(String status) {
		grid = WithdrawalFromTrustAccountForm.withdrawalFromTrustAccountForm(status, grid);
		RootPanel.get("two").add(grid);
	}

}


