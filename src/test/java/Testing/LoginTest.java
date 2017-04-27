package Testing;

import static org.junit.Assert.assertNotEquals;
import static org.testng.Assert.assertEquals;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import guru.springframework.domain.User;
import guru.springframework.repositories.UserRepository;

public class LoginTest {
	WebDriver driver ;
	UserRepository userRepository;
	String login="http://localhost:8080/#!/login";
	String StudentProfileUrl = "http://localhost:8080/#!/student";
	String TeacherProfileUrl = "http://localhost:8080/#!/teacher";
	String trueUserName="S";
	String truePassword="P";
	
	String falseUserName="falseUserName";
	String falsePassword="falsePassword";
	
	@BeforeMethod // execute before each function
	public void OpenBrowser(){
		driver = new ChromeDriver();
		driver.get(login);

	}
	
	// Test True Login
	@Test(priority = 0)
	public void LoginTest1() throws InterruptedException{
        driver.findElement(By.name("username")).sendKeys(trueUserName);
        driver.findElement(By.name("password")).sendKeys(truePassword);
        driver.findElement(By.xpath("/html/body/main/div/div/div/div/div[2]/form/fieldset/button")).click();
        Thread.sleep(30, 0);
        assertEquals(driver.getCurrentUrl(),StudentProfileUrl);
	}
	
	//Test Fail Login
	@Test(priority = 1)
	public void LoginTest2() throws InterruptedException{
        driver.findElement(By.name("username")).sendKeys(falseUserName);
        driver.findElement(By.name("password")).sendKeys(falsePassword);
        driver.findElement(By.xpath("/html/body/main/div/div/div/div/div[2]/form/fieldset/button")).click();
        
        Thread.sleep(30, 0);
        String currentPage=driver.getCurrentUrl();
        assertEquals(currentPage,login );
        // ===>>>> we must check here if there is a wrong message appear or not <<====
	}
	
	 @AfterMethod // execute after each function
	 public void terminateBrowser() {
		  System.out.println("Closing the browser ...");
		  driver.close();
	 }

}
