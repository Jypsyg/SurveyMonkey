package objectrepository;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
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
public class BillingPWMessages extends TechnicalComponents {

	WebDriver driver;

	public static String urlsuffix = "/billing/pw/messages/mismatched_audience_currency";

	@FindBy(xpath = "//a[contains(text(),'Customer Support')]")
	public static WebElement btnCustomerSupport;

	public BillingPWMessages(WebDriver driver) {
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
			TechnicalComponents.waitTill(btnCustomerSupport, "visible");
			if (driver.getCurrentUrl().contains(urlsuffix)) {
				return true;
			} else {
				return false;
			}
		} catch (FrameworkException e) {
			throw new FrameworkException("Billing mismatch currency Loaded within specified time.---" + e.getClass()
					+ "---" + e.getMessage());
		}

	}

	@FindBy(xpath = "//div[@class='wds-type--body']")
	public static WebElement txtErrormsg;

	public boolean verifyMismatchError() {
		try {
			String ActualError = TechnicalComponents.getAttribute(txtErrormsg, "text", "error message");
			if (ActualError.equals(
					"You purchased credits in a different currency than your current billing country. To get your credits refunded or converted, update your billing country or contact our customer support team."))
				;
			return true;
		} catch (Exception e) {
			throw new FrameworkException("not verified.---" + e.getClass() + "---" + e.getMessage());
		}

	}
}
