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
 * Test Class to validate billing checkout .
 * 
 * @author jypsy
 *
 */
public class ToCheckBillingCheckout extends BusinessComponents {

	/**
	 * JavaDoc
	 */
	@Test(dataProvider = "billingCheckout", dataProviderClass = data.TestData.class)
	public void B2PBillingCheckout(String testdesc, String password, String complexity, String Firstname,
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
				clickLink("clickMySurveys");
				AdvantagePopupHandle();
				verify_Redirection("pricingsummary");
				clickLink("IndividualPricingPage");
				selectPlan(PlanName);
				verify_Redirection("billingCheckout");
				EnterBillingDetails(Firstname, Lastname, Country, PostalCode, Billing_Email);
				EnterPaymentDetails(PaymentType, CardType);
				AddAdditionalUser("billingcheckout", Additional_SeatCount);
				String ActualTotalAmount = PlanAmount("billingcheckout");
				clickConfirmButton();
				verify_Redirection("billingConfirmation");
				String ActualInvoice = getInvoiceNumber("billingconfirm");
				clickLink("billingPage");
				verify_Redirection("billingDetail");
				VerifyBillingDetails("US", PlanNameDetails, Frequency, getDate("annual", "MMM D,yyyy").trim(),
						AutoRenew, NextBillingAmount, TaxStatus);
				clickLink("transactionHistoryPage");
				verify_Redirection("transactionhistory");
				verifyPurchaseActivityDetails("transactionhistory", ActualInvoice, getDate("currentday", "dd-MMM-yy"),
						PlanDescription, Additional_SeatCount, "Paid", PayNow, ActualTotalAmount, PlanDescription,
						Frequency, TotalSeatCount);
				clickLink("signOut");
				verify_Redirection("homepage");
				//OutlookEmail("Jypsyg@surveymonkey.com","Jazz$123",)

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

	@Test(dataProvider = "D2PCheckout", dataProviderClass = data.TestData.class)
	public void D2PBillingCheckout(String testdesc, String password, String complexity, String Firstname,
			String Lastname, String Country, String PostalCode, String Billing_Email, String CardType,
			String Additional_SeatCount, String PlanName, String PaymentType, String FlowType, String PlanNameDetails,
			String Frequency, String AutoRenew, String NextBillingAmount, String TaxStatus, String InvoicePaymentType,
			String PlanDescription, String PayNow, String TotalSeatCount) {
		if (toBeTested) {
			try {
				navigatetoUrl(Utilities.getProperty("ENVIRONMENT_URL"));
				verify_Redirection("homepage");
				clickLink("plansandpricingsummary");
				verify_Redirection("pricingsummary");
				clickLink("IndividualPricingPage");
				AdvantagePopupHandle();
				signUpPlan(PlanName);
				verify_Redirection("signup");
				String newUser = "automation" + Utilities.randomNum();
				enterSignUpDetails(newUser, newUser + "1", Billing_Email, Firstname, Lastname);
				// verify_Redirection("billingCheckout");
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
				VerifyBillingDetails("US", PlanNameDetails, Frequency, getDate("annual", "MMM D,yyyy").trim(),
						AutoRenew, NextBillingAmount, TaxStatus);
				clickLink("transactionHistoryPage");
				verify_Redirection("transactionhistory");
				verifyPurchaseActivityDetails("transactionhistory", ActualInvoice, getDate("currentday", "dd-MMM-yy"),
						PlanDescription, Additional_SeatCount, "Paid", PayNow, ActualTotalAmount, PlanDescription,
						Frequency, TotalSeatCount);
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

	@Test(dataProvider = "Invoicecheckout", dataProviderClass = data.TestData.class)
	public void InvoiceBillingCheckout(String testdesc, String password, String complexity, String Firstname,
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
					loginToApp("automation20190411_093813", "automation20190411_0938131");
				default:
					break;
				}

				verify_Redirection("dashboard");
				clickLink("plansandpricingsummary");
				AdvantagePopupHandle();
				verify_Redirection("pricingsummary");
				// clickLink("plansandpricingsummary");
				clickLink("IndividualPricingPage");
				selectPlan(PlanName);
				verify_Redirection("billingCheckout");
				EnterBillingDetails(Firstname, Lastname, Country, PostalCode, Billing_Email);
				EnterPaymentDetails(PaymentType, CardType);
				AddAdditionalUser("billingcheckout", Additional_SeatCount);
				String ActualTotalAmount = PlanAmount("billingcheckout");
				clickConfirmButton();
				verify_Redirection("invoice_PFI");
				String ActualInvoice = getInvoiceNumber("invoicePFI");
				try {
					verifyReviewAndConfirmDetails("Advantage Annual Plan",
							getDate("currentday", "MMM d, yyyy") + " - "
									+ getDate("previousDateWithNextYear", "MMM dd, yyyy"),
							TotalSeatCount, ActualTotalAmount);
					EnterInvoicePFIPaymentDetails();
				} catch (Exception e) {
					// TODO: handle exception
				}

				PFIConfirmButton();
				verify_Redirection("billingConfirmation");
				clickLink("billingPage");
				verify_Redirection("billingDetail");
				VerifyBillingDetails("US", PlanNameDetails, Frequency, getDate("annual", "MMM D,yyyy").trim(),
						AutoRenew, NextBillingAmount, TaxStatus);
				clickLink("transactionHistoryPage");
				verify_Redirection("transactionhistory");
				verifyPurchaseActivityDetails("transactionhistory", ActualInvoice, getDate("currentday", "MMM DD,yyyy"),
						PlanDescription, Additional_SeatCount, "Paid", PayNow, ActualTotalAmount, PlanDescription,
						Frequency, TotalSeatCount);
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

	@Test(dataProvider = "Invoicecheckout", dataProviderClass = data.TestData.class)
	public void EditBillingDetails(String testdesc, String password, String complexity, String Firstname,
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
					loginToApp("automation20190411_093813", "automation20190411_0938131");
				default:
					break;
				}

				verify_Redirection("dashboard");
				clickLink("plansandpricingsummary");
				AdvantagePopupHandle();
				verify_Redirection("pricingsummary");
				// clickLink("plansandpricingsummary");
				clickLink("IndividualPricingPage");
				selectPlan(PlanName);
				verify_Redirection("billingCheckout");
				EnterBillingDetails(Firstname, Lastname, Country, PostalCode, Billing_Email);
				EnterPaymentDetails(PaymentType, CardType);
				AddAdditionalUser("billingcheckout", Additional_SeatCount);
				String ActualTotalAmount = PlanAmount("billingcheckout");
				clickConfirmButton();
				verify_Redirection("invoice_PFI");
				String ActualInvoice = getInvoiceNumber("invoicePFI");
				try {
					verifyReviewAndConfirmDetails("Advantage Annual Plan",
							getDate("currentday", "MMM d, yyyy") + " - "
									+ getDate("previousDateWithNextYear", "MMM dd, yyyy"),
							TotalSeatCount, ActualTotalAmount);
					EnterInvoicePFIPaymentDetails();
				} catch (Exception e) {
					// TODO: handle exception
				}

				PFIConfirmButton();
				verify_Redirection("billingConfirmation");
				clickLink("billingPage");
				verify_Redirection("billingDetail");
				verifyEditContactDetails();
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
