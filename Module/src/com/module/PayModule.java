package com.module;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;


public class PayModule {

	public void getModule(WebDriver driver,String title) throws Exception{
		List<WebElement> Rows =driver.findElements(By.xpath("//*[@id='con']/div[1]/div/div[1]/div[2]/div"));
		for(int i=1;i<=Rows.size();i++){
			if(driver.findElement(By.xpath("//*[@id='con']/div[1]/div/div[1]/div[2]/div["+i+"]/a")).getText().trim().equals(title)){
				driver.findElement(By.xpath("//*[@id='con']/div[1]/div/div[1]/div[2]/div["+i+"]/a")).click();
				Thread.sleep(500);
			}
		}
	}
}
