package testsCamDriveMonitoring.testForAndroidNative;

import lib.CoreTestCase;
import lib.ui.CamDrivePageObject;
import lib.ui.factories.CamDrivePageObjectFactory;
import org.junit.Test;

public class AllCameraOnlineCheck extends CoreTestCase {
    @Test
    public void testOnline() throws InterruptedException {
        CamDrivePageObject CamDrivePageObject = CamDrivePageObjectFactory.get(driver);
        CamDrivePageObject.authForMobile();
        CamDrivePageObject.cameraChoiseForMobile();

        Thread.sleep(10000);
    }
}
