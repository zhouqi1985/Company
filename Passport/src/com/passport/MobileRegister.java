package com.passport;

import java.io.File;
import java.util.Scanner;

import org.openqa.selenium.By;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;

import com.judge.Judge;

import readExcel.readExcel;

public class MobileRegister {

	@SuppressWarnings("resource")
	public static void main(String[] args) throws Exception {
		int count =0;
		FirefoxProfile pro =new FirefoxProfile(new File("C:\\Users\\lixuerui\\AppData\\Roaming\\Mozilla\\Firefox\\Profiles\\81xof17x.default"));
		FirefoxDriver driver =new FirefoxDriver(pro);
		readExcel read =new readExcel();
		read.getExcel("D:\\Data\\passport.xls", "�ֻ�");
		driver.get("https://passport.zygames.com");
		Thread.sleep(1000);
		driver.findElement(By.className("resBtn")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//*[@id='formRegister']/div/div[2]/div[1]/dl/dd[2]/a")).click();  //1Ϊ�û���ע�ᣬ2Ϊ�ֻ�ע�ᣬ3Ϊ����ע��
		Thread.sleep(200);
		for(int i=1;i<read.rows;i++){
			driver.findElement(By.id("PhoneNumber")).clear();
			driver.findElement(By.id("PhoneNumber")).sendKeys(read.readColumn("�ֻ���", i));
			Scanner scan =new Scanner(System.in);
			System.out.print("��������֤�룺");
			String validCode_m =scan.nextLine();
			driver.findElement(By.id("validCode_m")).clear();
			driver.findElement(By.id("validCode_m")).sendKeys(validCode_m); 
			Thread.sleep(200);
			System.out.print("�������ֻ���֤�룺");
			String PhoneValidCode=scan.nextLine();
			driver.findElement(By.id("PhoneValidCode")).clear();
			driver.findElement(By.id("PhoneValidCode")).sendKeys(PhoneValidCode); 
			driver.findElement(By.id("LoginPassWord")).clear();
			driver.findElement(By.id("LoginPassWord")).sendKeys(read.readColumn("��¼����", i));
			driver.findElement(By.id("ConfirmPassword")).clear();
			driver.findElement(By.id("ConfirmPassword")).sendKeys(read.readColumn("ȷ������", i));
			if(read.readColumn("�û�����", i).equals("��")){
				driver.findElement(By.id("ConfirmLicence")).click();
			}
			driver.findElement(By.id("userName")).clear();
			driver.findElement(By.id("userName")).sendKeys(read.readColumn("����", i));
			driver.findElement(By.id("IDCardNO")).clear();
			driver.findElement(By.id("IDCardNO")).sendKeys(read.readColumn("����֤", i));
			driver.findElement(By.id("validCode")).clear();
			if(read.readColumn("�Ƿ����������֤��", i).equals("��")){
				driver.findElement(By.id("validCode")).sendKeys("123a");
			}else {
				System.out.print("��������֤��");
				String validCode=scan.nextLine();
				driver.findElement(By.id("validCode")).sendKeys(validCode);
			}
			driver.findElement(By.className("resBtn")).click();
			Thread.sleep(1500);
			Judge judge=new Judge();
			By locator =By.className("field-validation-error");
			boolean error =judge.isElementExsit(driver, locator);
			if(error){
				driver.findElement(By.name("�ύ")).click();
				Thread.sleep(500);
				driver.findElement(By.id("validCode")).click();
				Thread.sleep(1500);
				System.out.println(count+"."+read.readColumn("������ʾ", i)+" �Ƿ���ȷ��"+driver.findElement(By.xpath("//*[@id='formRegister']/div/div[2]/div[3]/dl["+read.readColumn("code����", i)+"]/dd/span[1]")).getText().trim().contains(read.readColumn("������ʾ", i)));
				//code���룺1.�ֻ�  3.�ֻ���֤�� 4.�ǳ� 5.��¼���� 6.ȷ������ 8.�û����� 9.���� 10.����֤ 11.��֤��
				Thread.sleep(500);
			}else {
				System.out.println(count+"."+"ע���Ƿ�ɹ���"+driver.findElement(By.className("sysTxt")).getText().trim().contains("ע��ɹ�"));
				Thread.sleep(500);
				driver.findElement(By.cssSelector("input[title=\"ע��\"]")).click();
				Thread.sleep(500);
				if(i<read.rows-1){
					driver.findElement(By.className("resBtn")).click();
					Thread.sleep(1000);
					driver.findElement(By.xpath("//*[@id='formRegister']/div/div[2]/div[1]/dl/dd[2]/a")).click();  //1Ϊ�û���ע�ᣬ2Ϊ�ֻ�ע�ᣬ3Ϊ����ע��
					Thread.sleep(200);
				}
			}
		}
	}

}