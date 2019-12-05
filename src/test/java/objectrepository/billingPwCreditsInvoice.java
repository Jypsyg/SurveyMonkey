package objectrepository;

import java.awt.Desktop.Action;
import java.util.Calendar;
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
import groovyjarjarantlr.actions.csharp.ActionLexer;
import reusablecomponents.BusinessComponents;
import reusablecomponents.TechnicalComponents;

/**
 * Object repository of the about us page.
 * 
 * @author Dsavita
 *
 */
public class billingPwCreditsInvoice extends TechnicalComponents {

	WebDriver driver;

	public static String urlsuffix = "/billing/pw/credits-invoice?";

	public billingPwCreditsInvoice(WebDriver driver) {

		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//input[@name='user_requested_credits']")
	public static WebElement txtEnterAmount;

	/**
	 * function to validate the redirection to billing details page.
	 * 
	 * @author Jypsy
	 *
	 */
	public boolean isPageOpened() {
		try {
			TechnicalComponents.waitTill(txtEnterAmount, "visible");
			if (driver.getCurrentUrl().contains(urlsuffix)) {
				return true;
			} else {
				return false;
			}
		} catch (FrameworkException e) {
			throw new FrameworkException(
					"About Us Page Not Loaded within specified time.---" + e.getClass() + "---" + e.getMessage());
		}
	}

	public void enterAmount() {
		Actions action = new Actions(driver);
		action.moveToElement(txtEnterAmount).click();
		action.perform();
		TechnicalComponents.type(txtEnterAmount, "200", "Amount enetered");
	}

	@FindBy(xpath = "//input[@name='postal_code']")
	public static WebElement txtPostalCode;

	public void enterPostalCode() {
		TechnicalComponents.type(txtPostalCode, "06001", "Zip code  enetered");
		TechnicalComponents.EnterKeys(txtPostalCode, "TAB");
	}

	@FindBy(xpath = "//input[@name='first_name']")
	public static WebElement txtFirst;

	@FindBy(xpath = "//input[@name='last_name']")
	public static WebElement txtLast;

	@FindBy(xpath = "//button[@name='submit-payment']")
	public static WebElement btnSubmit;
	
	@FindBy(xpath = "//div[contains(@class,'base-header')]")
	public static WebElement lblHeader;
	

	public void enterFirstnameLastName() {
		TechnicalComponents.type(txtFirst, "First", "First Name  enetered");
		TechnicalComponents.type(txtLast, "Last", "Last Name  enetered");	
	}
	
	

	public void clickConfirm() {
		Actions action = new Actions(driver);
		action.moveToElement(lblHeader).click().perform();
		TechnicalComponents.waitTill(btnSubmit, "enable");
		TechnicalComponents.click(btnSubmit, "Confirm button entered");

	}
}
