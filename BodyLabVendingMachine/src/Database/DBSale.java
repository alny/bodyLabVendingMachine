package Database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLDataException;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

import Infrastructure.DBSaleIF;
import Model.Product;
import Model.Sale;
import Model.VendingMachine;

public class DBSale implements DBSaleIF {
	private static DBSale instance;
	private Connection connection;

	private DBSale() {
		connection = DBConnection.getInstance().getConnection();
	}

	public static DBSale getInstance() {
		if (instance == null) {
			instance = new DBSale();
		}
		return instance;
	}

	// fix execptions ikke godt at have try catch i en catch
			@Override
			public int insertSale(Sale sale) {
				int id = 0;
				String insert = "insert into Sale (vendingMachineId, productId, salesPrice)" + " values (?,?,?)";
				String changeQuantity = "update MachineProduct set qty = qty - 1 where productId = ? and vendingMachineId = ?";
				try {
					DBConnection.getInstance().startTransaction();
					PreparedStatement insertPS = connection.prepareStatement(insert,  Statement.RETURN_GENERATED_KEYS);
					PreparedStatement updateQty = connection.prepareStatement(changeQuantity);
					insertPS.setInt(1, sale.getVendingmachine().getId());
					insertPS.setInt(2, sale.getProduct().getId());
					insertPS.setFloat(3, sale.getPrice());
					id = DBConnection.getInstance().executeInsertWithIdentity(insertPS);
					updateQty.executeQuery();
					DBConnection.getInstance().commitTransaction();
				}
				 catch (SQLException e) {
					// TODO Auto-generated catch block
					try {
						DBConnection.getInstance().rollbackTransaction();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				return id;
			}	
}
