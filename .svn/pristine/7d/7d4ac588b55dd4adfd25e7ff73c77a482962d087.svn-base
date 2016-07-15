package com.jifen;

import java.io.File;
import java.util.Scanner;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.support.ui.Select;

import readExcel.readExcel;

public class pointsExchange {

	@SuppressWarnings("resource")
	public static void main(String[] args) throws Exception {
		int count=0;
		FirefoxProfile pro =new FirefoxProfile(new File("C:\\Users\\lixuerui\\AppData\\Roaming\\Mozilla\\Firefox\\Profiles\\81xof17x.default"));
		WebDriver driver =new FirefoxDriver(pro);
		readExcel read =new readExcel();
		read.getExcel("D:\\Data\\jifen.xls", "exchange");
		driver.navigate().to("http://event.zygames.com/jifen/");
		Thread.sleep(1500);
		driver.findElement(By.cssSelector("area[title=\"兑换礼包\"]")).click();
		Thread.sleep(1500);
		driver.switchTo().frame(0);
		;Thread.sleep(200);
		driver.findElement(By.id("LoginName")).clear();
		driver.findElement(By.id("LoginName")).sendKeys("quanqiushiming7@qq.com");
		driver.findElement(By.id("LoginPassWord")).sendKeys("qqsmATM");
		Thread.sleep(1000);
		Scanner scan =new Scanner(System.in);
		System.out.print("请输入验证码：");
		String validCode=scan.nextLine();
		driver.findElement(By.id("validCode")).sendKeys(validCode);
		driver.findElement(By.name("提交")).click();
		driver.switchTo().defaultContent();
		Thread.sleep(1000);
		for(int i=1;i<read.rows;i++){
			count++;
			if(read.readColumn("区服code", i).equals("")) break;
			String currentPoint =driver.findElement(By.xpath("//*[@id='container']/div/span["+read.readColumn("区服code", i)+"]")).getText();  //区服code：1、2和3，分别为电信一区、电信二区和网通一区
			int total =Integer.valueOf(currentPoint.substring(0, currentPoint.length()-1));
			int n=Integer.valueOf(read.readColumn("所需分数", i));
			if(n>total){
				driver.findElement(By.cssSelector("area[title=\"兑换"+read.readColumn("礼包序号", i)+"\"]")).click();
				Thread.sleep(1000);
				Select gameAreaID = new Select(driver.findElement(By.id("GameAreaID")));
                gameAreaID.selectByVisibleText(read.readColumn("区服", i));
                Thread.sleep(3000);
                driver.findElement(By.cssSelector("area[title=\"确认兑换\"]")).click();
                Thread.sleep(1000);
                System.out.println(count+"."+read.readColumn("礼包名称", i)+" 礼包是否兑换失败，显示积分不足："+driver.findElement(By.xpath("//*[@id='container']/div/span")).getText().trim().equals("积分不足"));
                Thread.sleep(1000);
                driver.findElement(By.cssSelector("area[title=\"继续兑换\"]")).click();
                System.out.print("\n");
                Thread.sleep(1000);
			}else{
				driver.findElement(By.cssSelector("area[title=\"兑换"+read.readColumn("礼包序号", i)+"\"]")).click();
				Thread.sleep(1000);
				Select gameAreaID = new Select(driver.findElement(By.id("GameAreaID")));
                gameAreaID.selectByVisibleText(read.readColumn("区服", i));
                Thread.sleep(3000);
                driver.findElement(By.cssSelector("area[title=\"确认兑换\"]")).click();
                System.out.print("\n");
                Thread.sleep(1000);
                boolean cg =driver.findElement(By.xpath("//*[@id='container']/div/span")).getText().trim().contains("兑换成功");
                if(cg==true){
                	System.out.println(count+"."+read.readColumn("礼包名称", i)+"是否兑换成功："+driver.findElement(By.xpath("//*[@id='container']/div/span")).getText().trim().contains("兑换成功"));
                	driver.findElement(By.cssSelector("area[title=\"继续兑换\"]")).click();
                	Thread.sleep(500);
                	int j=total-n;
                	String currentPoint1 =driver.findElement(By.xpath("//*[@id='container']/div/span["+read.readColumn("区服code", i)+"]")).getText();
        			int total1 =Integer.valueOf(currentPoint1.substring(0, currentPoint1.length()-1));
        			System.out.println("本次礼包所需积分为："+read.readColumn("所需分数", i));
        			System.out.println("当前剩余积分："+j);
        			System.out.println("扣除积分是否正确:"+(j==total1));
        			System.out.print("\n");
        			Thread.sleep(500);
                }else {
                	System.out.println(count+"."+read.readColumn("礼包名称", i)+"是否不能重复兑换："+driver.findElement(By.xpath("//*[@id='container']/div/span")).getText().trim().contains("不能重复兑换"));
                	driver.findElement(By.cssSelector("area[title=\"继续兑换\"]")).click();
                	Thread.sleep(500);
                	int j=total;
                	String currentPoint1 =driver.findElement(By.xpath("//*[@id='container']/div/span["+read.readColumn("区服code", i)+"]")).getText();
        			int total1 =Integer.valueOf(currentPoint1.substring(0, currentPoint1.length()-1));
                	System.out.println("本次礼包所需积分为："+read.readColumn("所需分数", i));
        			System.out.println("当前剩余积分："+j);
        			System.out.println("积分是否正确，并未扣除积分:"+(j==total1));
        			System.out.print("\n");
        			Thread.sleep(500);
				}	
			}
		}
	}
}
