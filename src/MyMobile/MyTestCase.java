package MyMobile;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

import org.openqa.selenium.remote.DesiredCapabilities;
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
	@Test
	public void test1() {
		System.out.println("Hi");
	}

}
