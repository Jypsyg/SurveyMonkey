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
 * Object repository of the Plans and pricing page.
 * 
 * @author Jypsy
 *
 */
public class Dashboard extends TechnicalComponents {

	WebDriver driver;
	public static String Dashboard_title = "Welcome to SurveyMonkey!";
	public static String urlsuffix = "/dashboard/";

	public Dashboard(WebDriver driver) {

		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//h1[@class='welcome-line']")
	public static WebElement txt_Welcome;

	@FindBy(xpath = "//a[@class='responsive-logo']/following-sibling::ol//a[contains(text(),'My Survey')]")
	public static WebElement lnkMySurvey;
	
	/**
	 * function to validate the redirection to plans and pricing page.
	 * 
	 * @author Jypsy
	 *
	 */
	public boolean isPageOpened() {
		try {
			TechnicalComponents.waitTill(txt_Welcome, "visible");
			if (driver.getCurrentUrl().contains(urlsuffix)) {
				return true;
			} else {
				return false;
			}
		} catch (FrameworkException e) {
			throw new FrameworkException(
					"Dashboard Not Loaded within specified time.---" + e.getClass() + "---" + e.getMessage());
		}

	}
	
	@FindBy(xpath = "//div[@id='hd']//a[contains(@href,'/pricing/')]")
	public static WebElement lnk_PlansAndpricing;
	
	public void clickPlansAndPricing() {
		
		TechnicalComponents.click(lnk_PlansAndpricing, "plans and pricing");
	}

	
	@FindBy(xpath = "//a[@href='/pricing/upgrade/quickview/?ut_source=header_loggedIn']")
	public static WebElement lnk_LoggedInPlansAndpricing;
	
	public void clickLoggedInPlansAndPricing() {
		
		TechnicalComponents.click(lnk_LoggedInPlansAndpricing, "plans and pricing");
	}
	
	@FindBy(xpath = "//div[@class='actions']//a[contains(@href,'/pricing/')]")
	public static WebElement lnk_Upgrade;
	
	public void click_upgrade() {
		
		TechnicalComponents.click(lnk_Upgrade, "plans and pricing");
	}
	
	public void click_MySurvey() {
		
		TechnicalComponents.click(lnkMySurvey, "My Survey Link");
	}
}
