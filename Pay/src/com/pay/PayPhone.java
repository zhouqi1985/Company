package com.pay;

import java.io.File;
import java.util.Scanner;

import org.openqa.selenium.By;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.support.ui.Select;

import com.module.PayModule;

import readExcel.readExcel;

public class PayPhone {

	@SuppressWarnings("resource")
	public static void main(String[] args) throws Exception {
		int count =0;
		FirefoxProfile pro =new FirefoxProfile(new File("C:\\Users\\lixuerui\\AppData\\Roaming\\Mozilla\\Firefox\\Profiles\\81xof17x.default"));
		FirefoxDriver driver =new FirefoxDriver(pro);
		readExcel read =new readExcel();
		read.getExcel("D:\\Data\\pay.xls", "Phone");
		driver.get("https://pay.zygames.com/");
		Thread.sleep(500);
		for(int i=1;i<read.rows;i++){
			count++;
			PayModule mod =new PayModule();
			mod.getModule(driver,read.readColumn("固话充值类型", i));
			driver.findElement(By.name("GameID")).click();
			Thread.sleep(1000);
			Select GameAreaID =new Select(driver.findElement(By.id("GameAreaID")));
			GameAreaID.selectByVisibleText(read.readColumn("区服", i));
			Thread.sleep(1000);
			Select ConfirmGameAreaID =new Select(driver.findElement(By.id("ConfirmGameAreaID")));
			ConfirmGameAreaID.selectByVisibleText(read.readColumn("确认区服", i));
			driver.findElement(By.id("LoginName")).clear();
			driver.findElement(By.id("LoginName")).sendKeys(read.readColumn("账号", i));
			driver.findElement(By.id("ConfirmLoginName")).clear();
			driver.findElement(By.id("ConfirmLoginName")).sendKeys(read.readColumn("确认账号", i));
			Thread.sleep(500);
			Select ProductID = new Select(driver.findElement(By.id("ProductID")));
			ProductID.selectByVisibleText(read.readColumn("金额", i));
			Scanner scan =new Scanner(System.in);
			System.out.print("请输入验证码：");
			String ValidCode =scan.nextLine();
			driver.findElement(By.id("ValidCode")).sendKeys(ValidCode);
			driver.findElement(By.id("checkinfo")).click();
			Thread.sleep(1500);
			driver.findElement(By.className("btn_next")).click();
			driver.switchTo().alert().accept();
			System.out.print("是否跳转成功：");
			String judge =scan.nextLine();
			if(judge.equals("y")){
				System.out.println(count+"."+read.readColumn("固话充值类型", i)+"-"+read.readColumn("金额", i)+"元 跳转成功");
			}else {
				System.out.println(count+"."+read.readColumn("固话充值类型", i)+"-"+read.readColumn("金额", i)+"元 跳转失败");
			}
			Thread.sleep(500);
		}

	}

}
