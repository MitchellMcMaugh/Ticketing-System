package pillion.hba.hub.client;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Element;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.cellview.client.HasKeyboardSelectionPolicy.KeyboardSelectionPolicy;
import com.google.gwt.user.cellview.client.SimplePager;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.view.client.ListDataProvider;
import com.google.gwt.view.client.SelectionChangeEvent;
import com.google.gwt.view.client.SingleSelectionModel;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Label;

import pillion.hba.hub.shared.RedmineService;
import pillion.hba.hub.shared.RedmineServiceAsync;
import pillion.hba.hub.shared.Ticket;
import pillion.hba.hub.shared.Tickets;

public class TicketPage {
	
	private static RedmineServiceAsync redmineService = GWT.create(RedmineService.class);
	
	static DateTimeFormat sdf = DateTimeFormat.getFormat("dd/MM/yyyy");
	
	public static Button newTicketButton = new Button();
	public static ListBox ticketFilterPriorityListBox = new ListBox();
	public static ListBox ticketFilterCategoryListBox = new ListBox();
	public static CellTable<Ticket> cellTable = new CellTable<Ticket>();
	public static String selectedCategory;
	public static String selectedPriority;
	public static Tickets initialData = new Tickets();
	
	private Element listTicketsPanel, newTicketPanel, viewTicketPanel;
	
	
	public void go() {
		
		cellTable.setVisibleRange(0, 250);
		
		selectedCategory = "Category Filter";
		selectedPriority = "Priority Filter";
		ticketFilterCategoryListBox.setSelectedIndex(0);
		ticketFilterPriorityListBox.setSelectedIndex(0);
		
		newTicketButton.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				FlowPanel newTicketFlex = new FlowPanel();
				newTicketFlex = pillion.hba.hub.client.Tickets.NewTicket.newTicket();
				cellTable.setVisible(false);
				RootPanel.get("newticketticketbit").add(newTicketFlex);
				RootPanel.get("topbit").remove(newTicketButton);
				RootPanel.get("topbit").remove(ticketFilterCategoryListBox);
				RootPanel.get("topbit").remove(ticketFilterPriorityListBox);
			}
		});
		
		redmineService.getTickets(new AsyncCallback<Tickets>() {
			public void onSuccess(Tickets result) { populateTickets(result);}
			public void onFailure(Throwable e) { throw new RuntimeException(e); }
		});
		
	    addDateColumn(cellTable);
	    addPriorityColumn(cellTable);
	    addStatusColumn(cellTable);
	    addCategoryColumn(cellTable);
	    addTitleColumn(cellTable);
	    addAssigneeColumn(cellTable);
		
		ticketsForm();
		
	}
	
	public static void ticketsForm() {
		
		
		
		ticketFilterCategoryListBox.clear();
		ticketFilterPriorityListBox.clear();

		cellTable.setVisible(true);
		
		cellTable.setStyleName("tbl-ticket-list");
		cellTable.setWidth("100%");

		cellTable.setKeyboardSelectionPolicy(KeyboardSelectionPolicy.DISABLED);
		
		 cellTable.setEmptyTableWidget(new Label(" No Tickets Found"));

	//New Ticket Button
		RootPanel.get("topbit").add(newTicketButton);
		newTicketButton.setText("New Ticket");
		newTicketButton.setStyleName("buttonz");
		
	//Filter Tickets
	//Categories
		String[] ticketFilterCategories = {
				"Category Filter", 
				"Affinity Issue", 
				"Big Hand Issue", 
				"Hardware Issue", 
				"Lost File Issue", 
				"Microsoft Office 365 Issue", 
				"Microsoft Windows Issue",
				"Printing Issue", 
				"Remote Desktop Issue", 
				"Software Issue", 
				"User Login Issue", 
				"Other Issue"
		};
		
	//Assigns category filter listbox values
		for (int i = 0; i < ticketFilterCategories.length; i++) {
			ticketFilterCategoryListBox.addItem(ticketFilterCategories[i]);
		}

	//Gets and sets previously selected category so filter is saved between page changes
		String category = selectedCategory;
		int categoryIndex = -1;
		for (int i = 0; i < ticketFilterCategoryListBox.getItemCount(); i++) {
		    if (ticketFilterCategoryListBox.getItemText(i).equals(category)) {
		    	categoryIndex = i;
		        break;
		    }
		}
		ticketFilterCategoryListBox.setSelectedIndex(categoryIndex);
		
		ticketFilterCategoryListBox.setStyleName("listBoxez");
		RootPanel.get("topbit").add(ticketFilterPriorityListBox);
		
	//Category filter listbox change handler
		ticketFilterCategoryListBox.addChangeHandler(new ChangeHandler() {
			public void onChange(ChangeEvent e) {
				filter();
			}
		});
		
		
		
	//Priorities
		String[] ticketFilterPriorities = {
				"Priority Filter", 
				"Low", 
				"Normal", 
				"High", 
				"Urgent"
		};
		
	//Assigns priority filter listbox values
		for (int i = 0; i < ticketFilterPriorities.length; i++) {
			ticketFilterPriorityListBox.addItem(ticketFilterPriorities[i]);
		}

	//Gets and sets previously selected priority so filter is saved between page changes
		String priority = selectedPriority;
		int priorityIndex = -1;
		for (int i = 0; i < ticketFilterPriorityListBox.getItemCount(); i++) {
		    if (ticketFilterPriorityListBox.getItemText(i).equals(priority)) {
		    	priorityIndex = i;
		        break;
		    }
		}
		ticketFilterPriorityListBox.setSelectedIndex(priorityIndex);
		
		ticketFilterPriorityListBox.setStyleName("listBoxez");
		RootPanel.get("topbit").add(ticketFilterCategoryListBox);
		
		ticketFilterPriorityListBox.addChangeHandler(new ChangeHandler() {
			public void onChange(ChangeEvent e) {
				filter();
			}
		});		
		
	// Add a selection model to handle user selection.
	    final SingleSelectionModel<Ticket> singleSelectionModel = new SingleSelectionModel<Ticket>();
	    cellTable.setSelectionModel(singleSelectionModel);
	    singleSelectionModel.setSelected(singleSelectionModel.getSelectedObject(), false);
	    singleSelectionModel.addSelectionChangeHandler(new SelectionChangeEvent.Handler() {
	      public void onSelectionChange(SelectionChangeEvent event) {
	    	  Ticket selectedEmployee = singleSelectionModel.getSelectedObject();
	        if (selectedEmployee != null) {
	        	FlowPanel tDetails = pillion.hba.hub.client.Tickets.TicketDetails.ticketDetails(selectedEmployee);
				cellTable.setVisible(false);
				RootPanel.get("topbit").remove(newTicketButton);
				RootPanel.get("topbit").remove(ticketFilterCategoryListBox);
				RootPanel.get("topbit").remove(ticketFilterPriorityListBox);
            	RootPanel.get("detailbit").add(tDetails);
	        }
	      }
	    });
		
	}
	
	public static void populateTickets(Tickets results) {
		initialData.addAll(results);
	    cellTable.setRowData(0, results);
	    cellTable.setRowCount(results.size(), true);
	    
	    RootPanel.get("tablebit").add(cellTable);
	    
		}	
	
	public static int ordinalIndexOf(String str, String substr, int n) {
	    int pos = str.indexOf(substr);
	    while (--n > 0 && pos != -1)
	        pos = str.indexOf(substr, pos + 1);
	    return pos;
	}
	
	private static void addDateColumn(CellTable<Ticket> cellTable) {
	    TextColumn<Ticket> dateColumn = new TextColumn<Ticket>() {

	      @Override
	      public String getValue(Ticket object) {
	    	  return sdf.format(object.getLogged());
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
	  
	    private static void addCategoryColumn(CellTable<Ticket> cellTable) {
		    TextColumn<Ticket> categoryColumn = new TextColumn<Ticket>() {

		      @Override
		      public String getValue(Ticket object) {
		        return object.getCategory();
		      }
		    };
		    // Add column to table
		    cellTable.addColumn(categoryColumn, "Category");
	    }
	    
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
	    
	    private static void filter() {
	    	selectedCategory = ticketFilterCategoryListBox.getSelectedItemText();
	    	selectedPriority = ticketFilterPriorityListBox.getSelectedItemText();
			
			Tickets resultz = new Tickets();
			resultz.addAll(initialData);
			
			//Change Change
			if (selectedCategory != "Category Filter" && selectedPriority != "Priority Filter") {
		    	resultz.removeIf(x -> x.getCategory() != selectedCategory);
				resultz.removeIf(x -> x.getPriority() != selectedPriority);
			}
			//Default Change
			if (selectedCategory == "Category Filter" && selectedPriority != "Priority Filter") {
				resultz.removeIf(x -> x.getPriority() != selectedPriority);
			}
			//Change Default
			if (selectedCategory != "Category Filter" && selectedPriority == "Priority Filter") {
		    	resultz.removeIf(x -> x.getCategory() != selectedCategory);
			}

			cellTable.setRowData(0, resultz);
			cellTable.setRowCount(resultz.size(), true);
			
	    }
	
			
	    

}