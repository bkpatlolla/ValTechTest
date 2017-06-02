import java.util.List;
import java.util.concurrent.TimeUnit;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;


public class ValtechHackerTest {
	
	static WebDriver driver = null;
	
	@BeforeClass
	public static void beforeClass(){
		System.setProperty("webdriver.gecko.driver",
				System.getProperty("user.dir")+"\\geckodriver.exe");
		driver = new FirefoxDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
	}
	
	@Before
	public void beforeTest(){
		driver.get("http://valtech.com");
	}

	@Test
	public void latestNewsTest(){
		WebElement elem = driver.findElement(By.xpath("//div[@class='news-post__listing-header']/.."));
		Assert.assertTrue(elem.isDisplayed());
		WebElement elem2 = driver.findElement(By.xpath("//div[@class='news-post__listing-header']//h2"));
		Assert.assertEquals("LATEST NEWS", elem2.getText());
	}
	
	@Test
	public void aboutPageTest(){
		//About
		driver.findElement(By.cssSelector("div#navigationMenuWrapper a[href*='about']")).click();
		Assert.assertEquals(driver.findElement(By.cssSelector("div.page-header>h1")).getText(), "About");
	}
	@Test
	public void workPageTest(){
		//work
		driver.findElement(By.cssSelector("div#navigationMenuWrapper a[href*='cases']")).click();
		Assert.assertEquals(driver.findElement(By.cssSelector("header.page-header>h1")).getText(), "Work");
	}
	@Test
	public void servicesPageTest(){
		//Services
		driver.findElement(By.cssSelector("div#navigationMenuWrapper a[href*='services']")).click();
		Assert.assertEquals(driver.findElement(By.cssSelector("section>header.page-header>h1")).getText(), "Services");
	}
	@Test
	public void contactOfficesTest(){
		driver.findElement(By.cssSelector("a#contacticon")).click();
		List<WebElement> allOffices = driver.findElements(By.cssSelector("div.contactcountry>h3"));
		System.out.println("Total Number off Offices are: "+allOffices.size());
		for(int i=0;i<allOffices.size();i++){
			System.out.println("Office "+(i+1)+" Name Is:"+allOffices.get(i).getText());
		}
	}
		
	
	@AfterClass
	public static void afterClass(){
		driver.quit();
	}
}