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
 * Object repository of the billingConfirmed page.
 * 
 * @author Jypsy
 *
 */
public class TeamAdd extends TechnicalComponents {

	WebDriver driver;
	public static String urlsuffix = "/team/add/";
	
	
	public TeamAdd(WebDriver driver) {

		this.driver = driver;
		PageFactory.initElements(driver, this);
	}


	@FindBy(xpath = "//textarea[@id='emails']")
	public static WebElement txtAreaEmail;


	/**
	 * function to validate the redirection to billing/confirm page.
	 * 
	 * @author Jypsy
	 *
	 */
	public boolean isPageOpened() {
		try {
			TechnicalComponents.waitTill(txtAreaEmail, "visible");
			if (driver.getCurrentUrl().contains(urlsuffix)) {
				return true;
			} else {
				return false;
			}
		} catch (FrameworkException e) {
			throw new FrameworkException(
					"Team Add  page Not Loaded within specified time.---" + e.getClass() + "---" + e.getMessage());
		}

	}
	
	public void enterEmail(String email) {
		TechnicalComponents.waitTill(btndisable, "visible");
		TechnicalComponents.type(txtAreaEmail, email, "email enetered");
	}
	
	@FindBy(xpath = "//input[@id='add-user-acknowledge']")
	public static WebElement chkBox;
	
	public void clickCheckbox() {
		TechnicalComponents.click(chkBox, "checkbox checked");
	}
	@FindBy(xpath = "//button[@id='submit-button']")
	public static WebElement btnSendInviattaion;
	
	@FindBy(xpath = "//button[@disabled='disabled']")
	public static WebElement btndisable;
	
	@FindBy(xpath = "//h3[contains(text(),'Your invitation was sent successfully!')]")
	public static WebElement txtPopUp;
	
	
	
	public void clickSendInvitation() {
		TechnicalComponents.click(btnSendInviattaion, "send invitation clicked ");
	}

	public void visibleInvisible() {
		TechnicalComponents.visibleInvisible(txtPopUp);
	}


}
