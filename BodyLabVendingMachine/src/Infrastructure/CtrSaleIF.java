package Infrastructure;

import java.util.List;

import Model.Product;
import Model.Sale;
import Model.VendingMachine;

public interface CtrSaleIF {
	int getSumFromMachine(int vmId);
	int getSumFromProduct(int pId);
	List<Sale> getAllSalesFromMachine(int vmId);
	List<Sale> getAllSalesFromProduct(int pId);
	int getAmountOfSalesFromMachine(int vmId);
	int getAmountOfSalesFromProduct(int pId);
	int insertSale(Sale sale);
	Sale createSale(int vmId, int productId);
}
