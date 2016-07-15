package com.getcard;

import java.io.File;
import java.util.List;
import java.util.Scanner;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.support.ui.Select;

import com.module.EsalesModule;
import com.module.Module;

import readExcel.readExcel;

public class S_card {

	@SuppressWarnings("resource")
	public static void main(String[] args) throws Exception {
		FirefoxProfile pro =new FirefoxProfile(new File("C:\\Users\\lixuerui\\AppData\\Roaming\\Mozilla\\Firefox\\Profiles\\81xof17x.default"));
		WebDriver driver =new FirefoxDriver(pro);
		readExcel read =new readExcel();
		read.getExcel("D:\\Data\\esales.xls", "Scard");
		for(int i=1;i<read.rows;i++){
			if(read.readColumn("Esales�˺�", i).equals("")) break;
			driver.navigate().to("http://esales.zygames.com/");
			driver.findElement(By.id("UserName")).sendKeys(read.readColumn("Esales�˺�", i));
			driver.findElement(By.id("LoginPassword")).sendKeys("111111");
			Thread.sleep(1000);
			Scanner scan =new Scanner(System.in);
			System.out.print("��������֤�룺");
			String ValidCode=scan.nextLine();
			driver.findElement(By.id("ValidCode")).sendKeys(ValidCode);
			driver.findElement(By.xpath("//*[@id='container']/form/div/div/div/div/input")).click();   //�����¼
			Thread.sleep(1000);
			EsalesModule emod =new EsalesModule();
			emod.getModule(driver, "�㿨�ɹ�", "��Ʒ�ɹ�");
			if(read.readColumn("��Ʒ����", i).equals("����")){
				driver.findElement(By.xpath("//*[@id='container']/div/div/div/div[2]/ul[2]/li/a")).click();  //ѡ�񷵵����͵㿨
			}else {
				List<WebElement> row=driver.findElements(By.xpath("//*[@id='container']/div/div/div/div[2]/ul[1]/li"));
				for(int j=1;j<row.size();j++){
					if(driver.findElement(By.xpath("//*[@id='container']/div/div/div/div[2]/ul[1]/li["+j+"]/a")).getText().equals(read.readColumn("��Ʒ����", i))){
						driver.findElement(By.xpath("//*[@id='container']/div/div/div/div[2]/ul[1]/li["+j+"]/a")).click();
						break;
					}
				}
			}
			Thread.sleep(1000);
			Select ServiceID =new Select(driver.findElement(By.id("ServiceID")));
			ServiceID.selectByVisibleText(read.readColumn("�㿨���", i));
			driver.findElement(By.id("numb")).sendKeys(read.readColumn("�㿨����", i));
			driver.findElement(By.className("bt-samll")).click();
			Thread.sleep(500);
			driver.findElement(By.cssSelector("input.btn1[value=\"���ɶ���\"]")).click();
			driver.switchTo().alert().accept();
			driver.findElement(By.id("Linkman")).sendKeys("lee");
			driver.findElement(By.className("btn1")).click();
			Thread.sleep(1000);
			driver.navigate().to("http://a.zygames.com/");
			Thread.sleep(1000);
			driver.findElement(By.id("LoginName")).clear();
			driver.findElement(By.id("LoginName")).sendKeys("admin");
			driver.findElement(By.id("LoginPassword")).clear();
			driver.findElement(By.id("LoginPassword")).sendKeys("123.123a");
			driver.findElement(By.className("login_button")).click();
			Thread.sleep(1000);
			Module mod =new Module();
			driver.switchTo().defaultContent();
			mod.getModule(driver, "����ϵͳ����V2.0", "�������");
			Thread.sleep(200);
			driver.findElement(By.xpath("//*[@id='data']/table/tbody/tr[2]/td[11]/input[1]")).click();  //���ͨ����һ������
			driver.switchTo().alert().accept();
			Thread.sleep(500);
			mod.repeatModule(driver, "����ϵͳ����V2.0", "�����տ�");
			Thread.sleep(1000);
			driver.findElement(By.xpath("//*[@id='data']/table/tbody/tr[2]/td[12]/input[1]")).click();  //�����һ���տ���Ϣ
			driver.findElement(By.id("C_FinanceInfo")).sendKeys("123123");
			driver.findElement(By.id("C_PayMethod")).sendKeys("����");
			driver.findElement(By.id("C_Amount")).sendKeys("270");
			driver.findElement(By.id("C_ReceiveDate")).sendKeys("2014-6-9");
			driver.findElement(By.id("Submit1")).click();
			Thread.sleep(3000);
			driver.get("http://a.zygames.com");
			Thread.sleep(200);
			mod.getModule(driver, "��Ϸ��ϵͳV2.0", "��Ϸ������");
			Thread.sleep(1000);
			Select AreaID =new Select(driver.findElement(By.id("AreaID")));
			AreaID.selectByVisibleText("101");
			Thread.sleep(200);
			Select GameID =new Select(driver.findElement(By.id("GameID")));
			GameID.selectByVisibleText("ȫ��ʹ��");
			Thread.sleep(200);
			Select CardID =new Select(driver.findElement(By.id("ServiceID")));
			CardID.selectByVisibleText(read.readColumn("��ֵ��Ʒ", i));
			driver.findElement(By.id("quantity")).sendKeys(read.readColumn("�㿨����", i));
			driver.findElement(By.id("TimeValid")).sendKeys("2016-11-30");
			driver.findElement(By.className("bt-big")).click();
			Thread.sleep(2000);
			mod.repeatModule(driver, "��Ϸ��ϵͳV2.0", "��Ϸ����ѯ");
			String batch =driver.findElement(By.xpath("//*[@id='data']/table/tbody/tr[2]/td[3]")).getText().trim();
			mod.repeatModule(driver, "��Ϸ��ϵͳV2.0", "��Ϸ�����");
			driver.findElement(By.id("Batch")).sendKeys(batch);
			driver.findElement(By.id("BatchSerial")).sendKeys("1");
			driver.findElement(By.id("BatchSerialEnd")).sendKeys(read.readColumn("�㿨����", i));
			driver.findElement(By.cssSelector("input[value=\"����\"]")).click();
			Thread.sleep(1500);
			driver.findElement(By.xpath("//*[@id='page_title']/table/tbody/tr/td[2]/input[6]")).click();  //������
			Thread.sleep(1500);
			mod.repeatModule(driver, "��Ϸ��ϵͳV2.0", "��Ϸ������");
			Thread.sleep(1000);
			driver.findElement(By.xpath("//*[@id='data']/table/tbody/tr[2]/td[8]/input[1]")).click();
			Thread.sleep(2000);
			driver.findElement(By.xpath("//*[@id='outItemTable']/tbody/tr[2]/td[4]/input")).sendKeys(batch);
			driver.findElement(By.xpath("//*[@id='outItemTable']/tbody/tr[2]/td[5]/input")).sendKeys("1");
			driver.findElement(By.xpath("//*[@id='outItemTable']/tbody/tr[2]/td[6]/input")).sendKeys(read.readColumn("�㿨����", i));
			Thread.sleep(2000);
			driver.findElement(By.xpath("//*[@id='data']/form/input[2]")).click();  //�������
			Thread.sleep(3000);
			mod.repeatModule(driver, "��Ϸ��ϵͳV2.0", "��Ϸ����ѯ");
			driver.findElement(By.id("Batch")).sendKeys(batch);
			driver.findElement(By.id("BatchSerial")).sendKeys("1");
			driver.findElement(By.id("BatchSerialEnd")).sendKeys(read.readColumn("�㿨����", i));
			driver.findElement(By.cssSelector("input[value=\"���ɿ��ܰ�\"]")).click();
			Thread.sleep(1500);
			driver.get("http://a.zygames.com");
			Thread.sleep(200);
			mod.getModule(driver, "����ϵͳ����V2.0", "��������");
			driver.findElement(By.xpath("//*[@id='data']/table/tbody/tr[2]/td[12]/input[1]")).click();
			Thread.sleep(300);
			driver.switchTo().alert().accept();
			Thread.sleep(1000);
			driver.navigate().to("http://esales.zygames.com/");
			Thread.sleep(1000); 
			emod.getModule(driver, "�㿨�ɹ�", "�ջ�ȷ��");  //���ȷ���ջ�
			Thread.sleep(500);
			driver.findElement(By.xpath("//*[@id='orderListContainer']/table/tbody/tr[1]/td[7]/a[2]")).click();  //ȷ�ϵ�һ���ջ���Ϣ
			Thread.sleep(1000);
			driver.switchTo().frame(0);
			driver.findElement(By.id("Batch")).sendKeys(batch);
			driver.findElement(By.id("BatchSerial")).sendKeys("1");
			driver.findElement(By.id("BatchSerialEnd")).sendKeys(read.readColumn("�㿨����", i));
			driver.findElement(By.cssSelector("input[value=\"ȷ��\"]")).click();
			Thread.sleep(500);
			driver.navigate().to("http://a.zygames.com/");
			Thread.sleep(500); 
			mod.repeatModule(driver, "����ϵͳ����V2.0", "��������");
			Thread.sleep(1000);
			driver.findElement(By.xpath("//*[@id='data']/table/tbody/tr[2]/td[12]/input[1]")).click(); //�����һ������
			driver.switchTo().alert().accept();
			Thread.sleep(1000);
			mod.getModule(driver, "��Ϸ��ϵͳV2.0", "��������ѯ");
			System.out.println("ʵ�￨������룺"+driver.findElement(By.xpath("//*[@id='data']/table/tbody/tr[2]/td[2]")).getText().trim()+"���뵽��Ӧ\\Publish\\WebSite\\Admin\\CardV2\\CardExport�л�ȡ"+driver.findElement(By.xpath("//*[@id='data']/table/tbody/tr[2]/td[1]")).getText()+"ʵ�￨��");
		}

	}

}
