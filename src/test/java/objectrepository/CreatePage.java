package objectrepository;

import java.awt.Desktop.Action;
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
public class CreatePage extends TechnicalComponents {

	WebDriver driver;

	public static String urlsuffix = "/create/?";

	@FindBy(xpath = "//button[contains(text(),'Done')]")
	public static WebElement btnDone;

	@FindBy(xpath = "//div[@id='editQuestionContent']//tr[@class='title-row']//span[@class='placeholder']")
	public static WebElement txtEnterQuestion;

	@FindBy(xpath = "(//span[contains(text(),'Enter an answer choice')])[2]")
	public static WebElement textEnterAnswer;

	@FindBy(xpath = "//div[@id='editQuestion']//ul//a[contains(text(),'OPTIONS')]")
	public static WebElement lblOptions;

	@FindBy(xpath = "//label[contains(text(),' Require an Answer to This Question ')]")
	public static WebElement chkRequireQuestion;

	@FindBy(xpath = "//fieldset[@id='randomAssignmentSetting']/following-sibling::div/a[contains(text(),'SAVE')]")
	public static WebElement btnReqSave;

	@FindBy(xpath = "//a[@data-location='collect']")
	public static WebElement lnkCollectResponse;

	public CreatePage(WebDriver driver) {
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
			TechnicalComponents.waitTill(btnDone, "visible");
			if (driver.getCurrentUrl().contains(urlsuffix)) {
				return true;
			} else {
				return false;
			}
		} catch (FrameworkException e) {
			throw new FrameworkException(
					"Create Page within specified time.---" + e.getClass() + "---" + e.getMessage());
		}

	}

	public void enterQuestion(String enterQuestion) {

		TechnicalComponents.typeWithoutClear(txtEnterQuestion, enterQuestion, "question entered");

	}

	public void enterAnswer(String enterAnswer) {
		{
			TechnicalComponents.waitTill(textEnterAnswer, "visible");
			TechnicalComponents.typeWithoutClear(textEnterAnswer, enterAnswer, "question entered");

		}
	}

	public void clickOption() {
		TechnicalComponents.click(lblOptions, "Options Clicked");
	}

	public void clickRequireOptions() {
		TechnicalComponents.click(chkRequireQuestion, "require Options Clicked");
	}

	public void clickRequireSAVE() {
		TechnicalComponents.click(btnReqSave, "require save btn  Clicked");
	}

	public void clickCollectResponse() {
		TechnicalComponents.click(lnkCollectResponse, "collect response link  Clicked");
	}

	
	@FindBy(xpath = "//a[contains(@class,'hideFooterBtn')]")
	public static WebElement btnHideFooter;
	
	@FindBy(xpath = "//p[@class='sm-banner__text']")
	public static WebElement txtBAnner;
	
	
	public void HideFooter() {
		try {
			TechnicalComponents.click(btnHideFooter, "HideFooter");
			TechnicalComponents.visibleInvisible(txtBAnner);
		} catch (Exception e) {
			throw new FrameworkException(
					"Hide footer---" + e.getClass() + "---" + e.getMessage());
		}
	
		
	}
	
	@FindBy(xpath = "//a[contains(@href,'34')]")
	public static WebElement btnAdvanatge;
	
	public void clickAdv() {
		TechnicalComponents.waitTill(btnAdvanatge, "visible");
		TechnicalComponents.click(btnAdvanatge, "Plans elected");
	}
	
}
