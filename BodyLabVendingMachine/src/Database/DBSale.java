package Database;

import java.sql.SQLException;
import java.util.List;

import Infrastructure.DBSaleIF;
import Model.Sale;

public class DBSale implements DBSaleIF {
	private static DBSale instance;
	private static final String getSalesFromMachineId = "select * from Sale where vendingMachineId = ?";
	private static final String getSumMachine = " SUM(price) from Sale where vendingMachineId = ?";
	private static final String getSumProduct = "select sum(price) from Sale where produktId = ?";
	private static final String getSalesProduct = "select * from Sale where produktId = ?";
	private DBSale() {
		
	}
	
	public static DBSale getInstance() throws SQLException {
		if(instance == null) {
			instance = new DBSale();
		}
		return instance;
	}
	
	@Override
	public List<Sale> getSalesFromMachineId(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getTotalSaleFromMachineId(int id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getTotalSaleFromProductId(int id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<Sale> getSalesFromProductId(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int insertSale(Sale s) {
		// TODO Auto-generated method stub
		return 0;
	}

}
