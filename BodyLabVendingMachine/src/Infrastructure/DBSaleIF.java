package Infrastructure;

import java.util.List;

import Model.Sale;

public interface DBSaleIF {
	List<Sale> getSalesFromMachineId(int id);
	int getTotalSaleFromMachineId(int id);
	int getTotalSaleFromProductId(int id);
	List<Sale> getSalesFromProductId(int id);
	int insertSale(Sale s);
}
