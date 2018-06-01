package controller;

import java.sql.SQLException;
import java.util.List;

import database.DBCustomer;
import database.PersistensException;
import infrastructure.CtrCustomerIF;
import infrastructure.CtrDBConnectionIF;
import infrastructure.CtrLoanIF;
import infrastructure.DBCustomerIF;
import model.Customer;
import model.Loan;

public class CtrCustomer implements CtrCustomerIF {

	private DBCustomerIF dbCustomer;
	private CtrLoanIF ctrLoan;
	private CtrDBConnectionIF ctrDB;

	public CtrCustomer() {
		dbCustomer = DBCustomer.getInstance();
		ctrLoan = new CtrLoan();
		ctrDB = new CtrDBConnection();
	}

	@Override
	public List<Customer> findAllCustomers() throws PersistensException {
		return dbCustomer.findAllCustomers();
	}
	

	@Override
	public Customer findCustomer(int customerId) throws PersistensException {
		return dbCustomer.findCustomer(customerId);
	}

	@Override
	public void addLoanToCustomer(Loan loan, Customer customer) {
		customer.addLoan(loan);
	}
	public void sendLoanDataForLoanCreation(int custID) throws PersistensException, CannotFindException {
		Loan loan = ctrLoan.createLoan(findCustomer(custID));
		addLoanToCustomer(loan,findCustomer(custID));
	}
	
	public Boolean getCurrentConnection() {
		return ctrDB.recheckConnection();
	}

	@Override
	public List<Loan> findAllLoansForCustomer(int id) throws PersistensException {
		return ctrLoan.findLoansForCustomer(findCustomer(id));
	}

}
