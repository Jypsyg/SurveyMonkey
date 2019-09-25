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
 * Object repository of the SignUp page.
 * 
 * @author Jypsy
 *
 */
public class SignUpPage extends TechnicalComponents {

	WebDriver driver;

	public SignUpPage(WebDriver driver) {

		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public static String SignUp_Title = "Sign up for a FREE SurveyMonkey account";

	public static String urlsuffix = "/user/sign-up/";

	@FindBy(xpath = "//input[@id='username']")
	public static WebElement text_Username;

	@FindBy(xpath = "//input[@id='password']")
	public static WebElement text_Password;

	@FindBy(xpath = "//input[@id='email']")
	public static WebElement text_Email;

	@FindBy(xpath = "//input[@id='first_name']")
	public static WebElement text_FirstName;

	@FindBy(xpath = "//input[@id='last_name']")
	public static WebElement text_LastName;

	@FindBy(xpath = "//button[@id='submitform']")
	public static WebElement btn_CreatAccount;

	/**
	 * function to validate the redirection to SignUp page.
	 * 
	 * @author Dsavita
	 *
	 */
	public boolean isPageOpened() {
		try {
			TechnicalComponents.waitTill(text_FirstName, "visible");
			if (driver.getCurrentUrl().contains(urlsuffix)) {
				return true;
			} else {
				return false;
			}
		} catch (FrameworkException e) {
			throw new FrameworkException(
					"Signup Not Loaded within specified time.---" + e.getClass() + "---" + e.getMessage());
		}

	}

	public void enterSignUpformsDetails(String username, String password, String email, String firstname,
			String LastName) {

		TechnicalComponents.type(text_Username, username, "username ");
		TechnicalComponents.type(text_Password, password, "password ");
		TechnicalComponents.type(text_Email, email, "email ");
		TechnicalComponents.type(text_FirstName, firstname, "firstname ");
		TechnicalComponents.type(text_LastName, LastName, "lastname");

	}

	public void clickCreatAccountbtn() {
		TechnicalComponents.click(btn_CreatAccount, "Create account");
	}
}
