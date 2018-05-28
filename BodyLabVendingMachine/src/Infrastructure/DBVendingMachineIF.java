package Infrastructure;

import Database.PersistensException;
import Model.Product;
import Model.VendingMachine;

public interface DBVendingMachineIF {
	VendingMachine findVendingMachine(int id, boolean retrieveAssociation) throws PersistensException;
	int insertVendingMachine(VendingMachine vm) throws PersistensException;
	VendingMachine findFirstAvailable() throws PersistensException;
}
