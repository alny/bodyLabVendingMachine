package Model;

public class VendingMachine {

	private int id;
	private String name;
	private String model;
	private int capacity;
	private String serialNo;

	public VendingMachine(int id, String name, String model, int capacity, String serialNo) {
		this.id = id;
		this.name = name;
		this.model = model;
		this.capacity = capacity;
		this.serialNo = serialNo;
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
