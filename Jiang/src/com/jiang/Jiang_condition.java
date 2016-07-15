package com.jiang;

import java.io.File;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;

import readExcel.readExcel;

import com.module.Module;

public class Jiang_condition {

	public static void main(String[] args) throws Exception {
		int count=0;
		FirefoxProfile pro =new FirefoxProfile(new File("C:\\Users\\lixuerui\\AppData\\Roaming\\Mozilla\\Firefox\\Profiles\\81xof17x.default"));
		WebDriver driver =new FirefoxDriver(pro);
		driver.get("http://a.zygames.com/");
		Thread.sleep(1000);
		driver.findElement(By.id("LoginName")).clear();
		driver.findElement(By.id("LoginName")).sendKeys("admin");
		driver.findElement(By.id("LoginPassword")).clear();
		driver.findElement(By.id("LoginPassword")).sendKeys("123.123a");
		driver.findElement(By.className("login_button")).click();
		Thread.sleep(1000);
		Module mod =new Module();
		mod.getModule(driver, "活动管理", "【RV2】注册有奖菜单");
		Thread.sleep(1000);
		driver.findElement(By.cssSelector("input[value=\"兑换条件列表\"]")).click();
		Thread.sleep(500);
		readExcel read =new readExcel();
		read.getExcel("D:\\Data\\Jiang.xls", "condition");    //礼包信息
		for(int i=1;i<read.rows;i++){
			count++;
			if(read.readColumn("条件ID", i).equals("")) break;
			driver.findElement(By.cssSelector("input[value=\"增加\"]")).click();
			Thread.sleep(500);
			driver.findElement(By.id("ConditionID")).clear();
			driver.findElement(By.id("ConditionID")).sendKeys(read.readColumn("条件ID", i));
			driver.findElement(By.id("ConditionDesc")).sendKeys(read.readColumn("条件说明", i));
			driver.findElement(By.id("button2")).click();
			Thread.sleep(500);
			System.out.println(count+"."+read.readColumn("条件说明", i)+"是否新增成功："+driver.findElement(By.className("red")).getText().trim().contains("Success"));
			driver.findElement(By.className("link-clew")).click();
			Thread.sleep(1000);
		}

	}

}
