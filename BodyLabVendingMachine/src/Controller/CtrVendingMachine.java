package Controller;

import Database.DBVendingMachine;
import Database.PersistensException;
import Infrastructure.CtrVendingMachineIF;
import Model.VendingMachine;

public class CtrVendingMachine implements CtrVendingMachineIF {
	
	private DBVendingMachine dbVendingMachine;
	
	public CtrVendingMachine() {
		dbVendingMachine = DBVendingMachine.getInstance();
	}
	@Override
	public VendingMachine findVendingMachine(int id) throws PersistensException {
		return dbVendingMachine.findVendingMachine(id);
	}

	@Override
	public int insertVendingMachine(VendingMachine vm) throws PersistensException {
		return dbVendingMachine.insertVendingMachine(vm);
	}

}
