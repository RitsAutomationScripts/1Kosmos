package Automation.Functions;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import Automation.TestBase.TestBase;

public class myPediaPageClass extends TestBase {

	public void waitforElement(WebElement element) {
		// WebDriverWait wait = new WebDriverWait(super.getDriver(), 20);
		// wait.until(ExpectedConditions.VisibilityofElementLocated(element));
	}

	public void verifyDD() {
		WebElement dropdown = driver
				.findElement(By
						.xpath("//*[@class='accountDetailsLangDropDown']//div[starts-with(@style,'color')]"));
		dropdown.click();
		List<WebElement> lang = driver.findElements(By
				.xpath("//span[@role='menuitem']/div/div/div"));
		if (lang.size() > 0) {
			if (lang.get(0).getText().contains("English")) {
				System.out.println("English Language option is present");
			} else {
				Assert.fail("English Language option is not preent");
			}
			if (lang.get(1).getText().contains("हिंदी")) {
				System.out.println("हिंदी Language option is present");
			} else {
				Assert.fail("Hindi Language option is not preent");

			}
			if (lang.get(2).getText().contains("Español")) {
				System.out.println("Español Language option is present");
			} else {
				Assert.fail("Español Language option is not preent");
			}
		} else {
			Assert.fail("Language option is not preent =" + lang.size());
			System.out.println("Language option is not preent =" + lang.size());
		}

	}

	public void langChnage() throws InterruptedException {
		List<WebElement> lang = driver.findElements(By
				.xpath("//span[@role='menuitem']/div/div/div"));
		lang.get(1).click();
		WebElement cont = driver.findElement(By
				.xpath("//button[@id='SI_0005']/div/div"));
		Thread.sleep(1000);
		if (cont.getText().equalsIgnoreCase("अग्रसर रहें")) {
			System.out.println("Hindi lang for DD is validated");
		} else {
			Assert.fail("Hindi Language not change for Continue button.");
		}
	}

	public void setupParent() {
		WebElement setup = driver.findElement(By
				.xpath("//div[@class='childSupportLink']/a"));
		waitForWebElementVisible(setup, 10);
		setup.click();

	}

	public void createAccount() throws InterruptedException {

		WebElement create = driver.findElement(By
				.xpath("//*[text()='CREATE A NEW ACCOUNT']"));
		waitForWebElementVisible(create, 10);
		create.click();

	}

	public void fillRegistration() throws InterruptedException, IOException {
		Thread.sleep(5000);
		List<WebElement> inputbox = driver.findElements(By.xpath("//input"));
		waitForWebElementVisible(inputbox.get(0), 10);
		List<String> value = readExcel();
		if (inputbox.size() > 0) {
			inputbox.get(0).sendKeys(value.get(0));
			inputbox.get(1).sendKeys(value.get(1));
			inputbox.get(2).sendKeys(value.get(2));
			inputbox.get(4).sendKeys(value.get(3));
			inputbox.get(5).sendKeys(value.get(4));
			inputbox.get(6).sendKeys(value.get(5));

		}
		WebElement locationDD = driver.findElement(By
				.xpath("(//div[starts-with(@style,'color')])[3]"));
		waitForWebElementVisible(locationDD, 10);
		locationDD.click();

		List<WebElement> location = driver.findElements(By
				.xpath("//span[@role='menuitem']"));
		location.get(1).click();

		WebElement CreateBotton = driver.findElement(By
				.xpath("(//span[starts-with(@style,'position')])[3]"));
		if (CreateBotton.getAttribute("style").contains("opacity: 0.3")) {
			System.out
					.println("Creation button is desabled as Capcha is not filled!");
		} else {
			Assert.fail("Creation button is enabled!");
		}

	}

	public boolean waitForWebElementVisible(WebElement ele, int timeout) {
		try {
			(new WebDriverWait(driver, timeout)).until(ExpectedConditions
					.visibilityOf(ele));
		} catch (Exception e) {

		}
		return true;

	}

	public List<String> readExcel() throws IOException {
		String FILE_PATH = System.getProperty("user.dir") + "/config/data.txt";
		FileReader FR = new FileReader(FILE_PATH);
		BufferedReader BR = new BufferedReader(FR);
		String Content = "";
		String[] values = null;
		List<String> records = new ArrayList<String>();
		// Loop to read all lines one by one from file and print It.
		while ((Content = BR.readLine()) != null) {
			values = Content.split(",");
		}
		records.addAll(Arrays.asList(values));

		return records;

	}
}
