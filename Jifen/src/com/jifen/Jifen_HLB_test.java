package com.jifen;

import java.io.*;

import com.thoughtworks.selenium.*;


public class Jifen_HLB_test {
	
	public static void main(String[] args) throws Exception {
		int count=0;
	    InputStreamReader isr =new InputStreamReader(new FileInputStream("D:\\Data\\ces1.txt"), "GBK");
		BufferedReader br =new BufferedReader(isr);
		String line=null;
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
		while((line=br.readLine())!=null){
			String[] data =line.split("/");
			count++;
		    selenium.click("css=input.bt-big[value=\"礼包管理\"]");
		    Thread.sleep(1000);
		    selenium.click("css=input.bt-big[value=\"新建礼包\"]");
		    Thread.sleep(300);
		    selenium.type("id=PacketID",data[0]);
		    selenium.type("id=PacketName", data[1]);
		    selenium.select("id=PacketTypeID", "label="+data[2]);
		    selenium.type("id=NeedPoints",data[3]);
		    selenium.type("id=Price",data[4]);
		    if(data[5].equals("是")){
		        selenium.click("id=IsShow");
		    }
		    if(data[6].equals("是")){
		    	selenium.click("id=IsRepeat");
		    }
		    if(data[7].equals("是")){
		    	selenium.click("id=IsNotice");
		    }
		    selenium.type("id=ShowOrder",data[8]);
		    selenium.click("css=input.bt-big[value=\"提  交\"]");
		    Thread.sleep(1000);
		    System.out.println(count+".是否新增成功"+selenium.getText("//*[@id='clew']/table/tbody/tr[3]/td/span").equals("Success"));
		    selenium.click("//*[@id='clew']/table/tbody/tr[4]/td/a");
		    Thread.sleep(500);
		}
		br.close();
	}

}
