package tests;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import static org.testng.Assert.assertTrue;

import org.testng.Assert;

import base.TestBase;
import pages.HomePage;
import pages.LoginPage;

public class LoginPageTest extends TestBase {

	LoginPage login;
	HomePage home;

	@Test(priority = 1)
	public void testLoadPage() {
		System.out.println("LoginPageTest::testLoadPage() Test start");
		login = new LoginPage(driver);
		String pageTitle = login.getPageTitle();
		String expectedTitle = "Swag Labs";
		Assert.assertEquals(pageTitle, expectedTitle);
		System.out.println("LoginPageTest::testLoadPage() Test finish");
	}

	@Parameters({ "username", "password" })
	@Test(priority = 2)
	public void testEnterCredentials(String user, String pass) throws InterruptedException {
		System.out.println("LoginPageTest::testEnterCredentials() Test start");
		assertTrue(login.enterCredentials(user, pass));
		System.out.println("LoginPageTest::testEnterCredentials() Test finish");
	}

	@Test(priority = 3)
	public void loginSwagLabs() {
		System.out.println("LoginPageTest::loginSwagLabs() Test start");
		home = login.login();
		System.out.println("LoginPageTest::loginSwagLabs() Test finish");
	}
}
