import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class ScrollToNotificationTest {

    public static AndroidDriver driver;

    @BeforeClass
    public static void launchDriver() {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
        capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, "5.1");
        capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "Android Emulator");
        capabilities.setCapability(MobileCapabilityType.APP, "/Users/thivyalakshimi/Desktop/Appium_Vodqa_Feb2017/notifyme.apk");

        try {
            driver = new AndroidDriver<>(capabilities);

        } catch (Exception e) {
            System.out.println(e);
        }

    }


    @Test()
    public void verifyNotificationCreation(){

        ArrayList<String> actualTitle=new ArrayList<>();
        ArrayList<String> expectedTitle = new ArrayList<>(Arrays.asList("notification_test1","notification_test2","notification_test3","notification_test4","notification_test5"));
        addNotification(driver,"notification_test1","First test notification");
        addNotification(driver,"notification_test2","Second test notification");
        addNotification(driver,"notification_test3","Third test notification");
        addNotification(driver,"notification_test4","Fourth test notification");
        addNotification(driver,"notification_test5","Fifth test notification");


        String pageTitle = driver.findElementByClassName("android.widget.TextView").getText().toString();
        Assert.assertEquals("Notify",pageTitle);
        List<WebElement> listTitle = driver.findElementsById("com.guvery.notifyme:id/title");
        for(WebElement title: listTitle) {
            String text = title.getText();
            actualTitle.add(text);
        }
        Object[] actualTitles = actualTitle.toArray();
        Assert.assertEquals (expectedTitle,actualTitle);
        scrollTo();
    }

    public TouchAction scrollTo(){

        addNotification(driver,"notification_test6","Sixth test notification");
        addNotification(driver,"notification_test7","Seventh test notification");
        addNotification(driver,"notification_test8","Eighth test notification");
        TouchAction touchAction=new TouchAction(driver);
        touchAction.press((int)0.5,(int)0.5).waitAction(1000).moveTo((int) 0.5,  1).release();
        return driver.performTouchAction(touchAction);


    }


    public void addNotification(AndroidDriver driver,String title,String description) {
        WebElement addNoteBtn = driver.findElement(By.id("com.guvery.notifyme:id/fab_create"));
        addNoteBtn.click();
        driver.findElementById("com.guvery.notifyme:id/create_title").sendKeys(title);
        driver.findElementById("com.guvery.notifyme:id/create_body").sendKeys(description);
        driver.findElementById("com.guvery.notifyme:id/action_done").click();
    }



    @AfterClass
    public static void tearDown() throws Exception {
        if(driver!=null)
            driver.quit();
    }


}
