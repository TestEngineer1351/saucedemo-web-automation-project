package pages;

// https://chatgpt.com/c/690f248f-646c-8331-8cb9-d394cb6fcc58




import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import java.time.Duration;

public class LoginTest extends BaseTest {
	
	
//		
//
//		obj.setUp();
//		obj.InvalidLogin();
//		
//		obj.setUp();
//		obj.ValidUserInvalidPwd();
//		
//		obj.setUp();
//		obj.InvalidUserValidPwd();
		
//		obj.setUp();
//		obj.emptyusername();
//		
//		obj.setUp();
//		obj.emptypassword();
//		
//		obj.setUp();
//		obj.emptyusernamepassword();
		
		//obj.tearDown();
	

//    WebDriver driver;

//    @BeforeEach
//    public void setUp() {
//        WebDriverManager.chromedriver().setup();
//        driver = new ChromeDriver();
//
//        driver.manage().window().maximize();
//        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
//        driver.get("https://www.saucedemo.com/");
//        
//        
//    }
    
	 @BeforeClass
	    public void validLogin() throws InterruptedException {

	        driver.findElement(By.id("user-name"))
	              .sendKeys("standard_user");

	        driver.findElement(By.id("password"))
	              .sendKeys("secret_sauce");

	        driver.findElement(By.id("login-button"))
	              .click();

	        Thread.sleep(2000);
	        System.out.println(driver.getTitle());
	    }
	
    
 //   @Test
    public void invalidLogin() {
    	
    	driver.findElement(By.id("user-name")).sendKeys("invalid_username");
    	driver.findElement(By.id("password")).sendKeys("invalid_password");
    	driver.findElement(By.id("login-button")).click();
    	String error_message = driver.findElement(By.xpath("//h3[text()='Epic sadface: Username and password do not match any user in this service']")).getText();
    	System.out.println(error_message);
    }
    
 //   @Test
    public void ValidUserInvalidPwd() {
    	
    	driver.findElement(By.id("user-name")).sendKeys("standard_user");
    	driver.findElement(By.id("password")).sendKeys("invalid_password");
    	driver.findElement(By.id("login-button")).click();
    	String error_message = driver.findElement(By.xpath("//h3[text()='Epic sadface: Username and password do not match any user in this service']")).getText();
    	System.out.println(error_message);
    	
    }
    
 //   @Test
    public void InvalidUserValidPwd() {
    	
    	driver.findElement(By.id("user-name")).sendKeys("invalid_username");
    	driver.findElement(By.id("password")).sendKeys("secret_sauce");
    	driver.findElement(By.id("login-button")).click();
    	String error_message = driver.findElement(By.xpath("//h3[text()='Epic sadface: Username and password do not match any user in this service']")).getText();
    	System.out.println(error_message);
    	
    }
    
 //   @Test
    public void emptyusername() {
    	
    	
    	driver.findElement(By.id("password")).sendKeys("secret_sauce");
    	driver.findElement(By.id("login-button")).click();
    	String error_message = driver.findElement(By.xpath("//h3[text()='Epic sadface: Username and password do not match any user in this service']")).getText();
    	System.out.println(error_message);
    	
    }
    
//    @Test
    public void emptypassword() {
    	
    	
    	driver.findElement(By.id("user-name")).sendKeys("standard_user");
    	driver.findElement(By.id("login-button")).click();
    	String error_message = driver.findElement(By.xpath("//h3[text()='Epic sadface: Username and password do not match any user in this service']")).getText();
    	System.out.println(error_message);
    	
    }
    
 //   @Test
    public void emptyusernamepassword() {
    	
    	
    	
    	driver.findElement(By.id("login-button")).click();
    	String error_message = driver.findElement(By.xpath("//h3[text()='Epic sadface: Username and password do not match any user in this service']")).getText();
    	System.out.println(error_message);
    	
    }
    
    

//    @AfterEach
//    public void tearDown() {
//        if (driver != null) {
//            driver.quit();
//        }
//    }
}




//TEST CASES AUTOMATED:

//Login with valid username and valid password  
//
//Login with valid username and invalid password 
//
//Login with invalid username and valid password 
//
//Login with invalid username and invalid password  
//
//Login with empty username and password 
//
//Login with empty username and valid password 
// 
//Login with valid username and empty password 
//
//Verify error message is displayed for failed login 
//
//Verify user is redirected to Products page after successful login 






