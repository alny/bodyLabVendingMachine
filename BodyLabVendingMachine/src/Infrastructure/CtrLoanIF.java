package Infrastructure;

import java.util.List;

import Database.PersistensException;
import Model.Customer;
import Model.Loan;
import Model.VendingMachine;

public interface CtrLoanIF {
	void insertLoan(Loan loan, Customer customer);
	List<Loan> findLoansForCustomer(Customer customer) throws PersistensException;
	Loan createLoan(VendingMachine vendingmachine, Customer customer);
}
