package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import database.DBCustomer;
import database.DBLoan;
import database.PersistensException;
import infrastructure.DBCustomerIF;
import infrastructure.DBLoanIF;
import model.Customer;
import model.Loan;

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
		assertEquals(loans.size(),2);
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
		assertTrue("same size", loans.size() == 2);
	}
}
