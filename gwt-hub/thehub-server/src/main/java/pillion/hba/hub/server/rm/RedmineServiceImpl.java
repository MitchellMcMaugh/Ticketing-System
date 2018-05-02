package pillion.hba.hub.server.rm;

import javax.servlet.annotation.WebServlet;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import pillion.hba.hub.shared.RedmineService;
import pillion.hba.hub.shared.Tickets;

@WebServlet("/barnacle/redmineService")
public class RedmineServiceImpl extends RemoteServiceServlet implements RedmineService{

	@Override
	public Tickets getTickets() {
		return null;
	}

}
