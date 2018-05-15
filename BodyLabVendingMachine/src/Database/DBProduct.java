package Database;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import Model.Product;

public class DBProduct {
	private static final String insertProduct = " insert into Product (productNo, name, description, stockValue) values (?,?,?,?,?) ";
	private PreparedStatement insert;
	private DBProduct instance;
	
	private DBProduct () throws SQLException {
		insert = DBConnection.getInstance().getConnection().prepareStatement(insertProduct);
	}
	
	public DBProduct getinstance() throws SQLException {
		if (instance == null) {
			instance = new DBProduct();
		}
		return instance;
	}
	
	public void insertProduct(Product product) throws SQLException {
		insert.setString(1, product.getProductNo());
		insert.setString(2, product.getName());
		insert.setString(3, product.getDescription());
		insert.setDouble(4, product.getStockValue());
		
	}
	
}
