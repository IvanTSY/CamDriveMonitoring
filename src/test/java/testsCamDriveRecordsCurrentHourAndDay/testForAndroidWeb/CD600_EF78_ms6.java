package testsCamDriveRecordsCurrentHourAndDay.testForAndroidWeb;

import lib.CoreTestCase;
import lib.ui.CamDrivePageObject;
import lib.ui.factories.CamDrivePageObjectFactory;
import org.junit.Test;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Calendar;

public class CD600_EF78_ms6 extends CoreTestCase {
    int currentHour;
    int currentDay;
    int currentMonth;
    int currentMinute;
    int currentYear;
    int tick;
    String currentFirstMinuteCONVERTED;
    String currentLastMinuteCONVERTED;
    String currentMinuteCONVERTED;
    String currentHourCONVERTED;
    String currentDayCONVERTED;
    String currentMonthCONVERTED;

    @Test
    public void testCD600_EF78_MS6() throws InterruptedException, IOException {

        currentYear = Calendar.getInstance().getWeekYear();
        currentHour = Calendar.getInstance().getTime().getHours();
        currentDay = Calendar.getInstance().getTime().getDate();
        currentMonth = Calendar.getInstance().getTime().getMonth() + 1;
        currentMinute = Calendar.getInstance().getTime().getMinutes();
        tick = 5;

        if(currentMinute < tick){
            currentMinute = 59;
            currentHour = currentHour - 1;
        }

        CamDrivePageObject CamDrivePageObject = CamDrivePageObjectFactory.get(driver);
        CamDrivePageObject.authorizationOnCamdrive();

        CamDrivePageObject.choiseCD600_EF78_MS6_SERV();
        CamDrivePageObject.choiseTheCurrentDay("");
//Открытие потока для фантика
        System.out.println("\nData: "+currentYear+"/"+currentMonth+"/"+currentDay+"\nStart test in "+currentHour+" hour and "+currentMinute+" minutes ");
        FileWriter testFile = new FileWriter("TestRecordCD600_EF78_MS6_SERV.txt",false);
        testFile.write("Data: "+currentYear+"/"+currentMonth+"/"+currentDay+"\nStart test in "+currentHour+" hour and "+currentMinute+" minutes \n");
        System.out.println("Imitation web Android");
        testFile.write("Imitation web Android");
//*Открытие потока для фантика

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

        for (int m = 0; m < (currentMinute -6)/tick; m ++){

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

//Фантик
            System.out.println("\n"+(m + 1)+" Play archive block of 5 minutes (Android MW)");
            testFile.write("\n"+(m + 1)+" Play archive block of 5 minutes (Android MW)\n");
//*Фантик
            try{
                CamDrivePageObject.clickMinute(
                        currentDayCONVERTED,
                        currentMonthCONVERTED,
                        currentHourCONVERTED,
                        currentFirstMinuteCONVERTED,
                        currentLastMinuteCONVERTED);



            }catch (RuntimeException e){
//Фантик
                System.out.println("No records. Block "+currentHourCONVERTED+":00h. "+currentFirstMinuteCONVERTED+"min-"+currentLastMinuteCONVERTED+"min\n" );
                testFile.write("No records. Block: "+currentHourCONVERTED+":00h. "+currentFirstMinuteCONVERTED+"min-"+currentLastMinuteCONVERTED+"min\n" );
//*Фантик
                continue;
            }
            CamDrivePageObject.loadArchiveVideoAndroid();
            try {
                CamDrivePageObject.checkLoadVideoPlayerForAndroidMW();
            }catch (RuntimeException e){
//Фантик
                System.out.println("Error load archive video. Block: "+currentHourCONVERTED+":00h. "+currentFirstMinuteCONVERTED+"min-"+currentLastMinuteCONVERTED+"min\n" );
                testFile.write("Error load archive video. Block: "+currentHourCONVERTED+":00h. "+currentFirstMinuteCONVERTED+"min-"+currentLastMinuteCONVERTED+"min\n" );
//*Фантик
                CamDrivePageObject.clickBackOnMinuteScreenCD600_EF78();
                continue;
            }
            CamDrivePageObject.clickOnVideoForm();
            String attribute = CamDrivePageObject.getTime("max");
//Фантик
            System.out.println(attribute+" sec");
            testFile.write(attribute+" sec\n");
//*Фантик
            CamDrivePageObject.clickCloseButtonOnPlayArchiveScreen();
            CamDrivePageObject.clickBackOnMinuteScreenCD600_EF78();
        }
        CamDrivePageObject.clickBackOnHourScreenCD600_EF78();
//Закрытие потока для фантика
        testFile.close();
//*Закрытие потока для фантика
    }
}
