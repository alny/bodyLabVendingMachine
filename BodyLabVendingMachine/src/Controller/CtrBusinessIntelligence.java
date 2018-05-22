package Controller;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import Database.DBBusinessIntelligence;
import Database.DBBusinessIntelligence;
import Database.PersistensException;
import Infrastructure.CtrBusinessIntelligenceIF;
import Infrastructure.CtrProductIF;
import Infrastructure.CtrVendingMachineIF;
import Infrastructure.DBBusinessIntelligenceIF;
import Infrastructure.DBBusinessIntelligenceIF;
import Model.Sale;

public class CtrBusinessIntelligence implements CtrBusinessIntelligenceIF {
	private DBBusinessIntelligenceIF dbBI;
	private CtrProductIF ctrP;
	private CtrVendingMachineIF ctrVM;
	
	public CtrBusinessIntelligence() {
		dbBI = DBBusinessIntelligence.getInstance();
		ctrP = new CtrProduct();	
		ctrVM = new CtrVendingMachine();
	}

	/* (non-Javadoc)
	 * @see Controller.CtrBusinessIntelligenceIF#getSumFromMachine(int)
	 */
	@Override
	public float getSumFromMachine(int vmId) throws CannotFindException {
		float sum = 0;
		try {
			sum = dbBI.getSumOfSaleFromMachineId(ctrVM.findVendingMachine(vmId));
		} catch (PersistensException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return sum;
	}
	
	/* (non-Javadoc)
	 * @see Controller.CtrBusinessIntelligenceIF#getSumFromProduct(int)
	 */
	@Override
	public float getSumFromProduct(int pId) throws CannotFindException {
		float sum = 0;
		sum = dbBI.getSumOfSaleFromProductId(ctrP.findProductById(pId));
		return sum;
	}
	
	/* (non-Javadoc)
	 * @see Controller.CtrBusinessIntelligenceIF#getAllSalesFromMachine(int)
	 */
	@Override
	public List<Sale> getAllSalesFromMachine(int vmId) throws CannotFindException {
		List<Sale> sale = null;
		try {
			sale = dbBI.getSalesFromMachineId(ctrVM.findVendingMachine(vmId), true);
		} catch (PersistensException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return sale;
	}
	
	/* (non-Javadoc)
	 * @see Controller.CtrBusinessIntelligenceIF#getAllSalesFromProduct(int)
	 */
	@Override
	public List<Sale> getAllSalesFromProduct(int pId) throws CannotFindException {
		List<Sale> sale = null;
		try {
			sale = dbBI.getSalesFromProductId(ctrP.findProductById(pId), true);
		} catch (PersistensException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return sale;
	}
	
	/* (non-Javadoc)
	 * @see Controller.CtrBusinessIntelligenceIF#getAmountOfSalesFromMachine(int)
	 */
	@Override
	public int getAmountOfSalesFromMachine(int vmId) throws CannotFindException {
		int totalSales = 0;
		try {
			totalSales = dbBI.getTotalSaleFromMachineId(ctrVM.findVendingMachine(vmId));
		} catch (PersistensException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return totalSales;
	}
	
	/* (non-Javadoc)
	 * @see Controller.CtrBusinessIntelligenceIF#getAmountOfSalesFromProduct(int)
	 */
	@Override
	public int getAmountOfSalesFromProduct(int pId) throws CannotFindException {
		int totalSales = 0;
		totalSales = dbBI.getTotalSaleFromProductId(ctrP.findProductById(pId));
		if (totalSales == 0) {
			throw new CannotFindException("produktet findes ikke");
		}
		return totalSales;
	}
	
	/* (non-Javadoc)
	 * @see Controller.CtrBusinessIntelligenceIF#createSale(int, int, float)
	 */
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

	@Override
	public int getTotalSumFromAllMachines(int cId) throws CannotFindException {
		int totalSum = 0;
		try {
			totalSum = dbBI.getTotalSumFromAllMachines(cId);
		} catch (PersistensException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return totalSum;
	}
}
