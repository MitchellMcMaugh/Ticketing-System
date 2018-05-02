package pillion.hba.hub.shared;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface RedmineServiceAsync {
	void getTickets(AsyncCallback<Tickets> tickets);

}
