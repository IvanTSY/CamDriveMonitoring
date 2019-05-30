package testsCamDriveRecordsCurrentHourAndDay.testForAndroidWeb;

import lib.CoreTestCase;
import lib.Platform;
import lib.ui.CamDrivePageObject;
import lib.ui.factories.CamDrivePageObjectFactory;
import org.junit.Test;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Calendar;

public class AllCameraAndroidArchiveTests extends CoreTestCase {
    int currentHour = Calendar.getInstance().getTime().getHours() - 1;
    int currentDay = Calendar.getInstance().getTime().getDate();
    int currentMonth = Calendar.getInstance().getTime().getMonth() + 1;
    int currentMinute = 59;
    int currentYear = Calendar.getInstance().getWeekYear();
    int tick = 5;
    String currentFirstMinuteCONVERTED;
    String currentLastMinuteCONVERTED;
    String currentMinuteCONVERTED;
    String currentHourCONVERTED;
    String currentDayCONVERTED;
    String currentMonthCONVERTED;



    @Test
    public void testCD100_E75A_MS3() throws IOException, InterruptedException {
//Чистим файл перед запуском теста
        FileWriter cleanFile = new FileWriter("TestRecordCD100_E75A_MS3.txt",false);
        cleanFile.close();

        CamDrivePageObject CamDrivePageObject = CamDrivePageObjectFactory.get(driver);
        CamDrivePageObject.authorizationOnCamdrive();
        CamDrivePageObject.choiseCD100_E75A_MS3_DEV();

        if (currentHour <10){
            currentHourCONVERTED = "0"+currentHour;
        }else currentHourCONVERTED = Integer.toString(currentHour);
        //==========================================================
        if (currentMonth <10){
            currentMonthCONVERTED = "0"+currentMonth;
        }else currentMonthCONVERTED = Integer.toString(currentMonth);
        //==========================================================
//обработка ситуации с полуночью
        if(currentHour <0){
            currentHour = 23;
            currentDay = currentDay -1;

            if (currentDay <10){
                currentDayCONVERTED = ""+(currentDay);
            }else currentDayCONVERTED = Integer.toString(currentDay);

            CamDrivePageObject.choiseDay(currentDayCONVERTED,currentMonthCONVERTED);
        }else {

            if (currentDay <10){
                currentDayCONVERTED = ""+(currentDay);
            }else currentDayCONVERTED = Integer.toString(currentDay);

            CamDrivePageObject.choiseTheCurrentDay();
        }
// \обработка ситуации с полуночью
//Открытие потока для фантика
        System.out.println("\nData: "+currentYear+"/"+currentMonth+"/"+currentDay+"\nStart test in "+currentHour+" hour and 00 minutes ");
        FileWriter testFile = new FileWriter("TestRecordCD100_E75A_MS3.txt",false);
        testFile.write("Data: "+currentYear+"/"+currentMonth+"/"+currentDay+"\nStart test in "+currentHour+" hour and 00 minutes \n");
        System.out.println("Camera: CD100_E75A_MS3 \n");
        testFile.write("Camera: CD100_E75A_MS3 \n");
        System.out.println("Imitation web Android");
        testFile.write("Imitation web Android");
//*Открытие потока для фантика



        CamDrivePageObject.clickHour(
                currentDayCONVERTED,
                currentMonthCONVERTED,
                currentHourCONVERTED);


        int currentFirstMinute = -5;
        int currentLastMinute = -1;
//TODO: Для камер с включенной склейкой требуется предусмотреть таймаут подготовки блока с записью минимум в 369 секунд
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

//Фантик
            System.out.println("\n Block:"+(m + 1)+" Play archive block of "+currentHourCONVERTED+":00h. "+currentFirstMinuteCONVERTED+"min-"+currentLastMinuteCONVERTED+"min\n");
            testFile.write("\n Block:"+(m + 1)+" Play archive block of "+currentHourCONVERTED+":00h. "+currentFirstMinuteCONVERTED+"min-"+currentLastMinuteCONVERTED+"min\n");
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

                CamDrivePageObject.clickBackOnMinuteScreenAndroid();

                continue;
            }
            CamDrivePageObject.clickOnVideoForm();
            String attribute = CamDrivePageObject.getTime("max");
//Фантик
            System.out.println(attribute+" sec");
            testFile.write(attribute+" sec\n");
//*Фантик
            CamDrivePageObject.clickCloseButtonOnPlayArchiveScreen();
            CamDrivePageObject.clickBackOnMinuteScreenAndroid();
        }
        CamDrivePageObject.clickBackOnHourScreenCD100_E75A();
//Закрытие потока для фантика
        testFile.close();
//*Закрытие потока для фантика
    }

    @Test
    public void testCD100_E772_MS4() throws IOException, InterruptedException {
        FileWriter cleanFile = new FileWriter("TestRecordCD100_E772_MS4.txt",false);
        cleanFile.close();

        CamDrivePageObject CamDrivePageObject = CamDrivePageObjectFactory.get(driver);
        CamDrivePageObject.authorizationOnCamdrive();
        CamDrivePageObject.choiseCD100_E772_MS4();

        if (currentHour <10){
            currentHourCONVERTED = "0"+currentHour;
        }else currentHourCONVERTED = Integer.toString(currentHour);
        //==========================================================
        if (currentMonth <10){
            currentMonthCONVERTED = "0"+currentMonth;
        }else currentMonthCONVERTED = Integer.toString(currentMonth);
        //==========================================================
//обработка ситуации с полуночью
        if(currentHour <0){
            currentHour = 23;
            currentDay = currentDay -1;

            if (currentDay <10){
                currentDayCONVERTED = ""+(currentDay);
            }else currentDayCONVERTED = Integer.toString(currentDay);

            CamDrivePageObject.choiseDay(currentDayCONVERTED,currentMonthCONVERTED);
        }else {

            if (currentDay <10){
                currentDayCONVERTED = ""+(currentDay);
            }else currentDayCONVERTED = Integer.toString(currentDay);

            CamDrivePageObject.choiseTheCurrentDay();
        }
// \обработка ситуации с полуночью
//Открытие потока для фантика
        System.out.println("\nData: "+currentYear+"/"+currentMonth+"/"+currentDay+"\nStart test in "+currentHour+" hour and 00 minutes ");
        FileWriter testFile = new FileWriter("TestRecordCD100_E772_MS4.txt",false);
        testFile.write("Data: "+currentYear+"/"+currentMonth+"/"+currentDay+"\nStart test in "+currentHour+" hour and 00 minutes \n");
        System.out.println("Camera: CD100_E772_MS4 \n");
        testFile.write("Camera: CD100_E772_MS4 \n");
        System.out.println("Imitation web Android");
        testFile.write("Imitation web Android");
//*Открытие потока для фантика



        CamDrivePageObject.clickHour(
                currentDayCONVERTED,
                currentMonthCONVERTED,
                currentHourCONVERTED);


        int currentFirstMinute = -5;
        int currentLastMinute = -1;
//TODO: Для камер с включенной склейкой требуется предусмотреть таймаут подготовки блока с записью минимум в 369 секунд
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

//Фантик
            System.out.println("\n Block:"+(m + 1)+" Play archive block of "+currentHourCONVERTED+":00h. "+currentFirstMinuteCONVERTED+"min-"+currentLastMinuteCONVERTED+"min\n");
            testFile.write("\n Block:"+(m + 1)+" Play archive block of "+currentHourCONVERTED+":00h. "+currentFirstMinuteCONVERTED+"min-"+currentLastMinuteCONVERTED+"min\n");
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

                CamDrivePageObject.clickBackOnMinuteScreenAndroid();

                continue;
            }
            CamDrivePageObject.clickOnVideoForm();
            String attribute = CamDrivePageObject.getTime("max");


//Фантик
            System.out.println(attribute+" sec");
            testFile.write(attribute+" sec\n");
//*Фантик
            CamDrivePageObject.clickCloseButtonOnPlayArchiveScreen();
            CamDrivePageObject.clickBackOnMinuteScreenAndroid();
        }
        CamDrivePageObject.clickBackOnHourScreenCD100_E75A();
//Закрытие потока для фантика
        testFile.close();
//*Закрытие потока для фантика
    }

    @Test
    public void testCD100_E778_MS5() throws IOException, InterruptedException {
        FileWriter cleanFile = new FileWriter("TestRecordCD100_E778_MS5.txt",false);
        cleanFile.close();

        CamDrivePageObject CamDrivePageObject = CamDrivePageObjectFactory.get(driver);
        CamDrivePageObject.authorizationOnCamdrive();
        CamDrivePageObject.choiseCD100_E778_MS5();

        if (currentHour <10){
            currentHourCONVERTED = "0"+currentHour;
        }else currentHourCONVERTED = Integer.toString(currentHour);
        //==========================================================
        if (currentMonth <10){
            currentMonthCONVERTED = "0"+currentMonth;
        }else currentMonthCONVERTED = Integer.toString(currentMonth);
        //==========================================================
//обработка ситуации с полуночью
        if(currentHour <0){
            currentHour = 23;
            currentDay = currentDay -1;

            if (currentDay <10){
                currentDayCONVERTED = ""+(currentDay);
            }else currentDayCONVERTED = Integer.toString(currentDay);

            CamDrivePageObject.choiseDay(currentDayCONVERTED,currentMonthCONVERTED);
        }else {

            if (currentDay <10){
                currentDayCONVERTED = ""+(currentDay);
            }else currentDayCONVERTED = Integer.toString(currentDay);

            CamDrivePageObject.choiseTheCurrentDay();
        }
// \обработка ситуации с полуночью
//Открытие потока для фантика
        System.out.println("\nData: "+currentYear+"/"+currentMonth+"/"+currentDay+"\nStart test in "+currentHour+" hour and 00 minutes ");
        FileWriter testFile = new FileWriter("TestRecordCD100_E778_MS5.txt",false);
        testFile.write("Data: "+currentYear+"/"+currentMonth+"/"+currentDay+"\nStart test in "+currentHour+" hour and 00 minutes \n");
        System.out.println("Camera: CD100_E778_MS5 \n");
        testFile.write("Camera: CD100_E778_MS5 \n");
        System.out.println("Imitation web Android");
        testFile.write("Imitation web Android");
//*Открытие потока для фантика



        CamDrivePageObject.clickHour(
                currentDayCONVERTED,
                currentMonthCONVERTED,
                currentHourCONVERTED);


        int currentFirstMinute = -5;
        int currentLastMinute = -1;
//TODO: Для камер с включенной склейкой требуется предусмотреть таймаут подготовки блока с записью минимум в 369 секунд
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

//Фантик
            System.out.println("\n Block:"+(m + 1)+" Play archive block of "+currentHourCONVERTED+":00h. "+currentFirstMinuteCONVERTED+"min-"+currentLastMinuteCONVERTED+"min\n");
            testFile.write("\n Block:"+(m + 1)+" Play archive block of "+currentHourCONVERTED+":00h. "+currentFirstMinuteCONVERTED+"min-"+currentLastMinuteCONVERTED+"min\n");
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

                CamDrivePageObject.clickBackOnMinuteScreenAndroid();

                continue;
            }
            CamDrivePageObject.clickOnVideoForm();
            String attribute = CamDrivePageObject.getTime("max");


//Фантик
            System.out.println(attribute+" sec");
            testFile.write(attribute+" sec\n");
//*Фантик
            CamDrivePageObject.clickCloseButtonOnPlayArchiveScreen();
            CamDrivePageObject.clickBackOnMinuteScreenAndroid();
        }
        CamDrivePageObject.clickBackOnHourScreenCD100_E75A();
//Закрытие потока для фантика
        testFile.close();
//*Закрытие потока для фантика
    }

    @Test
    public void testCD310_2E51_MS4() throws IOException, InterruptedException {
        FileWriter cleanFile = new FileWriter("TestRecordCD310_2E51_MS4.txt",false);
        cleanFile.close();

        CamDrivePageObject CamDrivePageObject = CamDrivePageObjectFactory.get(driver);
        CamDrivePageObject.authorizationOnCamdrive();
        CamDrivePageObject.choiseCD310_2E51_MS4_DEV();

        if (currentHour <10){
            currentHourCONVERTED = "0"+currentHour;
        }else currentHourCONVERTED = Integer.toString(currentHour);
        //==========================================================
        if (currentMonth <10){
            currentMonthCONVERTED = "0"+currentMonth;
        }else currentMonthCONVERTED = Integer.toString(currentMonth);
        //==========================================================
//обработка ситуации с полуночью
        if(currentHour <0){
            currentHour = 23;
            currentDay = currentDay -1;

            if (currentDay <10){
                currentDayCONVERTED = ""+(currentDay);
            }else currentDayCONVERTED = Integer.toString(currentDay);

            CamDrivePageObject.choiseDay(currentDayCONVERTED,currentMonthCONVERTED);
        }else {

            if (currentDay <10){
                currentDayCONVERTED = ""+(currentDay);
            }else currentDayCONVERTED = Integer.toString(currentDay);

            CamDrivePageObject.choiseTheCurrentDay();
        }
// \обработка ситуации с полуночью
//Открытие потока для фантика
        System.out.println("\nData: "+currentYear+"/"+currentMonth+"/"+currentDay+"\nStart test in "+currentHour+" hour and 00 minutes ");
        FileWriter testFile = new FileWriter("TestRecordCD310_2E51_MS4.txt",false);
        testFile.write("Data: "+currentYear+"/"+currentMonth+"/"+currentDay+"\nStart test in "+currentHour+" hour and 00 minutes \n");
        System.out.println("Camera: CD310_2E51_MS4 \n");
        testFile.write("Camera: CD310_2E51_MS4 \n");
        System.out.println("Imitation web Android");
        testFile.write("Imitation web Android");
//*Открытие потока для фантика



        CamDrivePageObject.clickHour(
                currentDayCONVERTED,
                currentMonthCONVERTED,
                currentHourCONVERTED);


        int currentFirstMinute = -5;
        int currentLastMinute = -1;
//TODO: Для камер с включенной склейкой требуется предусмотреть таймаут подготовки блока с записью минимум в 369 секунд
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

//Фантик
            System.out.println("\n Block:"+(m + 1)+" Play archive block of "+currentHourCONVERTED+":00h. "+currentFirstMinuteCONVERTED+"min-"+currentLastMinuteCONVERTED+"min\n");
            testFile.write("\n Block:"+(m + 1)+" Play archive block of "+currentHourCONVERTED+":00h. "+currentFirstMinuteCONVERTED+"min-"+currentLastMinuteCONVERTED+"min\n");
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

                CamDrivePageObject.clickBackOnMinuteScreenAndroid();

                continue;
            }
            CamDrivePageObject.clickOnVideoForm();
            String attribute = CamDrivePageObject.getTime("max");


//Фантик
            System.out.println(attribute+" sec");
            testFile.write(attribute+" sec\n");
//*Фантик
            CamDrivePageObject.clickCloseButtonOnPlayArchiveScreen();
            CamDrivePageObject.clickBackOnMinuteScreenAndroid();
        }
        CamDrivePageObject.clickBackOnHourScreenCD100_E75A();
//Закрытие потока для фантика
        testFile.close();
//*Закрытие потока для фантика
    }

    @Test
    public void testCD320_AA06_MS3() throws IOException, InterruptedException {
        FileWriter cleanFile = new FileWriter("TestRecordCD320_AA06_MS3_DEV.txt",false);
        cleanFile.close();

        CamDrivePageObject CamDrivePageObject = CamDrivePageObjectFactory.get(driver);
        CamDrivePageObject.authorizationOnCamdrive();
        CamDrivePageObject.choiseCD320_AA06_MS3_DEV();

        if (currentHour <10){
            currentHourCONVERTED = "0"+currentHour;
        }else currentHourCONVERTED = Integer.toString(currentHour);
        //==========================================================
        if (currentMonth <10){
            currentMonthCONVERTED = "0"+currentMonth;
        }else currentMonthCONVERTED = Integer.toString(currentMonth);
        //==========================================================
//обработка ситуации с полуночью
        if(currentHour <0){
            currentHour = 23;
            currentDay = currentDay -1;

            if (currentDay <10){
                currentDayCONVERTED = ""+(currentDay);
            }else currentDayCONVERTED = Integer.toString(currentDay);

            CamDrivePageObject.choiseDay(currentDayCONVERTED,currentMonthCONVERTED);
        }else {

            if (currentDay <10){
                currentDayCONVERTED = ""+(currentDay);
            }else currentDayCONVERTED = Integer.toString(currentDay);

            CamDrivePageObject.choiseTheCurrentDay();
        }
// \обработка ситуации с полуночью
//Открытие потока для фантика
        System.out.println("\nData: "+currentYear+"/"+currentMonth+"/"+currentDay+"\nStart test in "+currentHour+" hour and 00 minutes ");
        FileWriter testFile = new FileWriter("TestRecordCD320_AA06_MS3_DEV.txt",false);
        testFile.write("Data: "+currentYear+"/"+currentMonth+"/"+currentDay+"\nStart test in "+currentHour+" hour and 00 minutes \n");
        System.out.println("Camera: CD320_AA06_MS3 \n");
        testFile.write("Camera: CD320_AA06_MS3 \n");
        System.out.println("Imitation web Android");
        testFile.write("Imitation web Android");
//*Открытие потока для фантика



        CamDrivePageObject.clickHour(
                currentDayCONVERTED,
                currentMonthCONVERTED,
                currentHourCONVERTED);


        int currentFirstMinute = -5;
        int currentLastMinute = -1;
//TODO: Для камер с включенной склейкой требуется предусмотреть таймаут подготовки блока с записью минимум в 369 секунд
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

//Фантик
            System.out.println("\n Block:"+(m + 1)+" Play archive block of "+currentHourCONVERTED+":00h. "+currentFirstMinuteCONVERTED+"min-"+currentLastMinuteCONVERTED+"min\n");
            testFile.write("\n Block:"+(m + 1)+" Play archive block of "+currentHourCONVERTED+":00h. "+currentFirstMinuteCONVERTED+"min-"+currentLastMinuteCONVERTED+"min\n");
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

                CamDrivePageObject.clickBackOnMinuteScreenAndroid();

                continue;
            }
            CamDrivePageObject.clickOnVideoForm();
            String attribute = CamDrivePageObject.getTime("max");


//Фантик
            System.out.println(attribute+" sec");
            testFile.write(attribute+" sec\n");
//*Фантик
            CamDrivePageObject.clickCloseButtonOnPlayArchiveScreen();
            CamDrivePageObject.clickBackOnMinuteScreenAndroid();
        }
        CamDrivePageObject.clickBackOnHourScreenCD100_E75A();
//Закрытие потока для фантика
        testFile.close();
//*Закрытие потока для фантика
    }

    @Test
    public void testCD320_AA78_MS5() throws IOException, InterruptedException {
        FileWriter cleanFile = new FileWriter("TestRecordCD320_AA78_MS5.txt",false);
        cleanFile.close();

        CamDrivePageObject CamDrivePageObject = CamDrivePageObjectFactory.get(driver);
        CamDrivePageObject.authorizationOnCamdrive();
        CamDrivePageObject.choiseCD320_AA78_MS5();

        if (currentHour <10){
            currentHourCONVERTED = "0"+currentHour;
        }else currentHourCONVERTED = Integer.toString(currentHour);
        //==========================================================
        if (currentMonth <10){
            currentMonthCONVERTED = "0"+currentMonth;
        }else currentMonthCONVERTED = Integer.toString(currentMonth);
        //==========================================================
//обработка ситуации с полуночью
        if(currentHour <0){
            currentHour = 23;
            currentDay = currentDay -1;

            if (currentDay <10){
                currentDayCONVERTED = ""+(currentDay);
            }else currentDayCONVERTED = Integer.toString(currentDay);

            CamDrivePageObject.choiseDay(currentDayCONVERTED,currentMonthCONVERTED);
        }else {

            if (currentDay <10){
                currentDayCONVERTED = ""+(currentDay);
            }else currentDayCONVERTED = Integer.toString(currentDay);

            CamDrivePageObject.choiseTheCurrentDay();
        }
// \обработка ситуации с полуночью
//Открытие потока для фантика
        System.out.println("\nData: "+currentYear+"/"+currentMonth+"/"+currentDay+"\nStart test in "+currentHour+" hour and 00 minutes ");
        FileWriter testFile = new FileWriter("TestRecordCD320_AA78_MS5.txt",false);
        testFile.write("Data: "+currentYear+"/"+currentMonth+"/"+currentDay+"\nStart test in "+currentHour+" hour and 00 minutes \n");
        System.out.println("Camera: CD320_AA78_MS5 \n");
        testFile.write("Camera: CD320_AA78_MS5 \n");
        System.out.println("Imitation web Android");
        testFile.write("Imitation web Android");
//*Открытие потока для фантика



        CamDrivePageObject.clickHour(
                currentDayCONVERTED,
                currentMonthCONVERTED,
                currentHourCONVERTED);


        int currentFirstMinute = -5;
        int currentLastMinute = -1;
//TODO: Для камер с включенной склейкой требуется предусмотреть таймаут подготовки блока с записью минимум в 369 секунд
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

//Фантик
            System.out.println("\n Block:"+(m + 1)+" Play archive block of "+currentHourCONVERTED+":00h. "+currentFirstMinuteCONVERTED+"min-"+currentLastMinuteCONVERTED+"min\n");
            testFile.write("\n Block:"+(m + 1)+" Play archive block of "+currentHourCONVERTED+":00h. "+currentFirstMinuteCONVERTED+"min-"+currentLastMinuteCONVERTED+"min\n");
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

                CamDrivePageObject.clickBackOnMinuteScreenAndroid();

                continue;
            }
            CamDrivePageObject.clickOnVideoForm();
            String attribute = CamDrivePageObject.getTime("max");


//Фантик
            System.out.println(attribute+" sec");
            testFile.write(attribute+" sec\n");
//*Фантик
            CamDrivePageObject.clickCloseButtonOnPlayArchiveScreen();
            CamDrivePageObject.clickBackOnMinuteScreenAndroid();
        }
        CamDrivePageObject.clickBackOnHourScreenCD100_E75A();
//Закрытие потока для фантика
        testFile.close();
//*Закрытие потока для фантика
    }

    @Test
    public void testCD600_EF78_MS6() throws IOException, InterruptedException {
        FileWriter cleanFile = new FileWriter("TestRecordCD600_EF78_MS6_SERV.txt",false);
        cleanFile.close();

        CamDrivePageObject CamDrivePageObject = CamDrivePageObjectFactory.get(driver);
        CamDrivePageObject.authorizationOnCamdrive();
        CamDrivePageObject.choiseCD600_EF78_MS6_SERV();

        if (currentHour <10){
            currentHourCONVERTED = "0"+currentHour;
        }else currentHourCONVERTED = Integer.toString(currentHour);
        //==========================================================
        if (currentMonth <10){
            currentMonthCONVERTED = "0"+currentMonth;
        }else currentMonthCONVERTED = Integer.toString(currentMonth);
        //==========================================================
//обработка ситуации с полуночью
        if(currentHour <0){
            currentHour = 23;
            currentDay = currentDay -1;

            if (currentDay <10){
                currentDayCONVERTED = ""+(currentDay);
            }else currentDayCONVERTED = Integer.toString(currentDay);

            CamDrivePageObject.choiseDay(currentDayCONVERTED,currentMonthCONVERTED);
        }else {

            if (currentDay <10){
                currentDayCONVERTED = ""+(currentDay);
            }else currentDayCONVERTED = Integer.toString(currentDay);

            CamDrivePageObject.choiseTheCurrentDay();
        }
// \обработка ситуации с полуночью
//Открытие потока для фантика
        System.out.println("\nData: "+currentYear+"/"+currentMonth+"/"+currentDay+"\nStart test in "+currentHour+" hour and 00 minutes ");
        FileWriter testFile = new FileWriter("TestRecordCD600_EF78_MS6_SERV.txt",false);
        testFile.write("Data: "+currentYear+"/"+currentMonth+"/"+currentDay+"\nStart test in "+currentHour+" hour and 00 minutes \n");
        System.out.println("Camera: CD600_EF78_MS6 \n");
        testFile.write("Camera: CD600_EF78_MS6 \n");
        System.out.println("Imitation web Android");
        testFile.write("Imitation web Android");
//*Открытие потока для фантика



        CamDrivePageObject.clickHour(
                currentDayCONVERTED,
                currentMonthCONVERTED,
                currentHourCONVERTED);


        int currentFirstMinute = -5;
        int currentLastMinute = -1;
//TODO: Для камер с включенной склейкой требуется предусмотреть таймаут подготовки блока с записью минимум в 369 секунд
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

//Фантик
            System.out.println("\n Block:"+(m + 1)+" Play archive block of "+currentHourCONVERTED+":00h. "+currentFirstMinuteCONVERTED+"min-"+currentLastMinuteCONVERTED+"min\n");
            testFile.write("\n Block:"+(m + 1)+" Play archive block of "+currentHourCONVERTED+":00h. "+currentFirstMinuteCONVERTED+"min-"+currentLastMinuteCONVERTED+"min\n");
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

                CamDrivePageObject.clickBackOnMinuteScreenAndroid();

                continue;
            }
            CamDrivePageObject.clickOnVideoForm();
            String attribute = CamDrivePageObject.getTime("max");


//Фантик
            System.out.println(attribute+" sec");
            testFile.write(attribute+" sec\n");
//*Фантик
            CamDrivePageObject.clickCloseButtonOnPlayArchiveScreen();
            CamDrivePageObject.clickBackOnMinuteScreenAndroid();
        }
        CamDrivePageObject.clickBackOnHourScreenCD100_E75A();
//Закрытие потока для фантика
        testFile.close();
//*Закрытие потока для фантика
    }

    @Test
    public void testCD630_910D_MS6() throws IOException, InterruptedException {
        FileWriter cleanFile = new FileWriter("TestRecordCD630_910D_MS6_DEV.txt",false);
        cleanFile.close();

        CamDrivePageObject CamDrivePageObject = CamDrivePageObjectFactory.get(driver);
        CamDrivePageObject.authorizationOnCamdrive();
        CamDrivePageObject.choiseCD630_910D_MS6_DEV();

        if (currentHour <10){
            currentHourCONVERTED = "0"+currentHour;
        }else currentHourCONVERTED = Integer.toString(currentHour);
        //==========================================================
        if (currentMonth <10){
            currentMonthCONVERTED = "0"+currentMonth;
        }else currentMonthCONVERTED = Integer.toString(currentMonth);
        //==========================================================
//обработка ситуации с полуночью
        if(currentHour <0){
            currentHour = 23;
            currentDay = currentDay -1;

            if (currentDay <10){
                currentDayCONVERTED = ""+(currentDay);
            }else currentDayCONVERTED = Integer.toString(currentDay);

            CamDrivePageObject.choiseDay(currentDayCONVERTED,currentMonthCONVERTED);
        }else {

            if (currentDay <10){
                currentDayCONVERTED = ""+(currentDay);
            }else currentDayCONVERTED = Integer.toString(currentDay);

            CamDrivePageObject.choiseTheCurrentDay();
        }
// \обработка ситуации с полуночью
//Открытие потока для фантика
        System.out.println("\nData: "+currentYear+"/"+currentMonth+"/"+currentDay+"\nStart test in "+currentHour+" hour and 00 minutes ");
        FileWriter testFile = new FileWriter("TestRecordCD630_910D_MS6_DEV.txt",false);
        testFile.write("Data: "+currentYear+"/"+currentMonth+"/"+currentDay+"\nStart test in "+currentHour+" hour and 00 minutes \n");
        System.out.println("Camera: CD630_910D_MS6 \n");
        testFile.write("Camera: CD630_910D_MS6 \n");
        System.out.println("Imitation web Android");
        testFile.write("Imitation web Android");
//*Открытие потока для фантика



        CamDrivePageObject.clickHour(
                currentDayCONVERTED,
                currentMonthCONVERTED,
                currentHourCONVERTED);


        int currentFirstMinute = -5;
        int currentLastMinute = -1;
//TODO: Для камер с включенной склейкой требуется предусмотреть таймаут подготовки блока с записью минимум в 369 секунд
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

//Фантик
            System.out.println("\n Block:"+(m + 1)+" Play archive block of "+currentHourCONVERTED+":00h. "+currentFirstMinuteCONVERTED+"min-"+currentLastMinuteCONVERTED+"min\n");
            testFile.write("\n Block:"+(m + 1)+" Play archive block of "+currentHourCONVERTED+":00h. "+currentFirstMinuteCONVERTED+"min-"+currentLastMinuteCONVERTED+"min\n");
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

                CamDrivePageObject.clickBackOnMinuteScreenAndroid();

                continue;
            }
            CamDrivePageObject.clickOnVideoForm();
            String attribute = CamDrivePageObject.getTime("max");


//Фантик
            System.out.println(attribute+" sec");
            testFile.write(attribute+" sec\n");
//*Фантик
            CamDrivePageObject.clickCloseButtonOnPlayArchiveScreen();
            CamDrivePageObject.clickBackOnMinuteScreenAndroid();
        }
        CamDrivePageObject.clickBackOnHourScreenCD100_E75A();
//Закрытие потока для фантика
        testFile.close();
//*Закрытие потока для фантика
    }

    @Test
    public void testN1001_3A00_bwd() throws IOException, InterruptedException {
        FileWriter cleanFile = new FileWriter("TestRecordN1001_3A00_bwd.txt",false);
        cleanFile.close();

        CamDrivePageObject CamDrivePageObject = CamDrivePageObjectFactory.get(driver);
        CamDrivePageObject.authorizationOnCamdrive();
        CamDrivePageObject.choiseN1001_3A00_bwd();

        if (currentHour <10){
            currentHourCONVERTED = "0"+currentHour;
        }else currentHourCONVERTED = Integer.toString(currentHour);
        //==========================================================
        if (currentMonth <10){
            currentMonthCONVERTED = "0"+currentMonth;
        }else currentMonthCONVERTED = Integer.toString(currentMonth);
        //==========================================================
//обработка ситуации с полуночью
        if(currentHour <0){
            currentHour = 23;
            currentDay = currentDay -1;

            if (currentDay <10){
                currentDayCONVERTED = ""+(currentDay);
            }else currentDayCONVERTED = Integer.toString(currentDay);

            CamDrivePageObject.choiseDay(currentDayCONVERTED,currentMonthCONVERTED);
        }else {

            if (currentDay <10){
                currentDayCONVERTED = ""+(currentDay);
            }else currentDayCONVERTED = Integer.toString(currentDay);

            CamDrivePageObject.choiseTheCurrentDay();
        }
// \обработка ситуации с полуночью
//Открытие потока для фантика
        System.out.println("\nData: "+currentYear+"/"+currentMonth+"/"+currentDay+"\nStart test in "+currentHour+" hour and 00 minutes ");
        FileWriter testFile = new FileWriter("TestRecordN1001_3A00_bwd.txt",false);
        testFile.write("Data: "+currentYear+"/"+currentMonth+"/"+currentDay+"\nStart test in "+currentHour+" hour and 00 minutes \n");
        System.out.println("Camera: N1001_3A00_bwd \n");
        testFile.write("Camera: N1001_3A00_bwd \n");
        System.out.println("Imitation web Android");
        testFile.write("Imitation web Android");
//*Открытие потока для фантика



        CamDrivePageObject.clickHour(
                currentDayCONVERTED,
                currentMonthCONVERTED,
                currentHourCONVERTED);


        int currentFirstMinute = -5;
        int currentLastMinute = -1;
//TODO: Для камер с включенной склейкой требуется предусмотреть таймаут подготовки блока с записью минимум в 369 секунд
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

//Фантик
            System.out.println("\n Block:"+(m + 1)+" Play archive block of "+currentHourCONVERTED+":00h. "+currentFirstMinuteCONVERTED+"min-"+currentLastMinuteCONVERTED+"min\n");
            testFile.write("\n Block:"+(m + 1)+" Play archive block of "+currentHourCONVERTED+":00h. "+currentFirstMinuteCONVERTED+"min-"+currentLastMinuteCONVERTED+"min\n");
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

                CamDrivePageObject.clickBackOnMinuteScreenAndroid();

                continue;
            }
            CamDrivePageObject.clickOnVideoForm();
            String attribute = CamDrivePageObject.getTime("max");


//Фантик
            System.out.println(attribute+" sec");
            testFile.write(attribute+" sec\n");
//*Фантик
            CamDrivePageObject.clickCloseButtonOnPlayArchiveScreen();
            CamDrivePageObject.clickBackOnMinuteScreenAndroid();
        }
        CamDrivePageObject.clickBackOnHourScreenCD100_E75A();
//Закрытие потока для фантика
        testFile.close();
//*Закрытие потока для фантика
    }
}
