package Infrastructure;

import java.sql.SQLException;
import java.util.List;

import Model.Loan;

public interface DBLoanIF {
	List<Loan> findLoansForCustomer(int id, boolean retrieveAssociation) throws SQLException;
	int insertLoan(Loan l, int id) throws SQLException;
}
