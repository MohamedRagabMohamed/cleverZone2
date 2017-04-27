package Testing;

import static org.junit.Assert.assertNotEquals;
import static org.testng.Assert.assertEquals;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import guru.springframework.domain.User;
import guru.springframework.repositories.UserRepository;

public class SignUpTest {
	
	WebDriver driver ;
	UserRepository userRepository;
	String Signup="http://localhost:8080/#!/sign-up";
	String StudentProfileUrl = "http://localhost:8080/#!/student";
	String TeacherProfileUrl = "http://localhost:8080/#!/teacher";
	String teacherUserName="Teacher";
	String teacherPassword="Teacher";
	String teacherFirstName="Teacher";
	String teacherLastName="Teacher";
	
	String studentUserName="Student";
	String studentrPassword="Student";
	String studentFirstName="Student";
	String studentLastName="Student";
	@BeforeMethod // execute before each test function 
	public void OpenBrowser(){
		driver = new ChromeDriver();
		driver.get(Signup);
	}
	// Test sign up for Teacher
	@Test(priority=0)
	public void SignUpTest1(){
		driver.findElement(By.name("username")).sendKeys(teacherUserName);
		driver.findElement(By.name("firstname")).sendKeys(teacherFirstName);
		driver.findElement(By.name("lastname")).sendKeys(teacherLastName);
		driver.findElement(By.name("password")).sendKeys(teacherPassword);
		driver.findElement(By.xpath("//*[@id=\"radio1\"]")).click();
		driver.findElement(By.xpath("/html/body/main/div/div/div/div/div[2]/form/fieldset/div[6]/input")).click();
		
		String currentPage=driver.getCurrentUrl();
		assertNotEquals(TeacherProfileUrl, currentPage);
	}
	// Test sign up for Student
	@Test(priority=1)
	public void SignUpTest2(){
		driver.findElement(By.name("username")).sendKeys(studentUserName);
		driver.findElement(By.name("firstname")).sendKeys(studentFirstName);
		driver.findElement(By.name("lastname")).sendKeys(studentLastName);
		driver.findElement(By.name("password")).sendKeys(studentrPassword);
		driver.findElement(By.xpath("//*[@id=\"radio2\"]")).click();
		String currentPage=driver.getCurrentUrl();
		assertNotEquals(StudentProfileUrl, currentPage);
	}
	// Test sign up for not completed data
	@Test(priority=2)
	public void SignUpTest3(){
		driver.findElement(By.name("username")).sendKeys("");
		driver.findElement(By.name("firstname")).sendKeys(studentFirstName);
		driver.findElement(By.name("lastname")).sendKeys(studentLastName);
		driver.findElement(By.name("password")).sendKeys(studentrPassword);
		driver.findElement(By.xpath("//*[@id=\"radio2\"]")).click();
		String currentPage=driver.getCurrentUrl();
		assertNotEquals(StudentProfileUrl, currentPage);
	}
	 @AfterMethod // execute after each function
	 public void terminateBrowser() {
		  System.out.println("Closing the browser ...");
		  driver.close();
	 }
}
