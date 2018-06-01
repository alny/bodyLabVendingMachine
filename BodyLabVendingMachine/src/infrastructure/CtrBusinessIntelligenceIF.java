package infrastructure;

import java.util.List;

import controller.CannotFindException;
import database.PersistensException;
import model.Product;
import model.Sale;
import model.VendingMachine;

public interface CtrBusinessIntelligenceIF {

	float getSumFromMachine(int vmId, String startD, String endD) throws CannotFindException;

	float getSumFromProduct(int pId, String startD, String endD) throws CannotFindException;
	
	int getTotalSumFromAllMachines(int cId, String startD, String endD) throws CannotFindException;

	List<Sale> getAllSalesFromMachine(int vmId) throws CannotFindException;

	List<Sale> getAllSalesFromProduct(int pId) throws CannotFindException;

	int getAmountOfSalesFromMachine(int vmId) throws CannotFindException;

	int getAmountOfSalesFromProduct(int pId) throws CannotFindException;

	Sale createSale(int vmId, int productId, float price) throws CannotFindException;
	
	int getQuantity(int vmId, int productId) throws PersistensException, CannotFindException;

	int getTotalSumProductFromAllMachines(int cId, String startD, String endD, int productId)
			throws CannotFindException;
	List<Product> findCustomerProduct(int id) throws PersistensException;
	VendingMachine getVendingMachine(int id) throws PersistensException, CannotFindException;
}