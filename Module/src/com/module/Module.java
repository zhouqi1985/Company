package com.module;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;


public class Module {
	
	public void getModule(WebDriver driver,String type,String title) throws Exception{
		//切换frame到左边模块
		driver.switchTo().frame("admin_left");
		List<WebElement> tRows =driver.findElements(By.xpath("//*[@id='red']/li"));
		for(int i=1;i<=tRows.size();i++){
			if(driver.findElement(By.xpath("//*[@id='red']/li["+i+"]/span")).getText().equals(type)){
				driver.findElement(By.xpath("//*[@id='red']/li["+i+"]/span")).click();
				Thread.sleep(1000);
				List<WebElement> eRows =driver.findElements(By.xpath("//*[@id='red']/li["+i+"]/ul/li"));
				for(int j=1;j<=eRows.size();j++){
					if(driver.findElement(By.xpath("//*[@id='red']/li["+i+"]/ul/li["+j+"]/span/a")).getText().equals(title)){
						driver.findElement(By.xpath("//*[@id='red']/li["+i+"]/ul/li["+j+"]/span/a")).click();
						Thread.sleep(1000);
					}
				}
			}
		}
		//切换到默认模块
		driver.switchTo().defaultContent();
		//切换到信息输入模块
	    driver.switchTo().frame("admin_main");
	}
	public void repeatModule(WebDriver driver,String type,String title) throws Exception{
		driver.switchTo().defaultContent();
		//切换frame到左边模块
		driver.switchTo().frame("admin_left");
		List<WebElement> tRows =driver.findElements(By.xpath("//*[@id='red']/li"));
		for(int i=1;i<=tRows.size();i++){
			if(driver.findElement(By.xpath("//*[@id='red']/li["+i+"]/span")).getText().equals(type)){
				Thread.sleep(1000);
				List<WebElement> eRows =driver.findElements(By.xpath("//*[@id='red']/li["+i+"]/ul/li"));
				for(int j=1;j<=eRows.size();j++){
					if(driver.findElement(By.xpath("//*[@id='red']/li["+i+"]/ul/li["+j+"]/span/a")).getText().equals(title)){
						driver.findElement(By.xpath("//*[@id='red']/li["+i+"]/ul/li["+j+"]/span/a")).click();
						Thread.sleep(1000);
					}
				}
			}
		}
		//切换到默认模块
		driver.switchTo().defaultContent();
		//切换到信息输入模块
	    driver.switchTo().frame("admin_main");
	}
}
