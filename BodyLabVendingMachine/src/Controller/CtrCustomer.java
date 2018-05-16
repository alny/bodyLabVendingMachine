package Controller;

import java.sql.SQLException;
import java.util.List;

import Database.DBCustomer;
import Database.PersistensException;
import Infrastructure.CtrCustomerIF;
import Model.Customer;

public class CtrCustomer implements CtrCustomerIF {

	private DBCustomer dbCustomer;

	public CtrCustomer() {
		dbCustomer = DBCustomer.getInstance();
	}

	@Override
	public List<Customer> findAllCustomers() throws PersistensException {
		return dbCustomer.findAllCustomers();
	}

	@Override
	public Customer findCustomer(int customerId) throws PersistensException {
		return dbCustomer.findCustomer(customerId);
	}

}
