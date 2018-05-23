package Controller;

import java.sql.SQLException;
import java.util.List;

import Database.DBLoan;
import Database.DBVendingMachine;
import Database.PersistensException;
import Infrastructure.CtrCustomerIF;
import Infrastructure.CtrLoanIF;
import Infrastructure.CtrVendingMachineIF;
import Infrastructure.DBLoanIF;
import Model.Customer;
import Model.Loan;
import Model.VendingMachine;

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
	public void insertLoan(Loan loan, Customer customer) {
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
