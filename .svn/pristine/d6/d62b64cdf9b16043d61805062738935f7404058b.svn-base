package com.pickweapon;

import java.io.File;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;

import readExcel.readExcel;

import com.module.Module;

public class pickW_LB {

	public static void main(String[] args) throws Exception {
		int count=0;
		FirefoxProfile pro =new FirefoxProfile(new File("C:\\Users\\lixuerui\\AppData\\Roaming\\Mozilla\\Firefox\\Profiles\\81xof17x.default"));
		WebDriver driver =new FirefoxDriver(pro);
		driver.get("http://a.zygames.com/");
		Thread.sleep(1000);
		driver.findElement(By.id("LoginName")).clear();
		driver.findElement(By.id("LoginName")).sendKeys("huhongbin");
		driver.findElement(By.id("LoginPassword")).clear();
		driver.findElement(By.id("LoginPassword")).sendKeys("123.123.123a");
		driver.findElement(By.className("login_button")).click();
		Thread.sleep(1000);
		Module mod =new Module();
		mod.getModule(driver, "活动管理", "十一选点券武器");
		Thread.sleep(1000);
		driver.findElement(By.cssSelector("input[value=\"产品列表\"]")).click();
		Thread.sleep(1000);
		readExcel read =new readExcel();
		read.getExcel("D:\\Data\\pickweapon.xls", "lb");   
		for(int i=1;i<read.rows;i++){
			if(read.readColumn("礼包名称", i).equals("")) break;
			count++;
			driver.findElement(By.cssSelector("input[value=\"增加产品\"]")).click();
			Thread.sleep(500);
			driver.findElement(By.id("ProductName")).sendKeys(read.readColumn("礼包名称", i));
			driver.findElement(By.id("ProductDesc")).sendKeys(read.readColumn("礼包描述", i));
			driver.findElement(By.id("PacketId")).clear();
			driver.findElement(By.id("PacketId")).sendKeys(read.readColumn("礼包ID", i));
			driver.findElement(By.id("button2")).click();
			Thread.sleep(1000);
			System.out.println(count+"."+read.readColumn("礼包名称", i)+" 是否新增成功："+driver.findElement(By.className("red")).getText().trim().equals("Success"));
			driver.findElement(By.className("link-clew")).click();
			Thread.sleep(500);
		}

	}

}
