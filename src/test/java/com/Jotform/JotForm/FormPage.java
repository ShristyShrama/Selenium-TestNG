package com.Jotform.JotForm;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

public class FormPage 
{
    private WebDriver driver;

    public FormPage(WebDriver driver) 
    {
        this.driver = driver;
    }
    
    public void clickSubmitBtn()
    {
    	WebElement submitBtn = driver.findElement(By.xpath("//button[@id='input_2']"));
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		executor.executeScript("arguments[0].click();", submitBtn);
    }

    public WebElement getNameError() 
    {
        return driver.findElement(By.xpath("//li[@id='id_3']//span[@class='error-navigation-message']"));
    }

    public WebElement getPhoneNumberError() 
    {
        return driver.findElement(By.xpath("//div[@id='cid_5']//span[@class='error-navigation-message']"));
    }

    public WebElement getEmailError() 
    {
        return driver.findElement(By.xpath("//div[@id='cid_6']//span[@class='error-navigation-message']"));
    }

    public WebElement getPurposeError() 
    {
        return driver.findElement(By.xpath("//div[@id='cid_23']//span[@class='error-navigation-message']"));
    }

    public WebElement getErrorContainer() 
    {
        return driver.findElement(By.xpath("//div[@class='error-navigation-container']"));
    }

    public WebElement getErrorCount() 
    {
        return driver.findElement(By.xpath("//div[@class='error-navigation-container']//strong"));
    }
    
    //methods for invalid tests
    public void setEmail(String email) 
    {
        WebElement emailInput = driver.findElement(By.id("input_6"));
        emailInput.clear();
        emailInput.sendKeys(email);
    }

    public boolean isEmailErrorDisplayed() 
    {
        WebElement emailError = driver.findElement(By.xpath("//div[@id='cid_6']//span[@class='error-navigation-message']"));
        return emailError.isDisplayed();
    }
    
    public void validateEmailValue()
    {
    	WebElement emailValue = driver.findElement(By.id("input_6"));
        String expectedValue = "example@gmail.com";
        String actualValue = emailValue.getAttribute("value");
        Assert.assertEquals(actualValue, expectedValue, "E-mail value mismatch");
    }
    
    //methods for valid tests
    public void setFirstName(String firstname) 
    {
        WebElement firstNameInput = driver.findElement(By.id("first_3"));
        firstNameInput.sendKeys(firstname);
    }
    
    public String getFirstNameValue() 
    {
        WebElement firstNameValue = driver.findElement(By.id("first_3"));
        return firstNameValue.getAttribute("value");
    }
    
    public void validateFirstNameValue()
    {
    	WebElement firstNameValue = driver.findElement(By.id("first_3"));
        String expectedValue = "Test";
        String actualValue = firstNameValue.getAttribute("value");
       Assert.assertEquals(actualValue, expectedValue, "Text field value mismatch");
    }
    
    public void setLastName(String lastName) 
    {
    	WebElement lastNameInput = driver.findElement(By.xpath("//input[@id='last_3']"));
		lastNameInput.sendKeys(lastName);
    }
    
    public void validateLastNameValue()
    {
    	WebElement lastNameValue = driver.findElement(By.xpath("//input[@id='last_3']"));
        String expectedValue = "User";
        String actualValue = lastNameValue.getAttribute("value");
        Assert.assertEquals(actualValue, expectedValue, "Text field value mismatch");
    }
    
    public void setPhoneNumber(String phonenumber) 
    {
		WebElement phoneNumberInput = driver.findElement(By.xpath("//input[@name='q5_phoneNumber5[full]']"));
		phoneNumberInput.clear();
		phoneNumberInput.sendKeys(phonenumber);
    }
    
    public String getPhoneFieldValue() 
    {
        WebElement phoneNoInput = driver.findElement(By.xpath("//input[@name='q5_phoneNumber5[full]']"));
        return phoneNoInput.getAttribute("value");
    }
    
    public void validatePhoneNumberValue()
    {
    	WebElement phoneNumberValue = driver.findElement(By.xpath("//input[@name='q5_phoneNumber5[full]']"));
        String expectedValue = "(798) 361-0000";
        String actualValue = phoneNumberValue.getAttribute("value");
        Assert.assertEquals(actualValue, expectedValue, "Phone Number value mismatch");
    }
    
    public void setPurpose(String purpose) 
    {
    	WebElement purposeInput = driver.findElement(By.xpath("//input[@name='q23_purposeOf']"));
		purposeInput.sendKeys(purpose);
    }
    
    public void validatePurposeValue()
    {
    	WebElement purposeValue = driver.findElement(By.xpath("//input[@name='q23_purposeOf']"));
        String expectedValue = "Automation testing purpose";
        String actualValue = purposeValue.getAttribute("value");
        Assert.assertEquals(actualValue, expectedValue, "Purpose value mismatch");
    }
    
    public void setDate(String date) 
    {
    	WebElement dateInput = driver.findElement(By.xpath("//input[@id='lite_mode_17']"));
    	dateInput.clear();
		dateInput.sendKeys(date);
    }
    
    public String getDateFieldValue() 
    {
        WebElement dateInput = driver.findElement(By.xpath("//input[@id='lite_mode_17']"));
        return dateInput.getAttribute("value");
    }
    
    public String getDateErrorMessage() 
    {
        WebElement dateError = driver.findElement(By.xpath("//li[@id='id_17']//span[@class='error-navigation-message']"));
        return dateError.getText();
    }
    
    public void validateDateValue()
    {
    	WebElement dateValue = driver.findElement(By.xpath("//input[@id='lite_mode_17']"));
        String expectedValue = "01-01-2024";
        String actualValue = dateValue.getAttribute("value");
        Assert.assertEquals(actualValue, expectedValue, "Date value mismatch");
    }
    
    public void selectDateFromCalender() 
    {
    	//select date form calendar
		WebElement calenderIcon = driver.findElement(By.xpath("//img[@id='input_17_pick']"));
		calenderIcon.click();
		
		//set month
		WebElement monthElement = driver.findElement(By.xpath("//thead//td[@class='button nextMonth']"));
		monthElement.click();
		
		//set year
		WebElement yearElement = driver.findElement(By.xpath("//thead//td[@class='button nextYear']"));
		yearElement.click();
		
		//validate that the selected month is set in the calendar
		WebElement selectedMonth = driver.findElement(By.xpath("//div[@class='calendar-new-month']/span"));
        String expMonth = selectedMonth.getText();
        Assert.assertEquals(expMonth, "February");
        
        //validate that the selected year is set in the calendar
     	WebElement selectedYear = driver.findElement(By.xpath("//div[@class='calendar-new-year']"));
        String expYear = selectedYear.getText();
        Assert.assertEquals(expYear, "2025« »");
		
		//set date
		WebElement dateElement = driver.findElement(By.xpath("(//td[contains(text(),'15')])[1]"));
		dateElement.click();
		
		calenderIcon.click();
		
		//validate that the selected date is set in the calendar
		WebElement selecteddate = driver.findElement(By.xpath("//td[@class='weekend selected']"));
        String expDate = selecteddate.getText();
        Assert.assertEquals(expDate, "15");
        
        calenderIcon.click();
    }
    
    public void validateCalenderDateValue()
    {
    	WebElement dateValue = driver.findElement(By.xpath("//input[@id='lite_mode_17']"));
        String expectedValue = "02-15-2025";
        String actualValue = dateValue.getAttribute("value");
        Assert.assertEquals(actualValue, expectedValue, "Calender Date value mismatch");
    }
    
    public void setTimeframe() 
    {
    	WebElement checkbox1 = driver.findElement(By.id("label_input_21_0"));
		checkbox1.click();
		
		WebElement checkbox2 = driver.findElement(By.id("label_input_21_1"));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", checkbox2);
		checkbox2.click();
		
		WebElement checkbox3 = driver.findElement(By.id("label_input_21_2"));
		checkbox3.click();
    }
    
    public void validateTimeframe() 
    {
    	WebElement checkboxes = driver.findElement(By.xpath("//li[@id='id_21']"));
    	Assert.assertTrue(checkboxes.isDisplayed(), "Checkboxes not checked");
    }
 
    public void setTime(String time)
    {
    	//set time
		WebElement timeInput = driver.findElement(By.xpath("//input[@id='input_24_timeInput']"));
		timeInput.clear();
        timeInput.sendKeys(time);
		
		//set drop-down option
		WebElement dropdownElement = driver.findElement(By.xpath("//select[@id='input_24_ampm']"));
		Select dropdown = new Select(dropdownElement);
		dropdown.selectByValue("PM");
    }
    
    public String getTimeFieldValue() 
    {
        WebElement timeInput = driver.findElement(By.xpath("//input[@id='input_24_timeInput']"));
        return timeInput.getAttribute("value");
    }
    
    public void validateTimeValue()
    {
    	WebElement timeValue = driver.findElement(By.xpath("//input[@id='input_24_timeInput']"));
        String expectedValue = "02:30";
        String actualValue = timeValue.getAttribute("value");
        Assert.assertEquals(actualValue, expectedValue, "Time value mismatch");
    }
    
    public String getTimeErrorMessage() 
    {
        WebElement timeError = driver.findElement(By.xpath("//div[@id='cid_24']//span[@class='error-navigation-message']"));
        return timeError.getText();
    }
    
    public void setSpecificFeature(String features)
    {
    	WebElement specificInput = driver.findElement(By.xpath("//textarea[@name='q26_anySpecific']"));
		specificInput.sendKeys(features);
    }
    
    public void validateFeaturesValue()
    {
    	WebElement featuresValue = driver.findElement(By.xpath("//textarea[@name='q26_anySpecific']"));
        String expectedValue = "Any required feature needs to be specify here.";
        String actualValue = featuresValue.getAttribute("value");
        Assert.assertEquals(actualValue, expectedValue, "Features value mismatch");
    }
    
    public void setQuestionToBeAsked(String questions)
    {
    	WebElement questionInput = driver.findElement(By.xpath("//textarea[@name='q27_anyQuestions']"));
		questionInput.sendKeys(questions);
    }
    
    public void validateQuestionsValue()
    {
    	WebElement questionsValue = driver.findElement(By.xpath("//textarea[@name='q27_anyQuestions']"));
        String expectedValue = "Any query to be asked needs to jot down here.";
        String actualValue = questionsValue.getAttribute("value");
        Assert.assertEquals(actualValue, expectedValue, "Questions value mismatch");
    }
}

