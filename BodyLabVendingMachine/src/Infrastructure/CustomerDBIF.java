package Infrastructure;

import java.sql.SQLException;
import java.util.List;

import Model.Customer;

public interface CustomerIF {
	
	List<Customer> findAllCustomers() throws SQLException;

	Customer findCustomer(int customerId) throws SQLException;
	
}
