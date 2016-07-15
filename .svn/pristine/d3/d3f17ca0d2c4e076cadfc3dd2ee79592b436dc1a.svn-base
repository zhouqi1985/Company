package com.giftpoint;

import java.io.File;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.support.ui.Select;

import readExcel.readExcel;

import com.module.Module;

public class giftPoint_SetProbability {

	public static void main(String[] args) throws Exception {
		FirefoxProfile pro =new FirefoxProfile(new File("C:\\Users\\lixuerui\\AppData\\Roaming\\Mozilla\\Firefox\\Profiles\\81xof17x.default"));
		FirefoxDriver driver =new FirefoxDriver(pro);
		driver.get("http://a.zygames.com");
		Thread.sleep(1000);
		driver.findElement(By.id("LoginName")).clear();
		driver.findElement(By.id("LoginName")).sendKeys("admin");
		driver.findElement(By.id("LoginPassword")).clear();
		driver.findElement(By.id("LoginPassword")).sendKeys("123.123a");
		driver.findElement(By.className("login_button")).click();
		Thread.sleep(500);
		Module mod =new Module();
		mod.getModule(driver, "活动管理", "（G）礼物积分");
		Thread.sleep(500);
		driver.findElement(By.cssSelector("input[value=\"概率管理\"]")).click();
		readExcel read =new readExcel();
		read.getExcel("E:\\Data\\giftpoint.xls", "setpro");

		for(int i=1;i<read.rows;i++){
			Thread.sleep(500);
			if(!read.readColumn("转盘类型", i).equals("")){
				Select Machine =new Select(driver.findElement(By.id("Machine")));
				Machine.selectByVisibleText(read.readColumn("转盘类型", i));
				Thread.sleep(500);
				driver.findElement(By.className("bt-samll")).click();
				Thread.sleep(300);			
			
			if(read.readColumn("礼包名称", i).equals("")) break;
			if(read.readColumn("是否可以设置概率", i).equals("否")){
				List<WebElement> rows =driver.findElements(By.xpath("//*[@id='data']/form/table/tbody/tr"));
			
				   for(int j=2;j<=rows.size();j++){
					   for(int k=1;k<=rows.size();k++)
					   {
						   String webValue = driver.findElement(By.xpath("//*[@id='data']/form/table/tbody/tr["+j+"]/td[2]")).getText();
						   String execlValue = read.readColumn("礼包名称", k);						   
						   if(webValue.equals(execlValue)){						   
								driver.findElement(By.xpath("//*[@id='data']/form/table/tbody/tr["+j+"]/td[4]/input[@id='RateValue']")).clear();
								driver.findElement(By.xpath("//*[@id='data']/form/table/tbody/tr["+j+"]/td[4]/input[@id='RateValue']")).sendKeys(read.readColumn("概率",k));
								continue;
								
				  }
				}
			  }
			}
				Thread.sleep(500);
				driver.findElement(By.cssSelector("input[value=\"设置概率\"]")).click();
				Thread.sleep(500);
				driver.findElement(By.className("link-clew")).click();
				Thread.sleep(500);
				}
				Thread.sleep(500);
		}	
	}
}


