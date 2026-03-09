package MyMobile;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.Date;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.appium.java_client.android.AndroidDriver;

public class MyTestCase {
	AndroidDriver driver;
	DesiredCapabilities caps=new DesiredCapabilities();
	
	@BeforeTest
	public void MySetup() {
		caps.setCapability("platformName", "Android");
		caps.setCapability("appium:deviceName", "abc");
		
		File myapplication=new File("src/myapplication/calculator.apk");
		caps.setCapability("appium:app", myapplication.getAbsolutePath());
	}
	
	@BeforeMethod
	public void helloBefore() throws MalformedURLException {
		driver=new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"),caps);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
	}
	@Test(enabled = false)
	public void test1() {
		WebElement number7 = driver.findElement(By.id("com.google.android.calculator:id/digit_7"));
		number7.click();
		driver.findElement(By.id("com.google.android.calculator:id/op_mul")).click();

		driver.findElement(By.id("com.google.android.calculator:id/digit_8")).click();
	}

	@Test(enabled = false)
	public void PressonlyOnNumbers() {

		List<WebElement> AllButtons = driver.findElements(By.className("android.widget.ImageButton"));

		for (int i = 0; i < AllButtons.size(); i++) {

			if (AllButtons.get(i).getDomAttribute("resource-id").contains("0")
					|| AllButtons.get(i).getDomAttribute("resource-id").contains("2")
					|| AllButtons.get(i).getDomAttribute("resource-id").contains("4")
					|| AllButtons.get(i).getDomAttribute("resource-id").contains("6")
					|| AllButtons.get(i).getDomAttribute("resource-id").contains("8")) {
				AllButtons.get(i).click();
			}

		}
		String Actual = driver.findElement(By.id("com.google.android.calculator:id/formula")).getText(); 
		
		Assert.assertEquals(Actual, "84620");

	}
	
	@Test
	public void PressonlyOnOddNumbers() throws IOException {

		List<WebElement> AllButtons = driver.findElements(By.className("android.widget.ImageButton"));

		for (int i = 0; i < AllButtons.size(); i++) {

			if (AllButtons.get(i).getDomAttribute("resource-id").contains("1")
					|| AllButtons.get(i).getDomAttribute("resource-id").contains("3")
					|| AllButtons.get(i).getDomAttribute("resource-id").contains("5")
					|| AllButtons.get(i).getDomAttribute("resource-id").contains("7")
					|| AllButtons.get(i).getDomAttribute("resource-id").contains("9")) {
				AllButtons.get(i).click();
			}

		}
		String Actual = driver.findElement(By.id("com.google.android.calculator:id/formula")).getText(); 
		
		Assert.assertEquals(Actual, "79513");
		
		

		Date mydate = new Date();

		String finleNameee = mydate.toString().replace(":", "-");
		System.out.println();

		TakesScreenshot ts = (TakesScreenshot) driver;

		File file = ts.getScreenshotAs(OutputType.FILE);

		FileUtils.copyFile(file, new File("./screenshot/" + finleNameee + ".jpg"));

	}


}
