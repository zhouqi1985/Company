package com.hongbao;

import java.io.File;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.support.ui.Select;

import readExcel.readExcel;

import com.module.Module;

public class hongbao_pt{

	public static void main(String[] args) throws Exception {
		int count=0;
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
		Module mod =new Module();
		mod.getModule(driver, "活动管理", "(Q)积分兑换_New");
		readExcel read =new readExcel();
		read.getExcel("E:\\Data\\HB.xls", "HB1");
		Thread.sleep(1000);
		driver.findElement(By.cssSelector("input[value=\"普通礼包管理\"]")).click();
		for(int i=1;i<read.rows;i++){
			count++;
			if(read.readColumn("礼包ID", i).equals("")) break;
			Thread.sleep(1000);
			driver.findElement(By.cssSelector("input[value=\"增加普通奖励\"]")).click();
			Thread.sleep(1000);
			driver.findElement(By.id("PacketId")).clear();
			driver.findElement(By.id("PacketId")).sendKeys(read.readColumn("礼包ID", i));
			driver.findElement(By.id("PacketName")).sendKeys(read.readColumn("礼包名称", i));
			driver.findElement(By.id("PacketDesc")).sendKeys(read.readColumn("礼包描述", i));
			Thread.sleep(500);
		    Select PacketTypeID =new Select(driver.findElement(By.id("RangeId")));
		    PacketTypeID.selectByVisibleText(read.readColumn("礼包类型", i));
//			driver.findElement(By.id("ClassID")).clear();     //充值产品ID，默认为0（一般情况不用更改）
//			driver.findElement(By.id("ClassID")).sendKeys(read.readColumn("ClassID", i));
			if(read.readColumn("是否公告", i).equals("是")){
				driver.findElement(By.id("IsNotice")).click();
			}
//			driver.findElement(By.id("BuyCount")).clear();  //统计礼包领取数，默认为0
//			driver.findElement(By.id("BuyCount")).sendKeys(read.readColumn("BuyCount", i));
/*			if(read.readColumn("是否滚屏", i).equals("是")){
				driver.findElement(By.id("IsRoll")).click();
			}
            if(read.readColumn("是否可以重复得到", i).equals("是")){
				driver.findElement(By.id("IsRepeat")).click();
			}*/
			driver.findElement(By.id("button2")).click();
			Thread.sleep(1000);
			System.out.println(count+"."+read.readColumn("礼包名称", i)+" 礼包是否新增成功:"+driver.findElement(By.className("red")).getText().trim().equals("Success"));
			driver.findElement(By.className("link-clew")).click();
			Thread.sleep(1000);
		}

	}

}
