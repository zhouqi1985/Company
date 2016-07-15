package com.hongbao;

import java.io.File;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.support.ui.Select;

import readExcel.readExcel;

import com.module.Module;

public class hongbao_jp {

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
		mod.getModule(driver, "活动管理", "(Q)积分兑换_New");
		readExcel read =new readExcel();
		read.getExcel("E:\\Data\\HB.xls", "JP");
		Thread.sleep(1000);
		driver.findElement(By.cssSelector("input[value=\"积分列表\"]")).click();
		Thread.sleep(1000);
		for(int i=1;i<read.rows;i++){
			count++;
			if(read.readColumn("用户ID", i).equals("")) break;
			Thread.sleep(1000);
			driver.findElement(By.cssSelector("input[value=\"增加\"]")).click();
			Thread.sleep(1000);
			driver.findElement(By.id("UserId")).clear();
			driver.findElement(By.id("UserId")).sendKeys(read.readColumn("用户ID", i));
			Thread.sleep(1000);
			Select GameAreaID= new Select(driver.findElement(By.id("GameAreaId")));
			GameAreaID.selectByVisibleText(read.readColumn("区服", i));
			driver.findElement(By.id("BalancePoints")).sendKeys(read.readColumn("积分", i));
			driver.findElement(By.id("button2")).click();
			Thread.sleep(1000);
			System.out.println(count+"."+read.readColumn("用户名", i)+"&"+read.readColumn("区服", i)+"添加积分是否成功:"+driver.findElement(By.className("red")).getText().trim().equals("Success"));
			driver.findElement(By.className("link-clew")).click();
			Thread.sleep(1000);	
		}

	}

}
