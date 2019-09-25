package objectrepository;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.server.handler.GetCurrentUrl;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

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
public class PlansAndPricingSummaryPage extends TechnicalComponents {

	WebDriver driver;

	public static String PlansAndPricing_title = "SurveyMonkey Plans and Pricing";
	public static String urlsuffix = "/pricing";

	public PlansAndPricingSummaryPage(WebDriver driver) {

		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//h1[@class='heading page-heading']")
	public static WebElement headingText;

	/**
	 * function to validate the redirection to plans and pricing page.
	 * 
	 * @author Jypsy
	 *
	 */
	public boolean isPageOpened() {
		try {
			TechnicalComponents.waitTill(headingText, "visible");
			if (driver.getCurrentUrl().contains(urlsuffix)) {
				return true;
			} else {
				return false;
			}
		} catch (FrameworkException e) {
			throw new FrameworkException("pricing summary  Page Not Loaded within specified time.---" + e.getClass()
					+ "---" + e.getMessage());
		}

	}

	@FindBy(xpath = "//button[@id='close-overlay']")
	public static List<WebElement> btncrossAdvantagePopUp;

	public void handleUpgradePopup() {
		TechnicalComponents.waitTill(2);
		if (btncrossAdvantagePopUp.size() > 0) {
			TechnicalComponents.click(btncrossAdvantagePopUp.get(0), "cross button popup handled");

		}

	}

	@FindBy(xpath = "//ul[@class='tab-nav']//a[contains(@href,'/pricing/individual')]")
	public static WebElement lnk_IndividualPricingPage;

	public void clickIndividualPlans() {
		TechnicalComponents.click(lnk_IndividualPricingPage, "IndividualPlans");
	}

	@FindBy(xpath = "//th[@class='  ft__header ft__header-cta']//a[contains(@href,'31')]")
	public static WebElement lnk_StandardMonthlyPlan;

	@FindBy(xpath = "//th[@class='ft__header ft__header-cta']//a[contains(@href,'32')]")
	public static WebElement lnk_StandardAnnualPlan;

	@FindBy(xpath = "//th[@class=' highlight ft__header ft__header-cta']//a")
	public static WebElement lnk_AdvanatagePlan;

	@FindBy(xpath = "//div[@class='constrain-width plan-section']//a[contains(@href,'36')]")
	public static WebElement lnk_PremierPlan;

	@FindBy(xpath = "//button[@data-switch='standard_annual']")
	public static WebElement lnk_SaveWithAnnual;

	@FindBy(xpath = "//button[@data-switch='standard_monthly']")
	public static WebElement lnk_SeeMonthlyPlan;

	/**
	 * function to click on select button on plans and pricing page.
	 * 
	 * @author Jypsy
	 *
	 */

	public void clickMonthlyPlan() {
		wait.until(ExpectedConditions.visibilityOf(lnk_StandardMonthlyPlan));
		TechnicalComponents.click(lnk_StandardMonthlyPlan, "clicked on packageid 31");
	}

	public void clickStandardAnnualPlan() {
		wait.until(ExpectedConditions.visibilityOf(lnk_SaveWithAnnual));
		TechnicalComponents.click(lnk_StandardAnnualPlan, "clicked on standard annual plan - 32");

	}

	public void clickAdvantagePlan() {
		wait.until(ExpectedConditions.visibilityOf(lnk_AdvanatagePlan));
		TechnicalComponents.click(lnk_AdvanatagePlan, "clicked on packageid 34");
	}

	public void clickPremierPlan() {
		wait.until(ExpectedConditions.visibilityOf(lnk_PremierPlan));
		TechnicalComponents.click(lnk_PremierPlan, "clicked on packageid 36");
	}

	@FindBy(xpath = "//ul//div[@data-name='standard_monthly']//a[@class='wds-button wds-button--ghost plan-cta-btn standard']")
	public static WebElement lnkSignupMonthlyPlan;

	public void signupMonthlyPlan() {
		TechnicalComponents.click(lnkSignupMonthlyPlan, "Sign Up Monthly  Plan");

	}

	@FindBy(xpath = "//ul//div[@data-name='standard_annual']//a[@class='wds-button wds-button--ghost plan-cta-btn standard']")
	public static WebElement lnkSignupStnadardAnnualPlan;

	@FindBy(xpath = "//a[@data-switch='standard_monthly']")
	public static WebElement lnkSwitchAnnualPlan;

	public void signupStandardAnnualPlan() {
		TechnicalComponents.click(lnkSwitchAnnualPlan, "toggel link Clicked");
		TechnicalComponents.click(lnkSignupStnadardAnnualPlan, "Sign Up Stnadard Annual Plan");

	}

	@FindBy(xpath = "//ul//a[@class='wds-button wds-button--ghost plan-cta-btn advantage']")
	public static WebElement lnkSignupAdvantagePlan;

	public void signupAdvantagePlan() {
		TechnicalComponents.click(lnkSignupAdvantagePlan, "Sign Up Advantage Plan");

	}

	@FindBy(xpath = "//ul//a[@class='wds-button wds-button--ghost plan-cta-btn premier']")
	public static WebElement lnkSignupPremierPlan;

	public void signupPremierPlan() {
		TechnicalComponents.click(lnkSignupPremierPlan, "Sign Up Premier  Plan");

	}
	
	@FindBy(xpath = "//a[@class='learn-more-link']/preceding::a[@class='learn-more-link']")
	public static WebElement lnkEduPricing;

	public void clickeduPlans() {
		TechnicalComponents.click(lnkEduPricing, "edu pricing");
	}

}
