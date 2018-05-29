package infrastructure;

import java.util.List;

import controller.CannotFindException;
import database.PersistensException;
import model.Product;

public interface CtrProductIF {
	
	Product findProductById(int id) throws CannotFindException;
	void insertProduct(Product product);
}
