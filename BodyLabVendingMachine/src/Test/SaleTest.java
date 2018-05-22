package Test;

import static org.junit.Assert.assertEquals;
import java.sql.SQLException;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import Controller.CannotFindException;
import Controller.CtrBusinessIntelligence;
import Controller.CtrVendingMachine;
import Database.DBProduct;
import Database.DBBusinessIntelligence;
import Database.DBVendingMachine;
import Database.PersistensException;
import Infrastructure.CtrBusinessIntelligenceIF;
import Infrastructure.CtrVendingMachineIF;
import Infrastructure.DBProductIF;
import Infrastructure.DBBusinessIntelligenceIF;
import Infrastructure.DBVendingMachineIF;
import Model.Product;
import Model.Sale;
import Model.VendingMachine;

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
		i = dbBusinessIntelligence.getTotalSaleFromProductId(p);

		assertEquals(1, i);
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

		sum = dbBusinessIntelligence.getSumOfSaleFromProductId(p);

		assertEquals(192, sum, 0);
	}
	
	@Test
	public void CtrhappyDaysProductStatisticsPriceSumTest() {
		float sum = 0;
		try {
			sum = ctrBusinessIntelligence.getSumFromProduct(1);
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
			vm = dbvm.findVendingMachine(1,false);
		} catch (PersistensException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		sum = dbBusinessIntelligence.getSumOfSaleFromMachineId(vm);

		assertEquals(384, sum, 0);
	}
	
	@Test
	public void CtrhappyDaysVendingMachineStatisticsPriceSumTest() {
		float sum = 0;
		try {
			sum = ctrBusinessIntelligence.getSumFromMachine(1);
		} catch (CannotFindException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertEquals(384, sum, 0);
	}

}
