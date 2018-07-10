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

import com.google.gwt.user.datepicker.client.DateBox;


import pillion.hba.hub.client.OnlineFormMenu.OnlineFormMenu;
import com.google.gwt.user.cellview.client.SimplePager;
import com.google.gwt.user.client.ui.DockPanel;
import com.google.gwt.event.dom.client.ClickHandler;

public class TravelExpenseForm {
	
	static FlexTable gridTEF = new FlexTable();
	static int rows;
	
	
	public static FlexTable travelExpenseForm(String status, FlexTable gridTEF) {
	
		if (status != "Delete") {
			RootPanel.get("two").setVisible(true);
		
			//		T R A V E L   E X P E N S E   F O R M   L A B E L S
			
			Label TEFEmployeeNameLabel = new Label();
			TEFEmployeeNameLabel.setText("Your Name:");
			
			Label TEFTravelStartDateLabel = new Label();
			TEFTravelStartDateLabel.setText("Travel Start Date");
			
			Label TEFTravelEndDateLabel = new Label();
			TEFTravelEndDateLabel.setText("Travel End Date");
			
			Label TEFPurposeOfTravelLabel = new Label();
			TEFPurposeOfTravelLabel.setText("Purpose of Travel");
			
			Label TEFExpenseClaimedDateLabel = new Label();
			TEFExpenseClaimedDateLabel.setText("Date");
			
			Label TEFExpenseClaimedDescriptionLabel = new Label();
			TEFExpenseClaimedDescriptionLabel.setText("Description");
			
			Label TEFExpenseClaimedAmountLabel = new Label();
			TEFExpenseClaimedAmountLabel.setText("Amount");
			
			Label TEFExpenseTotalLabel = new Label();
			TEFExpenseTotalLabel.setText("Total");			
			
			
			//		T R A V E L   E X P E N S E   F O R M   W I D G E T S
			
			ListBox TEFEmployeeName = new ListBox();
			TEFEmployeeName.addItem("Mitchell McMaugh");
			
			DateBox TEFTravelStartDate = new DateBox();
			TEFTravelStartDate.setValue(new Date());
			TEFTravelStartDate.setFormat(new DateBox.DefaultFormat (DateTimeFormat.getFormat("dd MMMM, yyyy"))); 
			
			DateBox TEFTravelEndDate = new DateBox();
			TEFTravelEndDate.setValue(new Date());
			TEFTravelEndDate.setFormat(new DateBox.DefaultFormat (DateTimeFormat.getFormat("dd MMMM, yyyy"))); 
			
			TextBox TEFPurposeOfTravel = new TextBox();
			
			DateBox TEFExpenseClaimedDate = new DateBox();
			TEFExpenseClaimedDate.setValue(new Date());
			TEFExpenseClaimedDate.setFormat(new DateBox.DefaultFormat (DateTimeFormat.getFormat("dd MMMM, yyyy"))); 
			
			TextBox TEFExpenseClaimedDescription = new TextBox();
			
			TextBox TEFExpenseClaimedAmount = new TextBox();
			//TEFExpenseClaimedAmount.addChangeListener(listener);
			
			Button TEFNewRowButton = new Button();
			TEFNewRowButton.setText("+");
			
			TEFNewRowButton.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				
			
				DateBox TEFExpenseClaimedDate = new DateBox();
				TEFExpenseClaimedDate.setValue(new Date());
				TEFExpenseClaimedDate.setFormat(new DateBox.DefaultFormat (DateTimeFormat.getFormat("dd MMMM, yyyy"))); 
				
				TextBox TEFExpenseClaimedDescription = new TextBox();
				
				TextBox TEFExpenseClaimedAmount = new TextBox();
				
				Button TEFRemoveExpenseButton = new Button();
				TEFRemoveExpenseButton.setText("X");
				TEFRemoveExpenseButton.addClickHandler(new ClickHandler() {
					public void onClick(ClickEvent event) {
					int rowIndex = gridTEF.getCellForEvent(event).getRowIndex();
					gridTEF.removeRow(rowIndex);
			}
			});
			
				rows = gridTEF.getRowCount();
				
				gridTEF.setWidget(rows + 1, 2, TEFExpenseClaimedDate);
			
				gridTEF.setWidget(rows + 1, 3, TEFExpenseClaimedDescription);
			
				gridTEF.setWidget(rows + 1, 4, TEFExpenseClaimedAmount);
			
				gridTEF.setWidget(rows + 1, 5, TEFRemoveExpenseButton);
			
			
			}
			});
			
			Button TEFRemoveExpenseButton = new Button();
			TEFRemoveExpenseButton.setText("X");
			TEFRemoveExpenseButton.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				int rowIndex = gridTEF.getCellForEvent(event).getRowIndex();
				int cellIndex = gridTEF.getCellForEvent(event).getCellIndex();
				gridTEF.removeRow(rowIndex);
				gridTEF.removeCell(rowIndex, cellIndex);
				gridTEF.removeCell(rowIndex, cellIndex-1);
				gridTEF.removeCell(rowIndex, cellIndex-2);
				gridTEF.removeCell(rowIndex, cellIndex-3);
			}
			});
			
			Button TEFSubmitFormButton = new Button();
			TEFSubmitFormButton.setText("Submit");
			TEFSubmitFormButton.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
			Window.confirm("AHHH222");
			}
			});	    
			
			
			
			//		A D D I N G   W I D G E T S    T O   G R I D
			
			
			// (int row, int column)
			
			gridTEF.setWidget(1, 1, TEFEmployeeNameLabel);
			gridTEF.setWidget(1, 2, TEFEmployeeName);
			
			gridTEF.setWidget(2, 1, TEFTravelStartDateLabel);
			gridTEF.setWidget(2, 2, TEFTravelStartDate);
			
			gridTEF.setWidget(3, 1, TEFTravelEndDateLabel);
			gridTEF.setWidget(3, 2, TEFTravelEndDate);
			
			gridTEF.setWidget(4, 1, TEFPurposeOfTravelLabel);
			gridTEF.setWidget(4, 2, TEFPurposeOfTravel);
			
			gridTEF.setWidget(5, 2, TEFExpenseClaimedDateLabel);
			gridTEF.setWidget(6, 2, TEFExpenseClaimedDate);
			
			gridTEF.setWidget(5, 3, TEFExpenseClaimedDescriptionLabel);
			gridTEF.setWidget(6, 3, TEFExpenseClaimedDescription);
			
			gridTEF.setWidget(5, 4, TEFExpenseClaimedAmountLabel);
			gridTEF.setWidget(6, 4, TEFExpenseClaimedAmount);
			
			gridTEF.setWidget(5, 5, TEFNewRowButton);
			
			gridTEF.setWidget(5, 6, TEFExpenseTotalLabel);
			
			gridTEF.setWidget(6, 1, TEFSubmitFormButton);
			
			//grid.setWidget(6, 1, TEFCancelFormButton);
	
		
		//Window.confirm(docker.getWidget(1).getStyleName().toString());
		}
		if (status == "Delete") {
		//Remove Grid
			gridTEF.clear();
			RootPanel.get("two").remove(gridTEF);
			RootPanel.get("two").clear();
			gridTEF.removeAllRows();
			
		
		}
		return gridTEF;
	}
	
}
