package pillion.hba.hub.client.OnlineFormMenu;



import com.google.gwt.user.client.ui.VerticalPanel;

import com.google.gwt.cell.client.TextCell; 
import com.google.gwt.user.cellview.client.CellList;
import com.google.gwt.user.cellview.client.HasKeyboardSelectionPolicy.KeyboardSelectionPolicy;
import com.google.gwt.view.client.SelectionChangeEvent;

import com.google.gwt.view.client.SingleSelectionModel;

import pillion.hba.hub.client.OnlineForm;

import com.google.gwt.user.cellview.client.SimplePager;
import com.google.gwt.view.client.ListDataProvider;
import java.util.List; 


public class OnlineFormMenu extends VerticalPanel{
	
	static String pageNumber;
	static String currentPage;
	
	public static VerticalPanel OnlineFormMenuCreate() {
		
	SimplePager pager = new SimplePager();
	VerticalPanel vPanel = new VerticalPanel();
	CellList<String> cellList = new CellList<String>(new TextCell());
	ListDataProvider<String> dataProvider = new ListDataProvider<String>();
	
	
	List<String> data = dataProvider.getList();
    
    data.add("Online Forms");
    data.add("Consent for Photo and Video Recording and Release");
    data.add("Courier Approval Form");
    data.add("Deposit to Trust Account");
    data.add("File Closing Form");
    data.add("File Opening Form");
    data.add("Google Chart");
    data.add("HBA Giving Program Application Form");
    data.add("New Client Form");
    data.add("Petty Cash Form");
    data.add("Travel Booking Form");
    data.add("Travel Expenses Form");
    data.add("Trust to Office Transfer Form");
    data.add("Use of Personal Motor Vehicle Claim Form");
    data.add("Withdrawal from Office Account Form");
    data.add("Withdrawal from Trust Account Form");
    
    
    dataProvider.addDataDisplay(cellList);
    
    pager.setDisplay(cellList);
    
    //VerticalPanel vPanel = new VerticalPanel();
    vPanel.add(pager);
    vPanel.add(cellList);
    cellList.setKeyboardSelectionPolicy(KeyboardSelectionPolicy.DISABLED);
    
    final SingleSelectionModel<String> selectionModel = new SingleSelectionModel<String>();
    cellList.setSelectionModel(selectionModel);
    selectionModel.addSelectionChangeHandler(new SelectionChangeEvent.Handler() {
      public void onSelectionChange(SelectionChangeEvent event) {
        String selected = selectionModel.getSelectedObject();
        if (selected != null) {
        	pageNumber = OnlineForm.getPageNumber(pager);
        	OnlineForm.doStuffWithPageNumber(selected);
        }
      }
    });
    
    return vPanel;
	}
}
