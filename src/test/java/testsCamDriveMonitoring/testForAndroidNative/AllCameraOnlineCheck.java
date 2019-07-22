package testsCamDriveMonitoring.testForAndroidNative;

import lib.CoreTestCase;
import lib.logging.SuperVisor;
import lib.ui.CamDrivePageObject;
import lib.ui.factories.CamDrivePageObjectFactory;
import org.junit.Test;

import java.io.IOException;

public class AllCameraOnlineCheck extends CoreTestCase {
    @Test
    public void testOnline() throws InterruptedException, IOException {
        CamDrivePageObject CamDrivePageObject = CamDrivePageObjectFactory.get(driver);
        CamDrivePageObject.authForMobile();
        CamDrivePageObject.cameraChoiseForMobile();
        SuperVisor.logCat();
        Thread.sleep(10000);
    }
}
