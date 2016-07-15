package com.toupiao;

import java.io.File;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.support.ui.Select;

import readExcel.readExcel;

import com.module.Module;

public class toupiao_coupon {

	public static void main(String[] args) throws Exception {
		int count=0;

		FirefoxProfile pro =new FirefoxProfile(new File("C:\\Users\\lixuerui\\AppData\\Roaming\\Mozilla\\Firefox\\Profiles\\81xof17x.default"));
		WebDriver driver = new FirefoxDriver(pro);
		driver.navigate().to("http://a.zygames.com/");
		Thread.sleep(1000);	
		driver.findElement(By.id("LoginName")).clear();
		driver.findElement(By.id("LoginName")).sendKeys("huhongbin");
		driver.findElement(By.id("LoginPassword")).clear();
		driver.findElement(By.id("LoginPassword")).sendKeys("123.123.123a");
		driver.findElement(By.className("login_button")).click();
		Thread.sleep(1000);
		Module mod =new Module();
		mod.getModule(driver, "活动管理", "投票兑换券");
		Thread.sleep(1000);
		readExcel read =new readExcel();
		read.getExcel("D:\\Data\\toupiao_H.xls", "投票礼包配置");
		Thread.sleep(1000);
		driver.findElement(By.cssSelector("input[value=\"礼包管理\"]")).click();
		Thread.sleep(1000);
		for(int i=1;i<read.rows;i++){
			count++;
			if(read.readColumn("礼包ID", i).equals("")) break;
			String PackName=read.readColumn("礼包名称", i);
			driver.findElement(By.cssSelector("input[value=\"增加\"]")).click();
			Thread.sleep(1000);
			driver.findElement(By.id("PackID")).clear();
			driver.findElement(By.id("PackID")).sendKeys(read.readColumn("礼包ID", i));
			driver.findElement(By.id("PackName")).sendKeys(PackName);
			driver.findElement(By.id("packDesc")).sendKeys(read.readColumn("礼包描述", i));
			//是否一天限制领取（默认为否）
			if(read.readColumn("一天限制", i).equals("是")){
				driver.findElement(By.id("DayLimit")).click();
			}
			//是否IP限制领取（默认为否）
			if(read.readColumn("IP限制", i).equals("是")){
				driver.findElement(By.id("IPLimit")).click();
			}
			//是否UID限制领取（默认为否）
			if(read.readColumn("UID限制", i).equals("是")){
				driver.findElement(By.id("UIDLimit")).click();
			}
			Select EventID =new Select(driver.findElement(By.id("EventID")));
			EventID.selectByVisibleText(read.readColumn("活动", i));
			driver.findElement(By.id("button2")).click();
			Thread.sleep(500);
			System.out.println(count+"."+PackName+"投票礼包是否成功："+driver.findElement(By.className("red")).getText().equals("Success"));
			driver.findElement(By.className("link-clew")).click();
			Thread.sleep(1000);
		}

	}

}
