package Database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

import Infrastructure.DBBusinessIntelligenceIF;
import Model.Product;
import Model.Sale;
import Model.VendingMachine;

public class DBBusinessIntelligence implements DBBusinessIntelligenceIF {
	private static DBBusinessIntelligenceIF instance;
	Connection connection;
	
	private DBBusinessIntelligence() {
		connection = DBConnection.getInstance().getConnection();
	}
	
	public static DBBusinessIntelligenceIF getInstance() {
		if(instance == null) {
			instance = new DBBusinessIntelligence();
		}
		return instance;
	}
	
	/* (non-Javadoc)
	 * @see Database.DBBusinessIntelligenceIF#getSalesFromMachineId(Model.VendingMachine, boolean)
	 */
	@Override
	public List<Sale> getSalesFromMachineId(VendingMachine vm, boolean retrieveAssociation) throws PersistensException {
		String getSalesFromMachineId = "select * from Sale where vendingMachineId = ?";
		List<Sale> sales = null;
		try {
			PreparedStatement salesMachine = connection.prepareStatement(getSalesFromMachineId);
			salesMachine.setInt(1, vm.getId());
			ResultSet rs = salesMachine.executeQuery();
			sales = buildObjects(rs, retrieveAssociation);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return sales;
	}

	/* (non-Javadoc)
	 * @see Database.DBBusinessIntelligenceIF#getSumOfSaleFromMachineId(Model.VendingMachine)
	 */
	@Override
	public float getSumOfSaleFromMachineId(VendingMachine vm) {
		float sum = 0;
		String getSumMachine = "select sum(salesPrice) from Sale where vendingMachineId = ?";
		try {
			PreparedStatement sumMachine = connection.prepareStatement(getSumMachine);
			sumMachine.setInt(1, vm.getId());
			ResultSet rs = sumMachine.executeQuery();
			if(rs.next()) {
				sum = rs.getInt(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return sum;
	}
	
	/* (non-Javadoc)
	 * @see Database.DBBusinessIntelligenceIF#getSumOfSaleFromProductId(Model.Product)
	 */
	@Override
	public float getSumOfSaleFromProductId(Product product) {
		float sum = 0;
		String getSumProduct = "select sum(salesPrice) from Sale where productId = ?";
		try {
			PreparedStatement sumProduct = connection.prepareStatement(getSumProduct);
			sumProduct.setInt(1, product.getId());
			ResultSet rs = sumProduct.executeQuery();
			if(rs.next()) {
				sum = rs.getFloat(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return sum;
	}
	
	/* (non-Javadoc)
	 * @see Database.DBBusinessIntelligenceIF#getSalesFromProductId(Model.Product, boolean)
	 */
	@Override
	public List<Sale> getSalesFromProductId(Product product, boolean retrieveAssociation) throws PersistensException {
		String getSalesProduct = "select * from Sale where productId = ?";
		List<Sale> sale = new LinkedList<Sale>();
		try {
			PreparedStatement salesProduct = connection.prepareStatement(getSalesProduct);
			salesProduct.setInt(1, product.getId());
			ResultSet rs = salesProduct.executeQuery();
			sale = buildObjects(rs, retrieveAssociation);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return sale;
	}
	
	
		
		/* (non-Javadoc)
		 * @see Database.DBBusinessIntelligenceIF#getTotalSaleFromMachineId(Model.VendingMachine)
		 */
		@Override
		public int getTotalSaleFromMachineId(VendingMachine vm) {
			int sum = 0;
			String getTotalSalesMachine = "select count(id) from Sale where vendingMachineId";
			try {
				PreparedStatement totalMachine = connection.prepareStatement(getTotalSalesMachine);
				totalMachine.setInt(1, vm.getId());
				ResultSet rs = totalMachine.executeQuery();
				if(rs.next()) {
					sum =	rs.getInt(1);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			return sum;
		}
		
		/* (non-Javadoc)
		 * @see Database.DBBusinessIntelligenceIF#getTotalSaleFromProductId(Model.Product)
		 */
		@Override
		public int getTotalSaleFromProductId(Product product) {
			int sum = 0;
			String getTotalSalesProduct = "select count(id) from Sale where productId = ?";
			try {
				PreparedStatement totalProduct = connection.prepareStatement(getTotalSalesProduct);
				totalProduct.setInt(1, product.getId());
				ResultSet rs = totalProduct.executeQuery();
				if(rs.next()) {
					sum = rs.getInt(1);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			return sum;
		}
		
		private List<Sale> buildObjects(ResultSet rs, boolean retrieveAssociation) throws PersistensException {
			List<Sale> sale = new LinkedList<Sale>();
			try {
				while (rs.next()) {
					sale.add(buildObject(rs, retrieveAssociation));
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return sale;
		}

		private Sale buildObject(ResultSet rs, boolean retrieveAssociation) throws PersistensException, SQLException {
			Product product = new Product(rs.getInt("productId"));
			VendingMachine vm = new VendingMachine(rs.getInt("vendingMachineId"));
			if (retrieveAssociation) {
				product = DBProduct.getInstance().findProductById(product.getId());
				vm = DBVendingMachine.getInstance().findVendingMachine(vm.getId());
			}
			Sale sale = new Sale(rs.getInt("id"), rs.getDate("timeStamp"), product, vm, rs.getFloat("salesPrice"));
			return sale;
		}
		
}
