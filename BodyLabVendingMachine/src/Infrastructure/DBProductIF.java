package Infrastructure;

import java.sql.SQLException;
import java.util.List;

import Database.PersistensException;
import Model.Product;

public interface DBProductIF {


	void insertProduct(Product product) throws SQLException;

	Product findProductById(int id) throws SQLException;
	
	List<Product> findProductsInVM(int id) throws PersistensException;

}