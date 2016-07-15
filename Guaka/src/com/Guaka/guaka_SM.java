package com.Guaka;

import java.io.File;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;



import readExcel.readExcel;

import com.module.Module;

public class guaka_SM{

	public static void main(String[] args) throws Exception {
		int count=0;
		FirefoxProfile pro =new FirefoxProfile(new File("C:\\Users\\lixuerui\\AppData\\Roaming\\Mozilla\\Firefox\\Profiles\\81xof17x.default"));
		WebDriver driver =new FirefoxDriver(pro);
		driver.navigate().to("http://a.zygames.com/");
		Thread.sleep(1000);
		driver.findElement(By.id("LoginName")).clear();
		driver.findElement(By.id("LoginName")).sendKeys("admin");
		driver.findElement(By.id("LoginPassword")).clear();
		driver.findElement(By.id("LoginPassword")).sendKeys("123.123a");
		driver.findElement(By.className("login_button")).click();
		Thread.sleep(1000);
		Module mod =new Module();
		mod.getModule(driver, "活动管理", "[新]充值活动");
		readExcel read =new readExcel();
		read.getExcel("E:\\Data\\guaka.xls", "SM");
		Thread.sleep(1000);
		driver.findElement(By.cssSelector("input[value=\"神秘商店礼包管理\"]")).click();
		for(int i=1;i<read.rows;i++){
			count++;
			if(read.readColumn("类型", i).equals("")) break;
			Thread.sleep(1000);
			driver.findElement(By.cssSelector("input[value=\"增加神秘商店奖励\"]")).click();
			Thread.sleep(1000);
			driver.findElement(By.id("PacketId")).clear();
			driver.findElement(By.id("PacketId")).sendKeys(read.readColumn("礼包ID", i));
			driver.findElement(By.id("PacketName")).sendKeys(read.readColumn("礼包名称", i));
			Thread.sleep(1000);
//		Select PacketTypeID =new Select(driver.findElement(By.id("RangeId")));
//			PacketTypeID.selectByVisibleText(read.readColumn("礼包类型", i));
//			driver.findElement(By.id("ClassID")).clear();     //充值产品ID，默认为0（一般情况不用更改）
//			driver.findElement(By.id("ClassID")).sendKeys(read.readColumn("ClassID", i));
			if(read.readColumn("是否公告", i).equals("是")){
				driver.findElement(By.id("IsNotice")).click();
			}
			Thread.sleep(500);
			driver.findElement(By.id("button2")).click();
			Thread.sleep(1000);
			System.out.println(count+"."+read.readColumn("礼包名称", i)+" 礼包是否新增成功:"+driver.findElement(By.className("red")).getText().trim().equals("Success"));
			driver.findElement(By.className("link-clew")).click();
			Thread.sleep(1000);
		}

	}

}

