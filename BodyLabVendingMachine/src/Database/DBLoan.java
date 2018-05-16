package Database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return loan;
	}

	// id is a Customers id, need some help to know how to handle the execption that happens then rollback happens
	@Override
	public int insertLoan(Loan loan, Customer customer) {
		String insertLoan = "insert into Loan (date, endDate, customerId, vendingMachineId)" + " values (?,?,?,?)";
		int i = 0;
		try {
			PreparedStatement insert = connection.prepareStatement(insertLoan);
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
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		return i;
	}

	// returns true if there already exist a loan with the machine, that has not
	// finished, used solv the problem with problem with creation of two loans
	private boolean checkIfThere(Loan loan) {
		boolean found = false;
		String check = "Select * from Loan where vendingMachineId = ?";
		try {
			PreparedStatement checkIfNotThere = connection.prepareStatement(check);
			checkIfNotThere.setInt(1, loan.getVendingmachine().getId());
			ResultSet rs = checkIfNotThere.executeQuery();
			Date date = new Date();
			while (rs.next()) {
				if (date.before(rs.getDate("time"))) {
					found = true;
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return found;
	}

	private List<Loan> buildObjects(ResultSet rs, boolean retrieveAssociation) throws PersistensException, SQLException {
		List<Loan> loan = new LinkedList<Loan>();
		while (rs.next()) {
			loan.add(buildObject(rs, retrieveAssociation));
		}
		return loan;
	}

	private Loan buildObject(ResultSet rs, boolean retrieveAssociation) throws SQLException, PersistensException {
		VendingMachine vm = new VendingMachine(rs.getInt("vendingMachineId"));
		if (retrieveAssociation) {
			vm = DBVendingMachine.getInstance().findVendingMachine(rs.getInt("vendingMachineId"));
		}
		Loan loan = new Loan(rs.getInt("id"), vm);
		loan.setDate(rs.getDate("date"));
		loan.setEndDate(rs.getDate("endDate"));
		return loan;
	}

}
