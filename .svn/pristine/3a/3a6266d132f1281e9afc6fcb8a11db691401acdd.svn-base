package com.jiang;

import java.io.File;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;

import readExcel.readExcel;

import com.module.Module;

public class Jiang_LaBa {

	public static void main(String[] args) throws Exception {
		int count=0;
		FirefoxProfile pro =new FirefoxProfile(new File("C:\\Users\\lixuerui\\AppData\\Roaming\\Mozilla\\Firefox\\Profiles\\81xof17x.default"));
		WebDriver driver =new FirefoxDriver(pro);
		driver.get("http://a.zygames.com/");
		Thread.sleep(1000);
		driver.findElement(By.id("LoginName")).clear();
		driver.findElement(By.id("LoginName")).sendKeys("admin");
		driver.findElement(By.id("LoginPassword")).clear();
		driver.findElement(By.id("LoginPassword")).sendKeys("123.123a");
		driver.findElement(By.className("login_button")).click();
		Thread.sleep(1000);
		Module mod =new Module();
		mod.getModule(driver, "活动管理", "【RV2】注册有奖菜单");
		Thread.sleep(1000);
		driver.findElement(By.cssSelector("input[value=\"拉霸产品配置列表\"]")).click();
		Thread.sleep(500);
		readExcel read =new readExcel();
		read.getExcel("D:\\Data\\Jiang.xls", "laba");    //礼包信息
		for(int i=1;i<read.rows;i++){
			count++;
			if(read.readColumn("奖品名称", i).equals("")) break;
			driver.findElement(By.cssSelector("input[value=\"增加\"]")).click();
			Thread.sleep(500);
			driver.findElement(By.id("ProductName")).sendKeys(read.readColumn("奖品名称", i));
			driver.findElement(By.id("ProdcutDesc")).sendKeys(read.readColumn("奖品描述", i));
			driver.findElement(By.id("PacketID")).clear();
			driver.findElement(By.id("PacketID")).sendKeys(read.readColumn("奖品ID", i));
			if(read.readColumn("是否通知游戏", i).equals("是")){
				driver.findElement(By.id("IsNotice")).click();
			}
			driver.findElement(By.id("btnSubmit")).click();
			Thread.sleep(500);
			System.out.println(count+"."+read.readColumn("奖品名称", i)+"是否新增成功："+driver.findElement(By.className("red")).getText().trim().contains("Success"));
			driver.findElement(By.className("link-clew")).click();
			Thread.sleep(1000);
		}
		for(int j=1;j<read.rows;j++){
			if(read.readColumn("奖品名称", j).equals("")) break;
			driver.findElement(By.cssSelector("input[value=\"概率管理\"]")).click();
			Thread.sleep(500);
			List<WebElement> row =driver.findElements(By.xpath("//*[@id='data']/form/table/tbody/tr"));
			for(int n=2;n<=row.size();n++){
				if(driver.findElement(By.xpath("//*[@id='data']/form/table/tbody/tr["+n+"]/td[2]")).getText().trim().equals(read.readColumn("奖品名称", j))){
					driver.findElement(By.xpath("//*[@id='data']/form/table/tbody/tr["+n+"]/td[6]/input")).sendKeys(read.readColumn("概率", j));
				}
			}
			driver.findElement(By.cssSelector("input[value=\"编辑\"]")).click();
			Thread.sleep(500);
			driver.findElement(By.className("link-clew")).click();
			Thread.sleep(1000);
		}

	}

}
