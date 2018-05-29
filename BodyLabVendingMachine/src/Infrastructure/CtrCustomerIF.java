package infrastructure;

import java.sql.SQLException;
import java.util.List;

import database.PersistensException;
import model.Customer;
import model.Loan;

public interface CtrCustomerIF {

	List<Customer> findAllCustomers() throws PersistensException;

	Customer findCustomer(int customerId) throws PersistensException;
	void addLoanToCustomer(Loan loan, Customer customer);

}
