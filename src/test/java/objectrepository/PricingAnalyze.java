package objectrepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import config.FrameworkException;
import reusablecomponents.TechnicalComponents;

public class PricingAnalyze {
	WebDriver driver;

	public static String PricingAnalyze_title = "SurveyMonkey Plans and Pricing";
	public static String urlsuffix = "/pricing/analyze/";

	public PricingAnalyze(WebDriver driver) {

		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	
	@FindBy(xpath = "//h1[contains(text(),'FLEX Plan')]")
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
					"Pricing analyze page Loaded within specified time.---" + e.getClass() + "---" + e.getMessage());
		}

	}
}

