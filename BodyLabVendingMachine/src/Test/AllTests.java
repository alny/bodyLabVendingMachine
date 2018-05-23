package Test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(value=Suite.class)
@Suite.SuiteClasses(value={
	CustomerTest.class,
	LoanTest.class,
	ProductTest.class,
	SaleTest.class,
	VendingMachineTest.class,
})
public class AllTests {

}
