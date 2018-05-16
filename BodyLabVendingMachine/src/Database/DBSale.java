package Database;

import java.sql.Connection;
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
	private Connection connection;
	private DBSale() throws SQLException {
		connection = DBConnection.getInstance().getConnection();
	}	
	public static DBSale getInstance() throws SQLException {
		if(instance == null) {
			instance = new DBSale();
		}
		return instance;
	}
	
	@Override
	public List<Sale> getSalesFromMachineId(VendingMachine vm,  boolean retrieveAssociation) {
		String getSalesFromMachineId = "select * from Sale where vendingMachineId = ?";
		List<Sale> sale = null;
		try {
			PreparedStatement salesMachine = connection.prepareStatement(getSalesFromMachineId);
			salesMachine.setInt(0, vm.getId());
			ResultSet rs = salesMachine.executeQuery();
			sale = buildObjects(rs, retrieveAssociation);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return sale;
	}

	@Override
	public int getSumOfSaleFromMachineId(VendingMachine vm) {
		int sum = 0;
		String getSumMachine = " SUM(price) from Sale where vendingMachineId = ?";
		try {
			PreparedStatement sumMachine = connection.prepareStatement(getSumMachine);
			sumMachine.setInt(0, vm.getId());
			sum = sumMachine.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return sum;
	}

	@Override
	public int getSumOfSaleFromProductId(Product p) {
		int sum = 0;
		String getSumProduct = "select sum(price) from Sale where produktId = ?";
		try {
			PreparedStatement sumProduct = connection.prepareStatement(getSumProduct);
			sumProduct.setInt(0, p.getId());
			sum = sumProduct.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return sum;
	}

	@Override
	public List<Sale> getSalesFromProductId(Product p,  boolean retrieveAssociation) {
		String getSalesProduct = "select * from Sale where productId = ?";
		List<Sale> sale = new LinkedList<Sale>(); 
		try {
			PreparedStatement salesProduct = connection.prepareStatement(getSalesProduct);
			ResultSet rs = salesProduct.executeQuery();
			sale = buildObjects(rs, retrieveAssociation);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return sale;
	}
	
	@Override
	public int insertSale(Sale sale) {
		int id = 0;
		String insert = "insert into Sale (date, vendingMachineId, productId)" + " values (?,?,?)";
		String changeQuantity = "update "
		try {
			DBConnection.getInstance().startTransaction();
			PreparedStatement insertPS = connection.prepareStatement(insert);
			java.sql.Date sqlTime = new java.sql.Date( sale.getDate().getTime() );
			insertPS.setDate(0, sqlTime);
			insertPS.setInt(1, sale.getVendingmachine().getId());
			insertPS.setInt(2, sale.getProduct().getId());
			id = DBConnection.getInstance().executeInsertWithIdentity(insertPS);
		}
		catch()
		return id;
	}

	private List<Sale> buildObjects(ResultSet rs, boolean retrieveAssociation) {
		List<Sale> sale = new LinkedList<Sale>();
		try {
			while(rs.next()) {
				sale.add(buildObject(rs, retrieveAssociation));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return sale;
	}
	private Sale buildObject(ResultSet rs, boolean retrieveAssociation) throws SQLException {
		Product product = new Product(rs.getInt("productId"));
		VendingMachine vm = new VendingMachine(rs.getInt("vendingMachineId"));
		if(retrieveAssociation) {
			product = DBProduct.getinstance().findProductById(product.getId());
			vm = DBVendingMachine.getInstance().findVendingMachine(vm.getId());
		}
		Sale sale = new Sale(rs.getInt("id"), rs.getDate("time"), product, vm);
		return sale;
	}
	@Override
	public int getTotalSaleFromMachineId(VendingMachine vm) {
		int sum = 0;
		String getTotalSalesMachine = "select count(id) from Sale where vendingMachineId";
		try {
			PreparedStatement totalMachine = connection.prepareStatement(getTotalSalesMachine);
			totalMachine.setInt(0, vm.getId());
			sum = totalMachine.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		return sum;
	}
	@Override
	public int getTotalSaleFromProductId(Product p){
		int sum = 0;
		String getTotalSalesProduct = "select count(id) from Sale where productId = ?";
		try {
			PreparedStatement totalProduct = connection.prepareStatement(getTotalSalesProduct);
			totalProduct.setInt(0, p.getId());
			sum = totalProduct.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return sum;
	}
}
