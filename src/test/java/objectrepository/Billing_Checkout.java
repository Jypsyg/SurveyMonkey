package objectrepository;

import java.lang.reflect.Array;
import java.util.List;

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
	String EDUurlsuffix = "/billing/checkout/?e=edu_faq";
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
			TechnicalComponents.selectValuefromDropdown(dropdown_Country, "value", "slected");
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
			case "sepa":
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

		/*
		 * JavascriptExecutor js = (JavascriptExecutor) driver;
		 * js.executeScript("arguments[0].click();", txt_CreditCardNumber); js.
		 * executeScript("txt_CreditCardNumber.setAttribute('value', '4111111111111111')"
		 * );
		 */

		// Actions ac = new Actions(driver);
		// ac.moveToElement(txt_CreditCardNumber).sendKeys(txt_CreditCardNumber,
		// "4111111111111111").click(txt_Expirydate).perform();
		//
		TechnicalComponents.waitTill(2);
	}

	public void clickBillingPaymentNext() {
		TechnicalComponents.click(btn_paymentMethod_Next, "Billing Payment Next button clicked");
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
}
