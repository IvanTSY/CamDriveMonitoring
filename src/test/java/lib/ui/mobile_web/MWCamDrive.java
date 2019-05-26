package lib.ui.mobile_web;

import lib.ui.CamDrivePageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

public class MWCamDrive extends CamDrivePageObject {
    static {
        LOGIN = "xpath://input[@name='username']"; //"css:#ext-gen1129";
        PASSWORD = "xpath://input[@name='password']"; //"css:#ext-gen1134";
        ENTER_BUTTON = "xpath://span[text()='Войти']";//"css:#ext-gen1142";
        DAY_TODAY = "xpath://*[contains(@class,'day today')]"; //текущий день
        DAY = "id:2019/{Month}/{Day}"; //любой день

        ClOSE_BTN = "css:#conteiner_vac > div.closeFullscreen";
        DYNAMIC_MINUTE_XPATH = "id:2019-{Month}-{Day}-{Hour}-{MinuteFirst}-00_2019-{Month}-{Day}-{Hour}-{MinuteLast}-59";
        DYNAMIC_HOUR_XPATH = "id:2019-{Month}-{Day}-{Hour}-00-00_2019-{Month}-{Day}-{Hour}-59-59";
        BACK_BTN_ON_MINUTE_SCREEN = "xpath://span[contains(@class,'x-button-label')][contains(@id,'ext-gen1040')]";//div[contains(@id,'ext-comp-1071')][contains(@class,'x-button white-back x-button-back')]/span[contains(text(),'Назад')]";
        BACK_BTN_ON_HOUR_SCREEN = "xpath://span[contains(@class,'x-button-label')][contains(@id,'ext-gen1226')]";
//========================================================Dynamic_xpath_elements_"ext_gen"AND"ext_comp"
        CD120_EAF9_SERV_MS5 = "xpath://*[contains(@class,'x-list-item-body')][contains(text(),'CD120(EAF9)_serv_MS5')]";
//========================================================
        CD100_E75A_MS3_DEV = "xpath://*[contains(@class,'x-list-item-body')][contains(text(),'CD100(E75A)_ms3_dev')]";
//========================================================
        CD630_910D_MS6_DEV = "xpath://*[contains(@class,'x-list-item-body')][contains(text(),'CD630(910D)_ms6_dev')]";
//========================================================
        CD320_AA06_MS3_DEV = "xpath://*[contains(@class,'x-list-item-body')][contains(text(),'CD320(AA06)_ms3_dev')]";
//========================================================
        CD100_E778_MS5 = "xpath://*[contains(@class,'x-list-item-body')][contains(text(),'CD100(E778)_ms5_ПЗ')]";
//========================================================
        CD600_EF78_MS6_SERV = "xpath://*[contains(@class,'x-list-item-body')][contains(text(),'CD600(EF78)_ms6_serv')]";
//========================================================
        CD320_AA78_MS5 = "xpath://*[contains(@class,'x-list-item-body')][contains(text(),'CD320(AA78)_ms5_Пр_С')]";
//========================================================
        CD310_2E51_MS4_DEV = "xpath://*[contains(@class,'x-list-item-body')][contains(text(),'CD310(2E51)_ms4_dev')]";
//========================================================
        CD100_E772_MS4 = "xpath://*[contains(@class,'x-list-item-body')][contains(text(),'CD100(E772)_ms4_')]";
//========================================================
        N1001_3A00_bwd = "xpath://*[contains(@class,'x-list-item-body')][contains(text(),'N1001(3A00)_bwd')]";
//========================================================

    }

    public MWCamDrive(RemoteWebDriver driver)
    {
        super(driver);
    }
}

