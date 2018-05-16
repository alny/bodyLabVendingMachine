package Controller;

import java.sql.SQLException;
import java.util.List;

import Database.DBLoan;
import Infrastructure.CtrLoanIF;
import Infrastructure.DBLoanIF;
import Model.Customer;
import Model.Loan;
import Model.VendingMachine;

public class CtrLoan implements CtrLoanIF {
	private DBLoanIF dbl;
	private CtrVendingMachineIF cVM;
	
	public CtrLoan() throws SQLException {
		dbl = DBLoan.getInstance();
	}
	
	public List<Loan> findLoansForCustomer(Customer customer) throws SQLException {
		 return dbl.findLoansForCustomer(customer, true);
	}
	
	public int insertLoan(Customer customer, VendingMachine vm) {
		Loan l = new Loan (CtrVendingMachine.findVendingMachineById(vm.getId()));
//		return dbl.insertLoan(l, custid);
		return 0;
	}

	@Override
	public int insertLoan(VendingMachine vm) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<Loan> findLoansForCustomer(Customer customer, boolean retrieveAssociation) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Loan createLoan(VendingMachine vendingmachine, Customer customer) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
