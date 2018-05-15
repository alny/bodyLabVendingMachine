package Model;

import java.util.Calendar;
import java.util.Date;

public class Loan {

	private int id;
	private Date date;
	private Date endDate;
	private VendingMachine vendingmachine;

	public Loan(VendingMachine vendingmachine) {
		this.date = new Date();
		this.vendingmachine = vendingmachine;
		 Calendar cal = Calendar.getInstance();
		    cal.setTime(date);
		    cal.add(Calendar.YEAR, 2);
		    endDate= cal.getTime();
		    
	}	
	
	public Loan(int id,VendingMachine vendingmachine) {
		this.id = id;
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

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	
}
