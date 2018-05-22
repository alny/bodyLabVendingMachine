package Infrastructure;

import java.util.List;

import Controller.CannotFindException;
import Model.Product;
import Model.Sale;
import Model.VendingMachine;

public interface CtrSaleIF {
	int insertSale(Sale sale);
	Sale createSale(int vmId, int productId, float price) throws CannotFindException;
}
