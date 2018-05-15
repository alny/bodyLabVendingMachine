package Database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import Infrastructure.DBLoanIF;
import Model.Loan;
import model.Customer;


public class DBLoan implements DBLoanIF {
	private static DBLoan instance;
	private static final String findLoanForCustomer = "Select * from Loan where customerId = ?";
	private static final String insertLoan = "insert into Loan (time, customerId, vendingMachineId)" + " values (?,?,?)";
	private PreparedStatement findByCuId, insert;
	private DBLoan() throws SQLException {
		findByCuId = DBConnection.getInstance().getConnection().prepareStatement(findLoanForCustomer);
		insert = DBConnection.getInstance().getConnection().prepareStatement(insertLoan);
	}
	
	public static DBLoan getInstance() throws SQLException {
		if(instance == null) {
			instance = new DBLoan();
		}
		return instance;
	}
	@Override
	public List<Loan> findLoansForCustomer(int id) {
		try {
			findByCuId.setInt(1, id);
			ResultSet rs = findByCuId.executeQuery();
			System.out.println(rs);
			List<Loan> loan = new LinkedList<Loan>();
			if(rs.next()) {
				p = buildObject(rs);
			}
			return p;
		} catch (SQLException e) {
			System.out.println(e);
			throw new SQLException(e);
		}
	}

	@Override
	public int insertLoan(Loan l) {
		// TODO Auto-generated method stub
		return 0;
	}

}
