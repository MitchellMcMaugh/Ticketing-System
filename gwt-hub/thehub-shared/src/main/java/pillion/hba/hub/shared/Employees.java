package pillion.hba.hub.shared;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

public class Employees extends ArrayList<Employee> implements Serializable{
	public Employees() {
	}

	public Employees(Collection<? extends Employee> c) {
		super(c);
	}
}
