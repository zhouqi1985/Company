
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
		mod.getModule(driver, "兑换券系统", "赠送道具到游戏");
		Thread.sleep(1000);
		readExcel read =new readExcel();
		read.getExcel("E:\\Data\\Sign_ZX.xls", "赠送道具到游戏");  //赠送礼包信息
		for(int i=1;i<read.rows;i++){
			count++;
			if(read.readColumn("游戏", i).equals("")) break;
			String game =read.readColumn("游戏", i);
			String classid =read.readColumn("分类", i);
			String packet =read.readColumn("礼包", i);
			String site =read.readColumn("区服", i);
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
			System.out.println(count +"."+packet+ " 是否礼包发送成功："+driver.findElement(By.className("red")).getText().equals("成功！"));
           driver.findElement(By.className("link-clew")).click();
           Thread.sleep(5000);	
           mod.repeatModule(driver, "兑换券系统", "赠送道具到游戏");
           Thread.sleep(500);
		}

	}

}
