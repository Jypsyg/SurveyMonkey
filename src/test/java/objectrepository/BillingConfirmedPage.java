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
public class BillingConfirmedPage extends TechnicalComponents {

	WebDriver driver;
	public static String urlsuffix = "/billing/confirmed";

	public BillingConfirmedPage(WebDriver driver) {

		this.driver = driver;
		PageFactory.initElements(driver, this);
	}


	public String thanksData = "Thanks for your";

	@FindBy(xpath = "//h5[@class='wds-type--card-title'][contains(text(),'NEED HELP?')]")
	public static WebElement txtNeedHelp;
	
	
	
	@FindBy(xpath = "//h3")
	public static WebElement txtThanks;

	/**
	 * function to validate the redirection to billing/confirm page.
	 * 
	 * @author Jypsy
	 *
	 */
	public boolean isPageOpened() {
		try {
			TechnicalComponents.waitTill(txtNeedHelp, "visible");
			String ThanksText = TechnicalComponents.getAttribute(txtThanks, "text", "Thanks Data");
			if (ThanksText.contains(thanksData) || driver.getCurrentUrl().contains(urlsuffix)) {

				return true;
			}

			else
				return false;

		} catch (

		FrameworkException e) {
			throw new FrameworkException("Billing confirm page Not Loaded within specified time.---" + e.getClass()
					+ "---" + e.getMessage());
		}
	}

	@FindBy(xpath = "//span[@class='notranslate']")
	public static WebElement txtInvoiceNumber;

	public String getInvoice() {

		String ActualInvoice = TechnicalComponents.getAttribute(txtInvoiceNumber, "text", "invoice number");
		return ActualInvoice;
	}

	@FindBy(xpath = "//a[contains(text(),'CREATE TEAM')]")
	public static WebElement btnCreateTeam;

	public void clickOnCreatTeam() {
		TechnicalComponents.click(btnCreateTeam, "Create Team clicked");

	}

	@FindBy(xpath = "((//div[@class='success-head large-margin xlarge-gutter']//p)[2]//span)[2]")
	public static WebElement txtDisclaimerData;

	public String getRecepitDisclaimer() {
		return TechnicalComponents.getAttribute(txtDisclaimerData, "text", "Receipt number");
	}

}
