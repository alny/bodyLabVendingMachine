package Infrastructure;

import java.sql.SQLException;
import java.util.List;

import Database.PersistensException;
import Model.Customer;
import Model.Loan;

public interface CtrCustomerIF {

	List<Customer> findAllCustomers() throws PersistensException;

	Customer findCustomer(int customerId) throws PersistensException;
	void addLoanToCustomer(Loan loan, Customer customer);

}
