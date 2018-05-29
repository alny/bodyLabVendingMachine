package controller;

import java.sql.SQLException;
import java.util.List;

import database.DBCustomer;
import database.PersistensException;
import infrastructure.CtrCustomerIF;
import model.Customer;
import model.Loan;

public class CtrCustomer implements CtrCustomerIF {

	private DBCustomer dbCustomer;

	public CtrCustomer() {
		dbCustomer = DBCustomer.getInstance();
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

}
