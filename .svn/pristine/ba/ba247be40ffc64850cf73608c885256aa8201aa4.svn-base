package com.Additem;

import java.io.File;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.support.ui.Select;

import readExcel.readExcel;

import com.module.Module;

public class addItem {

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
		mod.getModule(driver, "兑换券系统", "Item管理");
		readExcel read =new readExcel();
		String path="D:\\Data\\itemId.xls";
		String sheetname ="item";
		read.getExcel(path, sheetname);
		for(int i=1;i<read.rows;i++){
			count++;
			driver.findElement(By.className("bt-big")).click();
			Thread.sleep(1000);
			driver.findElement(By.id("ItemCode")).clear();
			driver.findElement(By.id("ItemCode")).sendKeys(read.readColumn("ItemCode", i));
			Thread.sleep(300);
			driver.findElement(By.id("ItemName")).clear();
			driver.findElement(By.id("ItemName")).sendKeys(read.readColumn("ItemName", i));
			Thread.sleep(1000);
			Select GameID =new Select(driver.findElement(By.id("GameID")));
			GameID.selectByVisibleText(read.readColumn("GameID", i));
			Thread.sleep(1000);
			driver.findElement(By.id("NewItemClass")).clear();
			Thread.sleep(200);
			Select ItemClass =new Select(driver.findElement(By.id("ItemClass")));
			ItemClass.selectByVisibleText(read.readColumn("ItemClass", i));
			Thread.sleep(1000);
			driver.findElement(By.id("NewItemSubClass")).clear();
			Thread.sleep(1000);
			Select ItemSubClass =new Select(driver.findElement(By.id("ItemSubClass")));
			ItemSubClass.selectByVisibleText(read.readColumn("ItemSubClass", i));
			Thread.sleep(500);
            driver.findElement(By.id("button2")).click();
            Thread.sleep(500);
            System.out.println(count + "."+read.readColumn("ItemName", i)+"是否新增成功："+driver.findElement(By.className("red")).getText().equals("成功！"));
            driver.findElement(By.className("link-clew")).click();
            Thread.sleep(1000);
		}
	}
}
