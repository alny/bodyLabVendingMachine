package Infrastructure;

import java.util.List;

import Model.Sale;

public interface SaleIF {
	List<Sale> getSalesFromMachineId(int id);
	int insertSale(Sale s);
}
