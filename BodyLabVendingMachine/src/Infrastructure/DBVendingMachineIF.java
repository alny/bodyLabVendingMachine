package infrastructure;

import database.PersistensException;
import model.Product;
import model.VendingMachine;

public interface DBVendingMachineIF {
	VendingMachine findVendingMachine(int id, boolean retrieveAssociation) throws PersistensException;
	int insertVendingMachine(VendingMachine vm) throws PersistensException;
	VendingMachine findFirstAvailable() throws PersistensException;
}
