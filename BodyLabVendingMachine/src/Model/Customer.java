package model;

import java.util.LinkedList;
import java.util.List;

public class Customer {

	private int id;
	private String name;
	private String address;
	private String phone;
	private CityZip cityZip;
	private List<Loan> loans;

	public Customer(int id, String name, String address, String phone, CityZip cityZip) {
		this.id = id;
		this.name = name;
		this.address = address;
		this.phone = phone;
		this.cityZip = cityZip;
		loans = new LinkedList<Loan>();
	}

	public void addLoan(Loan loan) {
		loans.add(loan);
	}

	public void removeLoan(Loan loan) {
		loans.remove(loan);
	}

	public List<Loan> getLoans() {
		return loans;
	}

	public CityZip getCityZip() {
		return cityZip;
	}

	public void setCityZip(CityZip cityZip) {
		this.cityZip = cityZip;
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

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

}
