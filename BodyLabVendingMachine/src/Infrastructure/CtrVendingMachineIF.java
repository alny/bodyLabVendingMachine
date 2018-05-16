package Infrastructure;

import Database.PersistensException;
import Model.VendingMachine;

public interface CtrVendingMachineIF {
	VendingMachine findVendingMachine(int id)throws PersistensException; 
	int insertVendingMachine(VendingMachine vm)throws PersistensException;
}


