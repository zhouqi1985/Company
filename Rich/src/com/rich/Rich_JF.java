package com.rich;

import java.io.File;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.support.ui.Select;

import readExcel.readExcel;

import com.module.Module;

public class Rich_JF {

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
		mod.getModule(driver, "�����", "˫������");
		Thread.sleep(500);
		driver.findElement(By.cssSelector("input[value=\"�����б�\"]")).click();
		readExcel read =new readExcel();
		read.getExcel("D:\\Data\\rich.xls", "jf");
		for(int i=1;i<read.rows;i++){
			count++;
			if(read.readColumn("�û�ID", i).equals("")) break;
			Thread.sleep(1000);
			driver.findElement(By.cssSelector("input[value=\"����\"]")).click();
			Thread.sleep(500);
			driver.findElement(By.id("UserID")).clear();
			driver.findElement(By.id("UserID")).sendKeys(read.readColumn("�û�ID", i));
			Thread.sleep(1000);
			Select GameArea =new Select(driver.findElement(By.id("GameArea")));
			GameArea.selectByVisibleText(read.readColumn("����", i));
			driver.findElement(By.id("button2")).click();
			Thread.sleep(1000);
			System.out.println(count+"."+read.readColumn("�û�ID", i)+" �����Ƿ������ɹ�:"+driver.findElement(By.className("red")).getText().trim().equals("Success"));
			driver.findElement(By.className("link-clew")).click();
			Thread.sleep(1000);
			
			
		}

	}

}
