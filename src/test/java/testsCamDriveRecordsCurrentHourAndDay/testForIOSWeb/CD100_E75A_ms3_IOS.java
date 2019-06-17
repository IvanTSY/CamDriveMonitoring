package testsCamDriveRecordsCurrentHourAndDay.testForIOSWeb;
import lib.CoreTestCase;
import lib.ui.CamDrivePageObject;
import lib.ui.factories.CamDrivePageObjectFactory;
import org.junit.Test;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Calendar;

public class CD100_E75A_ms3_IOS extends CoreTestCase {
    String currentFirstMinuteCONVERTED;
    String currentLastMinuteCONVERTED;
    String currentHourCONVERTED;
    String currentDayCONVERTED;
    String currentMonthCONVERTED;
    int currentYear = Calendar.getInstance().getWeekYear();
    int currentHour = Calendar.getInstance().getTime().getHours() - 1;
    int currentDay = Calendar.getInstance().getTime().getDate();
    int currentMonth = Calendar.getInstance().getTime().getMonth() + 1;
    int currentMinute = 59;
    int tick = 10;
    int statisticOfError =0;


    @Test
    public void testCD100_E75A_MS3_IOS() throws Exception {
        FileWriter cleanFile = new FileWriter("TestRecordIOSCD100_E75A_MS3.txt", false);
        cleanFile.close();

        CamDrivePageObject CamDrivePageObject = CamDrivePageObjectFactory.get(driver);
        CamDrivePageObject.checkScheldue(currentHour, "aee40e829262b7930f529c4fee6d326a");
        CamDrivePageObject.authorizationOnCamdrive();
        CamDrivePageObject.choiseCD100_E75A_MS3_DEV();
        if (currentHour < 10) {
            currentHourCONVERTED = "0" + currentHour;
        } else currentHourCONVERTED = Integer.toString(currentHour);
        //==========================================================
        if (currentMonth < 10) {
            currentMonthCONVERTED = "0" + currentMonth;
        } else currentMonthCONVERTED = Integer.toString(currentMonth);
        //==========================================================
//обработка ситуации с полуночью
        if (currentHour < 0) {
            currentHour = 23;
            currentHourCONVERTED = "23";
            currentDay = currentDay - 1;

            if (currentDay < 10) {
                currentDayCONVERTED = "" + (currentDay);
            } else currentDayCONVERTED = Integer.toString(currentDay);

            CamDrivePageObject.choiseDay(currentDayCONVERTED, currentMonthCONVERTED);
        } else {

            if (currentDay < 10) {
                currentDayCONVERTED = "" + (currentDay);
            } else currentDayCONVERTED = Integer.toString(currentDay);

            CamDrivePageObject.choiseTheCurrentDay();
        }
// \обработка ситуации с полуночью
//Открытие потока для фантика
//======================================================Сделать красиво!
        System.out.println("\nData: " + currentYear + "/" + currentMonth + "/" + currentDay + "\nStart test in " + currentHour + " hour and " + currentMinute + " minutes ");
        FileWriter testFile = new FileWriter("TestRecordIOSCD100_E75A_MS3.txt", false);
        testFile.write("Data: " + currentYear + "/" + currentMonth + "/" + currentDay + "\nStart test in " + currentHour + " hour and " + currentMinute + " minutes \n");
        System.out.println("Camera: CD100_E75A_MS3 \n");
        testFile.write("Camera: CD100_E75A_MS3 \n");
        System.out.println("Imitation web iPhone");
        testFile.write("Imitation web iPhone");


//*Открытие потока для фантика + определение  платформы

        int currentFirstMinute = -tick;
        int currentLastMinute = -1;

//--------------------------------------------------------------------------------------------------------------------
        //Удаление репорта ошибок
        File errorLog = new File("ErrorRecordIOSCD100_E75A_MS3.txt");
        errorLog.delete();
        //Удаление репорта ошибок

        int schedule = CamDrivePageObject.returnCurrentScheldueStatus(currentHour, "aee40e829262b7930f529c4fee6d326a");
//-----------------------------------------------------------------------------------------------------------
        //Открытие потока на запись Error файла
        FileWriter errorFile = new FileWriter("ErrorRecordIOSCD100_E75A_MS3.txt", true);
        errorFile.write("Data: " + currentYear + "/" + currentMonth + "/" + currentDay + "\nStart test in " + currentHour + " hour and 00 minutes \n" + "Camera: CD100_E75A_MS3 \n" + "Imitation web iOS\n");
//-----------------------------------------------------------------------------------------------------------
        for (int m = 0; m < 6; m++) {

            currentFirstMinute = currentFirstMinute + tick;
            currentLastMinute = currentLastMinute + tick;

            //==========================================================
            if (currentFirstMinute < 10) {
                currentFirstMinuteCONVERTED = "0" + currentFirstMinute;
            } else currentFirstMinuteCONVERTED = Integer.toString(currentFirstMinute);
            //==========================================================
            if (currentLastMinute < 10) {
                currentLastMinuteCONVERTED = "0" + currentLastMinute;
            } else currentLastMinuteCONVERTED = Integer.toString(currentLastMinute);
            //==========================================================
//Фантик
            System.out.println("\n Block:" + (m + 1) + " Play archive block of " + currentHourCONVERTED + ":00h. " + currentFirstMinuteCONVERTED + "min-" + currentLastMinuteCONVERTED + "min\n");
            testFile.write("\n Block:" + (m + 1) + " Play archive block of " + currentHourCONVERTED + ":00h. " + currentFirstMinuteCONVERTED + "min-" + currentLastMinuteCONVERTED + "min\n");
//*Фантик

            //CamDrivePageObject.scrollWebPageTitleElementNotVisible("id:2019-"+currentMonthCONVERTED+"-"+currentDayCONVERTED+"-"+currentHourCONVERTED+"-"+currentFirstMinuteCONVERTED+"-00_2019-"+currentMonthCONVERTED+"-"+currentDayCONVERTED+"-"+currentHourCONVERTED+"-"+currentLastMinuteCONVERTED+"-59","WTF",5);
//TODO : Очень грязный фикс , СРОЧНО исправить !!!
            if (currentHour > 11) {
                CamDrivePageObject.scrollIntoView();
            }
//            CamDrivePageObject.checkLoadVideoPlayerForIosMW();

//-----------------------------------------------------------------------------------------------------------
            try {
                CamDrivePageObject.clickMinute(
                        currentDayCONVERTED,
                        currentMonthCONVERTED,
                        currentHourCONVERTED,
                        currentFirstMinuteCONVERTED,
                        currentLastMinuteCONVERTED);
            } catch (RuntimeException e) {
//Фантик
                if ((schedule == 1) || (schedule == 3)) {
                    System.out.println("No records. Block " + currentHourCONVERTED + ":00h. " + currentFirstMinuteCONVERTED + "min-" + currentLastMinuteCONVERTED + "min\n");
                    testFile.write("No records. Block: " + currentHourCONVERTED + ":00h. " + currentFirstMinuteCONVERTED + "min-" + currentLastMinuteCONVERTED + "min\n");
                } else {
                    errorFile.write("No records. Block: " + currentHourCONVERTED + ":00h. " + currentFirstMinuteCONVERTED + "min-" + currentLastMinuteCONVERTED + "min\n");
                    statisticOfError++;
                    System.out.println("No records. Block " + currentHourCONVERTED + ":00h. " + currentFirstMinuteCONVERTED + "min-" + currentLastMinuteCONVERTED + "min\n");

                }
//*Фантик
                continue;
            }
//-----------------------------------------------------------------------------------------------------------------------
            CamDrivePageObject.loadArchiveVideoIOS(); //добавить трайклик

            try {
                CamDrivePageObject.checkLoadVideoPlayerForIosMW();
            } catch (RuntimeException e) {
//Фантик
                if ((schedule == 1) || (schedule == 3)) {
                    System.out.println("Error load archive video. Block: " + currentHourCONVERTED + ":00h. " + currentFirstMinuteCONVERTED + "min-" + currentLastMinuteCONVERTED + "min\n");
                    testFile.write("Error load archive video. Block: " + currentHourCONVERTED + ":00h. " + currentFirstMinuteCONVERTED + "min-" + currentLastMinuteCONVERTED + "min\n");
                } else {
                    System.out.println("Error load archive video. Block: " + currentHourCONVERTED + ":00h. " + currentFirstMinuteCONVERTED + "min-" + currentLastMinuteCONVERTED + "min\n");
                    errorFile.write("Error load archive video. Block: " + currentHourCONVERTED + ":00h. " + currentFirstMinuteCONVERTED + "min-" + currentLastMinuteCONVERTED + "min\n");
                    statisticOfError++;
                }
//*Фантик
                CamDrivePageObject.clickBackOnMinuteScreenIOS();
                continue;
            }
//-----------------------------------------------------------------------------------------------------------------------
            //TODO : JSE в тесте
            Object attribute = CamDrivePageObject.getTimeDurationVideoForIOSArchive(); //допилить джаваскрипт
            attribute.getClass();

            int time = 0;

            String result = attribute.toString();
            int indexEndResult = result.lastIndexOf(".");

            if (indexEndResult == -1) {
                try {
                    time = Integer.parseInt(result);
                } catch (Exception e) {
                    System.out.println("Cannot format type int current element :" + attribute);
                }
            } else {
                time = Integer.parseInt(result.substring(0, indexEndResult));
            }
            if ((schedule == 0) & (time < 590)) {
                errorFile.write("Current block: " + currentHourCONVERTED + ":00h. " + currentFirstMinuteCONVERTED + "min-" + currentLastMinuteCONVERTED + "min\n" + "Record is less then 590 second. Current record is " + time + "\n");
                statisticOfError++;
            }

            System.out.println(attribute + " sec " + attribute.getClass());
            System.out.println("RESULT!   " + result);
            System.out.println("Parse and cut. Result " + time);
            testFile.write(attribute + " sec\n");

            CamDrivePageObject.clickBackOnMinuteScreenIOS();
        }
//Закрытие потока для фантика

        testFile.close();
        errorFile.close();
        System.out.println(statisticOfError);
        if (statisticOfError == 0){
            errorLog.delete();
        }
//*Закрытие потока для фантика
    }
}