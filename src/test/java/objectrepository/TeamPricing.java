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
public class TeamPricing extends TechnicalComponents {

	WebDriver driver;

	public static String TeamPricing_title = "SurveyMonkey Plans and Pricing";
	public static String urlsuffix = "/pricing/teams/";

	public TeamPricing(WebDriver driver) {

		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//div[@class='constrain-width plan-section teams-feature-list']")
	public static WebElement teamfeatures;

	/**
	 * function to validate the redirection to team plans and pricing page.
	 * 
	 * @author Jypsy
	 *
	 */
	public boolean isPageOpened() {
		try {
			TechnicalComponents.waitTill(teamfeatures, "visible");
			if (driver.getCurrentUrl().contains(urlsuffix)) {
				return true;
			} else {
				return false;
			}
		} catch (FrameworkException e) {
			throw new FrameworkException(
					"team summary Page Not Loaded within specified time.---" + e.getClass() + "---" + e.getMessage());
		}

	}

	@FindBy(xpath = "(//a[@class='wds-button ft__cta-btn team premier'])[1]")
	public static WebElement lnk_TeamPremierPlan;

	@FindBy(xpath = "(//a[@class='wds-button ft__cta-btn team advantage'])[1]")
	public static WebElement lnk_TeamAdvantagePlan;

	/**
	 * function to click on select button on team plans and pricing page.
	 * 
	 * @author Jypsy
	 *
	 */

	public void clickTeamPremierPlan() {
		wait.until(ExpectedConditions.visibilityOf(lnk_TeamPremierPlan));
		TechnicalComponents.click(lnk_TeamPremierPlan, "clicked on packageid 136");
	}

	public void clickTeamAdvantagePlan() {
		wait.until(ExpectedConditions.visibilityOf(lnk_TeamAdvantagePlan));
		TechnicalComponents.click(lnk_TeamAdvantagePlan, "clicked on standard annual plan - 136");

	}
}
