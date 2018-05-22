package Controller;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import Database.DBSale;
import Database.PersistensException;
import Infrastructure.CtrProductIF;
import Infrastructure.CtrSaleIF;
import Infrastructure.CtrVendingMachineIF;
import Infrastructure.DBSaleIF;
import Model.Product;
import Model.Sale;
import Model.VendingMachine;

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
	public int insertSale(Sale sale) {
		return dbSale.insertSale(sale);
	}
	
	@Override
	public Sale createSale(int vmId, int productId, float price) throws CannotFindException {
		Sale sale = null;
		try {
			sale = new Sale(new Date(), ctrP.findProductById(productId), ctrVM.findVendingMachine(vmId,false), price );
		} catch (PersistensException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return sale;
	}



}
