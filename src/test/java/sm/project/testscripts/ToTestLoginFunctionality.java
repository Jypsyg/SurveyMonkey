package sm.project.testscripts;

import static org.testng.Assert.assertTrue;

import org.testng.SkipException;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.LogStatus;

import config.FrameworkException;
import config.TestSetup;
import reusablecomponents.BusinessComponents;
import reusablecomponents.Utilities;

/**
 * Test Class to validate My Services section.
 * 
 * @author jypsy
 *
 */
public class ToTestLoginFunctionality extends BusinessComponents {

	/**
	 * JavaDoc
	 */
	@Test(dataProvider = "loginSheet", dataProviderClass = data.TestData.class)
	public void tologin(String testDesc, String complexity, String username, String password, String errorMessage) {
	setParametersPerTestCase(testDesc, complexity);
		if (toBeTested) {
			try {
				navigatetoUrl(Utilities.getProperty("ENVIRONMENT_URL"));
				verify_Redirection("homepage");
				clickOnLoginLink("homepage");
				verify_Redirection("login");
				loginToApp(username, password);
				verify_Redirection("dashboard");
				
				if (errorMessage.equals("")) {
					logger.log(LogStatus.PASS, "Test Case: " + testDesc + " passed.");
					assertTrue(true, "Test Case: " + testDesc + " Passed");
				}
			} catch (FrameworkException e) {
				if (errorMessage.equals("")) {
					logger.log(LogStatus.FAIL, e.getMessage() + logger.addScreenCapture(screenshot(driver)));
					logger.log(LogStatus.FAIL, "Test Case: " + testDesc + " failed.");
					assertTrue(false, "Test Case: " + testDesc + " failed.");
				} else {
					try {
						logger.log(LogStatus.INFO, e.getMessage() + logger.addScreenCapture(screenshot(driver)));
						verifyError("login",errorMessage);
						logger.log(LogStatus.PASS, "Test Case: " + testDesc + " passed.");
					} catch (FrameworkException ex) {
						logger.log(LogStatus.FAIL, ex.getMessage() + logger.addScreenCapture(screenshot(driver)));
						logger.log(LogStatus.FAIL, "Test Case: " + testDesc + " failed.");
						assertTrue(false, "Test Case: " + testDesc + " failed.");
					}

				}
			}
		} else {
			logger.log(LogStatus.SKIP,
					"Test Case: " + testDesc+"  skipped.");
			throw new SkipException(
					"Test Case: " + testDesc+"  skipped.");
		}
	}

}
