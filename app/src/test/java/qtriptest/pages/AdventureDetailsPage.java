
package qtriptest.pages;

import qtriptest.Utility.SeleniumWaits;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class AdventureDetailsPage {
    WebDriver driver;
    @FindBy(id = "myForm")
    WebElement bookingForm;
    @FindBy(xpath = "//input[@name='name']")
    WebElement nameFeild;
    @FindBy(name = "person")
    WebElement personCountFeild;
    @FindBy(name = "date")
    WebElement datePicker;
    @FindBy(xpath = "//button[text()='Reserve']")
    WebElement reserveBtn;
    @FindBy(id = "reserved-banner")
    WebElement reservedBanner;
    @FindBy (xpath = "//a[text()='Reservations']")
    WebElement histroyBtn;
    
    public AdventureDetailsPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(new AjaxElementLocatorFactory(driver,10), this);
    }

    public void waitForAdventureDetailsPageLoad(){
        try{
          SeleniumWaits.waitForElementVisibility(driver, ExpectedConditions.visibilityOf(bookingForm), 15);
        }catch(Exception e){
            System.out.println("Error When finding element "+e.getMessage());
        }
    }
    public void fillUserDetails(String name, String date,String person){
        String str=date.replace("-", "");
        nameFeild.click();
        nameFeild.sendKeys(name);
        datePicker.click();
        datePicker.sendKeys(str);
        personCountFeild.click();
        personCountFeild.sendKeys(person);
        reserveBtn.submit();
    }

    public void waitForBookingSuccessfullMsg(){
        try{
            SeleniumWaits.waitForElementVisibility(driver, ExpectedConditions.visibilityOf(reservedBanner), 15);
          }catch(Exception e){
              System.out.println("Error When finding element "+e.getMessage());
          }
    }

    public void redirectToHistroyPage(){
        histroyBtn.click();
    }

    

}
