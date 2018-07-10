package pillion.hba.hub.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.RootPanel;

import pillion.hba.hub.shared.RedmineService;
import pillion.hba.hub.shared.RedmineServiceAsync;

public class Barnacle implements EntryPoint {

	private static final String TICKET_PAGE_CLASSNAME = "page-template-ticket-template";
	private static final String ONLINE_FORM_PAGE_CLASSNAME = "page-template-online-forms-template";
	
	@Override
	public void onModuleLoad() {
		RootPanel rootPanel = RootPanel.get();
		for(String cname : rootPanel.getStyleName().split(" ")) {
			
			if(cname.trim().equals(TICKET_PAGE_CLASSNAME)) {
				new TicketPage().go();
			}
			if(cname.trim().equals(ONLINE_FORM_PAGE_CLASSNAME)) {
				new OnlineForm().go();
			}
		}
		
		
//		GWT.log(rootPanel.getStyleName());
		
	}

}
