package Database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import Infrastructure.DBLoanIF;
import Model.Customer;
import Model.Loan;
import Model.VendingMachine;

public class DBLoan implements DBLoanIF {
	private static DBLoan instance;
	private Connection connection;

	private DBLoan() {
		connection = DBConnection.getInstance().getConnection();
	}

	public static DBLoan getInstance(){
		if (instance == null) {
			instance = new DBLoan();
		}
		return instance;
	}

	@Override
	public List<Loan> findLoansForCustomer(Customer customer, boolean retrieveAssociation) throws PersistensException {
		String findLoanForCustomer = "Select * from Loan where customerId = ?";
		List<Loan> loan = null;
		try {
			PreparedStatement findByCuId = connection.prepareStatement(findLoanForCustomer);
			findByCuId.setInt(1, customer.getId());
			ResultSet rs = findByCuId.executeQuery();
			loan = new LinkedList<Loan>();
			loan = buildObjects(rs, retrieveAssociation);
		} catch (SQLException e) {
			PersistensException pe = new PersistensException(e,"Kunne ikke gennemfører søgning");
			throw pe;
		}

		return loan;
	}

	// id is a Customers id, need some help to know how to handle the execption that happens then rollback happens
	@Override
	public int insertLoan(Loan loan, Customer customer) throws PersistensException {
		String insertLoan = "insert into Loan (date, endDate, customerId, vendingMachineId)" + " values (?,?,?,?)";
		int i = 0;
		try {
			PreparedStatement insert = connection.prepareStatement(insertLoan,  Statement.RETURN_GENERATED_KEYS);
			DBConnection.getInstance().startTransaction();
			java.sql.Date sqlTime = new java.sql.Date(loan.getDate().getTime());
			insert.setDate(1, sqlTime);
			java.sql.Date sqlDate = new java.sql.Date(loan.getEndDate().getTime());
			insert.setDate(2, sqlDate);
			insert.setInt(3, customer.getId());
			insert.setInt(4, loan.getVendingmachine().getId());
			if (checkIfThere(loan) == true) {
				throw new SQLException();
			}
			i = DBConnection.getInstance().executeInsertWithIdentity(insert);
			DBConnection.getInstance().commitTransaction();
		} catch (SQLException e) {
			try {
				DBConnection.getInstance().rollbackTransaction();
				PersistensException pe = new PersistensException(e,"kunne ikke indsætte lån");
				throw pe;	
			} catch (SQLException e1) {
				PersistensException pe = new PersistensException(e,"database kunne ikke rollback");
				throw pe;
			}
		}
		return i;
	}

	// returns true if there already exist a loan with the machine, that has not
	// finished, used solv the problem with problem with creation of two loans
	private boolean checkIfThere(Loan loan)throws PersistensException {
		boolean found = false;
		String check = "Select * from Loan where vendingMachineId = ? and endDate > ?";
		try {
			PreparedStatement checkIfNotThere = connection.prepareStatement(check);
			Date date = new Date();
			java.sql.Date sqlTime = new java.sql.Date(date.getTime());
			checkIfNotThere.setInt(1, loan.getVendingmachine().getId());
			checkIfNotThere.setDate(2, sqlTime);
			ResultSet rs = checkIfNotThere.executeQuery();
			if(rs.next()) {
				found = true;
			}
		} catch (SQLException e) {
			PersistensException pe = new PersistensException(e,"database error i checkIfThere");
			throw pe;
		}
		return found;
	}

	private List<Loan> buildObjects(ResultSet rs, boolean retrieveAssociation) throws PersistensException{
		List<Loan> loan = new LinkedList<Loan>();
		try {
			while (rs.next()) {
				loan.add(buildObject(rs, retrieveAssociation));
			}
		}
		catch(SQLException e) {
			PersistensException pe = new PersistensException(e,"database error i buildObjects");
			throw pe;
		}
		return loan;
	}

	private Loan buildObject(ResultSet rs, boolean retrieveAssociation) throws PersistensException {
		Loan loan = null;
		try {
			VendingMachine vm = new VendingMachine(rs.getInt("vendingMachineId"));
			if (retrieveAssociation) {
				vm = DBVendingMachine.getInstance().findVendingMachine(rs.getInt("vendingMachineId"),false);
			}
			loan = new Loan(rs.getInt("id"), vm);
			loan.setDate(rs.getDate("date"));
			loan.setEndDate(rs.getDate("endDate"));
		}
		catch(SQLException e) {
			PersistensException pe = new PersistensException(e,"database error i buildObject");
			throw pe;
		}
		return loan;
	}

}
