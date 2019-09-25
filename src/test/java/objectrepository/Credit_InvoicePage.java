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
 * Object repository of the credit invoice page.
 * 
 * @author Jypsy
 *
 */
public class Credit_InvoicePage extends TechnicalComponents {

	WebDriver driver;

	private String Credit_Invoice_title = "SurveyMonkey - Add Credits to your Account";
	public static String urlsuffix = "/billing/pw/credits-invoice";

	public Credit_InvoicePage(WebDriver driver) {

		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//input[@name='user_requested_credits']")
	public static WebElement fieldEnterAmount;

	@FindBy(xpath = "//input[@name='email']")
	public static WebElement fieldEmail;

	@FindBy(xpath = "//div[@class='select-dropdown']")
	public static WebElement dropdownCountry;

	@FindBy(xpath = "//input[@name='postal_code']")
	public static WebElement fieldInvoice_PostalCode;

	@FindBy(xpath = "//input[@name='first_name']")
	public static WebElement fieldFirstname;

	@FindBy(xpath = "//input[@name='last_name']")
	public static WebElement fieldlastName;

	@FindBy(xpath = "//input[@name='street_address']")
	public static WebElement fieldAddress;

	@FindBy(xpath = "//input[@name='city']")
	public static WebElement fieldTown;

	@FindBy(xpath = "//input[@name='phone_number']")
	public static WebElement fieldPhoneNumber;

	@FindBy(xpath = "//button[@name='submit-payment']")
	public static WebElement btnpay;

	/**
	 * function to validate the redirection to credit invoice page.
	 * 
	 * @author Jypsy
	 *
	 */
	public boolean isPageOpened() {
		try {
			TechnicalComponents.waitTill(fieldEnterAmount, "visible");
			if (driver.getCurrentUrl().endsWith(urlsuffix)) {
				return true;
			} else {
				return false;
			}
		} catch (FrameworkException e) {
			throw new FrameworkException(
					"About Us Page Not Loaded within specified time.---" + e.getClass() + "---" + e.getMessage());
		}

	}

	public void clickPayButton() {
		TechnicalComponents.click(btnpay, "Pay  button clicked");
	}

}
