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

import com.gargoylesoftware.htmlunit.javascript.host.dom.Document;
import com.gargoylesoftware.htmlunit.javascript.host.file.Blob;
import com.relevantcodes.extentreports.LogStatus;

import config.FrameworkException;
import config.TestSetup;
import reusablecomponents.BusinessComponents;
import reusablecomponents.TechnicalComponents;

/**
 * Object repository of the Profile Default page.
 * 
 * @author Jypsy
 *
 */
public class ProfileDefaultPage extends TechnicalComponents {

	WebDriver driver;

	public static String ProfileDefault_title = "SurveyMonkey - Profile Set-Up";
	public static String urlsuffix = "profile/default";

	public ProfileDefaultPage(WebDriver driver) {

		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//div[@class='groupHeaderLinks']//a")
	public static WebElement lnk_CrossIconClose;

	@FindBy(xpath = "//a[@class='sm-profile__wf-exit-link']")
	public static WebElement lnk_CrossIconCloseinAnchorTag;

	@FindBy(xpath = "//div[text()='Tell us a little about yourself.']")
	public static WebElement textHeader;

	/**
	 * function to validate the redirection to about us page.
	 * 
	 * @author Jypsy
	 *
	 */
	public boolean isPageOpened() {
		try {
			TechnicalComponents.waitTill(textHeader, "visible");
			if (driver.getCurrentUrl().contains(urlsuffix)) {
				return true;
			} else {
				return false;
			}
		} catch (FrameworkException e) {
			throw new FrameworkException(
					"profile default page Loaded within specified time.---" + e.getClass() + "---" + e.getMessage());
		}

	}

//	public void click_CrossIcon() {
//		WebElement element = null;
//		try {
//			element = lnk_CrossIconCloseinAnchorTag;
//			System.out.println();			TechnicalComponents.waitTill(lnk_CrossIconCloseinAnchorTag, "visible");
//
//		} catch (NoSuchElementException e) {
//			TechnicalComponents.waitTill(lnk_CrossIconClose, "visible");
//			element = lnk_CrossIconClose;
//		}
//		if (element != null) {
//			TechnicalComponents.click_exceptional(element, "Cross Icon clicked");
//		}
//	}

	public void click_CrossIcon() {
		WebElement element = null;
		try {
			if (driver.getCurrentUrl().contains("4.com")) {
				element = lnk_CrossIconClose;
			} 
			if(driver.getCurrentUrl().contains("1.com")) {
				element = lnk_CrossIconCloseinAnchorTag;
			}
			if (element != null) {
				TechnicalComponents.click_exceptional(element, "Cross Icon clicked");
			}
		} catch (Exception e) {
			throw new FrameworkException(
					"profile default page Loaded within specified time.---" + e.getClass() + "---" + e.getMessage());
		}
		
	}

	@FindBy(xpath = "//span[@class='smf-icon notranslate']/following-sibling::b")
	public static WebElement txtInvoiceNumber;

	public String getInvoiceNumber() {
		String ActualInvoice = TechnicalComponents.getAttribute(txtInvoiceNumber, "text", "invoice number");
		return ActualInvoice;

	}

}
