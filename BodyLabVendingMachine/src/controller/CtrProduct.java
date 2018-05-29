package controller;

import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import database.DBProduct;
import database.PersistensException;
import infrastructure.CtrProductIF;
import infrastructure.DBProductIF;
import model.Product;
import model.VendingMachine;

public class CtrProduct implements CtrProductIF {
	private DBProductIF dbProduct;
	private CtrVendingMachine ctrVendingMachine;
	
	public CtrProduct(){
		dbProduct = DBProduct.getInstance();
		ctrVendingMachine = new CtrVendingMachine();
	}
	@Override
	public Product findProductById(int id) throws CannotFindException {
		Product product = null;
		try {
			product = dbProduct.findProductById(id);
		} catch (PersistensException e) {
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
			dbProduct.insertProduct(product);
		} catch (PersistensException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
}
