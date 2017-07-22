
/**
 * Assignment 01.  
 * TC_Gmail_Login_functionality.
 * Version: JDK 8, selenium-java-3.4.0,chromedriver_win32.
 * Browser : Chrome .
 * Version 59.0.3071.115 (Official Build) (32-bit)
 * @author Mahendra Bobade
 * Date: 21-07-2017
 *
 */


package gmailassignment;

import java.io.File;
import java.util.Collections;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import java.io.IOException;
public class loginassignment {
	
	public static void main (String []args) throws InterruptedException, IOException{
		
		WebDriver driver ;
		System.setProperty("webdriver.chrome.driver","D:\\Selenium\\chromedriver_win32\\chromedriver.exe");
	
		String []args1={"-incognito","start-maximized","enable-automation","disable-infobars"};
		String []emailId={"automationnewuser24@gmail.com"};
		ChromeOptions options = new ChromeOptions();
		options.addArguments(args1);
		
		/************************Case 1: Valid Username & Password (Login & Logout)******************************/
	
		DesiredCapabilities capabilities = DesiredCapabilities.chrome();    
		capabilities.setCapability(ChromeOptions.CAPABILITY, options);
		driver = new ChromeDriver(capabilities);
		TakesScreenshot scrShot =((TakesScreenshot)driver);
		
		
		driver.get("https://www.google.com/gmail/about/#");
	//driver.manage().window().maximize();
		Thread.sleep(500);
		// Click on Sign in Link
		driver.findElement(By.xpath("/html/body/nav/div/a[2]")).click();
		
		Thread.sleep(1000);
		
		// Enter the email id 		
	    driver.findElement(By.cssSelector("#identifierId")).sendKeys("automationnewuser24@gmail.com");
		
	    File SrcFile=scrShot.getScreenshotAs(OutputType.FILE);
		
		//Move image file to new destination

        File DestFile=new File("D:\\Selenium\\Screenshots\\UserID.jpg");

        //Copy file at destination

        FileUtils.copyFile(SrcFile, DestFile);
        
        //click on next button 
		driver.findElement(By.xpath(".//*[@id='identifierNext']/div[2]")).click();   
		
		// Enter Password
		driver.findElement(By.xpath("//*[@id='password']/div[1]/div/div[1]/input")).sendKeys("automationnewuser2410"); 

		//Move image file to new destination

		SrcFile=scrShot.getScreenshotAs(OutputType.FILE);
		
         DestFile=new File("D:\\Selenium\\Screenshots\\Password.jpg");

        //Copy file at destination

        FileUtils.copyFile(SrcFile, DestFile);
		
		Thread.sleep(5000);
		
		// Click on next button
		driver.findElement(By.xpath(".//*[@id='passwordNext']/div[2]")).click(); 
		Thread.sleep(5000);
		
		//Move image file to new destination

		SrcFile=scrShot.getScreenshotAs(OutputType.FILE);
        DestFile=new File("data\\Inbox.jpg");

        //Copy file at destination

        FileUtils.copyFile(SrcFile, DestFile);
		
        // Click on profile image	
		driver.findElement(By.xpath(".//*[@id='gb']/div[1]/div[1]/div[2]/div[4]/div[1]/a/span")).click();
		
		Thread.sleep(3000);
		
		// Sign out 
		driver.findElement(By.xpath(".//*[@id='gb_71']")).click();
		driver.quit();
	
		/**********************************Case 2: Invalid Username***********************************/
		
		driver = new ChromeDriver(capabilities);
		
		driver.get("https://www.google.com/gmail/about/#");
		//driver.manage().window().maximize();
		Thread.sleep(500);
		
		driver.findElement(By.xpath("/html/body/nav/div/a[2]")).click(); // Sign in link
		
		Thread.sleep(3000);
		
		WebElement Username = driver.findElement(By.cssSelector("#identifierId"));

		Username.sendKeys("InvalidUser_UVW");

		driver.findElement(By.xpath("N ext_Button")).click();

		Thread.sleep(3000);

		List<WebElement> ErrMsg = driver.findElements(By.xpath(" \\*[contains (text(),'Couldn't find your Google Account')]"));

		String Output = "";
		
		if(ErrMsg.size() > 0){
		   Output = "Passed";
		}else{
		   Output = "Failed";
		}

		driver.quit();
		
		/**********************************Case 3: Invalid Password***********************************/
		
		driver = new ChromeDriver(capabilities);
		
		driver.get("https://www.google.com/gmail/about/#");
		//driver.manage().window().maximize();
		Thread.sleep(500);
		
		driver.findElement(By.xpath("//*[@data-g-label='Sign in']")).click(); // Sign in link
		
		Thread.sleep(3000);
		
		Username = driver.findElement(By.cssSelector("#identifierId"));

		Username.sendKeys("automationnewuser24@gmail.com");
		
		driver.findElement(By.xpath("Next_Button")).click();

		Thread.sleep(3000);
		
		WebElement Password = driver.findElement(By.xpath("//*[@id='password']/div[1]/div/div[1]/input"));
		
		Password.sendKeys("InvalidPwd");
		
		driver.findElement(By.xpath(".//*[@id='passwordNext']/div[2]")).click(); 
		Thread.sleep(5000);
			
		ErrMsg = driver.findElements(By.xpath(" \\*[contains (text(),'Wrong password. Try again.')]"));

		String Output2 = "";
		
		if(ErrMsg.size() > 0){
		   Output2 = "Passed";
		}else{
		   Output2 = "Failed";
		}

		driver.quit();
}

}
