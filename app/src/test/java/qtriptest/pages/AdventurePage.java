package qtriptest.pages;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.Select;

public class AdventurePage {
    WebDriver driver;
    @FindBy(xpath = "//div[@class='ms-3']")
    List<WebElement> clearBtn;
    @FindBy(xpath = "//select[@id='duration-select']")
    WebElement durationFilter;
    @FindBy (xpath = "//select[@id='category-select']")
    WebElement categoryFilter;
    @FindBy(xpath = "//input[@id='search-adventures']")
    WebElement adventureFilter;
    @FindBy(xpath = "//div[@class='row']/div[@class='col-6 col-lg-3 mb-4']")
    List<WebElement> adventureResult;





    public AdventurePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(new AjaxElementLocatorFactory(driver, 10), this);
    }

    public void setHourFilterValue(String filterName){
        Select s = new Select(durationFilter);
        List<WebElement> allFilterValue=s.getOptions();
        for (WebElement e :allFilterValue){
            if(e.getText().equalsIgnoreCase(filterName.trim())){
                s.selectByVisibleText(e.getText());
            }
        }

    }

    public void setCategoryValue(String categoryName){
        Select s = new Select(categoryFilter);
        List<WebElement> allFilterValue=s.getOptions();
        for (WebElement e :allFilterValue){
            if(e.getText().equalsIgnoreCase(categoryName.trim())){
                s.selectByVisibleText(e.getText());
            }
        }
    }

    public int getResultCount(){
     return adventureResult.size();
    }

    public void setAdventureName(String adventureName){
        adventureFilter.click();
        adventureFilter.sendKeys(adventureName);
    }


    public void selectAdventure(String adventureName){
       WebElement adv =driver.findElement(By.xpath(String.format("//h5[text()='%s']/../../div[@class='d-block d-md-flex justify-content-between flex-wrap pl-3 pr-3']", adventureName)));
       adv.click();
    }


    public void clearFilterValue(){
        for(WebElement e:clearBtn){
            e.click();
        }
    }
}