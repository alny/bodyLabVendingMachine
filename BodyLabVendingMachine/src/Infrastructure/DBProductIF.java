package Infrastructure;

import java.sql.SQLException;

import Model.Product;

public interface DBProductIF {

	DBProductIF getinstance() throws SQLException;

	void insertProduct(Product product) throws SQLException;

}