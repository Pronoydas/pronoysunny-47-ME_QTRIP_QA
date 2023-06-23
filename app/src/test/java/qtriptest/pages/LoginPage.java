package qtriptest.pages;

import qtriptest.Utility.SeleniumWaits;
import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class LoginPage {
    WebDriver driver;
  // @FindBys({@FindBy(name="email"),@FindBy(id = "floatingInput")})
  @FindBy(xpath = "//label[text()='Email address']/../input[@id='floatingInput']")
  WebElement emailAddressFeild;
  @FindBy(id = "floatingPassword")
  WebElement passwordFeild;
  @FindBy(xpath = "//button[text()='Login to QTrip']")
  WebElement loginButton;

  public LoginPage(WebDriver driver) {
     this.driver=driver;
     PageFactory.initElements(new AjaxElementLocatorFactory(driver, 15), this);
  }

  public void loginIntoQtrip(String userNmae,String password){
    
    emailAddressFeild.click();
    emailAddressFeild.sendKeys(userNmae);
    passwordFeild.click();
    passwordFeild.sendKeys(password);
    loginButton.click();

  }

  public void performActionOnAlert(){
    if(SeleniumWaits.waitForAlertPresent(driver, ExpectedConditions.alertIsPresent(), 20)){
        Alert a=driver.switchTo().alert();
        a.accept();
    }
  }



  


}
