package test;

import static org.junit.Assert.assertEquals;
import java.sql.SQLException;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import controller.CannotFindException;
import controller.CtrBusinessIntelligence;
import controller.CtrVendingMachine;
import database.DBBusinessIntelligence;
import database.DBProduct;
import database.DBVendingMachine;
import database.PersistensException;
import infrastructure.CtrBusinessIntelligenceIF;
import infrastructure.CtrVendingMachineIF;
import infrastructure.DBBusinessIntelligenceIF;
import infrastructure.DBProductIF;
import infrastructure.DBVendingMachineIF;
import model.Product;
import model.Sale;
import model.VendingMachine;

public class SaleTest {
	DBBusinessIntelligenceIF dbBusinessIntelligence;
	DBProductIF dbProduct;
	CtrBusinessIntelligenceIF ctrBusinessIntelligence;

	DBVendingMachineIF dbvm;
	CtrVendingMachineIF ctrvm;
	

	@Before
	public void setUp() {
		dbBusinessIntelligence = DBBusinessIntelligence.getInstance();
		dbProduct = DBProduct.getInstance();
		dbvm = DBVendingMachine.getInstance();
		ctrBusinessIntelligence = new CtrBusinessIntelligence();

		ctrvm = new CtrVendingMachine();
		
	}

	 @Test
	 public void happyDaysStatisticsTest() {
	VendingMachine vm= null;
	List<Sale> sales = null;
	try {
		vm = dbvm.findVendingMachine(1,false);
		System.out.println(vm);
	} catch (PersistensException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	 try {
		sales = dbBusinessIntelligence.getSalesFromMachineId(vm, true);
		System.out.println(sales);
	} catch (PersistensException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	 assertEquals(4, sales.size());
	 }
	
	@Test
	public void happyDaysProductStatisticsTest() {
		Product p = null;
		try {
			p = dbProduct.findProductById(1);
		} catch (PersistensException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		int i = 0;
		i = dbBusinessIntelligence.getTotalSaleFromProductId(p);

		assertEquals(2, i);
	}

	@Test
	public void CtrhappyDaysProductStatisticsTest() {
		int sum = 0;
		try {
			sum = ctrBusinessIntelligence.getAmountOfSalesFromProduct(2);
		} catch (CannotFindException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertEquals(sum, 2);
	}
	
	@Test
	public void happyDaysProductStatisticsPriceSumTest() {
		Product p = null;
		float sum = 0;
		try {
			p = dbProduct.findProductById(2);
		} catch (PersistensException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		sum = dbBusinessIntelligence.getSumOfSaleFromProductId(p,"01/01/1900","01/01/2020");

		assertEquals(384, sum, 0);
	}
	
	@Test
	public void CtrhappyDaysProductStatisticsPriceSumTest() {
		float sum = 0;
		try {
			sum = ctrBusinessIntelligence.getSumFromProduct(1,"01/01/1900","01/01/2020");
		} catch (CannotFindException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertEquals(384, sum, 0);
	}

	@Test()
	public void wrongProductIdTest() {
		Product p = null;
		try {
			p = dbProduct.findProductById(10000000);
		} catch (PersistensException e) {
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
			vm = dbvm.findVendingMachine(1,false);
		} catch (PersistensException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		sum = dbBusinessIntelligence.getSumOfSaleFromMachineId(vm,"01/01/1900","01/01/2020");

		assertEquals(768, sum, 0);
	}
	
	@Test
	public void CtrhappyDaysVendingMachineStatisticsPriceSumTest() {
		float sum = 0;
		try {
			sum = ctrBusinessIntelligence.getSumFromMachine(1,"01/01/1900","01/01/2020");
		} catch (CannotFindException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertEquals(768, sum, 0);
	}

}
