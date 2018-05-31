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
	
	// Klasse variable
	private static DBVendingMachine instance;
	// Instans variable
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
	// Public metode der returnerer en automat via to parametre, et id af typen int og en boolean der skal bestemme om produkter skal tilføjes til automaten
	public VendingMachine findVendingMachine(int VendingMachineId, boolean retrieveAssociation) throws PersistensException {
		
		// Et objekt af vendingMachine instantieres til null, så den senere kan bruges i try/catch blokken
		VendingMachine vendingMachine = null;
		// Lokal variabel af typen String er sat til final da den indeholder embbedded sql og derfor kun må tildeles en gang
		final String findVendingMachine = "SELECT * From VendingMachine where id = ?";
		// try/catch blok bruges til at indkapsle den kode der kan kaste en exception 
		try {
			PreparedStatement statement = connection.prepareStatement(findVendingMachine);
			// Her sættes automat id'et som parameter i det statement med sql stringen
			statement.setInt(1, VendingMachineId);
			// Her eksekveres sql statementet og returnerer et ResultSet genereret af forespørgslen
			ResultSet rs = statement.executeQuery();
			// while loop der bygger automat objektet ved at kalde metoden buildVendingMachineObject udfra det returnerede ResultSet
			while (rs.next()) {
				vendingMachine = buildVendingMachineObject(rs, retrieveAssociation);
			}
		} catch (SQLException e) {
			// Her fanges de undtagelser der kan opstå i metoden
			PersistensException pe = new PersistensException(e, "Kunne ikke gennemføre søgning");
			pe.printStackTrace();
			throw pe;
		}
		return vendingMachine;
	}
	
	// privat metode der bygger og returnerer et automat object via et ResultSet og en boolean som parameter
	private VendingMachine buildVendingMachineObject(ResultSet rs, boolean retrieveAssociation) throws PersistensException {
		
		// Et objekt af vendingMachine instantieres til null, så den senere kan bruges i try/catch blokken
		VendingMachine vendingMachine = null;
		try {
			int id = rs.getInt("id");
			String model = rs.getString("model");
			int capacity = rs.getInt("capacity");
			String serialNo = rs.getString("serialNo");
			Boolean isLentOut = rs.getBoolean("isLentOut");
			// automat object instantieres med den nye data
			vendingMachine = new VendingMachine(id, model, capacity, serialNo, isLentOut);
			// alt efter om retrieveAssociation er true/false kaldes findProductsInVM metoden og sætter produkter på automaten
			if (retrieveAssociation) {
				vendingMachine.setProducts(DBProduct.getInstance().findProductsInVM(id));
			}
		} catch (SQLException e) {
			// Her fanges de undtagelser der kan opstå i metoden
			PersistensException pe = new PersistensException(e, "Kunne ikke bygge objekt");
			pe.printStackTrace();
			throw pe;
		}
		return vendingMachine;
	}
	
	// public metode der indsætter en automat i databasen via et automat objekt, og tilslut returnerer det nyoprettede automat id af typen int
	@Override
	public int insertVendingMachine(VendingMachine vm) throws PersistensException {
		// Lokal variabel af typen String er sat til final da den indeholder embbedded sql og derfor kun må tildeles en gang
		final String insertVendingMachine = "INSERT * INTO VendingMachine (model,capacity,serialNo,Products) VALUES (?,?,?,?,?)";
		// Lokal variabel af typen int, instantieres til 0
		int vmId = 0;
		// try/catch blok bruges til at indkapsle den kode der kan kaste en exception 
		try {
			PreparedStatement statement = connection.prepareStatement(insertVendingMachine, Statement.RETURN_GENERATED_KEYS);
			statement.setString(1, vm.getModel());
			statement.setInt(2, vm.getCapacity());
			statement.setString(3, vm.getSerialNo());
			
			// Her eksekveres sql statementet og returnerer et id fra forespørgslen
			vmId = DBConnection.getInstance().executeInsertWithIdentity(statement);

		} catch (SQLException e) {
			// Her fanges de undtagelser der kan opstå i metoden
			PersistensException pe = new PersistensException(e, "kunne ikke indsætte automat");
			throw pe;
		}
		return vmId;
	}
	
	// public metode der returnerer den første ledige automat 
	@Override
	public VendingMachine findFirstAvailable() throws PersistensException {
		// Lokal variabel af typen String er sat til final da den indeholder embbedded sql og derfor kun må tildeles en gang
		final String findAvailbe = "Select top 1* from VendingMachine where isLentOut = 'false'";
		// Et objekt af vendingMachine instantieres til null, så den senere kan bruges i try/catch blokken
		VendingMachine vm = null;
		// try/catch blok bruges til at indkapsle den kode der kan kaste en exception
		try {
			PreparedStatement statement = connection.prepareStatement(findAvailbe);
			// Her eksekveres sql statementet og returnerer et ResultSet genereret af forespørgslen
			ResultSet rs = statement.executeQuery();
			if (rs.next()) {
				vm = buildVendingMachineObject(rs, false);
			}
		} catch (SQLException e) {
			// Her fanges de undtagelser der kan opstå i metoden
			PersistensException pe = new PersistensException(e, "Kunne ikke gennemfører søgning");
			throw pe;
		}
		return vm;
	}
	
	// public metode der opdaterer om en automat er udlejet via et automat objekt som parameter
	public void updateIsLentOut(VendingMachine vm) throws PersistensException {
		// Lokal variabel af typen String er sat til final da den indeholder embbedded sql og derfor kun må tildeles en gang
		final String changeIsLentOut = "update vendingMachine set isLentOut = ? where id = ?";
		// try/catch blok bruges til at indkapsle den kode der kan kaste en exception
		try {
			PreparedStatement statement = connection.prepareStatement(changeIsLentOut);
			statement.setBoolean(1, true);
			statement.setInt(2, vm.getId());
			// Her eksekveres sql statementet
			statement.executeUpdate();
		} catch (SQLException e) {
			// Her fanges de undtagelser der kan opstå i metoden
			PersistensException pe = new PersistensException(e, "Kunne ikke gennemfører update");
			throw pe;
		}
	}
}
