package Controller;

import java.sql.SQLException;
import java.util.List;

import Database.DBLoan;
import Database.DBVendingMachine;
import Database.PersistensException;
import Infrastructure.CtrLoanIF;
import Infrastructure.CtrVendingMachineIF;
import Infrastructure.DBLoanIF;
import Model.Customer;
import Model.Loan;
import Model.VendingMachine;

public class CtrLoan implements CtrLoanIF {
	private DBLoanIF dbL;
	private CtrVendingMachineIF cVM;
	
	public CtrLoan() throws SQLException {
		dbL = DBLoan.getInstance();
		cVM = new CtrVendingMachine();
	}
	@Override
	public List<Loan> findLoansForCustomer(Customer customer) throws PersistensException {
		 return dbL.findLoansForCustomer(customer, true);
	}
	

	@Override
	public int insertLoan(Loan loan, Customer customer) {
		return dbL.insertLoan(loan, customer);
	}

	@Override
	public Loan createLoan(VendingMachine vm, Customer customer) {
		Loan loan = new Loan(vm);
		insertLoan(loan, customer);
		return loan;
		
	}
	
}
