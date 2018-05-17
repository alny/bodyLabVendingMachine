package Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import org.junit.Test;

import Database.DBCustomer;
import Database.PersistensException;
import Infrastructure.DBCustomerIF;

import org.junit.Before;

import Model.Customer;
public class CustomerTest {
	DBCustomerIF dbC;
	@Before
	public void setUp() {
		dbC = DBCustomer.getInstance();
	}
	@Test
	public void testDBFindCustomerSucces(){
		int id = 1;
		Customer customer = null;
		try {
			customer = dbC.findCustomer(id);
		} catch (PersistensException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertEquals("Fitness Hadsund", customer.getName());
	}
	
	@Test
	public void testDBFindCustomerFail() {
		int id = 2;
		Customer customer = null;
		try {
			customer = dbC.findCustomer(id);
		} catch (PersistensException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertNotEquals(customer.getName(),"Fitness Aalbog");
	}
	
}
