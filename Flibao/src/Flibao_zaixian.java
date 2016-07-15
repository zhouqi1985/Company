
import java.io.File;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.support.ui.Select;

import readExcel.readExcel;

import com.module.Module;

public class Flibao_zaixian {

	public static void main(String[] args) throws Exception {
		int count =0;
		FirefoxProfile pro =new FirefoxProfile(new File("C:\\Users\\zhouqi\\AppData\\Roaming\\Mozilla\\Firefox\\Profiles\\vtco59ig.default"));
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
		mod.getModule(driver, "�һ�ȯϵͳ", "���͵��ߵ���Ϸ");
		Thread.sleep(1000);
		readExcel read =new readExcel();
		read.getExcel("E:\\Data\\Sign_ZX.xls", "���͵��ߵ���Ϸ");  //���������Ϣ
		for(int i=1;i<read.rows;i++){
			count++;
			if(read.readColumn("��Ϸ", i).equals("")) break;
			String game =read.readColumn("��Ϸ", i);
			String classid =read.readColumn("����", i);
			String packet =read.readColumn("���", i);
			String site =read.readColumn("����", i);
			String avater =read.readColumn("AvatarID", i);
			Thread.sleep(1000);
			if(game==null||game.isEmpty()) break;
			Select GameID =new Select(driver.findElement(By.id("GameID")));
			GameID.selectByVisibleText(game);
			Thread.sleep(1500);
			Select ClassID =new Select(driver.findElement(By.id("ClassID")));
			ClassID.selectByVisibleText(classid);
			Thread.sleep(3000);
			Select Site =new Select(driver.findElement(By.id("Site")));
			Site.selectByVisibleText(site);
			Thread.sleep(1000);
            Select PacketID =new Select(driver.findElement(By.id("PacketID")));
			PacketID.selectByVisibleText(packet);
			Thread.sleep(2000);
			driver.findElement(By.id("AvatarIDs")).clear();
			driver.findElement(By.id("AvatarIDs")).sendKeys(avater);
			Thread.sleep(1000);
			driver.findElement(By.id("packetDescription")).click();
			Thread.sleep(2000);
			driver.findElement(By.id("btnSubmit")).click();
			Thread.sleep(2000);
			System.out.println(count +"."+packet+ " �Ƿ�������ͳɹ���"+driver.findElement(By.className("red")).getText().equals("�ɹ���"));
           driver.findElement(By.className("link-clew")).click();
           Thread.sleep(5000);	
           mod.repeatModule(driver, "�һ�ȯϵͳ", "���͵��ߵ���Ϸ");
           Thread.sleep(500);
		}

	}

}