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
		mod.getModule(driver, "�����", "��RV2��ע���н��˵�");
		Thread.sleep(1000);
		driver.findElement(By.cssSelector("input[value=\"���Բ�Ʒ�����б�\"]")).click();
		Thread.sleep(500);
		readExcel read =new readExcel();
		read.getExcel("D:\\Data\\Jiang.xls", "laba");    //�����Ϣ
		for(int i=1;i<read.rows;i++){
			count++;
			if(read.readColumn("��Ʒ����", i).equals("")) break;
			driver.findElement(By.cssSelector("input[value=\"����\"]")).click();
			Thread.sleep(500);
			driver.findElement(By.id("ProductName")).sendKeys(read.readColumn("��Ʒ����", i));
			driver.findElement(By.id("ProdcutDesc")).sendKeys(read.readColumn("��Ʒ����", i));
			driver.findElement(By.id("PacketID")).clear();
			driver.findElement(By.id("PacketID")).sendKeys(read.readColumn("��ƷID", i));
			if(read.readColumn("�Ƿ�֪ͨ��Ϸ", i).equals("��")){
				driver.findElement(By.id("IsNotice")).click();
			}
			driver.findElement(By.id("btnSubmit")).click();
			Thread.sleep(500);
			System.out.println(count+"."+read.readColumn("��Ʒ����", i)+"�Ƿ������ɹ���"+driver.findElement(By.className("red")).getText().trim().contains("Success"));
			driver.findElement(By.className("link-clew")).click();
			Thread.sleep(1000);
		}
		for(int j=1;j<read.rows;j++){
			if(read.readColumn("��Ʒ����", j).equals("")) break;
			driver.findElement(By.cssSelector("input[value=\"���ʹ���\"]")).click();
			Thread.sleep(500);
			List<WebElement> row =driver.findElements(By.xpath("//*[@id='data']/form/table/tbody/tr"));
			for(int n=2;n<=row.size();n++){
				if(driver.findElement(By.xpath("//*[@id='data']/form/table/tbody/tr["+n+"]/td[2]")).getText().trim().equals(read.readColumn("��Ʒ����", j))){
					driver.findElement(By.xpath("//*[@id='data']/form/table/tbody/tr["+n+"]/td[6]/input")).sendKeys(read.readColumn("����", j));
				}
			}
			driver.findElement(By.cssSelector("input[value=\"�༭\"]")).click();
			Thread.sleep(500);
			driver.findElement(By.className("link-clew")).click();
			Thread.sleep(1000);
		}

	}

}
