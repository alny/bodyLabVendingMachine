package controller;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import database.DBBusinessIntelligence;
import database.PersistensException;
import infrastructure.CtrBusinessIntelligenceIF;
import infrastructure.CtrProductIF;
import infrastructure.CtrVendingMachineIF;
import infrastructure.DBBusinessIntelligenceIF;
import model.Product;
import model.Sale;
import model.VendingMachine;

public class CtrBusinessIntelligence implements CtrBusinessIntelligenceIF {
	private DBBusinessIntelligenceIF dbBI;
	private CtrProductIF ctrP;
	private CtrVendingMachineIF ctrVM;
	
	public CtrBusinessIntelligence() {
		dbBI = DBBusinessIntelligence.getInstance();
		ctrP = new CtrProduct();	
		ctrVM = new CtrVendingMachine();
	}

	@Override
	public float getSumFromMachine(int vmId, String startD, String endD) throws CannotFindException {
		float sum = 0;
		try {
			sum = dbBI.getSumOfSaleFromMachineId(ctrVM.findVendingMachine(vmId, false), startD, endD);
		} catch (PersistensException e) {
			e.printStackTrace();
		}
		return sum;
	}
	
	@Override
	public float getSumFromProduct(int pId, String startD, String endD) throws CannotFindException {
		float sum = 0;
		sum = dbBI.getSumOfSaleFromProductId(ctrP.findProductById(pId), startD, endD);
		return sum;
	}
	
	@Override
	public List<Sale> getAllSalesFromMachine(int vmId) throws CannotFindException {
		List<Sale> sale = null;
		try {
			sale = dbBI.getSalesFromMachineId(ctrVM.findVendingMachine(vmId, false), false);
		} catch (PersistensException e) {
			e.printStackTrace();
		}
		return sale;
	}
	
	@Override
	public List<Sale> getAllSalesFromProduct(int pId) throws CannotFindException {
		List<Sale> sale = null;
		try {
			sale = dbBI.getSalesFromProductId(ctrP.findProductById(pId), true);
		} catch (PersistensException e) {
			e.printStackTrace();
		}
		return sale;
	}
	
	@Override
	public int getAmountOfSalesFromMachine(int vmId) throws CannotFindException {
		int totalSales = 0;
		try {
			totalSales = dbBI.getTotalSaleFromMachineId(ctrVM.findVendingMachine(vmId,false));
		} catch (PersistensException e) {
			e.printStackTrace();
		}
		return totalSales;
	}
	
	@Override
	public int getAmountOfSalesFromProduct(int pId) throws CannotFindException {
		int totalSales = 0;
		totalSales = dbBI.getTotalSaleFromProductId(ctrP.findProductById(pId));
		if (totalSales == 0) {
			throw new CannotFindException("produktet findes ikke");
		}
		return totalSales;
	}
	
	@Override
	public Sale createSale(int vmId, int productId, float price) throws CannotFindException {
		Sale sale = null;
		try {
			sale = new Sale(new Date(), ctrP.findProductById(productId), ctrVM.findVendingMachine(vmId,false), price );
		} catch (PersistensException e) {
			e.printStackTrace();
		}
		return sale;
	}

	@Override
	public int getTotalSumFromAllMachines(int cId, String startD, String endD) throws CannotFindException {
		int totalSum = 0;
		try {
			totalSum = dbBI.getTotalSumFromAllMachines(cId, startD, endD);
		} catch (PersistensException e) {
			e.printStackTrace();
		}
		return totalSum;
	}
	
	@Override
	public int getTotalSumProductFromAllMachines(int cId, String startD, String endD, int productId) throws CannotFindException {
		int totalSum = 0;
		try {
			totalSum = dbBI.getTotalSumFromProductAllMachines(cId, startD, endD, productId);
		} catch (PersistensException e) {
			e.printStackTrace();
		}
		return totalSum;
	}
	
	public int getQuantity(int vmId, int productId ) throws PersistensException, CannotFindException {
		int Quantity = 0;
		Quantity = dbBI.getMachineQuantity(ctrVM.findVendingMachine(vmId, true), ctrP.findProductById(productId));
		return Quantity;
	}
	
	public List<Product> findCustomerProduct(int id) throws PersistensException{
		List<Product> products = dbBI.findCustomerProduct(id);
		return products;
	}

	@Override
	public VendingMachine getVendingMachine(int id) throws PersistensException, CannotFindException {
		return ctrVM.findVendingMachine(id, true);
	}
}
