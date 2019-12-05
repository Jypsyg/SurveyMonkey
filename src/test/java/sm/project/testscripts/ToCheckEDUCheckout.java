package sm.project.testscripts;

import static org.testng.Assert.assertTrue;

import org.testng.SkipException;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.LogStatus;

import config.FrameworkException;
import config.TestSetup;
import reusablecomponents.BusinessComponents;
import reusablecomponents.TechnicalComponents;
import reusablecomponents.Utilities;

/**
 * Test Class to validate edu checkout.
 * 
 * @author jypsy
 *
 */
public class ToCheckEDUCheckout extends BusinessComponents {

	/**
	 * JavaDoc
	 */
	@Test(dataProvider = "D2PCheckout", dataProviderClass = data.TestData.class)
	public void D2P_EDU_BillingCheckout(String testdesc, String password, String complexity, String Firstname,
			String Lastname, String Country, String PostalCode, String Billing_Email, String CardType,
			String Additional_SeatCount, String PlanName, String PaymentType, String FlowType, String PlanNameDetails,
			String Frequency, String AutoRenew, String NextBillingAmount, String TaxStatus, String InvoicePaymentType,
			String PlanDescription, String PayNow, String TotalSeatCount) {
		if (toBeTested) {
			try {
				navigatetoUrl(Utilities.getProperty("ENVIRONMENT_URL"));
				verify_Redirection("homepage");
				clickLink("LoggedOutPlansandpricingsummary");
				verify_Redirection("pricingsummary");
				clickLink("eduPricing");
				verify_Redirection("edu_pricing");
				SignUpEDUPlan(PlanName);
				verify_Redirection("signup");
				String newUser = "automation" + Utilities.randomNum();
				enterSignUpDetails(newUser, newUser + "1", Billing_Email, Firstname, Lastname);
				verify_Redirection("edu_billingcheckout");
				EnterBillingDetails(Firstname, Lastname, Country, PostalCode, Billing_Email);
				EnterPaymentDetails(PaymentType, CardType);
				AddAdditionalUser("billingcheckout", Additional_SeatCount);
				String ActualTotalAmount = PlanAmount("billingcheckout");
				clickConfirmButton();
				verify_Redirection("profiledefault");
				String ActualInvoice = getInvoiceNumber("profiledefault");
				click_CrossIcon();
				clickLink("billingPage");
				verify_Redirection("billingDetail");
				VerifyBillingDetails("US", PlanNameDetails, Frequency, getDate("annual", "MMM D,yyyy").trim(),AutoRenew, NextBillingAmount, TaxStatus);
				clickLink("transactionHistoryPage");
				verify_Redirection("transactionhistory");
			//	verifyPurchaseActivityDetails("transactionhistory", ActualInvoice, getDate("currentday", "dd-MMM-yy"),						PlanDescription, Additional_SeatCount, "Paid", PayNow, ActualTotalAmount, PlanDescription,
					//	Frequency, TotalSeatCount);
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
	}}

