package lib.ui.mobile_web;

import lib.ui.CamDrivePageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

public class MWCamDrive extends CamDrivePageObject {
    static {
        LOGIN = "css:#ext-gen1129";
        PASSWORD = "css:#ext-gen1134";
        ENTER_BUTTON = "css:#ext-gen1142";
        CD120_EAF9_SERV_MS5 = "xpath://*[contains(@class,'x-list-item-body')][contains(text(),'CD120(EAF9)_serv_MS5')]";
        DAY = "xpath://*[contains(@class,'day today')]";
        ClOSE_BTN = "css:#conteiner_vac > div.closeFullscreen";
        BACK_BTN = "xpath://*[contains(@class,' x-button white-back x-button-back')][contains(@id,'ext-comp-1071')]"; //TODO : если найду способ исправить клик по координатам
        MINUTE_TEST = "id:2019-{Month}-{Day}-{Hour}-{MinuteFirst}-00_2019-{Month}-{Day}-{Hour}-{MinuteLast}-59";
        HOUR_TEST = "id:2019-{Month}-{Day}-{Hour}-00-00_2019-{Month1}-{Day1}-{Hour1}-59-59";
    }

    public MWCamDrive(RemoteWebDriver driver)
    {
        super(driver);
    }
}

