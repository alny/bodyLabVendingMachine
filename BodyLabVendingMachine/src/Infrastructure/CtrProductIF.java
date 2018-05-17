package Infrastructure;

import Model.Product;

public interface CtrProductIF {
	
	Product findProductById(int id);
	void insertProduct(Product product);
}
