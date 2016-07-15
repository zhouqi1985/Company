package com.passport;

import java.io.File;
import java.util.Scanner;

import org.openqa.selenium.By;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;

import com.judge.Judge;

import readExcel.readExcel;

public class EmailRegister {

	@SuppressWarnings("resource")
	public static void main(String[] args) throws Exception {
		int count =0;
		FirefoxProfile pro =new FirefoxProfile(new File("C:\\Users\\lixuerui\\AppData\\Roaming\\Mozilla\\Firefox\\Profiles\\81xof17x.default"));
		FirefoxDriver driver =new FirefoxDriver(pro);
		readExcel read =new readExcel();
		read.getExcel("D:\\Data\\passport.xls", "邮箱");
		driver.get("https://passport.zygames.com");
		Thread.sleep(1000);
		driver.findElement(By.className("resBtn")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//*[@id='formRegister']/div/div[2]/div[1]/dl/dd[3]/a")).click();  //1为用户名注册，2为手机注册，3为邮箱注册
		Thread.sleep(200);
		for(int i=1;i<read.rows;i++){
			driver.findElement(By.id("LoginName")).clear();
			driver.findElement(By.id("LoginName")).sendKeys(read.readColumn("邮箱", i));
			driver.findElement(By.id("Nickname")).clear();
			driver.findElement(By.id("Nickname")).sendKeys(read.readColumn("昵称", i));
			driver.findElement(By.id("LoginPassWord")).clear();
			driver.findElement(By.id("LoginPassWord")).sendKeys(read.readColumn("登录密码", i));
			driver.findElement(By.id("ConfirmPassword")).clear();
			driver.findElement(By.id("ConfirmPassword")).sendKeys(read.readColumn("确认密码", i));
			if(read.readColumn("用户条款", i).equals("否")){
				driver.findElement(By.id("ConfirmLicence")).click();
			}
			driver.findElement(By.id("userName")).clear();
			driver.findElement(By.id("userName")).sendKeys(read.readColumn("姓名", i));
			driver.findElement(By.id("IDCardNO")).clear();
			driver.findElement(By.id("IDCardNO")).sendKeys(read.readColumn("身份证", i));
			driver.findElement(By.id("validCode")).clear();
			if(read.readColumn("是否输入错误验证码", i).equals("是")){
				driver.findElement(By.id("validCode")).sendKeys("123a");
			}else{
				Scanner scan =new Scanner(System.in);
				System.out.print("请输入验证：");
				String validCode=scan.nextLine();
				driver.findElement(By.id("validCode")).sendKeys(validCode);
			}
			driver.findElement(By.className("resBtn")).click();
			Thread.sleep(500);
			Judge judge=new Judge();
			By locator =By.className("field-validation-error");
			boolean error =judge.isElementExsit(driver, locator);
			if(error){
				driver.findElement(By.name("提交")).click();
				Thread.sleep(500);
				driver.findElement(By.id("validCode")).click();
				Thread.sleep(1500);
				System.out.println(count+"."+read.readColumn("错误提示", i)+" 是否正确："+driver.findElement(By.xpath("//*[@id='formRegister']/div/div[2]/div[3]/dl["+read.readColumn("code代码", i)+"]/dd/span[1]")).getText().trim().contains(read.readColumn("错误提示", i)));
				//code代码：1.用户名 2.昵称 3.登录密码 4.确认密码 6.用户条款 7.姓名 8.身份证 9.验证码
				Thread.sleep(500);
			}else {
				System.out.println(count+"."+"注册是否成功："+driver.findElement(By.className("sysTxt")).getText().trim().contains("注册成功"));
				Thread.sleep(500);
				driver.findElement(By.cssSelector("input[title=\"注销\"]")).click();
				Thread.sleep(500);
				if(i<read.rows-1){
					driver.findElement(By.className("resBtn")).click();
					Thread.sleep(1000);
					driver.findElement(By.xpath("//*[@id='formRegister']/div/div[2]/div[1]/dl/dd[3]/a")).click();  //1为用户名注册，2为手机注册，3为邮箱注册
					Thread.sleep(200);
				}
			}
		}

	}

}
