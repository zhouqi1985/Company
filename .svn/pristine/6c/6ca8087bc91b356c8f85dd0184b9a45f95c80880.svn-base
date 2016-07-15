package com.pickweapon;

import java.io.File;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.support.ui.Select;

import readExcel.readExcel;

import com.module.Module;

public class pickW_JF {

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
		driver.findElement(By.cssSelector("input[value=\"钱包列表\"]")).click();
		Thread.sleep(1000);
		readExcel read =new readExcel();
		read.getExcel("D:\\Data\\pickweapon.xls", "jf");   
		for(int i=1;i<read.rows;i++){
			if(read.readColumn("用户ID", i).equals("")) break;
			count++;
			driver.findElement(By.cssSelector("input[value=\"新增\"]")).click();
			Thread.sleep(500);
			driver.findElement(By.id("UserId")).clear();
			driver.findElement(By.id("UserId")).sendKeys(read.readColumn("用户ID", i));
			driver.findElement(By.id("AvatarId")).clear();
			driver.findElement(By.id("AvatarId")).sendKeys(read.readColumn("角色ID", i));
			Thread.sleep(500);
			Select GameAreaId =new Select(driver.findElement(By.id("GameAreaId")));
			GameAreaId.selectByVisibleText(read.readColumn("区服", i));
			driver.findElement(By.id("button2")).click();
			Thread.sleep(1000);
			System.out.println(count+"."+read.readColumn("用户ID", i)+"-"+read.readColumn("区服", i)+" 积分是否增加成功："+driver.findElement(By.className("red")).getText().trim().equals("Success"));
			driver.findElement(By.className("link-clew")).click();
			Thread.sleep(500);
		}

	}

}
