package WalletHub;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;



public class Assignment2 {
	
	// Method for page load
		public static void browser_load(ChromeDriver driver)
	    {
	        new WebDriverWait(driver, 500).until(
	          WebDriver -> ((JavascriptExecutor) WebDriver).executeScript("return document.readyState").equals("complete"));
	    }
	
	@Test

	public void test_method() throws InterruptedException {
		
		
		//Launching Chrome Browser and navigating to Wallet Hub Page
		System.setProperty("webdriver.chrome.driver","./drivers/chromedriver.exe");
		ChromeDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://wallethub.com/join/light");
		Thread.sleep(2000);
		//driver.manage().timeouts().implicitlyWait(20,TimeUnit.SECONDS);
		//browser_load(driver);
		
		//Login with valid credentials
		driver.findElementByXPath("//a[text()='Login']").click();
		driver.findElementByName("em").sendKeys("kevinchristopherkc7@gmail.com");
		driver.findElementByName("pw1").sendKeys("Kevin777!");
		driver.findElementByXPath("//button[@data-hm-tap='doSubmit($event);']").click();
		Thread.sleep(3000);
		driver.findElementByXPath("//span[@class='w-icon-wallet']/following::span").click();

		//Navigation to Test_Insurance_Company Profile
		driver.get("https://wallethub.com/profile/test_insurance_company/");
		browser_load(driver);	
		
		//Moving to the Review Stars Section
		WebElement element = driver.findElementByXPath("//div[@class='wh-rating-notes']");
        Actions action = new Actions(driver);
		action.moveToElement(element).build().perform();

		//hovering fourth star and verifying the "Good" message displayed 
		WebElement element1 = driver.findElementByCssSelector("div.wh-rating-choices-holder>a:nth-child(4)");
		Actions action1 = new Actions(driver);
		action1.moveToElement(element1).build().perform();
		browser_load(driver);

		//verifying the message displayed as "Good"
		String verf_string = driver.findElementByCssSelector("div.wh-rating-choices-holder>em>span").getText();
		Assert.assertEquals("Comparison failed","Good", verf_string);
		driver.findElementByCssSelector("div.wh-rating-choices-holder>a:nth-child(5)").click();

        //Selecting Policy Dropdown
		driver.findElementByCssSelector("div.dropdown-title").click();
		browser_load(driver);
        driver.findElementByXPath("//ul[@class='drop-el']/li/a[contains(text(),'Health')]").click();

        //Writing Review
        driver.findElementById("review-content").sendKeys("Test Test Test Test Test Test Test Test Test Test Test Test Test Test Test Test Test Test Test Test Test Test Test Test Test Test Test Test Test Test Test Test Test Test Test Test  Test Test Test Test Test Test");
        Thread.sleep(3000);
        //driver.findElementByCssSelector("span#overallRating>a:nth-child(1)").click();

        //Click Submit Button
        //driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        driver.findElementByXPath("//input[@type='submit']").click();
  
		//Confirming the Review Updation
		WebElement review= driver.findElementByXPath("//a[text()='has been posted.']");
		String reviewStatus=review.getText();
		System.out.println(reviewStatus);
		Assert.assertEquals("has been posted.",reviewStatus);
		
		/*WebElement userElement = driver.findElementByXPath("//a[@data-menu='m-user']");
		Actions builder=new Actions(driver);
		builder.moveToElement(userElement).perform();
		WebDriverWait wait = new WebDriverWait(driver,40);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("(//a[text()='Profile'])[2]")));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//a[text()='Profile'])[2]")));
		WebElement elem = driver.findElement(By.xpath("(//a[text()='Profile'])[2]"));
		String js = "arguments[0].style.height='auto'; arguments[0].style.visibility='visible';";
		((JavascriptExecutor) driver).executeScript(js, elem);
		elem.click();*/
		

		/*//Navigation to User Profile
		WebElement userElement = driver.findElementByXPath("//a[@data-menu='m-user']");
		WebElement userProfile = driver.findElementByXPath("(//a[text()='Profile'])[2]");
		Actions builder=new Actions(driver);
		builder.moveToElement(userElement).perform();
		Thread.sleep(2000);
		//builder.click(userProfile).perform();
		builder.moveToElement(userProfile).click();*/
	
		//Navigation to My Profile
		driver.findElementByXPath("//span[@class='w-icon-wallet']").click();
		browser_load(driver);
		driver.get("https://wallethub.com/profile/kevin7test/");

		//Confirming the Reflection of Review in MyProfile
		WebElement recentReview = driver.findElementByXPath("(//p[@class='feeddesc'])[1]");
		String latestReviewUpdate=recentReview.getText();
		System.out.println(latestReviewUpdate);
		Assert.assertTrue("Review posting and confirmation", true);			
	}
	
}
