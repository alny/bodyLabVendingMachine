package Infrastructure;

import Controller.CannotFindException;
import Database.PersistensException;
import Model.VendingMachine;

public interface CtrVendingMachineIF {
	VendingMachine findVendingMachine(int id, boolean retrieveAssociation)throws PersistensException, CannotFindException; 
	int insertVendingMachine(VendingMachine vm)throws PersistensException;
	VendingMachine findFirstAvailbe()throws PersistensException;
	void changeLentOut(VendingMachine vm);
}


