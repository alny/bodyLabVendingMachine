package Infrastructure;

import java.sql.SQLException;
import java.util.List;

import Model.Product;
import Model.Sale;
import Model.VendingMachine;

public interface DBSaleIF {
	int getTotalSaleFromMachineId(VendingMachine vm) throws SQLException;
	int getTotalSaleFromProductId(Product p);
	List<Sale> getSalesFromProductId(Product p);
	int insertSale(Sale s);
	List<Sale> getSalesFromMachineId(VendingMachine vm) throws SQLException;
}
