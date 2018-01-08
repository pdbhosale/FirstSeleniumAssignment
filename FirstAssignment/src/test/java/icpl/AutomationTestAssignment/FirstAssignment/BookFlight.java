package icpl.AutomationTestAssignment.FirstAssignment;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.Sleeper;
import org.openqa.selenium.support.ui.Wait;
/**\
 * 
 * @author Priyanka Bhosale
  */

public class BookFlight {

	public static void main(String[] args) {
		
		System.out.println("started-----------a");
		System.setProperty("webdriver.gecko.driver", "D://Gecko 32 bit//geckodriver.exe");
		System.out.println("started-----------0");
		WebDriver driver= new FirefoxDriver();
		System.out.println("started-----------1");
		driver.get("https://www.orbitz.com/");
		System.out.println("started-----------2");
	
		sleep(10);
		driver.manage().window().maximize();
		driver.findElement(By.id("tab-flight-tab")).click();

		driver.findElement(By.id("flight-origin")).sendKeys("Mumbai, India (BOM-Chhatrapati Shivaji Intl.)");
		driver.findElement(By.id("flight-destination")).sendKeys("Bengaluru, India (BLR-Kempegowda Intl.)");
		driver.findElement(By.id("flight-departing")).sendKeys("11/15/2017");
		driver.findElement(By.id("flight-returning")).clear();
		driver.findElement(By.id("flight-returning")).sendKeys("11/16/2017");
		driver.findElement(By.id("search-button")).click();
		sleep(5);
		int noOfFlights=driver.findElements(By.xpath("//li[@class='flight-module segment offer-listing']")).size();
		System.out.println("No of flights present are:"+noOfFlights);
	
	}

	public static  void sleep(int sec) {
		try {
			Thread.sleep(sec*1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}
}
