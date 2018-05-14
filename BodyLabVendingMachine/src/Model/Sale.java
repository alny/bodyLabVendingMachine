package Model;

import java.util.Date;

public class Sale {

	private int id;
	private Date timestamp;
	private Product product;
	private VendingMachine vendingmachine;
	
	public Sale(int id, Date timestamp, Product product, VendingMachine vendingMachine) {
		this.id = id;
		this.timestamp = timestamp;
		this.product = product;
		this.vendingmachine = vendingMachine;
		}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
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
