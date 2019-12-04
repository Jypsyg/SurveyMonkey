package objectrepository;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
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
 * Object repository of the Billing Invoice Checkout page.
 * 
 * @author Jypsy
 *
 */
public class Billing_InvoiceCheckoutPage extends TechnicalComponents {

	WebDriver driver;
	String urlsuffix="/billing/invoice/checkout/";
	
	public Billing_InvoiceCheckoutPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}	

	@FindBy(xpath = "//input[contains(@name,'first')]")
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
	
	@FindBy(xpath = "//input[@id='yui_3_4_1_2_1554206335259_5397']")
	public static WebElement btnpay;
	

	/**
	 * function to validate the redirection to invoice checkout  page.
	 * 
	 * @author Jypsy
	 *
	 */
	public boolean isPageOpened() {
		try {
			TechnicalComponents.waitTill(txt_FirstName, "visible");
			if (driver.getCurrentUrl().endsWith(urlsuffix)) {
				return true;
			} else {
				return false;
			}
		} catch (FrameworkException e) {
			throw new FrameworkException(
					"Invoice checkout page  Not Loaded within specified time.---" + e.getClass() + "---" + e.getMessage());
		}

	}
	
	@FindBy(xpath = "//input[@value='CONFIRM']")
	public static WebElement btnConfirm;
	
	public void clickConfirm() {
		
		TechnicalComponents.click(btnConfirm,"Clicked on Confirm");

	}
	
	@FindBy(xpath = "(//input[@id='cardnumber'])[1]")
	public static WebElement txtCreditAddNumber;
	
	
	public void enterAddCreditCardNum() {
		
		TechnicalComponents.type(txtCreditAddNumber, "4111111111111111", "CardNumber");
	}
	
	
	@FindBy(xpath = "(//input[@name='name_on_card'])[1]")
	public static WebElement txtCreditCardName;
	
	public void enterAddCreditCardName() {
		
		TechnicalComponents.type(txtCreditCardName, "test test", "CardName");
	}
	
	@FindBy(xpath = "(//select[@name='month'])[1]")
	public static WebElement ddlExpDate;
	
	@FindBy(xpath = "(//select[@name='year'])[1]")
	public static WebElement ddlExpYear;
	
	
	@FindBy(xpath = "(//input[@name='security_code'])[1]")
	public static WebElement txtAddCreditInvoiceCVV;
	
	public void selectExpDateYear() {
		
		TechnicalComponents.selectValuefromDropdown(ddlExpDate, "value", "10");
		TechnicalComponents.selectValuefromDropdown(ddlExpYear, "value", "2020");
	}
	
	public void enterCVV() {
		
		TechnicalComponents.type(txtAddCreditInvoiceCVV, "737", "cvv");
	
	}
}
