package Infrastructure;

import java.sql.SQLException;
import java.util.List;

import Database.PersistensException;
import Model.Customer;

public interface CtrCustomerIF {

	List<Customer> findAllCustomers() throws PersistensException;

	Customer findCustomer(int customerId) throws PersistensException;

}
