package com.module;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class EsalesModule {
	
	public void getModule(WebDriver driver,String type,String title) throws Exception{
		List<WebElement> Row1 =driver.findElements(By.xpath("//*[@id='container']/div/div/div/div[1]/div"));
		for(int i=1;i<=Row1.size();i++){
			if(driver.findElement(By.xpath("//*[@id='container']/div/div/div/div[1]/div["+i+"]")).getText().trim().equals(type)){
				List<WebElement> Row2= driver.findElements(By.xpath("//*[@id='container']/div/div/div/div[1]/div["+(i+1)+"]/ul/li"));
				for(int j=1;j<=Row2.size();j++){
					if(driver.findElement(By.xpath("//*[@id='container']/div/div/div/div[1]/div["+(i+1)+"]/ul/li["+j+"]/a")).getText().trim().equals(title)){
						driver.findElement(By.xpath("//*[@id='container']/div/div/div/div[1]/div["+(i+1)+"]/ul/li["+j+"]/a")).click();
						Thread.sleep(500);
					}
				}
			}
		}
	}
}
