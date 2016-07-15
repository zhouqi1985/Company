package com.football;

import java.io.File;
import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.support.ui.Select;

import jxl.Cell;
import jxl.JXLException;
import jxl.Sheet;
import jxl.Workbook;

public class Football_HBS1 {

	public static void main(String[] args) throws JXLException, IOException, Exception {
		int count =0;
		Workbook wb= Workbook.getWorkbook(new File("D:\\Data\\football.xls"));
		Sheet st =wb.getSheet("比赛日程1");
		FirefoxProfile pro =new FirefoxProfile(new File("C:\\Users\\lixuerui\\AppData\\Roaming\\Mozilla\\Firefox\\Profiles\\81xof17x.default"));
		WebDriver driver =new FirefoxDriver(pro);
		driver.navigate().to("http://a.zygames.com");
		driver.findElement(By.id("LoginName")).clear();
		driver.findElement(By.id("LoginName")).sendKeys("admin");
		driver.findElement(By.id("LoginPassword")).clear();
		driver.findElement(By.id("LoginPassword")).sendKeys("123.123a");
		driver.findElement(By.className("login_button")).click();
		Thread.sleep(1000);
		/* 暂时注释
		//切换frame到左边模块
		driver.switchTo().frame("admin_left");
		//点击后台“活动管理”模块
		driver.findElement(By.xpath("//*[@id='red']/li[20]/span")).click(); 
		//点击FootballGuess
		driver.findElement(By.xpath("//*[@id='red']/li[20]/ul/li[2]/span/a")).click();
		//driver.navigate().to("http://a.zygames.com/Poll/AddPoll");
		//切换到默认模块
		driver.switchTo().defaultContent();
		//切换到信息输入模块
		driver.switchTo().frame("admin_main");
		*/
		driver.navigate().to("http://a.zygames.com/footballguess/");
		Thread.sleep(500);
		for(int i=0;i<st.getRows();i++){
			Cell[] cell=st.getRow(i);
			count++;
			driver.findElement(By.cssSelector("input[value=\"比赛列表\"]")).click();
			Thread.sleep(500);
			driver.findElement(By.cssSelector("input[value=\"增加比赛\"]")).click();
			Thread.sleep(500);
			String match =cell[0].getContents()+"vs"+cell[1].getContents();
			driver.findElement(By.id("MatchName")).sendKeys(match);
			driver.findElement(By.id("MatchDescription")).sendKeys(cell[2].getContents());
			driver.findElement(By.cssSelector("img.ui-datepicker-trigger")).click();
			Thread.sleep(300);
			Select month =new Select(driver.findElement(By.cssSelector("select.ui-datepicker-new-month")));
			month.selectByVisibleText(cell[3].getContents());
			Select year =new Select(driver.findElement(By.cssSelector("select.ui-datepicker-new-year")));
			year.selectByVisibleText(cell[4].getContents());
			Thread.sleep(200);
			driver.findElement(By.linkText(cell[5].getContents())).click();
			Thread.sleep(200);
			Select Team1 =new Select(driver.findElement(By.id("Team1")));
			Team1.selectByVisibleText(cell[0].getContents());
			Select Team2 =new Select(driver.findElement(By.id("Team2")));
			Team2.selectByVisibleText(cell[1].getContents());
			driver.findElement(By.id("button2")).click();
            Thread.sleep(1000);
            System.out.println(count+".是否新增成功："+driver.findElement(By.className("red")).getText().equals("Success"));
            driver.findElement(By.className("link-clew")).click();
            Thread.sleep(1000);
		}
		wb.close();
	}
}
