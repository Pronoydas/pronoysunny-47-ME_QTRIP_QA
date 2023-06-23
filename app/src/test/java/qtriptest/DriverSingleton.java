package qtriptest;

import java.net.MalformedURLException;
import java.net.URL;
import org.openqa.selenium.remote.*;

// public class DriverSingleton {

//   public static WebDriver driver;

//     private DriverSingleton(){

//     }


//     public static WebDriver getWebDriverInstance(){
//             if (driver==null){
//                 final DesiredCapabilities capabilities = new DesiredCapabilities();
//                 capabilities.setBrowserName(BrowserType.CHROME);
//                 try{
//                 driver = new RemoteWebDriver(new URL("http://localhost:8082/wd/hub"), capabilities);
//                 System.out.println("Driver Created");
//                 }catch(Exception e){
//                     System.out.println("Error Occur"+e.getMessage());
//                 }
//                 driver.manage().window().maximize();
//                 System.out.println("createDriver()");
//             }
//             return driver;    
//     }
// }


public class DriverSingleton {

    private static DriverSingleton instanceOfSingletonBrowserClass = null;
    
    private RemoteWebDriver driver;

    private DriverSingleton() throws MalformedURLException {
        final DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setBrowserName(BrowserType.CHROME);
		driver = new RemoteWebDriver(new URL("http://localhost:8082/wd/hub"), capabilities);
        driver.manage().window().maximize();
    }

    public static DriverSingleton getInstanceOfSingletonBrowserClass() throws MalformedURLException {
        if (instanceOfSingletonBrowserClass == null) {
            instanceOfSingletonBrowserClass = new DriverSingleton();
        }
        return instanceOfSingletonBrowserClass;
    }

    public RemoteWebDriver getDriver() {
        return driver;
    }
}