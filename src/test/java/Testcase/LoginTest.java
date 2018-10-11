package Testcase;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import junit.framework.Assert;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

//import com.tests.LoginTest;

public class LoginTest {
	
	WebDriver driver;
	Properties prop;
	Logger log;
	
	
	
	@BeforeTest
	public void setup() throws IOException{
		
		prop= new Properties();
		FileInputStream fis= new FileInputStream("C:\\Users\\nmori\\newnew\\NalProject\\src\\main\\java\\config\\config.properties");
		prop.load(fis);
		String browser=prop.getProperty("browser");
		if(browser.equals("chrome")){
		
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\nmori\\Desktop\\ChromeIEFFoxDrivers\\chromedriver.exe");
		driver=new ChromeDriver();
		
		}else if(browser.equals("firefox")){
			
			System.setProperty("webdriver.gecko.driver", "C:\\Users\\nmori\\Desktop\\ChromeIEFFoxDrivers\\geckodriver.exe");
		driver=new FirefoxDriver();
		}
		driver.manage().deleteAllCookies();
		//driver.manage().window().maximize();
		driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.get(prop.getProperty("url"));
		
	}
	@Test(priority=1)
	public void login(){
		
		Logger log = Logger.getLogger(LoginTest.class);
		log.info("**************test1 start*************");
		
		driver.findElement(By.id("email")).sendKeys(prop.getProperty("username"));
		driver.findElement(By.id("pass")).sendKeys(prop.getProperty("password"));
		driver.findElement(By.xpath("//input[@value='Log In']")).click();
		System.out.println("TITLE IS----->"+driver.getTitle());
		
		log.info("**************test1 end*************");
	}
	
	@Test(priority=2)
	public void verifyTitle() throws InterruptedException{
		
		//log.info("**************test2 start*************");
		Thread.sleep(5000);
		
		String title=driver.getTitle();
	
		System.out.println("TITLE IS----->"+title);
		Assert.assertEquals("(76) Facebook", title);
		//Assert.assertTrue(true);
		
		//log.info("**************test2 end*************");
		
	}
	
	@AfterTest
	public void teardown(){
		driver.quit();
		
		
		
	}

}
