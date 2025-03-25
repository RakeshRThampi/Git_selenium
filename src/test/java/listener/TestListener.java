package listener;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class TestListener implements ITestListener {
	ExtentReports reports; // Common report info such as system, user info
	ExtentSparkReporter spark; // The Report UI configuration incl. Doc title, name
	ExtentTest test; // Test case entries with data attached incl. screenshot

	@Override
	public void onStart(ITestContext context) { // Called when the first @test start
		System.out.println("----------------TestListener::onStart()");
		reports = new ExtentReports();
		reports.setSystemInfo("User Name:", System.getProperty("user.name"));
		reports.setSystemInfo("OS:", System.getProperty("os.name"));
		reports.setSystemInfo("OS Version:", System.getProperty("os.version"));
		reports.setSystemInfo("Architecture:", System.getProperty("os.arch"));
		reports.setSystemInfo("Java Version:", System.getProperty("java.version"));
		LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
        String formattedDateTime = now.format(formatter);
		spark = new ExtentSparkReporter(
				System.getProperty("user.dir") + "//reports//RRT_report_" + formattedDateTime + ".html");
		spark.config().setDocumentTitle("Git Selenium Tests with TestNG, Maven, Page Object Model, Listeners");
		spark.config().setReportName("Git Selenium Report");
		spark.config().setTheme(Theme.STANDARD);
		reports.attachReporter(spark);
	}

	@Override
	public void onTestStart(ITestResult result) { // Called for each @Test
		System.out.println("----------------TestListener::onTestStart()");
		test = reports.createTest(result.getName()); // Test name
		test.log(Status.PASS, "Test Start: " + result.getName());
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		System.out.println("----------------TestListener::onTestSuccess()");
		test.log(Status.PASS, "Test passed: " + result.getName());
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		System.out.println("----------------TestListener::onTestSkipped()");
		test.log(Status.SKIP, "Test skipped: " + result.getName());
	}

	@Override
	public void onTestFailure(ITestResult result) {
		System.out.println("----------------TestListener::onTestFailure()");
		test.log(Status.FAIL, "Test failed: " + result.getName() + ", Reason: " + result.getThrowable());
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		System.out.println("----------------TestListener::onTestFailedButWithinSuccessPercentage()");
	}

	@Override
	public void onFinish(ITestContext context) {
		System.out.println("----------------TestListener::onFinish()");
		reports.flush(); // Write all data to HTML report
	}

}
