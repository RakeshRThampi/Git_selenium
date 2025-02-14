package base;

import utilities.SeleniumUtils;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import io.github.bonigarcia.wdm.WebDriverManager;

public class TestBase {

	protected static WebDriver driver;
	protected static WebDriverWait wait;

	@Parameters({ "browser", "url" })
	@BeforeTest
	public void beforeTest(@Optional("Chrome") String browser, String url) {
		System.out.println("TestBase::beforeTest() - Browser = " + browser + ", URL=" + url);
		if (browser.equalsIgnoreCase("Chrome")) {
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
			wait = new WebDriverWait(driver, Duration.ofSeconds(SeleniumUtils.WEBDRIVER_WAIT));
		} else {
			System.out.println("TestBase::beforeTest() - Browser not setup");
			return;
		}
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(SeleniumUtils.IMPLICIT_WAIT));
		driver.get(url);
	}

	public boolean waitForElementBy( /* WebDriver driver, */ By by) {
		System.out.println("TestBase::waitForElementBy -> " + by.toString());
		wait.until(ExpectedConditions.visibilityOfElementLocated(by));
		return true;
	}

	@AfterTest
	public void afterTest() {
		if (driver.equals(null)) {
			System.out.println("TestBase::afterTest() - Unexpected! Driver is already closed or null!");
		} else {
			System.out.println("TestBase::afterTest() - Quiting driver...");
			driver.quit();
		}
	}
}
