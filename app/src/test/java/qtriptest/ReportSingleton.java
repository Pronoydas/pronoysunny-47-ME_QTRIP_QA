package qtriptest;
import java.io.File;
import com.relevantcodes.extentreports.ExtentReports;

public class ReportSingleton {
    public static ReportSingleton rs=null;
    private ExtentReports report;
    

    private ReportSingleton() {
        report = new ExtentReports(System.getProperty("user.dir")+"/ExtentReportResult.html",true);
        report.loadConfig(new File(System.getProperty("user.dir")+"/extent_customization_configs.xml"));

    }


    public static ReportSingleton getReportSingletonIns(){

        if (rs==null){
            rs = new ReportSingleton();
        }
        return rs;
    }

    public ExtentReports gExtentReports(){
        return report;
    }
     
    

}