package infrastructure;

import java.util.List;

import database.PersistensException;
import model.Customer;

public interface DBCustomerIF {

	List<Customer> findAllCustomers() throws PersistensException;

	Customer findCustomer(int customerId) throws PersistensException;

}
