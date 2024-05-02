package com.Jotform.JotForm;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import io.github.bonigarcia.wdm.WebDriverManager;

public class FormTests 
{
	private ChromeDriver driver;
	private FormPage formPage;
	
	@BeforeClass
	@Parameters("url")
	public void setup(String url)
	{
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
        driver.get(url);
	}
	
	@AfterClass
	public void teardown()
	{
		driver.close();
	}
	
	@BeforeMethod
	public void pageReload()
	{
		driver.navigate().refresh();
	}
	
	@Test(priority = 1)
	public void verifyNullFieldValidation() 
	{
		formPage = new FormPage(driver);

		//click on submit button without entering any data
		formPage.clickSubmitBtn();

	    //validate error messages for null fields
	    Assert.assertTrue(formPage.getNameError().isDisplayed(), "Name field validation message not displayed");
	    Assert.assertTrue(formPage.getPhoneNumberError().isDisplayed(), "Phone Number field validation message not displayed");
	    Assert.assertTrue(formPage.getEmailError().isDisplayed(), "Email field validation message not displayed");
	    Assert.assertTrue(formPage.getPurposeError().isDisplayed(), "Demo Purpose field validation message not displayed");

	    //validate error navigation container
	    Assert.assertTrue(formPage.getErrorContainer().isDisplayed(), "Error navigation container not displayed");

	    //validate count of errors
	    int actualCount = Integer.parseInt(formPage.getErrorCount().getText());
	    int expectedCount = 4;
	    Assert.assertEquals(actualCount, expectedCount, "Count value mismatch");
	 }
	
	@Test(priority = 2)
	public void verifyInvalidData() throws InterruptedException 
	{
		formPage = new FormPage(driver);
        
		//validate ph.no. input as alphabets & special characters
		String[] phoneNumberValues = {"testNumber", "$%^$%"};
	    for (String value : phoneNumberValues) 
	    {
	    	formPage.setPhoneNumber(value);
	        String fieldValue = formPage.getPhoneFieldValue();
	        Assert.assertTrue(fieldValue.isEmpty(), "Field is not empty");
	    }
		
        //validate email input with invalid format
        String[] invalidValues = {"testgmail.com", "Test$%5@@gmail.com", "example@", "test @ gmail.com"};
        for (String value : invalidValues) 
        {
            formPage.setEmail(value);
        }
        Assert.assertTrue(formPage.isEmailErrorDisplayed(), "Email error message not displayed");
        
        //validate date input as alphabets & special characters
        String[] dateValues = {"datetest", ")(*&"};
        for (String value : dateValues) 
        {
            formPage.setDate(value);
            String fieldValue = formPage.getDateFieldValue();
            Assert.assertTrue(fieldValue.isEmpty(), "Field is not empty");
        }
        
        //validate date input as 29th feb with non-leap years
        String[] dateValues2 = {"02-29-2025", "02-29-2027"};
        for (String value : dateValues2) 
        {
        	WebElement dateInput = driver.findElement(By.xpath("//input[@id='lite_mode_17']"));
        	dateInput.clear();
        	dateInput.sendKeys(value);
        	formPage.clickSubmitBtn();
        	Thread.sleep(3000);
        	WebElement dateError = driver.findElement(By.xpath("//li[@id='id_17']//span[@class='error-navigation-message']"));
        	String actualErrorMessage = dateError.getText();
        	String expectedErrorMessage = "This date is not valid. The date format is MM-DD-YYYY";
        	Assert.assertEquals(actualErrorMessage, expectedErrorMessage, "Error message text mismatch");
        }
        
        //validate time input as alphabets & special characters
        String[] timeValues1 = {"testtime", ")(*&"};
        for (String value : timeValues1) 
        {
            formPage.setTime(value);
            String fieldValue = formPage.getTimeFieldValue();
            Assert.assertTrue(fieldValue.isEmpty(), "Field is not empty");
        }
        
        
      //validate time input with invalid time
        String[] timeValues2 = {"00:00"};
        for (String value : timeValues2) 
        {
            formPage.setTime(value);
            formPage.clickSubmitBtn();
            Thread.sleep(3000);
            String actualErrorMessage = formPage.getTimeErrorMessage();
            String expectedErrorMessage = "Enter a valid time";
            Assert.assertEquals(actualErrorMessage, expectedErrorMessage, "Error message text mismatch");
        }
    }
	
	@Test(priority = 3)
	@Parameters({"firstname", "lastname", "phonenumber", "email", "purpose", "date", "time","features", "questions"})
	public void verifyValidData(String firstname, String lastname, String phonenumber, String email, String purpose, 
								String date, String time, String features, String questions)
	{
		formPage = new FormPage(driver);
		
		formPage.setFirstName(firstname);
		formPage.validateFirstNameValue();
		
		formPage.setLastName(lastname);
		formPage.validateLastNameValue();
		
		formPage.setPhoneNumber(phonenumber);
		formPage.validatePhoneNumberValue();
		
		formPage.setEmail(email);
		formPage.validateEmailValue();
		
		formPage.setPurpose(purpose);
		formPage.validatePurposeValue();
		
		formPage.setDate(date);
		formPage.validateDateValue();
		
		formPage.selectDateFromCalender();
		formPage.validateCalenderDateValue();
		
		formPage.setTimeframe();
		formPage.validateTimeframe();
		
		formPage.setTime(time); 
		formPage.validateTimeValue(); 
		
		formPage.setSpecificFeature(features);
		formPage.validateFeaturesValue(); 
		
		formPage.setQuestionToBeAsked(questions);
		formPage.validateQuestionsValue(); 
		
		formPage.clickSubmitBtn();
	}	
}
