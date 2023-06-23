package qtriptest.Utility;

import org.testng.Assert;

public class AssertSteps {
    // private static final String url ="";
    // private static final String url3 ="";
    // private static final String url4 ="";
    // private static final String url5 ="";


    public static void assertEqualMethod(String expected , String actual,String message){
        Assert.assertEquals(actual, expected,String.format(message, actual,expected));
    }
    public static void assertEqualMethod(int expected , int actual,String message){
        Assert.assertEquals(actual, expected,String.format(message, actual,expected));
    }

    public static void assertBooleanValues( String type,boolean b )
    {
       switch(type){
        case "true":
        Assert.assertTrue(b);
        break;
        case "false":
        Assert.assertFalse(b);
        break;
       }

    }
    
    
}
