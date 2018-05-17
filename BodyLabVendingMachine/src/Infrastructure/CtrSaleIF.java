package Infrastructure;

import java.util.List;

import Controller.CannotFindException;
import Model.Product;
import Model.Sale;
import Model.VendingMachine;

public interface CtrSaleIF {
	int getSumFromMachine(int vmId) throws CannotFindException;
	int getSumFromProduct(int pId) throws CannotFindException;
	List<Sale> getAllSalesFromMachine(int vmId) throws CannotFindException;
	List<Sale> getAllSalesFromProduct(int pId) throws CannotFindException;
	int getAmountOfSalesFromMachine(int vmId) throws CannotFindException;
	int getAmountOfSalesFromProduct(int pId) throws CannotFindException;
	int insertSale(Sale sale);
	Sale createSale(int vmId, int productId, float price) throws CannotFindException;
}
