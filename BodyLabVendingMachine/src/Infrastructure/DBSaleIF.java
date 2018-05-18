package Infrastructure;

import java.sql.SQLException;
import java.util.List;

import Database.PersistensException;
import Model.Product;
import Model.Sale;
import Model.VendingMachine;

public interface DBSaleIF {
	int insertSale(Sale s);

}
