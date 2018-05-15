package Database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import Infrastructure.DBLoanIF;
import Model.Loan;


public class DBLoan implements DBLoanIF {
	private static DBLoan instance;
	private static final String findLoanForCustomer = "Select * from Loan where customerId = ?";
	private static final String check = "Select * from Loan where vendingMachineId = ?";
	private static final String insertLoan = "insert into Loan (time, customerId, vendingMachineId)" + " values (?,?,?)";
	private PreparedStatement findByCuId, insert, checkIfNotThere;
	
	private DBLoan() throws SQLException {
		findByCuId = DBConnection.getInstance().getConnection().prepareStatement(findLoanForCustomer);
		insert = DBConnection.getInstance().getConnection().prepareStatement(insertLoan);
		checkIfNotThere = DBConnection.getInstance().getConnection().prepareStatement(check);
	}
	
	public static DBLoan getInstance() throws SQLException {
		if(instance == null) {
			instance = new DBLoan();
		}
		return instance;
	}
	@Override
	public List<Loan> findLoansForCustomer(int id) throws SQLException {
		List<Loan> loan = null;
		findByCuId.setInt(1, id);
		ResultSet rs = findByCuId.executeQuery();
		loan = new LinkedList<Loan>();
		if(rs.next()) {
			loan = buildObjects(rs);
		}
		return loan;
	}
	//id is a Customers id
	@Override
	public int insertLoan(Loan l, int id) throws SQLException {
		int i = 0;
		try {
			DBConnection.getInstance().startTransaction();
			java.sql.Date sqlTime = new java.sql.Date( l.getTimestamp().getTime() );
			insert.setDate(0, sqlTime);
			insert.setInt(1, id);
			insert.setInt(2, l.getVendingmachine().getId());
			if(checkIfThere(l)== true) {
				throw new SQLException();
			}
			i = DBConnection.getInstance().executeInsertWithIdentity(insert);
			DBConnection.getInstance().commitTransaction();
		} 
		catch (SQLException e) {	
            
				DBConnection.getInstance().rollbackTransaction();
			
		}
		return i;
	}
	// returns true if there already exist a loan with the machine, that has not finished, used solv the problem with problem with creation of two loans
	private boolean checkIfThere(Loan l) throws SQLException {
		boolean b = false;
		checkIfNotThere.setInt(0, l.getVendingmachine().getId());
		ResultSet rs = checkIfNotThere.executeQuery();
		Date d = new Date();
		while(rs.next()) {
			if(d.before(rs.getDate("time"))) {
				b = true;
			}
		}
		return b;
	}
	
	
	private List<Loan> buildObjects(ResultSet rs){
		List<Loan> l = new LinkedList<Loan>();
		while(rs.next()) {
			l.add(new Loan(rs.getInt("id"), rs.getTime("time"), rs. ))
		}
		return l;
	}

}
