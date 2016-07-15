package com.pay;

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

import com.module.Module;

public class PayService {
	private String payment;


	public static void main(String[] args) throws Exception {
		FirefoxProfile pro =new FirefoxProfile(new File("C:\\Users\\lixuerui\\AppData\\Roaming\\Mozilla\\Firefox\\Profiles\\81xof17x.default"));
		FirefoxDriver driver =new FirefoxDriver(pro);
		readExcel read =new readExcel();
		read.getExcel("D:\\Data\\pay.xls", "PaymentList");
		for(int i=1;i<read.rows;i++){
			String state =read.readColumn("订单状态", i);
			String result =read.readColumn("结果信息", i);
			String reState =read.readColumn("结果状态", i);
			if(state.equals("")) break;
			PayService payment =new PayService();
			payment.PaymentList(driver, state, result, reState);
		}
		Thread.sleep(1000);
		readExcel read1 =new readExcel();
		read1.getExcel("D:\\Data\\pay.xls", "QueryCard");
		for(int j=1;j<read1.rows;j++){
			String cardPassword =read1.readColumn("卡密", j);
			String Cstate =read1.readColumn("卡状态", j);
			String Ctype =read1.readColumn("卡密类型", j);
			if(cardPassword.equals("")) break;
			PayService querycard =new PayService();
			querycard.QueryCard(driver, cardPassword, Cstate, Ctype);
		}

	}
	
	@SuppressWarnings("resource")
	public void PaymentList(WebDriver driver,String state,String result,String reState) throws Exception{
		driver.get("https://passport.zygames.com");
		driver.findElement(By.id("LoginName")).clear();
		driver.findElement(By.id("LoginName")).sendKeys("smsp001");
		driver.findElement(By.id("LoginPassWord")).clear();
		driver.findElement(By.id("LoginPassWord")).sendKeys("111111");
		Scanner scan =new Scanner(System.in);
		System.out.print("请输入验证码：");
		String validCode =scan.nextLine();
		driver.findElement(By.id("validCode")).sendKeys(validCode);
		driver.findElement(By.className("enterBtn")).click();
		Thread.sleep(500);
		driver.findElement(By.xpath("//*[@id='container']/div[1]/div[2]/dl/dt[5]/a/span")).click();    //点击充值
		Thread.sleep(1000);
		driver.findElement(By.xpath("//*[@id='con']/div[2]/div[2]/div[2]/a")).click();  //点击充值记录
		Thread.sleep(1000);
		List<WebElement> row1 =driver.findElements(By.xpath("//*[@id='dataList']/table/tbody/tr"));
		for(int i=1;i<=row1.size();i++){
			if(driver.findElement(By.xpath("//*[@id='dataList']/table/tbody/tr["+i+"]/td[4]/span")).getText().trim().equals(state)){
				Thread.sleep(500);
				driver.findElement(By.xpath("//*[@id='dataList']/table/tbody/tr["+i+"]/td[6]/a")).click();
				Thread.sleep(500);
				driver.findElement(By.xpath("//*[@id='lr_hist']/div[2]/table/tbody/tr[7]/td/a[1]")).click();  //点击提交查询
				Thread.sleep(1000);
				String error =driver.switchTo().alert().getText().trim();
				if (error.equals("不允许重复提交")) {
					driver.switchTo().alert().accept();
					Thread.sleep(1000);
					driver.findElement(By.xpath("//*[@id='lr_hist']/div[2]/span/a")).click();  //点击返回
					Thread.sleep(1000);
				}else{
					driver.switchTo().alert().accept();
					Thread.sleep(1000);
					this.payment =driver.findElement(By.xpath("//*[@id='lr_hist']/div[2]/table/tbody/tr[1]/td")).getText().trim();
					Thread.sleep(500);
					break;
				}		
			}
		}
		driver.get("http://a.zygames.com");
		Thread.sleep(1000);
		driver.findElement(By.id("LoginName")).clear();
		driver.findElement(By.id("LoginName")).sendKeys("admin");
		driver.findElement(By.id("LoginPassword")).clear();
		driver.findElement(By.id("LoginPassword")).sendKeys("123.123a");
		driver.findElement(By.className("login_button")).click();
		Thread.sleep(1000);
		Module mod =new Module();
		mod.getModule(driver, "客服：系统管理", "订单自助查询");
		List<WebElement> row2 =driver.findElements(By.xpath("//*[@id='data']/table/tbody/tr"));
		for(int j=2;j<=row2.size();j++){
			if(driver.findElement(By.xpath("//*[@id='data']/table/tbody/tr["+j+"]/td[1]")).getText().trim().equals(payment)){
				driver.findElement(By.xpath("//*[@id='data']/table/tbody/tr["+j+"]/td[13]/input")).click();
				Thread.sleep(1000);
				break;
			}
		}
		Select ReviewStatus =new Select(driver.findElement(By.id("ReviewStatus")));
		ReviewStatus.selectByVisibleText(reState);
		driver.findElement(By.id("Result")).sendKeys(result);
		driver.findElement(By.id("button2")).click();
		driver.findElement(By.className("link-clew")).click();
		Thread.sleep(1000);
		driver.get("https://pay.zygames.com");
		Thread.sleep(1000);
		driver.findElement(By.xpath("//*[@id='con']/div[2]/div[2]/div[2]/a")).click();  //再次点击充值记录
		List<WebElement> row3 =driver.findElements(By.xpath("//*[@id='dataList']/table/tbody/tr"));
		for(int n=1;n<=row3.size();n++){
			if(driver.findElement(By.xpath("//*[@id='dataList']/table/tbody/tr["+n+"]/td[1]")).getText().trim().equals(payment)){
				driver.findElement(By.xpath("//*[@id='dataList']/table/tbody/tr["+n+"]/td[6]/a")).click();
				Thread.sleep(500);
				break;
			}
		}
		driver.findElement(By.xpath("//*[@id='lr_hist']/div[2]/table/tbody/tr[7]/td/a[2]")).click(); //点击反馈信息
		Thread.sleep(1000);
		System.out.println("是否操作成功："+driver.findElement(By.id("feedbackContent")).getText().trim().equals(result));
		Thread.sleep(500);
	}
	
	@SuppressWarnings("resource")
	public void QueryCard(WebDriver driver,String cardPassword,String state,String type) throws Exception{
		driver.get("https://pay.zygames.com");
		Thread.sleep(500);
		driver.findElement(By.xpath("//*[@id='con']/div[2]/div[2]/div[3]/a")).click(); //点击卡状态查询
		Thread.sleep(500);
		driver.findElement(By.id("cardPassword")).sendKeys(cardPassword);
		Scanner scan =new Scanner(System.in);
		System.out.print("请输入验证码：");
		String ValidCode =scan.nextLine();
		driver.findElement(By.id("ValidCode")).sendKeys(ValidCode);
		driver.findElement(By.id("checkinfo")).click();
		Thread.sleep(1000);
		System.out.println("卡密类型:"+type+" 是否正确："+driver.findElement(By.xpath("//*[@id='lr_hist']/div[3]/table/tbody/tr[1]/td")).getText().trim().equals(type));
		System.out.println("卡状态："+state+" 是否正确"+driver.findElement(By.xpath("//*[@id='lr_hist']/div[3]/table/tbody/tr[2]/td")).getText().trim().equals(state));
	}

}
