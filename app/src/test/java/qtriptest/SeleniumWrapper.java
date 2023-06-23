package qtriptest;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class SeleniumWrapper {


    public boolean click(WebDriver driver , WebElement ele){

        return true;
    }

    public boolean sendKeys(WebElement ele ,String str){
        return true;

    }

    public boolean navigate(WebDriver driver , String str){
        return true;
    }

    public boolean findElementWithRetry(WebDriver driver){

        return true;
    }
}
