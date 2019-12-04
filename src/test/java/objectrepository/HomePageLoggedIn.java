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
public class HomePageLoggedIn extends TechnicalComponents {

	WebDriver driver;


	public static String urlsuffix = "/home/?ut_source=header";

	@FindBy(xpath = "//button[@type='submit']")
	public static WebElement btnAddQues;

	@FindBy(xpath = "//input[@id='create-title-input']")
	public static WebElement txtSurveyName;
	

	

	public HomePageLoggedIn(WebDriver driver) {
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
			TechnicalComponents.waitTill(btnAddQues, "visible");
			if (driver.getCurrentUrl().contains(urlsuffix)) {
				return true;
			} else {
				return false;
			}
		} catch (FrameworkException e) {
			throw new FrameworkException(
					"Home page Loaded within specified time.---" + e.getClass() + "---" + e.getMessage());
		}

	}

	public void enterSurveyName(String SurveyName) {
		if (txtSurveyName.isEnabled())
		{
			TechnicalComponents.type(txtSurveyName, SurveyName, "Entered Survey name");
		}
	}
	
	public void clickAddQuestion() {
		TechnicalComponents.click(btnAddQues, "Add question clicked");
	}
	


}
