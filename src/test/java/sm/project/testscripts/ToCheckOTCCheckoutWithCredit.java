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

public class ToCheckOTCCheckoutWithCredit extends BusinessComponents {

	/**
	 * JavaDoc
	 */
	@Test(dataProvider = "OTCCheckoutwithCredit", dataProviderClass = data.TestData.class)
	public void OTCCheckoutwithCredit(String testdesc, String password, String complexity, String Firstname,
			String Lastname, String Country, String PostalCode, String Billing_Email, String CardType,
			String Additional_SeatCount, String PlanName, String PaymentType, String FlowType, String PlanNameDetails,
			String Frequency, String AutoRenew, String NextBillingAmount, String TaxStatus, String InvoicePaymentType,
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
					loginToApp("automation20190412_193929", "automation20190412_1939291");
				default:
					break;
				}
				verify_Redirection("dashboard");
				clickLink("MySurvey");
				verify_Redirection("homePageLoggedIn");
				createSurveyWithRequiredQuestion();
				clickLink("collectResponse");
				verify_Redirection("collectAdd");
				clickLink("buyResponse");
				verify_Redirection("collectAudience");
				clickButton("proceedToCheckout");
				verify_Redirection("billingOTC");
				String otcURL = driver.getCurrentUrl();
				switchPaymentmethod();
				clickButton("addcredit");
				verify_Redirection("billingPwCreditsInvoice");
				enterOTCCreditDetails(PostalCode);
				clickButton("confirm");
				verify_Redirection("BillingPWCreditConfirmationPage");
				clickButton("PayNow");
				verify_Redirection("billingInvoiceCheckout");
				enterOTCCreditPaymentDetails();
				clickButton("ConfirmOnInvoice");
				verify_Redirection("BillingPWCreditConfirmationPage");
				driver.navigate().to(otcURL);
				verify_Redirection("billingOTC");
				clickButton("upgradeConfirm");
				verify_Redirection("collectAudience");
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

	/**
	 * JavaDoc
	 */
	@Test(dataProvider = "OTCCheckoutwithGBCredit", dataProviderClass = data.TestData.class)
	public void OTCCheckoutwithGBCredit(String testdesc, String password, String complexity, String Firstname,
			String Lastname, String Country, String PostalCode, String Billing_Email, String CardType,
			String Additional_SeatCount, String PlanName, String PaymentType, String FlowType, String PlanNameDetails,
			String Frequency, String AutoRenew, String NextBillingAmount, String TaxStatus, String InvoicePaymentType,
			String PlanDescription, String PayNow, String TotalSeatCount) {
		if (toBeTested) {
			try {
				navigatetoUrl(Utilities.getProperty("ENVIRONMENT_URL"));
				verify_Redirection("homepage");
				Cookie ck = new Cookie("sm_test_geo", "GB");
				driver.manage().addCookie(ck);
				switch (FlowType) {
				case "signup":
					clickOnSignUpLink("homepage");
					verify_Redirection("signup");
					handleCookieBanner();
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
				clickLink("clickMySurveys");
				AdvantagePopupHandle();
				verify_Redirection("pricingsummary");
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
				clickConfirmButton();
				verify_Redirection("billingConfirmation");
				String ActualInvoice = getInvoiceNumber("billingconfirm");
				clickLink("MySurvey");
				verify_Redirection("homePageLoggedIn");
				createSurveyWithRequiredQuestion();
				clickLink("collectResponse");
				verify_Redirection("collectAdd");
				clickLink("buyResponse");
				verify_Redirection("collectAudience");
				clickButton("proceedToCheckout");
				verify_Redirection("billingOTC");
				String otcURL = driver.getCurrentUrl();
				switchPaymentmethod();
				clickButton("addcredit");
				verify_Redirection("billingPwCreditsInvoice");
				enterOTCGBCreditDetails(PostalCode);
				clickButton("confirm");
				verify_Redirection("BillingPWCreditConfirmationPage");
				clickButton("PayNow");
				verify_Redirection("billingInvoiceCheckout");
				enterOTCCreditPaymentDetails();
				clickButton("PayNow");
				verify_Redirection("BillingPWCreditConfirmationPage");
				driver.navigate().to(otcURL);
				verify_Redirection("billingOTC");
				clickButton("upgradeConfirm");
				verify_Redirection("collectAudience");
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
