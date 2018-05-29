package infrastructure;

import java.sql.SQLException;
import java.util.List;

import database.PersistensException;
import model.Customer;
import model.Loan;
import model.VendingMachine;

public interface DBLoanIF {
	List<Loan> findLoansForCustomer(Customer cu, boolean retrieveAssociation) throws PersistensException;
	int insertLoan(Loan l, Customer customer) throws PersistensException;
	
}
