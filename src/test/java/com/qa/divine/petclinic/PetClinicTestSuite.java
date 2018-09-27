package com.qa.divine.petclinic;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import cucumber.runtime.java.StepDefAnnotation;

@RunWith(Suite.class)
@SuiteClasses({CucumberTests.class})
public class PetClinicTestSuite {
	
}
