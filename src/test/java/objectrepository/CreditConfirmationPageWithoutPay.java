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
public class CreditConfirmationPageWithoutPay extends TechnicalComponents {

	WebDriver driver;

	private String Credit_confirmation_title = "SurveyMonkey - Thank you for your credits purchase";
	public static String urlsuffix = "/billing/pw/credits-confirmation";

	public CreditConfirmationPageWithoutPay(WebDriver driver) {

		this.driver = driver;
		PageFactory.initElements(driver, this);
	}


	/**
	 * function to validate the redirection to credit confirmation  page.
	 * 
	 * @author Jypsy
	 *
	 */
	public boolean isPageOpened() {
		try {
			TechnicalComponents.waitTill(btnPayNow, "visible");
			if (driver.getCurrentUrl().contains(urlsuffix)) {
				return true;
			} else {
				return false;
			}
		} catch (FrameworkException e) {
			throw new FrameworkException(
					"Credit Confirmation  Page Not Loaded within specified time.---" + e.getClass() + "---" + e.getMessage());
		}

	}
	

	@FindBy(xpath = "//button[contains(text(),'Confirm') or contains(text(),'Buy') or contains(text(),'Pay') or contains(text(), 'Kaufen') or contains(text(),'CONFIRM') or contains(text(),'PAY')or contains(text(),'BUY') or contains(text(),'submit')or contains(@type,'submit')] | //input[contains(@value,'Confirm')or contains(@value,'Buy') or contains(@value,'Pay') or contains(@value, 'Kaufen') or contains(@value,'CONFIRM') or contains(@value,'PAY') or contains(@value,'BUY') or contains(@type,'submit')]")
	public static WebElement btnPayNow;
	
	public void clickPayNow() {

		TechnicalComponents.click(btnPayNow,"Clicked on PayNOw");

	}



}
