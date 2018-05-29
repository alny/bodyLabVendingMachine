package model;

import java.util.LinkedList;
import java.util.List;

public class VendingMachine {

	private int id;
	private String model;
	private int capacity;
	private String serialNo;
	private List<Product> products;
	private Boolean isLentOut = false;

	
	public VendingMachine(int id) {
		this.id = id;
	}
	public VendingMachine(int id,  String model, int capacity, String serialNo, Boolean lentOut) {
		this.id = id;
		this.model = model;
		this.capacity = capacity;
		this.serialNo = serialNo;
		isLentOut = lentOut;
		products = new LinkedList<Product>();
	}
	
	public VendingMachine(String model, int capacity, String serialNo) {
		this.model = model;
		this.capacity = capacity;
		this.serialNo = serialNo;
		products = new LinkedList<Product>();
		isLentOut = false;
	}
	public void setProducts(List<Product> products) {
		this.products = products; 
	}
	public Boolean getIsLentOut() {
		return isLentOut;
	}
	public void setIsLentOut(Boolean isLentOut) {
		this.isLentOut = isLentOut;
	}
	public void addProduct(Product product) {
		products.add(product);
	}
	
	public void removeproduct(Product product) {
		products.remove(product);
	}
	
	public List<Product> getProducts() {
		return products;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public int getCapacity() {
		return capacity;
	}

	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}

	public String getSerialNo() {
		return serialNo;
	}

	public void setSerialNo(String serialNo) {
		this.serialNo = serialNo;
	}

}
