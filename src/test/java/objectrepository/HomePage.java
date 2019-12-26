package objectrepository;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import config.FrameworkException;
import reusablecomponents.TechnicalComponents;

/**
 * Object repository of the about us page.
 * 
 * @author jypsy
 *
 */
public class HomePage extends TechnicalComponents {

	WebDriver driver;

	public HomePage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public static String Home_Title = "SurveyMonkey: The World’s Most Popular Free Online Survey Tool";

	@FindBy(xpath = "//ol[@class=\"user-area\"]//a[contains(@class,'log-in')]")
	public static WebElement lnk_LogIn;

	@FindBy(xpath = "//a[@class='sign-up static-buttons']")
	public static WebElement lnk_SignUp;
	
	@FindBy(xpath = "//ol[@class='nav clearfix']")
	public static WebElement txt_Header;
	

	public boolean isPageOpened() {
		try {
			TechnicalComponents.waitTill(lnk_LogIn, "visible");
			if (lnk_LogIn.isDisplayed() || txt_Header.isDisplayed()) {
				return true;
			} else {
				return false;
			}
		} catch (FrameworkException e) {
			throw new FrameworkException(
					"Home Page Not Loaded within specified time.---" + e.getClass() + "---" + e.getMessage());
		}

	}

	/**
	 * function to click on login link.
	 * 
	 * @author Jypsy
	 *
	 */
	public void click_LoginLink() {
		TechnicalComponents.click(lnk_LogIn, "login link");
	
	}

	/**
	 * function to click on Signup link.
	 * 
	 * @author Jypsy
	 *
	 */
	public void click_SignupLink() {

		TechnicalComponents.click(lnk_SignUp, "sign up link");
	}
	
	@FindBy(xpath = "//a[@id='userAcctTab_MainMenu']")
	public static WebElement menuAccountTab;
	
	@FindBy(xpath = "//ul[@class='nav-submenu']//a[contains(@href,'sign-out')]")
	public static WebElement tabSignOut;
	
	
	/**
	 * function to click on Signup link.
	 * 
	 * @author Jypsy
	 *
	 */
	public void click_LogOut() {

		TechnicalComponents.click(menuAccountTab, "my account tab link");
		TechnicalComponents.click(tabSignOut, "signout");
	}
	

	@FindBy(xpath = "//a[contains(text(),'SIGN IN')]")
	public static WebElement btnSIgnin;
	
	
	public void clickEUSignIN() {

		TechnicalComponents.click(btnSIgnin, "my account tab link");

	}

}
