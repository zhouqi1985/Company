package com.Guaka;

import java.io.File;

import org.openqa.selenium.By;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.support.ui.Select;

import readExcel.readExcel;

import com.module.Module;

public class guaka_pt {

	public static void main(String[] args) throws Exception {
		int count=0;
		FirefoxProfile pro =new FirefoxProfile(new File("C:\\Users\\lixuerui\\AppData\\Roaming\\Mozilla\\Firefox\\Profiles\\81xof17x.default"));
		FirefoxDriver driver =new FirefoxDriver(pro);
		driver.get("http://a.zygames.com");
		Thread.sleep(1000);
		driver.findElement(By.id("LoginName")).clear();
		driver.findElement(By.id("LoginName")).sendKeys("huhongbin");
		driver.findElement(By.id("LoginPassword")).clear();
		driver.findElement(By.id("LoginPassword")).sendKeys("123.123.123a");
		driver.findElement(By.className("login_button")).click();
		Thread.sleep(500);
		Module mod =new Module();
		mod.getModule(driver, "�����", "(Q)���ֶһ�_New");
		Thread.sleep(500);
		driver.findElement(By.cssSelector("input[value=\"��ͨ����\"]")).click();
		readExcel read =new readExcel();
		read.getExcel("E:\\Data\\guaka.xls", "pt");
		for(int i=1;i<read.rows;i++){
			count++;
			if(read.readColumn("�������", i).equals("")) break;
			Thread.sleep(500);
			driver.findElement(By.cssSelector("input[value=\"������������\"]")).click();
			Thread.sleep(1000);
			Select MachineId=new Select(driver.findElement(By.id("RangeId")));
			MachineId.selectByVisibleText(read.readColumn("����", i));
			Thread.sleep(500);
			Select PacketId =new Select(driver.findElement(By.id("PacketId")));
			PacketId.selectByVisibleText(read.readColumn("�������", i));
			driver.findElement(By.id("button2")).click();
			Thread.sleep(500);
			System.out.println(count+"."+read.readColumn("����", i)+"-"+read.readColumn("�������", i)+"����Ƿ��ڸ��ʱ�����ӳɹ���"+driver.findElement(By.className("red")).getText().trim().equals("Success"));
			driver.findElement(By.className("link-clew")).click();
			Thread.sleep(500);
		}

	}

}
