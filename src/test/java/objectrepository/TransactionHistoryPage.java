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
 * Object repository of the billing history page.
 * 
 * @author Jypsy
 *
 */
public class TransactionHistoryPage extends TechnicalComponents {

	WebDriver driver;

	private String TransactionHistory_title = "SurveyMonkey - My Account: Transaction History";
	public static String urlsuffix = "/billing/history/";

	public TransactionHistoryPage(WebDriver driver) {

		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//div[@class='customer-support-info']")
	public static WebElement customerupportInfo;

	@FindBy(xpath = "//div[@id='billing-history-panel']/table/tbody/tr/td[1]")
	public static WebElement txtInvoiceNumber;

	@FindBy(xpath = "//div[@id='billing-history-panel']/table/tbody/tr/td[2]")
	public static WebElement txtBillingDate;

	@FindBy(xpath = "//div[@id='billing-history-panel']/table/tbody/tr/td[3]")
	public static WebElement txtPlanName;

	@FindBy(xpath = "//div[@id='billing-history-panel']/table/tbody/tr/td[5]")
	public static WebElement txtInvoiceStatus;

	@FindBy(xpath = "//div[@id='billing-history-panel']/table/tbody/tr/td[7]")
	public static WebElement txtAmount;

	@FindBy(xpath = "//a[contains(@href,'/billing/pw/credits-invoice')]")
	public static WebElement btnAddCredit;

	@FindBy(xpath = "//a[@data-action='print']")
	public static WebElement btnPrint;

	/**
	 * function to validate the redirection to billing history page.
	 * 
	 * @author Jypsy
	 *
	 */
	public boolean isPageOpened() {
		try {
			TechnicalComponents.waitTill(customerupportInfo, "visible");
			if (driver.getCurrentUrl().contains(urlsuffix)) {
				return true;
			} else {
				return false;
			}
		} catch (FrameworkException e) {
			throw new FrameworkException(
					"Transaction history Not Loaded within specified time.---" + e.getClass() + "---" + e.getMessage());
		}

	}

	public void clickAddCreditButton() {
		TechnicalComponents.click(btnAddCredit, "Add Credit button clicked");
	}

	public void clickPrintButton() {
		TechnicalComponents.click(btnPrint, "Print button clicked");
	}

	@FindBy(xpath = "//div[@id='billing-history-panel']//table/tbody/tr[1]/td")
	public static List<WebElement> ListPurchaseActivity;

	@FindBy(xpath = "//div[@id='group-history-panel']//table/tbody/tr[1]/td")
	public static List<WebElement> ListTeamtPurchaseActivity;

	public void verifyPurcahseActivityDetails(String invoice, String BillingDate, String Description, String Seats,
			String Status, String PayNow, String Amount) {
		try {
			BillingDate = getDate("currentday", "MMM dd, yyyy").trim();
			Amount = Amount.substring(0, 7);
			if (invoice.contains("#")) {
				invoice = invoice.replace("#", "");
			}

			// System.out.println(Amount);
			String ArrayPurchaseDetails[] = { invoice, BillingDate, Description, Seats, Status, PayNow, Amount };
			for (int i = 0; i < ListPurchaseActivity.size(); i++) {
				if (!(ListPurchaseActivity.get(i).getText().isEmpty())) {
					TechnicalComponents.verifyAttribute(ListPurchaseActivity.get(i), "text-contains",
							ArrayPurchaseDetails[i], ArrayPurchaseDetails[i]);
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	public void verifyPurcahseTeamActivityDetails(String invoice, String BillingDate, String Description, String Seats,
			String Status, String PayNow, String Amount) {
		try {
			BillingDate = getDate("currentday", "MMM dd, yyyy").trim();
			Amount = Amount.substring(0, 7);
			String ArrayPurchaseDetails[] = { invoice, BillingDate, Description, Seats, Status, PayNow, Amount };
			for (int i = 0; i < ArrayPurchaseDetails.length; i++) {
				if (!(ListPurchaseActivity.get(i).getText().isEmpty())) {
					TechnicalComponents.verifyAttribute(ListTeamtPurchaseActivity.get(i + 1), "text-contains",
							ArrayPurchaseDetails[i], ArrayPurchaseDetails[i]);
				}
			}

		} catch (Exception e) {
			// TODO: handle exception
		}
		logger.log(LogStatus.PASS, "Trnsaction History details verified successfully " + invoice + " : " + BillingDate
				+ ":" + Description + ":" + Seats + ":" + Status + ":" + PayNow + ":" + Amount);
	}
	
	@FindBy(xpath = "(//table[@class='info-table']//tr//a)[1]")
	public static WebElement lnkLatest;
	
	@FindBy(xpath = "(//table[@class='info-table']//tr//a//a)[1]")
	public static WebElement lnkTaxamoLatest;
	
	
			
	public void clickLatestInvoice() {
		TechnicalComponents.click(lnkLatest, "latest invoice clciked");
	}
	
	public void clickLatestTaxamoInvoice() {
		TechnicalComponents.click(lnkTaxamoLatest, "latest taxamo invoice clciked");
	}
	public void verifyTaxamoInvoice(String PlanName) {
		try {
			WebElement ele = driver.findElement(By.xpath("//td[contains(text(),'" + PlanName + "')])"));
			TechnicalComponents.isDisplayed(ele, "taxamo data");
			logger.log(LogStatus.PASS, "data verified", "data verified");
		} catch (Exception e) {
			throw new FrameworkException("taxamo data not present.---" + e.getClass() + "---" + e.getMessage());
		}

	}
	
	@FindBy(xpath = "//p[contains(text(),'Contact your CSM to access past invoices.')]")
	public static WebElement txtCSMInvoice;
	
	public void verifyCSMInvoice() {
		txtCSMInvoice.isDisplayed();
	}
	
	
}
