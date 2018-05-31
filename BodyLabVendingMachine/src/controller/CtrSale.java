package controller;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import database.DBSale;
import database.PersistensException;
import infrastructure.CtrProductIF;
import infrastructure.CtrSaleIF;
import infrastructure.CtrVendingMachineIF;
import infrastructure.DBSaleIF;
import model.Product;
import model.Sale;
import model.VendingMachine;

public class CtrSale implements CtrSaleIF {
	private DBSaleIF dbSale;
	private CtrProductIF ctrP;
	private CtrVendingMachineIF ctrVM;
	
	public CtrSale() {
		dbSale = DBSale.getInstance();
		ctrP = new CtrProduct();	
		ctrVM = new CtrVendingMachine();
	}

	@Override
	public int insertSale(Sale sale) throws PersistensException {
		return dbSale.insertSale(sale);
	}
	
	@Override
	public Sale createSale(int vmId, int productId, float price) throws CannotFindException {
		Sale sale = null;
		try {
			sale = new Sale(new Date(), ctrP.findProductById(productId), ctrVM.findVendingMachine(vmId,false), price );
			insertSale(sale);
		} catch (PersistensException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return sale;
	}



}
