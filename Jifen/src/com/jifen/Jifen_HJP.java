package com.jifen;

import java.io.File;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.support.ui.Select;

import readExcel.readExcel;

import com.module.Module;

public class Jifen_HJP {

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
		mod.getModule(driver, "活动管理", "(Q)积分_查询");
		readExcel read =new readExcel();
		read.getExcel("D:\\Data\\jifen.xls", "jp");
		for(int i=1;i<read.rows;i++){
			count++;
			if(read.readColumn("用户ID", i).equals("")) break;
			Thread.sleep(1000);
			driver.findElement(By.className("bt-big")).click();
			Thread.sleep(2000);
			driver.findElement(By.id("UserID")).sendKeys(read.readColumn("用户ID", i));
			driver.findElement(By.id("UserName")).sendKeys(read.readColumn("用户名", i));
			Thread.sleep(1000);
			Select GameAreaID= new Select(driver.findElement(By.id("GameAreaID")));
			GameAreaID.selectByVisibleText(read.readColumn("区服", i));
			driver.findElement(By.id("TotalPoints")).sendKeys(read.readColumn("积分", i));
			driver.findElement(By.id("button2")).click();
			Thread.sleep(1000);
			System.out.println(count+"."+read.readColumn("用户名", i)+"&"+read.readColumn("区服", i)+"添加积分是否成功:"+driver.findElement(By.className("red")).getText().trim().contains("成功"));
			driver.findElement(By.className("link-clew")).click();
			Thread.sleep(1000);	
		}

	}

}
