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

public class UseOfPersonalMotorVehicleForWorkRelatedTravelForm {
public static FlexTable fileClosingForm(String status, FlexTable gridUOPMV) {
		
		if (status != "Delete") {
			//Use Of Personal Motor Vehicle For Work Related Travel Form Labels
			Label UOPMVClaimDetailsLabel = new Label();
			UOPMVClaimDetailsLabel.setText("Claim Details");
			
			Label UOPMVEmployeeNameLabel = new Label();
			UOPMVEmployeeNameLabel.setText("Employee Name");
			
			Label UOPMVClientNameLabel = new Label();
			UOPMVClientNameLabel.setText("Client Name");
			
			Label UOPMVMatterNumberLabel = new Label();
			UOPMVMatterNumberLabel.setText("Matter Number");
			
			Label UOPMVMatterNameLabel = new Label();
			UOPMVMatterNameLabel.setText("Matter Name");
			
			Label UOPMVDateOfTripLabel = new Label();
			UOPMVDateOfTripLabel.setText("Date of Trip");
			
			Label UOPMVDetailsOfTripLabel = new Label();
			UOPMVDetailsOfTripLabel.setText("Details of Trip");
			
			Label UOPMVDistanceLabel = new Label();
			UOPMVDistanceLabel.setText("Distance (Km)");
			
			
			//Use Of Personal Motor Vehicle For Work Related Travel Form Widgets
			
			ListBox UOPMVEmployeeName = new ListBox();
			UOPMVEmployeeName.addItem("Mitchell McMaugh");
			
			TextBox UOPMVClientName = new TextBox();
			
			TextBox UOPMVMatterNumber = new TextBox();
			
			TextBox UOPMVMatterName = new TextBox();
			
			DateBox UOPMVDateOfTrip = new DateBox();
			UOPMVDateOfTrip.setValue(new Date());
			UOPMVDateOfTrip.setFormat(new DateBox.DefaultFormat (DateTimeFormat.getFormat("dd MMMM, yyyy"))); 
			
			TextBox UOPMVDetailsOfTrip = new TextBox();
			
			TextBox UOPMVDistance = new TextBox();
			
			
			// (int row, int column)
			gridUOPMV.setWidget(0, 1, UOPMVClaimDetailsLabel);
			
			gridUOPMV.setWidget(1, 1, UOPMVEmployeeNameLabel);
			gridUOPMV.setWidget(1, 2, UOPMVEmployeeName);
			
			gridUOPMV.setWidget(2, 1, UOPMVClientNameLabel);
			gridUOPMV.setWidget(2, 2, UOPMVClientName);
			
			gridUOPMV.setWidget(3, 1, UOPMVMatterNumberLabel);
			gridUOPMV.setWidget(3, 2, UOPMVMatterNumber);
			
			gridUOPMV.setWidget(4, 1, UOPMVMatterNameLabel);
			gridUOPMV.setWidget(4, 2, UOPMVMatterName);
			
			//Table
			
			gridUOPMV.setWidget(6, 1, UOPMVDateOfTripLabel);
			gridUOPMV.setWidget(7, 1, UOPMVDateOfTrip);
			
			gridUOPMV.setWidget(6, 2, UOPMVDetailsOfTripLabel);
			gridUOPMV.setWidget(7, 2, UOPMVDetailsOfTrip);
			
			gridUOPMV.setWidget(6, 3, UOPMVDistanceLabel);
			gridUOPMV.setWidget(7, 3, UOPMVDistance);

			


			
			
			
			
			
			
			
			
			
		}
		if (status == "Delete") {
			//Remove Grid
			gridUOPMV.clear();
			RootPanel.get("two").remove(gridUOPMV);
			RootPanel.get("two").clear();
			

			
			}
		return gridUOPMV;
	}
}
