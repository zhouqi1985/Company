package com.toupiao;

import java.io.File;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;

import readExcel.readExcel;

import com.module.Module;

public class touPiao_ZU {

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
		mod.getModule(driver, "ͶƱ����", "ͶƱ���б�");
		Thread.sleep(1000);
		readExcel read =new readExcel();     //ͶƱ������Ϣ
		read.getExcel("D:\\Data\\toupiao_H.xls", "ͶƱ��");
		for(int i=1;i<read.rows;i++){
			count++;
			if(read.readColumn("������", i).equals("")) break;
			driver.findElement(By.cssSelector("input[value=\"����\"]")).click();
			Thread.sleep(1000);
			driver.findElement(By.id("GroupName")).sendKeys(read.readColumn("������", i));
			driver.findElement(By.id("GroupFile")).sendKeys(read.readColumn("���ļ���", i));
			driver.findElement(By.id("button2")).click();
			Thread.sleep(1000);
			System.out.println(count+"."+read.readColumn("������", i)+" �Ƿ������ɹ���"+driver.findElement(By.className("red")).getText().trim().equals("�ɹ���"));
			driver.findElement(By.className("link-clew")).click();
			Thread.sleep(1000);
		}

	}

}
