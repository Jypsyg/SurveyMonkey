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
public class CollectAudience extends TechnicalComponents {

	WebDriver driver;

	public static String urlsuffix = "/collect/audience/";

	@FindBy(xpath = "//button[@id='checkout-button']")
	public static WebElement btncheckout;

	

	public CollectAudience(WebDriver driver) {
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
			TechnicalComponents.waitTill(btncheckout, "visible");
			if (driver.getCurrentUrl().contains(urlsuffix)) {
				return true;
			} else {
				return false;
			}
		} catch (FrameworkException e) {
			throw new FrameworkException(
					"Collect audience Loaded within specified time.---" + e.getClass() + "---" + e.getMessage());
		}

	}


	public void clickProceedToCheckout() throws FrameworkException {
		TechnicalComponents.waitTill(btncheckout, "enable");
		TechnicalComponents.click(btncheckout, "click on proceed to checkout");;
	}
}
