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
	public float getSumFromMachine(int vmId) throws CannotFindException {
		float sum = 0;
		try {
			sum = dbSale.getSumOfSaleFromMachineId(ctrVM.findVendingMachine(vmId));
		} catch (SQLException | PersistensException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return sum;
	}

	@Override
	public float getSumFromProduct(int pId) throws CannotFindException {
		float sum = 0;
		try {
			sum = dbSale.getSumOfSaleFromProductId(ctrP.findProductById(pId));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return sum;
	}

	@Override
	public List<Sale> getAllSalesFromMachine(int vmId) throws CannotFindException {
		List<Sale> sale = null;
		try {
			sale = dbSale.getSalesFromMachineId(ctrVM.findVendingMachine(vmId), true);
		} catch (SQLException | PersistensException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return sale;
	}

	@Override
	public List<Sale> getAllSalesFromProduct(int pId) throws CannotFindException {
		List<Sale> sale = null;
		try {
			sale = dbSale.getSalesFromProductId(ctrP.findProductById(pId), true);
		} catch (PersistensException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return sale;
	}

	@Override
	public int getAmountOfSalesFromMachine(int vmId) throws CannotFindException {
		int totalSales = 0;
		try {
			totalSales = dbSale.getTotalSaleFromMachineId(ctrVM.findVendingMachine(vmId));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (PersistensException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return totalSales;
	}

	@Override
	public int getAmountOfSalesFromProduct(int pId) throws CannotFindException {
		int totalSales = 0;
		try {
			totalSales = dbSale.getTotalSaleFromProductId(ctrP.findProductById(pId));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (totalSales == 0) {
			throw new CannotFindException("produktet fines ikke");
		}
		return totalSales;
	}

	@Override
	public int insertSale(Sale sale) {
		return dbSale.insertSale(sale);
	}

	@Override
	public Sale createSale(int vmId, int productId, float price) throws CannotFindException {
		Sale sale = null;
		try {
			sale = new Sale(new Date(), ctrP.findProductById(productId), ctrVM.findVendingMachine(vmId), price );
		} catch (PersistensException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return sale;
	}

}
