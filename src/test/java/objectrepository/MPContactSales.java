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
 * Object repository of the about us page.
 * 
 * @author Dsavita
 *
 */
public class MPContactSales extends TechnicalComponents {

	WebDriver driver;

	public static String urlsuffix = "/mp/contact-sales/";
	
	public MPContactSales(WebDriver driver) {

		this.driver = driver;
		PageFactory.initElements(driver, this);
	}


	@FindBy(xpath = "//h2[contains(text(),'SurveyMonkey Enterprise')]")
	public static WebElement txtEnterprise;

	
	

	/**
	 * function to validate the redirection to My Account page.
	 * 
	 * @author Jypsy
	 *
	 */
	public boolean isPageOpened() {
		try {
			TechnicalComponents.waitTill(txtEnterprise, "visible");
			if (driver.getCurrentUrl().contains(urlsuffix)) {
				return true;
			} else {
				return false;
			}
		} catch (FrameworkException e) {
			throw new FrameworkException(
					"mp contact sales Loaded within specified time.---" + e.getClass() + "---" + e.getMessage());
		}

	}

	
	
	
	
	
}
