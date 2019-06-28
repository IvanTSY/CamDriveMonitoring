package lib.ui.factories;

import lib.Platform;
import lib.ui.CamDrivePageObject;
import lib.ui.mobile_web.MWCamDrive;
import org.openqa.selenium.remote.RemoteWebDriver;

public class CamDrivePageObjectFactory {
    public static CamDrivePageObject get(RemoteWebDriver driver)
    {
        if(Platform.getInstance().isMWAndroid()){
            return new MWCamDrive(driver);
        }else if(Platform.getInstance().isMWIos()){
            return new MWCamDrive(driver);
        }else if(Platform.getInstance().isIOS()){
            return new MWCamDrive(driver);
        }else {
            return new MWCamDrive(driver);
        }
//        if(Platform.getInstance().isMWAndroid()||Platform.getInstance().isMWIos()){
//
//        }return new MWCamDrive(driver);

    }
}
