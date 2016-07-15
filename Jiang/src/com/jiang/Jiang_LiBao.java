package com.jiang;

import java.io.File;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;

import readExcel.readExcel;

import com.module.Module;

public class Jiang_LiBao {

	public static void main(String[] args) throws Exception {
		int count=0;
		FirefoxProfile pro =new FirefoxProfile(new File("C:\\Users\\lixuerui\\AppData\\Roaming\\Mozilla\\Firefox\\Profiles\\81xof17x.default"));
		WebDriver driver =new FirefoxDriver(pro);
		driver.get("http://a.zygames.com/");
		Thread.sleep(1000);
		driver.findElement(By.id("LoginName")).clear();
		driver.findElement(By.id("LoginName")).sendKeys("huhongbin");
		driver.findElement(By.id("LoginPassword")).clear();
		driver.findElement(By.id("LoginPassword")).sendKeys("123.123.123a");
		driver.findElement(By.className("login_button")).click();
		Thread.sleep(1000);
		Module mod =new Module();
		mod.getModule(driver, "�����(DEV)","(Q)ע���н�V2");
		Thread.sleep(1000);
		driver.findElement(By.cssSelector("input[value=\"�һ���Ʒ�б�\"]")).click();
		Thread.sleep(500);
		readExcel read =new readExcel();
		read.getExcel("D:\\Data\\Jiang.xls", "libao");    //�����Ϣ
		for(int i=1;i<read.rows;i++){
			count++;
			if(read.readColumn("��Ʒ����", i).equals("")) break;
			driver.findElement(By.cssSelector("input[value=\"����\"]")).click();
			Thread.sleep(500);
			driver.findElement(By.id("ProductName")).sendKeys(read.readColumn("��Ʒ����", i));
			driver.findElement(By.id("ProductDesc")).sendKeys(read.readColumn("��Ʒ����", i));
			if(read.readColumn("�Ƿ���coupon", i).equals("��")){
				driver.findElement(By.id("IsCouponPacket")).click();
			}
			driver.findElement(By.id("PacketID")).clear();
			driver.findElement(By.id("PacketID")).sendKeys(read.readColumn("���ID", i));
			if(read.readColumn("�Ƿ���ʾ", i).equals("��")){
				driver.findElement(By.id("IsShow")).click();
			}
			if(read.readColumn("�Ƿ���Ҫ��д��ϵ��Ϣ", i).equals("��")){
				driver.findElement(By.id("IsContact")).click();
			}
			if(read.readColumn("�Ƿ�֪ͨ��Ϸ", i).equals("��")){
				driver.findElement(By.id("IsNotice")).click();
			}
			if(read.readColumn("�Ƿ������ظ�", i).equals("��")){
				driver.findElement(By.id("IsRepeat")).click();
			}
			driver.findElement(By.id("Img")).sendKeys(read.readColumn("��Ʒ����", i));
			driver.findElement(By.id("btnSubmit")).click();
			Thread.sleep(500);
			System.out.println(count+"."+read.readColumn("��Ʒ����", i)+"�Ƿ������ɹ���"+driver.findElement(By.className("red")).getText().trim().contains("Success"));
			driver.findElement(By.className("link-clew")).click();
			Thread.sleep(1000);
		}

	}

}
