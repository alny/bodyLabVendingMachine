package database;

import java.net.ConnectException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import database.PersistensException;
import infrastructure.DBVendingMachineIF;
import model.Loan;
import model.Product;
import model.VendingMachine;

public class DBVendingMachine implements DBVendingMachineIF {
	private static DBVendingMachine instance;
	private Connection connection;

	// Konstruktøren er privat for at sikre at kun en instans af klassen oprettes
	private DBVendingMachine() {
		connection = DBConnection.getInstance().getConnection();
	}
	
	// Metoden returnerer eller opretter en instans af klassen alt efter om den eksisterer
	public static DBVendingMachine getInstance() {
		if (instance == null) {
			instance = new DBVendingMachine();
		}
		return instance;
	}
	// Metoden returnerer en automat via et automat id og tager i mod to parametre, en int og en boolean
	public VendingMachine findVendingMachine(int VendingMachineId, boolean retrieveAssociation) throws PersistensException {
		
		// Et objekt af vendingMachine instantieres til null, så den senere kan bruges i try/catch blokken
		VendingMachine vendingMachine = null;
		// Lokal variabel af typen String er sat til final da den indeholder embbedded sql og derfor kun må tildeles en gang
		final String findVendingMachine = "SELECT * From VendingMachine where id = ?";
		// try/catch indeholdende preparedStatement til at sætte parametret i den embedded sql string 
		try {
			PreparedStatement statement = connection.prepareStatement(findVendingMachine);
			// Her sættes automat id'et som parameter
			statement.setInt(1, VendingMachineId);
			// Her eksekveres sql statementet og returnerer et ResultSet genereret af forespørgslen
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				vendingMachine = buildVendingMachineObject(rs, retrieveAssociation);
			}
		} catch (SQLException e) {
			PersistensException pe = new PersistensException(e, "Kunne ikke gennemføre søgning");
			pe.printStackTrace();
			throw pe;

		}
		return vendingMachine;
	}

	private VendingMachine buildVendingMachineObject(ResultSet rs, boolean retrieveAssociation)
			throws PersistensException {
		VendingMachine vendingMachine = null;
		try {
			int id = rs.getInt("id");
			String model = rs.getString("model");
			int capacity = rs.getInt("capacity");
			String serialNo = rs.getString("serialNo");
			Boolean isLentOut = rs.getBoolean("isLentOut");
			vendingMachine = new VendingMachine(id, model, capacity, serialNo, isLentOut);
			if (retrieveAssociation) {
				vendingMachine.setProducts(DBProduct.getInstance().findProductsInVM(id));
			}
		} catch (SQLException e) {
			PersistensException pe = new PersistensException(e, "Kunne ikke bygge objekt");
			pe.printStackTrace();
			throw pe;
		}
		return vendingMachine;
	}

	@Override
	public int insertVendingMachine(VendingMachine vm) throws PersistensException {
		String insertVendingMachine = "INSERT * INTO VendingMachine (model,capacity,serialNo,Products)"
				+ "VALUES (?,?,?,?,?)";
		int vmId = 0;
		try {
			PreparedStatement statement = connection.prepareStatement(insertVendingMachine,
					Statement.RETURN_GENERATED_KEYS);
			statement.setString(1, vm.getModel());
			statement.setInt(2, vm.getCapacity());
			statement.setString(3, vm.getSerialNo());

			vmId = DBConnection.getInstance().executeInsertWithIdentity(statement);

		} catch (SQLException e) {
			PersistensException pe = new PersistensException(e, "kunne ikke indsætte automat");
			throw pe;
		}
		return vmId;
	}

	@Override
	public VendingMachine findFirstAvailable() throws PersistensException {
		final String findAvailbe = "Select top 1* from VendingMachine where isLentOut = 'false'";
		VendingMachine vm = null;
		try {
			PreparedStatement statement = connection.prepareStatement(findAvailbe);
			ResultSet rs = statement.executeQuery();
			if (rs.next()) {
				vm = buildVendingMachineObject(rs, false);
			}
		} catch (SQLException e) {
				PersistensException pe = new PersistensException(e, "Kunne ikke gennemfører søgning");
				throw pe;
		}
		return vm;
	}

	public void updateIsLentOut(VendingMachine vm) throws PersistensException {
		final String changeIsLentOut = "update vendingMachine set isLentOut = ? where id = ?";
		try {
			PreparedStatement statement = connection.prepareStatement(changeIsLentOut);
			statement.setBoolean(1, true);
			statement.setInt(2, vm.getId());
			statement.executeUpdate();
		} catch (SQLException e) {
			PersistensException pe = new PersistensException(e, "Kunne ikke gennemfører update");
			throw pe;
		}
	}
}
