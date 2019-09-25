package config;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import reusablecomponents.TechnicalComponents;
import reusablecomponents.Utilities;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import javax.mail.Flags;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.Flags.Flag;
import javax.mail.search.FlagTerm;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;

/**
 * Base file for all tests. Utilized for initializing test data files and
 * setting up driver/other required setups.
 * 
 * @author jypsy
 *
 */

public class TestSetup {

	public static ExtentReports report, log;
	public static ExtentTest logger, loggerForLogs;
	public static WebDriver driver;
	String testName, browser, reportName,excelReport;
	public static int testCasePassed = 0, testCaseFailed = 0, testCaseExecuted = 0, testCaseSkipped = 0;
	public static WebDriverWait wait;
	public static JavascriptExecutor js;
	public static long timeOut, driverWait, emailTimeOut;
	public static HashMap<String, String> testCasesToBeExecuted = new HashMap<String, String>();
	public static HashMap<String, String> testCaseBrowser = new HashMap<String, String>();
	public static HashMap<String, String> testCaseCategory = new HashMap<String, String>();
	public static HashMap<Integer,String> testCaseResult = new HashMap<Integer,String>(), 
										testCaseName = new HashMap<Integer,String>();
	public static String[][] testCases, frenchMappings;
	public boolean toBeTested = false;
	public static boolean isMobile = false, isFrench = false, emailVerification = false, masValidation = false;
	public static final String URL = "http://" + Utilities.getProperty("BS_USERNAME") + ":"
			+ Utilities.getProperty("BS_AUTOMATEKEY") + "@hub-cloud.browserstack.com/wd/hub";
	public static String runMode = "local";
	
	public static String testDataLocation,paymentsFileLocation;
	public static String testAPILocation;
	public static Session emailSession = null;
	public static Store store = null;
	public static Folder emailFolder = null;
	public static int testCaseCount;
	public static String defaultUserName;

	

	/**
	 * This function will be executed before each execution run.
	 * 
	 * @throws Throwable
	 * @throws FrameworkException
	 */
	@BeforeSuite
	public void beforeSuite() throws FrameworkException, Throwable {
		if (System.getProperty("TestCategory") != null) {
			System.setProperty("javax.net.ssl.trustStore", Utilities.getProperty("CERTIFICATES"));
			System.setProperty("javax.net.ssl.trustStorePassword", Utilities.getProperty("PASS"));
			runMode = "remote";
			if (System.getProperty("suitexml").toLowerCase().contains("fail")) {
				reportName = "Remote_" + System.getProperty("BrowserDevice") + "_SecondRun_";
			} else {
				reportName = "Remote_" + System.getProperty("BrowserDevice") + "_FirstRun_";
			}
			if (System.getProperty("language").toLowerCase().equals("french")) {
				isFrench = true;
			}
		} else {
			reportName = "LocalRun_";
			if (Utilities.getProperty("LANGUAGE").toLowerCase().equals("french")) {
				isFrench = true;
			}
		}
		
		defaultUserName = "at"+ Utilities.getTimeStamp("local").replace("-", "").replace(":", "") + "@testmail.com";
		/*try{
			API api = new API();
			HashMap<String, Object> response = api.activateUser("Automation", "User", defaultUserName, Utilities.getProperty("DEFAULT_LOGIN_PASSWORD"), false);
		}catch(Exception e) {
			
		}
		if(API.statusCode.equals("200")) {
			Utilities.setProperty("DEFAULT_LOGIN_USERNAME",defaultUserName);
			Utilities.setProperty("DEFAULT_USER_NAME","Automation User");
		}*/
		

		testDataLocation = Utilities.getProperty("TEST_DATA_LOCATION");
		
		
		if(Utilities.getProperty("EMAIL_VERIFICATION").equalsIgnoreCase("yes")) {
			emailVerification = true;
		}
		if (Utilities.getProperty("MAS_VALIDATION").toLowerCase().equals("yes")) {
			masValidation = true;
		}
		
		testAPILocation = Utilities.getProperty("API-TEST-DATA");

		testCases = Utilities.Read_Excel(testDataLocation, "TestCases");

		for (int i = 0; i < testCases.length; i++) {
			testCasesToBeExecuted.put(testCases[i][1], testCases[i][2]);
			testCaseBrowser.put(testCases[i][1], testCases[i][4]);
			testCaseCategory.put(testCases[i][1], testCases[i][3]);
		}
		testCasesToBeExecuted.put("testPayments", "Yes");
		testCaseBrowser.put("testPayments", "Chrome");
		testCaseCategory.put("testPayments", "web");
		reportName = Utilities.getCurrentDate().replace("/", "") + "/" + reportName
				+ Utilities.getTimeStamp("local").replace("-", "").replace(":", "");
		report = new ExtentReports("Reports/" + reportName + ".html");
		log = new ExtentReports("Logs/" + reportName + ".html");
		excelReport ="Reports/" + reportName+".xlsx"; 
		Utilities.setProperty("SCREENSHOTS_LOCATION_FOR_RUN",
				System.getProperty("user.dir") + "/Reports/" + Utilities.getCurrentDate().replace("/", "") + "/Screenshots/");
		Mappings.setFrenchMappings();
		testCaseCount = 0;
		
	}

	/**
	 * This method will be executed before each test run and is designed to invoke
	 * respective browser
	 * 
	 * @param method
	 * @throws Throwable
	 */
	@BeforeMethod
	public void beforeMethod(Method method) throws Throwable {
		testCaseCount++;
		driver = null;
		toBeTested = true;
		testName = method.getName();
		logger = report.startTest(testName, "");
		loggerForLogs = log.startTest(testName);
		emailTimeOut = Long.parseLong(Utilities.getProperty("EMAIL_TIME_OUT"));
		testCaseName.put(testCaseCount,testName);
		DesiredCapabilities capabilities = new DesiredCapabilities();
		try {
			if (testCasesToBeExecuted.get(testName).equalsIgnoreCase("Yes")) {
				if (runMode.equalsIgnoreCase("remote")) {
					if (!testCaseCategory.get(testName).toLowerCase().equals("api")) {
						testCaseCategory.put(testName, System.getProperty("TestCategory"));
						testCaseBrowser.put(testName, System.getProperty("BrowserDevice"));
					}
				}
				if (testCaseCategory.get(testName).equalsIgnoreCase("web") ) {
					if (!(testCaseBrowser.get(testName).equalsIgnoreCase("Default"))) {
						browser = testCaseBrowser.get(testName);
					} else {
						browser = Utilities.getProperty("BROWSER").toLowerCase();
					}
					switch (browser.toLowerCase()) {
					case "chrome":
						System.setProperty("webdriver.chrome.driver", "Drivers/" + "chromedriver.exe");
						driver = new ChromeDriver();
						break;
					case "firefox":
						System.setProperty("webdriver.gecko.driver", "Drivers/" + "geckodriver.exe");
						driver = new FirefoxDriver();
						break;
					case "internet explorer":
						System.setProperty("webdriver.ie.driver", "Drivers/" + "IEDriverServer.exe");
						capabilities = DesiredCapabilities.internetExplorer();
						capabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS,
								true);
						capabilities.setCapability(InternetExplorerDriver.IE_ENSURE_CLEAN_SESSION, true);
						capabilities.setCapability("nativeEvents", true);
						capabilities.setCapability("unexpectedAlertBehaviour", "accept");
						capabilities.setCapability("ignoreProtectedModeSettings", true);
						capabilities.setCapability("disable-popup-blocking", true);
						capabilities.setCapability("enablePersistentHover", true);
						capabilities.setCapability("ignoreZoomSetting", true);
						capabilities.setCapability(InternetExplorerDriver.REQUIRE_WINDOW_FOCUS, true);
						capabilities.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
						capabilities.setJavascriptEnabled(true);
						driver = new InternetExplorerDriver(capabilities);
						break;
					case "microsoft edge":
						System.setProperty("webdriver.edge.driver", "Drivers/" + "MicrosoftWebDriver.exe");
						capabilities = new DesiredCapabilities("MicrosoftEdge", "", Platform.WINDOWS);
						capabilities.setJavascriptEnabled(true);
						driver = new EdgeDriver(capabilities);
						break;
					default:
						throw new FrameworkException("Browser not configured.");
					}
					driver.manage().window().maximize();
				} else if (testCaseCategory.get(testName).toLowerCase().contains("remote")) {
					if (testCaseCategory.get(testName).toLowerCase().contains("web")) {
						Utilities.setConfigurations("web", testCaseBrowser.get(testName));
						capabilities.setCapability("os", RemoteConfigurations.OS);
						if (RemoteConfigurations.BROWSER != "") {
							capabilities.setCapability("browser", RemoteConfigurations.BROWSER);
						}
					} else {
						Utilities.setConfigurations("mobile", testCaseBrowser.get(testName));
						capabilities.setCapability("device", testCaseBrowser.get(testName));
						if (RemoteConfigurations.PLATFORM != "") {
							capabilities.setCapability(MobileCapabilityType.PLATFORM, RemoteConfigurations.PLATFORM);
						}
						if (RemoteConfigurations.BROWSER != "") {
							capabilities.setCapability("browserName", RemoteConfigurations.BROWSER);
						}
						capabilities.setCapability("realMobile", "true");
						capabilities.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, 100000);
						isMobile = true;
					}
					if (RemoteConfigurations.BROWSER_VERSION != "") {
						capabilities.setCapability("browser_version", RemoteConfigurations.BROWSER_VERSION);
					}
					if (RemoteConfigurations.OS_VERSION != "") {
						capabilities.setCapability("os_version", RemoteConfigurations.OS_VERSION);
					}
					capabilities.setCapability("acceptSslCerts", "true");
	
					driver = new RemoteWebDriver(new URL(URL), capabilities);
	
				} else if (testCaseCategory.get(testName).toLowerCase().equals("mobile")) {
					Utilities.setConfigurations("mobile", testCaseBrowser.get(testName));
					if (RemoteConfigurations.BROWSER != "") {
						capabilities.setCapability(MobileCapabilityType.BROWSER_NAME, RemoteConfigurations.BROWSER);
					}
					if (RemoteConfigurations.PLATFORM != "") {
						capabilities.setCapability(MobileCapabilityType.PLATFORM, RemoteConfigurations.PLATFORM);
						capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, RemoteConfigurations.PLATFORM);
					}
					capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, testCaseBrowser.get(testName));
					if (RemoteConfigurations.OS_VERSION != "") {
						capabilities.setCapability(MobileCapabilityType.VERSION, RemoteConfigurations.OS_VERSION);
					}
					capabilities.setCapability("realMobile", "true");
					capabilities.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, 100000);
					isMobile = true;
					driver = new RemoteWebDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
				}
				if (driver != null) {
					timeOut = Long.parseLong(Utilities.getProperty("TIME_OUT"));
					driverWait = Long.parseLong(Utilities.getProperty("IMPLICIT_WAIT"));
					// driver.manage().timeouts().implicitlyWait(driverWait,
					// TimeUnit.SECONDS);
					// logger.log(LogStatus.INFO, String.valueOf(TimeOut));
					wait = new WebDriverWait(driver,
							timeOut);/*
										 * .withTimeout(Long.parseLong(Utilities.getProperty("TIME_OUT")),
										 * TimeUnit.SECONDS) .pollingEvery(1, TimeUnit.SECONDS)
										 * .ignoring(NoSuchElementException.class);
										 */
					js = ((JavascriptExecutor) driver);
					//Utilities.setProperty("PARENT_HANDLE", driver.getWindowHandle());
					try {
						driver.manage().deleteAllCookies();
					} catch (Exception e) {
						driver.navigate().refresh();
					}
	
				}
	
			} else {
				toBeTested = false;
			}
		}catch(NullPointerException e) {
			toBeTested = false;
			logger.log(LogStatus.SKIP,testName + " not configured. Please check data file and function name for consistency.");
		}
	}

	/**
	 * This function will be executed after each test run. If test is a web test
	 * then this function will close the driver.
	 * 
	 * @throws InterruptedException
	 */
	@AfterMethod
	public void afterMethod(ITestResult result) throws InterruptedException {
		report.endTest(logger);
		report.flush();
		log.endTest(loggerForLogs);
		log.flush();
		String testCaseStatus = null;
	//	ITestResult result = Reporter.getCurrentTestResult();
		int result1 = Reporter.getCurrentTestResult().getStatus();
		switch (result.getStatus()) {
		case ITestResult.SUCCESS:
			testCasePassed++;
			testCaseStatus = "PASS";
			break;
		case ITestResult.FAILURE:
			testCaseFailed++;
			testCaseStatus = "FAIL";
			break;

		case ITestResult.SKIP:
			testCaseSkipped++;
			testCaseStatus = "SKIP";
			break;
		}
		testCaseResult.put(testCaseCount, testCaseStatus);
		testCaseExecuted = testCasePassed + testCaseFailed;
		if (toBeTested && (!testCaseCategory.get(testName).equalsIgnoreCase("api"))){
			driver.quit();
		}
	}

	/**
	 * This function will be executed before each execution run.
	 * 
	 * @throws Throwable
	 * @throws FrameworkException
	 */
	@AfterSuite
	public void afterSuite() throws FrameworkException, Throwable {
		String run = "";
		writeLogs();
		if(runMode.toLowerCase().equals("remote")) {
			if(reportName.toLowerCase().contains("first")) {
				run = "FIRST RUN";
			}else {
				run = "SECOND RUN";
			}
			String subject = Utilities.getProperty("BASE_SUBJECT").replace("<<PROJECT_NAME>>",
					Utilities.getProperty("PROJECT_NAME"));
			subject += Utilities.getTimeStamp("local") + "-";
			subject += System.getProperty("BrowserDevice") + "-";
			subject += System.getProperty("language") + "-" ;
			subject += run;
			String body = Utilities.getProperty("BASE_MESSAGE").replace("<<PROJECT_NAME>>",
					Utilities.getProperty("PROJECT_NAME"));
			
			body += "\n\n Environment: " + Utilities.getProperty("ENVIRONMENT_URL");
			body += "\n\nTest Cases Executed: " + testCaseExecuted;
			body += "\nTest Cases Passed: " + testCasePassed;
			body += "\nTest Cases Failed: " + testCaseFailed;
			body += "\nTest Cases Skipped: " + testCaseSkipped;
			body += "\n\nAttached is the detailed report. Please reach out to Automation Team for more details.";
			body += "\n\nRegards,\nAutomation Team";
			System.out.println("Sending Email for " + reportName);
			Utilities.sendEmail(subject, body, System.getProperty("user.dir") + "/Reports/" + reportName + ".html");
			System.out.println("Email Sent for " + reportName);
		}else {
			System.out.println("Test Cases Executed: " + testCaseExecuted);
			System.out.println("Test Cases Passed: " + testCasePassed);
			System.out.println("Test Cases Failed: " + testCaseFailed);
			System.out.println("Test Cases Skipped: " + testCaseSkipped);
		}
		
		
	}

	private void writeLogs() throws FileNotFoundException, IOException {
		int ctr = 1;
		Utilities.Write_Excel(excelReport, "TestCaseLogs", 0, 0, "Test Case Name");
		Utilities.Write_Excel(excelReport, "TestCaseLogs", 0, 1, "Test Case Result");
		Utilities.Write_Excel(excelReport, "TestCaseLogs", 0, 2, "Comments");
		while(ctr<=testCaseCount) {
			Utilities.Write_Excel(excelReport, "TestCaseLogs", ctr, 0, testCaseName.get(ctr));
			Utilities.Write_Excel(excelReport, "TestCaseLogs", ctr, 1, testCaseResult.get(ctr));
			ctr = ctr+1;
		}
	}
	
	
		
		
		
	
}
