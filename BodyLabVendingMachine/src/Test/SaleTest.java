package Test;


import static org.junit.Assert.assertEquals;

import java.sql.SQLException;

import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;

import Controller.CannotFindException;
import Controller.CtrProduct;
import Controller.CtrSale;
import Database.DBCustomer;
import Database.DBProduct;
import Database.DBSale;
import Infrastructure.CtrProductIF;
import Infrastructure.CtrSaleIF;
import Infrastructure.DBProductIF;
import Infrastructure.DBSaleIF;
import Model.Product;
import Model.Sale;

public class SaleTest {
	DBSaleIF dbSale;
	DBProductIF dbProduct;
	CtrSaleIF ctrSale;
	CtrProductIF ctrProduct;
	
	@Before
	public void setUp() {
		dbSale = DBSale.getInstance();
		dbProduct = DBProduct.getInstance();
		ctrSale= new CtrSale();
		ctrProduct = new CtrProduct();
	}
	
//	@Test 
//	public void happyDaysStatisticsTest() {
//		
//		List<Sale> sales =  dbSale.getSalesFromMachineId(vm, true);
//	}
	// HUSK AT RETTE 0 TIL NOGET ANDET NÅR SALE ER BLEVET LAVET
	@Test
	public void happyDaysProductStatisticsTest(){
		Product p = null;
		try {
			p = dbProduct.findProductById(1);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		int i = 0;
		try {
			i = dbSale.getSumOfSaleFromProductId(p);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		assertEquals(i, 0);
	}
	
	@Test
	public void CtrhappyDaysProductStatisticsTest(){
		int sum = 0;
		try {
			sum = ctrSale.getAmountOfSalesFromProduct(2);
		} catch (CannotFindException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertEquals(sum, 0);
	}
	
	@Test()
	public void wrongProductIdTest()  {
		Product p = null;
		try {
			p = dbProduct.findProductById(10000000);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		assertEquals(p, null);
		
	}
	
	@Test(expected = CannotFindException.class)
	public void ctrWrongProductIdTest() throws CannotFindException	{
		ctrProduct.findProductById(10000000);
		
	}

}
