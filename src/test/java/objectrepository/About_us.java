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
public class About_us extends TechnicalComponents {

	WebDriver driver;

	public static String Aboutus_Title = "Learn More About WM Canada | Waste Management Canada";
	private String Home_title = "Waste Disposal and Collection - Recycling Solutions | Waste Management Canada";
	public static String urlsuffix = "about";

	@FindBy(xpath = "//div[@class='AboutUs']//a[text()='VISIT BLOG']")
	public static WebElement lnk_VisitBlog;

	@FindBy(xpath = "//div[@class='AboutUs']//a[text()='VISITER LE BLOGUE']")
	public static WebElement lnk_VisitBlog_French;

	@FindBy(xpath = "//div[@class='AboutUs']//a[text()='charitable giving']")
	public static WebElement lnkCharitableGiving;

	@FindBy(xpath = "//div[@class='AboutUs']//a[text()='community initiatives']")
	public static WebElement lnkCommunityInitiatvies;

	@FindBy(xpath = "//div[@class='AboutUs']//a[text()='wildlife habitat']")
	public static WebElement lnkWildlifeHabitat;

	@FindBy(xpath = "//div[@class='AboutUs']//a[text()='SEARCH JOBS']")
	public static WebElement lnkSearchJob;

	@FindBy(xpath = "//div[@class='AboutUs']//a[text()='VIEW BENEFITS']")
	public static WebElement lnkViewBenefits;

	@FindBy(xpath = "//div[@class='AboutUs']//a")
	public static List<WebElement> lnk_AboutUs;

	public About_us(WebDriver driver) {
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
			TechnicalComponents.waitTill(lnk_VisitBlog, "visible");
			if (driver.getCurrentUrl().endsWith(urlsuffix)) {
				return true;
			} else {
				return false;
			}
		} catch (FrameworkException e) {
			throw new FrameworkException(
					"About Us Page Not Loaded within specified time.---" + e.getClass() + "---" + e.getMessage());
		}

	}

	public void click_AboutUsLinks(String value) {

		WebElement link = TechnicalComponents
				.getElementFromList(lnk_AboutUs, "text-contains", value, "About Us Page Links").getElement();
		TechnicalComponents.scroll(link);
		TechnicalComponents.click(link, "click Link" + link);
		if (link.getText().equals("SEARCH JOBS")) {
			TechnicalComponents
					.switchToNewWindow("Careers at Waste Management | Job opportunities at Waste Management");
		}
	}

	public void SwitchtoAboutUsPage() throws FrameworkException {

		TechnicalComponents.switchToNewWindow(Aboutus_Title);
	}
}
