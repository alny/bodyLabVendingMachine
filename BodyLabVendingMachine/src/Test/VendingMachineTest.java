package Test;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Test;

import Controller.CannotFindException;
import Controller.CtrProduct;
import Controller.CtrSale;
import Controller.CtrVendingMachine;
import Database.DBProduct;
import Database.DBSale;
import Database.DBVendingMachine;
import Database.PersistensException;
import Infrastructure.CtrProductIF;
import Infrastructure.CtrSaleIF;
import Infrastructure.CtrVendingMachineIF;
import Infrastructure.DBProductIF;
import Infrastructure.DBSaleIF;
import Infrastructure.DBVendingMachineIF;
import Model.VendingMachine;

public class VendingMachineTest {
	
	DBSaleIF dbSale;
	DBProductIF dbProduct;
	CtrSaleIF ctrSale;
	CtrProductIF ctrProduct;
	CtrVendingMachineIF cvm;
	DBVendingMachineIF dbvm;
	
	@Before
	public void setUp() {
		dbSale = DBSale.getInstance();
		dbProduct = DBProduct.getInstance();
		ctrSale= new CtrSale();
		ctrProduct = new CtrProduct();
		cvm = new CtrVendingMachine();
		dbvm = DBVendingMachine.getInstance();
	}
	
	@Test
	public void findVendingMachineByIdCtrTest() throws PersistensException, CannotFindException {
		VendingMachine vm = null;
		vm = cvm.findVendingMachine(1,false);
		assertEquals(vm.getId(), 1);
	}
	
	@Test(expected = CannotFindException.class)
	public void wrongfindVendingMachineByIdTest() throws PersistensException, CannotFindException {
		cvm.findVendingMachine(10000000,false);
	}
	
	@Test
	public void findVendingMachineByID() throws PersistensException {
		VendingMachine vm = null;
		vm = dbvm.findVendingMachine(1,false);
		assertEquals(vm.getId(), 1);
	}
	
	@Test
	public void wrongFindVendingMachineByID() throws PersistensException {
		VendingMachine vm = new VendingMachine(2);
		vm = dbvm.findVendingMachine(100000000,false);
		assertEquals(vm, null);
	}

}	
