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
public class MyAccountPage extends TechnicalComponents {

	WebDriver driver;

	private String AccountPage_title = "SurveyMonkey – My Account";
	public static String urlsuffix = "/user/account/";
	
	public MyAccountPage(WebDriver driver) {

		this.driver = driver;
		PageFactory.initElements(driver, this);
	}


	@FindBy(xpath = "//a[@id='userAcctTab_MainMenu']")
	public static WebElement menuAccountTab;

	@FindBy(xpath = "(//a[contains(@href, '/user/account')])[1]")
	public static WebElement lnk_myAccount;
	
	@FindBy(xpath = "//a[@title='Account Summary']")
	public static WebElement lnk_AccountSummary;
	
	@FindBy(xpath = "//a[@title='Billing Details']")
	public static WebElement lnk_BillingDetails;
	
	@FindBy(xpath = "//a[@title='Transaction History']")
	public static WebElement lnk_TransactionHistory;
	
	@FindBy(xpath = "//a[@title='Refer a Friend']")
	public static WebElement lnk_ReferAFriend;
	

	/**
	 * function to validate the redirection to My Account page.
	 * 
	 * @author Jypsy
	 *
	 */
	public boolean isPageOpened() {
		try {
			TechnicalComponents.waitTill(lnk_AccountSummary, "visible");
			if (driver.getCurrentUrl().endsWith(urlsuffix)) {
				return true;
			} else {
				return false;
			}
		} catch (FrameworkException e) {
			throw new FrameworkException(
					"About Us Page Not Loaded within specified time.---" + e.getClass() + "---" + e.getMessage());
		}

	}

	public void clickBillingDetails() {
		TechnicalComponents.waitTill(menuAccountTab, "visible");
		TechnicalComponents.click(menuAccountTab, "My Account tab clicked");
		TechnicalComponents.click(lnk_myAccount, "My Account page  clicked");
		TechnicalComponents.click(lnk_BillingDetails, "Billing Details clicked");	
	}
	
	@FindBy(xpath = "//a[@id='userAcctTab_MainMenu']/following-sibling::ul//a[contains(text(),'My Team')]")
	public static WebElement lnk_MyTeam;
	
	
	
	public void clickMyTeam() {
		TechnicalComponents.waitTill(menuAccountTab, "visible");
		TechnicalComponents.click(menuAccountTab, "My Account tab clicked");
		TechnicalComponents.click(lnk_MyTeam, "My team page  clicked");
		
	}

	public void clickTransactionHistory() {
		TechnicalComponents.waitTill(menuAccountTab, "enable");
		TechnicalComponents.click(menuAccountTab, "My Account tab clicked");
		TechnicalComponents.click(lnk_myAccount, "My Account page  clicked");
		TechnicalComponents.click(lnk_TransactionHistory, "Transaction History clicked");	
	}
	
	public void clickReferAFriend() {
		TechnicalComponents.click(menuAccountTab, "My Account tab clicked");
		TechnicalComponents.click(lnk_myAccount, "My Account page  clicked");
		TechnicalComponents.click(lnk_ReferAFriend, "Refer A friend  clicked");	
	}
	
	public void clickMyAccountTab() {
		TechnicalComponents.click(menuAccountTab, "My Account tab clicked");
		TechnicalComponents.click(lnk_myAccount, "My Account page  clicked");
		
	}
}
