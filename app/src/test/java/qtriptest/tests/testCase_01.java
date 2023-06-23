package qtriptest.tests;

import qtriptest.DP;
import qtriptest.DriverSingleton;
import qtriptest.Utility.QtripConstant;
import qtriptest.Utility.SeleniumWaits;
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

public class testCase_01 extends BaseTest  {
   WebDriver driver =null;
   ExtentTest test;



 @BeforeMethod(alwaysRun = true)
  public void setUp(Method m) throws MalformedURLException{
    driver=DriverSingleton.getInstanceOfSingletonBrowserClass().getDriver();
    BaseTest basetest = new BaseTest();
    basetest.navgateToHome(driver);
    test=BaseTest.report.startTest(m.getName());

  }
   
   @Test(description = "Register to QTRIP Website",priority = 1,groups = {"Login Flow"},dataProviderClass = DP.class,dataProvider = "data-provider")
   public void TestCase01(String userName, String password) throws InterruptedException{
    String lastRegisterUser =null;
    RegisterPage registerPage = new RegisterPage(driver);
    registerPage.clickOnRegisterBtn();
    try {
      SeleniumWaits.seleniumExpWait(driver, ExpectedConditions.urlToBe(QtripConstant.REGISTRATIONPAGE_URL), 15);
    } catch (Exception e) {
      System.out.println("Error Occur when wait for Url Redirection "+e.getMessage());
    }
    registerPage.assertRegistrationSuccessfully(QtripConstant.ASSERT_REGISTRATION_PAGE,
    QtripConstant.REGISTRATIONPAGE_URL,QtripConstant.FAIL_MESSAGE_WHEN_URL_NOTMATCH);
    registerPage.registerUser(userName, password, password,true);
    try {
      SeleniumWaits.seleniumExpWait(driver, ExpectedConditions.urlToBe(QtripConstant.LOGINPAGEURL_URL), 15);
    } catch (Exception e) {
      System.out.println("Error Occur when wait for Url Redirection "+e.getMessage());
    }
    registerPage.assertRegistrationSuccessfully(QtripConstant.ASSERT_LOGIN_PAGE,
    QtripConstant.LOGINPAGEURL_URL,QtripConstant.FAIL_MESSAGE_WHEN_URL_NOTMATCH);
    test.log(LogStatus.PASS, "User Register Successfully");
    lastRegisterUser=registerPage.getLastRegisterUserName();
    LoginPage lp = new LoginPage(driver);
    lp.loginIntoQtrip(lastRegisterUser, password);
    lp.performActionOnAlert();
    test.log(LogStatus.PASS, "User Login Successfully");
    HomePage hp = new HomePage(driver);
    hp.verifyVisibilityOfLogoutBtn();
    hp.logoutUser();
    test.log(LogStatus.PASS, "User Logout Successfully");
    hp.verifyInVisibilityOfLogoutBtn();
   }

   @AfterMethod
   public void demo(){
      BaseTest.report.endTest(test);
   }


}
