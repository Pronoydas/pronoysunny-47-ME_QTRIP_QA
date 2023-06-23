package qtriptest.pages;

import qtriptest.Utility.AssertSteps;
import qtriptest.Utility.SeleniumWaits;
import java.util.UUID;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class RegisterPage {
    WebDriver driver;
    String lastRegisterUserName=null;
    // private final String url="https://qtripdynamic-qa-frontend.vercel.app/pages/login";
    // private final String registrationPageUrl="https://qtripdynamic-qa-frontend.vercel.app/pages/register/";
    // private final String failMessageForReg ="Application Url is not Matching Expected %s But Actual %s";
    @FindBy(xpath = "//label[text()='Email address']/../input[@id='floatingInput']")
    WebElement emailAddressField;
    @FindBy(xpath = "//label[starts-with(text(),'Type to create')]/../input[@id='floatingPassword']")
    WebElement password;
    @FindBy(xpath = "//label[starts-with(text(),'Retype Password')]/../input[@id='floatingPassword']")
    WebElement confirmPassword;
    @FindBy(xpath = "//button[text()='Register Now']")
    WebElement registerButton;
    @FindBy(xpath = "//a[text()='Register']") 
    WebElement registerbtn_homeScreen;
    
    public RegisterPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(new AjaxElementLocatorFactory(driver, 15) ,this);
    }

//new AjaxElementLocatorFactory(driver, 15)
    public void clickOnRegisterBtn(){
        registerbtn_homeScreen.click();
    }

    

   
    public void registerUser(String userName, String password,String confirmPassword,boolean isRandomNumberReq){
        if(isRandomNumberReq){
            userName=String.format("%s%s@gmail.com",userName,UUID.randomUUID());
            // System.out.println(userName);
          }
        try{
        Actions a = new Actions(driver);
        a.click(emailAddressField).sendKeys(userName).sendKeys(Keys.TAB).sendKeys(password).sendKeys(Keys.TAB).
        sendKeys(confirmPassword).build().perform();
        // emailAddressField.click();
        // emailAddressField.sendKeys(userName);
        // this.password.click();
        // this.password.sendKeys(password);
        // this.confirmPassword.click();
        // this.confirmPassword.sendKeys(confirmPassword);
        registerButton.click();
        setLastRegisterUserName(userName);
        }catch(Exception e){
            System.out.println("Error Occur at the time of User Reggistration "+e.getMessage());
        }
    }

    public void waitForPageRedriction(String url){
        try{
            SeleniumWaits.seleniumExpWait(driver, ExpectedConditions.urlToBe(url), 15);

        }catch(Exception e){
            System.out.println("Error At the time of Url Redriction "+e.getMessage());

        }
    }

    public void assertRegistrationSuccessfully(String type,String url,String failMessageForReg ){
        String currentUrl=driver.getCurrentUrl();
        switch(type){
            case "Registration Page":
            AssertSteps.assertEqualMethod(url, currentUrl, String.format(failMessageForReg, url,currentUrl));

            case  "Login Page":
            AssertSteps.assertEqualMethod(url, currentUrl, String.format(failMessageForReg, url,currentUrl));
            break;
        }
        
    }


    public String getLastRegisterUserName() {
        return lastRegisterUserName;
    }


    public void setLastRegisterUserName(String lastRegisterUserName) {
        this.lastRegisterUserName = lastRegisterUserName;
    }





    

}
