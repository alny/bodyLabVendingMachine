package infrastructure;

import java.util.List;

import database.PersistensException;
import model.Product;
import model.Sale;
import model.VendingMachine;

public interface DBBusinessIntelligenceIF {

	List<Sale> getSalesFromMachineId(VendingMachine vm, boolean retrieveAssociation) throws PersistensException;

	float getSumOfSaleFromMachineId(VendingMachine vm, String startD, String endD);
	
	int getTotalSumFromAllMachines(int cId, String startD, String endD) throws PersistensException;

	List<Sale> getSalesFromProductId(Product product, boolean retrieveAssociation) throws PersistensException;

	int getTotalSaleFromMachineId(VendingMachine vm);

	int getTotalSaleFromProductId(Product product);

	float getSumOfSaleFromProductId(Product product, String startD, String endD);
	
	int getMachineQuantity(VendingMachine vm, Product product);

	int getTotalSumFromProductAllMachines(int cId, String startD, String endD, int productId)
			throws PersistensException;
	
	List<Product> findCustomerProduct (int id) throws PersistensException;

}