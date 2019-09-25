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
 * Object repository of the credit confirmation  page.
 * 
 * @author Jypsy
 *
 */
public class Credit_ConfirmationPage extends TechnicalComponents {

	WebDriver driver;

	private String Credit_confirmation_title = "SurveyMonkey - Thank you for your credits purchase";
	public static String urlsuffix = "/billing/pw/credits-confirmation";

	public Credit_ConfirmationPage(WebDriver driver) {

		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//div[contains(@class ,'invoice-number')]")
	public static WebElement txtInvoiceNumber;




	/**
	 * function to validate the redirection to credit confirmation  page.
	 * 
	 * @author Jypsy
	 *
	 */
	public boolean isPageOpened() {
		try {
			TechnicalComponents.waitTill(txtInvoiceNumber, "visible");
			if (driver.getCurrentUrl().endsWith(urlsuffix)) {
				return true;
			} else {
				return false;
			}
		} catch (FrameworkException e) {
			throw new FrameworkException(
					"Xredit Confirmation  Page Not Loaded within specified time.---" + e.getClass() + "---" + e.getMessage());
		}

	}



}
