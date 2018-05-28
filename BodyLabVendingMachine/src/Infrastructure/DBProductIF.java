package Infrastructure;

import java.sql.SQLException;
import java.util.List;

import Database.PersistensException;
import Model.Product;

public interface DBProductIF {


	void insertProduct(Product product) throws PersistensException;

	Product findProductById(int id) throws PersistensException;
	
	List<Product> findProductsInVM(int id) throws PersistensException;

}