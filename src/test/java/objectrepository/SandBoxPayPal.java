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
public class SandBoxPayPal extends TechnicalComponents {

	WebDriver driver;
	public static String urlsuffix = "paypal.com";	

	public SandBoxPayPal(WebDriver driver) {

		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//input[@id='email']")
	public static WebElement txtEmail;
	
	/**
	 * function to validate the redirection to plans and pricing page.
	 * 
	 * @author Jypsy
	 *
	 */
	public boolean isPageOpened() {
		try {
			TechnicalComponents.waitTill(txtEmail, "visible");
			if (driver.getCurrentUrl().contains(urlsuffix)) {
				return true;
			} else {
				return false;
			}
		} catch (FrameworkException e) {
			throw new FrameworkException(
					"Sand box paypal Page Loaded within specified time.---" + e.getClass() + "---" + e.getMessage());
		}

	}
	
	
	
	@FindBy(xpath = "//input[@id='create-title-input']")
	public static WebElement txtAreainput;

	public void  enterEmail() {
		TechnicalComponents.type(txtEmail, "SMQA@ppsandbox.com", "Survey Name entered");
	}
	
	@FindBy(xpath = "//input[@id='password']")
	public static WebElement txtPassword;
	
	public void  clickNext() {
		TechnicalComponents.click(btnNExt, "Next clicked");
	}
	@FindBy(xpath = "//button[@name='btnNext']")
	public static WebElement btnNExt;
	
	public void  enterPassword() {
		TechnicalComponents.waitTill(txtPassword, "visible");;
		TechnicalComponents.type(txtPassword, "smqa1234", "PAssword  entered");
	}
	
	@FindBy(xpath = "//button[@name='btnLogin']")
	public static WebElement btnLogin;
	
	public void  clickLogin() {
		TechnicalComponents.click(btnLogin, "login clicked");
	}
	@FindBy(xpath = "//button[@name='Agree & Continue']")
	public static WebElement btnAgreeContinue;
	
	public void  clickAgreeContinue() {
		TechnicalComponents.waitTill(btnAgreeContinue, "visible");
		TechnicalComponents.click(btnAgreeContinue, "Continue clicked");
		//TechnicalComponents.visibleInvisible(loaderIcon);
	}
	
	@FindBy(xpath = "//h2[contains(text(),'Your payment is being processed.')]")
	public static WebElement loaderIcon;
	
}
