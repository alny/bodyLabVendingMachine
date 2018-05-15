package Controller;

import java.sql.SQLException;
import java.util.List;

import Database.DBCustomer;
import Infrastructure.CtrCustomerIF;
import Model.Customer;

public class CtrCustomer implements CtrCustomerIF {

	private DBCustomer dbCustomer;

	public CtrCustomer() throws SQLException {
		dbCustomer = DBCustomer.getInstance();
	}

	@Override
	public List<Customer> findAllCustomers() throws SQLException {
		return dbCustomer.findAllCustomers();
	}

	@Override
	public Customer findCustomer(int customerId) throws SQLException {
		return dbCustomer.findCustomer(customerId);
	}

}
