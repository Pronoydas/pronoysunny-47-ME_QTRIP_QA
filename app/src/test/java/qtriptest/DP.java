package qtriptest;

import qtriptest.Utility.ExcelOperation;
import java.lang.reflect.Method;
import org.testng.annotations.DataProvider;

public class DP {

    ExcelOperation eo = null;
@DataProvider(name="data-provider")
    public Object[][] dp(Method m){
        String methodName =m.getName();
        eo= new ExcelOperation();
        eo.setupExcel();
        switch(methodName){
            case "TestCase01" :
            return eo.readDataFromExcel(methodName);
            case "TestCase02" :
            return eo.readDataFromExcel(methodName);
            case "TestCase03" :
            return eo.readDataFromExcel(methodName);
            case "TestCase04" :
            return eo.readDataFromExcel(methodName);
        }

        return null;
    }
}
