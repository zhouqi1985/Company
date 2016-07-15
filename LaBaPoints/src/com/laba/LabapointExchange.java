package com.laba;

import java.io.File;
import java.util.Scanner;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.support.ui.Select;

import readExcel.readExcel;

public class LabapointExchange {

	@SuppressWarnings("resource")
	public static void main(String[] args) throws Exception {
		int count=0;
		int total;
		FirefoxProfile pro =new FirefoxProfile(new File("C:\\Users\\lixuerui\\AppData\\Roaming\\Mozilla\\Firefox\\Profiles\\81xof17x.default"));
		WebDriver driver =new FirefoxDriver(pro);
		readExcel read =new readExcel();
		read.getExcel("D:\\Data\\labapoints.xls", "labaExchange");
		driver.navigate().to("http://event.zygames.com/qqsm/201412/LaBa/");
		Thread.sleep(1500);
		driver.findElement(By.cssSelector("a[title=\"积分查询\"]")).click();
		Thread.sleep(1500);
		driver.switchTo().frame(0);
		Thread.sleep(200);
		driver.findElement(By.id("LoginName")).clear();
		driver.findElement(By.id("LoginName")).sendKeys("test0099");
		driver.findElement(By.id("LoginPassWord")).sendKeys("111111");
		Thread.sleep(1000);
		Scanner scan =new Scanner(System.in);
		System.out.print("请输入验证码：");
		String ValidCode=scan.nextLine();
		driver.findElement(By.id("validCode")).sendKeys(ValidCode);
		driver.findElement(By.name("提交")).click();
		Thread.sleep(3000);
		Select GameAreaList =new Select(driver.findElement(By.id("GameAreaList")));
		GameAreaList.selectByVisibleText(read.readColumn("区服", 1));
		Thread.sleep(3000);
		driver.findElement(By.id("btnSubmit")).click();
		driver.switchTo().defaultContent();
		Thread.sleep(1000);
		for(int i=1;i<read.rows;i++){
			count++;
			Thread.sleep(1000);
			if(read.readColumn("礼包名称", i).equals("")) break;
			String currentPoint =driver.findElement(By.cssSelector("span#points")).getText();
			if(i==1){
			    total =Integer.valueOf(currentPoint.substring(0, currentPoint.length()-2));
			}else{
				total =Integer.valueOf(currentPoint.substring(0, currentPoint.length()));
			}
			
			int n=Integer.valueOf(read.readColumn("所需分数", i));
			if(total<n){
				driver.findElement(By.cssSelector("li[pid=\""+read.readColumn("礼包ID", i)+"\"]")).click();
				Thread.sleep(500);
                driver.findElement(By.xpath("//*[@id='popBox']/div[2]/a")).click();  //点击我要兑换
                Thread.sleep(500);
                System.out.println(count+"."+read.readColumn("礼包名称", i)+" 礼包是否兑换失败，显示积分不足："+driver.findElement(By.xpath("//*[@id='popBox']/table/tbody/tr/td")).getText().trim().equals("积分不足"));
                Thread.sleep(500);
                driver.findElement(By.xpath("//*[@id='popBox']/div[1]/a")).click();  //点击返回
                System.out.print("\n");
                Thread.sleep(500);
			}else{
				driver.findElement(By.cssSelector("li[pid=\""+read.readColumn("礼包ID", i)+"\"]")).click();
				Thread.sleep(500);
                driver.findElement(By.xpath("//*[@id='popBox']/div[2]/a")).click();  //点击我要兑换
                Thread.sleep(1000);
                boolean cg =driver.findElement(By.xpath("//*[@id='popBox']/table/tbody/tr/td")).getText().trim().equals("兑换成功");
                Thread.sleep(500);
                if(cg==true){
                	System.out.println(count+"."+read.readColumn("礼包名称", i)+" 礼包是否兑换成功："+driver.findElement(By.xpath("//*[@id='popBox']/table/tbody/tr/td")).getText().trim().equals("兑换成功"));
                	driver.findElement(By.xpath("//*[@id='popBox']/div[1]/a")).click();  //点击返回
                	Thread.sleep(1000);
                	int j=total-n;
                	String currentPoint1 =driver.findElement(By.cssSelector("span#points")).getText();
        			int total1 =Integer.valueOf(currentPoint1);
        			System.out.println("本次礼包所需积分为："+read.readColumn("所需分数", i));
        			System.out.println("当前剩余积分："+j);
        			System.out.println("扣除积分是否正确:"+(j==total1));
        			System.out.print("\n");
        			Thread.sleep(500);
                }else {
                	System.out.println(count+"."+read.readColumn("礼包名称", i)+" 礼包是否不能重复兑换："+driver.findElement(By.xpath("//*[@id='popBox']/table/tbody/tr/td")).getText().trim().contains("不能重复兑换"));
                	driver.findElement(By.xpath("//*[@id='popBox']/div[1]/a")).click();  //点击返回
                	Thread.sleep(500);
                	int j=total;
                	String currentPoint1 =driver.findElement(By.cssSelector("span#points")).getText();
                	int total1 =Integer.valueOf(currentPoint1);
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
