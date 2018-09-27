package com.qa.divine.petclinic;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;

import com.qa.petclinic.Constants;
import com.qa.petclinic.ScreenshotHelper;
import com.qa.petclinic.WebPage;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import com.qa.petclinic.ExcelUtils;

public class GUITest {
	
	
	WebDriver driver = null;
	static int counter = 0;
	static ExtentReports report;
	ExtentTest test;
	private WebPage wp;
	
	private void init() {
		System.setProperty(Constants.driverName, Constants.chromeDriverLocation);
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		wp = PageFactory.initElements(driver, WebPage.class);
		report = CucumberTests.report;
		ExcelUtils.setExcelFile(Constants.pathTestData + Constants.fileTestData, 0);
	}
	
	private void close() {
		driver.close();
		report.endTest(test);
		report.flush();
	}
	
	@Given("^I'm on the homepage$")
	public void i_m_on_the_homepage() {
	    init();
	    driver.get(Constants.GUIHome);
	    counter++;
		test = report.startTest("PetClinic GUI Test "+counter);
		test.log(LogStatus.INFO, "navigated to home page");
		ExcelUtils.setCellData("home page works fine", 1, 1); 
	}

	@When("^I click owners and then all$")
	public void i_click_owners_and_then_all() {
		wp.navigate(driver);
		test.log(LogStatus.INFO, "navigated to owners page");
		ExcelUtils.setCellData("owners page works fine", 2, 1); 
	}

	@Then("^I can see all records$")
	public void i_can_see_all_records() {
		int totalOwners = wp.countAllOwners(driver);
		if (totalOwners>0)
		{
			test.log(LogStatus.PASS, totalOwners+" Owners Found");
			test.log(LogStatus.INFO, "Screenshot that shows the users: \n"+test.addScreenCapture(ScreenshotHelper.takeScreenShot(driver)));
		}
		else
		{
			test.log(LogStatus.FAIL, "0 Owners Found");
		}
		;
		close();
	}
}