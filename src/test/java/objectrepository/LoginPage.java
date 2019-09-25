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
 * @author jypsy
 *
 */
public class LoginPage extends TechnicalComponents {

	WebDriver driver;

	public LoginPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public String Login_Title = "Log in to your account";
	public static String urlloginlinksuffix = "/user/sign-in/?ut_source=megamenu";

	@FindBy(xpath = "//input[@id='username']")
	public static WebElement txt_Username;

	@FindBy(xpath = "//input[@id='password']")
	public static WebElement txt_Passowrd;

	@FindBy(xpath = "//button[@type='submit']")
	public static WebElement btn_login;
	
	@FindBy(xpath = "//div[@class='error-message']//li")
	public static WebElement errorMsg;

	/**
	 * function to validate the redirection to Login page.
	 * 
	 * @author Jypsy
	 *
	 */
	public boolean isPageOpened() {
		try {
			TechnicalComponents.waitTill(btn_login, "visible");
			if (driver.getCurrentUrl().endsWith(urlloginlinksuffix)) {
				return true;
			} else {
				return false;
			}
		} catch (FrameworkException e) {
			throw new FrameworkException(
					"Login page Not Loaded within specified time.---" + e.getClass() + "---" + e.getMessage());
		}
		
	}

	/**
	 * function to enter user name on Login page.
	 * 
	 * @author Jypsy
	 *
	 */
	public void enterUserName(String username) {
		TechnicalComponents.type(txt_Username, username, "username field");
	}

	public void enterPassWord(String password) {
		TechnicalComponents.type(txt_Passowrd, password, "password field");
	}

	public void clickLoginButton() {
		TechnicalComponents.click(btn_login, "Login button");
	}

	public void verifyErrorMsg(String errormsg) {
		TechnicalComponents.verifyAttribute(errorMsg, "text",errormsg , "error msg Verification");
	}
	
}
