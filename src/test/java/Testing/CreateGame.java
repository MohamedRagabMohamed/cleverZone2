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

import guru.springframework.domain.Course;
import guru.springframework.domain.MCQ_Game;
import guru.springframework.domain.TF_Game;
import guru.springframework.repositories.CourseRepository;
import guru.springframework.repositories.MCQGameRepository;
import guru.springframework.repositories.TFGameRepository;
import guru.springframework.repositories.UserRepository;

public class CreateGame  {
	WebDriver driver ;
	MCQGameRepository mcqGameRepository;
	TFGameRepository tfGameRepository;
	String login="http://localhost:8080/#!/login";
	String teacherProfile="http://localhost:8080/#!/teacher";
	String mcqGameName = "MCQ_Game1";
	String mcqGameDesc = "MCQ_Game";
	String mcqGameImgSrc = "MCQ_Game";
	String tfGameName = "TF_Game1";
	String tfGameDesc = "TF_Game";
	String tfGameImgSrc = "TF_Game";
	@BeforeMethod // execute before each function
	public void Start() throws InterruptedException{
		driver = new ChromeDriver();
		driver.get(login);
        driver.findElement(By.name("username")).sendKeys("T");
        driver.findElement(By.name("password")).sendKeys("P");
        driver.findElement(By.xpath("/html/body/main/div/div/div/div/div[2]/form/fieldset/button")).click();
        Thread.sleep(500);
        driver.findElement(By.xpath("//*[@id=\"page-wrapper\"]/div[2]/div[2]/div/div[1]")).click();
        Thread.sleep(500);
	}
	
	// Create valid MCQ Game 
	@Test(priority = 0)
	public void CreateGameTest1() throws InterruptedException{
		Thread.sleep(500);
		driver.findElement(By.xpath("//*[@id=\"page-wrapper\"]/div[2]/div/div/div[1]/a[3]/span")).click();
		Thread.sleep(500);
		driver.findElement(By.xpath("//*[@id=\"rname\"]")).sendKeys(mcqGameName);
        driver.findElement(By.xpath("//*[@id=\"decription\"]")).sendKeys(mcqGameDesc);
        driver.findElement(By.xpath("//*[@id=\"imgsrc\"]")).sendKeys(mcqGameImgSrc);
        driver.findElement(By.xpath("//*[@id=\"radio1\"]")).click();
        driver.findElement(By.xpath("/html/body/main/div/div/div/div/div[2]/form/fieldset/div[5]/input")).click();
        Thread.sleep(500);
        String currentUrl=driver.getCurrentUrl();
        assertEquals(teacherProfile, currentUrl);
	}
	// Create valid TF Game 
	@Test(priority = 1)
	public void CreateGameTest2() throws InterruptedException{
		Thread.sleep(200);
		driver.findElement(By.xpath("//*[@id=\"page-wrapper\"]/div[2]/div/div/div[1]/a[3]/span")).click();
		Thread.sleep(200);
		driver.findElement(By.xpath("//*[@id=\"rname\"]")).sendKeys(mcqGameName);
        driver.findElement(By.xpath("//*[@id=\"decription\"]")).sendKeys(mcqGameDesc);
        driver.findElement(By.xpath("//*[@id=\"imgsrc\"]")).sendKeys(mcqGameImgSrc);
        driver.findElement(By.xpath("//*[@id=\"radio2\"]")).click();
        driver.findElement(By.xpath("/html/body/main/div/div/div/div/div[2]/form/fieldset/div[5]/input")).click();
        Thread.sleep(200);
        String currentUrl=driver.getCurrentUrl();
        assertEquals(teacherProfile, currentUrl);
	}	
	@AfterMethod // execute after each function
	 public void terminateBrowser() {
		  System.out.println("Closing the browser ...");
		  driver.close();
	 }
}
