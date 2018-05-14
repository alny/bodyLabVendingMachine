package Infrastructure;

import java.util.List;

import Model.Loan;

public interface DBLoanIF {
	List<Loan> findLoanForCustomer(int id);
	int insertLoan(Loan l);
}
