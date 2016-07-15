package com.giftpoint;

import java.io.File;
import java.util.Scanner;

import org.openqa.selenium.By;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.support.ui.Select;

import readExcel.readExcel;

public class giftpointExchange {

	@SuppressWarnings("resource")
	public static void main(String[] args) throws Exception {
		int count =0;
		FirefoxProfile pro =new FirefoxProfile(new File("C:\\Users\\lixuerui\\AppData\\Roaming\\Mozilla\\Firefox\\Profiles\\81xof17x.default"));
		FirefoxDriver driver =new FirefoxDriver(pro);
		driver.get("http://event.zygames.com/qqsm/201402/giftpoints");
		Thread.sleep(1000);
		driver.findElement(By.id("Map2")).click();
		Thread.sleep(1500);
		driver.switchTo().frame(0);
		Thread.sleep(200);
		driver.findElement(By.id("LoginName")).clear();
		driver.findElement(By.id("LoginName")).sendKeys("smsp000");
		driver.findElement(By.id("LoginPassWord")).sendKeys("111111");
		Thread.sleep(1000);
		Scanner scan =new Scanner(System.in);
		System.out.print("��������֤�룺");
		String validCode=scan.nextLine();
		driver.findElement(By.id("validCode")).sendKeys(validCode);
		driver.findElement(By.name("�ύ")).click();
		driver.switchTo().defaultContent();
		Thread.sleep(1000);
		readExcel read =new readExcel();
		read.getExcel("D:\\Data\\giftpoint.xls", "giftpoints");
		for(int i=1;i<read.rows;i++){
			count++;
			if(read.readColumn("����code", i).equals("")) break;    //����code:3��4��5���ֱ��ʾΪ����һ������ͨһ���͵��Ŷ���
			String currentPoint=driver.findElement(By.xpath("//*[@id='container']/div[2]/div["+read.readColumn("����code", i)+"]/span")).getText().trim();
			int total =Integer.valueOf(currentPoint);
			int n=Integer.valueOf(read.readColumn("�������", i));
			if(n>total){
				driver.findElement(By.xpath("//*[@id='container']/div[3]/table/tbody/tr["+Integer.valueOf(read.readColumn("������", i))+"]/td/div/a/img")).click();
				Thread.sleep(1000);
				Select gameAreaID = new Select(driver.findElement(By.id("GameAreaID")));
                gameAreaID.selectByVisibleText(read.readColumn("����", i));
                Thread.sleep(3000);
                driver.findElement(By.cssSelector("area[title=\"ȷ�϶һ�\"]")).click();
                System.out.println(count+"."+read.readColumn("�������", i)+" �һ�ʧ�ܣ��Ƿ���ʾ���ֲ��㣺"+driver.findElement(By.className("msg")).getText().trim().equals("���ֲ���"));
				driver.findElement(By.cssSelector("area[title=\"�����һ�\"]")).click();
				 System.out.print("\n");
				Thread.sleep(1000);
			}else {
				driver.findElement(By.xpath("//*[@id='container']/div[3]/table/tbody/tr["+Integer.valueOf(read.readColumn("������", i))+"]/td/div/a/img")).click();
				Thread.sleep(1000);
				Select gameAreaID = new Select(driver.findElement(By.id("GameAreaID")));
                gameAreaID.selectByVisibleText(read.readColumn("����", i));
                Thread.sleep(3000);
                driver.findElement(By.cssSelector("area[title=\"ȷ�϶һ�\"]")).click();
                Thread.sleep(1000);
                Boolean cg=driver.findElement(By.className("msg")).getText().trim().contains("��ϲ�����");
                if(cg==true){
                	System.out.println(count+"."+read.readColumn("�������", i)+"�Ƿ�һ��ɹ���"+cg);
                	driver.findElement(By.cssSelector("area[title=\"�����һ�\"]")).click();
                	int j=total-n;
                	String currentPoint1 =driver.findElement(By.xpath("//*[@id='container']/div[2]/div["+read.readColumn("����code", i)+"]/span")).getText().trim();
        			int total1 =Integer.valueOf(currentPoint1);
        			System.out.println("��������������Ϊ��"+read.readColumn("�������", i));
        			System.out.println("��ǰʣ����֣�"+j);
        			System.out.println("�۳������Ƿ���ȷ:"+(j==total1));
        			System.out.print("\n");
        			Thread.sleep(1000);
                }else {
					System.out.println(count+"."+read.readColumn("�������", i)+"�Ƿ����ظ��һ���"+driver.findElement(By.className("msg")).getText().trim().contains("�����ظ��һ�"));
					driver.findElement(By.cssSelector("area[title=\"�����һ�\"]")).click();
					int j=total;
                	String currentPoint1 =driver.findElement(By.xpath("//*[@id='container']/div[2]/div["+read.readColumn("����code", i)+"]/span")).getText().trim();
        			int total1 =Integer.valueOf(currentPoint1);
                	System.out.println("��������������Ϊ��"+read.readColumn("�������", i));
        			System.out.println("��ǰʣ����֣�"+j);
        			System.out.println("�����Ƿ���ȷ����δ�۳�����:"+(j==total1));
        			System.out.print("\n");
        			Thread.sleep(1000);
				}
			}
		}
	}

}
