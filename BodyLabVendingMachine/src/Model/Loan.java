package Model;

import java.util.Date;

public class Loan {

	private int id;
	private Date date;
	private VendingMachine vendingmachine;

	public Loan(VendingMachine vendingmachine) {
		this.date = new Date();
		this.vendingmachine = vendingmachine;
	}	
	
	public Loan(int id,VendingMachine vendingmachine) {
		this.id = id;
		this.date = new Date();
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
		return date;
	}

	public void setTimestamp(Date date) {
		this.date = date;
	}

}
