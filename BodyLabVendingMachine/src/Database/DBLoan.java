package Database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import Infrastructure.DBLoanIF;
import Model.Loan;
import Model.VendingMachine;


public class DBLoan implements DBLoanIF {
	private static DBLoan instance;
	private Connection connection;
	
	private DBLoan() throws SQLException {
		connection = DBConnection.getInstance().getConnection();
	}
	
	public static DBLoan getInstance() throws SQLException {
		if(instance == null) {
			instance = new DBLoan();
		}
		return instance;
	}
	@Override
	public List<Loan> findLoansForCustomer(int id, boolean retrieveAssociation)  {
		String findLoanForCustomer = "Select * from Loan where customerId = ?";
		List<Loan> loan = null;
		try {
			PreparedStatement findByCuId = DBConnection.getInstance().getConnection().prepareStatement(findLoanForCustomer);
			findByCuId.setInt(1, id);
			ResultSet rs = findByCuId.executeQuery();
			loan = new LinkedList<Loan>();
			loan = buildObjects(rs,retrieveAssociation);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		return loan;
	}
	//id is a Customers id
	@Override
	public int insertLoan(Loan l, int id) throws SQLException {
		String insertLoan = "insert into Loan (date,endDate, customerId, vendingMachineId)" + " values (?,?,?,?)";
		int i = 0;
		try {
			PreparedStatement insert = DBConnection.getInstance().getConnection().prepareStatement(insertLoan);
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
	private boolean checkIfThere(Loan l) {
		boolean b = false;
		String check = "Select * from Loan where vendingMachineId = ?";
		try {
			PreparedStatement checkIfNotThere = DBConnection.getInstance().getConnection().prepareStatement(check);
			checkIfNotThere.setInt(0, l.getVendingmachine().getId());
			ResultSet rs = checkIfNotThere.executeQuery();
			Date d = new Date();
			while(rs.next()) {
				if(d.before(rs.getDate("time"))) {
					b = true;
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return b;
	}
	
	
	private List<Loan> buildObjects(ResultSet rs, boolean retrieveAssociation) throws SQLException{
		List<Loan> l = new LinkedList<Loan>();
		while(rs.next()) {
			l.add(buildObject(rs,retrieveAssociation));
		}
		return l;
	}
	
	private Loan buildObject(ResultSet rs, boolean retrieveAssociation) throws SQLException {
		VendingMachine vm = new VendingMachine(rs.getInt("vendingMachineId"));
		if(retrieveAssociation) {
			vm = DBVendingMachine.getInstance().findVendingMachine(rs.getInt("vendingMachineId"));
		}
		Loan l = new Loan(rs.getInt("id"),vm);
		l.setDate(rs.getDate("date"));
		l.setEndDate(rs.getDate("endDate"));
		return l;	
	}

}
