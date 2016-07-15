package com.laba;

import java.io.File;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.support.ui.Select;

import readExcel.readExcel;

import com.module.Module;

public class LaBa_HGP {

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
		mod.getModule(driver, "活动管理", "(Q)积分兑换拉霸版");
		readExcel read =new readExcel();
		read.getExcel("E:\\Data\\labapoints.xls", "gp");
		Thread.sleep(1000);
		driver.findElement(By.cssSelector("input[value=\"拉霸礼包\"]")).click();
		for(int i=1;i<read.rows;i++){
			count++;
			Thread.sleep(1000);
			Select PacketTypeID =new Select(driver.findElement(By.id("PacketTypeID")));
			PacketTypeID.selectByVisibleText(read.readColumn("礼包类型", i));
			driver.findElement(By.id("PackName")).sendKeys(read.readColumn("礼包名称", i));
			driver.findElement(By.className("bt-samll")).click();
			Thread.sleep(1000);
			driver.findElement(By.className("bt-list")).click();
			Thread.sleep(1000);
			driver.findElement(By.id("IsRoll")).click();
			driver.findElement(By.id("button2")).click();
			Thread.sleep(1000);
			System.out.println(count+"."+read.readColumn("礼包名称", i)+" 礼包是否勾选滚屏:"+driver.findElement(By.className("red")).getText().trim().equals("Success"));
			driver.findElement(By.className("link-clew")).click();
			Thread.sleep(1000);
		}

	}

}
