package qtriptest.tests;

import qtriptest.DP;
import qtriptest.DriverSingleton;
import qtriptest.Utility.QtripConstant;
import qtriptest.Utility.SeleniumWaits;
import qtriptest.pages.AdventurePage;
import qtriptest.pages.HomePage;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class testCase_02 {
    WebDriver driver =null;
    ExtentTest test;
    



    @BeforeMethod(alwaysRun = true)
    public void setUp(Method m) throws MalformedURLException{
      driver=DriverSingleton.getInstanceOfSingletonBrowserClass().getDriver();
      BaseTest basetest = new BaseTest();
      basetest.navgateToHome(driver);
      test=BaseTest.report.startTest(m.getName());
    }
    
     @Test(description = "Verify the Search Feature",priority =2,groups = {"Search and Filter flow"},dataProviderClass = DP.class,dataProvider = "data-provider")
     public void TestCase02(String cityName,String categoryFilter,String durationFilter,String expectedFilterResult,String expectedUnFilerResult){
        int unFilterResult=0;
        int filterResult=0;
        HomePage hp = new HomePage(driver);
        hp.searchCity(cityName);
        test.log(LogStatus.PASS, "Search for City Completed");
        hp.selectCity(cityName);
        test.log(LogStatus.PASS, "Selected Search City");
        try{
        SeleniumWaits.seleniumExpWait(driver, ExpectedConditions.urlContains(QtripConstant.ADVENTUREPAGE_URL), 15);
        }catch(Exception e){
            e.printStackTrace();
        }
        AdventurePage ap = new AdventurePage(driver);
        unFilterResult=ap.getResultCount();
        Assert.assertEquals(Integer.parseInt(expectedUnFilerResult), unFilterResult, QtripConstant.WHEN_SEARCHCOUTNOTMATCH);
        ap.setHourFilterValue(durationFilter);
        test.log(LogStatus.PASS, "Add Duration Filter");
        ap.setCategoryValue(categoryFilter);
        test.log(LogStatus.PASS, "Add Category Filter");
        SeleniumWaits.waitForElementCountMatch(driver, ExpectedConditions.numberOfElementsToBe(By.xpath("//div[@class='row']/div[@class='col-6 col-lg-3 mb-4']"), 1), 15);
        filterResult=ap.getResultCount();
        Assert.assertEquals(Integer.parseInt(expectedFilterResult), filterResult, QtripConstant.WHEN_SEARCHCOUTNOTMATCH);
        test.log(LogStatus.PASS, "Verify Adventure after puting filter");
        ap.clearFilterValue();
        Assert.assertEquals(Integer.parseInt(expectedUnFilerResult), unFilterResult, QtripConstant.WHEN_SEARCHCOUTNOTMATCH);
        test.log(LogStatus.PASS, "Verify Adventure after removing filter");
     }
     @AfterMethod
     public void demo(){
        BaseTest.report.endTest(test);
     }
  
}
