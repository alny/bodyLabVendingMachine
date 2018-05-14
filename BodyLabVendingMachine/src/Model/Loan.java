package Model;

import java.util.Date;

public class Loan {

	private int id;
	private Date timestamp;
	private VendingMachine vendingmachine;

	public Loan(int id, VendingMachine vendingmachine) {
		this.id = id;
		this.timestamp = new Date();
		this.vendingmachine = vendingmachine;
	}	
	
	public VendingMachine getVendingmachine() {
		return vendingmachine;
	}

	public void setVendingmachine(VendingMachine vendingmachine) {
		this.vendingmachine = vendingmachine;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}

}
