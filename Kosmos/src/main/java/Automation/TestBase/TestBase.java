package Automation.TestBase;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

public class TestBase {

	public static WebDriver driver;

	@BeforeTest
	public void openBrowser() throws InterruptedException {
		System.setProperty("webdriver.chrome.driver",
				System.getProperty("user.dir") + "/driver/chromedriver.exe");
		driver = new ChromeDriver();
		driver.get("https://www.mypedia.pearson.com/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.switchTo().frame(driver.findElement(By.id("contentIframe")));
		Thread.sleep(5000);
		WebElement done = driver.findElement(By.id("spanDone"));
		done.click();
		driver.switchTo().defaultContent();
	}
	@AfterTest
	public void closeBrowser(){
		driver.close();
	}

}
