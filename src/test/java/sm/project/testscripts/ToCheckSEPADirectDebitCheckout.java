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
public class ToCheckSEPADirectDebitCheckout extends BusinessComponents {

	/**
	 * JavaDoc
	 */
	@Test(dataProvider = "SEPADirectDebitCheckout", dataProviderClass = data.TestData.class)
	public void SEPADirectDebitCheckout(String testdesc, String password, String complexity, String Firstname, String Lastname,
			String Country, String PostalCode, String Billing_Email, String CardType, String Additional_SeatCount,
			String PlanName, String PaymentType, String FlowType, String PlanNameDetails, String Frequency,
			String AutoRenew, String NextBillingAmount, String TaxStatus, String InvoicePaymentType,
			String PlanDescription, String PayNow, String TotalSeatCount,String DisclaimerData) {
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
					loginToApp("automation20191202_153331", "automation20191202_1533311");
				default:
					break;
				}
				verify_Redirection("dashboard");
				clickLink("Upgrade");
				verify_Redirection("TeamPricingsummary");
				clickLink("IndividualPricingPage");
				verify_Redirection("individualPricingsummary");
				selectPlan(PlanName);
				verify_Redirection("billingCheckout");
				EnterGBBillingDetails(Firstname, Lastname, Country, Billing_Email);
				EnterPaymentDetails(PaymentType, CardType);
				AddAdditionalUser("billingcheckout", Additional_SeatCount);
				String ActualTotalAmount = PlanAmount("billingcheckout");
				clickButton("Buy");
				verifyandProceedSEPADirectDebitModal();
				clickButton("modalConfirm");	
				verify_Redirection("billingConfirmation");
				String ActualInvoice = getInvoiceNumber("billingconfirm");
				verifyDirectDebitDisclaimer(DisclaimerData);		
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
