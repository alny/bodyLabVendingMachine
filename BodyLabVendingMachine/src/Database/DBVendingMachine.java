package Database;

import java.net.ConnectException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Infrastructure.DBVendingMachineIF;
import Model.VendingMachine;

public class DBVendingMachine implements DBVendingMachineIF {
	private static DBVendingMachine instance;
	private static final String findVendingMachine = "SELECT * From VendingMachine where id = ?";
	private static final String insertVendingMachine = "INSERT * INTO VendingMachine (name, model,capacity,serialNo,Products)"
			+ "VALUES (?,?,?,?,?)";

	private DBVendingMachine() {
		
	}
	public static DBVendingMachine getInstance() {
		if(instance == null) {
			instance = new DBVendingMachine();
		}
		return instance;
	}
	@Override
	public VendingMachine findVendingMachine(int VendingMachineId) {
		VendingMachine vendingMachineList = null;

		try (PreparedStatement statement = DBConnection.getInstance().getConnection()
				.prepareStatement(findVendingMachine)) {
			statement.setInt(1, VendingMachineId);

			ResultSet rs = statement.executeQuery();
			if (rs.next()) {
				vendingMachineList = buildVendingMachineObject(rs);
			}
			System.out.println(vendingMachineList);
		}
		catch(SQLException e) {
			e.printStackTrace();
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
	public int insertVendingMachine(VendingMachine vm) {
		int vmId = 0;
		try {
			PreparedStatement statement = DBConnection.getInstance().getConnection().prepareStatement(insertVendingMachine);
			statement.setString(1, vm.getName());
			statement.setString(2, vm.getModel());
			statement.setInt(3, vm.getCapacity());
			statement.setString(4, vm.getSerialNo());
			
			vmId = DBConnection.getInstance().executeInsertWithIdentity(statement);
			
		}
		catch(SQLException e) {
			e.getStackTrace();
		}
		return vmId;
	}
}
	