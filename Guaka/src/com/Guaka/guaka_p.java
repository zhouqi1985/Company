package com.Guaka;

import java.io.File;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.support.ui.Select;

import readExcel.readExcel;

import com.module.Module;

public class guaka_p{

	public static void main(String[] args) throws Exception {
		int count=0;
		FirefoxProfile pro =new FirefoxProfile(new File("C:\\Users\\lixuerui\\AppData\\Roaming\\Mozilla\\Firefox\\Profiles\\81xof17x.default"));
		WebDriver driver =new FirefoxDriver(pro);
		driver.navigate().to("http://a.zygames.com/");
		Thread.sleep(1000);
		driver.findElement(By.id("LoginName")).clear();
		driver.findElement(By.id("LoginName")).sendKeys("admin");
		driver.findElement(By.id("LoginPassword")).clear();
		driver.findElement(By.id("LoginPassword")).sendKeys("123.123a");
		driver.findElement(By.className("login_button")).click();
		Thread.sleep(1000);
		Module mod =new Module();
		mod.getModule(driver, "�����", "[��]��ֵ�");
		readExcel read =new readExcel();
		read.getExcel("E:\\Data\\guaka.xls", "ggk");
		Thread.sleep(1000);
		driver.findElement(By.cssSelector("input[value=\"��ͨ�������\"]")).click();
		for(int i=1;i<read.rows;i++){
			count++;
			if(read.readColumn("���ID", i).equals("")) break;
			Thread.sleep(1000);
			driver.findElement(By.cssSelector("input[value=\"������ͨ����\"]")).click();
			Thread.sleep(1000);
			driver.findElement(By.id("PacketId")).clear();
			driver.findElement(By.id("PacketId")).sendKeys(read.readColumn("���ID", i));
			driver.findElement(By.id("PacketName")).sendKeys(read.readColumn("�������", i));
			Thread.sleep(500);
		    Select PacketTypeID =new Select(driver.findElement(By.id("RangeId")));
		    PacketTypeID.selectByVisibleText(read.readColumn("�������", i));
//			driver.findElement(By.id("ClassID")).clear();     //��ֵ��ƷID��Ĭ��Ϊ0��һ��������ø��ģ�
//			driver.findElement(By.id("ClassID")).sendKeys(read.readColumn("ClassID", i));
			if(read.readColumn("�Ƿ񹫸�", i).equals("��")){
				driver.findElement(By.id("IsNotice")).click();
			}
//			driver.findElement(By.id("BuyCount")).clear();  //ͳ�������ȡ����Ĭ��Ϊ0
//			driver.findElement(By.id("BuyCount")).sendKeys(read.readColumn("BuyCount", i));
/*			if(read.readColumn("�Ƿ����", i).equals("��")){
				driver.findElement(By.id("IsRoll")).click();
			}
            if(read.readColumn("�Ƿ�����ظ��õ�", i).equals("��")){
				driver.findElement(By.id("IsRepeat")).click();
			}*/
			driver.findElement(By.id("button2")).click();
			Thread.sleep(1000);
			System.out.println(count+"."+read.readColumn("�������", i)+" ����Ƿ������ɹ�:"+driver.findElement(By.className("red")).getText().trim().equals("Success"));
			driver.findElement(By.className("link-clew")).click();
			Thread.sleep(1000);
		}

	}

}