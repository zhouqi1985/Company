package com.giftpoint;

import java.io.File;

import org.openqa.selenium.By;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;

import readExcel.readExcel;

import com.module.Module;

public class giftPoint_HLaBa {

	public static void main(String[] args) throws Exception {
		int count =0;
		FirefoxProfile pro =new FirefoxProfile(new File("C:\\Users\\lixuerui\\AppData\\Roaming\\Mozilla\\Firefox\\Profiles\\81xof17x.default"));
		FirefoxDriver driver =new FirefoxDriver(pro);
		driver.get("http://a.zygames.com");
		Thread.sleep(1000);
		driver.findElement(By.id("LoginName")).clear();
		driver.findElement(By.id("LoginName")).sendKeys("huhongbin");
		driver.findElement(By.id("LoginPassword")).clear();
		driver.findElement(By.id("LoginPassword")).sendKeys("123.123.123a");
		driver.findElement(By.className("login_button")).click();
		Thread.sleep(1000);
		Module mod =new Module();
		mod.getModule(driver, "活动管理", "(Q)礼物兑换券活动");
		readExcel read =new readExcel();
		read.getExcel("E:\\Data\\giftpoint.xls", "laba");
		Thread.sleep(1000);
		driver.findElement(By.cssSelector("input[value=\"拉霸礼包管理\"]")).click();
		for(int i=1;i<read.rows;i++){
			count++;
			if(read.readColumn("礼包ID", i).equals("")) break;
			Thread.sleep(1000);
			driver.findElement(By.cssSelector("input[value=\"增加\"]")).click();
			Thread.sleep(1000);
			driver.findElement(By.id("PacketID")).clear();
			driver.findElement(By.id("PacketID")).sendKeys(read.readColumn("礼包ID", i));
			driver.findElement(By.id("PacketName")).sendKeys(read.readColumn("礼包名称", i).trim());
			driver.findElement(By.id("NeedPoints")).clear();
			driver.findElement(By.id("NeedPoints")).sendKeys(read.readColumn("需要积分", i));
			driver.findElement(By.id("Price")).clear();
			driver.findElement(By.id("Price")).sendKeys(read.readColumn("价格", i));
			if(read.readColumn("是否显示", i).equals("是")){
				driver.findElement(By.id("IsShow")).click();
			}
			if(read.readColumn("是否可以重复得到", i).equals("n")){    //n表示该礼包可以重复多次领取
				driver.findElement(By.id("IsRepeat")).click();
			}
			if(read.readColumn("是否公告", i).equals("是")){
				driver.findElement(By.id("IsNotice")).click();
			}
			driver.findElement(By.id("ShowOrder")).clear();
			driver.findElement(By.id("ShowOrder")).sendKeys(read.readColumn("显示顺序", i));
			Thread.sleep(500);
			driver.findElement(By.id("button2")).click();
			Thread.sleep(1000);
			System.out.println(count+"."+read.readColumn("礼包名称", i)+" 是否新增成功："+driver.findElement(By.className("red")).getText().trim().equals("Success"));
			driver.findElement(By.className("link-clew")).click();
			Thread.sleep(1000);
		}
	}
}
