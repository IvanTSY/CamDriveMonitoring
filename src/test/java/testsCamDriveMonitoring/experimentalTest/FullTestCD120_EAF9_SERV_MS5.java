package testsCamDriveMonitoring.experimentalTest;

import lib.CoreTestCase;
import lib.ui.CamDrivePageObject;
import lib.ui.factories.CamDrivePageObjectFactory;
import org.junit.Test;

import java.io.IOException;
import java.util.Calendar;

public class FullTestCD120_EAF9_SERV_MS5 extends CoreTestCase {


    int paramHour = -1;
    int currentHour;
    int currentDay;
    int currentMonth;
    int currentYear;


    String currentFirstMinuteCONVERTED;
    String currentLastMinuteCONVERTED;
    String currentHourCONVERTED;
    String currentDayCONVERTED;
    String currentMonthCONVERTED;
    String cameraName = "";
    String errorLogFile = "";

    @Test
    public void testMonitoringCamDriveRecords() throws InterruptedException, IOException {

        currentHour = Calendar.getInstance().getTime().getHours();
        currentDay = Calendar.getInstance().getTime().getDate();
        currentMonth = Calendar.getInstance().getTime().getMonth() + 1;


        CamDrivePageObject CamDrivePageObject = CamDrivePageObjectFactory.get(driver);
        CamDrivePageObject.authorizationOnCamdrive();

        CamDrivePageObject.choiseCD120MS5();
        CamDrivePageObject.choiseTheCurrentDay(
                errorLogFile,
                cameraName,
                currentYear,
                currentMonth,
                currentDay,
                currentHour);


        //*******************************
        for (int h = 0; h <= currentHour; h ++){
            paramHour ++;
            //==========================================================
            if (paramHour <10){
                currentHourCONVERTED = "0"+paramHour;
            }else currentHourCONVERTED = Integer.toString(paramHour);
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

            System.out.println(currentHourCONVERTED);

            int currentFirstMinute = -5;
            int currentLastMinute = -1;

            for (int m = 0; m < 12; m ++){

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
                if (paramHour <10){
                    currentHourCONVERTED = "0"+paramHour;
                }else currentHourCONVERTED = Integer.toString(paramHour);
                //==========================================================
                if (currentDay <10){
                    currentDayCONVERTED = ""+currentDay;
                }else currentDayCONVERTED = Integer.toString(currentDay );
                //==========================================================
                if (currentMonth <10){
                    currentMonthCONVERTED = "0"+currentMonth;
                }else currentMonthCONVERTED = Integer.toString(currentMonth);
                //==========================================================

                System.out.println("Iteration = "+ (m + 1));
                CamDrivePageObject.clickMinute(
                        currentDayCONVERTED,
                        currentMonthCONVERTED,
                        currentHourCONVERTED,
                        currentFirstMinuteCONVERTED,
                        currentLastMinuteCONVERTED);

                CamDrivePageObject.waitForElementPresent("id:container_pl","Not found play button after 10 seconds wait",10);

                CamDrivePageObject.waitForElementAndClick("id:container_pl","Video is not load after 10 seconds wait",10);
                try {

                    CamDrivePageObject.checkLoadVideoPlayerForAndroidMW();
                }catch (RuntimeException e){
                    System.out.println("Error load archive video. Month-"+currentMonthCONVERTED+"-Day-"+currentDayCONVERTED+"-hh-"+currentHourCONVERTED+"-mm:"+currentFirstMinuteCONVERTED+"-"+currentLastMinuteCONVERTED );
                    CamDrivePageObject.tryClickElementWithFewAttempts("xpath://span[contains(@class,'x-button-label')][contains(@id,'ext-gen1040')]","nooooo",10);
                    continue;
                }
                CamDrivePageObject.waitForElementAndClick("xpath://div[@id='conteiner_vac']/video","Not found player form after 10 seconds wait",10);
                String attribute = CamDrivePageObject.waitForElementAndGetAtribute("xpath://*[contains(@class,'x-controlbar-Android-seek-loading-right')]","max","Time line is not visible",2);
                System.out.println(attribute);
                CamDrivePageObject.clickCloseButtonOnPlayArchiveScreen();
                CamDrivePageObject.tryClickElementWithFewAttempts("xpath://span[contains(@class,'x-button-label')][contains(@id,'ext-gen1040')]","ypal na minutu",20);
                Thread.sleep(2500); //Убрать тред
            }
            CamDrivePageObject.tryClickElementWithFewAttempts("xpath://span[contains(@class,'x-button-label')][contains(@id,'ext-gen1226')]","ypal na chas",20);
        }
    }
}
