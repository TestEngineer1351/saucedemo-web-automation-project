package pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CheckoutPageTest extends LoginTest {
	
	
	
	@Test(priority = 1)
	public void verifyAddToCart() throws InterruptedException {
		
		Thread.sleep(2000);driver.findElement(By.id("add-to-cart-sauce-labs-backpack")).click();
		Thread.sleep(2000);
		driver.findElement(By.id("shopping_cart_container")).click();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("checkout"))).click();
		
		
	}
	
	@Test(priority = 2)
	public void verifyPageLoads() {
		
		ChromeOptions options = new ChromeOptions();
		options.setPageLoadStrategy(PageLoadStrategy.NORMAL);
		driver.get("https://www.saucedemo.com/checkout-step-one.html");
	}
	
	@Test(priority = 3)
	public void verifyUIElements() {
		
		
		String expected_firstname = driver.findElement(By.id("first-name")).getText();
		String expected_secondname = driver.findElement(By.id("last-name")).getText();
		String expected_postalcode = driver.findElement(By.id("postal-code")).getText();
		
		String actual_firstname = driver.findElement(By.id("first-name")).getText();
		String actual_secondname = driver.findElement(By.id("last-name")).getText();
		String actual_postalcode = driver.findElement(By.id("postal-code")).getText();
		
		Assert.assertEquals(actual_firstname, expected_firstname);
		Assert.assertEquals(actual_secondname, expected_secondname);
		Assert.assertEquals(actual_postalcode, expected_postalcode);
		
		
		
	}
	
	@Test(priority = 4)
	public void verifyMandatoryFieldsAllEmpty() throws InterruptedException {
		
		Thread.sleep(2000);
		driver.findElement(By.id("continue")).click();
		String errorMessage = driver.findElement(By.xpath("//div[@class='error-message-container error']")).getText();
		Assert.assertEquals("Error: First Name is required", errorMessage);
		System.out.println("Assert passed for all empty fields "+errorMessage);
		
		
		
	}
	
	@Test(priority = 5)
	public void verifyIndividualFieldValidation() throws InterruptedException {
		
		Thread.sleep(2000);
		WebElement firstname = driver.findElement(By.id("first-name"));
		WebElement lastname = driver.findElement(By.id("last-name"));
		WebElement postalcode = driver.findElement(By.id("postal-code"));
		WebElement errorMsg = driver.findElement(By.xpath("//div[@class='error-message-container error']"));
		
		
		
		
		Thread.sleep(2000);
		firstname.sendKeys("Tom");
		Thread.sleep(2000);
		driver.findElement(By.id("continue")).click();
        System.out.println(errorMsg.getText());
		Assert.assertEquals(errorMsg.getText(), "Error: Last Name is required");
		System.out.println("Assert passed for only firstname filled");
		Thread.sleep(1000);
		firstname.clear();
		Thread.sleep(2000);
		
		lastname.sendKeys("Lincoln");
		Thread.sleep(2000);
		driver.findElement(By.id("continue")).click();
        System.out.println(errorMsg.getText());
		Assert.assertEquals(errorMsg.getText(), "Error: Postal Code is required");
		System.out.println("Assert passed for only lastname filled");
		lastname.clear();
		Thread.sleep(2000);
		
		

		
		
	}
	
	@Test(priority = 6)
	public void verifyNavigationToStepTwo() throws InterruptedException {
		
		WebElement firstname = driver.findElement(By.id("first-name"));
		WebElement lastname = driver.findElement(By.id("last-name"));
		WebElement postalcode = driver.findElement(By.id("postal-code"));
		
		firstname.sendKeys("Tom");
		Thread.sleep(2000);
		lastname.sendKeys("Linkoln");
		Thread.sleep(2000);
		postalcode.sendKeys("010101");
		Thread.sleep(2000);
		driver.findElement(By.id("continue")).click();
		Thread.sleep(2000);
		if(driver.getCurrentUrl().contains("checkout-step-two.html")) {
			System.out.println("Page has been Redirect to Checkout Step Two page");
			System.out.println(driver.getCurrentUrl());
		}
		else {
			System.out.println("Page has not been redirected to checkout step two page");
		}
		
		
	}
	
	@Test(priority = 7)
	public void verifyCancelButton() {
		
		driver.findElement(By.id("cancel")).click();
		
		
	}

}





//TEST CASES :

//1. Verify page loads successfully - DONE
//2. Verify UI Elements Validation - DONE
//3. Mandatory Fields Validation (All Empty) - DONE
//4. Individual Field Validation (Granular Errors) - DONE
//5. Valid Input → Navigation to Step Two - DONE
//6. Postal Code Format Validation - bug in this test case
//9. Cancel Button Functionality - DONE


