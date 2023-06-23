package qtriptest.Utility;


import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;

public class SeleniumWaits {

    public static void seleniumExpWait(WebDriver driver,  ExpectedCondition<java.lang.Boolean> isTrue, int waitTime) throws Exception{
        WebDriverWait wdw = new WebDriverWait(driver, waitTime);
        wdw.until(isTrue);
    }


    public static boolean waitForAlertPresent(WebDriver driver , ExpectedCondition<Alert> isTrue,int waitTime ){
        boolean alearPresent=false;
        WebDriverWait wdw = new WebDriverWait(driver, waitTime);
        try{
          wdw.until(isTrue);
          alearPresent=true;
        }catch(Exception e){
           System.out.println("No Alert Present "+e.getMessage());
        }

        return alearPresent;
    }

    public static void waitForElementVisibility(WebDriver driver ,ExpectedCondition<WebElement>exe ,int waitTime ){
        WebDriverWait wdw = new WebDriverWait(driver, waitTime);
        wdw.until(exe);
    }

    public static void waitForElementCountMatch(WebDriver driver ,ExpectedCondition<java.util.List<WebElement>> exe1 ,int waitTime ){
        WebDriverWait wdw = new WebDriverWait(driver, waitTime);
        wdw.until(exe1);
    }

    
}
