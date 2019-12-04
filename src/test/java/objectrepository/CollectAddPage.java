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
public class CollectAddPage extends TechnicalComponents {

	WebDriver driver;

	public static String urlsuffix = "/collect/add";

	@FindBy(xpath = "//li[@id='audience-collector']")
	public static WebElement lnkbuyAudience;
	
	@FindBy(xpath = "//h2[contains(text(),'Target your ideal respondents')]")
	public static WebElement txtNewPage;

	public CollectAddPage(WebDriver driver) {
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
			if (txtNewPage.isDisplayed()) {
				driver.navigate().refresh();
			}
			TechnicalComponents.waitTill(lnkbuyAudience, "visible");
			if (driver.getCurrentUrl().contains(urlsuffix)) {
				return true;
			} else {
				return false;
			}
		} catch (FrameworkException e) {
			throw new FrameworkException(
					"Collect Page Not Loaded within specified time.---" + e.getClass() + "---" + e.getMessage());
		}

	}

		public void clickBuyAudience() {
			TechnicalComponents.click(lnkbuyAudience, "buy audience");
		}

}
