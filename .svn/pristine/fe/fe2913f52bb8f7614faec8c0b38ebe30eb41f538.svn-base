package com.toupiao;

import java.io.File;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.support.ui.Select;

import readExcel.readExcel;

import com.module.Module;
/*
 * 该类只适合用于单选礼包配置
 */

public class toupiao_options {

	public static void main(String[] args) throws Exception {
		int count=0;
		FirefoxProfile pro =new FirefoxProfile(new File("C:\\Users\\lixuerui\\AppData\\Roaming\\Mozilla\\Firefox\\Profiles\\81xof17x.default"));
		WebDriver driver = new FirefoxDriver(pro);
		driver.navigate().to("http://a.zygames.com/");
		Thread.sleep(1000);	
		driver.findElement(By.id("LoginName")).clear();
		driver.findElement(By.id("LoginName")).sendKeys("huhongbin");
		driver.findElement(By.id("LoginPassword")).clear();
		driver.findElement(By.id("LoginPassword")).sendKeys("123.123.123a");
		driver.findElement(By.className("login_button")).click();
		Thread.sleep(1000);
		Module mod =new Module();
		mod.getModule(driver, "活动管理", "投票兑换券");
		Thread.sleep(1000);
		readExcel read =new readExcel();
		read.getExcel("E:\\Data\\toupiao_H.xls", "组选项");
		Thread.sleep(1000);
		driver.findElement(By.cssSelector("input[value=\"选项关系\"]")).click();
		Thread.sleep(1000);
		for(int i=0;i<read.rows;i++){
			count++;
			if(read.readColumn("投票主题", i).equals("")) break;
			driver.findElement(By.cssSelector("input[value=\"增加\"]")).click();
			Thread.sleep(1000);
			driver.findElement(By.id("btnItemSelect")).click();
			Thread.sleep(1000);
			Select ThreadID =new Select(driver.findElement(By.id("ThreadID")));
			ThreadID.selectByVisibleText(read.readColumn("投票主题", i));
		    driver.findElement(By.id("OptionName")).sendKeys(read.readColumn("选项", i));
			driver.findElement(By.cssSelector("input[value=\"搜索\"]")).click();
			Thread.sleep(1000);
			List<WebElement> options =driver.findElements(By.xpath("//*[@id='itemListTable']/tbody/tr"));
			for(int j=2;j<=options.size();j++){
				String optionName =driver.findElement(By.xpath("//*[@id='itemListTable']/tbody/tr["+j+"]/td[4]")).getText().trim();
				if(optionName.equals(read.readColumn("选项", i))){
					driver.findElement(By.xpath("//*[@id='itemListTable']/tbody/tr["+j+"]/th/input")).click();
				}
			}
			//点击choose确认按钮
			driver.findElement(By.xpath("//html/body/div[6]/div[11]/button[2]")).click();
			Select PackID =new Select(driver.findElement(By.id("PackID")));
			PackID.selectByVisibleText(read.readColumn("投票礼包", i));
			//是否设置为正确答案（默认为否）
			if(read.readColumn("是否正确", i).equals("是")){
				driver.findElement(By.id("IsRight")).click();
			}
			//是否设置为定时答案（默认为否）
			if(read.readColumn("是否定时", i).equals("是")){
				driver.findElement(By.id("IsTiming")).click();
			}
			driver.findElement(By.id("button2")).click();
			Thread.sleep(500);
			System.out.println(count+"."+read.readColumn("选项", i)+" 投票礼包是否成功："+driver.findElement(By.className("red")).getText().equals("Success"));
			driver.findElement(By.className("link-clew")).click();
			Thread.sleep(1000);
		}

	}

}
