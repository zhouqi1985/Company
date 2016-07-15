package com.annex;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class addAnnex {
	
	public void getAnnex(WebDriver driver,String itemName,String Num,String Time,String Type,String Gameid) throws Exception{
		int date =Integer.valueOf(Time);
		driver.findElement(By.id("btnItemSelect")).click();
		Thread.sleep(2000);
		if(Type.equals("id")){    //判断是否使用的是itemcode建立附件
			driver.findElement(By.id("ItemCode")).clear();
			driver.findElement(By.id("ItemCode")).sendKeys(itemName);
			Thread.sleep(1000);
			Select GameID=new Select(driver.findElement(By.id("GameID")));
			GameID.selectByVisibleText(Gameid);
		}else{
			driver.findElement(By.id("ItemName")).clear();
			driver.findElement(By.id("ItemName")).sendKeys(itemName);
			Thread.sleep(1000);
			Select GameID=new Select(driver.findElement(By.id("GameID")));
			GameID.selectByVisibleText(Gameid);
		}
		driver.findElement(By.id("btSearch")).click();
		Thread.sleep(1500);
		List<WebElement> options =driver.findElements(By.cssSelector("table#itemListTable tr"));
		if(options.size()>1){
			for(int k=2;k<=options.size();k++){
				if(Type.equals("id")){  //搜索类型为id
					if(driver.findElement(By.xpath("//*[@id='itemListTable']/tbody/tr["+k+"]/td[2]")).getText().equals(itemName)){
						driver.findElement(By.xpath("//*[@id='itemListTable']/tbody/tr["+k+"]/td[1]/input")).click();
					}
				}	
				
				if(Type.equals("服装")){ //搜索类型为服装
					 if(driver.findElement(By.xpath("//*[@id='itemListTable']/tbody/tr["+k+"]/td[3]")).getText().equals(itemName)&&driver.findElement(By.xpath("//*[@id='itemListTable']/tbody/tr["+k+"]/td[2]")).getText().contains("_")){
							driver.findElement(By.xpath("//*[@id='itemListTable']/tbody/tr["+k+"]/td[1]/input")).click();
			         }
				}
				
				if(Type.equals("礼包")){  //搜索类型为礼包
					if(driver.findElement(By.xpath("//*[@id='itemListTable']/tbody/tr["+k+"]/td[3]")).getText().equals(itemName)){
						driver.findElement(By.xpath("//*[@id='itemListTable']/tbody/tr["+k+"]/td[1]/input")).click();
					}
				}
				
	         }
			driver.findElement(By.xpath("//html/body/div[6]/div[11]/button[2]")).click();
	   }else{
		   driver.findElement(By.xpath("//html/body/div[6]/div[11]/button[1]")).click();
	   }
		Thread.sleep(200);
		List<WebElement> row =driver.findElements(By.cssSelector("tbody#selectedItemBody tr"));
		for(int h=1;h<=row.size();h++){
			if(Type.equals("id")){
				if(driver.findElement(By.xpath("//*[@id='selectedItemBody']/tr[" + h + "]/td[1]")).getText().equals(itemName)){
					if(date>0){
						String time =String.valueOf(Integer.valueOf(Time));
						driver.findElement(By.xpath("//*[@id='selectedItemBody']/tr[" + h + "]/td[3]/input")).clear(); 
						driver.findElement(By.xpath("//*[@id='selectedItemBody']/tr[" + h + "]/td[3]/input")).sendKeys(time);
					}else {
						String time="-1";
						driver.findElement(By.xpath("//*[@id='selectedItemBody']/tr[" + h+ "]/td[3]/input")).clear();
						driver.findElement(By.xpath("//*[@id='selectedItemBody']/tr[" +h + "]/td[3]/input")).sendKeys(time);
					}
						driver.findElement(By.xpath("//*[@id='selectedItemBody']/tr["+h+"]/td[4]/input[2]")).clear();
	       				driver.findElement(By.xpath("//*[@id='selectedItemBody']/tr["+h+"]/td[4]/input[2]")).sendKeys(Num);	
				}	
			}else{
				if(driver.findElement(By.xpath("//*[@id='selectedItemBody']/tr[" + h + "]/td[2]")).getText().equals(itemName)){
					if(date>0){
						String time =String.valueOf(Integer.valueOf(Time));
						driver.findElement(By.xpath("//*[@id='selectedItemBody']/tr[" + h + "]/td[3]/input")).clear();
						driver.findElement(By.xpath("//*[@id='selectedItemBody']/tr[" + h + "]/td[3]/input")).sendKeys(time);
					}else {
						String time="-1";
						driver.findElement(By.xpath("//*[@id='selectedItemBody']/tr[" + h+ "]/td[3]/input")).clear();
						driver.findElement(By.xpath("//*[@id='selectedItemBody']/tr[" +h + "]/td[3]/input")).sendKeys(time);
					}
						driver.findElement(By.xpath("//*[@id='selectedItemBody']/tr["+h+"]/td[4]/input[2]")).clear();
	       				driver.findElement(By.xpath("//*[@id='selectedItemBody']/tr["+h+"]/td[4]/input[2]")).sendKeys(Num);	
				}
			}
		}
	}
	
	public void getXYAnnex(WebDriver driver,String itemName,String Num,String Stars,String Type,String Gameid,String Sort) throws Exception{
		driver.findElement(By.id("btnItemSelect")).click();
		Thread.sleep(2000);
		if(Type.equals("id")){    //判断是否使用的是itemcode建立附件
			driver.findElement(By.id("ItemCode")).clear();
			driver.findElement(By.id("ItemCode")).sendKeys(itemName);
			Thread.sleep(1000);
			Select GameID=new Select(driver.findElement(By.id("GameID")));
			GameID.selectByVisibleText(Gameid);
		}else{
			driver.findElement(By.id("ItemName")).clear();
			driver.findElement(By.id("ItemName")).sendKeys(itemName);
			Thread.sleep(1000);
			Select GameID=new Select(driver.findElement(By.id("GameID")));
			GameID.selectByVisibleText(Gameid);
		}
		driver.findElement(By.id("btSearch")).click();
		Thread.sleep(1500);
		List<WebElement> options =driver.findElements(By.cssSelector("table#itemListTable tr"));
		if(options.size()>1){
			for(int k=2;k<=options.size();k++){
				if(Type.equals("id")){  //搜索类型为id
					if(driver.findElement(By.xpath("//*[@id='itemListTable']/tbody/tr["+k+"]/td[2]")).getText().equals(itemName)){
						driver.findElement(By.xpath("//*[@id='itemListTable']/tbody/tr["+k+"]/td[1]/input")).click();
					}
				}	
				
				if(Type.equals("服装")){ //搜索类型为服装
					 if(driver.findElement(By.xpath("//*[@id='itemListTable']/tbody/tr["+k+"]/td[3]")).getText().equals(itemName)&&driver.findElement(By.xpath("//*[@id='itemListTable']/tbody/tr["+k+"]/td[2]")).getText().contains("_")){
							driver.findElement(By.xpath("//*[@id='itemListTable']/tbody/tr["+k+"]/td[1]/input")).click();
			         }
				}
				
				if(Type.equals("礼包")){  //搜索类型为礼包
					if(driver.findElement(By.xpath("//*[@id='itemListTable']/tbody/tr["+k+"]/td[3]")).getText().equals(itemName)){
						driver.findElement(By.xpath("//*[@id='itemListTable']/tbody/tr["+k+"]/td[1]/input")).click();
					}
				}
				
	         }
			driver.findElement(By.xpath("//html/body/div[6]/div[11]/button[2]")).click();
	   }else{
		   driver.findElement(By.xpath("//html/body/div[6]/div[11]/button[1]")).click();
	   }
		Thread.sleep(200);
		List<WebElement> row =driver.findElements(By.cssSelector("tbody#selectedItemBody tr"));
		for(int h=1;h<=row.size();h++){
			if(Type.equals("id")){
				if(driver.findElement(By.xpath("//*[@id='selectedItemBody']/tr[" + h + "]/td[1]")).getText().equals(itemName)){
					driver.findElement(By.xpath("//*[@id='selectedItemBody']/tr["+h+"]/td[3]/input")).clear();
					driver.findElement(By.xpath("//*[@id='selectedItemBody']/tr["+h+"]/td[3]/input")).sendKeys(Sort);
					driver.findElement(By.xpath("//*[@id='selectedItemBody']/tr["+h+"]/td[4]/input")).clear();
	       			driver.findElement(By.xpath("//*[@id='selectedItemBody']/tr["+h+"]/td[4]/input")).sendKeys(Num);
	       			driver.findElement(By.xpath("//*[@id='selectedItemBody']/tr["+h+"]/td[5]/input")).clear();
	       			driver.findElement(By.xpath("//*[@id='selectedItemBody']/tr["+h+"]/td[5]/input")).sendKeys(Stars);
				}	
			}else{
				if(driver.findElement(By.xpath("//*[@id='selectedItemBody']/tr[" + h + "]/td[2]")).getText().equals(itemName)){
					driver.findElement(By.xpath("//*[@id='selectedItemBody']/tr["+h+"]/td[3]/input")).clear();
					driver.findElement(By.xpath("//*[@id='selectedItemBody']/tr["+h+"]/td[3]/input")).sendKeys(Sort);
					driver.findElement(By.xpath("//*[@id='selectedItemBody']/tr["+h+"]/td[4]/input")).clear();
	       			driver.findElement(By.xpath("//*[@id='selectedItemBody']/tr["+h+"]/td[4]/input")).sendKeys(Num);
	       			driver.findElement(By.xpath("//*[@id='selectedItemBody']/tr["+h+"]/td[5]/input")).clear();
	       			driver.findElement(By.xpath("//*[@id='selectedItemBody']/tr["+h+"]/td[5]/input")).sendKeys(Stars);
				}
			}
		}
	}

}
