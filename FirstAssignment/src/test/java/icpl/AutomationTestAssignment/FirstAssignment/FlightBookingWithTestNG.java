package icpl.AutomationTestAssignment.FirstAssignment;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.filefilter.FalseFileFilter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.ITestContext;
import org.testng.Reporter;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.Listeners;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
/**\
 * 
 * @author Priyanka Bhosale
  */


public class FlightBookingWithTestNG {
	WebDriver driver;
	String browser;
	
	@Test(description="Launches the Flight Booking site")
	public void launchSite(ITestContext context) {
		
	browser = context.getCurrentXmlTest().getParameter("browser");

		if(browser.equals("Chrome"))
		{
			System.setProperty("webdriver.chrome.driver", "D://Gecko 32 bit//chromedriver.exe");
			driver= new ChromeDriver();
		}
		if(browser.equals("Firefox"))
		{
			System.setProperty("webdriver.gecko.driver", "D://Gecko 32 bit//geckodriver.exe");
			driver= new FirefoxDriver();
		}
		driver.get("https://www.orbitz.com/");
		Reporter.log("Orbitz site opned for flight booking");
		
		sleep(5);
		driver.manage().window().maximize();
		driver.findElement(By.id("tab-flight-tab")).click();

	}

	@Test(description="Enters valid data and Search for flight",dependsOnMethods="launchSite")
	public void searchFlight() {
		driver.findElement(By.id("flight-origin")).sendKeys("Mumbai, India (BOM-Chhatrapati Shivaji Intl.)");
		driver.findElement(By.id("flight-destination")).sendKeys("Bengaluru, India (BLR-Kempegowda Intl.)");
		driver.findElement(By.id("flight-departing")).sendKeys("1/8/2018");
		driver.findElement(By.id("flight-returning")).clear();
		driver.findElement(By.id("flight-returning")).sendKeys("1/9/2018");
		driver.findElement(By.id("search-button")).click();
		Reporter.log("Data entered for search flight");
		sleep(5);
	}

	@Test(description="Validate searched flight details",dependsOnMethods="searchFlight")
	public void validateResult() {
		int noOfFlights=driver.findElements(By.xpath("//li[@class='flight-module segment offer-listing']")).size();
		System.out.println(browser+"No of flights present are:"+noOfFlights);	
		Reporter.log("No of flights present are:"+noOfFlights);
	}

	@AfterSuite(enabled= false)
	public void killDriver() {
		driver.close();
		try {
			Runtime.getRuntime().exec("taskkill /F /IM chromeDriver.exe");
			Runtime.getRuntime().exec("taskkill /F /IM IEDriverServer.exe");
			Reporter.log("chromeDriver task killed");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static  void sleep(int sec) {
		try {
			Thread.sleep(sec*1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
