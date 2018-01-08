package icpl.AutomationTestAssignment.FirstAssignment;

import java.io.IOException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Reporter;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

/**\
 * 
 * @author Priyanka Bhosale
  */


public class FlightBookingAdvance {
	WebDriver driver;
	

	@DataProvider(name="TestData")
	public static Object[][] getDataFromDataprovider(){
		return new Object[][] {  { "Mumbai, India (BOM-Chhatrapati Shivaji Intl.)","Bengaluru, India (BLR-Kempegowda Intl.)","12/22/2017","12/23/2017"}};

	};  


	@Parameters({ "browser","url" })
	@BeforeTest(description="Launches the Flight Booking site")
	public void launchSite(String browser,String url) {
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
		driver.get(url);
		Reporter.log("Orbitz site opned for flight booking");
		sleep(1);
		driver.manage().window().maximize();


	}

	@Test(description="Enters valid data and Search for flight",dataProvider="TestData")
	public void searchFlight(String origin,String destination,String departureDate,String returnDate) {
		driver.findElement(By.id("tab-flight-tab")).click();
		driver.findElement(By.id("flight-origin")).sendKeys(origin);
		driver.findElement(By.id("flight-destination")).sendKeys(destination);
		driver.findElement(By.id("flight-departing")).sendKeys(departureDate);
		driver.findElement(By.id("flight-returning")).clear();
		driver.findElement(By.id("flight-returning")).sendKeys(returnDate);
		driver.findElement(By.id("search-button")).click();
		Reporter.log("Data entered for search flight");
		sleep(5);
	}

	@Test(description="Validate searched flight details",dependsOnMethods="searchFlight")
	public void validateResult() {
		int noOfFlights=driver.findElements(By.xpath("//li[@class='flight-module segment offer-listing']")).size();
		Reporter.log("No of flights present are:"+noOfFlights);
	}

	@AfterSuite()
	public void killDriver() {
		driver.quit();
		try {
			Runtime.getRuntime().exec("taskkill /F /IM chromeDriver.exe");
			Runtime.getRuntime().exec("taskkill /F /IM geckoDriver.exe");
			Reporter.log("chromeDriver task killed");
		} catch (IOException e) {
			Reporter.log("Exception in task kill"+e.getMessage());
			e.printStackTrace();
		}
	}

	public static  void sleep(int sec) {
		try {
			Thread.sleep(sec*1000);
		} catch (InterruptedException e) {
			Reporter.log("Exception in waiting"+e.getMessage());
			e.printStackTrace();
		}

	}
}
