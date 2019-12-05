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
 * Test Class to validate team  checkout.
 * 
 * @author jypsy
 *
 */
public class ToCheckUpgradeFlow extends BusinessComponents {

	/**
	 * JavaDoc
	 */
	@Test(dataProvider = "P2PUpgarde", dataProviderClass = data.TestData.class)
	public void P2PUpgarde(String testdesc, String password, String complexity, String Firstname, String Lastname,
			String Country, String PostalCode, String Billing_Email, String CardType, String Additional_SeatCount,
			String PlanName, String PaymentType, String FlowType, String PlanNameDetails, String Frequency,
			String AutoRenew, String NextBillingAmount, String TaxStatus, String InvoicePaymentType,
			String PlanDescription, String PayNow, String TotalSeatCount, String UpgradedPlanName) {
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
					loginToApp("automation20190412_193929", "automation20190412_1939291");
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
				EnterBillingDetails(Firstname, Lastname, Country, PostalCode, Billing_Email);
				EnterPaymentDetails(PaymentType, CardType);
				AddAdditionalUser("billingcheckout", Additional_SeatCount);
				String ActualTotalAmount = PlanAmount("billingcheckout");
				clickConfirmButton();
				verify_Redirection("billingConfirmation");
				clickButton("CreateTeam");
				verify_Redirection("TeamPricingsummary");
				selectPlan(UpgradedPlanName);
				verify_Redirection("upgardePage");
				clickButton("upgradeConfirm");
				verify_Redirection("team");
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
	
	@Test(dataProvider = "P2PUpgradeWithCountryChange", dataProviderClass = data.TestData.class)
	public void P2PUpgradeWithCountryChange(String testdesc, String password, String complexity, String Firstname, String Lastname,
			String Country, String PostalCode, String Billing_Email, String CardType, String Additional_SeatCount,
			String PlanName, String PaymentType, String FlowType, String PlanNameDetails, String Frequency,
			String AutoRenew, String NextBillingAmount, String TaxStatus, String InvoicePaymentType,
			String PlanDescription, String PayNow, String TotalSeatCount, String UpgradedPlanName) {
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
					loginToApp("automation20191128_162653", "automation20191128_1626531");
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
				EnterBillingDetails(Firstname, Lastname, Country, PostalCode, Billing_Email);
				EnterPaymentDetails(PaymentType, CardType);
				AddAdditionalUser("billingcheckout", Additional_SeatCount);
				String ActualTotalAmount = PlanAmount("billingcheckout");
				clickConfirmButton();
				verify_Redirection("billingConfirmation");
				clickLink("billingPage");
				verify_Redirection("billingDetail");
				UpdateCountry();
				clickButton("CreateTeam");
				verify_Redirection("TeamPricingsummary");
				selectPlan(UpgradedPlanName);
				verify_Redirection("upgardePage");
				clickButton("upgradeConfirm");
				verify_Redirection("team");
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
