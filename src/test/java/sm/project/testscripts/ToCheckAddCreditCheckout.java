package sm.project.testscripts;

import static org.testng.Assert.assertTrue;

import org.openqa.selenium.remote.server.handler.ClickElement;
import org.testng.SkipException;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.LogStatus;

import config.FrameworkException;
import config.TestSetup;
import reusablecomponents.BusinessComponents;
import reusablecomponents.TechnicalComponents;
import reusablecomponents.Utilities;

/**
 * Test Class to validate team checkout.
 * 
 * @author jypsy
 *
 */
public class ToCheckAddCreditCheckout extends BusinessComponents {

	/**
	 * JavaDoc
	 */
	@Test(dataProvider = "AddCreditCheckout", dataProviderClass = data.TestData.class)
	public void AddCreditCheckout(String testdesc, String password, String complexity, String Firstname, String Lastname,
			String Country, String PostalCode, String Billing_Email, String CardType, String Additional_SeatCount,
			String PlanName, String PaymentType, String FlowType, String PlanNameDetails, String Frequency,
			String AutoRenew, String NextBillingAmount, String TaxStatus, String InvoicePaymentType,
			String PlanDescription, String PayNow, String TotalSeatCount) {
		if (toBeTested) {
			try {
				navigatetoUrl(Utilities.getProperty("ENVIRONMENT_URL"));
				verify_Redirection("homepage");
				switch (FlowType) {
				case "signup":
					clickOnSignUpLink("homepage");
					verify_Redirection("signup");
					String newUser = "automation" + Utilities.randomNum();
					enterSignUpDetails(newUser, newUser + "1", Billing_Email, Firstname, Lastname);
					verify_Redirection("profiledefault");
					click_CrossIcon();
					break;
				case "login":
					clickOnLoginLink("homepage");
					verify_Redirection("login");
					loginToApp("automation20191126_152753", "automation20191126_1527531");
				default:
					break;
				}
				verify_Redirection("dashboard");
				clickLink("transactionHistoryPage");
				verify_Redirection("transactionhistory");
				clickButton("AddCreditHistioryPage");
				verify_Redirection("billingPwCreditsInvoice");
				enterAddCreditBillingDetails(PostalCode);
				verify_Redirection("CreditConfirmationPageWithoutPay");
				clickButton("PayNow");
				verify_Redirection("billingInvoiceCheckoutforAudiencecredit");
				enterAddCreditPaymentDetails();
				clickButton("ConfirmOnInvoice");
				verify_Redirection("BillingPWCreditConfirmationPage");
				clickLink("signOut");
				verify_Redirection("homepage");
				
			} catch (FrameworkException e) {

				logger.log(LogStatus.FAIL, e.getMessage() + logger.addScreenCapture(screenshot(driver)));
				logger.log(LogStatus.FAIL, "Test Case: " + testdesc + " failed.");
				assertTrue(false, "Test Case: " + testdesc + " failed.");
			}

		} else {
			logger.log(LogStatus.SKIP, "Test Case: " + testdesc + "  skipped.");
			throw new SkipException("Test Case: " + testdesc + "  skipped.");
		}
	}
}