package tests;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import base.TestBase;
import pages.HomePage;
@Listeners(listener.TestListener.class)
public class HomePageTest extends TestBase {
	HomePage home;

	@Test(priority = 1)
	@Parameters({ "dropDownCount" })
	public void checkDropDownItems(String optionCount) {
		System.out.println("HomePageTest::checkDropDownItems() Test start");
		home = new HomePage(driver);
		int expCount = Integer.parseInt(optionCount);
		Assert.assertEquals(home.checkDropdownCount(), expCount);
		System.out.println("HomePageTest::checkDropDownItems() Test end");
	}

	@Test(priority = 2, dataProvider = "sortOptions")
	public void sortDropDownFromDataProviderOptions(Integer option) {
		System.out.println("HomePageTest::checkDropDownItems() Test start - Which option to sort = " + option);
		home.sortDropDownByOption(option);
	}

	@DataProvider(name = "sortOptions")
	public Integer[] getDropDownOptions() {
		return new Integer[] { 0, 1, 2, 3 };
	}
}
