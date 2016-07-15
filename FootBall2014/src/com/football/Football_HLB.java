package com.football;

import java.io.File;
import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;

import jxl.Cell;
import jxl.JXLException;
import jxl.Sheet;
import jxl.Workbook;


public class Football_HLB {

	public static void main(String[] args) throws JXLException, IOException, Exception {
		int count =0;
		Workbook wb =Workbook.getWorkbook(new File("D:\\Data\\football.xls"));
		Sheet st =wb.getSheet("产品列表");
		FirefoxProfile pro =new FirefoxProfile(new File("C:\\Users\\lixuerui\\AppData\\Roaming\\Mozilla\\Firefox\\Profiles\\81xof17x.default"));
		WebDriver driver =new FirefoxDriver(pro);
		driver.navigate().to("http://a.zygames.com/");
		Thread.sleep(1000);
		driver.findElement(By.id("LoginName")).clear();
		driver.findElement(By.id("LoginName")).sendKeys("huhongbin");
		driver.findElement(By.id("LoginPassword")).clear();
		driver.findElement(By.id("LoginPassword")).sendKeys("123.123.123a");
		driver.findElement(By.className("login_button")).click();
		Thread.sleep(1000);
		//切换frame到左边模块
		driver.switchTo().frame("admin_left");
		//点击后台“活动管理”模块
		driver.findElement(By.xpath("//*[@id='red']/li[12]/span")).click(); 
		Thread.sleep(200);
		//点击FootballGuess
		driver.findElement(By.xpath("//*[@id='red']/li[12]/ul/li[60]/span/a")).click();
		//driver.navigate().to("http://a.zygames.com/Poll/AddPoll");
		//切换到默认模块
		driver.switchTo().defaultContent();
		//切换到信息输入模块
		driver.switchTo().frame("admin_main");

//		driver.navigate().to("http://a.zygames.com/footballguess/");
		Thread.sleep(500);
		for(int i=0;i<st.getRows();i++){
			Cell[] cell=st.getRow(i);
			count++;
			driver.findElement(By.cssSelector("input[value=\"产品列表\"]")).click();
			Thread.sleep(500);
			driver.findElement(By.cssSelector("input[value=\"增加\"]")).click();
			Thread.sleep(500);
			driver.findElement(By.id("PacketName")).sendKeys(cell[0].getContents());
			driver.findElement(By.id("PacketDescription")).sendKeys(cell[1].getContents());
			//判断是否是兑换券
			if(cell[2].getContents().equals("是")){
				driver.findElement(By.id("IsCoponPacket")).click();
			}
			driver.findElement(By.id("PacketID")).clear();
			driver.findElement(By.id("PacketID")).sendKeys(cell[3].getContents());
			driver.findElement(By.id("NeedPoints")).clear();
			driver.findElement(By.id("NeedPoints")).sendKeys(cell[4].getContents());
			driver.findElement(By.id("ShowOrder")).clear();
			driver.findElement(By.id("ShowOrder")).sendKeys(cell[8].getContents());
			//判断是否滚动
			if(cell[5].getContents().equals("Y")){
				driver.findElement(By.id("IsRoll")).click();
			}
			//判断是否可以重复领取,N为无限制
			if(cell[6].getContents().equals("N")){
				driver.findElement(By.id("IsRepeat")).click();
			}
			//判断是否通知游戏
			if(cell[7].getContents().equals("Y")){
				driver.findElement(By.id("IsNotice")).click();
			}
			driver.findElement(By.id("button2")).click();
            Thread.sleep(1000);
            System.out.println(count+".是否新增成功："+driver.findElement(By.className("red")).getText().equals("Success"));
            driver.findElement(By.className("link-clew")).click();
            Thread.sleep(1000);
		}
		wb.close();
	}
}
