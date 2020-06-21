package managers;

import java.util.logging.Logger;

public class Log {
    // Initialize Log4j logs

    private static Logger Log = Logger.getLogger(managers.Log.class.getName());//

    // This is to print log for the beginning of the test case, as we usually run so many test cases as a test suite

    public static void startTestCase(String sTestCaseName){
        Log.info("********************************************");
        Log.info("$$$$$$$$$"+sTestCaseName+ "$$$$$$$$$");
    }

    //This is to print log for the ending of the test case

    public static void endTestCase(String sTestCaseName){
        Log.info("XXXXXXXXXX"+"-E---N---D-"+"XXXXXXXXXX");
    }

    // Need to create these methods, so that they can be called

    public static void info(String message) {
        Log.info(message);
    }

    public static void info_multi(String msg1,String msg2, String mgs3, String msg4) {
        Log.info(msg1+msg2+mgs3+msg4);
    }
}
