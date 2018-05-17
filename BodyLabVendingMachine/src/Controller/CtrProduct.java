package Controller;

import java.sql.SQLException;

import Database.DBProduct;
import Infrastructure.CtrProductIF;
import Infrastructure.DBProductIF;
import Model.Product;

public class CtrProduct implements CtrProductIF {
	private DBProductIF dbP;
	
	public CtrProduct(){
		dbP = DBProduct.getInstance();
	}
	@Override
	public Product findProductById(int id) throws CannotFindException {
		Product product = null;
		try {
			product = dbP.findProductById(id);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(product == null) {
			throw new CannotFindException("Produktet findes ikke");
		}
		return product;
		
	}

	@Override
	public void insertProduct(Product product) {
		try {
			dbP.insertProduct(product);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
}
