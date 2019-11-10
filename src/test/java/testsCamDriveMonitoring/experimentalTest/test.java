package testsCamDriveMonitoring.experimentalTest;

import lib.CoreTestCase;
import lib.ui.CamDrivePageObject;
import lib.ui.factories.CamDrivePageObjectFactory;
import org.junit.Test;

import java.io.FileWriter;
import java.util.Calendar;

public class test extends CoreTestCase {
    int currentHour = Calendar.getInstance().getTime().getHours() - 1;
    int currentDay = Calendar.getInstance().getTime().getDate();
    int currentMonth = Calendar.getInstance().getTime().getMonth() + 1;
    int currentYear = Calendar.getInstance().getWeekYear();

    String currentFirstMinuteCONVERTED;
    String currentLastMinuteCONVERTED;
    String currentHourCONVERTED;
    String currentDayCONVERTED;
    String currentMonthCONVERTED;
    String cameraName = "";
    String errorLogFile = "";


    @Test
    public void testCD100_E770_test() throws Exception {

        CamDrivePageObject CamDrivePageObject = CamDrivePageObjectFactory.get(driver);

        /*CamDrivePageObject.authorizationOnCamdriveTest();*/CamDrivePageObject.authorizationOnCamdrive();
        //CamDrivePageObject.choiseCD100_E770_test();

        Thread.sleep(10000);
    }


}
