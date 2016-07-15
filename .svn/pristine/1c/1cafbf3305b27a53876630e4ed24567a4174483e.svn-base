package com.getcard;

import java.io.File;
import java.util.List;
import java.util.Scanner;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.support.ui.Select;

import readExcel.readExcel;

import com.module.EsalesModule;
import com.module.Module;



public class E_cards {

	@SuppressWarnings("resource")
	public static void main(String[] args) throws Exception {
		FirefoxProfile pro =new FirefoxProfile(new File("C:\\Users\\lixuerui\\AppData\\Roaming\\Mozilla\\Firefox\\Profiles\\81xof17x.default"));
		WebDriver driver =new FirefoxDriver(pro);
		readExcel read =new readExcel();
		read.getExcel("D:\\Data\\esales.xls", "Ecard");
		for(int i=1;i<read.rows;i++){
			if(read.readColumn("Esales账号", i).equals("")) break;
			driver.navigate().to("http://esales.zygames.com/");
			driver.findElement(By.id("UserName")).sendKeys(read.readColumn("Esales账号", i));
			driver.findElement(By.id("LoginPassword")).sendKeys("111111");
			Thread.sleep(1000);
			Scanner scan =new Scanner(System.in);
			System.out.print("请输入验证码：");
			String ValidCode=scan.nextLine();
			driver.findElement(By.id("ValidCode")).sendKeys(ValidCode);
			driver.findElement(By.xpath("//*[@id='container']/form/div/div/div/div/input")).click();   //点击登录
			Thread.sleep(1000);
			EsalesModule emod =new EsalesModule();
			emod.getModule(driver, "点卡采购", "产品采购");
			if(read.readColumn("产品类型", i).equals("返点")){
				driver.findElement(By.xpath("//*[@id='container']/div/div/div/div[2]/ul[2]/li/a")).click();  //选择返点类型点卡
			}else {
				List<WebElement> row=driver.findElements(By.xpath("//*[@id='container']/div/div/div/div[2]/ul[1]/li"));
				for(int j=1;j<row.size();j++){
					if(driver.findElement(By.xpath("//*[@id='container']/div/div/div/div[2]/ul[1]/li["+j+"]/a")).getText().equals(read.readColumn("产品类型", i))){
						driver.findElement(By.xpath("//*[@id='container']/div/div/div/div[2]/ul[1]/li["+j+"]/a")).click();
					}
				}
			}
			Thread.sleep(1000);
			Select ServiceID =new Select(driver.findElement(By.id("ServiceID")));
			ServiceID.selectByVisibleText(read.readColumn("点卡金额", i));
			driver.findElement(By.id("numb")).sendKeys(read.readColumn("点卡数量", i));
			driver.findElement(By.className("bt-samll")).click();
			Thread.sleep(500);
			driver.findElement(By.cssSelector("input.btn1[value=\"生成订单\"]")).click();
			driver.switchTo().alert().accept();
			driver.navigate().to("http://a.zygames.com/");
			Thread.sleep(1000);
			driver.findElement(By.id("LoginName")).clear();
			driver.findElement(By.id("LoginName")).sendKeys("admin");
			driver.findElement(By.id("LoginPassword")).clear();
			driver.findElement(By.id("LoginPassword")).sendKeys("123.123a");
			driver.findElement(By.className("login_button")).click();
			Thread.sleep(1000);
			Module mod =new Module();
			driver.switchTo().defaultContent();
			mod.getModule(driver, "销售系统管理V2.0", "订单审核");
			Thread.sleep(200);
			driver.findElement(By.xpath("//*[@id='data']/table/tbody/tr[2]/td[11]/input[1]")).click();  //审核通过第一条订单
			driver.switchTo().alert().accept();
			Thread.sleep(500);
			mod.repeatModule(driver, "销售系统管理V2.0", "订单收款");
			Thread.sleep(1000);
			driver.findElement(By.xpath("//*[@id='data']/table/tbody/tr[2]/td[12]/input[1]")).click();  //点击第一条收款信息
			driver.findElement(By.id("C_FinanceInfo")).sendKeys("123123");
			driver.findElement(By.id("C_PayMethod")).sendKeys("网银");
			driver.findElement(By.id("C_Amount")).sendKeys("270");
			driver.findElement(By.id("C_ReceiveDate")).sendKeys("2014-6-9");
			driver.findElement(By.id("Submit1")).click();
			Thread.sleep(3000);
			driver.navigate().to("http://esales.zygames.com/");
			Thread.sleep(1000); 
			emod.getModule(driver, "点卡采购", "收货确认");  //点击确认收货
			Thread.sleep(500);
			driver.findElement(By.xpath("//*[@id='orderListContainer']/table/tbody/tr[1]/td[7]/a[2]")).click();  //确认第一条收货信息
			Thread.sleep(1000);
			driver.findElement(By.className("btn1")).click();
			Thread.sleep(500);
			driver.navigate().to("http://a.zygames.com/");
			Thread.sleep(500); 
			mod.repeatModule(driver, "销售系统管理V2.0", "订单激活");
			Thread.sleep(1000);
			driver.findElement(By.xpath("//*[@id='data']/table/tbody/tr[2]/td[12]/input[1]")).click(); //激活第一条数据
			driver.switchTo().alert().accept();
			Thread.sleep(1000);
			driver.navigate().to("http://esales.zygames.com/");
			Thread.sleep(1000);
			emod.getModule(driver, "点卡采购", "电子卡密码下载");  //点击电子卡密码下载
			driver.findElement(By.xpath("//*[@id='orderListContainer']/table/tbody/tr[1]/td[7]/a[2]")).click();  //下载第一条电子卡密码
			Thread.sleep(300);
			driver.findElement(By.id("traderPassword")).sendKeys("123.123a");
			driver.findElement(By.cssSelector("input[value=\"下载\"]")).click();
		}
	}
}
