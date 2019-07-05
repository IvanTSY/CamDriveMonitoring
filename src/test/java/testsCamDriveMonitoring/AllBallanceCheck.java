package testsCamDriveMonitoring;


import lib.CoreTestCase;
import lib.ui.CamDrivePageObject;
import lib.ui.factories.CamDrivePageObjectFactory;
import org.junit.Test;

public class AllBallanceCheck extends CoreTestCase {

    //adb shell wm size

    @Test
    public void testEqualsBalance() throws Exception {
        CamDrivePageObject CamDrivePageObject = CamDrivePageObjectFactory.get(driver);
        CamDrivePageObject.authorizationOnCamdrive();
        assertTrue(CamDrivePageObject.checkBalance());
    }
}
