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



}
