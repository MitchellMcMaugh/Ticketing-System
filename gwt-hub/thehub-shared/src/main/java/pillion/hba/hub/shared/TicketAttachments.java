package pillion.hba.hub.shared;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

public class TicketAttachments extends ArrayList<TicketAttachment> implements Serializable {
	public TicketAttachments() {
		
	}

	public TicketAttachments(Collection<? extends TicketAttachment> c) {
		super(c);
	}
}
