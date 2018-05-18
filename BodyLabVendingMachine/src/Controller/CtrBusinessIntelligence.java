package Controller;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import Database.PersistensException;
import Infrastructure.CtrBusinessIntelligenceIF;
import Model.Sale;

public class CtrBusinessIntelligence implements CtrBusinessIntelligenceIF {

	/* (non-Javadoc)
	 * @see Controller.CtrBusinessIntelligenceIF#getSumFromMachine(int)
	 */
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
	
	/* (non-Javadoc)
	 * @see Controller.CtrBusinessIntelligenceIF#getSumFromProduct(int)
	 */
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
	
	/* (non-Javadoc)
	 * @see Controller.CtrBusinessIntelligenceIF#getAllSalesFromMachine(int)
	 */
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
	
	/* (non-Javadoc)
	 * @see Controller.CtrBusinessIntelligenceIF#getAllSalesFromProduct(int)
	 */
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
	
	/* (non-Javadoc)
	 * @see Controller.CtrBusinessIntelligenceIF#getAmountOfSalesFromMachine(int)
	 */
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
	
	/* (non-Javadoc)
	 * @see Controller.CtrBusinessIntelligenceIF#getAmountOfSalesFromProduct(int)
	 */
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
}
