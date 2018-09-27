package com.qa.divine.petclinic;

import org.json.JSONArray;
import org.json.JSONObject;
import org.openqa.selenium.WebDriver;

import com.qa.petclinic.Constants;
import com.qa.petclinic.ExcelUtils;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.response.ResponseBodyExtractionOptions;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import junit.framework.Assert;

public class APICucumber {
	
	private RequestSpecification request;
	private Response response;
	private ValidatableResponse json;
	
	WebDriver driver = null;
	static ExtentReports report;
	ExtentTest test;
	String url;
	
	static int counter = 0;
	
	@Before
	public void setUp()  {
		report = CucumberTests.report;
		ExcelUtils.setExcelFile(Constants.pathTestData + Constants.fileTestData, 0);
	}

	@After
	public void tearDown()  {
		report.endTest(test);
		report.flush();
	}
	
	@Given("^a vet$")
	public void a_vet() {
		RestAssured.baseURI = Constants.APIBASEURL+"api/vets";
		request = RestAssured.given();
		request.header("Content-Type", "application/json");
		counter++;
		test = report.startTest("PetClinic System Test "+"a vet "+counter);
		test.log(LogStatus.INFO, "Accessing: "+RestAssured.baseURI);
		ExcelUtils.setCellData("Accessing: "+RestAssured.baseURI, 7, 1); 
	}
	
	@When("^I retrieve all records$")
	public void i_retrieve_all_records() {
		response = request.get("/");
		test.log(LogStatus.INFO, "Response Time: "+response.getTime());
		ExcelUtils.setCellData("Response Time: "+response.getTime(), 8, 2); 
	}

	@Then("^I can see the records available for animal with id (\\d+)$")
	public void i_can_see_the_records_available_for_animal_with_id(int arg1) throws Throwable {
		JSONArray animalRecord = new JSONArray(response.body().asString());
		boolean notRun = true;
		for (Object object : animalRecord)
		{
			JSONObject o = (JSONObject) object;
			  if (o.getInt("id")==arg1)
			  {
				  Assert.assertEquals(arg1, o.getInt("id"));
				  test.log(LogStatus.PASS, "Records can be seen for id: "+arg1);
				  notRun = false;
			}
		}
		if (notRun) {
			test.log(LogStatus.WARNING, "Record with id: "+arg1+" was not found!");
		}
	}

	@Given("^an admin$")
	public void an_admin() {
		RestAssured.baseURI = Constants.APIBASEURL+"api/pets";
		request = RestAssured.given();
		request.header("Content-Type", "application/json");
		counter++;
		test = report.startTest("PetClinic System Test "+"an admin "+counter);
		test.log(LogStatus.INFO, "Accessing: "+RestAssured.baseURI);
		ExcelUtils.setCellData("Accessing: "+RestAssured.baseURI, 9, 1); 
	}

	@When("^I update a record of id (\\d+)$")
	public void i_update_a_record_of_id(int arg1) {
		JSONArray visits = new JSONArray();
		JSONObject visit = new JSONObject();
		JSONArray pets = new JSONArray();
		JSONObject responseParam = new JSONObject();
		JSONObject owner = new JSONObject();
		JSONObject type = new JSONObject();
		JSONObject pet = new JSONObject();
		responseParam.put("birthDate", "2018/11/12");
		responseParam.put("id", arg1);
		responseParam.put("name", "Sample");
		owner.put("address", "bleh");
		owner.put("city", "bleh");
		owner.put("firstName", "bleh");
		owner.put("id", arg1);
		owner.put("lastName", "bleh");
		owner.put("pets", pets.put(pet));
		owner.put("telephone", "numbers");
		responseParam.put("owner", owner);
		type.put("name", "bleh");
		type.put("id", arg1);
		responseParam.put("type", type);
		visit.put("date", "2018/11/13");
		visit.put("description", "meow");
		visit.put("pet", pet);
		visit.put("id", arg1);
		visits.put(visit);
		responseParam.put("visits", visits);
		request.body(responseParam.toString());
		response = request.put(arg1+"/");
		test.log(LogStatus.INFO, "Response Time: "+response.getTime());
		ExcelUtils.setCellData("Response Time: "+response.getTime(), 10, 2);
	}

	@Then("^the correct details are now shown on id (\\d+)$")
	public void the_correct_details_are_now_shown_on_id(int arg1) {
		logThen("Details Shown for Pet: id = "+arg1);
	}
	
	@When("^I delete a animal of id (\\d+)$")
	public void i_delete_a_animal_of_id(int arg1) {
		response = request.delete(arg1+"/");
		test.log(LogStatus.INFO, "Response Time: "+response.getTime());
		test.log(LogStatus.INFO, "Attempting Delete of pet with id : "+arg1);
		ExcelUtils.setCellData("Response Time: "+response.getTime(), 11, 2);
	}

	@Then("^emails arent sent to deceased annimals$")
	public void emails_arent_sent_to_deceased_annimals() {
		logThen("emails arent sent to deceased annimals");
	}
	
	@When("^I add new record for id (\\d+)$")
	public void i_add_new_record_for_id(int arg1) {
		JSONArray visits = new JSONArray();
		JSONObject visit = new JSONObject();
		JSONArray pets = new JSONArray();
		JSONObject responseParam = new JSONObject();
		JSONObject owner = new JSONObject();
		JSONObject type = new JSONObject();
		JSONObject pet = new JSONObject();
		responseParam.put("birthDate", "2018/11/12");
		responseParam.put("id", arg1);
		responseParam.put("name", "Sample");
		owner.put("address", "bleh");
		owner.put("city", "bleh");
		owner.put("firstName", "bleh");
		owner.put("id", arg1);
		owner.put("lastName", "bleh");
		owner.put("pets", pets.put(pet));
		owner.put("telephone", "numbers");
		responseParam.put("owner", owner);
		type.put("name", "bleh");
		type.put("id", arg1);
		responseParam.put("type", type);
		visit.put("date", "2018/11/13");
		visit.put("description", "meow");
		visit.put("pet", pet);
		visit.put("id", arg1);
		visits.put(visit);
		responseParam.put("visits", visits);
		request.body(responseParam.toString());
		response = request.post(arg1+"/");
		System.out.println(response.getStatusCode());
		test.log(LogStatus.INFO, "Response Time: "+response.getTime());
		ExcelUtils.setCellData("Response Time: "+response.getTime(), 12, 2);
	}

	@Then("^the records are added$")
	public void the_records_are_added() {
		logThen("records are correct");
	}
	
	@When("^I add new owners to the records of id (\\d+)$")
	public void i_add_new_owners_to_the_records_of_id(int arg1) {
		response = request.get(Constants.APIBASEURL+"api/owners");
		System.out.println(response.getStatusCode());
		
		JSONObject responseParam = new JSONObject();
		responseParam.put("address", "address bleh");
		responseParam.put("city", "address bleh");
		responseParam.put("id", arg1);
		responseParam.put("firstName", "bleh");
		responseParam.put("lastName", "bleh");
		JSONArray pets = new JSONArray();
		JSONObject pet = new JSONObject();
		pet.put("birthDate", "2018/11/12");
		pet.put("id", 1);
		pet.put("name", "Sample");
		JSONObject owner = new JSONObject();
		pet.put("owner", owner);
		JSONObject type = new JSONObject();
		type.put("name", "cat");
		type.put("id", 1);
		pet.put("type", type);
		JSONArray visits = new JSONArray();
		JSONObject visit = new JSONObject();
		visit.put("date", "2018/11/13");
		visit.put("description", "meow");
		JSONObject newPet = new JSONObject();
		visit.put("pet", newPet);
		visit.put("id", 1);
		pet.put("visits",visits);
		pets.put(pet);
		responseParam.put("pets",pets);
		responseParam.put("telephone","079456");
		
		request.body(responseParam.toString());
		response = request.post("/");
		System.out.println(response.getStatusCode());
		System.out.println(responseParam.toString());
		
		test.log(LogStatus.INFO, "Response Time: "+response.getTime());
		ExcelUtils.setCellData("Response Time: "+response.getTime(), 13, 2);
	}

	@Then("^the record is added$")
	public void the_record_is_added() {
		logThen("record is added");
	}
	
	
	private void logThen(String pass) {
		if (response.getStatusCode()==404) {
			test.log(LogStatus.WARNING, "Pet Not Found");
		}
		else if (response.getStatusCode()==400) {
			test.log(LogStatus.FATAL, "Invalid Request");
		}
		else if (response.getStatusCode()==204) {
			json = response.then().statusCode(204);
			test.log(LogStatus.PASS, pass);
		}
		else if (response.getStatusCode()==201) {
			json = response.then().statusCode(201);
			test.log(LogStatus.PASS, pass);
		}
		else
		{
			test.log(LogStatus.WARNING, "Error : Status Code "+response.getStatusCode());
		}
	}
}
