package model;

import java.util.Date;

public class Sale {

	private int id;
	private Date date;
	private Product product;
	private VendingMachine vendingmachine;
	private Float price;
	
	public Sale(int id, Date date, Product product, VendingMachine vendingMachine, Float price) {
		this.id = id;
		this.date = date;
		this.product = product;
		this.vendingmachine = vendingMachine;
		this.price = price;
	}
	

	public Sale(Date date, Product product, VendingMachine vendingMachine, Float price) {
		this.date = date;
		this.product = product;
		this.vendingmachine = vendingMachine;
		this.price = price;
	}
	
	public Float getPrice() {
		return price;
	}

	public void setPrice(Float price) {
		this.price = price;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
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

	public VendingMachine getVendingmachine() {
		return vendingmachine;
	}

	public void setVendingmachine(VendingMachine vendingmachine) {
		this.vendingmachine = vendingmachine;
	}
	
	

}
