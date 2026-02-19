package demo;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;
import org.openqa.selenium.support.locators.RelativeLocator;


public class tabactivity {

	//@Test
    public void testtab() throws InterruptedException {
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();

        driver.get("https://the-internet.herokuapp.com");  // put your actual URL here
        String parent = driver.getWindowHandle();
        System.out.println("--->"+parent);

        // Open a new tab
        driver.switchTo().newWindow(WindowType.TAB);
        driver.get("https://the-internet.herokuapp.com/login");  // put your login URL here
        
        String child = driver.getWindowHandle();
        System.out.println("--->"+child);

        System.out.println("New tab title: " + driver.getTitle());

        // Switch back to parent window
        driver.switchTo().window(parent);
        System.out.println("Back to parent title: " + driver.getTitle());

        Thread.sleep(3000);
        driver.quit();
    }
	
	//@Test
	public void relativelocators() throws InterruptedException {
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();

        driver.get("https://the-internet.herokuapp.com/login");
        
        WebElement userl=driver.findElement(By.cssSelector("label[for='username']"));
        WebElement passl=driver.findElement(By.cssSelector("label[for='password']"));
        
        WebElement user = driver.findElement(RelativeLocator.with(By.tagName("input")).below(userl));
        WebElement pass = driver.findElement(RelativeLocator.with(By.tagName("input")).below(passl));

        
        user.sendKeys("tomsmith");
        pass.sendKeys("SuperSecretPassword!");
        
        driver.findElement(By.cssSelector("button[type='submit']")).click();
        Thread.sleep(3000);
        
        driver.quit();
		
	}
}
