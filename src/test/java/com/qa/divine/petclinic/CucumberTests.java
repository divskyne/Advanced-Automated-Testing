package com.qa.divine.petclinic;

import org.junit.runner.RunWith;
import com.relevantcodes.extentreports.ExtentReports;
import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(features = "src/main/resources/")
public class CucumberTests
{	
	static ExtentReports report = new ExtentReports("src/test/resources/report.html",true);	
}