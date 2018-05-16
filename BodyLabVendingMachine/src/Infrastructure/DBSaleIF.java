package Infrastructure;

import java.sql.SQLException;
import java.util.List;

import Database.PersistensException;
import Model.Product;
import Model.Sale;
import Model.VendingMachine;

public interface DBSaleIF {
	int getTotalSaleFromMachineId(VendingMachine vm) throws SQLException;
	int getTotalSaleFromProductId(Product p) throws SQLException;
	List<Sale> getSalesFromProductId(Product p, boolean retrieveAssociation) throws PersistensException;
	int insertSale(Sale s);
	List<Sale> getSalesFromMachineId(VendingMachine vm, boolean retrieveAssociation) throws SQLException, PersistensException;
	int getSumOfSaleFromMachineId(VendingMachine vm) throws SQLException;
	int getSumOfSaleFromProductId(Product p) throws SQLException;
}
