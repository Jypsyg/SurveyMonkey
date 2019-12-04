package reusablecomponents;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.util.List;
import java.util.UUID;

import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.server.handler.SendKeys;
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
import objectrepository.BillingInvoice;
import objectrepository.BillingOTC;
import objectrepository.BillingOTCSuccessful;
import objectrepository.BillingPWCreditConfirmationPage;
import objectrepository.Billing_Checkout;
import objectrepository.Billing_InvoiceCheckoutPage;
import objectrepository.Billing_PFI_Invoice_CheckoutPage;
import objectrepository.CollectAddPage;
import objectrepository.CollectAudience;
import objectrepository.CreatePage;
import objectrepository.CreditConfirmationPageWithoutPay;
import objectrepository.Dashboard;
import objectrepository.Edu_Pricing;
import objectrepository.HomePage;
import objectrepository.HomePageLoggedIn;
import objectrepository.IndividualPlansAndPricingSummaryPage;
import objectrepository.LoginPage;
import objectrepository.MyAccountPage;
import objectrepository.PlansAndPricingSummaryPage;
import objectrepository.ProfileDefaultPage;
import objectrepository.SignUpPage;
import objectrepository.TeamAdd;
import objectrepository.TeamPage;
import objectrepository.TeamPricing;
import objectrepository.TeamSetUp;
import objectrepository.TransactionHistoryPage;
import objectrepository.UpgradePage;
import objectrepository.billingPwCreditsInvoice;
import objectrepository.BillingOTCSuccessful;

/**
 * Application layer class which have functions specific to Application.
 * 
 * @author Jypsy
 *
 */
public class BusinessComponents extends TechnicalComponents {

	private static final Keys TAB = null;

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
			case "individualPricingsummary":
				IndividualPlansAndPricingSummaryPage ip = new IndividualPlansAndPricingSummaryPage(driver);
				redirectionsuccess = ip.isPageOpened();
				break;
			case "TeamPricingsummary":
				PlansAndPricingSummaryPage ps1 = new PlansAndPricingSummaryPage(driver);
				redirectionsuccess = ps1.isPageOpened();
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
			case "homePageLoggedIn":
				HomePageLoggedIn hpl = new HomePageLoggedIn(driver);
				redirectionsuccess = hpl.isPageOpened();
				break;
			case "create":
				CreatePage cp = new CreatePage(driver);
				redirectionsuccess = cp.isPageOpened();
				break;
			case "collectAdd":
				CollectAddPage ca = new CollectAddPage(driver);
				redirectionsuccess = ca.isPageOpened();
				break;
			case "collectAudience":
				CollectAudience cr = new CollectAudience(driver);
				redirectionsuccess = cr.isPageOpened();
				break;
			case "billingOTC":
				BillingOTC bo = new BillingOTC(driver);
				redirectionsuccess = bo.isPageOpened();
				break;
			case "BillingOTCSuccessful":
				BillingOTCSuccessful bos = new BillingOTCSuccessful(driver);
				redirectionsuccess = bos.isPageOpened();
				break;
			case "billingPwCreditsInvoice":
				billingPwCreditsInvoice ba = new billingPwCreditsInvoice(driver);
				redirectionsuccess = ba.isPageOpened();
				break;
			case "CreditConfirmationPageWithoutPay":
				CreditConfirmationPageWithoutPay ba1 = new CreditConfirmationPageWithoutPay(driver);
				redirectionsuccess = ba1.isPageOpened();
				break;
			case "billingInvoiceCheckout":
				Billing_InvoiceCheckoutPage bp1 = new Billing_InvoiceCheckoutPage(driver);
				redirectionsuccess = bp1.isPageOpened();
				break;
			case "BillingPWCreditConfirmationPage":
				BillingPWCreditConfirmationPage bp2 = new BillingPWCreditConfirmationPage(driver);
				redirectionsuccess = bp2.isPageOpened();
				break;
			case "upgardePage":
				UpgradePage up = new UpgradePage(driver);
				redirectionsuccess = up.isPageOpened();
				break;
			case "team":
				TeamPage tp = new TeamPage(driver);
				redirectionsuccess = tp.isPageOpened();
				break;
			case "teamadd":
				TeamAdd ta = new TeamAdd(driver);
				redirectionsuccess = ta.isPageOpened();
				break;
			case "billinginvoice":
				BillingInvoice bi = new BillingInvoice(driver);
				redirectionsuccess = bi.isPageOpened();
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
		// bd.verifyNextBillingDate(NextBillingDate);
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
		case "LoggedOutPlansandpricingsummary":
			Dashboard d = new Dashboard(driver);
			d.clickPlansAndPricing();
			logger.log(LogStatus.PASS, "clicked Succesfully" + LinkName);

			break;
		case "LoggedInPlansandpricingsummary":
			Dashboard dloggedIn = new Dashboard(driver);
			dloggedIn.clickLoggedInPlansAndPricing();
			logger.log(LogStatus.PASS, "clicked Succesfully" + LinkName);

			break;
		case "Upgrade":
			Dashboard d1 = new Dashboard(driver);
			d1.click_upgrade();
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

		case "MySurvey":
			Dashboard s = new Dashboard(driver);
			s.click_MySurvey();
			logger.log(LogStatus.PASS, "clicked Succesfully" + LinkName);
			break;
		case "collectResponse":
			CreatePage cp = new CreatePage(driver);
			cp.clickCollectResponse();
			logger.log(LogStatus.PASS, "clicked Succesfully" + LinkName);
			break;
		case "buyResponse":
			CollectAddPage ca = new CollectAddPage(driver);
			ca.clickBuyAudience();
			logger.log(LogStatus.PASS, "clicked Succesfully" + LinkName);
			break;
		case "myteam":
			mc.clickBillingDetails();		
			verify_Redirection("billingDetail");
			mc.clickMyTeam();
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

	public void EnterGBBillingDetails(String FirstName, String LastName, String Country, String Email) {
		Billing_Checkout br = new Billing_Checkout(driver);

		br.enterGBBillingCheckoutDetails(FirstName, LastName, Country, Email);
		br.clickBillingDetailsNext();

	}
	
	
	public void enterBrazilBillingCheckoutDetails(String FirstName, String LastName, String Country, String PostalCode,String Email) {
		Billing_Checkout br = new Billing_Checkout(driver);

		br.enterBrazilBillingCheckoutDetails(FirstName, LastName, Country, PostalCode ,Email);
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
			case "PSD2creditcard":
				br.SelectPaymentMethod(PaymentType);
				br.enterPSD2PaymentDetails(Cardtype);
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
			case "directdebit":
				br.SelectPaymentMethod(PaymentType);
				br.verifyDirectDebitPaymentErroMessage();
				br.enterDirectDebitPaymentDetails();
				br.clickBillingPaymentNext();
				PaymentDetailsEntered = true;

				break;
			case "sepadirectdebit":
				br.SelectPaymentMethod(PaymentType);
				br.verifySEPADirectDebitPaymentErroMessage();
				br.enterSEPADirectDebitPaymentDetails();
				br.clickBillingPaymentNext();
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

	public void UpdateCountry() {
		try {
			BillingDetailspage br = new BillingDetailspage(driver);
			br.clickEditPaymentButton();
			br.UpadteBillingCountry("GB");
			logger.log(LogStatus.PASS, "details updated  Succesfully");
			logger.log(LogStatus.INFO, logger.addScreenCapture(screenshot(driver)));
		} catch (Exception e) {
			throw new FrameworkException("Billing details upadted  Not verified within specified time.---"
					+ e.getClass() + "---" + e.getMessage());
		}

	}

	public void getFlexPackageIdOnURL() {

		String id;
		String href = TechnicalComponents.getCurrentURL();
		String[] recordID = href.split("=");
		String newURL = recordID[0] + recordID[1] + "=37";
		driver.get(newURL);

	}

	public void createSurveyWithRequiredQuestion() {

		HomePageLoggedIn hp = new HomePageLoggedIn(driver);
		CreatePage cp = new CreatePage(driver);
		hp.enterSurveyName("team");
		hp.clickAddQuestion();
		verify_Redirection("create");
		cp.enterQuestion("What is team");
		cp.enterAnswer("a");
		cp.clickOption();
		cp.clickRequireOptions();
		cp.clickRequireSAVE();
	}

	public void enterOTCPaymentDetails() {

		BillingOTC br = new BillingOTC(driver);
		br.enterCardNum();
		br.enterCardName();
		br.selectExpDate();
		br.enterCVV();
	}

	public void enterOTCBillingDetails() {

		BillingOTC br = new BillingOTC(driver);
		br.enterPostal();
		br.enterFirstLast();

	}

	public void clickButton(String ButtonName) {

		switch (ButtonName) {
		case "AddCredit":
			TransactionHistoryPage tr = new TransactionHistoryPage(driver);
			tr.clickAddCreditButton();
			logger.log(LogStatus.PASS, "clicked Succesfully" + ButtonName);
			break;
		case "PayNow":
			CreditConfirmationPageWithoutPay cp = new CreditConfirmationPageWithoutPay(driver);
			cp.clickPayNow();
			logger.log(LogStatus.PASS, "clicked Succesfully" + ButtonName);
			break;
		case "ConfirmOnInvoice":
			Billing_InvoiceCheckoutPage bg = new Billing_InvoiceCheckoutPage(driver);
			bg.clickConfirm();
			logger.log(LogStatus.PASS, "clicked Succesfully" + ButtonName);
			break;
		case "OTCConfirmInvoice":
			BillingOTC br = new BillingOTC(driver);
			br.clickConfirm();
			logger.log(LogStatus.PASS, "clicked Succesfully" + ButtonName);
			break;
		case "proceedToCheckout":
			CollectAudience c = new CollectAudience(driver);
			c.clickProceedToCheckout();
			logger.log(LogStatus.PASS, "clicked Succesfully" + ButtonName);
			break;
		case "CreateTeam":
			BillingConfirmedPage bc = new BillingConfirmedPage(driver);
			bc.clickOnCreatTeam();
			logger.log(LogStatus.PASS, "clicked Succesfully" + ButtonName);
			break;
		case "upgradeConfirm":
			UpgradePage up = new UpgradePage(driver);
			up.clickUpgradeConfirm();
			logger.log(LogStatus.PASS, "clicked Succesfully" + ButtonName);
			break;
		case "upgrade":
			TeamSetUp ts = new TeamSetUp(driver);
			ts.clickUpgarde();
			logger.log(LogStatus.PASS, "clicked Succesfully" + ButtonName);
			break;
		case "sendInvitation":
			TeamPage tp = new TeamPage(driver);
			tp.clickSendInviattion();
			logger.log(LogStatus.PASS, "clicked Succesfully" + ButtonName);
			break;
		case "close":
			BillingInvoice ba = new BillingInvoice(driver);
			ba.clickClose();
			logger.log(LogStatus.PASS, "clicked Succesfully" + ButtonName);
			break;
		case "PAY":
			Billing_Checkout bch = new Billing_Checkout(driver);
			bch.clickPay();
			logger.log(LogStatus.PASS, "clicked Succesfully" + ButtonName);
			break;
		case "Buy":
			Billing_Checkout bc1h = new Billing_Checkout(driver);
			bc1h.clickBuy();
			logger.log(LogStatus.PASS, "clicked Succesfully" + ButtonName);
			break;
		case "modalConfirm":
			Billing_Checkout bcc = new Billing_Checkout(driver);
			bcc.clickConfirmOnModal();
			logger.log(LogStatus.PASS, "clicked Succesfully" + ButtonName);
			break;
		default:
			logger.log(LogStatus.INFO, "No link found" + ButtonName);
			break;
		}

	}

	public void enterAddCreditBillingDetails() {

		billingPwCreditsInvoice br = new billingPwCreditsInvoice(driver);
		br.enterAmount();
		br.enterPostalCode();
		br.enterFirstnameLastName();
		br.clickConfirm();

	}

	public void enterAddCreditPaymentDetails() {

		Billing_InvoiceCheckoutPage br = new Billing_InvoiceCheckoutPage(driver);
		br.enterAddCreditCardNum();
		br.enterAddCreditCardName();
		br.selectExpDateYear();
		br.enterCVV();

	}

	public void sendInvite(String email) {
		TeamAdd ta = new TeamAdd(driver);
		ta.enterEmail(email);
		ta.clickCheckbox();
		ta.clickSendInvitation();
	}

	public void clickLatestInvoice() {
		TransactionHistoryPage ta = new TransactionHistoryPage(driver);
		ta.clickLatestInvoice();
	}

	public void verifyOverage() {
		BillingInvoice ta = new BillingInvoice(driver);
		ta.verifyOverage();
	}

	public void handleInvitationPopup() {
		TeamAdd ta = new TeamAdd(driver);
		ta.visibleInvisible();
	}

	public void acceptPSD2PopUp() {
		Billing_Checkout bc = new Billing_Checkout(driver);
		bc.handlePSD2PopUp();
	}

	public void clickLatestTaxamoInvoice() {
		TransactionHistoryPage ta = new TransactionHistoryPage(driver);
		ta.clickLatestTaxamoInvoice();
	}

	public void verifyPlanInTaxamoInvoice(String PlanName) {
		TransactionHistoryPage ta = new TransactionHistoryPage(driver);
		ta.verifyTaxamoInvoice(PlanName);
	}

	public void verifyandProceedDirectDebitModal() {
		Billing_Checkout bc = new Billing_Checkout(driver);
		bc.verifyDirectDebitModal();

	}
	public void verifyandProceedSEPADirectDebitModal() {
		Billing_Checkout bc = new Billing_Checkout(driver);
		bc.verifySEPADirectDebitModal();

	}

	public void verifyDirectDebitDisclaimer(String Data) {
		BillingConfirmedPage bc = new BillingConfirmedPage(driver);
		if (Data.equals(bc.getRecepitDisclaimer())) {
			logger.log(LogStatus.PASS, "Data  verified Succesfully");
		} else {
			logger.log(LogStatus.FAIL, "Data not Verified Successfully ");

		}

	}

}
