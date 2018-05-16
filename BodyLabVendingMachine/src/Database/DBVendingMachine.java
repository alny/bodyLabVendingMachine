package Database;

import java.net.ConnectException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import Infrastructure.DBVendingMachineIF;
import Model.VendingMachine;

public class DBVendingMachine implements DBVendingMachineIF {

	private static final String findVendingMachine = "SELECT * From VendingMachine where id = ?";
	private static final String insertVendingMachine = "INSERT * INTO VendingMachine (name, model,capacity,serialNo,Products)"
			+ "VALUES (?,?,?,?,?)";

	@Override
	public VendingMachine findVendingMachine(int VendingMachineId) {
		VendingMachine vendingMachineList = null;

		try (PreparedStatement statement = DBConnection.getInstance().getConnection()
				.prepareStatement(findVendingMachine)) {
			statement.setInt(1, VendingMachineId);

			ResultSet rs = statement.executeQuery();
			if (rs.next()) {
				vendingMachineList = buildVendingMachineObjects(rs);
			}
			System.out.println(vendingMachineList);
		}
		return vendingMachineList;
	}

	private VendingMachine buildVendingMachineObjects(ResultSet rs) {
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
		try {
			PreparedStatement statement = DBConnection.getInstance().getConnection().prepareStatement(insertVendingMachine)){
			statement.setString(1, vm.getName());
			statement.setString(2, vm.getModel());
			statement.setInt(3, vm.getCapacity());
			statement.setString(4, vm.getSerialNo());
			
			int vmId = statement.executeInsertWithIdentity;
			}
		return vmId;
		}
		
	}
	