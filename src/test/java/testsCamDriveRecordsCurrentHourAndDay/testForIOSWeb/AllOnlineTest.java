package testsCamDriveRecordsCurrentHourAndDay.testForIOSWeb;


import io.appium.java_client.MobileElement;
import lib.CoreTestCase;
import lib.ui.CamDrivePageObject;
import lib.ui.factories.CamDrivePageObjectFactory;
import org.junit.Test;

public class AllOnlineTest extends CoreTestCase {
    @Test
    public void testCD100_E75A_MS3_IOS() throws InterruptedException {
        CamDrivePageObject CamDrivePageObject = CamDrivePageObjectFactory.get(driver);
        CamDrivePageObject.authForMobile();
        Thread.sleep(5000);
        //CamDrivePageObject.choiseCD100_E75A_MS3_DEV();
        //CamDrivePageObject.clickOnline();
    }
}
