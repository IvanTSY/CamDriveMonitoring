package testsCamDriveRecordsCurrentHourAndDay.testForAndroidWeb;

import lib.CoreTestCase;
import lib.Platform;
import lib.ui.CamDrivePageObject;
import lib.ui.factories.CamDrivePageObjectFactory;
import org.junit.Test;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Calendar;

public class N1001_3A00_bwd extends CoreTestCase {
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
    public void testN1001_3A00_bwd() throws IOException, InterruptedException {

        currentYear = Calendar.getInstance().getWeekYear();
        currentHour = Calendar.getInstance().getTime().getHours();
        currentDay = Calendar.getInstance().getTime().getDate();
        currentMonth = Calendar.getInstance().getTime().getMonth() + 1;
        currentMinute = Calendar.getInstance().getTime().getMinutes();
//Определяем шаг интервала в зависимости от WEB платформы
        if(Platform.getInstance().isMWIos()){
            tick = 10;
        }else{
            tick = 5;
        }
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

        CamDrivePageObject.choiseN1001_3A00_bwd();
        CamDrivePageObject.choiseTheCurrentDay();
//Открытие потока для фантика
//======================================================Сделать красиво!
        System.out.println("\nData: "+currentYear+"/"+currentMonth+"/"+currentDay+"\nStart test in "+currentHour+" hour and "+currentMinute+" minutes ");
        FileWriter testFile = new FileWriter("TestRecordN1001_3A00_bwd.txt",false);
        testFile.write("Data: "+currentYear+"/"+currentMonth+"/"+currentDay+"\nStart test in "+currentHour+" hour and "+currentMinute+" minutes \n");
        System.out.println("Imitation web Android");
        testFile.write("Imitation web Android");


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





        if(Platform.getInstance().isMWAndroid()){
            CamDrivePageObject.clickHour(
                    currentDayCONVERTED,
                    currentMonthCONVERTED,
                    currentHourCONVERTED);
        }
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
            if (Platform.getInstance().isMWIos() & currentHour <11){
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

            if(Platform.getInstance().isMWAndroid()) {
                CamDrivePageObject.loadArchiveVideoAndroid();
            }else if(Platform.getInstance().isMWIos()){
                CamDrivePageObject.loadArchiveVideoIOS(); //добавить трайклик
            }else{System.out.println("Трай с проверкой видео. Ошибка определения платформы");}


            //CamDrivePageObject.checkLoadVideoPlayerForIosMW();


            try {
                if(Platform.getInstance().isMWAndroid()){
                    CamDrivePageObject.checkLoadVideoPlayerForAndroidMW();
                }else if(Platform.getInstance().isMWIos()){
                    CamDrivePageObject.checkLoadVideoPlayerForIosMW();
                }else{System.out.println("Трай с проверкой видео. Ошибка определения платформы");}
            }catch (RuntimeException e){
//Фантик
                System.out.println("Error load archive video. Block: "+currentHourCONVERTED+":00h. "+currentFirstMinuteCONVERTED+"min-"+currentLastMinuteCONVERTED+"min\n" );
                testFile.write("Error load archive video. Block: "+currentHourCONVERTED+":00h. "+currentFirstMinuteCONVERTED+"min-"+currentLastMinuteCONVERTED+"min\n" );
//*Фантик
                CamDrivePageObject.clickBackOnMinuteScreenN1001_3A00();
                continue;
            }

            if(Platform.getInstance().isMWAndroid()){
                //CamDrivePageObject.clickOnVideoForm();
                String attribute = CamDrivePageObject.getTime("max");
//Фантик
                System.out.println(attribute+" sec");
                testFile.write(attribute+" sec\n");
//*Фантик
                CamDrivePageObject.clickCloseButtonOnPlayArchiveScreen();
                CamDrivePageObject.clickBackOnMinuteScreenN1001_3A00();
            }else{
                //TODO : JSE в тесте
                Object attribute = CamDrivePageObject.getTimeDurationVideoForIOSArchive(); //допилить джаваскрипт
                System.out.println(attribute+" sec");
                testFile.write(attribute+" sec\n");
                CamDrivePageObject.clickBackOnMinuteScreenN1001_3A00();
            }
        }
        //CamDrivePageObject.clickBackOnHourScreenN1001_3A00();
//Закрытие потока для фантика
        testFile.close();
//*Закрытие потока для фантика
    }
}
