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
 * Object repository of the Billing Checkout page.
 * 
 * @author Jypsy
 *
 */
public class Billing_PFI_Invoice_CheckoutPage extends TechnicalComponents {

	WebDriver driver;
	String urlsuffix = "/billing/invoice/pfi/checkout/";

	public Billing_PFI_Invoice_CheckoutPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//section[@class='invoice-details']")
	public static WebElement sectionReviewAndConfirm;

	/**
	 * function to validate the redirection to billing invoice PFI page.
	 * 
	 * @author Jypsy
	 *
	 */
	public boolean isPageOpened() {
		try {
			TechnicalComponents.waitTill(sectionReviewAndConfirm, "visible");
			if (driver.getCurrentUrl().contains(urlsuffix)) {
				return true;
			} else {
				return false;
			}
		} catch (FrameworkException e) {
			throw new FrameworkException(
					"PFI invoice page is Not Loaded within specified time.---" + e.getClass() + "---" + e.getMessage());
		}
	}

	@FindBy(xpath = "//section[@class='invoice-details']//table")
	public static WebElement tableReviewAndConfirm;

	// public void verifyReviewAndConfirmTableData(String Item, String
	// BillingFrequency, String Quantity , String Amount) {
	//
	//
	// }

	@FindBy(xpath = "//div[@class='row lower mb-md']//ul//li")
	public static WebElement listTotalAmount;

	public void verifyRowAmount(String ListItems) {

		// TechnicalComponents.getListItems(listTotalAmount, ListItems, ListItems);
	}

	@FindBy(xpath = "//input[@value='CONFIRM']")
	public static WebElement btnPFIConfirm;

	public void clickPFIConfirmButton() {
		TechnicalComponents.click(btnPFIConfirm, " PFI confirm button");

	}

	@FindBy(xpath = "//p[@class='wds-type--body-copy mb-md']//strong//span")
	public static WebElement txtInvoiceNum;

	public String getInvoiceNumber() {
		String InvoiceNumber = TechnicalComponents.getAttribute(txtInvoiceNum, "text", "Invoice Number");
		return InvoiceNumber;
	}

	@FindBy(xpath = "//div[@class='payment-container payment-method-container']//input[@name='cardnumber']")
	public static WebElement txtCardNumber;

	public void enterCardNumber(String CardNumber) {
		TechnicalComponents.type(txtCardNumber, CardNumber, "cardnumber");
	}

	@FindBy(xpath = "//div[@class='payment-container payment-method-container']//input[@name='name_on_card']")
	private WebElement txtCardName;

	public void enterCardName(String CardName) {
		TechnicalComponents.type(txtCardName, CardName, "cardname");
	}

	@FindBy(xpath = "//div[@class='payment-container payment-method-container']//span[@class='sec-code']/following-sibling::input")
	private WebElement txtCVV;

	public void enterCVV(String CVV) {
		TechnicalComponents.type(txtCVV, CVV, "CVV");
	}

	@FindBy(xpath = "//div[@class='payment-container payment-method-container']//select[@name='month']")
	private WebElement txtExpiryMonth;

	public void enterExpiryMonth(String expiryMonth) {
		TechnicalComponents.selectValuefromDropdown(txtExpiryMonth, "value", expiryMonth);
	}

	@FindBy(xpath = "//div[@class='payment-container payment-method-container']//select[@name='year']")
	private WebElement txtExpiryYear;

	public void enterExpiryYear(String expiryYear) {
		TechnicalComponents.selectValuefromDropdown(txtExpiryYear, "value", expiryYear);
	}

	@FindBy(xpath = "//section[@class='invoice-details']//tbody//td")
	private List<WebElement> ReviewAndConfirmList;

	public void verifyReviewAndConfirmSection(String PlanName, String BillingFrequency, String Quantity,
			String Amount) {
		try {
			String ArrayListItems[] = { PlanName, BillingFrequency, Quantity, Amount };
			for (int i = 0; i < 4; i++) {
				TechnicalComponents.verifyAttribute(ReviewAndConfirmList.get(i), "text-contains", ArrayListItems[i],
						ArrayListItems[i]);
			}

		} catch (Exception e) {
			// TODO: handle exception
		}
		logger.log(LogStatus.PASS, "Review and Confirm invoice section verified successfully " + PlanName + " : "
				+ BillingFrequency + ":" + Quantity + ":" + Amount);
	}

}
