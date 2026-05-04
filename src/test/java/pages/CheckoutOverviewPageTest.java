package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CheckoutOverviewPageTest extends LoginTest{
	
	@Test(priority = 1)
	public void verifyAddToCartAndCheckout() throws InterruptedException {
		
		driver.findElement(By.id("add-to-cart-sauce-labs-backpack")).click();
		Thread.sleep(2000);
		driver.findElement(By.id("shopping_cart_container")).click();
		Thread.sleep(2000);
		driver.findElement(By.id("checkout")).click();
		Thread.sleep(2000);
		driver.findElement(By.id("first-name")).sendKeys("Tom");
		Thread.sleep(2000);
		driver.findElement(By.id("last-name")).sendKeys("Schmidt");
		Thread.sleep(2000);
		driver.findElement(By.id("postal-code")).sendKeys("0000");
		Thread.sleep(2000);
		driver.findElement(By.id("continue")).click();
		
	}
	
	@Test(priority = 2)
	public void verifyCheckoutOverviewPageLoads() {
		
		ChromeOptions option = new ChromeOptions();
		option.setPageLoadStrategy(PageLoadStrategy.NORMAL);
		driver.get("https://www.saucedemo.com/checkout-step-two.html");
	}
	
	@Test(priority = 3)
	public void verifyProductsDisplayedCorrectly() throws InterruptedException {
		
		driver.findElement(By.id("shopping_cart_container")).click();
		Thread.sleep(2000);
		String expected_name = driver.findElement(By.id("item_4_title_link")).getText();
		String expected_price = driver.findElement(By.xpath("//div[text()='29.99']")).getText();
		Thread.sleep(1000);
		driver.navigate().back();
		Thread.sleep(2000);
		String actual_name = driver.findElement(By.id("item_4_title_link")).getText();
		String actual_price = driver.findElement(By.xpath("//div[text()='29.99'][1]")).getText();
		Assert.assertEquals(actual_name, expected_name);
		Assert.assertEquals(actual_price, expected_price);
		System.out.println("The actual item name and price matches with the expected item name and price");
		
	}
	
	@Test(priority = 4)
	public void verifyPriceBreakdown() {
		
		
		
		double item_total = getTotal(driver, "summary_subtotal_label");
		double tax = getTotal(driver, "summary_tax_label");
		double total = getTotal(driver, "summary_total_label");
		
		Assert.assertEquals(item_total + tax, total);
		System.out.println("Actual total matched with the expected total");
		
		
		
		
	}
	
	@Test(priority = 5)
	public void verifyFinishButton() throws InterruptedException {
		
		Thread.sleep(2000);
		driver.findElement(By.id("finish")).click();
	}
	
	
	@Test(priority = 6)
	public void verifyThankyouMessage() throws InterruptedException {
		
		Thread.sleep(2000);
		String expected_confirmation = driver.findElement(By.xpath("//h2[text()='Thank you for your order!']")).getText();
		WebElement actual_confirmation = driver.findElement(By.xpath("//h2[text()='Thank you for your order!']"));
		Assert.assertEquals(expected_confirmation, actual_confirmation.getText());
		System.out.println(actual_confirmation);
		
		String expected_conf_text = "Your order has been dispatched, and will arrive just as fast as the pony can get there!";
		String actual_conf_text = driver.findElement(By.xpath("//div[@class='complete-text']")).getText();
		Assert.assertEquals(expected_conf_text, actual_conf_text);
		System.out.println(actual_conf_text);
	}
	
	@Test(priority = 7)
	public void verifyClickBackHome() {
		driver.findElement(By.id("back-to-products")).click();
	}
	
	private double getTotal(WebDriver driver, String classname) {
		String text = driver.findElement(By.className(classname)).getText();
		return Double.parseDouble(text.replaceAll("[^0-9.]", ""));
	}

}
