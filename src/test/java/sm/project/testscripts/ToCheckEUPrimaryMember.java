package sm.project.testscripts;

import static org.testng.Assert.assertTrue;

import org.openqa.selenium.Cookie;
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
public class ToCheckEUPrimaryMember extends BusinessComponents {

	/**
	 * JavaDoc
	 */
	@Test(dataProvider = "EUPrimaryMemberCheck", dataProviderClass = data.TestData.class)
	public void EUTeamMemberCheck(String testdesc, String password, String complexity, String Firstname, String Lastname,
			String Country, String PostalCode, String Billing_Email, String CardType, String Additional_SeatCount,
			String PlanName, String PaymentType, String FlowType, String PlanNameDetails, String Frequency,
			String AutoRenew, String NextBillingAmount, String TaxStatus, String InvoicePaymentType,
			String PlanDescription, String PayNow, String TotalSeatCount) {
		if (toBeTested) {
			try {
				navigatetoUrl(Utilities.getProperty("EU_ENVIRONMENT_URL"));
				verify_Redirection("EUSignIn");
				Cookie ck = new Cookie("sm_test_geo", "US");
				driver.manage().addCookie(ck);
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
					clickButton("clickEUSignIN");			
					verify_Redirection("login");
					loginToApp("qa_idc_eu_39387048-6e1e-4476-9ce2-39ec09dd59c0", "TestMonkey");
				default:
					break;
				}
				verify_Redirection("team");
				clickLink("LoggedInPlansandpricingsummary");	
				verify_Redirection("mpContactSales");
				driver.navigate().back();
				verify_Redirection("dashboard");
				clickLink("billingPage");
				verify_Redirection("billingDetail");
				verifyPlanType(PlanNameDetails);
				VerifyCSMText();
				clickLink("transactionHistoryPage");
				verify_Redirection("transactionhistory");
				VerifyCSMTInvoiceText();
				clickLink("signOut");
				verify_Redirection("EUSignIn");			
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
