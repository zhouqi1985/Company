package com.toupiao;

import java.io.File;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.support.ui.Select;

import readExcel.readExcel;

import com.module.Module;

public class toupiao_coupon {

	public static void main(String[] args) throws Exception {
		int count=0;

		FirefoxProfile pro =new FirefoxProfile(new File("C:\\Users\\lixuerui\\AppData\\Roaming\\Mozilla\\Firefox\\Profiles\\81xof17x.default"));
		WebDriver driver = new FirefoxDriver(pro);
		driver.navigate().to("http://a.zygames.com/");
		Thread.sleep(1000);	
		driver.findElement(By.id("LoginName")).clear();
		driver.findElement(By.id("LoginName")).sendKeys("huhongbin");
		driver.findElement(By.id("LoginPassword")).clear();
		driver.findElement(By.id("LoginPassword")).sendKeys("123.123.123a");
		driver.findElement(By.className("login_button")).click();
		Thread.sleep(1000);
		Module mod =new Module();
		mod.getModule(driver, "�����", "ͶƱ�һ�ȯ");
		Thread.sleep(1000);
		readExcel read =new readExcel();
		read.getExcel("D:\\Data\\toupiao_H.xls", "ͶƱ�������");
		Thread.sleep(1000);
		driver.findElement(By.cssSelector("input[value=\"�������\"]")).click();
		Thread.sleep(1000);
		for(int i=1;i<read.rows;i++){
			count++;
			if(read.readColumn("���ID", i).equals("")) break;
			String PackName=read.readColumn("�������", i);
			driver.findElement(By.cssSelector("input[value=\"����\"]")).click();
			Thread.sleep(1000);
			driver.findElement(By.id("PackID")).clear();
			driver.findElement(By.id("PackID")).sendKeys(read.readColumn("���ID", i));
			driver.findElement(By.id("PackName")).sendKeys(PackName);
			driver.findElement(By.id("packDesc")).sendKeys(read.readColumn("�������", i));
			//�Ƿ�һ��������ȡ��Ĭ��Ϊ��
			if(read.readColumn("һ������", i).equals("��")){
				driver.findElement(By.id("DayLimit")).click();
			}
			//�Ƿ�IP������ȡ��Ĭ��Ϊ��
			if(read.readColumn("IP����", i).equals("��")){
				driver.findElement(By.id("IPLimit")).click();
			}
			//�Ƿ�UID������ȡ��Ĭ��Ϊ��
			if(read.readColumn("UID����", i).equals("��")){
				driver.findElement(By.id("UIDLimit")).click();
			}
			Select EventID =new Select(driver.findElement(By.id("EventID")));
			EventID.selectByVisibleText(read.readColumn("�", i));
			driver.findElement(By.id("button2")).click();
			Thread.sleep(500);
			System.out.println(count+"."+PackName+"ͶƱ����Ƿ�ɹ���"+driver.findElement(By.className("red")).getText().equals("Success"));
			driver.findElement(By.className("link-clew")).click();
			Thread.sleep(1000);
		}

	}

}
