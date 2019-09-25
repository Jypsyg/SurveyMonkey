/**
 * 
 */
package data;

import java.io.FileNotFoundException;
import java.io.IOException;
import org.testng.annotations.DataProvider;
import reusablecomponents.Utilities;

/**
 * This class is designed to return test data for each test, wherever required.
 * 
 * @author jypsy
 */
public class TestData {

	@DataProvider(name = "loginSheet")
	public static Object[][] login() throws FileNotFoundException, IOException {
		return Utilities.Read_Excel(Utilities.getProperty("TEST_DATA_LOCATION"), "login");
	}

	@DataProvider(name = "billingCheckout")
	public static Object[][] billingCheckout() throws FileNotFoundException, IOException {
		return Utilities.Read_Excel(Utilities.getProperty("TEST_DATA_LOCATION"), "billingCheckout");
	}

	@DataProvider(name = "Invoicecheckout")
	public static Object[][] Invoicecheckout() throws FileNotFoundException, IOException {
		return Utilities.Read_Excel(Utilities.getProperty("TEST_DATA_LOCATION"), "Invoicecheckout");
	}

	@DataProvider(name = "D2PCheckout")
	public static Object[][] D2PCheckout() throws FileNotFoundException, IOException {
		return Utilities.Read_Excel(Utilities.getProperty("TEST_DATA_LOCATION"), "D2PCheckout");
	}

	@DataProvider(name = "TeamCheckout")
	public static Object[][] TeamCheckout() throws FileNotFoundException, IOException {
		return Utilities.Read_Excel(Utilities.getProperty("TEST_DATA_LOCATION"), "TeamCheckout");
	}

	@DataProvider(name = "FlexCheckout")
	public static Object[][] FlexCheckout() throws FileNotFoundException, IOException {
		return Utilities.Read_Excel(Utilities.getProperty("TEST_DATA_LOCATION"), "FlexCheckout");
	}
}
