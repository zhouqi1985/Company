package com.Guaka;

import java.io.File;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.support.ui.Select;

import readExcel.readExcel;

import com.module.Module;

public class guaka_setpro {

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
		mod.getModule(driver, "活动管理", "[新]充值活动");
		Thread.sleep(500);
		driver.findElement(By.cssSelector("input[value=\"普通概率\"]")).click();
		readExcel read =new readExcel();
		read.getExcel("E:\\Data\\guaka.xls", "setpro");

		for(int i=1;i<read.rows;i++){
			Thread.sleep(500);
			if(!read.readColumn("类型", i).equals("")){
				Select Range =new Select(driver.findElement(By.id("Range")));
				Range.selectByVisibleText(read.readColumn("类型", i));
				Thread.sleep(500);
				driver.findElement(By.className("bt-samll")).click();
				Thread.sleep(300);			
			}
			if(read.readColumn("礼包名称", i).equals("")) break;
			if(read.readColumn("是否可以设置概率", i).equals("是")){
				List<WebElement> rows =driver.findElements(By.xpath("//*[@id='data']/form/table/tbody/tr"));
				    for(int j=2;j<=rows.size();j++){
					if(driver.findElement(By.xpath("//*[@id='data']/form/table/tbody/tr["+j+"]/td[2]")).getText().trim().equals(read.readColumn("礼包名称", i))){
						driver.findElement(By.xpath("//*[@id='data']/form/table/tbody/tr["+j+"]/td[4]/[@id='RateValue']")).clear();
						driver.findElement(By.xpath("//*[@id='data']/form/table/tbody/tr["+j+"]/td[4]/[@id='RateValue']")).sendKeys(read.readColumn("概率", i));
					}
				}
				Thread.sleep(500);
				driver.findElement(By.cssSelector("input[value=\"设置概率\"]")).click();
				Thread.sleep(500);
				driver.findElement(By.className("link-clew")).click();
				Thread.sleep(500);
			}else {
				driver.findElement(By.className("bt-samll")).click();
				Thread.sleep(300);
				List<WebElement> rows =driver.findElements(By.xpath("//*[@id='data']/form/table/tbody/tr"));
				
				for(int j=2;j<=rows.size();j++){
					
				if(driver.findElement(By.xpath("//*[@id='data']/form/table/tbody/tr["+j+"]/td[2]")).getText().trim().equals(read.readColumn("礼包名称", i))){
						driver.findElement(By.xpath("//*[@id='data']/form/table/tbody/tr["+j+"]/td[4]/[@id='RateValue']")).clear();
						driver.findElement(By.xpath("//*[@id='data']/form/table/tbody/tr["+j+"]/td[4]/[@id='RateValue']")).sendKeys(read.readColumn("概率", i));
					}
				}
				Thread.sleep(500);
			}	
		}
	}
}
