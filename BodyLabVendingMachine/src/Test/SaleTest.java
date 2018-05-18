package Test;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.sql.SQLException;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;

import Controller.CannotFindException;
import Controller.CtrProduct;
import Controller.CtrSale;
import Controller.CtrVendingMachine;
import Database.DBCustomer;
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
import Model.Product;
import Model.Sale;
import Model.VendingMachine;

public class SaleTest {
	DBSaleIF dbSale;
	DBProductIF dbProduct;
	CtrSaleIF ctrSale;

	DBVendingMachineIF dbvm;
	CtrVendingMachineIF ctrvm;

	@Before
	public void setUp() {
		dbSale = DBSale.getInstance();
		dbProduct = DBProduct.getInstance();
		dbvm = DBVendingMachine.getInstance();
		ctrSale = new CtrSale();

		ctrvm = new CtrVendingMachine();
		
	}

	 @Test
	 public void happyDaysStatisticsTest() {
	VendingMachine vm= null;
	List<Sale> sales = null;
	try {
		vm = dbvm.findVendingMachine(1);
		System.out.println(vm);
	} catch (PersistensException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	 try {
		sales = dbSale.getSalesFromMachineId(vm, true);
		System.out.println(sales);
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (PersistensException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	 assertEquals(2, sales.size());
	 }
	
	@Test
	public void happyDaysProductStatisticsTest() {
		Product p = null;
		try {
			p = dbProduct.findProductById(1);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		int i = 0;
		try {
			i = dbSale.getTotalSaleFromProductId(p);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		assertEquals(1, i);
	}

	@Test
	public void CtrhappyDaysProductStatisticsTest() {
		int sum = 0;
		try {
			sum = ctrSale.getAmountOfSalesFromProduct(2);
		} catch (CannotFindException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertEquals(sum, 1);
	}
	
	@Test
	public void happyDaysProductStatisticsPriceSumTest() {
		Product p = null;
		float sum = 0;
		try {
			p = dbProduct.findProductById(2);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			sum = dbSale.getSumOfSaleFromProductId(p);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		assertEquals(192, sum, 0);
	}
	
	@Test
	public void CtrhappyDaysProductStatisticsPriceSumTest() {
		float sum = 0;
		try {
			sum = ctrSale.getSumFromProduct(1);
		} catch (CannotFindException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertEquals(192, sum, 0);
	}

	@Test()
	public void wrongProductIdTest() {
		Product p = null;
		try {
			p = dbProduct.findProductById(10000000);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		assertEquals(p, null);

	}


	@Test
	public void happyDaysVendingMachineStatisticsPriceSumTest() {
		VendingMachine vm = null;
		float sum = 0;
		try {
			vm = dbvm.findVendingMachine(1);
		} catch (PersistensException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			sum = dbSale.getSumOfSaleFromMachineId(vm);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		assertEquals(384, sum, 0);
	}
	
	@Test
	public void CtrhappyDaysVendingMachineStatisticsPriceSumTest() {
		float sum = 0;
		try {
			sum = ctrSale.getSumFromMachine(1);
		} catch (CannotFindException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertEquals(384, sum, 0);
	}

}