package Database;

import java.net.ConnectException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import Database.PersistensException;

import Infrastructure.DBVendingMachineIF;
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
		VendingMachine vendingMachineList = null;
		String findVendingMachine = "SELECT * From VendingMachine where id = ?";

		try (PreparedStatement statement = connection.prepareStatement(findVendingMachine)) {
			statement.setInt(1, VendingMachineId);

			ResultSet rs = statement.executeQuery();
			if (rs.next()) {
				vendingMachineList = buildVendingMachineObject(rs);
			}
			System.out.println(vendingMachineList);
		} 
		catch (SQLException e) {
			PersistensException pe = new PersistensException(e, "Could not find all");
			throw pe;
			
		}
		return vendingMachineList;
	}

	private VendingMachine buildVendingMachineObject(ResultSet rs) throws SQLException {
		int id = rs.getInt("id");
		String name = rs.getString("name");
		String model = rs.getString("model");
		int capacity = rs.getInt("capacity");
		String serialNo = rs.getString("serialNo");

		VendingMachine vendingMachine = new VendingMachine(id, name, model, capacity, serialNo);
		
		return vendingMachine;
	}

	@Override
	public int insertVendingMachine(VendingMachine vm) throws PersistensException {
		String insertVendingMachine = "INSERT * INTO VendingMachine (name, model,capacity,serialNo,Products)"
				+ "VALUES (?,?,?,?,?)";
		int vmId = 0;
		try {
			PreparedStatement statement = connection.prepareStatement(insertVendingMachine);
			statement.setString(1, vm.getName());
			statement.setString(2, vm.getModel());
			statement.setInt(3, vm.getCapacity());
			statement.setString(4, vm.getSerialNo());

			vmId = DBConnection.getInstance().executeInsertWithIdentity(statement);

		} catch (SQLException e) {
			PersistensException pe = new PersistensException(e, "Could not find all");
			throw pe;
		}
		return vmId;
	}
}
