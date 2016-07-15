package com.hongbao;

import java.io.File;

import org.openqa.selenium.By;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;


import readExcel.readExcel;

import com.module.Module;

public class hongbao_shixiao {

	public static void main(String[] args) throws Exception {
		int count=0;
		FirefoxProfile pro =new FirefoxProfile(new File("C:\\Users\\lixuerui\\AppData\\Roaming\\Mozilla\\Firefox\\Profiles\\81xof17x.default"));
		FirefoxDriver driver =new FirefoxDriver(pro);
		driver.get("http://a.zygames.com");
		Thread.sleep(1000);
		driver.findElement(By.id("LoginName")).clear();
		driver.findElement(By.id("LoginName")).sendKeys("huhongbin");
		driver.findElement(By.id("LoginPassword")).clear();
		driver.findElement(By.id("LoginPassword")).sendKeys("123.123.123a");
		driver.findElement(By.className("login_button")).click();
		Thread.sleep(500);
		Module mod =new Module();
		mod.getModule(driver, "活动管理", "(Q)积分兑换_New");
		Thread.sleep(500);
		driver.findElement(By.cssSelector("input[value=\"[红包活动]礼包时效管理\"]")).click();
		readExcel read =new readExcel();
		read.getExcel("E:\\Data\\HB.xls", "SX");
		for(int i=1;i<read.rows;i++){
			count++;
			if(read.readColumn("类型", i).equals("")) break;
			Thread.sleep(500);
			driver.findElement(By.cssSelector("input[value=\"增加\"]")).click();
			Thread.sleep(1000);
			driver.findElement(By.id("RangeId")).sendKeys(read.readColumn("类型", i));
			driver.findElement(By.id("PacketId")).clear();
			driver.findElement(By.id("PacketId")).sendKeys(read.readColumn("礼包ID", i));
			driver.findElement(By.id("EffectiveDate")).clear();
			driver.findElement(By.id("EffectiveDate")).sendKeys(read.readColumn("第一次的生效时间", i));
			driver.findElement(By.id("Interval")).sendKeys(read.readColumn("间隔天数", i));
			driver.findElement(By.id("Loop")).sendKeys(read.readColumn("循环天数", i));
			Thread.sleep(1000);
			driver.findElement(By.id("button2")).click();
			Thread.sleep(500);
			System.out.println(count+"."+read.readColumn("类型", i)+"礼包是否在概率表中添加成功："+driver.findElement(By.className("red")).getText().trim().equals("Success"));
			driver.findElement(By.className("link-clew")).click();
			Thread.sleep(500);
		}

	}

}
