package Database;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import Infrastructure.DBLoanIF;
import Model.Loan;


public class DBLoan implements DBLoanIF {
	private static DBLoan instance;
	private static final String findLoanForCustomer = "Select * from Loan where customerId = ?";
	private static final String insertLoan = "insert into Loan (time, customerId, vendingMachineId)" + " values (?,?,?)";
	private PreparedStatement findByCuId, insert;
	private DBLoan() throws SQLException {
		findByCuId = DBConnection.getInstance().getConnection().prepareStatement(findLoanForCustomer);
		
	}
	
	public static DBLoan getInstance() throws SQLException {
		if(instance == null) {
			instance = new DBLoan();
		}
		return instance;
	}
	@Override
	public List<Loan> findLoanForCustomer(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int insertLoan(Loan l) {
		// TODO Auto-generated method stub
		return 0;
	}

}
