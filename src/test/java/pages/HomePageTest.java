package pages;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import java.time.Duration;
import java.util.List;

import org.testng.Assert;


public class HomePageTest extends LoginTest {

	
		

	
	
	
        

	
	@Test(priority = 1)
    public void verifyHomePageTitle() {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        WebElement title = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.className("title"))
        );

        Assert.assertEquals(title.getText(), "Products");
    }

	 
     @Test(priority = 2)
	 public void verifyProductsDisplayed() {
		 
		
		 WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

	        wait.until(ExpectedConditions.visibilityOfElementLocated(
	                By.className("inventory_list")
	        ));

	        List<WebElement> products =
	                driver.findElements(By.className("inventory_item"));

	        Assert.assertEquals(products.size(), 6);
	        
	        for(WebElement pro : products) {
	        	
	        	WebElement image = driver.findElement(By.xpath(".//img"));
	        	WebElement name = driver.findElement(By.xpath(".//div[@class='inventory_item_name ']"));
	        	WebElement price = driver.findElement(By.xpath(".//div[@class='inventory_item_price']"));
	        	
	        	Assert.assertTrue(image.isDisplayed(), "Product image is not dispayed");
	        	Assert.assertTrue(name.isDisplayed(), "Product name is not displayed");
	        	Assert.assertTrue(price.isDisplayed(), "Product price is not displayes");
	        	
	        }
	        
	        	 }
	 
	 @Test(priority = 3)
	    public void verifyClickingProductNavigatesToDetailsPage() {
	        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

	        WebElement productName = wait.until(
	                ExpectedConditions.elementToBeClickable(
	                        By.xpath("//div[text()='Sauce Labs Backpack']")
	                )
	        );
	        
	        productName.click();

	        WebElement productTitle = wait.until(
	                ExpectedConditions.visibilityOfElementLocated(
	                        By.className("inventory_details_name")
	                )
	        );

	        Assert.assertEquals(productTitle.getText(), "Sauce Labs Backpack");
	    }
			 
			 
		
		     
		
		 
	 
	 
	 
	 @Test(priority = 4)
	 public void AddToCart() throws InterruptedException {
		 
		 WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		 WebElement backtoproducts = wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.id("back-to-products"))));
		 backtoproducts.click();
		
		 driver.findElement(By.id("add-to-cart-sauce-labs-backpack")).click();
		 Thread.sleep(2000);
		 driver.findElement(By.id("remove-sauce-labs-backpack")).click();
		 Thread.sleep(2000);
		
		 
		 }
	 
	 @Test(priority = 5)
		public void verifyAddToCartIncrease() throws InterruptedException {
		 
		
		 
		 
		 int beforeCount = 0;
		 if(driver.findElements(By.className("shopping_cart_badge")).size() > 0) {
			 beforeCount = Integer.parseInt(driver.findElement(By.className("shopping_cart_badge")).getText());
		 }
		 
		 System.out.println("Before Count : " +beforeCount);
		 
		 driver.findElement(By.id("add-to-cart-sauce-labs-backpack")).click();
		 Thread.sleep(2000);
		 driver.findElement(By.id("add-to-cart-sauce-labs-bolt-t-shirt")).click();
		 
		 int afterCount = Integer.parseInt(driver.findElement(By.className("shopping_cart_badge")).getText());
		 System.out.println("After Count : " +afterCount);
		 
		 if(afterCount == beforeCount + 1) {
			 System.out.println("The count of items in the cart has increased on adding more than one item");
		 }
		 else {
			 System.out.println("The count of items in the cart has not increased on adding more than one item");
		 }
		 
		 
			
			
		}
		
		@Test(priority = 6)
		public void verifyAddToCartDecrease() throws InterruptedException {
			
			
			Thread.sleep(2000);
			driver.findElement(By.id("add-to-cart-sauce-labs-bike-light")).click();
			Thread.sleep(2000);
			driver.findElement(By.id("add-to-cart-sauce-labs-fleece-jacket")).click();
			
			int beforeCount = Integer.parseInt(driver.findElement(By.className("shopping_cart_badge")).getText());
			 System.out.println("Before item removed Count is  : " +beforeCount);
			 
			Thread.sleep(2000);
			driver.findElement(By.id("remove-sauce-labs-bike-light")).click();
			
			int afterCount = Integer.parseInt(driver.findElement(By.className("shopping_cart_badge")).getText());
			System.out.println("After item removed Count is : " +afterCount);
			
			if(afterCount == beforeCount - 1) {
				System.out.println("The count of item is decreased ");
			}
			else {
				System.out.println("The count of item didnot decrease");
			}
			
			
			
			
		}
		
		@Test(priority = 7)
		public void verifysortingproductsbyName() throws InterruptedException {
			
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
			
			String values[] = {"za", "az", "lohi", "hilo"};
			
			for(String value : values) {
				
				WebElement dropdown = wait.until(
		                ExpectedConditions.elementToBeClickable(By.className("product_sort_container")));
		        
				
				Select select = new Select(dropdown);
				System.out.println("successfully verified");
			}
			
			
			
			
			
			
			
			
			
			
			
		}
		
		@Test(priority = 8)
		public void verifyHeaderElements() throws InterruptedException {
			
			WebElement logo = driver.findElement(By.className("app_logo"));
			Assert.assertTrue(logo.isDisplayed());
			System.out.println("Logo is displayed successfully!");
			
			Thread.sleep(2000);
			driver.findElement(By.id("react-burger-menu-btn")).click();
			Thread.sleep(2000);
			WebElement logout = driver.findElement(By.id("logout_sidebar_link"));
			logout.click();
			driver.findElement(By.id("user-name"))
            .sendKeys("standard_user");
            driver.findElement(By.id("password"))
            .sendKeys("secret_sauce");
            driver.findElement(By.id("login-button"))
            .click();
            System.out.println("User can successfully logout and login again !");


			Thread.sleep(2000);
			driver.findElement(By.id("react-burger-menu-btn")).click();
			Thread.sleep(2000);
			String menu = driver.findElement(By.xpath("//div[@class='bm-menu']")).getText();
			System.out.println("The texts inside the menubar are : "+menu);
			Assert.assertTrue(!menu.isEmpty());
			System.out.println("Menu bar is not empty");
			
			WebElement about = driver.findElement(By.id("about_sidebar_link"));
			about.click();
            String expectedtitle = "Sauce Labs: Cross Browser Testing, Selenium Testing & Mobile Testing";
			String actualtitle = driver.getTitle();
			Assert.assertEquals(actualtitle, expectedtitle);
			System.out.println("About section is getting clicked and opens new page");
			
			
			
			
			
			
		}
	 
	 
	 

		 
		 
	 }
	
	
	
	





//TEST CASES :
//Verify Inventory page loads successfully after valid login  
//
////
//
//Verify all products are displayed on the Inventory page 
//
////
//
//Verify each product displays name, price, description, and image 
//
////
//
//Verify clicking on a product name navigates to the product details page 
//
////
//
//Verify "Add to Cart" button adds the product to the cart 
//
////
//
//Verify cart icon count increases when a product is added 
//
////
//
//Verify "Remove" button removes the product from the cart 
//
////
//
//Verify cart icon count decreases when a product is removed 
//
////
//
//Verify sorting products by Name (Z to A) 
//
////
//
//Verify sorting products by Price (Low to High) 
