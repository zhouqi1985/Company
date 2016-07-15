package com.jifen;


import java.io.File;
import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;

import com.thoughtworks.selenium.*;


public class jifen_HJX_test {
	
	public static void main(String[] args) throws Exception {
		int count=0;
		Workbook wb = Workbook.getWorkbook(new File("D:\\Data\\test.xls"));
		Sheet sheet = wb.getSheet("Sheet1");
		DefaultSelenium selenium =new DefaultSelenium("localhost", 4444, "*firefox", "http://a.zygames.com");
		selenium.start();
		selenium.open("/");
        selenium.type("id=LoginName", "admin");
        selenium.type("id=LoginPassword", "123.123a");
        selenium.click("css=input.login_button[value=\"点击登录\"]");
        selenium.waitForPageToLoad("3000");
		selenium.click("//*[@id='red']/li[10]/span");
		selenium.click("//*[@id='red']/li[10]/ul/li[149]/span/a");
		Thread.sleep(1000);
		for(int i=0;i<sheet.getRows();i++){
			Cell[] cell=sheet.getRow(i);
			count++;
		    selenium.click("css=input.bt-big[value=\"礼包管理\"]");
		    Thread.sleep(1000);
		    selenium.click("css=input.bt-big[value=\"新建礼包\"]");
		    Thread.sleep(300);
		    selenium.type("id=PacketID",cell[0].getContents());
		    selenium.type("id=PacketName", cell[1].getContents());
		    selenium.select("id=PacketTypeID", "label="+cell[2].getContents());
		    selenium.type("id=NeedPoints",cell[3].getContents());
		    selenium.type("id=Price",cell[4].getContents());
		    if(cell[5].getContents().equals("是")){
		        selenium.click("id=IsShow");
		    }
		    if(cell[6].getContents().equals("是")){
		    	selenium.click("id=IsRepeat");
		    }
		    if(cell[7].getContents().equals("是")){
		    	selenium.click("id=IsNotice");
		    }
		    selenium.type("id=ShowOrder",cell[8].getContents());
		    selenium.click("css=input.bt-big[value=\"提  交\"]");
		    Thread.sleep(1000);
		    System.out.println(count+".是否新增成功"+selenium.getText("//*[@id='clew']/table/tbody/tr[3]/td/span").equals("Success"));
		    selenium.click("//*[@id='clew']/table/tbody/tr[4]/td/a");
		    Thread.sleep(500);
		}
	}
}