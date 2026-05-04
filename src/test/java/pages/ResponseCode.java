package pages;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;
import java.time.Duration;

public class ResponseCode {

	public static void main(String[] args) {
		
		WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(java.time.Duration.ofSeconds(10));
		driver.get("https://www.saucedemo.com/");

        
        driver.findElement(By.id("user-name")).sendKeys("standard_user");
        driver.findElement(By.id("password")).sendKeys("secret_sauce");
        driver.findElement(By.id("login-button")).click();
		
		
		
        List<WebElement> links = driver.findElements(By.tagName("a"));
        System.out.println("The no of links" +links.size());
        
        List<String> urllist = new ArrayList<String>();
        
        for(WebElement e : links) {
        	String url = e.getAttribute("href");
        	
        	if(url!=null && !url.isEmpty() && !url.startsWith("javascript") && !url.equals("#") && !url.contains("#")) {
        	urllist.add(url);
        }
        	
        }
        
        long stTime = System.currentTimeMillis();
        urllist.parallelStream().forEach(ResponseCode::checkBrokenLinks);
        
        long endTime = System.currentTimeMillis();
        System.out.println("Total time taken = " +(endTime - stTime) + " ms");
        
    }

	

@Test
public static void checkBrokenLinks(String linkUrl) {
	 
	
	
	
	 try {
		 
		 URL url = new URL(linkUrl);
		 HttpURLConnection httpUrlConnection = (HttpURLConnection) url.openConnection();
		 httpUrlConnection.setConnectTimeout(5000);
		 httpUrlConnection.connect();
		 
		 int response =  httpUrlConnection.getResponseCode();
		 
		 if(response >= 400) {
			 System.out.println(linkUrl+ "----->" +httpUrlConnection.getResponseMessage() + " is a broken link");
			 
		 }
		 
		 else {
			 System.out.println(linkUrl+ "----->" +httpUrlConnection.getResponseMessage());
		 }
		 
		 
		 
	 }
	 
	 catch(Exception e) {
		 System.out.println("EXCEPTION");
	 }
	 
}

}
