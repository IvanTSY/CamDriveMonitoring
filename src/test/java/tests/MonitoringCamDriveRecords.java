package tests;

import lib.CoreTestCase;
import lib.ui.CamDrivePageObject;
import lib.ui.factories.CamDrivePageObjectFactory;
//import lib.ui.mobile_web.MWCamDriveTime;
import org.junit.Test;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

import java.util.Calendar;

public class MonitoringCamDriveRecords extends CoreTestCase {

    int currentFirstMinute = -5;
    int currentLastMinute = -1;
    int paramHour = -1;
    int currentHour;
    int currentDay;
    int currentMonth;

    String currentFirstMinuteCONVERTED;
    String currentLastMinuteCONVERTED;
    String currentHourCONVERTED;
    String currentDayCONVERTED;
    String currentMonthCONVERTED;

    @Test
    public void testMonitoringCamDriveRecords() throws InterruptedException {

        currentHour = Calendar.getInstance().getTime().getHours();
        currentDay = Calendar.getInstance().getTime().getDate();
        currentMonth = Calendar.getInstance().getTime().getMonth() + 1;


        CamDrivePageObject CamDrivePageObject = CamDrivePageObjectFactory.get(driver);
        CamDrivePageObject.authorizationOnCamdrive();

        CamDrivePageObject.choiseCD120MS5();
        CamDrivePageObject.choiseTheCurrentDay();

        //*******************************
        for (int h = 0; h < 23; h ++){
            paramHour = paramHour + 1;
            //==========================================================
            if (paramHour <10){
                currentHourCONVERTED = "0"+paramHour;
            }else currentHourCONVERTED = Integer.toString(paramHour);
            //==========================================================

            if (currentDay <10){
                currentDayCONVERTED = ""+currentDay;
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

            for (int m =0; m < 12; m ++){

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
                }else currentDayCONVERTED = Integer.toString(currentDay);
                //==========================================================
                if (currentMonth <10){
                    currentMonthCONVERTED = "0"+currentMonth;
                }else currentMonthCONVERTED = Integer.toString(currentMonth);
                //==========================================================


                CamDrivePageObject.clickMinute(
                        currentDayCONVERTED,
                        currentMonthCONVERTED,
                        currentHourCONVERTED,
                        currentFirstMinuteCONVERTED,
                        currentLastMinuteCONVERTED);
                CamDrivePageObject.waitForElementAndClick("id:container_pl","Not found play button after 10 seconds wait",10);
                CamDrivePageObject.checkLoadVideo();
                CamDrivePageObject.waitForElementAndClick("xpath://div[@id='conteiner_vac']/video","Not found player form after 10 seconds wait",10);
                String attribute = CamDrivePageObject.waitForElementAndGetAtribute("xpath://*[contains(@class,'x-controlbar-Android-seek-loading-right')]","max","Time line is not visible",2);
                System.out.println(attribute);

                CamDrivePageObject.clickCloseButtonOnPlayArchiveScreen();
                //Thread.sleep(5000);
                //CamDrivePageObject.clickElementToTheLeft("xpath://*[@id='ext-gen1003']","barabara");
                //CamDrivePageObject.clickBackButtonOnArchiveScreen();
                CamDrivePageObject.tryClickElementWithFewAttempts("xpath://span[contains(@class,'x-button-label')][contains(@id,'ext-gen1040')]","nooooo",10);
                Thread.sleep(5000);
            }
            CamDrivePageObject.clickBackButtonOnArchiveScreen();
        }
        //*******************************
    }
}
