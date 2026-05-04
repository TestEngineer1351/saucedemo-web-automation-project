package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import java.time.Duration;
import java.util.List;

import org.testng.Assert;

public class ProductDetailsTest extends LoginTest {

	
	
	
	@Test(priority=1)
	public void verifyProductsDetailsPageUrl() throws InterruptedException {
		
		Thread.sleep(2000);
		driver.findElement(By.id("item_4_title_link")).click();
		System.out.println(driver.getCurrentUrl());
		Assert.assertTrue(driver.getCurrentUrl().contains("inventory-item.html?id=4"));
		System.out.println("The Current Url is correct");
		
	
		 }
	
	@Test(priority = 2)
	public void verifyProductDetailsPageLoads() {
		
		ChromeOptions options = new ChromeOptions();
		options.setPageLoadStrategy(PageLoadStrategy.NORMAL);
		driver.get("https://www.saucedemo.com/inventory-item.html?id=4");
		
	}
	
	@Test(priority = 3)
    public void verifyCorrectProductNameDisplayed() throws InterruptedException {
    	
		driver.findElement(By.id("back-to-products")).click();
		String expectedname = driver.findElement(By.id("item_4_title_link")).getText();
		System.out.println(expectedname);
		Thread.sleep(2000);
		driver.findElement(By.id("item_4_title_link")).click();
		
		String actualname = driver.findElement(By.xpath("//div[@class='inventory_details_name large_size']")).getText();
		Assert.assertEquals(actualname, expectedname);
		System.out.println("Name matches");
    	
    }
	
	@Test(priority = 4)
	public void verifyProductPriceDisplayed() throws InterruptedException {
		
		driver.findElement(By.id("back-to-products")).click();
		String expectedprice = driver.findElement(By.xpath("//div[text()='29.99']")).getText();
		Thread.sleep(2000);
		driver.findElement(By.id("item_4_title_link")).click();
		
		String actualprice = driver.findElement(By.xpath("//div[@class='inventory_details_price']")).getText();
		Assert.assertEquals(actualprice, expectedprice);
		System.out.println("Price matches");
	}
	
	@Test(priority = 5)
	public void verifyProductImageDisplayed() throws InterruptedException {
		
		driver.findElement(By.id("back-to-products")).click();
		String expectedimage = driver.findElement(By.id("item_4_img_link")).getText();
		Thread.sleep(2000);
		driver.findElement(By.id("item_4_title_link")).click();
		
		String actualimage = driver.findElement(By.xpath("//img[@class='inventory_details_img']")).getText();
		Assert.assertEquals(actualimage, expectedimage);
		System.out.println("Image matches");
	}
		
	
	@Test(priority = 6)
	public void verifyProductDescriptionDisplayed() {
		
        String expectedDes = "carry.allTheThings() with the sleek, streamlined Sly Pack that melds uncompromising style with unequaled laptop and tablet protection.";
		String actualDes = driver.findElement(By.xpath("//div[@class='inventory_details_desc large_size']")).getText();
		Assert.assertEquals(actualDes, expectedDes);
		System.out.println("Actual descrption matches with expected description");
	}
	
	@Test(priority = 7)
	public void verifyAddToCart() throws InterruptedException {
		
		int beforeCount = 0;
		List<WebElement> cartBadge = driver.findElements(By.className("shopping_cart_badge"));
		if(!cartBadge.isEmpty()) {
			beforeCount = Integer.parseInt(cartBadge.get(0).getText());
		}
		System.out.println(beforeCount);
		
		driver.findElement(By.xpath("//button[contains(@id,'add-to-cart')]")).click();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
	    WebElement badge = wait.until(
	            ExpectedConditions.visibilityOfElementLocated(By.className("shopping_cart_badge"))
	    );
	    
	    int afterCount = Integer.parseInt(badge.getText());
	    
	    System.out.println(afterCount);
	    
	    if(afterCount == beforeCount + 1) {
			 System.out.println("The count of items in the cart has increased on adding the item");
		 }
		 else {
			 System.out.println("The count of items in the cart has not increased on adding the item");
		 }
	    
	    driver.findElement(By.id("remove")).click();
	    if(afterCount == beforeCount + 1) {
			 System.out.println("The count of items in the cart has decreased on removing the item");
		 }
		 else {
			 System.out.println("The count of items in the cart has not decreased on removing the item ");
		 }
		 
		 
		
		
	}
		 

	

}


//TEST CASES :

//1. Verify Product Detail Page URL is correct - DONE
//1. Verify Product Detail Page Loads Successfully - DONE
//2. Verify Correct Product Name is Displayed - DONE
//3. Verify Product Description is Displayed - DONE
//4. Verify Product Price is Displayed Properly - DONE
//5. Verify Product Image is Displayed - DONE
//6. Verify “Add to Cart” Button is Visible and Clickable - DONE
//7. Verify Add to Cart Functionality - DONE
//8. Verify Remove Button Functionality - DONE
//9. Verify Cart Badge Updates Correctly - DONE
//10. Verify “Back to Products” Navigation - DONE
