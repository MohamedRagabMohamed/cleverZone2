package Testing;

import static org.junit.Assert.assertNotEquals;
import static org.testng.Assert.assertEquals;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import guru.springframework.domain.Course;
import guru.springframework.repositories.CourseRepository;
import guru.springframework.repositories.UserRepository;

public class CreateCourse {
	WebDriver driver ;
	String login="http://localhost:8080/#!/login";
	String teacherProfile="http://localhost:8080/#!/teacher";
	String courseName = "Course111111";
	String courseDesc = "Course1111111";
	String courseImgSrc = "Course1111111";
	@BeforeMethod // execute before each function
	public void Start(){
		driver = new ChromeDriver();
		driver.get(login);
        driver.findElement(By.name("username")).sendKeys("T");
        driver.findElement(By.name("password")).sendKeys("P");
        driver.findElement(By.xpath("/html/body/main/div/div/div/div/div[2]/form/fieldset/button")).click();
	}
	
	// Create valid Course 
	@Test(priority = 0)
	public void CreateCourseTest1() throws InterruptedException{
		Thread.sleep(200);
		driver.findElement(By.xpath("//*[@id=\"page-wrapper\"]/div[1]/div/h1/a/span")).click();
        driver.findElement(By.xpath("//*[@id=\"name\"]")).sendKeys(courseName);
        driver.findElement(By.xpath("//*[@id=\"decription\"]")).sendKeys(courseDesc);
        driver.findElement(By.xpath("//*[@id=\"imgsrc\"]")).sendKeys(courseImgSrc);
        driver.findElement(By.xpath("/html/body/main/div/div/div/div/div[2]/form/fieldset/div[4]/input")).click();
        Thread.sleep(200);
        String currentUrl=driver.getCurrentUrl();
        assertNotEquals(teacherProfile, currentUrl);
	}
	
	//Create a new Course using the same course name of the previous test .
	@Test(priority = 1)
	public void CreateCourseTest2() throws InterruptedException{
		Thread.sleep(200);
        driver.findElement(By.name("name")).sendKeys(courseName);
        driver.findElement(By.name("decription")).sendKeys(courseDesc);
        driver.findElement(By.name("imgsrc")).sendKeys(courseImgSrc);
        driver.findElement(By.xpath("/html/body/div/div/div/div/div[2]/form/fieldset/div[4]/input")).click();
        Thread.sleep(200);
        String currentUrl=driver.getCurrentUrl();
        assertNotEquals(teacherProfile, currentUrl);
	}
	
	
	@AfterMethod // execute after each function
	 public void terminateBrowser() {
		  System.out.println("Closing the browser ...");
		  driver.close();
	 }
}
