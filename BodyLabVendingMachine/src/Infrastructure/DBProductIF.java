package Infrastructure;

import java.sql.SQLException;

import Model.Product;

public interface DBProductIF {

	Product findProductById(int id) throws SQLException;
	void insertProduct(Product product) throws SQLException;

}