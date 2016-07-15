package com.niudan;

import java.io.File;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.support.ui.Select;

import readExcel.readExcel;

import com.module.Module;

public class NiuDan_JF {

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
		mod.getModule(driver, "�����", "(Q)Ť������ֵ���ֶһ�");
		Thread.sleep(1000);
		driver.findElement(By.cssSelector("input[value=\"���ֲ�ѯ\"]")).click();
		readExcel read =new readExcel();
		read.getExcel("E:\\Data\\niudan.xls", "jf");
		for(int i=1;i<read.rows;i++){
			count++;
			if(read.readColumn("�û�ID", i).equals("")) break;
			Thread.sleep(1000);
			driver.findElement(By.cssSelector("input[value=\"��ӻ���\"]")).click();
			Thread.sleep(500);
			driver.findElement(By.id("UserID")).sendKeys(read.readColumn("�û�ID", i));
			driver.findElement(By.id("UserName")).sendKeys(read.readColumn("�û���", i));
			Thread.sleep(500);
			Select GameAreaID =new Select(driver.findElement(By.id("GameAreaID")));
			GameAreaID.selectByVisibleText(read.readColumn("����", i));
			driver.findElement(By.id("TotalPoints")).sendKeys(read.readColumn("����", i));
			driver.findElement(By.id("button2")).click();
			Thread.sleep(1000);
			System.out.println(count+"."+read.readColumn("�û���", i)+"-"+read.readColumn("����", i)+" �����Ƿ����ӳɹ�:"+driver.findElement(By.className("red")).getText().trim().equals("Success"));
			driver.findElement(By.className("link-clew")).click();
			Thread.sleep(1000);
		}

	}

}
