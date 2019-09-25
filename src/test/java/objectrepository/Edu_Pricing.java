package objectrepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import config.FrameworkException;
import reusablecomponents.TechnicalComponents;

public class Edu_Pricing {
	WebDriver driver;
	String urlsuffix = "e=edu_faq";

	public Edu_Pricing(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//a[@class='learn-more-link view-pricing']")
	public static WebElement lnk_ViewPricing;
	
	/**
	 * function to validate the redirection to billing/confirm page.
	 * 
	 * @author Jypsy
	 *
	 */
	public boolean isPageOpened() {
		try {
			TechnicalComponents.waitTill(lnk_ViewPricing, "visible");
			if (driver.getCurrentUrl().contains(urlsuffix)) {
				return true;
			} else {
				return false;
			}
		} catch (FrameworkException e) {
			throw new FrameworkException(
					"EduPricing Not Loaded within specified time.---" + e.getClass() + "---" + e.getMessage());
		}

	}
	
	
	@FindBy(xpath = "//td[@data-package='standard_monthly']/a[@class='wds-button--upgrade wds-button wds-button wds-button--cta']")
	public static WebElement lnk_StandardMonthlyPlan;

	@FindBy(xpath = "//td[@data-package='standard_annual']//a[@class='wds-button--upgrade wds-button wds-button wds-button--cta']")
	public static WebElement lnk_StandardAnnualPlan;

	@FindBy(xpath = "//td[@data-package='advantage_annual']//a")
	public static WebElement lnk_AdvanatagePlan;

	@FindBy(xpath = "//td[@data-package='premier_annual']//a")
	public static WebElement lnk_PremierPlan;

	@FindBy(xpath = "//a[@data-switch='standard_annual']")
	public static WebElement lnk_SaveWithAnnual;

	@FindBy(xpath = "//a[@data-switch='standard_monthly']")
	public static WebElement lnk_SeeMonthlyPlan;

	public void clickEdUMonthlyPlan() {
		TechnicalComponents.wait.until(ExpectedConditions.visibilityOf(lnk_StandardMonthlyPlan));
		TechnicalComponents.click(lnk_StandardMonthlyPlan, "clicked on packageid 31");
	}

	public void clickEDUStandardAnnualPlan() {
		TechnicalComponents.wait.until(ExpectedConditions.visibilityOf(lnk_SaveWithAnnual));
		TechnicalComponents.click(lnk_SaveWithAnnual, "toggle link");
		TechnicalComponents.click(lnk_StandardAnnualPlan, "clicked on standard annual plan - 32");

	}

	public void clickEDUAdvantagePlan() {
		TechnicalComponents.wait.until(ExpectedConditions.visibilityOf(lnk_AdvanatagePlan));
		TechnicalComponents.click(lnk_AdvanatagePlan, "clicked on packageid 34");
	}

	public void clickEDUPremierPlan() {
		TechnicalComponents.wait.until(ExpectedConditions.visibilityOf(lnk_PremierPlan));
		TechnicalComponents.click(lnk_PremierPlan, "clicked on packageid 36");
	}
}
