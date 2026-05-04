package pages;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

public class AddToCartTest extends LoginTest {
	
	
	
	
	@Test(priority = 1)
	public void verifyAddToCart() throws InterruptedException {
		
		Thread.sleep(2000);
		
		driver.findElement(By.id("add-to-cart-sauce-labs-backpack")).click();
		Thread.sleep(2000);
		driver.findElement(By.id("add-to-cart-sauce-labs-bike-light")).click();
		Thread.sleep(2000);
		driver.findElement(By.id("add-to-cart-sauce-labs-bolt-t-shirt")).click();
		Thread.sleep(2000);
		driver.findElement(By.id("shopping_cart_container")).click();
		
	}
	
	@Test(priority = 2)
	public void verifyCartPageLoads() {
		
		ChromeOptions options = new ChromeOptions();
		options.setPageLoadStrategy(PageLoadStrategy.NORMAL);
		driver.get("https://www.saucedemo.com/cart.html");
	}
	
	@Test(priority = 3)
	public void verifyAddedProductDisplayedinCart() throws InterruptedException {
		
		Thread.sleep(2000);
		driver.findElement(By.id("continue-shopping")).click();
		String expectedname_1 = driver.findElement(By.id("item_4_title_link")).getText();
		String expectedname_2 = driver.findElement(By.id("item_0_title_link")).getText();
		String expectedname_3 = driver.findElement(By.id("item_1_title_link")).getText();
		Thread.sleep(2000);
		driver.findElement(By.id("shopping_cart_container")).click();
		
		String actualname_1 = driver.findElement(By.id("item_4_title_link")).getText();
		String actualname_2 = driver.findElement(By.id("item_0_title_link")).getText();
		String actualname_3 = driver.findElement(By.id("item_1_title_link")).getText();
		
		Assert.assertEquals(actualname_1, expectedname_1);
		Assert.assertEquals(actualname_2, expectedname_2);
		Assert.assertEquals(actualname_3, expectedname_3);
	}
	
	@Test(priority = 4)
	public void verifyRemoveItemFromCart() throws InterruptedException {
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		WebElement removebutton = wait.until(ExpectedConditions.elementToBeClickable(By.id("remove-sauce-labs-backpack")));
		removebutton.click();
		
	}
	
	@Test(priority = 5)
	public void verifyContinueShopping() {
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		WebElement contshop = wait.until(ExpectedConditions.elementToBeClickable(By.id("continue-shopping")));
		contshop.click();
		
	}
	
	@Test(priority = 6)
	public void verifyCartWithNoItems() throws InterruptedException {
		
		Thread.sleep(2000);
		driver.findElement(By.id("remove-sauce-labs-bike-light")).click();
		Thread.sleep(2000);
		driver.findElement(By.id("remove-sauce-labs-bolt-t-shirt")).click();
		Thread.sleep(2000);
		driver.findElement(By.id("shopping_cart_container")).click();
		
		
		List<WebElement> cartItems = driver.findElements(By.className("cart_item"));
		Assert.assertEquals(cartItems.size(), 0);
		System.out.println("There are no items in the cart");
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		WebElement contshop = wait.until(ExpectedConditions.elementToBeClickable(By.id("continue-shopping")));
		contshop.click();
		
	}
	
	@Test(priority = 7)
	public void verifyCartItemQuantity() throws InterruptedException {
		
		driver.findElement(By.id("add-to-cart-sauce-labs-backpack")).click();
		Thread.sleep(2000);
		driver.findElement(By.id("shopping_cart_container")).click();
		
		List<WebElement> productquantity = driver.findElements(By.xpath("//div[@class='cart_quantity']"));
		Assert.assertEquals(productquantity.size(), 1);
		System.out.println("Quantity of the product in the cart is as expected");
		
	}
	
	@Test(priority = 8)
	public void verifyCartPersistence() throws InterruptedException {
		
		List<WebElement> cartItems_before = driver.findElements(By.className("cart_item"));
		int expectedItems = cartItems_before.size();
		System.out.println(expectedItems);
		
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		WebElement contshop = wait.until(ExpectedConditions.elementToBeClickable(By.id("continue-shopping")));
		contshop.click();
		Thread.sleep(2000);
		driver.findElement(By.id("shopping_cart_container")).click();
		
		List<WebElement> cartItems_after = driver.findElements(By.className("cart_item"));
		int actualItems = cartItems_after.size();
		Assert.assertEquals(actualItems, expectedItems);
		System.out.println("The cart is persistent");
		
		}
	
	@Test(priority = 9)
	public void verifyCheckout() {
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		WebElement checkout = wait.until(ExpectedConditions.elementToBeClickable(By.id("checkout")));
		checkout.click();
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}



// TEST CASES :

//1. Verify Cart Page Loads Correctly - DONE
//2. Verify Added Product Is Displayed in Cart - DONE
//3. Verify Multiple Products Display Correctly - DONE
//4. Verify Remove Button Removes Item from Cart - DONE
//6. Verify Continue Shopping Button Navigation - DONE
//7. Verify Checkout Button Navigation - DONE
//8. Verify Cart Page with No Items - DONE
//9. Verify Cart Item Quantity Display - DONE
//10. Verify Cart Persistence After Navigation - DONE
//11. Verify checkout button - DONE
