package infrastructure;

import java.sql.SQLException;
import java.util.List;

import database.PersistensException;
import model.Product;

public interface DBProductIF {
	void insertProduct(Product product) throws PersistensException;
	Product findProductById(int id) throws PersistensException;
	List<Product> findProductsInVM(int id) throws PersistensException;

}