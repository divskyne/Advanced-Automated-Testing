package com.qa.petclinic;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WebPage {
	
	@FindBy(xpath="/html/body/app-root/div[1]/nav/div/ul/li[2]")
	WebElement ownersButton;
	
	@FindBy(xpath="/html/body/app-root/div[1]/nav/div/ul/li[2]/ul/li[1]")
	WebElement allOwners;
	
	public void navigate(WebDriver driver) {
		getOwnersButton(driver);
		getAllOwners(driver);
	}
	
	public int countAllOwners(WebDriver driver) {
		return countOwners(driver);
	}
	
	private void getOwnersButton(WebDriver driver) {
		ownersButton = (new WebDriverWait(driver, 10)).until(ExpectedConditions.presenceOfElementLocated(By.xpath("/html/body/app-root/div[1]/nav/div/ul/li[2]")));

		ownersButton.click();
	}

	private void getAllOwners(WebDriver driver) {
		allOwners = (new WebDriverWait(driver, 10)).until(ExpectedConditions.presenceOfElementLocated(By.xpath("/html/body/app-root/div[1]/nav/div/ul/li[2]/ul/li[1]")));
		allOwners.click();
	}
	
	private int countOwners(WebDriver driver)
	{
		List <WebElement> av = (new WebDriverWait(driver, 10)).until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("/html/body/app-root/app-owner-list/div/div/div/table/tbody/tr")));
		int count = 0;
		for (WebElement webElement : av) {
			count++;
		}
		return count;
	}
}
