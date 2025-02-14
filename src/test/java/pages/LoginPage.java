package pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import base.TestBase;

public class LoginPage extends TestBase {
	private WebDriver driver;
	private By unBy = By.id("user-name");
	private By pwBy = By.id("password");
	private By loginBy = By.id("login-button");

	public LoginPage(WebDriver driver) {
		System.out.println("LoginPage::C'tor - driver = " + driver.getCurrentUrl());
		this.driver = driver;
	}

	public String getPageTitle() {
		String title = driver.getTitle();
		System.out.println("LoginPage::getPageTitle() - Title = " + title);
		return title;
	}

	public boolean enterCredentials(String user, String pass) {
		System.out.println("LoginPage::getPageTitle() - User name = " + user + ", Password = " + pass);
		waitForElementBy(unBy);
		waitForElementBy(pwBy);

		driver.findElement(unBy).sendKeys(user);
		driver.findElement(pwBy).sendKeys(pass);
		
		if (driver.findElement(unBy).getAttribute("value").equals(user) && driver.findElement(pwBy).getAttribute("value").equals(pass)) {
			System.out.println("LoginPage::getPageTitle() - Credentials input successfully");
			return true;
		}
		System.out.println("LoginPage::getPageTitle() - Credentials not input");
		return false;
	}

	public HomePage login() {
		waitForElementBy(loginBy);
		driver.findElement(loginBy).click();
		System.out.println("LoginPage::login() - Loading home page...");
		return new HomePage(driver);
	}
}
