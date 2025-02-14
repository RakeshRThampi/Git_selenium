package pages;

import java.time.Duration;
import java.util.Iterator;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import base.TestBase;

public class HomePage extends TestBase {
	private static WebDriver driver;
	private List<WebElement> dropElems;
	private Select objSelect;
	private final By sortDrop = By.xpath("//select[@class='product_sort_container']");

	public HomePage(WebDriver driver) {
		this.driver = driver;
	}

	public int checkDropdownCount() {
		System.out.println("HomePage::checkDropdown()");
		waitForElementBy(sortDrop);
		objSelect = new Select(driver.findElement(sortDrop));
		dropElems = objSelect.getOptions();
		System.out.println("HomePage::checkDropdown() - Dropdown item count:" + dropElems.size());
		return dropElems.size();
	}

	public boolean sortDropDownByOption(Integer sortOption) {
		System.out.println("HomePage::sortDropDownByOption() - Sort option = " + sortOption);
		waitForElementBy(sortDrop);
		try {
			// RRT:: Check 'SelectByValue' and update data provider accordingly. Inspect why it fails
			objSelect.selectByIndex(sortOption);
		} catch (Exception e) {
			System.out.println("HomePage::sortDropDownByOption() - EXCEPTION!");
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		return true;
	}
}
