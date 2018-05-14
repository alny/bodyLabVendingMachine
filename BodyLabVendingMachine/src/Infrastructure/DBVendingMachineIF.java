package Infrastructure;

import Model.Product;
import Model.VendingMachine;

public interface DBVendingMachineIF {
	VendingMachine findVendingMachine(int id);
	int insertVendingMachine(VendingMachine vm);
}
