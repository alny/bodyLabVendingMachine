package Infrastructure;

import java.util.List;

import Controller.CannotFindException;
import Database.PersistensException;
import Model.Product;

public interface CtrProductIF {
	
	Product findProductById(int id) throws CannotFindException;
	void insertProduct(Product product);
	List<Product> findProductsInVM(int id) throws PersistensException, CannotFindException;
}
