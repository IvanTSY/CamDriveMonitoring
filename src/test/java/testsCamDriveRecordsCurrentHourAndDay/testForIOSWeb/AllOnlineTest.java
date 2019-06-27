package testsCamDriveRecordsCurrentHourAndDay.testForIOSWeb;

import lib.CoreTestCase;
import lib.ui.CamDrivePageObject;
import lib.ui.factories.CamDrivePageObjectFactory;
import org.junit.Test;

public class AllOnlineTest extends CoreTestCase {
    @Test
    public void testCD100_E75A_MS3_IOS(){
        CamDrivePageObject CamDrivePageObject = CamDrivePageObjectFactory.get(driver);
        CamDrivePageObject.authorizationOnCamdrive();
        CamDrivePageObject.choiseCD100_E75A_MS3_DEV();
        CamDrivePageObject.clickOnline();

    }
}
