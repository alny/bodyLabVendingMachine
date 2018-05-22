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
	public VendingMachine findVendingMachine(int id, boolean retrieveAssociation) throws PersistensException, CannotFindException {
		VendingMachine vm = null;
		vm = dbVendingMachine.findVendingMachine(id, retrieveAssociation);
		if(vm == null) {
			throw new CannotFindException("automaten findes ikke");
		}
		return vm;
	}

	@Override
	public int insertVendingMachine(VendingMachine vm) throws PersistensException {
		return dbVendingMachine.insertVendingMachine(vm);
	}
	@Override
	public VendingMachine findFirstAvailbe() throws PersistensException {
		return dbVendingMachine.findFirstAvailable();
	}

}
