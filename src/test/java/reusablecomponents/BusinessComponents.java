package reusablecomponents;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.util.List;
import java.util.UUID;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.springframework.beans.factory.config.ProviderCreatingFactoryBean;

import com.relevantcodes.extentreports.LogStatus;

import config.FrameworkException;
import config.Mappings;
import config.TestSetup;
import net.sourceforge.htmlunit.corejs.javascript.ast.SwitchCase;
import objectrepository.BillingConfirmedPage;
import objectrepository.BillingDetailspage;
import objectrepository.Billing_Checkout;
import objectrepository.Billing_PFI_Invoice_CheckoutPage;
import objectrepository.Dashboard;
import objectrepository.Edu_Pricing;
import objectrepository.HomePage;
import objectrepository.LoginPage;
import objectrepository.MyAccountPage;
import objectrepository.PlansAndPricingSummaryPage;
import objectrepository.PricingAnalyze;
import objectrepository.ProfileDefaultPage;
import objectrepository.SignUpPage;
import objectrepository.TeamPricing;
import objectrepository.TeamSetUp;
import objectrepository.TransactionHistoryPage;

/**
 * Application layer class which have functions specific to Application.
 * 
 * @author Jypsy
 *
 */
public class BusinessComponents extends TechnicalComponents {

	public void loginToApp(String username, String password) {
		LoginPage lp = new LoginPage(driver);
		lp.enterUserName(username);
		lp.enterPassWord(password);
		lp.clickLoginButton();

	}

	public void clickOnLoginLink(String screenname) {
		switch (screenname) {
		case "homepage":
			HomePage hp = new HomePage(driver);
			hp.click_LoginLink();
			break;

		default:

			break;
		}

	}

	public void clickOnSignUpLink(String screenname) {
		switch (screenname) {
		case "homepage":
			HomePage hp = new HomePage(driver);
			hp.click_SignupLink();

			break;

		default:

			break;
		}

	}

	public void enterSignUpDetails(String username, String password, String email, String firstname, String LastName) {
		SignUpPage sp = new SignUpPage(driver);
		sp.enterSignUpformsDetails(username, password, email, firstname, LastName);
		sp.clickCreatAccountbtn();
		logger.log(LogStatus.PASS, "Account created successfully " + username + " : " + password);
	}

	public void verify_Redirection(String ScreenName) {
		try {
			boolean redirectionsuccess = false;
			switch (ScreenName) {
			case "homepage":
				HomePage hp = new HomePage(driver);
				redirectionsuccess = hp.isPageOpened();
				logger.log(LogStatus.PASS, "Page successfully redirected " + ScreenName);

				break;
			case "login":
				LoginPage lp = new LoginPage(driver);
				redirectionsuccess = lp.isPageOpened();
				break;
			case "dashboard":
				Dashboard dp = new Dashboard(driver);
				redirectionsuccess = dp.isPageOpened();
				break;
			case "signup":
				SignUpPage sp = new SignUpPage(driver);
				redirectionsuccess = sp.isPageOpened();
				break;
			case "profiledefault":
				ProfileDefaultPage pd = new ProfileDefaultPage(driver);
				redirectionsuccess = pd.isPageOpened();
				break;
			case "pricingsummary":
				PlansAndPricingSummaryPage ps = new PlansAndPricingSummaryPage(driver);
				redirectionsuccess = ps.isPageOpened();
				break;
			case "billingCheckout":
				Billing_Checkout br = new Billing_Checkout(driver);
				redirectionsuccess = br.isPageOpened();
				break;
			case "billingConfirmation":
				BillingConfirmedPage bc = new BillingConfirmedPage(driver);
				redirectionsuccess = bc.isPageOpened();
				break;
			case "billingDetail":
				BillingDetailspage bd = new BillingDetailspage(driver);
				redirectionsuccess = bd.isPageOpened();
				break;
			case "transactionhistory":
				TransactionHistoryPage tr = new TransactionHistoryPage(driver);
				redirectionsuccess = tr.isPageOpened();
				break;
			case "invoice_PFI":
				Billing_PFI_Invoice_CheckoutPage bp = new Billing_PFI_Invoice_CheckoutPage(driver);
				redirectionsuccess = bp.isPageOpened();
				break;
			case "edu_pricing":
				Edu_Pricing eu = new Edu_Pricing(driver);
				redirectionsuccess = eu.isPageOpened();
				break;
			case "edu_billingcheckout":
				Billing_Checkout er = new Billing_Checkout(driver);
				redirectionsuccess = er.isEDUBillingPageOpened();
				break;
			case "teamsetup":
				TeamSetUp t2 = new TeamSetUp(driver);
				redirectionsuccess = t2.isPageOpened();
				break;
			case "pricingAnalyze":
				PricingAnalyze pr = new PricingAnalyze(driver);
				redirectionsuccess = pr.isPageOpened();
				break;
			default:
				throw new FrameworkException("redirection verification not configure " + ScreenName);
			}
			if (redirectionsuccess) {
				logger.log(LogStatus.PASS, "Page successfully redirected " + ScreenName);
				logger.log(LogStatus.INFO, logger.addScreenCapture(screenshot(driver)));
			} else {
				logger.log(LogStatus.FAIL, "Page not successfully redirected " + ScreenName);
				logger.log(LogStatus.INFO, logger.addScreenCapture(screenshot(driver)));
			}

		} catch (Exception e) {
			// TODO: handle exception
		}

	}

	public void VerifyBillingDetails(String Locale, String PlanName, String Frequncy, String NextBillingDate,
			String AutoRenew, String NextBillingAmount, String TaxStatus) {
		BillingDetailspage bd = new BillingDetailspage(driver);
		bd.verifyPlanType(PlanName);
		bd.verifyBillingFrequency(Frequncy);
		bd.verifyNextBillingDate(NextBillingDate);
		bd.verifyAutorenew(AutoRenew);
		bd.verifyBillingNextAmount(NextBillingAmount);
		switch (Locale) {
		case "US":
			bd.verifyTaxStatus(TaxStatus);
			break;
		default:
			break;
		}
		logger.log(LogStatus.PASS, "Billing Details verified successfully " + PlanName + " : " + Frequncy + ":"
				+ NextBillingDate + ":" + AutoRenew + ":" + NextBillingAmount + " :" + TaxStatus);

	}

	public void verifyError(String ScreenName, String errormsg) {

		switch (ScreenName) {
		case "login":
			LoginPage lp = new LoginPage(driver);
			lp.verifyErrorMsg(errormsg);
			break;

		default:

			break;
		}
		logger.log(LogStatus.PASS, "Error Msg Verified Succesfully" + ScreenName);
		logger.log(LogStatus.INFO, logger.addScreenCapture(screenshot(driver)));

	}

	public void click_CrossIcon() {
		ProfileDefaultPage pd = new ProfileDefaultPage(driver);
		pd.click_CrossIcon();
	}

	public void verifyTableContent(String TableName, String invoice, String BillingDate, String Description,
			String Seats, String Status, String PayNow, String Amount) {
		TransactionHistoryPage tp = new TransactionHistoryPage(driver);
		switch (TableName) {
		case "purchase activity":

			tp.verifyPurcahseActivityDetails(invoice, BillingDate, Description, Seats, Status, PayNow, Amount);
			logger.log(LogStatus.PASS, "Table data verified Succesfully" + TableName);

			break;
		case "purchase team activity":
			tp.verifyPurcahseTeamActivityDetails(invoice, BillingDate, Description, Seats, Status, PayNow, Amount);
			logger.log(LogStatus.PASS, "Table data verified Succesfully" + TableName);
			break;
		default:
			logger.log(LogStatus.INFO, "No valid table found" + TableName);
			break;
		}
	}

	public void selectPlan(String PlanName) {
		PlansAndPricingSummaryPage ps = new PlansAndPricingSummaryPage(driver);
		switch (PlanName.toLowerCase()) {

		case "standardmonthly":
			ps.clickMonthlyPlan();
			logger.log(LogStatus.PASS, "Plan name  clicked Succesfully" + PlanName);

			break;
		case "standardannual":
			ps.clickStandardAnnualPlan();
			logger.log(LogStatus.PASS, "Plan name  clicked Succesfully" + PlanName);

			break;
		case "advantage":
			ps.clickAdvantagePlan();
			logger.log(LogStatus.PASS, "Plan name  clicked Succesfully" + PlanName);
			logger.log(LogStatus.PASS, "Plan name  clicked Succesfully" + PlanName);

			break;
		case "premier":
			ps.clickPremierPlan();
			logger.log(LogStatus.PASS, "Plan name  clicked Succesfully" + PlanName);
			break;
		case "teampremier":
			TeamPricing tr = new TeamPricing(driver);
			tr.clickTeamPremierPlan();
			logger.log(LogStatus.PASS, "Plan name  clicked Succesfully" + PlanName);
			break;
		case "teamadvantage":
			TeamPricing tr1 = new TeamPricing(driver);
			tr1.clickTeamAdvantagePlan();
			logger.log(LogStatus.PASS, "Plan name  clicked Succesfully" + PlanName);
			break;
		default:
			logger.log(LogStatus.INFO, "No valid plan name found" + PlanName);
		}

	}

	public void SignUpEDUPlan(String PlanName) {
		Edu_Pricing eu = new Edu_Pricing(driver);
		switch (PlanName.toLowerCase()) {

		case "standardmonthly":
			eu.clickEdUMonthlyPlan();
			logger.log(LogStatus.PASS, "Plan name  clicked Succesfully" + PlanName);

			break;
		case "standardannual":
			eu.clickEDUStandardAnnualPlan();
			logger.log(LogStatus.PASS, "Plan name  clicked Succesfully" + PlanName);

			break;
		case "advantage":
			eu.clickEDUAdvantagePlan();
			logger.log(LogStatus.PASS, "Plan name  clicked Succesfully" + PlanName);
			logger.log(LogStatus.PASS, "Plan name  clicked Succesfully" + PlanName);

			break;
		case "premier":
			eu.clickEDUPremierPlan();
			logger.log(LogStatus.PASS, "EDU Plan name  clicked Succesfully" + PlanName);

			break;
		default:
			logger.log(LogStatus.INFO, "No valid plan name found" + PlanName);
		}

	}

	public void signUpPlan(String PlanName) {
		PlansAndPricingSummaryPage ps = new PlansAndPricingSummaryPage(driver);
		switch (PlanName.toLowerCase()) {

		case "standardmonthly":
			ps.signupMonthlyPlan();
			logger.log(LogStatus.PASS, "Plan name  signup Succesfully" + PlanName);

			break;
		case "standardannual":
			ps.signupStandardAnnualPlan();
			logger.log(LogStatus.PASS, "Plan name  signup Succesfully" + PlanName);

			break;
		case "advantage":
			ps.signupAdvantagePlan();

			logger.log(LogStatus.PASS, "Plan name sign up  Succesfully" + PlanName);

			break;
		case "premier":
			ps.signupPremierPlan();
			logger.log(LogStatus.PASS, "Plan name  signup Succesfully" + PlanName);

			break;
		default:
			logger.log(LogStatus.INFO, "No valid plan name found" + PlanName);
		}

	}

	public void clickLink(String LinkName) {
		MyAccountPage mc = new MyAccountPage(driver);
		switch (LinkName) {
		case "plansandpricingsummary":
			Dashboard d = new Dashboard(driver);
			d.clickPlansAndPricing();
			logger.log(LogStatus.PASS, "clicked Succesfully" + LinkName);

			break;
		case "billingPage":
			mc.clickBillingDetails();
			logger.log(LogStatus.PASS, "clicked Succesfully" + LinkName);
			break;
		case "IndividualPricingPage":
			PlansAndPricingSummaryPage pd = new PlansAndPricingSummaryPage(driver);
			pd.clickIndividualPlans();
			logger.log(LogStatus.PASS, "Individual link clciked  Succesfully" + LinkName);
			break;
		case "transactionHistoryPage":
			mc.clickTransactionHistory();
			logger.log(LogStatus.PASS, "clicked Succesfully" + LinkName);
			break;
		case "refreAFriend":
			mc.clickReferAFriend();
			logger.log(LogStatus.PASS, "clicked Succesfully" + LinkName);
			break;
		case "signOut":
			HomePage hp = new HomePage(driver);
			hp.click_LogOut();
			logger.log(LogStatus.PASS, "clicked Succesfully" + LinkName);

			break;
		case "eduPricing":
			PlansAndPricingSummaryPage pr = new PlansAndPricingSummaryPage(driver);
			pr.clickeduPlans();
			logger.log(LogStatus.PASS, "clicked Succesfully" + LinkName);
			break;

		case "pricingAnalyze":
			BillingDetailspage br = new BillingDetailspage(driver);
			br.clickPricingAnalyzeLink();
			logger.log(LogStatus.PASS, "clicked Succesfully" + LinkName);
			break;
		case "mySurvey":
			Dashboard de = new 	Dashboard(driver);
			de.clickMySurveys();
			logger.log(LogStatus.PASS, "clicked Succesfully" + LinkName);
			break;
		default:
			logger.log(LogStatus.INFO, "No link found" + LinkName);
			break;
		}

	}

	public void AdvantagePopupHandle() {
		PlansAndPricingSummaryPage ps = new PlansAndPricingSummaryPage(driver);
		ps.handleUpgradePopup();

	}

	public void verifyPurchaseActivityDetails(String PageName, String invoice, String BillingDate, String Description,
			String Seats, String Status, String PayNow, String Amount, String PlanName, String BillingFrequency,
			String Quantity) {
		try {
			switch (PageName) {
			case "transactionhistory":
				TransactionHistoryPage tr = new TransactionHistoryPage(driver);
				tr.verifyPurcahseActivityDetails(invoice, BillingDate, Description, Seats, Status, PayNow, Amount);
				logger.log(LogStatus.PASS,
						"Transaction History details verified  Succesfully" + PageName + " : " + invoice + " : "
								+ BillingDate + " : " + Description + " : " + Seats + " : " + Status + " : " + PayNow
								+ " : " + Amount);
				logger.log(LogStatus.INFO, logger.addScreenCapture(screenshot(driver)));

				break;
			case "billing_pfi_invoice":
				Billing_PFI_Invoice_CheckoutPage pi = new Billing_PFI_Invoice_CheckoutPage(driver);
				pi.verifyReviewAndConfirmSection(PlanName, BillingFrequency, Quantity, Amount);
				logger.log(LogStatus.PASS,
						"Billing PFI details verified  Succesfully" + PageName + " : " + invoice + " : " + BillingDate
								+ " : " + Description + " : " + Seats + " : " + Status + " : " + PayNow + " : "
								+ Amount);
				logger.log(LogStatus.INFO, logger.addScreenCapture(screenshot(driver)));

			default:
				break;
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	public void EnterBillingDetails(String FirstName, String LastName, String Country, String PostalCode,
			String Email) {
		Billing_Checkout br = new Billing_Checkout(driver);

		br.enter_BillingCheckoutDetails(FirstName, LastName, Country, PostalCode, Email);
		br.clickBillingDetailsNext();

	}

	public void EnterPaymentDetails(String PaymentType, String Cardtype) {
		Billing_Checkout br = new Billing_Checkout(driver);
		try {
			boolean PaymentDetailsEntered = false;
			switch (PaymentType) {
			case "creditcard":
				br.SelectPaymentMethod(PaymentType);
				br.enterPaymentDetails(Cardtype);
				br.clickBillingPaymentNext();
				PaymentDetailsEntered = true;
				break;
			case "invoice":
				br.SelectPaymentMethod(PaymentType);
				br.clickBillingPaymentNext();
				PaymentDetailsEntered = true;
				break;
			case "invoicePFI":
				br.enterPaymentDetails(Cardtype);
				PaymentDetailsEntered = true;

				break;

			default:
				break;
			}
			if (PaymentDetailsEntered) {
				logger.log(LogStatus.PASS, "paymentd details entered successfully  " + PaymentType);
			} else {
				logger.log(LogStatus.FAIL, "payment details not enetred selected  " + PaymentType);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}

	}

	public void EnterInvoicePFIPaymentDetails() {
		try {
			Billing_PFI_Invoice_CheckoutPage bi = new Billing_PFI_Invoice_CheckoutPage(driver);
			bi.enterCardName("First, last");
			bi.enterCardNumber("4111111111111111");
			bi.enterExpiryMonth("10");
			bi.enterExpiryYear("2020");
			bi.enterCVV("737");
		} catch (Exception e) {
			// TODO: handle exception
		}

	}

	public void verifyReviewAndConfirmDetails(String PlanName, String BillingFrequency, String Quantity,
			String Amount) {
		try {
			Billing_PFI_Invoice_CheckoutPage bi = new Billing_PFI_Invoice_CheckoutPage(driver);
			bi.verifyReviewAndConfirmSection(PlanName, BillingFrequency, Quantity, Amount);
		} catch (Exception e) {
			// TODO: handle exception
		}

	}

	public void AddAdditionalUser(String PageName, String NumOfSeats) {
		Billing_Checkout br = new Billing_Checkout(driver);
		try {
			boolean UsersEntered = false;
			switch (PageName) {
			case "billingcheckout":
				br.addUsers(NumOfSeats);
				UsersEntered = true;
				break;

			default:
				break;
			}
			if (UsersEntered) {
				logger.log(LogStatus.PASS, "additional users added successfully  " + PageName);
			} else {
				logger.log(LogStatus.FAIL, "additional users not enetred   " + PageName);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}

	}

	public void clickConfirmButton() {

		Billing_Checkout br = new Billing_Checkout(driver);
		br.clickConfirmButton();

	}

	public void clickPFIConfirmButton() {
		Billing_PFI_Invoice_CheckoutPage bf = new Billing_PFI_Invoice_CheckoutPage(driver);
		bf.clickPFIConfirmButton();
	}

	public String getInvoiceNumber(String PageName) {
		String InvoiceNumber;
		switch (PageName) {

		case "billingconfirm":
			BillingConfirmedPage br = new BillingConfirmedPage(driver);
			InvoiceNumber = br.getInvoice();
			return InvoiceNumber;
		case "invoicePFI":
			Billing_PFI_Invoice_CheckoutPage bf = new Billing_PFI_Invoice_CheckoutPage(driver);
			InvoiceNumber = bf.getInvoiceNumber();
			return InvoiceNumber;
		case "teamsetup":
			TeamSetUp tr = new TeamSetUp(driver);
			InvoiceNumber = tr.getInvoice();
			return InvoiceNumber;
		case "profiledefault":
			try {
				ProfileDefaultPage pr = new ProfileDefaultPage(driver);
				InvoiceNumber = pr.getInvoiceNumber();
				return InvoiceNumber;
			} catch (Exception e) {
				// TODO: handle exception
			}

		default:
			break;
		}
		return PageName;

	}

	public String PlanAmount(String PageName) {
		String TotalAmount = "";
		switch (PageName) {

		case "billingcheckout":
			Billing_Checkout br = new Billing_Checkout(driver);
			TotalAmount = br.getTotalAmount();

			break;

		default:
			break;
		}
		return TotalAmount;
	}

	public void PFIConfirmButton() {
		Billing_PFI_Invoice_CheckoutPage br = new Billing_PFI_Invoice_CheckoutPage(driver);
		br.clickPFIConfirmButton();

	}

	public void verifyEditContactDetails() {
		BillingDetailspage br = new BillingDetailspage(driver);
		br.clickEditContactButton();
		br.enterContactDetails();
		br.verifyContactDetails();
	}

	public void getFlexPackageIdOnURL() {

		String id;
		String href = TechnicalComponents.getCurrentURL();
		String[] recordID = href.split("=");
		String newURL = recordID[0] + recordID[1] + "=37";
		driver.get(newURL);

	}

	public void getPlanOnCheckoutPage(String Package_id) {

	}

}
