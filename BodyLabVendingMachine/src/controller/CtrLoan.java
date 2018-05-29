package controller;

import java.sql.SQLException;
import java.util.List;

import database.DBLoan;
import database.DBVendingMachine;
import database.PersistensException;
import infrastructure.CtrCustomerIF;
import infrastructure.CtrLoanIF;
import infrastructure.CtrVendingMachineIF;
import infrastructure.DBLoanIF;
import model.Customer;
import model.Loan;
import model.VendingMachine;

public class CtrLoan implements CtrLoanIF {
	private DBLoanIF dbL;
	private CtrVendingMachineIF cVM;
	private CtrCustomerIF cCtr;
	
	public CtrLoan() {
		dbL = DBLoan.getInstance();
		cVM = new CtrVendingMachine();
		cCtr = new CtrCustomer();
	}
	@Override
	public List<Loan> findLoansForCustomer(Customer customer) throws PersistensException {
		 return dbL.findLoansForCustomer(customer, true);
	}
	

	@Override
	public void insertLoan(Loan loan, Customer customer) throws PersistensException {
		dbL.insertLoan(loan, customer);
		cCtr.addLoanToCustomer(loan, customer);
	}

	@Override
	public Loan createLoan(int customerId) throws PersistensException {
		Loan loan = new Loan(cVM.findFirstAvailbe());
		cVM.changeLentOut(loan.getVendingmachine());
		insertLoan(loan, cCtr.findCustomer(customerId));
		return loan;
		
	}
	
}
