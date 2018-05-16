package Infrastructure;

import java.util.List;

import Database.PersistensException;
import Model.Customer;

public interface DBCustomerIF {

	List<Customer> findAllCustomers() throws PersistensException;

	Customer findCustomer(int customerId) throws PersistensException;

}
