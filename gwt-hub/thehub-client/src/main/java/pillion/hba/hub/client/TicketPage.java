package pillion.hba.hub.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.HasKeyboardSelectionPolicy.KeyboardSelectionPolicy;
import com.google.gwt.user.cellview.client.SimplePager.TextLocation;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.view.client.ListDataProvider;
import com.google.gwt.view.client.SelectionChangeEvent;
import com.google.gwt.view.client.SingleSelectionModel;

import pillion.hba.hub.shared.RedmineService;
import pillion.hba.hub.shared.RedmineServiceAsync;
import pillion.hba.hub.shared.Ticket;
import pillion.hba.hub.shared.Tickets;

public class TicketPage {
	
	private static RedmineServiceAsync redmineService = GWT.create(RedmineService.class);
	
	static DateTimeFormat sdf = DateTimeFormat.getFormat("dd/MM/yyyy");
	
	static Button newTicketButton = new Button();
	static ListBox ticketFilterPriorityListBox = new ListBox();
	static ListBox ticketFilterCategoryListBox = new ListBox();
	static CellTable<Ticket> cellTable = new CellTable<Ticket>();
	static String selectedCategory;
	static String selectedPriority;
	static Tickets initialData = new Tickets();
	static MySimplePager.Resources pagerResources = GWT.create(MySimplePager.Resources.class);
	static MySimplePager pager = new MySimplePager(TextLocation.CENTER, pagerResources, false, 0,true);
	static ListDataProvider<Ticket> dataProvider = new ListDataProvider<Ticket>();
	static Tickets results = new Tickets();
	static String imageURL = new String();
	static String userName = new String();
	
	public void go() {
		
		
		
		redmineService.getData(new AsyncCallback<String>() {
			public void onSuccess(String url) {
				int position = ordinalIndexOf(url, "¯\\_(ツ)_/¯", 1);
				imageURL = url.substring(0, position);
				userName = url.substring(position + 9);	
						;}
			public void onFailure(Throwable e) { throw new RuntimeException(e); }
		});
		
		selectedCategory = "Category Filter";
		selectedPriority = "Priority Filter";
		ticketFilterCategoryListBox.setSelectedIndex(0);
		ticketFilterPriorityListBox.setSelectedIndex(0);
		
		newTicketButton.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				FlowPanel newTicketFlex = new FlowPanel();
				newTicketFlex = pillion.hba.hub.client.Tickets.NewTicket.newTicket();
				
				cellTable.setVisible(false);
				pager.setVisible(false);
				RootPanel.get("newticketticketbit").add(newTicketFlex);
				RootPanel.get("topbit").remove(newTicketButton);
				RootPanel.get("topbit").remove(ticketFilterCategoryListBox);
				RootPanel.get("topbit").remove(ticketFilterPriorityListBox);
			}
		});
		
			addDateColumn(cellTable);
		    addPriorityColumn(cellTable);
		    addStatusColumn(cellTable);
		    addCategoryColumn(cellTable);
		    addTitleColumn(cellTable);
		    addAssigneeColumn(cellTable);
		    
		    cellTable.setColumnWidth(0, "5%");
		    cellTable.setColumnWidth(1, "5%");
		    cellTable.setColumnWidth(2, "5%");
		    cellTable.setColumnWidth(3, "5%");
		    cellTable.setColumnWidth(4, "20%");
		    cellTable.setColumnWidth(5, "5%");

		    cellTable.redrawHeaders();
		    cellTable.setRowData(0, initialData);
		    RootPanel.get("tablebit").add(cellTable);

			redmineService.getTickets(new AsyncCallback<Tickets>() {
				public void onSuccess(Tickets result) { dataProvider.addDataDisplay(cellTable); populateTickets(result);}
				public void onFailure(Throwable e) { throw new RuntimeException(e); }
			});

		    ticketsForm(false);
	}
	
	public static void ticketsForm(Boolean bool) {
	    cellTable.setVisible(true);
	    pager.setVisible(true);
		
		if (bool) {
			redmineService.getTickets(new AsyncCallback<Tickets>() {
				public void onSuccess(Tickets result) { 
					populateTickets(result);
					}
				public void onFailure(Throwable e) { throw new RuntimeException(e); }
			});
	    }
		
		ticketFilterCategoryListBox.clear();
		ticketFilterPriorityListBox.clear();

		pager.setVisible(true);
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
		
	// Add a selection model to handle user selection for celltable.
	    final SingleSelectionModel<Ticket> singleSelectionModel = new SingleSelectionModel<Ticket>();
	    cellTable.setSelectionModel(singleSelectionModel);
	    singleSelectionModel.setSelected(singleSelectionModel.getSelectedObject(), false);
	    singleSelectionModel.addSelectionChangeHandler(new SelectionChangeEvent.Handler() {
	      public void onSelectionChange(SelectionChangeEvent event) {
	    	  Ticket selectedTicket = singleSelectionModel.getSelectedObject();
	        if (selectedTicket != null) {
	        	FlowPanel tDetails = pillion.hba.hub.client.Tickets.TicketDetails.ticketDetails(selectedTicket, imageURL, userName);
				cellTable.setVisible(false);
				pager.setVisible(false);
				RootPanel.get("topbit").remove(newTicketButton);
				RootPanel.get("topbit").remove(ticketFilterCategoryListBox);
				RootPanel.get("topbit").remove(ticketFilterPriorityListBox);
            	RootPanel.get("detailbit").add(tDetails);
	        }
	      }
	    });
		
	}
	
	public static void populateTickets(Tickets Asyncresults) {
		results.clear();
		initialData.clear();
		
		dataProvider.refresh();
		
		results.addAll(Asyncresults);
		initialData.addAll(results);
	    cellTable.setRowData(0, results);
	    cellTable.setRowCount(results.size(), true); 

	    pager.setDisplay(cellTable);
	    dataProvider.setList(results);
 
	    pager.setPageSize(10);
	    pager.setStyleName("pager");
	    
	    RootPanel.get("tablebit").add(pager);
	    
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
	    	pager.firstPage();
	    	
	    	selectedCategory = ticketFilterCategoryListBox.getSelectedItemText();
	    	selectedPriority = ticketFilterPriorityListBox.getSelectedItemText();
			
			//Change Change
			if (selectedCategory != "Category Filter" && selectedPriority != "Priority Filter") {
				results.removeAll(results);
		    	results.addAll(initialData);
				results.removeIf(x -> x.getCategory() != selectedCategory);
				results.removeIf(x -> x.getPriority() != selectedPriority);
				
				
			}
			//Default Change
			if (selectedCategory == "Category Filter" && selectedPriority != "Priority Filter") {
				results.removeAll(results);
		    	results.addAll(initialData);
				results.removeIf(x -> x.getPriority() != selectedPriority);
				
			}
			//Change Default
			if (selectedCategory != "Category Filter" && selectedPriority == "Priority Filter") {
				results.removeAll(results);
		    	results.addAll(initialData);
				results.removeIf(x -> x.getCategory() != selectedCategory);
			}
			
			//Default Default
			if (selectedCategory == "Category Filter" && selectedPriority == "Priority Filter") {
		    	results.removeAll(results);
		    	results.addAll(initialData);
			}
			
			cellTable.setRowData(0, results);
			cellTable.setRowCount(results.size(), true);
			dataProvider.refresh();
	    }
}