package demo;

import java.time.Duration;
import java.util.Set;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.Cookie;  
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class CookieDemo {

    private WebDriver driver;

    @BeforeMethod
    public void setup() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
    }

    @Test
    public void addAndDeleteCookies_demo() {
        driver.get("https://the-internet.herokuapp.com/");

        // add a cookie
        Cookie myCookie = new Cookie("trainerCookie", "Mohan123");
        driver.manage().addCookie(myCookie);

        // verify cookie is added
        Cookie fetched = driver.manage().getCookieNamed("trainerCookie");
        System.out.println("Added cookie: " + fetched);
        
        System.out.println("Cookie Name: " + fetched.getName()); 
        System.out.println("Cookie Path: " + fetched.getPath());
        System.out.println("Cookie Path: " + fetched.getExpiry());
        System.out.println("Cookie Path: " + fetched.getDomain());
        System.out.println("Cookie Name: " + fetched.getSameSite()); 
        System.out.println("Cookie Path: " + fetched.isHttpOnly());
        System.out.println("Cookie Path: " + fetched.getValue());
        System.out.println("Cookie Path: " + fetched.isSecure());
        

        Assert.assertNotNull(fetched, "Cookie was NOT added!");
        Assert.assertEquals(fetched.getValue(), "Mohan123", "Cookie value mismatch!");

        System.out.println("Added cookie: " + fetched);

        // print cookies
        Set<Cookie> all = driver.manage().getCookies();
        System.out.println("Total cookies now: " + all.size());
        for (Cookie c : all) {
            System.out.println("Cookie -> " + c.getName() + "=" + c.getValue());
        }

        // delete cookie
        driver.manage().deleteCookieNamed("trainerCookie");

        Cookie afterDelete = driver.manage().getCookieNamed("trainerCookie");
        Assert.assertNull(afterDelete, "Cookie was NOT deleted!");

        System.out.println("Deleted cookie trainerCookie");

        // delete all cookies
        driver.manage().deleteAllCookies();

//        Assert.assertEquals(driver.manage().getCookies().size(), 0, "All cookies were NOT deleted!");
//        Assert.assertNull(driver.manage().getCookieNamed("trainerCookie"), "trainerCookie was NOT deleted!"); 
//        System.out.println("Deleted ALL cookies (trainerCookie removed)");
        System.out.println("Deleted ALL cookies");
    }

    @AfterMethod(alwaysRun = true)
    public void teardown() {
        if (driver != null) driver.quit();
    }
}
