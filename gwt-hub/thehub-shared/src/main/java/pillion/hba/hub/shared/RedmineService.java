package pillion.hba.hub.shared;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("redmineService")
public interface RedmineService extends RemoteService {
	Tickets getTickets();
}