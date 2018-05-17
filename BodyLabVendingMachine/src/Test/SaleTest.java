package Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.sql.SQLException;
import java.util.List;

import org.junit.Before;
import org.junit.jupiter.api.Test;

import Database.DBProduct;
import Database.DBSale;
import Infrastructure.DBProductIF;
import Infrastructure.DBSaleIF;
import Model.Product;
import Model.Sale;

public class SaleTest {
	DBSaleIF dbSale;
	DBProductIF dbProduct;
	
	
	@Before
	public void setUp(){
		dbSale = DBSale.getInstance();
		dbProduct = DBProduct.getInstance();
	}
	
//	@Test 
//	public void happyDaysStatisticsTest() {
//		
//		List<Sale> sales =  dbSale.getSalesFromMachineId(vm, true);
//	}
	
	@Test
	public void happyDaysProductStatisticsTest(){
		Product p = null;
		System.out.println(p);
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

}
