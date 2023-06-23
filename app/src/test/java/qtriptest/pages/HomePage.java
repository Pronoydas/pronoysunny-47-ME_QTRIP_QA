package qtriptest.pages;

import qtriptest.Utility.AssertSteps;
import qtriptest.Utility.QtripConstant;
import qtriptest.Utility.SeleniumWaits;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.*;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.util.*;

public class HomePage {
    WebDriver driver;
    @FindBy(xpath = "//div[text()='Logout']")
    WebElement logoutBtn;
     @FindBy(xpath =  "//input[@class='hero-input']")
     WebElement citySearchBox;
     @FindBy(id = "results")
     WebElement searchlist;
     @FindBy (xpath = "//ul[@id='results']//li")
     List<WebElement> searchResult;
     @FindBy(xpath = "//a[text()='Home']")
     WebElement homeBtn;


    public HomePage(WebDriver driver) {
      this.driver=driver;
      PageFactory.initElements(new AjaxElementLocatorFactory(driver, 15), this);
    }
     //new AjaxElementLocatorFactory(driver, 15)
    public void logoutUser(){
        logoutBtn.click();
    }

    public void verifyUserLoginSuccessfully(){
         AssertSteps.assertBooleanValues(QtripConstant.ASSERT_TRUE, logoutBtn.isDisplayed());
         
    }

    public void verifyUserLogoutSuccessfully(){
        AssertSteps.assertBooleanValues(QtripConstant.ASSERT_FALSE, logoutBtn.isDisplayed());
    }
    public void verifyVisibilityOfLogoutBtn(){
        SeleniumWaits.waitForElementVisibility(driver, ExpectedConditions.visibilityOf(logoutBtn), 15);
    }

    public void verifyInVisibilityOfLogoutBtn(){
        try{
        SeleniumWaits.seleniumExpWait(driver, ExpectedConditions.invisibilityOf(logoutBtn), 15);
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }


    public void searchCity(String cityName){
         Actions a = new Actions(driver);
         a.click(citySearchBox).sendKeys(cityName).build().perform();
        // citySearchBox.clear();
        // citySearchBox.click();
        // citySearchBox.sendKeys(cityName);
        // System.out.println("Before loop "+searchlist.isDisplayed());
         
        

    }

    public void selectCity(String city){
        try{
        Actions a = new Actions(driver);
        int index =0;
        while(((index<=city.length()-1)&& !(searchlist.isDisplayed()))){
        a.click(citySearchBox).sendKeys(Keys.BACK_SPACE).build().perform();
        index++;
       
            Thread.sleep(1000);
        
    }
}catch(Exception e){
    e.getMessage();
}
        
        for (WebElement we:searchResult){
            if(we.getText().equalsIgnoreCase(city)){
                AssertSteps.assertEqualMethod(city.toLowerCase(), we.getText().toLowerCase(), QtripConstant.FAILMESSAGE_CITYNAMENOTMATCH);
                we.click();
            }
        }


    }

    public void assertAutoCompetedText(String cityName  ){
        // AssertSteps.assertEqualMethod(expected, actual, message);
    }

    public void clickOnHome(){
        homeBtn.click();
    }



    
}
