import java.io.File;

import org.openqa.selenium.By;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;

import readExcel.readExcel;

public class QQSMattest {

	public static void main(String[] args) throws Exception {
		int count =0;
		FirefoxProfile pro =new FirefoxProfile(new File("C:\\Users\\lixuerui\\AppData\\Roaming\\Mozilla\\Firefox\\Profiles\\81xof17x.default"));
		FirefoxDriver driver =new FirefoxDriver(pro);
		readExcel read =new readExcel();
//		read.getExcel("D:\\Data\\QQSMattest.xls", "qqsm");   //线下信息
		read.getExcel("D:\\Data\\QQSMattest.xls", "qqsm_zx");  //在线信息
		for(int i=1;i<read.rows;i++){
			count++;
//			driver.get("http://qqsmpst.zygames.com/v2/sauth.aspx?username="+read.readColumn("username", i)+"&password="+read.readColumn("password", i)+"&site="+read.readColumn("site", i)+"&game="+read.readColumn("game", i)+"&version="+read.readColumn("version", i)+"&did="+read.readColumn("did", i)); 
			//线下游戏认证地址
			driver.get("https://qqsmpst00.zygames.com/v2/sauth.aspx?username="+read.readColumn("username", i)+"&password="+read.readColumn("password", i)+"&site="+read.readColumn("site", i)+"&game="+read.readColumn("game", i)+"&version="+read.readColumn("version", i)+"&did="+read.readColumn("did", i));
			//在线游戏认证地址
			Thread.sleep(300);
			if(driver.findElement(By.xpath("//html/body")).getText().contains("SUCCEEDED")){
				System.out.println(count+"."+"账号登录成功！");
			}else {
				System.out.println(count+"."+"账号登录失败,错误提示 "+read.readColumn("错误提示", i)+" 是否显示正确："+driver.findElement(By.xpath("//html/body")).getText().contains(read.readColumn("错误提示", i)));
			}
			Thread.sleep(300);
		}
	}

}
