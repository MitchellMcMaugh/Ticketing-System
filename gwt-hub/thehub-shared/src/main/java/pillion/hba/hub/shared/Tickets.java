package pillion.hba.hub.shared;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

public class Tickets extends ArrayList<Ticket> implements Serializable {

	public Tickets() {
	}

	public Tickets(Collection<? extends Ticket> c) {
		super(c);
	}

	
}
