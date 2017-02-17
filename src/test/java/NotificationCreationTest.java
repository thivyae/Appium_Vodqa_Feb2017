import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.remote.DesiredCapabilities;


public class NotificationCreationTest {

    public static AndroidDriver appiumDriver;

    @BeforeClass
    public static void launchDriver() {
        DesiredCapabilities capabilities = new DesiredCapabilities();
//        capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
        capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, "5.1");
        capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "Android Emulator");
        capabilities.setCapability(MobileCapabilityType.APP, "/Users/thivyalakshimi/Desktop/Appium_Vodqa_Feb2017/notifyme.apk");

        try {
            appiumDriver = new AndroidDriver<>(capabilities);

        } catch (Exception e) {
            System.out.println(e);
        }

    }

    @Test
    public void createNotification() {
        //create notification
        appiumDriver.findElementById("com.guvery.notifyme:id/fab_create").click();
        appiumDriver.findElementById("com.guvery.notifyme:id/create_title").sendKeys("VodQA Appium Session");
        appiumDriver.findElementById("com.guvery.notifyme:id/create_body").sendKeys("Demo Session");
        appiumDriver.findElementByClassName("android.widget.Spinner").click();//type attribute
        appiumDriver.findElementByXPath("//android.widget.ListView[1]/android.widget.CheckedTextView[2]").click();
        appiumDriver.findElementByAccessibilityId("Done").click();//content desc attribute is also referred by accessibility id
    }

    @AfterClass
    public static void tearDown() throws Exception {
        if (appiumDriver != null)
            appiumDriver.quit();
    }

}