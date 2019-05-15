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
//========================================================Dynamic_xpath_elements_"ext_gen"AND"ext_comp"
        CD120_EAF9_SERV_MS5 = "xpath://*[contains(@class,'x-list-item-body')][contains(text(),'CD120(EAF9)_serv_MS5')]";
        BACK_BTN_ON_MINUTE_SCREEN_CD120 = "xpath://span[contains(@class,'x-button-label')][contains(@id,'ext-gen1040')]";
        BACK_BTN_ON_HOUR_SCREEN_CD120 = "xpath://span[contains(@class,'x-button-label')][contains(@id,'ext-gen1226')]";
//========================================================
        CD100_E75A_MS3_DEV = "xpath://*[contains(@class,'x-list-item-body')][contains(text(),'CD100(E75A)_ms3_dev')]";
        BACK_BTN_ON_MINUTE_SCREEN_CD100_E75A = "xpath://span[contains(@class,'x-button-label')][contains(@id,'ext-gen1040')]";
        BACK_BTN_ON_HOUR_SCREEN_CD100_E75A = "xpath://span[contains(@class,'x-button-label')][contains(@id,'ext-gen1226')]";
//========================================================
        CD630_910D_MS6_DEV = "xpath://*[contains(@class,'x-list-item-body')][contains(text(),'CD630(910D)_ms6_dev')]";
        BACK_BTN_ON_MINUTE_SCREEN_CD630_910D = "xpath://span[contains(@class,'x-button-label')][contains(@id,'ext-gen1040')]";
        BACK_BTN_ON_HOUR_SCREEN_CD630_910D = "xpath://span[contains(@class,'x-button-label')][contains(@id,'ext-gen1226')]";
//========================================================
        CD320_AA06_MS3_DEV = "xpath://*[contains(@class,'x-list-item-body')][contains(text(),'CD320(AA06)_ms3_dev')]";
        BACK_BTN_ON_MINUTE_SCREEN_CD320_AA06 = "xpath://span[contains(@class,'x-button-label')][contains(@id,'ext-gen1040')]";
        BACK_BTN_ON_HOUR_SCREEN_CD320_AA06 = "xpath://span[contains(@class,'x-button-label')][contains(@id,'ext-gen1226')]";
//========================================================
        CD100_E778_MS5 = "xpath://*[contains(@class,'x-list-item-body')][contains(text(),'CD100(E778)_ms5_ПЗ')]";
        BACK_BTN_ON_MINUTE_SCREEN_CD100_E778 = "xpath://span[contains(@class,'x-button-label')][contains(@id,'ext-gen1040')]";
        BACK_BTN_ON_HOUR_SCREEN_CD100_E778 = "xpath://span[contains(@class,'x-button-label')][contains(@id,'ext-gen1226')]";
//========================================================
        CD600_EF78_MS6_SERV = "xpath://*[contains(@class,'x-list-item-body')][contains(text(),'CD600(EF78)_ms6_serv')]";
        BACK_BTN_ON_MINUTE_SCREEN_CD600_EF78 = "xpath://span[contains(@class,'x-button-label')][contains(@id,'ext-gen1040')]";
        BACK_BTN_ON_HOUR_SCREEN_CD600_EF78 = "xpath://span[contains(@class,'x-button-label')][contains(@id,'ext-gen1226')]";
//========================================================
        CD320_AA78_MS5 = "xpath://*[contains(@class,'x-list-item-body')][contains(text(),'CD320(AA78)_ms5_Пр_С')]";
        BACK_BTN_ON_MINUTE_SCREEN_CD320_AA78 = "xpath://span[contains(@class,'x-button-label')][contains(@id,'ext-gen1040')]";
        BACK_BTN_ON_HOUR_SCREEN_CD320_AA78 = "xpath://span[contains(@class,'x-button-label')][contains(@id,'ext-gen1226')]";
//========================================================
        CD310_2E51_MS4_DEV = "xpath://*[contains(@class,'x-list-item-body')][contains(text(),'CD310(2E51)_ms4_dev')]";
        BACK_BTN_ON_MINUTE_SCREEN_CD310_2E51 = "xpath://span[contains(@class,'x-button-label')][contains(@id,'ext-gen1040')]";
        BACK_BTN_ON_HOUR_SCREEN_CD310_2E51 = "xpath://span[contains(@class,'x-button-label')][contains(@id,'ext-gen1226')]";
//========================================================
        CD100_E772_MS4 = "xpath://*[contains(@class,'x-list-item-body')][contains(text(),'CD100(E772)_ms4_')]";
        BACK_BTN_ON_MINUTE_SCREEN_CD100_E772 = "xpath://span[contains(@class,'x-button-label')][contains(@id,'ext-gen1040')]";
        BACK_BTN_ON_HOUR_SCREEN_CD100_E772 = "xpath://span[contains(@class,'x-button-label')][contains(@id,'ext-gen1226')]";
//========================================================
        N1001_3A00_bwd = "xpath://*[contains(@class,'x-list-item-body')][contains(text(),'N1001(3A00)_bwd')]";
        BACK_BTN_ON_MINUTE_SCREEN_N1001_3A00 = "xpath://span[contains(@class,'x-button-label')][contains(@id,'ext-gen1040')]";
        BACK_BTN_ON_HOUR_SCREEN_N1001_3A00 = "xpath://span[contains(@class,'x-button-label')][contains(@id,'ext-gen1226')]";
//========================================================

    }

    public MWCamDrive(RemoteWebDriver driver)
    {
        super(driver);
    }
}

