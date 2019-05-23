package testsCamDriveRecordsCurrentHourAndDay.testForIOSWeb;

import lib.CoreTestCase;
import lib.ui.CamDrivePageObject;
import lib.ui.factories.CamDrivePageObjectFactory;
import org.junit.Test;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Calendar;

public class CD100_E772_ms4_IOS extends CoreTestCase {
    int currentHour;
    int currentDay;
    int currentMonth;
    int currentMinute;
    int currentYear;
    int tick;
    String currentMinuteCONVERTED;
    String currentFirstMinuteCONVERTED;
    String currentLastMinuteCONVERTED;
    String currentHourCONVERTED;
    String currentDayCONVERTED;
    String currentMonthCONVERTED;

    @Test
    public void testCD100_E772_MS4_IOS() throws InterruptedException, IOException {

        currentYear = Calendar.getInstance().getWeekYear();
        currentHour = Calendar.getInstance().getTime().getHours();
        currentDay = Calendar.getInstance().getTime().getDate();
        currentMonth = Calendar.getInstance().getTime().getMonth() + 1;
        currentMinute = Calendar.getInstance().getTime().getMinutes();
//Определяем шаг интервала в зависимости от WEB платформы
        tick = 10;
//Меняем время , если не сможем выполнить условие для открытия блока архива
// Услови следующие - если текущее время "-1"минута не может быть поделено на хотя бы одну целую часть = условие не выполнено,
// т.к. мы заведомо знаем, что даже если запись в блоке есть то она не полная , т.к.CamDrive не успел ее передать
        if(currentMinute < tick){
            currentMinute = 59;
            currentHour = currentHour - 1;
        }
//========================================================

        CamDrivePageObject CamDrivePageObject = CamDrivePageObjectFactory.get(driver);
        CamDrivePageObject.authorizationOnCamdrive();

        CamDrivePageObject.choiseCD100_E772_MS4();
        CamDrivePageObject.choiseTheCurrentDay();
//Открытие потока для фантика
//======================================================Сделать красиво!
        System.out.println("\nData: "+currentYear+"/"+currentMonth+"/"+currentDay+"\nStart test in "+currentHour+" hour and "+currentMinute+" minutes ");
        FileWriter testFile = new FileWriter("TestRecordIOSCD100_E772_MS4.txt",false);
        testFile.write("Data: "+currentYear+"/"+currentMonth+"/"+currentDay+"\nStart test in "+currentHour+" hour and "+currentMinute+" minutes \n");

        System.out.println("Imitation web iPhone");
        testFile.write("Imitation web iPhone");


//*Открытие потока для фантика + определение  платформы


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

        int currentFirstMinute = -tick;
        int currentLastMinute = -1;

        for (int m = 0; m < (currentMinute -6)/tick; m ++){

            currentFirstMinute = currentFirstMinute + tick;
            currentLastMinute = currentLastMinute + tick;

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
            System.out.println("\n"+(m + 1)+" Play archive block of "+tick+" minutes \n");
            testFile.write("\n"+(m + 1)+" Play archive block of "+tick+" minutes \n");
//*Фантик

            //CamDrivePageObject.scrollWebPageTitleElementNotVisible("id:2019-"+currentMonthCONVERTED+"-"+currentDayCONVERTED+"-"+currentHourCONVERTED+"-"+currentFirstMinuteCONVERTED+"-00_2019-"+currentMonthCONVERTED+"-"+currentDayCONVERTED+"-"+currentHourCONVERTED+"-"+currentLastMinuteCONVERTED+"-59","WTF",5);
//TODO : Очень грязный фикс , СРОЧНО исправить !!!
            if (currentHour >11){
                CamDrivePageObject.scrollIntoView();
            }

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

            CamDrivePageObject.loadArchiveVideoIOS(); //добавить трайклик

            try {
                CamDrivePageObject.checkLoadVideoPlayerForIosMW();
            }catch (RuntimeException e){
//Фантик
                System.out.println("Error load archive video. Block: "+currentHourCONVERTED+":00h. "+currentFirstMinuteCONVERTED+"min-"+currentLastMinuteCONVERTED+"min\n" );
                testFile.write("Error load archive video. Block: "+currentHourCONVERTED+":00h. "+currentFirstMinuteCONVERTED+"min-"+currentLastMinuteCONVERTED+"min\n" );
//*Фантик
                CamDrivePageObject.clickBackOnMinuteScreenN1001_3A00();
                continue;
            }
            //TODO : JSE в тесте
            Object attribute = CamDrivePageObject.getTimeDurationVideoForIOSArchive(); //допилить джаваскрипт
            System.out.println(attribute+" sec");
            testFile.write(attribute+" sec\n");
            CamDrivePageObject.clickBackOnMinuteScreenN1001_3A00();
        }
//Закрытие потока для фантика
        testFile.close();
//*Закрытие потока для фантика
    }
}