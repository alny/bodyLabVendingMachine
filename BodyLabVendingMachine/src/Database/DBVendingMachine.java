package Database;

import java.net.ConnectException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import Database.PersistensException;

import Infrastructure.DBVendingMachineIF;
import Model.Loan;
import Model.Product;
import Model.VendingMachine;

public class DBVendingMachine implements DBVendingMachineIF {
	private static DBVendingMachine instance;
	private Connection connection;

	private DBVendingMachine() {
		connection = DBConnection.getInstance().getConnection();

	}

	public static DBVendingMachine getInstance() {
		if (instance == null) {
			instance = new DBVendingMachine();
		}
		return instance;
	}

	
	public VendingMachine findVendingMachine(int VendingMachineId) throws PersistensException {
		VendingMachine vendingMachine = null;
		final String findVendingMachine = "SELECT * From VendingMachine where id = ?";
		final String findMachineProduct = "SELECT * FROM MachineProduct, Product as product WHERE productId = product.id AND vendingMachineId = ?";

		try {
			PreparedStatement statement = connection.prepareStatement(findVendingMachine);
			statement.setInt(1, VendingMachineId);

			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				vendingMachine = buildVendingMachineObject(rs);
			}
			System.out.println(vendingMachine);
		} 
		catch (SQLException e) {
			PersistensException pe = new PersistensException(e, "Could not find all");
			pe.printStackTrace();
			throw pe;
			
		}
		
		return vendingMachine;
	}
	
	private VendingMachine buildVendingMachineObject(ResultSet rs) throws SQLException {
		int id = rs.getInt("id");
		String model = rs.getString("model");
		int capacity = rs.getInt("capacity");
		String serialNo = rs.getString("serialNo");

		VendingMachine vendingMachine = new VendingMachine(id, model, capacity, serialNo);
		
		return vendingMachine;
	}

	@Override
	public int insertVendingMachine(VendingMachine vm) throws PersistensException {
		String insertVendingMachine = "INSERT * INTO VendingMachine (model,capacity,serialNo,Products)"
				+ "VALUES (?,?,?,?,?)";
		int vmId = 0;
		try {
			PreparedStatement statement = connection.prepareStatement(insertVendingMachine,  Statement.RETURN_GENERATED_KEYS);
			statement.setString(1, vm.getModel());
			statement.setInt(2, vm.getCapacity());
			statement.setString(3, vm.getSerialNo());

			vmId = DBConnection.getInstance().executeInsertWithIdentity(statement);

		} catch (SQLException e) {
			PersistensException pe = new PersistensException(e, "Could not find all");
			throw pe;
		}
		return vmId;
	}
}
