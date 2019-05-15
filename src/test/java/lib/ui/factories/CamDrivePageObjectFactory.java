package lib.ui.factories;

import lib.Platform;
import lib.ui.CamDrivePageObject;
import lib.ui.mobile_web.MWCamDrive;
import org.openqa.selenium.remote.RemoteWebDriver;

public class CamDrivePageObjectFactory {
    public static CamDrivePageObject get(RemoteWebDriver driver)
    {
        if(Platform.getInstance().isMWAndroid()||Platform.getInstance().isMWIos()){

        }return new MWCamDrive(driver);

    }
}
