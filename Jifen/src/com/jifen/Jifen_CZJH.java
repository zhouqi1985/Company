package com.jifen;

import java.io.File;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;

import readExcel.readExcel;

import com.module.Module;

public class Jifen_CZJH {

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
		mod.getModule(driver, "�����", "(Q)����_�ɳ��ƻ�����");
		readExcel read =new readExcel();
		read.getExcel("E:\\Data\\jifen.xls", "czjh");
		for(int i=1;i<read.rows;i++){
			count++;
			if(read.readColumn("�ƻ�����", i).equals("")) break;
			Thread.sleep(500);
			driver.findElement(By.cssSelector("input[value=\"����\"]")).click();
			Thread.sleep(500);
			driver.findElement(By.id("PlanName")).sendKeys(read.readColumn("�ƻ�����", i));
			driver.findElement(By.id("Points")).sendKeys(read.readColumn("�ƻ�����", i));
			driver.findElement(By.id("button2")).click();
			Thread.sleep(1000);
			System.out.println(count+"."+read.readColumn("�ƻ�����", i)+" �Ƿ����ӳɹ���"+driver.findElement(By.className("red")).getText().trim().equals("Success"));
			driver.findElement(By.className("link-clew")).click();
			Thread.sleep(500);
		}

	}

}
