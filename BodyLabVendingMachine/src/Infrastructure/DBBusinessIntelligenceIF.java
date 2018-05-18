package Infrastructure;

import java.util.List;

import Database.PersistensException;
import Model.Product;
import Model.Sale;
import Model.VendingMachine;

public interface DBBusinessIntelligenceIF {

	List<Sale> getSalesFromMachineId(VendingMachine vm, boolean retrieveAssociation) throws PersistensException;

	float getSumOfSaleFromMachineId(VendingMachine vm);

	float getSumOfSaleFromProductId(Product product);

	List<Sale> getSalesFromProductId(Product product, boolean retrieveAssociation) throws PersistensException;

	int getTotalSaleFromMachineId(VendingMachine vm);

	int getTotalSaleFromProductId(Product product);

}