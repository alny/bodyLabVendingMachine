package Database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import Infrastructure.DBProductIF;
import Model.Product;

public class DBProduct implements DBProductIF {
	private static final String insertProduct = "insert into Product (productNo, name, description, stockValue) values (?,?,?,?,?) ";
	private static final String findProductById = "select * from product where id = ?";
	private PreparedStatement insert, findById;
	private static  DBProduct instance;
	
	private DBProduct () throws SQLException {
		insert = DBConnection.getInstance().getConnection().prepareStatement(insertProduct);
		findById =  DBConnection.getInstance().getConnection().prepareStatement(findProductById);
	}
	

	public static DBProduct getinstance() throws SQLException {
		if (instance == null) {
			instance = new DBProduct();
		}
		return instance;
	}
	
	@Override
	public void insertProduct(Product product) throws SQLException {
		insert.setString(1, product.getProductNo());
		insert.setString(2, product.getName());
		insert.setString(3, product.getDescription());
		insert.setDouble(4, product.getStockValue());
		insert.execute();
		
	}
	
	public Product findProductById(int id) throws SQLException {
		insert.setInt(1, id);
		return buildProductObject(findById.executeQuery());
	}
	
	private Product buildProductObject(ResultSet rs) throws SQLException {

		int id = rs.getInt("id");
		String productNo = rs.getString("productNo");	
		String name = rs.getString("name");
		String description = rs.getString("description");
		double stockValue = rs.getFloat("stockValue");

		Product product = new Product(id, productNo, name, description, stockValue);

		return product;
	}

	
}
