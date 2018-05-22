package Infrastructure;

import Database.PersistensException;
import Model.Product;
import Model.VendingMachine;

public interface DBVendingMachineIF {
	
	VendingMachine findVendingMachine(int id) throws PersistensException;
	int insertVendingMachine(VendingMachine vm) throws PersistensException;
	VendingMachine findFirstAvailable() throws PersistensException;
}
