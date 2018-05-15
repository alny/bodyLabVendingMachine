package Database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Infrastructure.DBCustomerIF;
import Model.CityZip;
import Model.Customer;
import Database.DBCustomer;

public class DBCustomer implements DBCustomerIF {
	
	public static DBCustomer instance;
	private static final String findCustomerById = "SELECT * FROM Customer AS customer, CityZip AS cityzip WHERE customer.cityZipId = cityzip.id AND customer.id = ?";
	private static final String findAllCustomers = "SELECT * FROM Customer AS customer, CityZip AS cityzip WHERE customer.cityZipId = cityzip.id";
	
	@Override
	public List<Customer> findAllCustomers() throws SQLException {
		List<Customer> customerList = new ArrayList<>();
		
		try (PreparedStatement statement = DBConnection.getInstance().getConnection().prepareStatement(findAllCustomers)) {

			ResultSet rs = statement.executeQuery();
			if (rs.next()) {
				customerList = buildCustomerObjects(rs);
			}
			System.out.println(customerList);

		} catch (SQLException e) {
			System.out.println(e);
		}
		return customerList;
	}
	
	public static DBCustomer getInstance() throws SQLException {
		if(instance == null) {
			instance = new DBCustomer();
		}
		return instance;
	}

	@Override
	public Customer findCustomer(int customerId) throws SQLException {
		Customer customer = null;
		try (PreparedStatement statement = DBConnection.getInstance().getConnection().prepareStatement(findCustomerById)) {
			statement.setInt(1, customerId);

			ResultSet rs = statement.executeQuery();
			if (rs.next()) {
				customer = buildCustomerObject(rs);
			}
			System.out.println(customer);

		} catch (SQLException e) {
			System.out.println(e);
		}
		return customer;
	}

	private Customer buildCustomerObject(ResultSet rs) throws SQLException {

		int id = rs.getInt("id");
		String name = rs.getString("name");
		String address = rs.getString("name");
		String phone = rs.getString("phone");
		String zipCode = rs.getString("zipCode");
		String city = rs.getString("city");
		Customer customer = new Customer(id, name, address, phone, new CityZip(zipCode, city));

		return customer;
	}

	private List<Customer> buildCustomerObjects(ResultSet rs) throws SQLException {
		List<Customer> res = new ArrayList<>();
		if (rs.next()) {
			res.add(buildCustomerObject(rs));
		}
		return res;
	}

}
