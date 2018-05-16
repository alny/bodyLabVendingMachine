package Infrastructure;

import java.util.List;

import Model.Product;
import Model.Sale;
import Model.VendingMachine;

public interface CtrSaleIF {
	int getSumFromMachine(VendingMachine vm);
	int getSumFromProduct(Product product);
	List<Sale> getAllSalesFromMachine(VendingMachine vm);
	List<Sale> getAllSalesFromProduct(Product product);
	int getAmountOfSalesFromMachine(VendingMachine vm);
	int getAmountOfSalesFromProduct(Product product);
	int insertSale(Sale sale);
	Sale createSale(int vmId, int productId);
}
