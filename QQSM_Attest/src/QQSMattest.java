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
//		read.getExcel("D:\\Data\\QQSMattest.xls", "qqsm");   //������Ϣ
		read.getExcel("D:\\Data\\QQSMattest.xls", "qqsm_zx");  //������Ϣ
		for(int i=1;i<read.rows;i++){
			count++;
//			driver.get("http://qqsmpst.zygames.com/v2/sauth.aspx?username="+read.readColumn("username", i)+"&password="+read.readColumn("password", i)+"&site="+read.readColumn("site", i)+"&game="+read.readColumn("game", i)+"&version="+read.readColumn("version", i)+"&did="+read.readColumn("did", i)); 
			//������Ϸ��֤��ַ
			driver.get("https://qqsmpst00.zygames.com/v2/sauth.aspx?username="+read.readColumn("username", i)+"&password="+read.readColumn("password", i)+"&site="+read.readColumn("site", i)+"&game="+read.readColumn("game", i)+"&version="+read.readColumn("version", i)+"&did="+read.readColumn("did", i));
			//������Ϸ��֤��ַ
			Thread.sleep(300);
			if(driver.findElement(By.xpath("//html/body")).getText().contains("SUCCEEDED")){
				System.out.println(count+"."+"�˺ŵ�¼�ɹ���");
			}else {
				System.out.println(count+"."+"�˺ŵ�¼ʧ��,������ʾ "+read.readColumn("������ʾ", i)+" �Ƿ���ʾ��ȷ��"+driver.findElement(By.xpath("//html/body")).getText().contains(read.readColumn("������ʾ", i)));
			}
			Thread.sleep(300);
		}
	}

}
