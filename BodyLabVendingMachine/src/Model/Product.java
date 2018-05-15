package Model;

public class Product {

	private int id;
	private String productNo;
	private String name;
	private String description;
	private double stockValue;



	public Product(int id, String productNo, String name, String description, double stockValue) {
		this.id = id;
		this.productNo = productNo;
		this.name = name;
		this.description = description;
		this.stockValue = stockValue;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}


	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getProductNo() {
		return productNo;
	}

	public void setProductNo(String productNo) {
		this.productNo = productNo;
	}

	public double getStockValue() {
		return stockValue;
	}

	public void setStockValue(double stockValue) {
		this.stockValue = stockValue;
	}

}
