package Infrastructure;

import Controller.CannotFindException;
import Model.Product;

public interface CtrProductIF {
	
	Product findProductById(int id) throws CannotFindException;
	void insertProduct(Product product);
}
