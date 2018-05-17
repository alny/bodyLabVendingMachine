package Infrastructure;

import java.util.List;

import Controller.CannotFindException;
import Model.Product;
import Model.Sale;
import Model.VendingMachine;

public interface CtrSaleIF {
	int getSumFromMachine(int vmId);
	int getSumFromProduct(int pId) throws CannotFindException;
	List<Sale> getAllSalesFromMachine(int vmId);
	List<Sale> getAllSalesFromProduct(int pId) throws CannotFindException;
	int getAmountOfSalesFromMachine(int vmId);
	int getAmountOfSalesFromProduct(int pId) throws CannotFindException;
	int insertSale(Sale sale);
	Sale createSale(int vmId, int productId) throws CannotFindException;
}
