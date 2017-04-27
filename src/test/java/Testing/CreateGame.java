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
	String login="F:\\SpringBoot\\cleverZone2-masterLastVersion1\\src\\main\\resources\\static\\pages\\login.html";
	String tmpCreateGame="F:\\SpringBoot\\cleverZone2-masterLastVersion1\\src\\main\\resources\\static\\pages\\create-game.html";
	String mcqGameName = "MCQ_Game";
	String mcqGameDesc = "MCQ_Game";
	String mcqGameImgSrc = "MCQ_Game";
	String tfGameName = "TF_Game";
	String tfGameDesc = "TF_Game";
	String tfGameImgSrc = "TF_Game";
	@BeforeMethod // execute before each function
	public void Start(){
		driver = new ChromeDriver();
		driver.get(tmpCreateGame);
		/*
		// login => profile => my courses => choose course => addGame 
		driver = new ChromeDriver();
        driver.findElement(By.name("username")).sendKeys("T");
        driver.findElement(By.name("password")).sendKeys("P");
        driver.findElement(By.xpath("/html/body/div/div/div/div/div[2]/form/fieldset/button")).click();
        */
        // => go to profile here => my courses button => my courses button => choose course => click on add game <<====
	}
	
	// Create valid MCQ Game 
	@Test(priority = 0)
	public void CreateGameTest1(){
        driver.findElement(By.name("name")).sendKeys(mcqGameName);
        driver.findElement(By.name("decription")).sendKeys(mcqGameDesc);
        driver.findElement(By.name("imgsrc")).sendKeys(mcqGameImgSrc);
        driver.findElement(By.xpath("//*[@id=\"radio1\"]")).click();
        driver.findElement(By.xpath("/html/body/div/div/div/div/div[2]/form/fieldset/div[5]/input")).click();
        MCQ_Game mcqGame = mcqGameRepository.findByname(mcqGameName);
        assertNotEquals(null, mcqGame);
        assertEquals(mcqGameDesc, mcqGame.getImageSrc());
        assertEquals(mcqGameImgSrc, mcqGame.getdescption());
	}
	// Create valid TF Game 
	@Test(priority = 1)
	public void CreateGameTest2(){
        driver.findElement(By.name("name")).sendKeys(tfGameName);
        driver.findElement(By.name("decription")).sendKeys(tfGameDesc);
        driver.findElement(By.name("imgsrc")).sendKeys(tfGameImgSrc);
        driver.findElement(By.xpath("//*[@id=\"radio2\"]")).click();
        driver.findElement(By.xpath("/html/body/div/div/div/div/div[2]/form/fieldset/div[5]/input")).click();
        TF_Game tfGame = tfGameRepository.findByname(mcqGameName);
        assertNotEquals(null, tfGame);
        assertEquals(tfGameDesc, tfGame.getImageSrc());
        assertEquals(tfGameImgSrc, tfGame.getdescption());
	}
	// Create not valid TF Game (not his course)
	@Test(priority = 2)
	public void CreateGameTest3(){
		// try to create a game for another teacher course 
	}
	
	@AfterMethod // execute after each function
	 public void terminateBrowser() {
		  System.out.println("Closing the browser ...");
		  driver.close();
	 }
}
