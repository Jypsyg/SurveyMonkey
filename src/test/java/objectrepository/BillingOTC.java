package objectrepository;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.gargoylesoftware.htmlunit.javascript.host.file.Blob;
import com.relevantcodes.extentreports.LogStatus;

import config.FrameworkException;
import config.TestSetup;
import reusablecomponents.BusinessComponents;
import reusablecomponents.TechnicalComponents;

/**
 * Object repository of the about us page.
 * 
 * @author jypsy
 *
 */
public class BillingOTC extends TechnicalComponents {

	private static final Keys TAB = null;

	WebDriver driver;

	public static String urlsuffix = "/billing/otc?";

	@FindBy(xpath = "//label[contains(text(),'Credit or Debit Card')]")
	public static WebElement lblCreditDebit;

	@FindBy(xpath = "(//input[@id='cardnumber'])[1]")
	public static WebElement txtCardNum;

	@FindBy(xpath = "(//input[@name='name_on_card'])[1]")
	public static WebElement txtCardName;

	@FindBy(xpath = "(//select[@name='month'])[1]")
	public static WebElement ddlMonth;

	@FindBy(xpath = "(//select[@name='year'])[1]")
	public static WebElement ddlYear;

	@FindBy(xpath = "(//input[@name='security_code'])[1]")
	public static WebElement txtCVV;

	@FindBy(xpath = "(//input[@name='postalcode'])[1]")
	public static WebElement txtPostalCode;

	@FindBy(xpath = "//input[@name='firstName']")
	public static WebElement txtFirstName;

	@FindBy(xpath = "//input[@name='lastName']")
	public static WebElement txtlastName;

	@FindBy(xpath = "//input[@value='CONFIRM']")
	public static WebElement btnConfirm;

	public BillingOTC(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	/**
	 * function to validate the redirection to about us page.
	 * 
	 * @author jypsy
	 *
	 */
	public boolean isPageOpened() {
		try {
			TechnicalComponents.waitTill(lblCreditDebit, "visible");
			if (driver.getCurrentUrl().contains(urlsuffix)) {
				return true;
			} else {
				return false;
			}
		} catch (FrameworkException e) {
			throw new FrameworkException(
					"Billing OTC Page Loaded within specified time.---" + e.getClass() + "---" + e.getMessage());
		}

	}

	public void enterCardNum() {

		TechnicalComponents.type(txtCardNum, "4111111111111111", "Card Num entered");

	}

	public void enterCardName() {
		{
			TechnicalComponents.type(txtCardName, "Test Test", "Card name  entered");
		}
	}

	public void selectExpDate() {

		TechnicalComponents.selectValuefromDropdown(ddlMonth, "value", "10");
		TechnicalComponents.selectValuefromDropdown(ddlYear, "value", "2020");

	}

	public void enterCVV() {

		TechnicalComponents.type(txtCVV, "737", "CVV");

	}

	public void enterPostal() {

		TechnicalComponents.type(txtPostalCode, "06001", "Postal code entered");
		TechnicalComponents.EnterKeys(txtPostalCode, "TAB");

	}

	public void enterFirstLast() {
		if (txtFirstName.isEnabled()) {
			TechnicalComponents.click(txtFirstName, "First name clicked");
			TechnicalComponents.type(txtFirstName, "First", "First name entered");
			TechnicalComponents.type(txtlastName, "Last", "txtlastName enetred");
		}

	}

	@FindBy(xpath = "//div[contains(@class,'base-header')]")
	public static WebElement lblheader;

	public void clickConfirm() {
		Actions action = new Actions(driver);
		action.moveToElement(lblheader).click().perform();
		if (btnConfirm.isEnabled())
			action.moveToElement(btnConfirm).click().perform();

	}
	
	@FindBy(xpath = "//input[@id='payment-credits']")
	public static WebElement radioAudience;
	
	public void selectMyCreditPayMethod() {
		TechnicalComponents.waitTill(radioAudience, "visible");
		TechnicalComponents.click(radioAudience, "credit payment method selected");
	}
	
	@FindBy(xpath = "(//a[@data-track-ui-trigger='add_credits_button'])[1]")
	public static WebElement btnAddcredit;
	

	public void clickAddCredit() {
		TechnicalComponents.waitTill(btnAddcredit, "visible");
		TechnicalComponents.click(btnAddcredit, "Add credit clcikd");
	}
	
}
