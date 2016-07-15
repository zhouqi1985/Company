package com.rich;

import java.io.File;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;

import readExcel.readExcel;

import com.module.Module;

public class Rich_CP {

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
		mod.getModule(driver, "活动管理", "双蛋大富翁");
		Thread.sleep(500);
		driver.findElement(By.cssSelector("input[value=\"产品列表\"]")).click();
		readExcel read =new readExcel();
		read.getExcel("D:\\Data\\rich.xls", "cp");
		for(int i=1;i<read.rows;i++){
			Thread.sleep(1000);
			if(read.readColumn("产品名称", i).equals("")) break;
			driver.findElement(By.cssSelector("input[value=\"增加\"]")).click();
			Thread.sleep(500);
			driver.findElement(By.id("PacketName")).sendKeys(read.readColumn("产品名称", i));
			driver.findElement(By.id("PacketDescription")).sendKeys(read.readColumn("产品描述", i));
			driver.findElement(By.id("PacketID")).sendKeys(read.readColumn("礼包ID", i));
			if(read.readColumn("是否是Coupon礼包", i).equals("是")){
				driver.findElement(By.id("IsCoponPacket")).click();
			}
			if(read.readColumn("是否滚动显示", i).equals("是")){
				driver.findElement(By.id("IsRoll")).click();
			}
			if(read.readColumn("是否允许重复", i).equals("是")){
				driver.findElement(By.id("IsRepeat")).click();
			}
			if(read.readColumn("是否通知游戏", i).equals("是")){
				driver.findElement(By.id("IsNotice")).click();
			}
			driver.findElement(By.id("button2")).click();
			Thread.sleep(1000);
			System.out.println(count+"."+read.readColumn("产品名称", i)+" 是否新增成功:"+driver.findElement(By.className("red")).getText().trim().equals("Success"));
			driver.findElement(By.className("link-clew")).click();
			Thread.sleep(1000);
		}
	}
}
