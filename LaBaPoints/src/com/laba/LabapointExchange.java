package com.laba;

import java.io.File;
import java.util.Scanner;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.support.ui.Select;

import readExcel.readExcel;

public class LabapointExchange {

	@SuppressWarnings("resource")
	public static void main(String[] args) throws Exception {
		int count=0;
		int total;
		FirefoxProfile pro =new FirefoxProfile(new File("C:\\Users\\lixuerui\\AppData\\Roaming\\Mozilla\\Firefox\\Profiles\\81xof17x.default"));
		WebDriver driver =new FirefoxDriver(pro);
		readExcel read =new readExcel();
		read.getExcel("D:\\Data\\labapoints.xls", "labaExchange");
		driver.navigate().to("http://event.zygames.com/qqsm/201412/LaBa/");
		Thread.sleep(1500);
		driver.findElement(By.cssSelector("a[title=\"���ֲ�ѯ\"]")).click();
		Thread.sleep(1500);
		driver.switchTo().frame(0);
		Thread.sleep(200);
		driver.findElement(By.id("LoginName")).clear();
		driver.findElement(By.id("LoginName")).sendKeys("test0099");
		driver.findElement(By.id("LoginPassWord")).sendKeys("111111");
		Thread.sleep(1000);
		Scanner scan =new Scanner(System.in);
		System.out.print("��������֤�룺");
		String ValidCode=scan.nextLine();
		driver.findElement(By.id("validCode")).sendKeys(ValidCode);
		driver.findElement(By.name("�ύ")).click();
		Thread.sleep(3000);
		Select GameAreaList =new Select(driver.findElement(By.id("GameAreaList")));
		GameAreaList.selectByVisibleText(read.readColumn("����", 1));
		Thread.sleep(3000);
		driver.findElement(By.id("btnSubmit")).click();
		driver.switchTo().defaultContent();
		Thread.sleep(1000);
		for(int i=1;i<read.rows;i++){
			count++;
			Thread.sleep(1000);
			if(read.readColumn("�������", i).equals("")) break;
			String currentPoint =driver.findElement(By.cssSelector("span#points")).getText();
			if(i==1){
			    total =Integer.valueOf(currentPoint.substring(0, currentPoint.length()-2));
			}else{
				total =Integer.valueOf(currentPoint.substring(0, currentPoint.length()));
			}
			
			int n=Integer.valueOf(read.readColumn("�������", i));
			if(total<n){
				driver.findElement(By.cssSelector("li[pid=\""+read.readColumn("���ID", i)+"\"]")).click();
				Thread.sleep(500);
                driver.findElement(By.xpath("//*[@id='popBox']/div[2]/a")).click();  //�����Ҫ�һ�
                Thread.sleep(500);
                System.out.println(count+"."+read.readColumn("�������", i)+" ����Ƿ�һ�ʧ�ܣ���ʾ���ֲ��㣺"+driver.findElement(By.xpath("//*[@id='popBox']/table/tbody/tr/td")).getText().trim().equals("���ֲ���"));
                Thread.sleep(500);
                driver.findElement(By.xpath("//*[@id='popBox']/div[1]/a")).click();  //�������
                System.out.print("\n");
                Thread.sleep(500);
			}else{
				driver.findElement(By.cssSelector("li[pid=\""+read.readColumn("���ID", i)+"\"]")).click();
				Thread.sleep(500);
                driver.findElement(By.xpath("//*[@id='popBox']/div[2]/a")).click();  //�����Ҫ�һ�
                Thread.sleep(1000);
                boolean cg =driver.findElement(By.xpath("//*[@id='popBox']/table/tbody/tr/td")).getText().trim().equals("�һ��ɹ�");
                Thread.sleep(500);
                if(cg==true){
                	System.out.println(count+"."+read.readColumn("�������", i)+" ����Ƿ�һ��ɹ���"+driver.findElement(By.xpath("//*[@id='popBox']/table/tbody/tr/td")).getText().trim().equals("�һ��ɹ�"));
                	driver.findElement(By.xpath("//*[@id='popBox']/div[1]/a")).click();  //�������
                	Thread.sleep(1000);
                	int j=total-n;
                	String currentPoint1 =driver.findElement(By.cssSelector("span#points")).getText();
        			int total1 =Integer.valueOf(currentPoint1);
        			System.out.println("��������������Ϊ��"+read.readColumn("�������", i));
        			System.out.println("��ǰʣ����֣�"+j);
        			System.out.println("�۳������Ƿ���ȷ:"+(j==total1));
        			System.out.print("\n");
        			Thread.sleep(500);
                }else {
                	System.out.println(count+"."+read.readColumn("�������", i)+" ����Ƿ����ظ��һ���"+driver.findElement(By.xpath("//*[@id='popBox']/table/tbody/tr/td")).getText().trim().contains("�����ظ��һ�"));
                	driver.findElement(By.xpath("//*[@id='popBox']/div[1]/a")).click();  //�������
                	Thread.sleep(500);
                	int j=total;
                	String currentPoint1 =driver.findElement(By.cssSelector("span#points")).getText();
                	int total1 =Integer.valueOf(currentPoint1);
                	System.out.println("��������������Ϊ��"+read.readColumn("�������", i));
        			System.out.println("��ǰʣ����֣�"+j);
        			System.out.println("�����Ƿ���ȷ����δ�۳�����:"+(j==total1));
        			System.out.print("\n");
        			Thread.sleep(500);
				}	
			}
		}

	}

}
