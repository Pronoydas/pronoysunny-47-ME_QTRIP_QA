package qtriptest.tests;

import qtriptest.ReportSingleton;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

public class BaseTest {
    public static ExtentReports report ;
    private final String applicationUlr="https://qtripdynamic-qa-frontend.vercel.app/index.html";

    public  void navgateToHome(WebDriver driver){
        // driver.manage().window().maximize();
        driver.get(applicationUlr);
    }

    @BeforeSuite
    public static void setupExtentReport(){
        report=ReportSingleton.getReportSingletonIns().gExtentReports();
        
    }
    @AfterSuite
     public static void closedExtentConnection(){
        report.flush();
        report.close();
     } 
    
}
