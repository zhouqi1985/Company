package com.getcard;

import java.io.File;
import java.util.Scanner;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.support.ui.Select;

import com.module.EsalesModule;

import readExcel.readExcel;

public class terminalRecharge {

	@SuppressWarnings({ "resource"})
	public static void main(String[] args) throws Exception {
		FirefoxProfile pro =new FirefoxProfile(new File("C:\\Users\\lixuerui\\AppData\\Roaming\\Mozilla\\Firefox\\Profiles\\81xof17x.default"));
		WebDriver driver =new FirefoxDriver(pro);
		driver.navigate().to("http://esales.zygames.com/");
		driver.findElement(By.id("UserName")).sendKeys("smsp42611");
		driver.findElement(By.id("LoginPassword")).sendKeys("111111");
		Thread.sleep(2000);
		Scanner scan =new Scanner(System.in);
		System.out.print("请输入验证码：");
		String ValidCode=scan.nextLine();
		driver.findElement(By.id("ValidCode")).sendKeys(ValidCode);
		driver.findElement(By.xpath("//*[@id='container']/form/div/div/div/div/input")).click();   //点击登录
		readExcel read =new readExcel();
		read.getExcel("D:\\Data\\esales.xls", "pay");
		Thread.sleep(1000);
		EsalesModule emod =new EsalesModule();
		emod.getModule(driver,"点卡交易", "终端充值");
		for(int i=1;i<read.rows;i++){
			Thread.sleep(1000);
			Select GameID =new Select(driver.findElement(By.id("GameID")));
			GameID.selectByVisibleText(read.readColumn("游戏", i));
			Thread.sleep(1000);
			Select GameAreaID=new Select(driver.findElement(By.id("GameAreaID")));
			GameAreaID.selectByVisibleText(read.readColumn("区服", i));
			Thread.sleep(1000);
			Select ProductID =new Select(driver.findElement(By.id("ProductID")));
			ProductID.selectByVisibleText(read.readColumn("充值点券", i));
			driver.findElement(By.id("LoginName")).sendKeys(read.readColumn("账号", i));
			driver.findElement(By.id("UserCode")).sendKeys(read.readColumn("账号", i));
			driver.findElement(By.className("btn1")).click();
			Thread.sleep(1000);
			driver.findElement(By.id("TradePassword")).sendKeys(read.readColumn("交易密码", i));
			driver.findElement(By.id("ConfrimTradePassword")).sendKeys(read.readColumn("交易密码", i));
			Thread.sleep(1000);
			Scanner scan1 =new Scanner(System.in);
			System.out.print("请输入验证码：");
			String ValidCode1 =scan1.nextLine();
			driver.findElement(By.id("ValidCode")).sendKeys(ValidCode1);
			driver.findElement(By.cssSelector("input[value=\"确认\"]")).click();
			Thread.sleep(1000);
			System.out.println("是否充值成功："+driver.findElement(By.xpath("//*[@id='container']/div/div/div/div[2]/div/span")).getText().contains("成功"));
			driver.findElement(By.cssSelector("input[value=\"返回\"]")).click();
			Thread.sleep(1000);
		}
	}

}
