package qtriptest.tests;

import qtriptest.DP;
import qtriptest.DriverSingleton;
import qtriptest.Utility.QtripConstant;
import qtriptest.Utility.SeleniumWaits;
import qtriptest.pages.AdventureDetailsPage;
import qtriptest.pages.AdventurePage;
import qtriptest.pages.HistoryPage;
import qtriptest.pages.HomePage;
import qtriptest.pages.LoginPage;
import qtriptest.pages.RegisterPage;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class testCase_03 {
    WebDriver driver =null;
    ExtentTest test;



    @BeforeMethod(alwaysRun = true)
    public void setUp(Method m) throws MalformedURLException{
      driver=DriverSingleton.getInstanceOfSingletonBrowserClass().getDriver();
      BaseTest basetest = new BaseTest();
      basetest.navgateToHome(driver);
      test=BaseTest.report.startTest(m.getName());
    }
     @Test(description = "Booking & Cancel Flow ",priority = 3,groups = {"Booking and Cancellation Flow"},
     dataProviderClass = DP.class,dataProvider = "data-provider")
     public void TestCase03(String userName,String pass,String searchCity,String advName,String gName,String date,String pCount){
        String lastRegisterUser =null;
        RegisterPage registerPage = new RegisterPage(driver);
        registerPage.clickOnRegisterBtn();
        try {
           SeleniumWaits.seleniumExpWait(driver, ExpectedConditions.urlToBe(QtripConstant.REGISTRATIONPAGE_URL), 15);
        } catch (Exception e) {
          System.out.println("Error Occur when wait for Url Redirection "+e.getMessage());
        }
        registerPage.registerUser(userName, pass, pass,true);
        test.log(LogStatus.PASS, "User Registration Done");
        try {
            SeleniumWaits.seleniumExpWait(driver, ExpectedConditions.urlToBe(QtripConstant.LOGINPAGEURL_URL), 15);
          } catch (Exception e) {
            System.out.println("Error Occur when wait for Url Redirection "+e.getMessage());
          }
          lastRegisterUser=registerPage.getLastRegisterUserName();
          LoginPage lp = new LoginPage(driver);
          lp.loginIntoQtrip(lastRegisterUser, pass);
          lp.performActionOnAlert(); 
          test.log(LogStatus.PASS, "User Registration Done");
          HomePage hp = new HomePage(driver);
          hp.searchCity(searchCity); 
          test.log(LogStatus.PASS, "Search for Ciyt");
          hp.selectCity(searchCity);
          test.log(LogStatus.PASS, "Select the search city");
          try{
            SeleniumWaits.seleniumExpWait(driver, ExpectedConditions.urlContains(QtripConstant.ADVENTUREPAGE_URL), 15);
            }catch(Exception e){
                e.printStackTrace();
            }
          AdventurePage ap = new AdventurePage(driver);
          ap.setAdventureName(advName);
          test.log(LogStatus.PASS, "Search for Adventure");
          try {
            Thread.sleep(1000);
        } catch (InterruptedException e1) {
            e1.printStackTrace();
        }
          ap.selectAdventure(advName);
          test.log(LogStatus.PASS, "User Select Adventure");
          AdventureDetailsPage adp = new AdventureDetailsPage(driver);
          adp.waitForAdventureDetailsPageLoad();
          adp.fillUserDetails(gName,date,pCount);
          adp.waitForBookingSuccessfullMsg();
          test.log(LogStatus.PASS, "Adventure Book Successfully");
          adp.redirectToHistroyPage();
          HistoryPage hp1 = new HistoryPage(driver);
          hp1.waitForHistroyPageLoad();
         String str= hp1.getReservationHistroy();
         hp1.cancelReservation();
         test.log(LogStatus.PASS, "Adventure Cancel Successfully");
         hp1.waitForCancelSuccessfully();
         hp1.refreshHistroyPage();
         hp.logoutUser();
         test.log(LogStatus.PASS, "User Logout Done");
         System.out.println(str);
          
     }
     @AfterMethod
     public void demo(){
     BaseTest.report.endTest(test);
     }
}
