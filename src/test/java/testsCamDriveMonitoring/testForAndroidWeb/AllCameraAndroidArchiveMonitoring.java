package testsCamDriveMonitoring.testForAndroidWeb;

import lib.CoreTestCase;
import lib.apiSlack.SlackSendMessaages;
import lib.ui.BigMethodsForUniqueTests;
import lib.ui.CamDrivePageObject;
import lib.ui.factories.CamDrivePageObjectFactory;
import org.apache.commons.lang3.text.WordUtils;
import org.junit.Test;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class AllCameraAndroidArchiveMonitoring extends CoreTestCase {
    private int tick = 5;
    SlackSendMessaages ms = new SlackSendMessaages();
    private String dateOfTestRun = WordUtils.capitalize("\""+new SimpleDateFormat("dd MMMM").format(new Date())+"\"");
    private int currentHour = Calendar.getInstance().getTime().getHours() - 1;

    @Test
    public void testAuth() throws IOException {
        CamDrivePageObject CamDrivePageObject = CamDrivePageObjectFactory.get(driver);

        if (CamDrivePageObject.authorizationOnCamdrive()){
            ms.sendMSG("WEB Android: "+dateOfTestRun+" INFO: Авторизация с валидными данными - выполнена.");
        }else {
            ms.sendMSG("WEB Android: "+dateOfTestRun+" WARNING: Авторизация с валидными данными - не выполнена.");
        }
    }

    @Test
    public void testCD100_E75A_MS3_ANDROID() throws Exception {
        String cameraName = "CD100_E75A_MS3";
        String errorLogFile = "ErrorRecordIOSCD100_E75A_MS3.txt";
        String channelID = "aee40e829262b7930f529c4fee6d326a";

        CamDrivePageObject CamDrivePageObject = CamDrivePageObjectFactory.get(driver);
        CamDrivePageObject.checkScheldue(currentHour,channelID);
        CamDrivePageObject.authorizationOnCamdrive();

        CamDrivePageObject.choiseCD100_E75A_MS3_DEV();

        BigMethodsForUniqueTests BMUTest = new BigMethodsForUniqueTests();
        BMUTest.coreTestForMonitoring(CamDrivePageObject,errorLogFile,cameraName,channelID,tick);

    }

    @Test
    public void testCD100_E772_MS4_ANDROID() throws Exception {
        String cameraName = "CD100_E772_MS4";
        String errorLogFile = "ErrorRecordIOSCD100_E772_MS4.txt";
        String channelID = "95100e92696c2163bef3185cd29deff2";

        CamDrivePageObject CamDrivePageObject = CamDrivePageObjectFactory.get(driver);
        CamDrivePageObject.checkScheldue(currentHour,channelID);
        CamDrivePageObject.authorizationOnCamdrive();

        CamDrivePageObject.choiseCD100_E772_MS4();

        BigMethodsForUniqueTests BMUTest = new BigMethodsForUniqueTests();
        BMUTest.coreTestForMonitoring(CamDrivePageObject,errorLogFile,cameraName,channelID,tick);

//*Закрытие потока для фантика
    }

    @Test
    public void testCD100_E778_MS5_ANDROID() throws Exception {
        String cameraName = "CD100_E778_MS5 ";
        String errorLogFile = "ErrorRecordIOSCD100_E778_MS5.txt";
        String channelID = "aef64c10d39975425e711014dcb8a061";

        CamDrivePageObject CamDrivePageObject = CamDrivePageObjectFactory.get(driver);
        CamDrivePageObject.checkScheldue(currentHour,"aef64c10d39975425e711014dcb8a061");
        CamDrivePageObject.authorizationOnCamdrive();

        CamDrivePageObject.choiseCD100_E778_MS5();

        BigMethodsForUniqueTests BMUTest = new BigMethodsForUniqueTests();
        BMUTest.coreTestForMonitoring(CamDrivePageObject,errorLogFile,cameraName,channelID,tick);
    }

    @Test
    public void testCD310_2E51_MS4_ANDROID() throws Exception {
        String cameraName = "CD310_2E51_MS4";
        String errorLogFile = "ErrorRecordIOSCD310_2E51_MS4.txt";
        String channelID = "78cbbc49a31cdff1778023fc57e89f46";

        CamDrivePageObject CamDrivePageObject = CamDrivePageObjectFactory.get(driver);
        CamDrivePageObject.checkScheldue(currentHour,"78cbbc49a31cdff1778023fc57e89f46");
        CamDrivePageObject.authorizationOnCamdrive();

        CamDrivePageObject.choiseCD310_2E51_MS4_DEV();

        BigMethodsForUniqueTests BMUTest = new BigMethodsForUniqueTests();
        BMUTest.coreTestForMonitoring(CamDrivePageObject,errorLogFile,cameraName,channelID,tick);
    }

    @Test
    public void testCD320_AA06_MS3_ANDROID() throws Exception {

        String cameraName = "CD320_AA06_MS3";
        String errorLogFile = "ErrorRecordIOSCD320_AA06_MS3.txt";
        String channelID = "2e6fb75139e4226198f9f6c0786e8b8a";

        CamDrivePageObject CamDrivePageObject = CamDrivePageObjectFactory.get(driver);
        CamDrivePageObject.checkScheldue(currentHour,"2e6fb75139e4226198f9f6c0786e8b8a");
        CamDrivePageObject.authorizationOnCamdrive();

        CamDrivePageObject.choiseCD320_AA06_MS3_DEV();

        BigMethodsForUniqueTests BMUTest = new BigMethodsForUniqueTests();
        BMUTest.coreTestForMonitoring(CamDrivePageObject,errorLogFile,cameraName,channelID,tick);
    }

    @Test
    public void testCD320_AA78_MS5_ANDROID() throws Exception {
        String cameraName = "CD320_AA78_MS5";
        String errorLogFile = "ErrorRecordIOSCD320_AA78_MS5.txt";
        String channelID = "b63c65eeaa4410befcac0a2e96281f5c";

        CamDrivePageObject CamDrivePageObject = CamDrivePageObjectFactory.get(driver);
        CamDrivePageObject.checkScheldue(currentHour,"b63c65eeaa4410befcac0a2e96281f5c");
        CamDrivePageObject.authorizationOnCamdrive();

        CamDrivePageObject.choiseCD320_AA78_MS5();

        BigMethodsForUniqueTests BMUTest = new BigMethodsForUniqueTests();
        BMUTest.coreTestForMonitoring(CamDrivePageObject,errorLogFile,cameraName,channelID,tick);
    }
    @Test
    public void testCD600_EF78_MS6_ANDROID() throws Exception {
        String cameraName = "CD600_EF78_MS6";
        String errorLogFile = "ErrorRecordIOSCD600_EF78_MS6.txt";
        String channelID = "23d6fb09e101dc587b8b16db3cf7b5dd";

        CamDrivePageObject CamDrivePageObject = CamDrivePageObjectFactory.get(driver);
        CamDrivePageObject.checkScheldue(currentHour,"23d6fb09e101dc587b8b16db3cf7b5dd");
        CamDrivePageObject.authorizationOnCamdrive();

        CamDrivePageObject.choiseCD600_EF78_MS6_SERV();

        BigMethodsForUniqueTests BMUTest = new BigMethodsForUniqueTests();
        BMUTest.coreTestForMonitoring(CamDrivePageObject,errorLogFile,cameraName,channelID,tick);
    }

    @Test
    public void testCD630_910D_MS6_ANDROID() throws Exception {
        String cameraName = "CD630_910D_MS6";
        String errorLogFile = "ErrorRecordIOSCD630_910D_MS6.txt";
        String channelID = "81037196ecb69d5306abcb8f61fba33c";

        CamDrivePageObject CamDrivePageObject = CamDrivePageObjectFactory.get(driver);
        CamDrivePageObject.checkScheldue(currentHour,"81037196ecb69d5306abcb8f61fba33c");
        CamDrivePageObject.authorizationOnCamdrive();

        CamDrivePageObject.choiseCD630_910D_MS6_DEV();

        BigMethodsForUniqueTests BMUTest = new BigMethodsForUniqueTests();
        BMUTest.coreTestForMonitoring(CamDrivePageObject,errorLogFile,cameraName,channelID,tick);
    }

    @Test
    public void testN1001_3A00_bwd_ANDROID() throws Exception {
        String cameraName = "N1001_3A00_bwd";
        String errorLogFile = "ErrorRecordIOSN1001_3A00_bwd.txt";
        String channelID = "4ac35c97e26af54c55caa2b36ceab0ca";

        CamDrivePageObject CamDrivePageObject = CamDrivePageObjectFactory.get(driver);
        CamDrivePageObject.checkScheldue(currentHour,"4ac35c97e26af54c55caa2b36ceab0ca");
        CamDrivePageObject.authorizationOnCamdrive();

        CamDrivePageObject.choiseN1001_3A00_bwd();

        BigMethodsForUniqueTests BMUTest = new BigMethodsForUniqueTests();
        BMUTest.coreTestForMonitoring(CamDrivePageObject,errorLogFile,cameraName,channelID,tick);
    }
}
