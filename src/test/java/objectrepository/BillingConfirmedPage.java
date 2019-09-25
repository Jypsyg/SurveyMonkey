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
 * Object repository of the billingConfirmed page.
 * 
 * @author Jypsy
 *
 */
public class BillingConfirmedPage extends TechnicalComponents {

	WebDriver driver;
	public static String urlsuffix = "/billing/confirmed";
	
	
	public BillingConfirmedPage(WebDriver driver) {

		this.driver = driver;
		PageFactory.initElements(driver, this);
	}


	@FindBy(xpath = "//div[@class='unit size1of2 confirmation-info']")
	public static WebElement txtConfirmation;


	/**
	 * function to validate the redirection to billing/confirm page.
	 * 
	 * @author Jypsy
	 *
	 */
	public boolean isPageOpened() {
		try {
			TechnicalComponents.waitTill(txtConfirmation, "visible");
			if (driver.getCurrentUrl().contains(urlsuffix)) {
				return true;
			} else {
				return false;
			}
		} catch (FrameworkException e) {
			throw new FrameworkException(
					"Billing confirm page Not Loaded within specified time.---" + e.getClass() + "---" + e.getMessage());
		}

	}
	
	@FindBy(xpath = "//span[@class='notranslate']")
	public static WebElement txtInvoiceNumber;
	
	

	public String getInvoice() {
		
		String ActualInvoice = TechnicalComponents.getAttribute(txtInvoiceNumber, "text", "invoice number");
		return ActualInvoice;
	}

}
