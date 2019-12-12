package objectrepository;

import java.lang.reflect.Array;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.server.handler.GetCurrentUrl;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.gargoylesoftware.htmlunit.javascript.host.file.Blob;
import com.relevantcodes.extentreports.LogStatus;

import config.FrameworkException;
import config.TestSetup;
import reusablecomponents.BusinessComponents;
import reusablecomponents.TechnicalComponents;

/**
 * Object repository of the Billing Checkout page.
 * 
 * @author Jypsy
 *
 */
public class Billing_Checkout extends TechnicalComponents {

	WebDriver driver;
	String urlsuffix = "/billing/checkout/";
	String EDUurlsuffix = "/billing/checkout/?direct_to_pro";
	String directToProurlSuffix = "direct_to_pro";

	public Billing_Checkout(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//input[@name='first_name']")
	public static WebElement txt_FirstName;

	@FindBy(xpath = "//input[@name='last_name']")
	public static WebElement txt_LastName;

	@FindBy(xpath = "//select[@name='country']")
	public static WebElement dropdown_Country;

	@FindBy(xpath = "//input[@name='email']")
	public static WebElement txt_Email;

	@FindBy(xpath = "//input[@name='postal_code']")
	public static WebElement txt_postalcode;

	@FindBy(xpath = "//input[@name='city']")
	public static WebElement txt_city;

	@FindBy(xpath = "//button[@id='next-0']")
	public static WebElement btn_BillingDetails_Next;

	@FindBy(xpath = "//input[@name='full_name']")
	public static WebElement txt_FullName;

	@FindBy(xpath = "//div[@class='credit_card-icon']")
	public static WebElement icon_creditCard;

	@FindBy(xpath = "//div[@class='invoice-icon']")
	public static WebElement icon_Invoice;

	@FindBy(xpath = "//div[@class='sepa-icon']")
	public static WebElement icon_SEPA;

	@FindBy(xpath = "//div[@class='paypal-icon']")
	public static WebElement icon_PayPal;

	@FindBy(xpath = "//div[@class='bacs-icon']")
	public static WebElement icon_DirectDebit;

	@FindBy(xpath = "//input[@name='credit_card_number']")
	public static WebElement txt_CreditCardNumber;

	@FindBy(xpath = "//input[@name='expiry_date']")
	public static WebElement txt_Expirydate;

	@FindBy(xpath = "//input[@name='credit_card_verification']")
	public static WebElement txt_CVV;

	@FindBy(xpath = "//button[@id='next-1']")
	public static WebElement btn_paymentMethod_Next;

	@FindBy(xpath = "//button[@name='submit-payment']")
	public static WebElement btn_Confirm;

	@FindBy(xpath = "//button[@name='additional-seats-increment']")
	public static WebElement btn_AdditionalSeat;

	@FindBy(xpath = "//a[@id='edit-0']")
	public static WebElement btn_BillingDetails_Edit;

	@FindBy(xpath = "//a[@id='edit-1']")
	public static WebElement btn_PaymentMethods_Edit;

	@FindBy(xpath = "//img[@alt='TRUSTe']")
	public static WebElement image_Truste;

	@FindBy(xpath = "//a[contains(@title,'BBB report')]")
	public static WebElement lnk_BBB_Report;

	@FindBy(xpath = "//img[contains(@title,'McAfee')]")
	public static WebElement img_McAfee;

	@FindBy(xpath = "//a[@href='/mp/policy/terms-of-use/?ut_source=billing_checkout']")
	public static WebElement lnk_TermsOfUse;

	/**
	 * function to validate the redirection to about us page.
	 * 
	 * @author Jypsy
	 *
	 */
	public boolean isPageOpened() {
		try {
			TechnicalComponents.waitTill(txt_FirstName, "visible");
			if (driver.getCurrentUrl().contains(urlsuffix)) {
				return true;
			} else {
				return false;
			}
		} catch (FrameworkException e) {
			throw new FrameworkException("billing checkout is  Not Loaded within specified time.---" + e.getClass()
					+ "---" + e.getMessage());
		}
	}

	public boolean isEDUBillingPageOpened() {
		try {
			TechnicalComponents.waitTill(txt_FirstName, "visible");
			if (driver.getCurrentUrl().contains(EDUurlsuffix)) {
				return true;
			} else {
				return false;
			}
		} catch (FrameworkException e) {
			throw new FrameworkException("billing checkout is  Not Loaded within specified time.---" + e.getClass()
					+ "---" + e.getMessage());
		}
	}

	public void enterFirstName(String FirstName) {
		if (txt_FirstName.isEnabled())

		{
			TechnicalComponents.type(txt_FirstName, FirstName, "Entered first name");
		}
	}

	public void enterLastName(String LastName) {
		if (txt_LastName.isEnabled())
			;
		{
			TechnicalComponents.type(txt_LastName, LastName, "Entered last name");
		}
	}

	public void selctDropDownValue(String Value) {
		if (dropdown_Country.isEnabled()) {
			TechnicalComponents.selectValuefromDropdown(dropdown_Country, "actualdata", Value);
		}
	}

	public void enterEmail(String Email) {
		if (txt_Email.isEnabled()) {
			TechnicalComponents.click(txt_Email, "billing email");
		}

	}

	public void enterPostalCode(String PostalCode) {
		if (txt_postalcode.isEnabled()) {
			TechnicalComponents.type(txt_postalcode, PostalCode, "Entered PostalCode");
			txt_postalcode.sendKeys(Keys.TAB);
		}
	}

	public void enter_BillingCheckoutDetails(String FirstName, String LastName, String Country, String PostalCode,
			String Email) {

		enterFirstName(FirstName);
		enterLastName(LastName);
		// selctDropDownValue(Country);
		enterPostalCode(PostalCode);
		TechnicalComponents.waitTill(3);
		enterEmail(Email);
	}

	public void enterGBBillingCheckoutDetails(String FirstName, String LastName, String Country, String Email) {

		enterFirstName(FirstName);
		enterLastName(LastName);
		selctDropDownValue(Country);
		TechnicalComponents.waitTill(3);
		enterEmail(Email);
	}

	@FindBy(xpath = "//input[@name='brazil_tax_number']")
	public static WebElement txtCNPF;

	@FindBy(xpath = "//input[@name='company_name']")
	public static WebElement txtCompanyName;

	@FindBy(xpath = "//input[@name='purchase_order_number']")
	public static WebElement txtPO;

	@FindBy(xpath = "//input[@name='street_address']")
	public static WebElement txtStreet;

	@FindBy(xpath = "//input[@name='house_number']")
	public static WebElement txtHousenum;

	@FindBy(xpath = "//select[@name='country_subdivision']")
	public static WebElement ddlCountry;

	@FindBy(xpath = "//select[@name='city']")
	public static WebElement ddlcity;

	public void enterBrazilBillingCheckoutDetails(String FirstName, String LastName, String Country, String PostalCode,
			String Email) {
		try {
			enterFirstName(FirstName);
			enterLastName(LastName);
			selctDropDownValue(Country);
			TechnicalComponents.waitTill(3);
			enterPostalCode(PostalCode);
			TechnicalComponents.waitTill(txtCNPF, "visible");
			TechnicalComponents.click(txtCNPF, "visible");
			TechnicalComponents.type(txtCNPF, "43.337.004/0001-72", "CNPF eneterd");
			
			txtCNPF.sendKeys(Keys.chord(Keys.CONTROL, "a"));
			TechnicalComponents.EnterKeys(txtCNPF, "BACK_SPACE");
			TechnicalComponents.type(txtCNPF, "43.337.004/0001-72", "CNPF eneterd");
			
			
			
			TechnicalComponents.waitTill(txtCompanyName, "visible");
			TechnicalComponents.type(txtCompanyName, "testcompany", "company eneterd");
			TechnicalComponents.waitTill(txtPO, "visible");	
			TechnicalComponents.type(txtPO, "testpo", "PO eneterd");
			TechnicalComponents.waitTill(txtStreet, "visible");		
			TechnicalComponents.type(txtStreet, "teststreet", "strret eneterd");
			TechnicalComponents.waitTill(txtHousenum, "visible");
			TechnicalComponents.type(txtHousenum, "testhouse", "house eneterd");
			TechnicalComponents.waitTill(ddlCountry, "visible");
			TechnicalComponents.selectValuefromDropdown(ddlCountry, "actualdata", "BR-CE");
			TechnicalComponents.waitTill(ddlcity, "visible");
			TechnicalComponents.selectValuefromDropdown(ddlcity, "actualdata", "Ibiapina");
			enterEmail(Email);
		} catch (Exception e) {
			throw new FrameworkException(
					"Unknown exception occured while handling alerts.---" + e.getClass() + "---" + e.getMessage());
		}

	}

	public void enter_BillingInvoicePFICheckoutDetails(String FirstName, String LastName, String Country,
			String PostalCode, String Email) {

		enterFirstName(FirstName);
		enterLastName(LastName);
		selctDropDownValue(Country);
		enterPostalCode(PostalCode);
		TechnicalComponents.waitTill(3);
		enterEmail(Email);
	}

	public void clickBillingDetailsNext() {
		TechnicalComponents.waitTill(btn_BillingDetails_Next, "enable");
		TechnicalComponents.click(btn_BillingDetails_Next, "Billing Details Next button clicked");
	}

	public static void SelectPaymentMethod(String PaymentType) {

		try {
			boolean PaymentMethodSelected = false;
			switch (PaymentType) {
			case "creditcard":
				TechnicalComponents.click(icon_creditCard, "Selected Credit card payment method");
				PaymentMethodSelected = true;
				break;
			case "invoice":
				TechnicalComponents.click(icon_Invoice, "select Invoice payment method");
				PaymentMethodSelected = true;
				break;
			case "sepadirectdebit":
				TechnicalComponents.click(icon_SEPA, "Select SEPA Payment method");
				PaymentMethodSelected = true;
				break;
			case "paypal":
				TechnicalComponents.click(icon_PayPal, "Select paypal payment method");
				PaymentMethodSelected = true;

			case "directdebit":
				TechnicalComponents.click(icon_DirectDebit, "Select Direct Debit payment method");
				PaymentMethodSelected = true;

				break;
			default:
				throw new FrameworkException("Payment method not found  " + PaymentType);
			}
			if (PaymentMethodSelected) {
				logger.log(LogStatus.PASS, "payment method successfully selected " + PaymentType);
			} else {
				logger.log(LogStatus.FAIL, "payment method not successfully selected  " + PaymentType);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}

	}

	public void enterExpiryDate() {

		TechnicalComponents.type(txt_Expirydate, "10/20", "Expiry Date entered");

	}

	public void enterCVV() {

		TechnicalComponents.type(txt_CVV, "737", "CVV entered");

	}

	public void enterCardAccountName() {

		TechnicalComponents.type(txt_FullName, "FirstName Lastname", "'CardName Entered");

	}

	public void enterPSD2CardAccountName() {

		TechnicalComponents.type(txt_FullName, "test_test_test 3DS_V1_CHALLENGE_IDENTIFIED", "'CardName Entered");

	}

	public void enterCreditCardNumber(String CardType) {

		TechnicalComponents.type(txt_CreditCardNumber, "4111111111111111", "Card Number entered");
	}

	public void enterPaymentDetails(String CardType) {
		TechnicalComponents.waitTill(2);
		enterCreditCardNumber(CardType);
		enterCardAccountName();
		TechnicalComponents.waitTill(2);
		enterExpiryDate();
		enterCVV();
		TechnicalComponents.waitTill(2);
	}

	public void enterPSD2PaymentDetails(String CardType) {
		TechnicalComponents.waitTill(2);
		enterCreditCardNumber(CardType);
		enterPSD2CardAccountName();
		TechnicalComponents.waitTill(2);
		enterExpiryDate();
		enterCVV();
		TechnicalComponents.waitTill(2);
	}

	public void clickBillingPaymentNext() {
		TechnicalComponents.click(btn_paymentMethod_Next, "Billing Payment Next button clicked");
	}

	@FindBy(xpath = "//button[contains(text(),'Confirm') or contains(text(),'Buy') or contains(text(),'Pay') or contains(text(), 'Kaufen') or contains(text(),'CONFIRM') or contains(text(),'PAY')or contains(text(),'BUY') or contains(text(),'submit')or contains(@type,'submit')] | //input[contains(@value,'Confirm')or contains(@value,'Buy') or contains(@value,'Pay') or contains(@value, 'Kaufen') or contains(@value,'CONFIRM') or contains(@value,'PAY') or contains(@value,'BUY') or contains(@type,'submit')]")
	public static WebElement btnPAY;

	public void clickPay() {

		TechnicalComponents.scroll(btnPAY);
		TechnicalComponents.click(btnPAY, "Pay button clicked");
	}

	@FindBy(xpath = "//button[@name='submit-payment']")
	public static WebElement btnBUY;

	public void clickBuy() {

		TechnicalComponents.scroll(btnBUY);
		TechnicalComponents.click(btnBUY, "Buy button clicked");
	}

	public void addUsers(String NumOfSeats) {
		int numberSeats = Integer.parseInt(NumOfSeats);
		if (!(numberSeats == 0)) {
			for (int i = 0; i < numberSeats; i++) {
				TechnicalComponents.click(btn_AdditionalSeat, "Added seat number" + i);
				waitTill(btn_AdditionalSeat, "Visible", "Waiting for enable + button");
			}
		}
	}

	public void clickConfirmButton() {
		TechnicalComponents.click(btn_Confirm, "Confirm button clicked");
	}

	@FindBy(xpath = "//div[@class='wds-type--right wds-p-r-0 wds-grid__col']//preceding-sibling::strong")
	public static WebElement textTotalAmount;

	public String getTotalAmount() {
		String ActualTotalAmount = TechnicalComponents.getAttribute(textTotalAmount, "text", "TotalAmount");
		if (ActualTotalAmount.contains("/")) {
			String SplitedAmount[] = ActualTotalAmount.split("/");
			return SplitedAmount[0];
		}
		return ActualTotalAmount;
	}

	@FindBy(xpath = "//iframe[@id='payment-challenge-frame']")
	public static WebElement frame;

	@FindBy(xpath = "//input[@value='OK']")
	public static WebElement btnOK;

	@FindBy(xpath = "//circle[@class='wds-spinner__path-fill']")
	public static WebElement loading;

	public void handlePSD2PopUp() {
		try {
			TechnicalComponents.waitTill(frame, "visible");
			TechnicalComponents.switchtoiframe(frame);
		    driver.manage().timeouts().implicitlyWait(8,TimeUnit.SECONDS) ;
			String OK="OK";
			if (OK.equals(TechnicalComponents.getAttribute(btnOK, "value", "test"))) {
				TechnicalComponents.click(btnOK, "Ok clicked");	
			}		
			//TechnicalComponents.switchToDefaultContent();
			//TechnicalComponents.visibleInvisible(loading);

		} catch (Exception e) {
			throw new FrameworkException("not handled psd2 popup.---" + e.getClass() + "---" + e.getMessage());
		}

	}

	@FindBy(xpath = "//input[@name='sort_code']")
	public static WebElement txtSortCode;

	@FindBy(xpath = "//input[@name='account_number']")
	public static WebElement txtAccountNUmber;

	@FindBy(xpath = "//input[@name='account_holder']")
	public static WebElement txtAccountHolder;

	@FindBy(xpath = "//input[@name='confirm_checkbox']")
	public static WebElement chkBoxConfirm;

	public void enterDirectDebitPaymentDetails() {
		TechnicalComponents.type(txtSortCode, "200000", "sortcode entered");
		TechnicalComponents.type(txtAccountNUmber, "55667711", "account number entered");
		TechnicalComponents.type(txtAccountHolder, "test test", "account holder name entered");
		TechnicalComponents.click(chkBoxConfirm, "CheckBox Checked");

	}

	@FindBy(xpath = "//div[@class='wds-type--warning wds-type--body']")
	public static WebElement txtSortCodeError;

	@FindBy(xpath = "//label[contains(text(),'Account number')]/parent::span//parent::div/following-sibling::div")
	public static WebElement txtAcccountNumberError;

	@FindBy(xpath = "//div[contains(text(),'This field needs a first and last name')][@class='wds-type--warning wds-type--body']")
	public static WebElement txtName;

	public void verifyDirectDebitPaymentErroMessage() {
		TechnicalComponents.click(txtSortCode, "IBAN  code clciked");
		TechnicalComponents.EnterKeys(txtSortCode, "TAB");
		TechnicalComponents.isDisplayed(txtIBANError, "IABN Error message verified");
		TechnicalComponents.click(txtAccountNUmber, "Account number  code clciked");
		TechnicalComponents.EnterKeys(txtAccountNUmber, "TAB");
		TechnicalComponents.isDisplayed(txtAcccountNumberError, "IABN Error message verified");
		TechnicalComponents.click(txtAccountHolder, "Account number  code clciked");
		TechnicalComponents.EnterKeys(txtAccountHolder, "TAB");
		TechnicalComponents.isDisplayed(txtName, "IABN Error message verified");

	}

	@FindBy(xpath = "//div[contains(text(),'This field is required')][@class='wds-type--warning wds-type--body']")
	public static WebElement txtIBANError;

	@FindBy(xpath = "//div[contains(text(),'This field needs a first and last name')][@class='wds-type--warning wds-type--body']")
	public static WebElement txtIBANAccountholderError;

	public void verifySEPADirectDebitPaymentErroMessage() {
		TechnicalComponents.click(txtIBAN, "IBAN  code clciked");
		TechnicalComponents.EnterKeys(txtIBAN, "TAB");
		TechnicalComponents.isDisplayed(txtIBANError, "IABN Error message verified");
		TechnicalComponents.click(txtAccHold, "Account number  code clciked");
		TechnicalComponents.EnterKeys(txtAccHold, "TAB");
		TechnicalComponents.isDisplayed(txtIBANAccountholderError, "IABN Error message verified");

	}

	@FindBy(xpath = "//input[@name='iban']")
	public static WebElement txtIBAN;

	@FindBy(xpath = "//input[@name='account_holder']")
	public static WebElement txtAccHold;

	public void enterSEPADirectDebitPaymentDetails() {
		TechnicalComponents.type(txtIBAN, "DE89370400440532013000", "IBAN  entered");
		TechnicalComponents.type(txtAccHold, "test test", "account Holder  entered");
		TechnicalComponents.click(chkBoxConfirm, "CheckBox Checked");

	}

	@FindBy(xpath = "//div[@class='bacs-logo']")
	public static WebElement logoDirectDebit;

	@FindBy(xpath = "//div[contains(text(),'200000')]")
	public static WebElement txtDataSortCode;

	@FindBy(xpath = "//div[contains(text(),'55667711')]")
	public static WebElement txtDataAccountNumCode;

	@FindBy(xpath = "//div[contains(text(),'test test')]")
	public static WebElement txtDataAccountNameCode;

	@FindBy(xpath = "//a[contains(text(),'Direct Debit Guarantee.')]")
	public static WebElement lnkDirectDebit;

	public void verifyDirectDebitModal() {
		TechnicalComponents.waitTill(logoDirectDebit, "visible");
		try {
			if (logoDirectDebit.isDisplayed() || txtDataSortCode.isDisplayed() || txtDataAccountNumCode.isDisplayed()
					|| txtDataAccountNameCode.isDisplayed() || lnkDirectDebit.isDisplayed()) {
				logger.log(LogStatus.PASS, "Moadl data verified successfiully");
			}
		} catch (Exception e) {
			throw new FrameworkException("Modal not verifed sucessfully---" + e.getClass() + "---" + e.getMessage());
		}

	}

	@FindBy(xpath = "//div[contains(text(),'GB33ZZZSDDBARC0000007495895516')]")
	public static WebElement txtCreditor;

	@FindBy(xpath = "//div[contains(text(),'DE89370400440532013000')]")
	public static WebElement txtIBANID;

	@FindBy(xpath = "//div[contains(text(),'test test')]")
	public static WebElement txtHolder;

	@FindBy(xpath = "//div[contains(text(),'Available after confirmation')]")
	public static WebElement txtConfirm;

	public void verifySEPADirectDebitModal() {
		TechnicalComponents.waitTill(txtCreditor, "visible");
		try {
			if (txtCreditor.isDisplayed() || txtIBANID.isDisplayed() || txtHolder.isDisplayed()
					|| txtConfirm.isDisplayed()) {
				logger.log(LogStatus.PASS, "SEPA Modal data verified successfiully");
			}
		} catch (Exception e) {
			throw new FrameworkException(
					"SEPA Modal not verifed sucessfully---" + e.getClass() + "---" + e.getMessage());
		}

	}

	@FindBy(xpath = "//button[contains(text(),'Confirm')]")
	public static WebElement btnModalConfirm;

	public void clickConfirmOnModal() {
		TechnicalComponents.click(btnModalConfirm, "confirm button clicked");

	}
	
	public void clickMonthlyNext() {
		TechnicalComponents.click(btnModalConfirm, "confirm button clicked");

	}
	
	@FindBy(xpath = "//button[@id='next-2']")
	public static WebElement btnReviewOrder_Next;
	
	

	public void clickReviewOrderNext() {
		TechnicalComponents.click(btnReviewOrder_Next, "Billing Payment Next button clicked");
	}

}
