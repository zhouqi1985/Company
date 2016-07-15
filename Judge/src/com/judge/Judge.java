package com.judge;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;

public class Judge {
	public  boolean isElementExsit(WebDriver driver,By locator){
		try {
			driver.findElement(locator);
			return true;
		} catch (NoSuchElementException e) {
			return false;
		}
	}
	public boolean isByElementDisplayed(WebDriver driver,By by, int time) {
		boolean status = false;
	    if (driver.findElement(by).isDisplayed() == false) {
	        driver.manage().timeouts().implicitlyWait(time, TimeUnit.SECONDS);
	    } else {
	      status = true;
	   }
	    return status;
	}
	public boolean dealPotentialAlert(WebDriver driver,boolean option) {  
	    boolean flag = false;  
	    try {  
	        Alert alert = driver.switchTo().alert();  
	        if (null == alert)  
	            throw new NoAlertPresentException();  
	        try {  
	            if (option) {  
	                alert.accept();  
//	                System.out.println("Accept the alert: " + alert.getText());  
	            } else {  
	                alert.dismiss();  
//	                System.out.println("Dismiss the alert: " + alert.getText());  
	            }  
	            flag = true;  
	        } catch (WebDriverException ex) {  
	            if (ex.getMessage().startsWith("Could not find"))  {
//	                System.out.println("There is no alert appear!");              	
	            }
	            else  
	                throw ex;  
	        }  
	    } catch (NoAlertPresentException e) {  
//	        System.out.println("There is no alert appear!");  
	    }  
	    return flag;  
	}  
}
