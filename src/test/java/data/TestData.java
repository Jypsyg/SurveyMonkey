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
	
	@DataProvider(name = "OTCCheckout")
	public static Object[][] OTCCheckout() throws FileNotFoundException, IOException {
		return Utilities.Read_Excel(Utilities.getProperty("TEST_DATA_LOCATION"), "OTCCheckout");
	}
	
	@DataProvider(name = "AddCreditCheckout")
	public static Object[][] AddCreditCheckout() throws FileNotFoundException, IOException {
		return Utilities.Read_Excel(Utilities.getProperty("TEST_DATA_LOCATION"), "AddCreditCheckout");
	}
	
	@DataProvider(name = "P2PUpgarde")
	public static Object[][] P2PUpgarde() throws FileNotFoundException, IOException {
		return Utilities.Read_Excel(Utilities.getProperty("TEST_DATA_LOCATION"), "P2PUpgarde");
	}
	
	@DataProvider(name = "P2PUpgradeWithCountryChange")
	public static Object[][] P2PUpgardeWithCountryChange() throws FileNotFoundException, IOException {
		return Utilities.Read_Excel(Utilities.getProperty("TEST_DATA_LOCATION"), "P2PUpgradeWithCountryChange");
	}
	@DataProvider(name = "OverageInvoice")
	public static Object[][] OverageInvoice() throws FileNotFoundException, IOException {
		return Utilities.Read_Excel(Utilities.getProperty("TEST_DATA_LOCATION"), "OverageInvoice");
	}
	@DataProvider(name = "CheckEditPayment")
	public static Object[][] CheckEditPayment() throws FileNotFoundException, IOException {
		return Utilities.Read_Excel(Utilities.getProperty("TEST_DATA_LOCATION"), "CheckEditPayment");
	}
	@DataProvider(name = "CheckPSD2Checkout")
	public static Object[][] CheckPSD2Checkout() throws FileNotFoundException, IOException {
		return Utilities.Read_Excel(Utilities.getProperty("TEST_DATA_LOCATION"), "CheckPSD2Checkout");
	}
	
	@DataProvider(name = "DirectDebitCheckout")
	public static Object[][] DirectDebitCheckout() throws FileNotFoundException, IOException {
		return Utilities.Read_Excel(Utilities.getProperty("TEST_DATA_LOCATION"), "DirectDebitCheckout");
	}
	@DataProvider(name = "SEPADirectDebitCheckout")
	public static Object[][] SEPADirectDebitCheckout() throws FileNotFoundException, IOException {
		return Utilities.Read_Excel(Utilities.getProperty("TEST_DATA_LOCATION"), "SEPADirectDebitCheckout");
	}
	@DataProvider(name = "BrazilCheckout")
	public static Object[][] BrazilCheckout() throws FileNotFoundException, IOException {
		return Utilities.Read_Excel(Utilities.getProperty("TEST_DATA_LOCATION"), "BrazilCheckout");
	}
	
	
	
}
