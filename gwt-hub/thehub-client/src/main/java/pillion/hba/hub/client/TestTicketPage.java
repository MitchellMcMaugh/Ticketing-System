package pillion.hba.hub.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Element;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.i18n.client.Constants;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.ColumnSortEvent.ListHandler;
import com.google.gwt.user.cellview.client.SimplePager;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.FlowPanel;

import java.util.Date;
import java.util.List;

import com.google.gwt.cell.client.DateCell;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.cellview.client.HasKeyboardSelectionPolicy.KeyboardSelectionPolicy;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.view.client.ListDataProvider;
import com.google.gwt.view.client.SelectionChangeEvent;
import com.google.gwt.view.client.SingleSelectionModel;

import pillion.hba.hub.shared.RedmineService;
import pillion.hba.hub.shared.RedmineServiceAsync;
import pillion.hba.hub.shared.Ticket;
import pillion.hba.hub.shared.Tickets;

public class TestTicketPage {
	
	private static RedmineServiceAsync redmineService = GWT.create(RedmineService.class);
	
	static DateTimeFormat sdf = DateTimeFormat.getFormat("dd/MM/yyyy");
	
	public static Button newTicketButton = new Button();
	public static ListBox ticketFilterPriorityListBox = new ListBox();
	public static ListBox ticketFilterCategoryListBox = new ListBox();
	public static CellTable<Ticket> cellTable = new CellTable<Ticket>();

	public static void go() {
		
		//New Ticket Button
				RootPanel.get("topbit").add(newTicketButton);
				newTicketButton.setText("New Ticket");
				newTicketButton.setStyleName("buttonz");
				
				//Filter Tickets
				ticketFilterCategoryListBox.addItem("Category Filter");
				ticketFilterCategoryListBox.addItem("Affinity Issue");
				ticketFilterCategoryListBox.addItem("Big Hand");
				ticketFilterCategoryListBox.addItem("Hardware Issue");
				ticketFilterCategoryListBox.addItem("Lost File Issue");
				ticketFilterCategoryListBox.addItem("Microsoft Office 365 Issue");
				ticketFilterCategoryListBox.addItem("Microsoft Windows Issue");
				ticketFilterCategoryListBox.addItem("Printing Issue");
				ticketFilterCategoryListBox.addItem("Remote Desktop Issue");
				ticketFilterCategoryListBox.addItem("Software Issue");
				ticketFilterCategoryListBox.addItem("User Login Issue");
				ticketFilterCategoryListBox.addItem("Other Issue");
				ticketFilterCategoryListBox.setSelectedIndex(0);
				ticketFilterCategoryListBox.setStyleName("listBoxez");
				RootPanel.get("topbit").add(ticketFilterPriorityListBox);
				
				ticketFilterPriorityListBox.addItem("Priority Filter");
				ticketFilterPriorityListBox.addItem("Low");
				ticketFilterPriorityListBox.addItem("Normal");
				ticketFilterPriorityListBox.addItem("High");
				ticketFilterPriorityListBox.addItem("Urgent");
				ticketFilterPriorityListBox.setSelectedIndex(0);
				ticketFilterPriorityListBox.setStyleName("listBoxez");
				RootPanel.get("topbit").add(ticketFilterCategoryListBox);
				
				cellTable.setStyleName("tbl-ticket-list");
				cellTable.setWidth("100%");

	
	redmineService.getTickets(new AsyncCallback<Tickets>() {
		public void onSuccess(Tickets result) { populateTickets(result);}
		public void onFailure(Throwable e) { throw new RuntimeException(e); }
	});
	
	}
	public static void populateTickets(Tickets result) {
		
		
		
		//CellTable<Ticket> cellTable = new CellTable<Ticket>();
	    cellTable.setKeyboardSelectionPolicy(KeyboardSelectionPolicy.DISABLED);

	    addDateColumn(cellTable);
	    addPriorityColumn(cellTable);
	    addStatusColumn(cellTable);
//	    addCategoryColumn(cellTable);
	    addTitleColumn(cellTable);
	    addAssigneeColumn(cellTable);

	    // Add a selection model to handle user selection.
	    final SingleSelectionModel<Ticket> singleSelectionModel = new SingleSelectionModel<Ticket>();
	    cellTable.setSelectionModel(singleSelectionModel);
	    singleSelectionModel.addSelectionChangeHandler(new SelectionChangeEvent.Handler() {
	      public void onSelectionChange(SelectionChangeEvent event) {
	    	  Ticket selectedEmployee = singleSelectionModel.getSelectedObject();
	        if (selectedEmployee != null) {
	          //DO STUFF ON CLICK OF TICKET
	        }
	      }
	    });

	    // Set the total row count. This isn't strictly necessary, but it affects
	    // paging calculations, so its good habit to keep the row count up to date.
	    cellTable.setRowCount(result.size(), true);

	    // Push the data into the widget.
	    cellTable.setRowData(0, result);
		
	    // Add it to the root panel.
	    RootPanel.get("tablebit").add(cellTable);
	  }

	

	
	  private static void addDateColumn(CellTable<Ticket> cellTable) {
	    TextColumn<Ticket> dateColumn = new TextColumn<Ticket>() {

	      @Override
	      public String getValue(Ticket object) {
	        return object.getLogged().toString();
	      }
	    };

	    // Add column to table
	    cellTable.addColumn(dateColumn, "Date");
	  }

	  private static void addPriorityColumn(CellTable<Ticket> cellTable) {
	    TextColumn<Ticket> priorityColumn = new TextColumn<Ticket>() {

	      @Override
	      public String getValue(Ticket object) {
	        return object.getPriority();
	      }
	    };

	    // Add column to table
	    cellTable.addColumn(priorityColumn, "Priority");
	  }

	  private static void addStatusColumn(CellTable<Ticket> cellTable) {
	    TextColumn<Ticket> statusColumn = new TextColumn<Ticket>() {

	      @Override
	      public String getValue(Ticket object) {
	        return object.getStatus();
	      }
	    };
	    // Add column to table
	    cellTable.addColumn(statusColumn, "Status");
	  }
	  
//	    private static void addCategoryColumn(CellTable<Ticket> cellTable) {
//		    TextColumn<Ticket> categoryColumn = new TextColumn<Ticket>() {
//
//		      @Override
//		      public String getValue(Ticket object) {
//		        return object.getCategory();
//		      }
//		    };
//		    // Add column to table
//		    cellTable.addColumn(categoryColumn, "Category");
//	    }
	    
	    private static void addTitleColumn(CellTable<Ticket> cellTable) {
		    TextColumn<Ticket> titleColumn = new TextColumn<Ticket>() {

		      @Override
		      public String getValue(Ticket object) {
		        return object.getTitle();
		      }
		    };
		    // Add column to table
		    cellTable.addColumn(titleColumn, "Title");
	    }
	    
	    private static void addAssigneeColumn(CellTable<Ticket> cellTable) {
		    TextColumn<Ticket> assigneeColumn = new TextColumn<Ticket>() {

		      @Override
		      public String getValue(Ticket object) {
		        return object.getAssignee();
		      }
		    };
		 // Add column to table
		    cellTable.addColumn(assigneeColumn, "Assignee");
	    }
}