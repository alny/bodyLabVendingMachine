package infrastructure;

import java.util.List;

import controller.CannotFindException;
import database.PersistensException;
import model.Product;
import model.Sale;
import model.VendingMachine;

public interface CtrSaleIF {
	int insertSale(Sale sale) throws PersistensException;
	Sale createSale(int vmId, int productId, float price) throws CannotFindException;
}
