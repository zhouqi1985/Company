package com.giftpoint;

import java.io.File;

import org.openqa.selenium.By;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;

import readExcel.readExcel;

import com.module.Module;

public class giftPoint_HLaBa {

	public static void main(String[] args) throws Exception {
		int count =0;
		FirefoxProfile pro =new FirefoxProfile(new File("C:\\Users\\lixuerui\\AppData\\Roaming\\Mozilla\\Firefox\\Profiles\\81xof17x.default"));
		FirefoxDriver driver =new FirefoxDriver(pro);
		driver.get("http://a.zygames.com");
		Thread.sleep(1000);
		driver.findElement(By.id("LoginName")).clear();
		driver.findElement(By.id("LoginName")).sendKeys("huhongbin");
		driver.findElement(By.id("LoginPassword")).clear();
		driver.findElement(By.id("LoginPassword")).sendKeys("123.123.123a");
		driver.findElement(By.className("login_button")).click();
		Thread.sleep(1000);
		Module mod =new Module();
		mod.getModule(driver, "�����", "(Q)����һ�ȯ�");
		readExcel read =new readExcel();
		read.getExcel("E:\\Data\\giftpoint.xls", "laba");
		Thread.sleep(1000);
		driver.findElement(By.cssSelector("input[value=\"�����������\"]")).click();
		for(int i=1;i<read.rows;i++){
			count++;
			if(read.readColumn("���ID", i).equals("")) break;
			Thread.sleep(1000);
			driver.findElement(By.cssSelector("input[value=\"����\"]")).click();
			Thread.sleep(1000);
			driver.findElement(By.id("PacketID")).clear();
			driver.findElement(By.id("PacketID")).sendKeys(read.readColumn("���ID", i));
			driver.findElement(By.id("PacketName")).sendKeys(read.readColumn("�������", i).trim());
			driver.findElement(By.id("NeedPoints")).clear();
			driver.findElement(By.id("NeedPoints")).sendKeys(read.readColumn("��Ҫ����", i));
			driver.findElement(By.id("Price")).clear();
			driver.findElement(By.id("Price")).sendKeys(read.readColumn("�۸�", i));
			if(read.readColumn("�Ƿ���ʾ", i).equals("��")){
				driver.findElement(By.id("IsShow")).click();
			}
			if(read.readColumn("�Ƿ�����ظ��õ�", i).equals("n")){    //n��ʾ����������ظ������ȡ
				driver.findElement(By.id("IsRepeat")).click();
			}
			if(read.readColumn("�Ƿ񹫸�", i).equals("��")){
				driver.findElement(By.id("IsNotice")).click();
			}
			driver.findElement(By.id("ShowOrder")).clear();
			driver.findElement(By.id("ShowOrder")).sendKeys(read.readColumn("��ʾ˳��", i));
			Thread.sleep(500);
			driver.findElement(By.id("button2")).click();
			Thread.sleep(1000);
			System.out.println(count+"."+read.readColumn("�������", i)+" �Ƿ������ɹ���"+driver.findElement(By.className("red")).getText().trim().equals("Success"));
			driver.findElement(By.className("link-clew")).click();
			Thread.sleep(1000);
		}
	}
}