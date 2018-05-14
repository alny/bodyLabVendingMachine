package Infrastructure;

import Model.Product;
import Model.VendingMachine;

public interface VendingMachineIF {
	VendingMachine findVendingMachine(int id);
	int insertVendingMachine(VendingMachine vm);
}
