package Database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import Infrastructure.DBSaleIF;
import Model.Product;
import Model.Sale;
import Model.VendingMachine;

public class DBSale implements DBSaleIF {
	private static DBSale instance;
	private static final String getSalesFromMachineId = "select * from Sale where vendingMachineId = ?";
	private static final String getSumMachine = " SUM(price) from Sale where vendingMachineId = ?";
	private static final String getSumProduct = "select sum(price) from Sale where produktId = ?";
	private static final String getSalesProduct = "select * from Sale where produktId = ?";
	private PreparedStatement sumMachine, sumProduct, salesProduct, salesMachine; 
	private DBSale() throws SQLException {
		sumMachine = DBConnection.getInstance().getConnection().prepareStatement(getSumMachine);
		sumProduct = DBConnection.getInstance().getConnection().prepareStatement(getSumProduct);
		salesMachine = DBConnection.getInstance().getConnection().prepareStatement(getSalesFromMachineId);
		salesProduct = DBConnection.getInstance().getConnection().prepareStatement(getSalesProduct);
	}	
	public static DBSale getInstance() throws SQLException {
		if(instance == null) {
			instance = new DBSale();
		}
		return instance;
	}
	
	@Override
	public List<Sale> getSalesFromMachineId(VendingMachine vm) throws SQLException {
		List<Sale> sale = null;
		salesMachine.setInt(0, vm.getId());
		ResultSet rs = salesMachine.executeQuery();
		sale = buildObjects(rs, vm);
		return sale;
	}

	@Override
	public int getTotalSaleFromMachineId(VendingMachine vm) throws SQLException {
		int i = 0;
		sumMachine.setInt(0, vm.getId());
		i = sumMachine.executeUpdate();
		return i;
	}

	@Override
	public int getTotalSaleFromProductId(Product p) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<Sale> getSalesFromProductId(Product p) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int insertSale(Sale s) {
		// TODO Auto-generated method stub
		return 0;
	}

	private List<Sale> buildObjects(ResultSet rs, VendingMachine vm) {
		List<Sale> sale = new LinkedList<Sale>();
		while(rs.next()) {
			sale.add(new Sale())
		}
		return null;
	}
}
