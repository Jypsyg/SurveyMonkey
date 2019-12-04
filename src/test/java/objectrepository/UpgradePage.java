package objectrepository;

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
import reusablecomponents.BusinessComponents;
import reusablecomponents.TechnicalComponents;

/**
 * Object repository of the billingConfirmed page.
 * 
 * @author Jypsy
 *
 */
public class UpgradePage extends TechnicalComponents {

	WebDriver driver;
	public static String urlsuffix = "/upgrade";
	
	
	public UpgradePage(WebDriver driver) {

		this.driver = driver;
		PageFactory.initElements(driver, this);
	}


	@FindBy(xpath = "//h1[contains(text(),'Upgrade Your Account')]")
	public static WebElement txtUpgradeAccount;


	/**
	 * function to validate the redirection to billing/confirm page.
	 * 
	 * @author Jypsy
	 *
	 */
	public boolean isPageOpened() {
		try {
			TechnicalComponents.waitTill(txtUpgradeAccount, "visible");
			if (driver.getCurrentUrl().contains(urlsuffix)) {
				return true;
			} else {
				return false;
			}
		} catch (FrameworkException e) {
			throw new FrameworkException(
					"Upgarde page Not Loaded within specified time.---" + e.getClass() + "---" + e.getMessage());
		}

	}
	

	@FindBy(xpath = "//input[@value='CONFIRM']")
	public static WebElement btnConfirm;
	
	public void clickUpgradeConfirm() {
		Actions action = new Actions(driver);
		action.moveToElement(btnConfirm).click().perform();
	}
	
}

