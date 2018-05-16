package Infrastructure;

import java.util.List;

import Model.Customer;
import Model.Loan;
import Model.VendingMachine;

public interface CtrLoanIF {
	int insertLoan(VendingMachine vm);
	List<Loan> findLoansForCustomer(Customer customer, boolean retrieveAssociation);
	Loan createLoan(VendingMachine vendingmachine, Customer customer);
}
