package infrastructure;

import java.sql.SQLException;
import java.util.List;

import database.PersistensException;
import model.Product;
import model.Sale;
import model.VendingMachine;

public interface DBSaleIF {
	int insertSale(Sale s) throws PersistensException;
}
