package Controller;

import java.sql.SQLException;
import java.util.List;

import Database.DBLoan;
import Model.Loan;

public class CtrLoan {
	private DBLoan dbl;
	
	public CtrLoan() throws SQLException {
		dbl = DBLoan.getInstance();
	}
	
	public List<Loan> findLoansForCustomer(int id) {
		 return dbl.findLoansForCustomer(id);
	}
	
	public int insertLoan(int custid, int vmid) {
		Loan l = new Loan (LoanCtrVendingMachine.findVendingMachineById(vmid));
		return dbl.insertLoan(l, custid);	
	}
	
}
