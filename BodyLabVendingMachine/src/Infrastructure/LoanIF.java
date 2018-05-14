package Infrastructure;

import java.util.List;

import Model.Loan;

public interface LoanIF {
	List<Loan> findLoanForCustomer(int id);
	int insertLoan(Loan l);
}
