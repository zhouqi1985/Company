package com.giftpoint;

import java.io.File;
import java.util.Scanner;

import org.openqa.selenium.By;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.support.ui.Select;

import readExcel.readExcel;

public class giftpointExchange {

	@SuppressWarnings("resource")
	public static void main(String[] args) throws Exception {
		int count =0;
		FirefoxProfile pro =new FirefoxProfile(new File("C:\\Users\\lixuerui\\AppData\\Roaming\\Mozilla\\Firefox\\Profiles\\81xof17x.default"));
		FirefoxDriver driver =new FirefoxDriver(pro);
		driver.get("http://event.zygames.com/qqsm/201402/giftpoints");
		Thread.sleep(1000);
		driver.findElement(By.id("Map2")).click();
		Thread.sleep(1500);
		driver.switchTo().frame(0);
		Thread.sleep(200);
		driver.findElement(By.id("LoginName")).clear();
		driver.findElement(By.id("LoginName")).sendKeys("smsp000");
		driver.findElement(By.id("LoginPassWord")).sendKeys("111111");
		Thread.sleep(1000);
		Scanner scan =new Scanner(System.in);
		System.out.print("请输入验证码：");
		String validCode=scan.nextLine();
		driver.findElement(By.id("validCode")).sendKeys(validCode);
		driver.findElement(By.name("提交")).click();
		driver.switchTo().defaultContent();
		Thread.sleep(1000);
		readExcel read =new readExcel();
		read.getExcel("D:\\Data\\giftpoint.xls", "giftpoints");
		for(int i=1;i<read.rows;i++){
			count++;
			if(read.readColumn("区服code", i).equals("")) break;    //区服code:3、4和5，分别表示为电信一区、网通一区和电信二区
			String currentPoint=driver.findElement(By.xpath("//*[@id='container']/div[2]/div["+read.readColumn("区服code", i)+"]/span")).getText().trim();
			int total =Integer.valueOf(currentPoint);
			int n=Integer.valueOf(read.readColumn("所需分数", i));
			if(n>total){
				driver.findElement(By.xpath("//*[@id='container']/div[3]/table/tbody/tr["+Integer.valueOf(read.readColumn("礼包序号", i))+"]/td/div/a/img")).click();
				Thread.sleep(1000);
				Select gameAreaID = new Select(driver.findElement(By.id("GameAreaID")));
                gameAreaID.selectByVisibleText(read.readColumn("区服", i));
                Thread.sleep(3000);
                driver.findElement(By.cssSelector("area[title=\"确认兑换\"]")).click();
                System.out.println(count+"."+read.readColumn("礼包名称", i)+" 兑换失败，是否提示积分不足："+driver.findElement(By.className("msg")).getText().trim().equals("积分不足"));
				driver.findElement(By.cssSelector("area[title=\"继续兑换\"]")).click();
				 System.out.print("\n");
				Thread.sleep(1000);
			}else {
				driver.findElement(By.xpath("//*[@id='container']/div[3]/table/tbody/tr["+Integer.valueOf(read.readColumn("礼包序号", i))+"]/td/div/a/img")).click();
				Thread.sleep(1000);
				Select gameAreaID = new Select(driver.findElement(By.id("GameAreaID")));
                gameAreaID.selectByVisibleText(read.readColumn("区服", i));
                Thread.sleep(3000);
                driver.findElement(By.cssSelector("area[title=\"确认兑换\"]")).click();
                Thread.sleep(1000);
                Boolean cg=driver.findElement(By.className("msg")).getText().trim().contains("恭喜您获得");
                if(cg==true){
                	System.out.println(count+"."+read.readColumn("礼包名称", i)+"是否兑换成功："+cg);
                	driver.findElement(By.cssSelector("area[title=\"继续兑换\"]")).click();
                	int j=total-n;
                	String currentPoint1 =driver.findElement(By.xpath("//*[@id='container']/div[2]/div["+read.readColumn("区服code", i)+"]/span")).getText().trim();
        			int total1 =Integer.valueOf(currentPoint1);
        			System.out.println("本次礼包所需积分为："+read.readColumn("所需分数", i));
        			System.out.println("当前剩余积分："+j);
        			System.out.println("扣除积分是否正确:"+(j==total1));
        			System.out.print("\n");
        			Thread.sleep(1000);
                }else {
					System.out.println(count+"."+read.readColumn("礼包名称", i)+"是否不能重复兑换："+driver.findElement(By.className("msg")).getText().trim().contains("不能重复兑换"));
					driver.findElement(By.cssSelector("area[title=\"继续兑换\"]")).click();
					int j=total;
                	String currentPoint1 =driver.findElement(By.xpath("//*[@id='container']/div[2]/div["+read.readColumn("区服code", i)+"]/span")).getText().trim();
        			int total1 =Integer.valueOf(currentPoint1);
                	System.out.println("本次礼包所需积分为："+read.readColumn("所需分数", i));
        			System.out.println("当前剩余积分："+j);
        			System.out.println("积分是否正确，并未扣除积分:"+(j==total1));
        			System.out.print("\n");
        			Thread.sleep(1000);
				}
			}
		}
	}

}
