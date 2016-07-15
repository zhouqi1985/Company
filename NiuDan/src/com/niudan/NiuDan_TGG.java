package com.niudan;

import java.io.File;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;



import readExcel.readExcel;

import com.module.Module;

public class NiuDan_TGG {

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
		mod.getModule(driver, "�����", "(Q)Ť������ֵ���ֶһ�");
		Thread.sleep(1000);
		driver.findElement(By.cssSelector("input[value=\"�������\"]")).click();
		readExcel read =new readExcel();
		read.getExcel("E:\\Data\\niudan.xls", "Sheet1");
		for(int i=1;i<read.rows;i++){
			count++;
			if(read.readColumn("���ID", i).equals("")) break;
			Thread.sleep(1000);
			driver.findElement(By.id("PacketID")).clear();
			driver.findElement(By.id("PacketID")).sendKeys(read.readColumn("���ID", i));
			driver.findElement(By.cssSelector("input[value=\"����\"]")).click();
			Thread.sleep(500);
			List<WebElement> rows =driver.findElements(By.xpath("//*[@id='data']/table/tbody/tr"));
			for (int j = 2; j <= rows.size(); j++) {
				if (driver
						.findElement(
								By.xpath("//*[@id='data']/table/tbody/tr[" + j+ "]/td[2]")).getText().trim()
						.equals(read.readColumn("�������", i))) {
					driver.findElement(
							By.xpath("//*[@id='data']/table/tbody/tr[" + j+ "]/td[9]/input[1]")).click();
				}
			}
			driver.findElement(By.cssSelector("input[value=\"�༭\"]")).click();
			Thread.sleep(1000);
			if (read.readColumn("�Ƿ񹫸�", i).equals("��")) {
				if (!driver.findElement(By.id("IsNotice")).isSelected()) {
					driver.findElement(By.id("IsNotice")).click();
				}
			} else {
				if (driver.findElement(By.id("IsNotice")).isSelected()) {
					driver.findElement(By.id("IsNotice")).click();
				}
			}
			if (read.readColumn("isroll", i).equals("��")) {
				if (!driver.findElement(By.id("IsRoll")).isSelected()) {
					driver.findElement(By.id("IsRoll")).click();
				}
			} else {
				if (driver.findElement(By.id("IsRoll")).isSelected()) {
					driver.findElement(By.id("IsRoll")).click();
				}
			}
			driver.findElement(By.id("button2")).click();
			Thread.sleep(500);
			System.out.println(count
					+ "."
					+ read.readColumn("���ID", i)
					+ " ��������Ƿ���ӳɹ�:"
					+ driver.findElement(By.className("red")).getText().trim()
							.equals("Success"));
			driver.findElement(By.className("link-clew")).click();
			Thread.sleep(500);
		}
	}

}
