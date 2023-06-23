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

public class testCase_04 {
    WebDriver driver =null;
    ExtentTest test;


    @BeforeMethod(alwaysRun = true)
    public void setUp(Method m) throws MalformedURLException{
      driver=DriverSingleton.getInstanceOfSingletonBrowserClass().getDriver();
      BaseTest basetest = new BaseTest();
      basetest.navgateToHome(driver);
      test=BaseTest.report.startTest(m.getName());
    }

     @Test(description = "Booking Multiple Adventure",priority = 4,groups = {"Reliability Flow"},dataProviderClass = DP.class,dataProvider = "data-provider")
     public void TestCase04(String userName , String pass ,String data1,String data2,String data3){
        String[] dataSet;
        String lastRegisterUser =null;
        RegisterPage registerPage = new RegisterPage(driver);
        registerPage.clickOnRegisterBtn();
        try {
           SeleniumWaits.seleniumExpWait(driver, ExpectedConditions.urlToBe(QtripConstant.REGISTRATIONPAGE_URL), 15);
        } catch (Exception e) {
          System.out.println("Error Occur when wait for Url Redirection "+e.getMessage());
        }
        registerPage.registerUser(userName, pass, pass,true);
        try {
            SeleniumWaits.seleniumExpWait(driver, ExpectedConditions.urlToBe(QtripConstant.LOGINPAGEURL_URL), 15);
          } catch (Exception e) {
            System.out.println("Error Occur when wait for Url Redirection "+e.getMessage());
          }
          lastRegisterUser=registerPage.getLastRegisterUserName();
          LoginPage lp = new LoginPage(driver);
          lp.loginIntoQtrip(lastRegisterUser, pass);
          lp.performActionOnAlert(); 
          HomePage hp = new HomePage(driver);
          AdventurePage ap = new AdventurePage(driver);
          AdventureDetailsPage adp = new AdventureDetailsPage(driver);
          for(int i=0 ;i<3;i++){
            if(i==0){
              dataSet=data1.split(";");
            }else if(i==1){
              dataSet=data2.split(";");
            }
            else {
              dataSet=data3.split(";");
            }
            int num=0;
            hp.searchCity(dataSet[num]); 
          hp.selectCity(dataSet[num]);
          try{
            SeleniumWaits.seleniumExpWait(driver, ExpectedConditions.urlContains(QtripConstant.ADVENTUREPAGE_URL), 15);
            }catch(Exception e){
                e.printStackTrace();
            }
            ++num;
            ap.setAdventureName(dataSet[num]);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e1) {
                e1.printStackTrace();
            }
            ap.selectAdventure(dataSet[num]);
            adp.waitForAdventureDetailsPageLoad();
            adp.fillUserDetails(dataSet[++num],dataSet[++num],dataSet[++num]);
            adp.waitForBookingSuccessfullMsg();
            if(i<=1){
            hp.clickOnHome();
            }
            dataSet=null;
          }
          
            //Second Booking 
            // hp.searchCity("bengaluru"); 
            // hp.selectCity("bengaluru");
            // try{
            //  SeleniumWaits.seleniumExpWait(driver, ExpectedConditions.urlContains(QtripConstant.ADVENTUREPAGE_URL), 15);
            // }catch(Exception e){
            //     e.printStackTrace();
            // }
            // ap.setAdventureName("Niaboytown");
            // try {
            //     Thread.sleep(1000);
            // } catch (InterruptedException e1) {
            //     e1.printStackTrace();
            // }
            // ap.selectAdventure("Niaboytown");
            // adp.waitForAdventureDetailsPageLoad();
            // adp.fillUserDetails("ABCFD1","09082024","1");
            // adp.waitForBookingSuccessfullMsg();
            // hp.clickOnHome();
            //3rd Booking 
            // hp.searchCity("singapore"); 
            // hp.selectCity("singapore");
            // try{
            //  SeleniumWaits.seleniumExpWait(driver, ExpectedConditions.urlContains(QtripConstant.ADVENTUREPAGE_URL), 15);
            // }catch(Exception e){
            //     e.printStackTrace();
            // }
            // ap.setAdventureName("Grand Ashland");
            // try {
            //     Thread.sleep(1000);
            // } catch (InterruptedException e1) {
            //     e1.printStackTrace();
            // }
            // ap.selectAdventure("Grand Ashland");
            // adp.waitForAdventureDetailsPageLoad();
            // adp.fillUserDetails("ABCFD5","09082024","1");
            // adp.waitForBookingSuccessfullMsg();
            // hp.clickOnHome();
            adp.redirectToHistroyPage();
            HistoryPage hp1 = new HistoryPage(driver);
            hp1.waitForHistroyPageLoad();
            hp.logoutUser();
            test.log(LogStatus.PASS, "Booked Avdenture");

     }
     @AfterMethod
     public void demo(){
      BaseTest.report.endTest(test);
     }
}
