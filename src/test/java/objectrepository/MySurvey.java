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
public class MySurvey extends TechnicalComponents {

	WebDriver driver;
	public static String urlsuffix = "/home//";	

	public MySurvey(WebDriver driver) {

		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//button[@class='wds-button']")
	public static WebElement btnAddQuestion;
	
	/**
	 * function to validate the redirection to plans and pricing page.
	 * 
	 * @author Jypsy
	 *
	 */
	public boolean isPageOpened() {
		try {
			TechnicalComponents.waitTill(btnAddQuestion, "visible");
			if (driver.getCurrentUrl().contains(urlsuffix)) {
				return true;
			} else {
				return false;
			}
		} catch (FrameworkException e) {
			throw new FrameworkException(
					"My Survey Page Loaded within specified time.---" + e.getClass() + "---" + e.getMessage());
		}

	}
	@FindBy(xpath = "//input[@id='create-title-input']")
	public static WebElement txtAreainput;

	public void  enterSurveyName(String SurveyName) {
		TechnicalComponents.type(txtAreainput, SurveyName, "Survey Name entered");
	}
	
	public void  clickAddQuestion() {
		TechnicalComponents.click(btnAddQuestion, "Add question button clicked");
	}
}
