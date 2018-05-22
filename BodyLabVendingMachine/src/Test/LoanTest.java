package Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import Database.DBCustomer;
import Database.DBLoan;
import Database.PersistensException;
import Infrastructure.DBCustomerIF;
import Infrastructure.DBLoanIF;
import Model.Customer;
import Model.Loan;

public class LoanTest {
	DBLoanIF dbLoan;
	DBCustomerIF dbCustomer;
	@Before
	public void setUp() {
		dbLoan = DBLoan.getInstance();
		dbCustomer = DBCustomer.getInstance();
	}
	
	@Test
	public void findLoansForCustomerAssertRightSize() {
		int id = 1;
		Customer customer = null;
		List<Loan> loans = null;
		try {
			customer = dbCustomer.findCustomer(id);
			loans = dbLoan.findLoansForCustomer(customer, true);
		} catch (PersistensException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertEquals(loans.size(),1);
	}
	
	@Test
	public void findLoansForCustomerAssertWrongSize() {
		int id = 2;
		Customer customer = null;
		List<Loan> loans = null;
		try {
			customer = dbCustomer.findCustomer(id);
			loans = dbLoan.findLoansForCustomer(customer, true);
		} catch (PersistensException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertTrue("same size", loans.size() == 1);
	}
}
