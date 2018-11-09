package WalletHub;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Assignment1_FacebookStatus {

	public static void main(String[] args) {
		
		String username="fb_username";
		String password="fb_password";

		//ChromeOptions is used to Handle the Browser Popup notifications
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--disable-notifications");
		System.setProperty("webdriver.chrome.driver","./drivers/chromedriver.exe");
		ChromeDriver driver =new ChromeDriver(options);
		driver.manage().window().maximize();

		//Launching the facebook website and giving credentials	
		driver.get("https://www.facebook.com/");
		driver.findElementByName("email").clear();
		driver.findElement(By.name("email")).sendKeys(username);
		driver.findElement(By.name("pass")).clear();
		driver.findElement(By.name("pass")).sendKeys(password);
		driver.findElementByXPath("//input[@type='submit']").click();

		//WevDriver wait is for Explicitly setting the Wait for specific web elements
		WebDriverWait wait = new WebDriverWait(driver, 500);
		driver.findElementByXPath("//span[text()='Compose Post']").click();

		//Implicit wait is set for the webdriver to wait for sometime.
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

		//Locating the element and inputting the status
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@contenteditable='true']")));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@contenteditable='true']")));
		driver.findElementByXPath("//div[@contenteditable='true']").sendKeys("Hello World");

		//Submit button is clicked after entering the status
		driver.findElementByXPath("//button[@data-testid='react-composer-post-button']").click();

		//Closing the Browser
		driver.close();

	}

}
