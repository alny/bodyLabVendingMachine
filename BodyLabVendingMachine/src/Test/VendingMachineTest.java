package test;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Test;

import controller.CannotFindException;
import controller.CtrProduct;
import controller.CtrSale;
import controller.CtrVendingMachine;
import database.DBProduct;
import database.DBSale;
import database.DBVendingMachine;
import database.PersistensException;
import infrastructure.CtrProductIF;
import infrastructure.CtrSaleIF;
import infrastructure.CtrVendingMachineIF;
import infrastructure.DBProductIF;
import infrastructure.DBSaleIF;
import infrastructure.DBVendingMachineIF;
import model.VendingMachine;

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
