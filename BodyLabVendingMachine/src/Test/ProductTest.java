package Test;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import Controller.CannotFindException;
import Controller.CtrProduct;
import Infrastructure.CtrProductIF;
import Model.Product;

public class ProductTest {
	CtrProductIF ctrProduct;
	public ProductTest() {
		ctrProduct = new CtrProduct();
	}
	
	@Test
	public void ctrProductIdTest() {
		Product product = null;
		try {
			product = ctrProduct.findProductById(1);
		} catch (CannotFindException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertEquals(1, product.getId());
	}
	
	@Test(expected = CannotFindException.class)
	public void ctrWrongProductIdTest() throws CannotFindException {
		ctrProduct.findProductById(10000000);

	}
	

}
