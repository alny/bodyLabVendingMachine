package Database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Infrastructure.DBCustomerIF;
import Model.CityZip;
import Model.Customer;
import Database.PersistensException;
import Database.DBCustomer;

public class DBCustomer implements DBCustomerIF {
	
	private static DBCustomer instance;
	private Connection connection; 
	
	public static DBCustomer getInstance() {
		if(instance == null) {
			instance = new DBCustomer();
		}
		return instance;
	}
	
	private DBCustomer() {
		connection = DBConnection.getInstance().getConnection();
	}
	
	@Override
	public List<Customer> findAllCustomers() throws PersistensException {
		List<Customer> customerList = new ArrayList<>();
		
		final String findAllCustomers = "SELECT * FROM Customer AS customer, CityZip AS cityzip WHERE customer.cityZipId = cityzip.id";
		
		try (PreparedStatement statement = connection.prepareStatement(findAllCustomers)) {
			ResultSet rs = statement.executeQuery();
			customerList = buildCustomerObjects(rs);
			System.out.println(customerList);
		} catch (SQLException e) {
			PersistensException pe = new PersistensException(e, "Kunne ikke gennemfører søgning");
			throw pe;
		}
		return customerList;
	}
	
	@Override
	public Customer findCustomer(int customerId) throws PersistensException {
		Customer customer = null;
		final String findCustomerById = "SELECT * FROM Customer AS customer, CityZip AS cityzip WHERE customer.cityZipId = cityzip.id AND customer.id = ?";
		try (PreparedStatement statement = connection.prepareStatement(findCustomerById)) {
			statement.setInt(1, customerId);
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				customer = buildCustomerObject(rs);
			}
			System.out.println(customer);
		} catch (SQLException e) {
			PersistensException pe = new PersistensException(e, "Kunne ikke gennemfører søgning");
			throw pe;
		}
		return customer;
	}
	
	private Customer buildCustomerObject(ResultSet rs) throws SQLException {
		int id = rs.getInt("id");
		String name = rs.getString("name");
		String address = rs.getString("address");
		String phone = rs.getString("phone");
		String zipCode = rs.getString("zipCode");
		String city = rs.getString("city");
		Customer customer = new Customer(id, name, address, phone, new CityZip(zipCode, city));
		return customer;
	}

	private List<Customer> buildCustomerObjects(ResultSet rs) throws SQLException {
		List<Customer> res = new ArrayList<>();
		while (rs.next()) {
			res.add(buildCustomerObject(rs));
		}
		return res;
	}

}
