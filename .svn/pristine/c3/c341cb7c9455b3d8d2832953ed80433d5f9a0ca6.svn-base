package com.laba;

import java.io.File;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.support.ui.Select;

import readExcel.readExcel;

import com.module.Module;

public class LaBa_CZLB {

	public static void main(String[] args) throws Exception {
		int count=0;
		FirefoxProfile pro =new FirefoxProfile(new File("C:\\Users\\lixuerui\\AppData\\Roaming\\Mozilla\\Firefox\\Profiles\\81xof17x.default"));
		WebDriver driver =new FirefoxDriver(pro);
		driver.navigate().to("http://a.zygames.com/");
		Thread.sleep(1000);
		driver.findElement(By.id("LoginName")).clear();
		driver.findElement(By.id("LoginName")).sendKeys("huhongbin");
		driver.findElement(By.id("LoginPassword")).clear();
		driver.findElement(By.id("LoginPassword")).sendKeys("123.123.123a");
		driver.findElement(By.className("login_button")).click();
		Thread.sleep(1000);
		Module mod =new Module();
		mod.getModule(driver, "活动管理", "(Q)积分_成长计划管理");
		readExcel read =new readExcel();
		read.getExcel("E:\\Data\\labapoints.xls", "czlb");
		Thread.sleep(500);
		driver.findElement(By.cssSelector("input[value=\"查看成长计划礼包\"]")).click();
		for(int i=1;i<read.rows;i++){
			count++;
			if(read.readColumn("礼包名称", i).equals("")) break;
			Thread.sleep(500);
			driver.findElement(By.cssSelector("input[value=\"增加\"]")).click();
			Thread.sleep(1000);
			Select PlanID =new Select(driver.findElement(By.id("PlanID")));
			PlanID.selectByVisibleText(read.readColumn("计划", i));
			driver.findElement(By.id("PacketID")).sendKeys(read.readColumn("礼包ID", i));
			driver.findElement(By.id("PacketName")).sendKeys(read.readColumn("礼包名称", i));
			driver.findElement(By.id("AfterDay")).sendKeys(read.readColumn("发放天数", i));
			driver.findElement(By.id("button2")).click();
			Thread.sleep(1000);
			System.out.println(count+"."+read.readColumn("礼包名称", i)+" 是否增加成功："+driver.findElement(By.className("red")).getText().trim().equals("Success"));
			driver.findElement(By.className("link-clew")).click();
			Thread.sleep(500);
		}

	}

}
