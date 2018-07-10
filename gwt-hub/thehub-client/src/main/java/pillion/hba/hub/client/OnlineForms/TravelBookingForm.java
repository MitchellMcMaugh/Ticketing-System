package pillion.hba.hub.client.OnlineForms;

import java.util.Date;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.datepicker.client.DateBox;
import com.google.gwt.user.client.ui.RadioButton;

public class TravelBookingForm {
	public static FlexTable travelBookingForm(String status, FlexTable gridTB) {
		if (status != "Delete") {
			//Travel Booking Form Labels
			Label TBDateLabel = new Label();
			TBDateLabel.setText("Date");
			
			Label TBPersonTravellingLabel = new Label();
			TBPersonTravellingLabel.setText("Person Travelling");
			
			Label TBQantasFFNumberLabel = new Label();
			TBQantasFFNumberLabel.setText("Qantas FF Number");
			
			Label TBAirTravelLabel = new Label();
			TBAirTravelLabel.setText("Air Travel");
			
			Label TBTravellingFromLabel = new Label();
			TBTravellingFromLabel.setText("Travelling From");
			
			Label TBTravellingToLabel = new Label();
			TBTravellingToLabel.setText("Travelling To");
			
			Label TBRequestFlyOutDateTimeLabel = new Label();
			TBRequestFlyOutDateTimeLabel.setText("Request Fly Out Date & Time");
			
			Label TBRequestFlyBackDateTimeLabel = new Label();
			TBRequestFlyBackDateTimeLabel.setText("Request Fly Back Date & Time");
			
			Label TBPurposeOfTravelLabel = new Label();
			TBPurposeOfTravelLabel.setText("Purpose of Travel");
			
			Label TBAirChargeToClientLabel = new Label();
			TBAirChargeToClientLabel.setText("Charge to client");
			
			Label TBAirMatterNumberLabel = new Label();
			TBAirMatterNumberLabel.setText("Matter Number");
			
			Label TBAirMatterNameLabel = new Label();
			TBAirMatterNameLabel.setText("Matter Name");
			
			Label TBAccommodationLabel = new Label();
			TBAccommodationLabel.setText("Accommodation");
			
			Label TBCheckInDateTimeLabel = new Label();
			TBCheckInDateTimeLabel.setText("Check In Date & Time");
			
			Label TBCheckOutDateTimeLabel = new Label();
			TBCheckOutDateTimeLabel.setText("Check Out Date & Time");
			
			Label TBAccomodationChargeToClientLabel = new Label();
			TBAccomodationChargeToClientLabel.setText("Charge to client");
			
			Label TBAccomodationMatterNumberLabel = new Label();
			TBAccomodationMatterNumberLabel.setText("Matter Number");
			
			Label TBAccomodationMatterNameLabel = new Label();
			TBAccomodationMatterNameLabel.setText("Matter Name");
			
			
			
			
			// Form Widgets
			
			DateBox TBDate = new DateBox();
			TBDate.setValue(new Date());
			TBDate.setFormat(new DateBox.DefaultFormat (DateTimeFormat.getFormat("dd MMMM, yyyy"))); 

			TextBox TBPersonTravelling = new TextBox();
			
			TextBox TBQantasFFNumber = new TextBox();

			TextBox TBTravellingFrom = new TextBox();
			
			TextBox TBTravellingTo = new TextBox();
			
			DateBox TBRequestFlyOutDateTime = new DateBox();
			TBRequestFlyOutDateTime.setValue(new Date());
			TBRequestFlyOutDateTime.setFormat(new DateBox.DefaultFormat (DateTimeFormat.getFormat("dd MMMM, yyyy"))); 

			DateBox TBRequestFlyBackDateTime = new DateBox();
			TBRequestFlyBackDateTime.setValue(new Date());
			TBRequestFlyBackDateTime.setFormat(new DateBox.DefaultFormat (DateTimeFormat.getFormat("dd MMMM, yyyy"))); 
			
			TextBox TBPurposeOfTravel = new TextBox();
			
			RadioButton TBAirChargeToClientYes = new RadioButton("TBAirChargeToClientGroup", "Yes");
			RadioButton TBAirChargeToClientNo = new RadioButton("TBAirChargeToClientGroup", "No");
			
			TextBox TBAirMatterNumber = new TextBox();
			
			TextBox TBAirMatterName = new TextBox();
			
			DateBox TBCheckInDateTime = new DateBox();
			TBCheckInDateTime.setValue(new Date());
			TBCheckInDateTime.setFormat(new DateBox.DefaultFormat (DateTimeFormat.getFormat("dd MMMM, yyyy"))); 
			
			DateBox TBCheckOutDateTime = new DateBox();
			TBCheckOutDateTime.setValue(new Date());
			TBCheckOutDateTime.setFormat(new DateBox.DefaultFormat (DateTimeFormat.getFormat("dd MMMM, yyyy"))); 
			
			RadioButton TBAccomodationChargeToClientYes = new RadioButton("TBAccomodationChargeToClientGroup", "Yes");
			RadioButton TBAccomodationChargeToClientNo = new RadioButton("TBAccomodationChargeToClientGroup", "No");
					

			TextBox TBAccomodationMatterNumber = new TextBox();
			
			TextBox TBAccomodationMatterName = new TextBox();
			
			
			// (int row, int column)
			gridTB.setWidget(1, 1, TBDateLabel);
			gridTB.setWidget(1, 2, TBDate);
			

			gridTB.setWidget(2, 1, TBPersonTravellingLabel);
			gridTB.setWidget(2, 2, TBPersonTravelling);
			
			
			gridTB.setWidget(3, 1, TBQantasFFNumberLabel);
			gridTB.setWidget(3, 2, TBQantasFFNumber);
		
			gridTB.setWidget(4, 1, TBAirTravelLabel);
			
			gridTB.setWidget(5, 1, TBTravellingFromLabel);
			gridTB.setWidget(5, 2, TBTravellingFrom);
			
			gridTB.setWidget(6, 1, TBTravellingToLabel);
			gridTB.setWidget(6, 2, TBTravellingTo);
			
			gridTB.setWidget(7, 1, TBRequestFlyOutDateTimeLabel);
			gridTB.setWidget(7, 2, TBRequestFlyOutDateTime);
			
			gridTB.setWidget(8, 1, TBRequestFlyBackDateTimeLabel);
			gridTB.setWidget(8, 2, TBRequestFlyBackDateTime);
			
			gridTB.setWidget(9, 1, TBPurposeOfTravelLabel);
			gridTB.setWidget(9, 2, TBPurposeOfTravel);
			
			gridTB.setWidget(10, 1, TBAirChargeToClientLabel);
			gridTB.setWidget(10, 2, TBAirChargeToClientYes);
			gridTB.setWidget(10, 3, TBAirChargeToClientNo);
			
			gridTB.setWidget(11, 1, TBAirMatterNumberLabel);
			gridTB.setWidget(11, 2, TBAirMatterNumber);
			
			gridTB.setWidget(12, 1, TBAirMatterNameLabel);
			gridTB.setWidget(12, 2, TBAirMatterName);
			
			gridTB.setWidget(13, 1, TBAccommodationLabel);
			
			gridTB.setWidget(14, 1, TBCheckInDateTimeLabel);
			gridTB.setWidget(14, 2, TBCheckInDateTime);
			
			gridTB.setWidget(15, 1, TBCheckOutDateTimeLabel);
			gridTB.setWidget(15, 2, TBCheckOutDateTime);
			
			gridTB.setWidget(16, 1, TBAccomodationChargeToClientLabel);
			gridTB.setWidget(16, 2, TBAccomodationChargeToClientYes);
			gridTB.setWidget(16, 3, TBAccomodationChargeToClientNo);
		
			gridTB.setWidget(17, 1, TBAccomodationMatterNumberLabel);
			gridTB.setWidget(17, 2, TBAccomodationMatterNumber);
		
			gridTB.setWidget(18, 1, TBAccomodationMatterNameLabel);
			gridTB.setWidget(18, 2, TBAccomodationMatterName);
			
		}
		if (status == "Delete") {
			//Remove Grid
			gridTB.clear();
			RootPanel.get("two").remove(gridTB);
			RootPanel.get("two").clear();}
		return gridTB;
		}
}
