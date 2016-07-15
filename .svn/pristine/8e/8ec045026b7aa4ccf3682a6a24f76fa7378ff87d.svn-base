package com.giftpoint;

import java.io.File;
import java.util.List;
import java.util.Scanner;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.support.ui.Select;

import readExcel.readExcel;

import com.module.Module;

public class giftPoint_ZhuanPanExchange {

	@SuppressWarnings("resource")
	public static void main(String[] args) throws Exception {
		int count=0;
		FirefoxProfile pro =new FirefoxProfile(new File("C:\\Users\\lixuerui\\AppData\\Roaming\\Mozilla\\Firefox\\Profiles\\81xof17x.default"));
		FirefoxDriver driver =new FirefoxDriver(pro);
		readExcel read =new readExcel();
		read.getExcel("D:\\Data\\giftpoint.xls", "new");
		driver.get("http://cdn1.zygames.com/qqsm/events/201411/turnplate/index.html");   //没期活动地址不同，需更改
		Thread.sleep(1000);
		driver.findElement(By.cssSelector("area[title=\"我要登录\"]")).click();
		Thread.sleep(1000);
		driver.switchTo().frame(0);
		Thread.sleep(200);
		driver.findElement(By.id("LoginName")).clear();
		driver.findElement(By.id("LoginName")).sendKeys("quanqiushiming7@qq.com");
		driver.findElement(By.id("LoginPassWord")).sendKeys("qqsmATM");
		Thread.sleep(1000);
		Scanner scan =new Scanner(System.in);
		System.out.print("请输入验证码：");
		String ValidCode=scan.nextLine();
		driver.findElement(By.id("validCode")).sendKeys(ValidCode);
		driver.findElement(By.name("提交")).click();
		Thread.sleep(2000);
		Select GameAreaList =new Select(driver.findElement(By.id("GameAreaList")));
		GameAreaList.selectByVisibleText(read.readColumn("区服", 1));
		Thread.sleep(1500);
		driver.findElement(By.id("btnSubmit")).click();
		driver.switchTo().defaultContent();
		Thread.sleep(1000);
		driver.get("http://a.zygames.com");
		Thread.sleep(1000);
		driver.findElement(By.id("LoginName")).clear();
		driver.findElement(By.id("LoginName")).sendKeys("huhongbin");
		driver.findElement(By.id("LoginPassword")).clear();
		driver.findElement(By.id("LoginPassword")).sendKeys("123.123.123a");
		driver.findElement(By.className("login_button")).click();
		Thread.sleep(500);
		Module mod =new Module();
		mod.getModule(driver, "活动管理", "(Q)礼物兑换券活动");    //线下和线上有区别，需更改
		for(int i=1;i<read.rows;i++){
			count++;
			Thread.sleep(500);
			driver.findElement(By.cssSelector("input[value=\"概率管理\"]")).click();
			Thread.sleep(1000);
			Select Machine =new Select(driver.findElement(By.id("Machine")));
			Machine.selectByVisibleText(read.readColumn("转盘类型", i));
			driver.findElement(By.className("bt-samll")).click();
			Thread.sleep(300);
			List<WebElement> rows =driver.findElements(By.xpath("//*[@id='data']/form/table/tbody/tr"));
			for(int j=2;j<rows.size();j++){
				if(driver.findElement(By.xpath("//*[@id='data']/form/table/tbody/tr["+j+"]/td[2]")).getText().trim().equals(read.readColumn("礼包名称", i))){
					driver.findElement(By.xpath("//*[@id='data']/form/table/tbody/tr["+j+"]/td[4]/*[@id='RateValue']")).clear();
					driver.findElement(By.xpath("//*[@id='data']/form/table/tbody/tr["+j+"]/td[4]/*[@id='RateValue']")).sendKeys(read.readColumn("概率", i));
				}else {
					driver.findElement(By.xpath("//*[@id='data']/form/table/tbody/tr["+j+"]/td[4]/*[@id='RateValue']")).clear();
					driver.findElement(By.xpath("//*[@id='data']/form/table/tbody/tr["+j+"]/td[4]/*[@id='RateValue']")).sendKeys("0");
				}
			}
			driver.findElement(By.cssSelector("input[value=\"设置概率\"]")).click();
			Thread.sleep(500);
			driver.findElement(By.className("link-clew")).click();
//			driver.findElement(By.cssSelector("input[value=\"修改概率\"]")).click();
//			Thread.sleep(1000);
//			Select Machine =new Select(driver.findElement(By.id("Machine")));
//			Machine.selectByVisibleText(read.readColumn("转盘类型", i));
//			driver.findElement(By.id("PacketId")).clear();
//			driver.findElement(By.id("PacketId")).sendKeys(read.readColumn("礼包ID", i));
//			Thread.sleep(500);
//			driver.findElement(By.id("button2")).click();
//			Thread.sleep(500);
//			driver.findElement(By.className("link-clew")).click();
			Thread.sleep(500);
			driver.get("http://cdn1.zygames.com/qqsm/events/201411/turnplate/LuckyDraw.html");  //没期活动地址不同，需更改
			Thread.sleep(1000);
			driver.findElement(By.cssSelector("a[title=\""+read.readColumn("抽奖类型", i)+"\"]")).click();
			Thread.sleep(500);
			driver.findElement(By.xpath("//*[@id='imgPan"+read.readColumn("转盘数", i)+"']")).click();    //1为破军、2为退魔 、3为pvp
			Thread.sleep(500);
			Scanner sr =new Scanner(System.in);
			System.out.print("请输入验证结果：");
			String success=sr.nextLine();
			System.out.println(count+".是否验证成功："+success);
			System.out.println(count+"."+read.readColumn("转盘类型", i)+"-"+read.readColumn("礼包名称", i)+"是否兑换正确："+driver.switchTo().alert().getText().trim().contains(read.readColumn("礼包名称", i).trim()));
			driver.switchTo().alert().accept();
			Thread.sleep(1000);
			if(i==read.rows-1) break;
			driver.get("http://a.zygames.com");
			Thread.sleep(500);
			mod.repeatModule(driver,"活动管理", "(Q)礼物兑换券活动");  //线下和线上有区别，需更改
		}
	}

}
