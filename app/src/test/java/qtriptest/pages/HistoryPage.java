
package qtriptest.pages;

import qtriptest.Utility.QtripConstant;
import qtriptest.Utility.SeleniumWaits;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class HistoryPage {
    WebDriver driver;
    @FindBy (xpath = "//button[text()='Cancel']")
    WebElement cancelBtn;
    ////table//tbody//tr/th
    @FindBy(xpath = "//table//tbody//tr/th")
    WebElement transId; 
    @FindBy (id = "no-reservation-banner")
    WebElement noResbanner;   



    public HistoryPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(new AjaxElementLocatorFactory(driver, 10), this);
    }

    public void waitForHistroyPageLoad(){
        try{
        SeleniumWaits.seleniumExpWait(driver, ExpectedConditions.urlToBe(QtripConstant.HISTROYPAGE_URL), 15);
        }catch(Exception e){
            e.getMessage();
        }
    }
    public String getReservationHistroy(){
        String transactionID=null;
        transactionID=transId.getText();
        return transactionID;
        
    }
    public void cancelReservation(){
     cancelBtn.click();
    }

    public void waitForCancelSuccessfully(){
        SeleniumWaits.waitForElementVisibility(driver, ExpectedConditions.visibilityOf(noResbanner), 15);
    }

    public void refreshHistroyPage(){
        driver.navigate().refresh();
    }

}