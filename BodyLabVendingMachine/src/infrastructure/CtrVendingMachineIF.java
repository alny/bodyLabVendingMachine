package infrastructure;

import controller.CannotFindException;
import database.PersistensException;
import model.VendingMachine;

public interface CtrVendingMachineIF {
	VendingMachine findVendingMachine(int id, boolean retrieveAssociation)throws PersistensException, CannotFindException; 
	int insertVendingMachine(VendingMachine vm)throws PersistensException;
	VendingMachine findFirstAvailbe()throws PersistensException, CannotFindException;
	void changeLentOut(VendingMachine vm);
}


