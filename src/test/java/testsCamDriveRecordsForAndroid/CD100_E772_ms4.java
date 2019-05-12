package testsCamDriveRecordsForAndroid;

import lib.CoreTestCase;
import lib.ui.CamDrivePageObject;
import lib.ui.factories.CamDrivePageObjectFactory;
import org.junit.Test;

import java.util.Calendar;

public class CD100_E772_ms4 extends CoreTestCase {
    int currentHour;
    int currentDay;
    int currentMonth;
    int currentMinute;

    String currentMinuteCONVERTED;
    String currentFirstMinuteCONVERTED;
    String currentLastMinuteCONVERTED;
    String currentHourCONVERTED;
    String currentDayCONVERTED;
    String currentMonthCONVERTED;

    @Test
    public void testCD100_E772_MS4() throws InterruptedException {

        currentHour = Calendar.getInstance().getTime().getHours();
        currentDay = Calendar.getInstance().getTime().getDate();
        currentMonth = Calendar.getInstance().getTime().getMonth() + 1;
        currentMinute = Calendar.getInstance().getTime().getMinutes();


        CamDrivePageObject CamDrivePageObject = CamDrivePageObjectFactory.get(driver);
        CamDrivePageObject.authorizationOnCamdrive();

        CamDrivePageObject.choiseCD100_E772_MS4();
        CamDrivePageObject.choiseTheCurrentDay();
        System.out.println("Start test in "+currentHourCONVERTED+":"+currentMinuteCONVERTED);

        //==========================================================
        if (currentMinute <10){
            currentMinuteCONVERTED = "0"+currentMinute;
        }else currentMinuteCONVERTED = Integer.toString(currentMinute);
        //==========================================================
        if (currentHour <10){
            currentHourCONVERTED = "0"+currentHour;
        }else currentHourCONVERTED = Integer.toString(currentHour);
        //==========================================================

        if (currentDay <10){
            currentDayCONVERTED = ""+(currentDay);
        }else currentDayCONVERTED = Integer.toString(currentDay);
        //==========================================================
        if (currentMonth <10){
            currentMonthCONVERTED = "0"+currentMonth;
        }else currentMonthCONVERTED = Integer.toString(currentMonth);
        //==========================================================

        CamDrivePageObject.clickHour(
                currentDayCONVERTED,
                currentMonthCONVERTED,
                currentHourCONVERTED);

        int currentFirstMinute = -5;
        int currentLastMinute = -1;

        for (int m = 0; m < (currentMinute -1)/5; m ++){

            currentFirstMinute = currentFirstMinute +5;
            currentLastMinute = currentLastMinute + 5;

            //==========================================================
            if (currentFirstMinute <10){
                currentFirstMinuteCONVERTED = "0"+currentFirstMinute;
            }else currentFirstMinuteCONVERTED = Integer.toString(currentFirstMinute);
            //==========================================================
            if (currentLastMinute <10){
                currentLastMinuteCONVERTED = "0"+currentLastMinute;
            }else currentLastMinuteCONVERTED = Integer.toString(currentLastMinute);
            //==========================================================
            if (currentHour <10){
                currentHourCONVERTED = "0"+currentHour;
            }else currentHourCONVERTED = Integer.toString(currentHour);
            //==========================================================
            if (currentDay <10){
                currentDayCONVERTED = ""+currentDay;
            }else currentDayCONVERTED = Integer.toString(currentDay );
            //==========================================================
            if (currentMonth <10){
                currentMonthCONVERTED = "0"+currentMonth;
            }else currentMonthCONVERTED = Integer.toString(currentMonth);
            //==========================================================

            System.out.println("Iteration of 5 minutes = "+ (m + 1));
            CamDrivePageObject.clickMinute(
                    currentDayCONVERTED,
                    currentMonthCONVERTED,
                    currentHourCONVERTED,
                    currentFirstMinuteCONVERTED,
                    currentLastMinuteCONVERTED);

            CamDrivePageObject.loadArchiveVideo();
            try {
                CamDrivePageObject.checkLoadVideoPlayer();
            }catch (RuntimeException e){
                System.out.println("Error load archive video. Month-"+currentMonthCONVERTED+"-Day-"+currentDayCONVERTED+"-hh-"+currentHourCONVERTED+"-mm:"+currentFirstMinuteCONVERTED+"-"+currentLastMinuteCONVERTED );
                CamDrivePageObject.clickBackOnMinuteScreenCD100_E772();
                continue;
            }
            CamDrivePageObject.clickOnVideoForm();
            String attribute = CamDrivePageObject.getTime("max");
            System.out.println(attribute);
            CamDrivePageObject.clickCloseButtonOnPlayArchiveScreen();
            CamDrivePageObject.clickBackOnMinuteScreenCD100_E772();
            Thread.sleep(2500); //Убрать тред
        }
        CamDrivePageObject.clickBackOnHourScreenCD100_E772();
    }
}
