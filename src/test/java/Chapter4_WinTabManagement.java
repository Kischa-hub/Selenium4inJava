import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Iterator;
import java.util.Set;


public class Chapter4_WinTabManagement {
    WebDriver driver;

    @BeforeMethod
    public void setUp(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.wanderingsolco.com/");
        System.out.println("Title: " + driver.getTitle());
    }


  //@AfterMethod
    public void tearDown(){
        driver.quit();
   }

   @Test
   public void testNewWindowTab(){
        WebDriver newWindow = driver.switchTo().newWindow(WindowType.WINDOW);
       newWindow.get("https://www.wanderingsolco.com/pages/shipping-info");
       System.out.println("Title2"+ newWindow.getTitle() );
   }
   @Test
    public void testWorkingInBothWindowTabs(){
        //Autmaticlay open & switch to the new window or tab
       driver.switchTo().newWindow(WindowType.TAB).get("https://www.wanderingsolco.com/account/register");
       System.out.println("Title: "+ driver.getTitle());


       //Work in the new Window or Tab
       driver.findElement(By.id("mailinglist_email")).sendKeys("Selenium4@TAU.com");
       driver.findElement(By.cssSelector("input[value=\"Join\"]")).click();
       //Get the window ID handels
       Set<String> allWindowsTabs = driver.getWindowHandles();
       Iterator<String> iterate = allWindowsTabs.iterator();
       String mainFirstWindow = iterate.next();

       //Switch & Work in the Main window or Tab
       driver.switchTo().window(mainFirstWindow);
       driver.findElement(By.cssSelector("input[placeholder=\"Search\"]")).sendKeys("COOLER");
       driver.findElement(By.cssSelector(".toolbar-links .search-form button[type=\"submit\"]")).click();
       System.out.println("Title: "+ driver.getTitle());


   }


}
