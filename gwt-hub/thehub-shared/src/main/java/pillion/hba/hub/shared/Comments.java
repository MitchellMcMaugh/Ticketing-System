package pillion.hba.hub.shared;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

public class Comments extends ArrayList<Comment> implements Serializable {
	public Comments() {
	}

	public Comments(Collection<? extends Comment> c) {
		super(c);
	}

}
