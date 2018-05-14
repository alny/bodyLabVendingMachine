package Model;

import java.util.Date;

public class Sale {

	private int id;
	private Date date;
	private Product product;
	
	public Sale(int id, Date date, Product product) {
		this.id = id;
		this.date = date;
		this.product = product;
		}
	
	public Sale(Date date, Product product) {
		this.date = date;
		this.product = product;
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

}
