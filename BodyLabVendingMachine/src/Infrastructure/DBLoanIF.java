package Infrastructure;

import java.sql.SQLException;
import java.util.List;

import Model.Customer;
import Model.Loan;
import Model.VendingMachine;

public interface DBLoanIF {
	List<Loan> findLoansForCustomer(Customer cu, boolean retrieveAssociation) throws SQLException;
	int insertLoan(Loan l, int id) throws SQLException;
	
}
