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
public class BillingInvoice extends TechnicalComponents {

	WebDriver driver;
	String urlsuffix = "/billing/invoice/";

	public BillingInvoice(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//a[@data-track-action-type='print_invoice']")
	public static WebElement btnPrint;

	/**
	 * function to validate the redirection to invoice checkout page.
	 * 
	 * @author Jypsy
	 *
	 */
	public boolean isPageOpened() {
		try {
			TechnicalComponents.waitTill(btnPrint, "visible");
			if (driver.getCurrentUrl().contains(urlsuffix)) {
				return true;
			} else {
				return false;
			}
		} catch (FrameworkException e) {
			throw new FrameworkException(
					"Invoice  page  Not Loaded within specified time.---" + e.getClass() + "---" + e.getMessage());
		}

	}

	@FindBy(xpath = "//p[contains(text(),'Team overage charge')]")
	public static WebElement txtOverage;

	public void verifyOverage() {
		try {
			if (txtOverage.isDisplayed()) {
				logger.log(LogStatus.PASS, "Overage Present");
				loggerForLogs.log(LogStatus.INFO, "Overage Present");
			}
		} catch (Exception e) {
			throw new FrameworkException("Unable to find overageL--- " + e.getClass() + "---" + e.getMessage());
		}

	}
	
	@FindBy(xpath = "//a[@data-track-action-type='close_invoice']")
	public static WebElement btnClose;
	
	
	
	public void clickClose() {
		TechnicalComponents.click(btnClose, "Close clicked");
	}
}
