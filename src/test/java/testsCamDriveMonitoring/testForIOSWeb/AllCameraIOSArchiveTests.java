package testsCamDriveMonitoring.testForIOSWeb;

import lib.CoreTestCase;
import lib.Platform;
import lib.logging.SuperVisor;
import lib.ui.CamDrivePageObject;
import lib.ui.factories.CamDrivePageObjectFactory;
import org.junit.Test;

import java.io.File;
import java.io.FileWriter;
import java.util.Calendar;

//@SuppressWarnings("ALL")
public class AllCameraIOSArchiveTests extends CoreTestCase {
    private String currentFirstMinuteCONVERTED;
    private String currentLastMinuteCONVERTED;
    private String currentHourCONVERTED;
    private String currentDayCONVERTED;
    private String currentMonthCONVERTED;
    private String keyWord = "Imitation web iOS";
    private int currentYear = Calendar.getInstance().getWeekYear();
    private int currentHour = Calendar.getInstance().getTime().getHours() - 1;
    private int currentDay = Calendar.getInstance().getTime().getDate();
    private int currentMonth = Calendar.getInstance().getTime().getMonth() + 1;
    private int currentMinute = 59;
    private int tick = 10;


    @Test
    public void testCD100_E75A_MS3_IOS() throws Exception {
        SuperVisor operation = new SuperVisor();

        String cameraName = "Camera: CD100_E75A_MS3";
        String errorLogFile = "ErrorRecordIOSCD100_E75A_MS3.txt";

        FileWriter cleanFile = new FileWriter("TestRecordIOSCD100_E75A_MS3.txt",false);
        cleanFile.close();
//+
        File errorLog = new File(errorLogFile);
        errorLog.delete();


        CamDrivePageObject CamDrivePageObject = CamDrivePageObjectFactory.get(driver);
        CamDrivePageObject.checkScheldue(currentHour,"aee40e829262b7930f529c4fee6d326a");
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
            currentHourCONVERTED = "23";
            currentDay = currentDay -1;

            if (currentDay <10){
                currentDayCONVERTED = ""+(currentDay);
            }else currentDayCONVERTED = Integer.toString(currentDay);

            CamDrivePageObject.choiseDay(currentDayCONVERTED,currentMonthCONVERTED);
        }else {

            if (currentDay <10){
                currentDayCONVERTED = ""+(currentDay);
            }else currentDayCONVERTED = Integer.toString(currentDay);
//TODO: Доделать ШАПКУ
            CamDrivePageObject.choiseTheCurrentDay(
                    errorLogFile,
                    cameraName,
                    currentYear,
                    currentMonth,
                    currentDay,
                    currentHour);
        }

// \обработка ситуации с полуночью
//Открытие потока для фантика
//======================================================Сделать красиво!
        System.out.println("\nData: "+currentYear+"/"+currentMonth+"/"+currentDay+"\nStart test in "+currentHour+" hour and "+currentMinute+" minutes ");
        FileWriter testFile = new FileWriter("TestRecordIOSCD100_E75A_MS3.txt",false);
        testFile.write("Data: "+currentYear+"/"+currentMonth+"/"+currentDay+"\nStart test in "+currentHour+" hour and "+currentMinute+" minutes \n");
        System.out.println(cameraName+"\n");
        testFile.write(cameraName+"\n");
        System.out.println("Imitation web iPhone");
        testFile.write("Imitation web iPhone");


//*Открытие потока для фантика + определение  платформы

        int currentFirstMinute = -tick;
        int currentLastMinute = -1;

//--------------------------------------------------------------------------------------------------------------------
        //Удаление репорта ошибок

        //Удаление репорта ошибок

        int schedule = CamDrivePageObject.returnCurrentScheldueStatus(currentHour,"aee40e829262b7930f529c4fee6d326a");
//-----------------------------------------------------------------------------------------------------------
        //Открытие потока на запись Error файла
        FileWriter errorFile = new FileWriter(errorLogFile,true);
        errorFile.write("Data: "+currentYear+"/"+currentMonth+"/"+currentDay+"\nStart test in "+currentHour+" hour and 00 minutes \n"+"Camera: CD100_E75A_MS3 \n"+"Imitation web iOS");
//-----------------------------------------------------------------------------------------------------------
        for (int m = 0; m < 6; m ++){

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
            System.out.println("\n Block:"+(m + 1)+" Play archive block of "+currentHourCONVERTED+":00h. "+currentFirstMinuteCONVERTED+"min-"+currentLastMinuteCONVERTED+"min\n");
            testFile.write("\n Block:"+(m + 1)+" Play archive block of "+currentHourCONVERTED+":00h. "+currentFirstMinuteCONVERTED+"min-"+currentLastMinuteCONVERTED+"min\n");
//*Фантик

            //CamDrivePageObject.scrollWebPageTitleElementNotVisible("id:2019-"+currentMonthCONVERTED+"-"+currentDayCONVERTED+"-"+currentHourCONVERTED+"-"+currentFirstMinuteCONVERTED+"-00_2019-"+currentMonthCONVERTED+"-"+currentDayCONVERTED+"-"+currentHourCONVERTED+"-"+currentLastMinuteCONVERTED+"-59","WTF",5);

            if (currentHour >11){
                CamDrivePageObject.scrollIntoView();
            }
//            CamDrivePageObject.checkLoadVideoPlayerForIosMW();

//-----------------------------------------------------------------------------------------------------------
            try{
                CamDrivePageObject.clickMinute(
                        currentDayCONVERTED,
                        currentMonthCONVERTED,
                        currentHourCONVERTED,
                        currentFirstMinuteCONVERTED,
                        currentLastMinuteCONVERTED);
            }catch (RuntimeException e){
//Фантик
                if((schedule == 1)||(schedule == 3)) {
                    System.out.println("No records. Block " + currentHourCONVERTED + ":00h. " + currentFirstMinuteCONVERTED + "min-" + currentLastMinuteCONVERTED + "min\n");
                    testFile.write("No records. Block: " + currentHourCONVERTED + ":00h. " + currentFirstMinuteCONVERTED + "min-" + currentLastMinuteCONVERTED + "min\n");
                }else{
                    errorFile.write("No records. Block: " + currentHourCONVERTED + ":00h. " + currentFirstMinuteCONVERTED + "min-" + currentLastMinuteCONVERTED + "min\n");
                    System.out.println("No records. Block " + currentHourCONVERTED + ":00h. " + currentFirstMinuteCONVERTED + "min-" + currentLastMinuteCONVERTED + "min\n");
                }
//*Фантик
                continue;
            }
//-----------------------------------------------------------------------------------------------------------------------
            CamDrivePageObject.loadArchiveVideoIOS(); //добавить трайклик

            try {
                CamDrivePageObject.checkLoadVideoPlayerForIosMW();
            }catch (RuntimeException e){
//Фантик
                if((schedule == 1)||(schedule == 3)) {
                    System.out.println("Error load archive video. Block: " + currentHourCONVERTED + ":00h. " + currentFirstMinuteCONVERTED + "min-" + currentLastMinuteCONVERTED + "min\n");
                    testFile.write("Error load archive video. Block: " + currentHourCONVERTED + ":00h. " + currentFirstMinuteCONVERTED + "min-" + currentLastMinuteCONVERTED + "min\n");
                }else{
                    System.out.println("Error load archive video. Block: " + currentHourCONVERTED + ":00h. " + currentFirstMinuteCONVERTED + "min-" + currentLastMinuteCONVERTED + "min\n");
                    errorFile.write("Error load archive video. Block: " + currentHourCONVERTED + ":00h. " + currentFirstMinuteCONVERTED + "min-" + currentLastMinuteCONVERTED + "min\n");
                }
//*Фантик
                CamDrivePageObject.clickBackOnMinuteScreenIOS();
                continue;
            }
//-----------------------------------------------------------------------------------------------------------------------
            //TODO : JSE в тесте
            Object attribute = CamDrivePageObject.getTimeDurationVideoForIOSArchive(); //допилить джаваскрипт

            int time = 0;

            String result = String.valueOf(attribute);
            int indexEndResult = result.lastIndexOf(".");

            if(indexEndResult == -1) {
                try {
                    time = Integer.parseInt(result);
                } catch (Exception e) {
                    System.out.println("Cannot format type int current element :" + attribute);
                    errorFile.write("JS return incorrect type variable: "+ attribute);
                }
            } else{
                time = Integer.parseInt(result.substring(0,indexEndResult));
            }
            if((schedule == 0) & (time<590)){
                errorFile.write("Current block: " + currentHourCONVERTED + ":00h. " + currentFirstMinuteCONVERTED + "min-" + currentLastMinuteCONVERTED + "min\n"+"Record is less then 590 second. Current record is "+ time + "\n");
            }

            System.out.println(attribute+" sec ");
            System.out.println("RESULT!   "+result);
            System.out.println("Parse and cut. Result "+ time);
            testFile.write(attribute+" sec\n");
            CamDrivePageObject.clickBackOnMinuteScreenIOS();
        }
//Закрытие потока для фантика
        testFile.close();
        errorFile.close();
//*Закрытие потока для фантика

        operation.checkerEmptyFile(keyWord, errorLogFile);
    }

    @Test
    public void testCD100_E772_MS4_IOS() throws Exception {
        String cameraName = "Camera: CD100_E772_MS4";
        String errorLogFile = "ErrorRecordIOSCD100_E772_MS4.txt";
        //Удаление репорта ошибок
        File errorLog = new File(errorLogFile);
        errorLog.delete();
        //Удаление репорта ошибок
        FileWriter cleanFile = new FileWriter("TestRecordIOSCD100_E772_MS4.txt",false);
        cleanFile.close();

        CamDrivePageObject CamDrivePageObject = CamDrivePageObjectFactory.get(driver);
        CamDrivePageObject.checkScheldue(currentHour,"95100e92696c2163bef3185cd29deff2");

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
            currentHourCONVERTED = "23";
            currentDay = currentDay -1;

            if (currentDay <10){
                currentDayCONVERTED = ""+(currentDay);
            }else currentDayCONVERTED = Integer.toString(currentDay);

            CamDrivePageObject.choiseDay(currentDayCONVERTED,currentMonthCONVERTED);
        }else {

            if (currentDay <10){
                currentDayCONVERTED = ""+(currentDay);
            }else currentDayCONVERTED = Integer.toString(currentDay);

            CamDrivePageObject.choiseTheCurrentDay(
                    errorLogFile,
                    cameraName,
                    currentYear,
                    currentMonth,
                    currentDay,
                    currentHour);
        }
//Открытие потока для фантика
//======================================================Сделать красиво!
        System.out.println("\nData: "+currentYear+"/"+currentMonth+"/"+currentDay+"\nStart test in "+currentHour+" hour and "+currentMinute+" minutes ");
        FileWriter testFile = new FileWriter("TestRecordIOSCD100_E772_MS4.txt",false);
        testFile.write("Data: "+currentYear+"/"+currentMonth+"/"+currentDay+"\nStart test in "+currentHour+" hour and "+currentMinute+" minutes \n");
        System.out.println(cameraName+" \n");
        testFile.write(cameraName+" \n");
        System.out.println("Imitation web iPhone");
        testFile.write("Imitation web iPhone");


//*Открытие потока для фантика + определение  платформы

        int currentFirstMinute = -tick;
        int currentLastMinute = -1;

//--------------------------------------------------------------------------------------------------------------------


        int schedule = CamDrivePageObject.returnCurrentScheldueStatus(currentHour,"95100e92696c2163bef3185cd29deff2");
//-----------------------------------------------------------------------------------------------------------
        //Открытие потока на запись Error файла
        FileWriter errorFile = new FileWriter(errorLogFile,true);
        errorFile.write("Data: "+currentYear+"/"+currentMonth+"/"+currentDay+"\nStart test in "+currentHour+" hour and 00 minutes \n"+"Camera: CD100_E772_MS4 \n"+"Imitation web iOS\n");
//-----------------------------------------------------------------------------------------------------------
        for (int m = 0; m < 6; m ++){

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
            System.out.println("\n Block:"+(m + 1)+" Play archive block of "+currentHourCONVERTED+":00h. "+currentFirstMinuteCONVERTED+"min-"+currentLastMinuteCONVERTED+"min\n");
            testFile.write("\n Block:"+(m + 1)+" Play archive block of "+currentHourCONVERTED+":00h. "+currentFirstMinuteCONVERTED+"min-"+currentLastMinuteCONVERTED+"min\n");
//*Фантик

            //CamDrivePageObject.scrollWebPageTitleElementNotVisible("id:2019-"+currentMonthCONVERTED+"-"+currentDayCONVERTED+"-"+currentHourCONVERTED+"-"+currentFirstMinuteCONVERTED+"-00_2019-"+currentMonthCONVERTED+"-"+currentDayCONVERTED+"-"+currentHourCONVERTED+"-"+currentLastMinuteCONVERTED+"-59","WTF",5);
//TODO :   исправить !!!
            if (currentHour >11){
                CamDrivePageObject.scrollIntoView();
            }
//            CamDrivePageObject.checkLoadVideoPlayerForIosMW();

//-----------------------------------------------------------------------------------------------------------
            try{
                CamDrivePageObject.clickMinute(
                        currentDayCONVERTED,
                        currentMonthCONVERTED,
                        currentHourCONVERTED,
                        currentFirstMinuteCONVERTED,
                        currentLastMinuteCONVERTED);
            }catch (RuntimeException e){
//Фантик
                if((schedule == 1)||(schedule == 3)) {
                    System.out.println("No records. Block " + currentHourCONVERTED + ":00h. " + currentFirstMinuteCONVERTED + "min-" + currentLastMinuteCONVERTED + "min\n");
                    testFile.write("No records. Block: " + currentHourCONVERTED + ":00h. " + currentFirstMinuteCONVERTED + "min-" + currentLastMinuteCONVERTED + "min\n");
                }else{
                    errorFile.write("No records. Block: " + currentHourCONVERTED + ":00h. " + currentFirstMinuteCONVERTED + "min-" + currentLastMinuteCONVERTED + "min\n");
                    System.out.println("No records. Block " + currentHourCONVERTED + ":00h. " + currentFirstMinuteCONVERTED + "min-" + currentLastMinuteCONVERTED + "min\n");
                }
//*Фантик
                continue;
            }
//-----------------------------------------------------------------------------------------------------------------------
            CamDrivePageObject.loadArchiveVideoIOS(); //добавить трайклик

            try {
                CamDrivePageObject.checkLoadVideoPlayerForIosMW();
            }catch (RuntimeException e){
//Фантик
                if((schedule == 1)||(schedule == 3)) {
                    System.out.println("Error load archive video. Block: " + currentHourCONVERTED + ":00h. " + currentFirstMinuteCONVERTED + "min-" + currentLastMinuteCONVERTED + "min\n");
                    testFile.write("Error load archive video. Block: " + currentHourCONVERTED + ":00h. " + currentFirstMinuteCONVERTED + "min-" + currentLastMinuteCONVERTED + "min\n");
                }else{
                    System.out.println("Error load archive video. Block: " + currentHourCONVERTED + ":00h. " + currentFirstMinuteCONVERTED + "min-" + currentLastMinuteCONVERTED + "min\n");
                    errorFile.write("Error load archive video. Block: " + currentHourCONVERTED + ":00h. " + currentFirstMinuteCONVERTED + "min-" + currentLastMinuteCONVERTED + "min\n");
                }
//*Фантик
                CamDrivePageObject.clickBackOnMinuteScreenIOS();
                continue;
            }
//-----------------------------------------------------------------------------------------------------------------------
            //TODO : JSE в тесте
            Object attribute = CamDrivePageObject.getTimeDurationVideoForIOSArchive(); //допилить джаваскрипт

            int time = 0;

            String result = String.valueOf(attribute);
            int indexEndResult = result.lastIndexOf(".");

            if(indexEndResult == -1) {
                try {
                    time = Integer.parseInt(result);
                } catch (Exception e) {
                    System.out.println("Cannot format type int current element :" + attribute);
                    errorFile.write("JS return incorrect type variable: "+ attribute);
                }
            } else{
                time = Integer.parseInt(result.substring(0,indexEndResult));
            }
            if(((schedule == 0) & (time<590))){
                errorFile.write("Current block: " + currentHourCONVERTED + ":00h. " + currentFirstMinuteCONVERTED + "min-" + currentLastMinuteCONVERTED + "min\n"+"Record is less then 590 second. Current record is "+ time + "\n");
            }

            System.out.println(attribute+" sec ");
            System.out.println("RESULT!   "+result);
            System.out.println("Parse and cut. Result "+ time);
            testFile.write(attribute+" sec\n");
            CamDrivePageObject.clickBackOnMinuteScreenIOS();
        }
//Закрытие потока для фантика
        testFile.close();
        errorFile.close();
        SuperVisor operation = new SuperVisor();
        operation.checkerEmptyFile(keyWord, errorLogFile);

//*Закрытие потока для фантика
    }

    @Test
    public void testCD100_E778_MS5_IOS() throws Exception {
        String cameraName = "Camera: CD100_E778_MS5 ";
        String errorLogFile = "ErrorRecordIOSCD100_E778_MS5.txt";
        //Удаление репорта ошибок
        File errorLog = new File(errorLogFile);
        errorLog.delete();
        //Удаление репорта ошибок
        FileWriter cleanFile = new FileWriter("TestRecordIOSCD100_E778_MS5.txt",false);
        cleanFile.close();

        CamDrivePageObject CamDrivePageObject = CamDrivePageObjectFactory.get(driver);
        CamDrivePageObject.checkScheldue(currentHour,"aef64c10d39975425e711014dcb8a061");
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
            currentHourCONVERTED = "23";
            currentDay = currentDay -1;

            if (currentDay <10){
                currentDayCONVERTED = ""+(currentDay);
            }else currentDayCONVERTED = Integer.toString(currentDay);

            CamDrivePageObject.choiseDay(currentDayCONVERTED,currentMonthCONVERTED);
        }else {

            if (currentDay <10){
                currentDayCONVERTED = ""+(currentDay);
            }else currentDayCONVERTED = Integer.toString(currentDay);

            CamDrivePageObject.choiseTheCurrentDay(
                    errorLogFile,
                    cameraName,
                    currentYear,
                    currentMonth,
                    currentDay,
                    currentHour);
        }
// \обработка ситуации с полуночью
//Открытие потока для фантика
//======================================================Сделать красиво!
        System.out.println("\nData: "+currentYear+"/"+currentMonth+"/"+currentDay+"\nStart test in "+currentHour+" hour and "+currentMinute+" minutes ");
        FileWriter testFile = new FileWriter("TestRecordIOSCD100_E778_MS5.txt",false);
        testFile.write("Data: "+currentYear+"/"+currentMonth+"/"+currentDay+"\nStart test in "+currentHour+" hour and "+currentMinute+" minutes \n");
        System.out.println(cameraName+" \n");
        testFile.write(cameraName+" \n");
        System.out.println("Imitation web iPhone");
        testFile.write("Imitation web iPhone");


//*Открытие потока для фантика + определение  платформы



        int currentFirstMinute = -tick;
        int currentLastMinute = -1;

        //--------------------------------------------------------------------------------------------------------------------


        int schedule = CamDrivePageObject.returnCurrentScheldueStatus(currentHour,"aef64c10d39975425e711014dcb8a061");
//-----------------------------------------------------------------------------------------------------------
        //Открытие потока на запись Error файла
        FileWriter errorFile = new FileWriter(errorLogFile,true);
        errorFile.write("Data: "+currentYear+"/"+currentMonth+"/"+currentDay+"\nStart test in "+currentHour+" hour and 00 minutes \n"+"Camera: CD100_E778_MS5 \n"+"Imitation web iOS\n");
//-----------------------------------------------------------------------------------------------------------
        for (int m = 0; m < 6; m ++){

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
            System.out.println("\n Block:"+(m + 1)+" Play archive block of "+currentHourCONVERTED+":00h. "+currentFirstMinuteCONVERTED+"min-"+currentLastMinuteCONVERTED+"min\n");
            testFile.write("\n Block:"+(m + 1)+" Play archive block of "+currentHourCONVERTED+":00h. "+currentFirstMinuteCONVERTED+"min-"+currentLastMinuteCONVERTED+"min\n");
//*Фантик

            //CamDrivePageObject.scrollWebPageTitleElementNotVisible("id:2019-"+currentMonthCONVERTED+"-"+currentDayCONVERTED+"-"+currentHourCONVERTED+"-"+currentFirstMinuteCONVERTED+"-00_2019-"+currentMonthCONVERTED+"-"+currentDayCONVERTED+"-"+currentHourCONVERTED+"-"+currentLastMinuteCONVERTED+"-59","WTF",5);
//TODO :   исправить !!!
            if (currentHour >11){
                CamDrivePageObject.scrollIntoView();
            }
//            CamDrivePageObject.checkLoadVideoPlayerForIosMW();

//-----------------------------------------------------------------------------------------------------------
            try{
                CamDrivePageObject.clickMinute(
                        currentDayCONVERTED,
                        currentMonthCONVERTED,
                        currentHourCONVERTED,
                        currentFirstMinuteCONVERTED,
                        currentLastMinuteCONVERTED);
            }catch (RuntimeException e){
//Фантик
                if((schedule == 1)||(schedule == 3)) {
                    System.out.println("No records. Block " + currentHourCONVERTED + ":00h. " + currentFirstMinuteCONVERTED + "min-" + currentLastMinuteCONVERTED + "min\n");
                    testFile.write("No records. Block: " + currentHourCONVERTED + ":00h. " + currentFirstMinuteCONVERTED + "min-" + currentLastMinuteCONVERTED + "min\n");
                }else{
                    errorFile.write("No records. Block: " + currentHourCONVERTED + ":00h. " + currentFirstMinuteCONVERTED + "min-" + currentLastMinuteCONVERTED + "min\n");
                    System.out.println("No records. Block " + currentHourCONVERTED + ":00h. " + currentFirstMinuteCONVERTED + "min-" + currentLastMinuteCONVERTED + "min\n");
                }
//*Фантик
                continue;
            }
//-----------------------------------------------------------------------------------------------------------------------
            CamDrivePageObject.loadArchiveVideoIOS(); //добавить трайклик

            try {
                CamDrivePageObject.checkLoadVideoPlayerForIosMW();
            }catch (RuntimeException e){
//Фантик
                if((schedule == 1)||(schedule == 3)) {
                    System.out.println("Error load archive video. Block: " + currentHourCONVERTED + ":00h. " + currentFirstMinuteCONVERTED + "min-" + currentLastMinuteCONVERTED + "min\n");
                    testFile.write("Error load archive video. Block: " + currentHourCONVERTED + ":00h. " + currentFirstMinuteCONVERTED + "min-" + currentLastMinuteCONVERTED + "min\n");
                }else{
                    System.out.println("Error load archive video. Block: " + currentHourCONVERTED + ":00h. " + currentFirstMinuteCONVERTED + "min-" + currentLastMinuteCONVERTED + "min\n");
                    errorFile.write("Error load archive video. Block: " + currentHourCONVERTED + ":00h. " + currentFirstMinuteCONVERTED + "min-" + currentLastMinuteCONVERTED + "min\n");
                }
//*Фантик
                CamDrivePageObject.clickBackOnMinuteScreenIOS();
                continue;
            }
//-----------------------------------------------------------------------------------------------------------------------
            //TODO : JSE в тесте
            Object attribute = CamDrivePageObject.getTimeDurationVideoForIOSArchive(); //допилить джаваскрипт

            int time = 0;

            String result = String.valueOf(attribute);
            int indexEndResult = result.lastIndexOf(".");

            if(indexEndResult == -1) {
                try {
                    time = Integer.parseInt(result);
                } catch (Exception e) {
                    System.out.println("Cannot format type int current element :" + attribute+"\n");
                    errorFile.write("JS return incorrect type variable: "+ attribute+"\n");
                }
            } else{
                time = Integer.parseInt(result.substring(0,indexEndResult));
            }
            if(((schedule == 0) & (time<590))){
                errorFile.write("Current block: " + currentHourCONVERTED + ":00h. " + currentFirstMinuteCONVERTED + "min-" + currentLastMinuteCONVERTED + "min\n"+"Record is less then 590 second. Current record is "+ time + "\n");
            }

            System.out.println(attribute+" sec ");
            System.out.println("RESULT!   "+result);
            System.out.println("Parse and cut. Result "+ time);
            testFile.write(attribute+" sec\n");
            CamDrivePageObject.clickBackOnMinuteScreenIOS();
        }
//Закрытие потока для фантика
        testFile.close();
        errorFile.close();
        SuperVisor operation = new SuperVisor();
        operation.checkerEmptyFile(keyWord, errorLogFile);
        //*Закрытие потока для фантика
    }

    @Test
    public void testCD310_2E51_MS4_IOS() throws Exception {
        String cameraName = "Camera: CD310_2E51_MS4";
        String errorLogFile = "ErrorRecordIOSCD310_2E51_MS4.txt";
        //Удаление репорта ошибок
        File errorLog = new File(errorLogFile);
        errorLog.delete();
        //Удаление репорта ошибок
        FileWriter cleanFile = new FileWriter("TestRecordIOSCD310_2E51_MS4.txt",false);
        cleanFile.close();

        CamDrivePageObject CamDrivePageObject = CamDrivePageObjectFactory.get(driver);
        CamDrivePageObject.checkScheldue(currentHour,"78cbbc49a31cdff1778023fc57e89f46");
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
            currentHourCONVERTED = "23";
            currentDay = currentDay -1;

            if (currentDay <10){
                currentDayCONVERTED = ""+(currentDay);
            }else currentDayCONVERTED = Integer.toString(currentDay);

            CamDrivePageObject.choiseDay(currentDayCONVERTED,currentMonthCONVERTED);
        }else {

            if (currentDay <10){
                currentDayCONVERTED = ""+(currentDay);
            }else currentDayCONVERTED = Integer.toString(currentDay);

            CamDrivePageObject.choiseTheCurrentDay(
                    errorLogFile,
                    cameraName,
                    currentYear,
                    currentMonth,
                    currentDay,
                    currentHour);
        }
// \обработка ситуации с полуночью
//Открытие потока для фантика
        System.out.println("\nData: "+currentYear+"/"+currentMonth+"/"+currentDay+"\nStart test in "+currentHour+" hour and "+currentMinute+" minutes ");
        FileWriter testFile = new FileWriter("TestRecordIOSCD310_2E51_MS4.txt",false);
        testFile.write("Data: "+currentYear+"/"+currentMonth+"/"+currentDay+"\nStart test in "+currentHour+" hour and "+currentMinute+" minutes \n");
        System.out.println(cameraName+" \n");
        testFile.write(cameraName+" \n");
        System.out.println("Imitation web iPhone");
        testFile.write("Imitation web iPhone");


//*Открытие потока для фантика

        int currentFirstMinute = -tick;
        int currentLastMinute = -1;

        //--------------------------------------------------------------------------------------------------------------------


        int schedule = CamDrivePageObject.returnCurrentScheldueStatus(currentHour,"78cbbc49a31cdff1778023fc57e89f46");
//-----------------------------------------------------------------------------------------------------------
        //Открытие потока на запись Error файла
        FileWriter errorFile = new FileWriter(errorLogFile,true);
        errorFile.write("Data: "+currentYear+"/"+currentMonth+"/"+currentDay+"\nStart test in "+currentHour+" hour and 00 minutes \n"+"Camera: CD310_2E51_MS4 \n"+"Imitation web iOS\n");
//-----------------------------------------------------------------------------------------------------------
        for (int m = 0; m < 6; m ++){

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
            System.out.println("\n Block:"+(m + 1)+" Play archive block of "+currentHourCONVERTED+":00h. "+currentFirstMinuteCONVERTED+"min-"+currentLastMinuteCONVERTED+"min\n");
            testFile.write("\n Block:"+(m + 1)+" Play archive block of "+currentHourCONVERTED+":00h. "+currentFirstMinuteCONVERTED+"min-"+currentLastMinuteCONVERTED+"min\n");
//*Фантик

            //CamDrivePageObject.scrollWebPageTitleElementNotVisible("id:2019-"+currentMonthCONVERTED+"-"+currentDayCONVERTED+"-"+currentHourCONVERTED+"-"+currentFirstMinuteCONVERTED+"-00_2019-"+currentMonthCONVERTED+"-"+currentDayCONVERTED+"-"+currentHourCONVERTED+"-"+currentLastMinuteCONVERTED+"-59","WTF",5);
//TODO :   исправить !!!
            if (currentHour >11){
                CamDrivePageObject.scrollIntoView();
            }
//            CamDrivePageObject.checkLoadVideoPlayerForIosMW();

//-----------------------------------------------------------------------------------------------------------
            try{
                CamDrivePageObject.clickMinute(
                        currentDayCONVERTED,
                        currentMonthCONVERTED,
                        currentHourCONVERTED,
                        currentFirstMinuteCONVERTED,
                        currentLastMinuteCONVERTED);
            }catch (RuntimeException e){
//Фантик
                if((schedule == 1)||(schedule == 3)) {
                    System.out.println("No records. Block " + currentHourCONVERTED + ":00h. " + currentFirstMinuteCONVERTED + "min-" + currentLastMinuteCONVERTED + "min\n");
                    testFile.write("No records. Block: " + currentHourCONVERTED + ":00h. " + currentFirstMinuteCONVERTED + "min-" + currentLastMinuteCONVERTED + "min\n");
                }else{
                    errorFile.write("No records. Block: " + currentHourCONVERTED + ":00h. " + currentFirstMinuteCONVERTED + "min-" + currentLastMinuteCONVERTED + "min\n");
                    System.out.println("No records. Block " + currentHourCONVERTED + ":00h. " + currentFirstMinuteCONVERTED + "min-" + currentLastMinuteCONVERTED + "min\n");
                }
//*Фантик
                continue;
            }
//-----------------------------------------------------------------------------------------------------------------------
            CamDrivePageObject.loadArchiveVideoIOS(); //добавить трайклик

            try {
                CamDrivePageObject.checkLoadVideoPlayerForIosMW();
            }catch (RuntimeException e){
//Фантик
                if((schedule == 1)||(schedule == 3)) {
                    System.out.println("Error load archive video. Block: " + currentHourCONVERTED + ":00h. " + currentFirstMinuteCONVERTED + "min-" + currentLastMinuteCONVERTED + "min\n");
                    testFile.write("Error load archive video. Block: " + currentHourCONVERTED + ":00h. " + currentFirstMinuteCONVERTED + "min-" + currentLastMinuteCONVERTED + "min\n");
                }else{
                    System.out.println("Error load archive video. Block: " + currentHourCONVERTED + ":00h. " + currentFirstMinuteCONVERTED + "min-" + currentLastMinuteCONVERTED + "min\n");
                    errorFile.write("Error load archive video. Block: " + currentHourCONVERTED + ":00h. " + currentFirstMinuteCONVERTED + "min-" + currentLastMinuteCONVERTED + "min\n");
                }
//*Фантик
                CamDrivePageObject.clickBackOnMinuteScreenIOS();
                continue;
            }
//-----------------------------------------------------------------------------------------------------------------------
            //TODO : JSE в тесте
            Object attribute = CamDrivePageObject.getTimeDurationVideoForIOSArchive(); //допилить джаваскрипт

            int time = 0;

            String result = String.valueOf(attribute);
            int indexEndResult = result.lastIndexOf(".");

            if(indexEndResult == -1) {
                try {
                    time = Integer.parseInt(result);
                } catch (Exception e) {
                    System.out.println("Cannot format type int current element :" + attribute);
                    errorFile.write("JS return incorrect type variable: "+ attribute);
                }
            } else{
                time = Integer.parseInt(result.substring(0,indexEndResult));
            }
            if(((schedule == 0) & (time<590))){
                errorFile.write("Current block: " + currentHourCONVERTED + ":00h. " + currentFirstMinuteCONVERTED + "min-" + currentLastMinuteCONVERTED + "min\n"+"Record is less then 590 second. Current record is "+ time + "\n");
            }

            System.out.println(attribute+" sec ");
            System.out.println("RESULT!   "+result);
            System.out.println("Parse and cut. Result "+ time);
            testFile.write(attribute+" sec\n");
            CamDrivePageObject.clickBackOnMinuteScreenIOS();
        }
//Закрытие потока для фантика
        testFile.close();
        errorFile.close();
        SuperVisor operation = new SuperVisor();
        operation.checkerEmptyFile(keyWord, errorLogFile);

//*Закрытие потока для фантика
    }

    @Test
    public void testCD320_AA06_MS3_IOS() throws Exception {
        String cameraName = "Camera: CD320_AA06_MS3";
        String errorLogFile = "ErrorRecordIOSCD320_AA06_MS3.txt";
        //Удаление репорта ошибок
        File errorLog = new File(errorLogFile);
        errorLog.delete();
        //Удаление репорта ошибок
        FileWriter cleanFile = new FileWriter("TestRecordIOSCD320_AA06_MS3_DEV.txt",false);
        cleanFile.close();

        CamDrivePageObject CamDrivePageObject = CamDrivePageObjectFactory.get(driver);
        CamDrivePageObject.checkScheldue(currentHour,"2e6fb75139e4226198f9f6c0786e8b8a");
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
            currentHourCONVERTED = "23";
            currentDay = currentDay -1;

            if (currentDay <10){
                currentDayCONVERTED = ""+(currentDay);
            }else currentDayCONVERTED = Integer.toString(currentDay);

            CamDrivePageObject.choiseDay(currentDayCONVERTED,currentMonthCONVERTED);
        }else {

            if (currentDay <10){
                currentDayCONVERTED = ""+(currentDay);
            }else currentDayCONVERTED = Integer.toString(currentDay);

            CamDrivePageObject.choiseTheCurrentDay(
                    errorLogFile,
                    cameraName,
                    currentYear,
                    currentMonth,
                    currentDay,
                    currentHour);
        }
// \обработка ситуации с полуночью
        //Открытие потока для фантика
        System.out.println("\nData: "+currentYear+"/"+currentMonth+"/"+currentDay+"\nStart test in "+currentHour+" hour and "+currentMinute+" minutes ");
        FileWriter testFile = new FileWriter("TestRecordIOSCD320_AA06_MS3_DEV.txt",false);
        testFile.write("Data: "+currentYear+"/"+currentMonth+"/"+currentDay+"\nStart test in "+currentHour+" hour and "+currentMinute+" minutes \n");
        System.out.println(cameraName+" \n");
        testFile.write(cameraName+" \n");
        System.out.println("Imitation web iPhone");
        testFile.write("Imitation web iPhone");


//*Открытие потока для фантика + определение  платформы


        int currentFirstMinute = -tick;
        int currentLastMinute = -1;
        //--------------------------------------------------------------------------------------------------------------------


        int schedule = CamDrivePageObject.returnCurrentScheldueStatus(currentHour,"2e6fb75139e4226198f9f6c0786e8b8a");
//-----------------------------------------------------------------------------------------------------------
        //Открытие потока на запись Error файла
        FileWriter errorFile = new FileWriter(errorLogFile,true);
        errorFile.write("Data: "+currentYear+"/"+currentMonth+"/"+currentDay+"\nStart test in "+currentHour+" hour and 00 minutes \n"+"Camera: CD320_AA06_MS3 \n"+"Imitation web iOS\n");
//-----------------------------------------------------------------------------------------------------------
        for (int m = 0; m < 6; m ++){

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
            System.out.println("\n Block:"+(m + 1)+" Play archive block of "+currentHourCONVERTED+":00h. "+currentFirstMinuteCONVERTED+"min-"+currentLastMinuteCONVERTED+"min\n");
            testFile.write("\n Block:"+(m + 1)+" Play archive block of "+currentHourCONVERTED+":00h. "+currentFirstMinuteCONVERTED+"min-"+currentLastMinuteCONVERTED+"min\n");
//*Фантик

            //CamDrivePageObject.scrollWebPageTitleElementNotVisible("id:2019-"+currentMonthCONVERTED+"-"+currentDayCONVERTED+"-"+currentHourCONVERTED+"-"+currentFirstMinuteCONVERTED+"-00_2019-"+currentMonthCONVERTED+"-"+currentDayCONVERTED+"-"+currentHourCONVERTED+"-"+currentLastMinuteCONVERTED+"-59","WTF",5);
//TODO :   исправить !!!
            if (currentHour >11){
                CamDrivePageObject.scrollIntoView();
            }
//            CamDrivePageObject.checkLoadVideoPlayerForIosMW();

//-----------------------------------------------------------------------------------------------------------
            try{
                CamDrivePageObject.clickMinute(
                        currentDayCONVERTED,
                        currentMonthCONVERTED,
                        currentHourCONVERTED,
                        currentFirstMinuteCONVERTED,
                        currentLastMinuteCONVERTED);
            }catch (RuntimeException e){
//Фантик
                if((schedule == 1)||(schedule == 3)) {
                    System.out.println("No records. Block " + currentHourCONVERTED + ":00h. " + currentFirstMinuteCONVERTED + "min-" + currentLastMinuteCONVERTED + "min\n");
                    testFile.write("No records. Block: " + currentHourCONVERTED + ":00h. " + currentFirstMinuteCONVERTED + "min-" + currentLastMinuteCONVERTED + "min\n");
                }else{
                    errorFile.write("No records. Block: " + currentHourCONVERTED + ":00h. " + currentFirstMinuteCONVERTED + "min-" + currentLastMinuteCONVERTED + "min\n");
                    System.out.println("No records. Block " + currentHourCONVERTED + ":00h. " + currentFirstMinuteCONVERTED + "min-" + currentLastMinuteCONVERTED + "min\n");
                }
//*Фантик
                continue;
            }
//-----------------------------------------------------------------------------------------------------------------------
            CamDrivePageObject.loadArchiveVideoIOS(); //добавить трайклик

            try {
                CamDrivePageObject.checkLoadVideoPlayerForIosMW();
            }catch (RuntimeException e){
//Фантик
                if((schedule == 1)||(schedule == 3)) {
                    System.out.println("Error load archive video. Block: " + currentHourCONVERTED + ":00h. " + currentFirstMinuteCONVERTED + "min-" + currentLastMinuteCONVERTED + "min\n");
                    testFile.write("Error load archive video. Block: " + currentHourCONVERTED + ":00h. " + currentFirstMinuteCONVERTED + "min-" + currentLastMinuteCONVERTED + "min\n");
                }else{
                    System.out.println("Error load archive video. Block: " + currentHourCONVERTED + ":00h. " + currentFirstMinuteCONVERTED + "min-" + currentLastMinuteCONVERTED + "min\n");
                    errorFile.write("Error load archive video. Block: " + currentHourCONVERTED + ":00h. " + currentFirstMinuteCONVERTED + "min-" + currentLastMinuteCONVERTED + "min\n");
                }
//*Фантик
                CamDrivePageObject.clickBackOnMinuteScreenIOS();
                continue;
            }
//-----------------------------------------------------------------------------------------------------------------------
            //TODO : JSE в тесте
            Object attribute = CamDrivePageObject.getTimeDurationVideoForIOSArchive(); //допилить джаваскрипт

            int time = 0;

            String result = String.valueOf(attribute);
            int indexEndResult = result.lastIndexOf(".");

            if(indexEndResult == -1) {
                try {
                    time = Integer.parseInt(result);
                } catch (Exception e) {
                    System.out.println("Cannot format type int current element :" + attribute);
                    errorFile.write("JS return incorrect type variable: "+ attribute);
                }
            } else{
                time = Integer.parseInt(result.substring(0,indexEndResult));
            }
            if(((schedule == 0) & (time<590))){
                errorFile.write("Current block: " + currentHourCONVERTED + ":00h. " + currentFirstMinuteCONVERTED + "min-" + currentLastMinuteCONVERTED + "min\n"+"Record is less then 590 second. Current record is "+ time + "\n");
            }

            System.out.println(attribute+" sec ");
            System.out.println("RESULT!   "+result);
            System.out.println("Parse and cut. Result "+ time);
            testFile.write(attribute+" sec\n");
            CamDrivePageObject.clickBackOnMinuteScreenIOS();
        }
//Закрытие потока для фантика
        testFile.close();
        errorFile.close();
        SuperVisor operation = new SuperVisor();
        operation.checkerEmptyFile(keyWord, errorLogFile);

//*Закрытие потока для фантика
    }

    @Test
    public void testCD320_AA78_MS5_IOS() throws Exception {
        String cameraName = "Camera: CD320_AA78_MS5";
        String errorLogFile = "ErrorRecordIOSCD320_AA78_MS5.txt";
        //Удаление репорта ошибок
        File errorLog = new File(errorLogFile);
        errorLog.delete();
        //Удаление репорта ошибок
        FileWriter cleanFile = new FileWriter("TestRecordIOSCD320_AA78_MS5.txt",false);
        cleanFile.close();

        CamDrivePageObject CamDrivePageObject = CamDrivePageObjectFactory.get(driver);
        CamDrivePageObject.checkScheldue(currentHour,"b63c65eeaa4410befcac0a2e96281f5c");
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
            currentHourCONVERTED = "23";
            currentDay = currentDay -1;

            if (currentDay <10){
                currentDayCONVERTED = ""+(currentDay);
            }else currentDayCONVERTED = Integer.toString(currentDay);

            CamDrivePageObject.choiseDay(currentDayCONVERTED,currentMonthCONVERTED);
        }else {

            if (currentDay <10){
                currentDayCONVERTED = ""+(currentDay);
            }else currentDayCONVERTED = Integer.toString(currentDay);

            CamDrivePageObject.choiseTheCurrentDay(
                    errorLogFile,
                    cameraName,
                    currentYear,
                    currentMonth,
                    currentDay,
                    currentHour);
        }
// \обработка ситуации с полуночью
        //Открытие потока для фантика
//======================================================Сделать красиво!
        System.out.println("\nData: "+currentYear+"/"+currentMonth+"/"+currentDay+"\nStart test in "+currentHour+" hour and "+currentMinute+" minutes ");
        FileWriter testFile = new FileWriter("TestRecordIOSCD320_AA78_MS5.txt",false);
        testFile.write("Data: "+currentYear+"/"+currentMonth+"/"+currentDay+"\nStart test in "+currentHour+" hour and "+currentMinute+" minutes \n");
        System.out.println(cameraName+" \n");
        testFile.write(cameraName+" \n");
        System.out.println("Imitation web iPhone");
        testFile.write("Imitation web iPhone");


//*Открытие потока для фантика + определение  платформы


        int currentFirstMinute = -tick;
        int currentLastMinute = -1;

        //--------------------------------------------------------------------------------------------------------------------


        int schedule = CamDrivePageObject.returnCurrentScheldueStatus(currentHour,"b63c65eeaa4410befcac0a2e96281f5c");
//-----------------------------------------------------------------------------------------------------------
        //Открытие потока на запись Error файла
        FileWriter errorFile = new FileWriter(errorLogFile,true);
        errorFile.write("Data: "+currentYear+"/"+currentMonth+"/"+currentDay+"\nStart test in "+currentHour+" hour and 00 minutes \n"+"Camera: CD320_AA78_MS5 \n"+"Imitation web iOS\n");
//-----------------------------------------------------------------------------------------------------------
        for (int m = 0; m < 6; m ++){

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
            System.out.println("\n Block:"+(m + 1)+" Play archive block of "+currentHourCONVERTED+":00h. "+currentFirstMinuteCONVERTED+"min-"+currentLastMinuteCONVERTED+"min\n");
            testFile.write("\n Block:"+(m + 1)+" Play archive block of "+currentHourCONVERTED+":00h. "+currentFirstMinuteCONVERTED+"min-"+currentLastMinuteCONVERTED+"min\n");
//*Фантик

            //CamDrivePageObject.scrollWebPageTitleElementNotVisible("id:2019-"+currentMonthCONVERTED+"-"+currentDayCONVERTED+"-"+currentHourCONVERTED+"-"+currentFirstMinuteCONVERTED+"-00_2019-"+currentMonthCONVERTED+"-"+currentDayCONVERTED+"-"+currentHourCONVERTED+"-"+currentLastMinuteCONVERTED+"-59","WTF",5);
//TODO :  исправить !!!
            if (currentHour >11){
                CamDrivePageObject.scrollIntoView();
            }
//            CamDrivePageObject.checkLoadVideoPlayerForIosMW();

//-----------------------------------------------------------------------------------------------------------
            try{
                CamDrivePageObject.clickMinute(
                        currentDayCONVERTED,
                        currentMonthCONVERTED,
                        currentHourCONVERTED,
                        currentFirstMinuteCONVERTED,
                        currentLastMinuteCONVERTED);
            }catch (RuntimeException e){
//Фантик
                if((schedule == 1)||(schedule == 3)) {
                    System.out.println("No records. Block " + currentHourCONVERTED + ":00h. " + currentFirstMinuteCONVERTED + "min-" + currentLastMinuteCONVERTED + "min\n");
                    testFile.write("No records. Block: " + currentHourCONVERTED + ":00h. " + currentFirstMinuteCONVERTED + "min-" + currentLastMinuteCONVERTED + "min\n");
                }else{
                    errorFile.write("No records. Block: " + currentHourCONVERTED + ":00h. " + currentFirstMinuteCONVERTED + "min-" + currentLastMinuteCONVERTED + "min\n");
                    System.out.println("No records. Block " + currentHourCONVERTED + ":00h. " + currentFirstMinuteCONVERTED + "min-" + currentLastMinuteCONVERTED + "min\n");
                }
//*Фантик
                continue;
            }
//-----------------------------------------------------------------------------------------------------------------------
            CamDrivePageObject.loadArchiveVideoIOS(); //добавить трайклик

            try {
                CamDrivePageObject.checkLoadVideoPlayerForIosMW();
            }catch (RuntimeException e){
//Фантик
                if((schedule == 1)||(schedule == 3)) {
                    System.out.println("Error load archive video. Block: " + currentHourCONVERTED + ":00h. " + currentFirstMinuteCONVERTED + "min-" + currentLastMinuteCONVERTED + "min\n");
                    testFile.write("Error load archive video. Block: " + currentHourCONVERTED + ":00h. " + currentFirstMinuteCONVERTED + "min-" + currentLastMinuteCONVERTED + "min\n");
                }else{
                    System.out.println("Error load archive video. Block: " + currentHourCONVERTED + ":00h. " + currentFirstMinuteCONVERTED + "min-" + currentLastMinuteCONVERTED + "min\n");
                    errorFile.write("Error load archive video. Block: " + currentHourCONVERTED + ":00h. " + currentFirstMinuteCONVERTED + "min-" + currentLastMinuteCONVERTED + "min\n");
                }
//*Фантик
                CamDrivePageObject.clickBackOnMinuteScreenIOS();
                continue;
            }
//-----------------------------------------------------------------------------------------------------------------------
            //TODO : JSE в тесте
            Object attribute = CamDrivePageObject.getTimeDurationVideoForIOSArchive(); //допилить джаваскрипт

            int time = 0;

            String result = String.valueOf(attribute);//attribute.toString().valueOf();
            int indexEndResult = result.lastIndexOf(".");

            if(indexEndResult == -1) {
                try {
                    time = Integer.parseInt(result);
                } catch (Exception e) {
                    System.out.println("Cannot format type int current element :" + attribute);
                    errorFile.write("JS return incorrect type variable: "+ attribute);
                }
            } else{
                time = Integer.parseInt(result.substring(0,indexEndResult));
            }
            if(((schedule == 0) & (time<590))){
                errorFile.write("Current block: " + currentHourCONVERTED + ":00h. " + currentFirstMinuteCONVERTED + "min-" + currentLastMinuteCONVERTED + "min\n"+"Record is less then 590 second. Current record is "+ time + "\n");
            }

            System.out.println(attribute+" sec ");
            System.out.println("RESULT!   "+result);
            System.out.println("Parse and cut. Result "+ time);
            testFile.write(attribute+" sec\n");
            CamDrivePageObject.clickBackOnMinuteScreenIOS();
        }
//Закрытие потока для фантика
        testFile.close();
        errorFile.close();
        SuperVisor operation = new SuperVisor();
        operation.checkerEmptyFile(keyWord, errorLogFile);

//*Закрытие потока для фантика
    }
    @Test
    public void testCD600_EF78_MS6_IOS() throws Exception {
        String cameraName = "Camera: CD600_EF78_MS6";
        String errorLogFile = "ErrorRecordIOSCD600_EF78_MS6.txt";
        //Удаление репорта ошибок
        File errorLog = new File(errorLogFile);
        errorLog.delete();
        //Удаление репорта ошибок
        FileWriter cleanFile = new FileWriter("TestRecordIOSCD600_EF78_MS6_SERV.txt",false);
        cleanFile.close();

        CamDrivePageObject CamDrivePageObject = CamDrivePageObjectFactory.get(driver);
        CamDrivePageObject.checkScheldue(currentHour,"23d6fb09e101dc587b8b16db3cf7b5dd");
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
            currentHourCONVERTED = "23";
            currentDay = currentDay -1;

            if (currentDay <10){
                currentDayCONVERTED = ""+(currentDay);
            }else currentDayCONVERTED = Integer.toString(currentDay);

            CamDrivePageObject.choiseDay(currentDayCONVERTED,currentMonthCONVERTED);
        }else {

            if (currentDay <10){
                currentDayCONVERTED = ""+(currentDay);
            }else currentDayCONVERTED = Integer.toString(currentDay);

            CamDrivePageObject.choiseTheCurrentDay(
                    errorLogFile,
                    cameraName,
                    currentYear,
                    currentMonth,
                    currentDay,
                    currentHour);
        }
// \обработка ситуации с полуночью
        //Открытие потока для фантика
//======================================================Сделать красиво!
        System.out.println("\nData: "+currentYear+"/"+currentMonth+"/"+currentDay+"\nStart test in "+currentHour+" hour and "+currentMinute+" minutes ");
        FileWriter testFile = new FileWriter("TestRecordIOSCD600_EF78_MS6_SERV.txt",false);
        testFile.write("Data: "+currentYear+"/"+currentMonth+"/"+currentDay+"\nStart test in "+currentHour+" hour and "+currentMinute+" minutes \n");
        System.out.println(cameraName+" \n");
        testFile.write(cameraName+" \n");
        System.out.println("Imitation web iPhone");
        testFile.write("Imitation web iPhone");


//*Открытие потока для фантика + определение  платформы

        int currentFirstMinute = -tick;
        int currentLastMinute = -1;

        //--------------------------------------------------------------------------------------------------------------------


        int schedule = CamDrivePageObject.returnCurrentScheldueStatus(currentHour,"23d6fb09e101dc587b8b16db3cf7b5dd");
//-----------------------------------------------------------------------------------------------------------
        //Открытие потока на запись Error файла
        FileWriter errorFile = new FileWriter(errorLogFile,true);
        errorFile.write("Data: "+currentYear+"/"+currentMonth+"/"+currentDay+"\nStart test in "+currentHour+" hour and 00 minutes \n"+"Camera: CD600_EF78_MS6 \n"+"Imitation web iOS\n");
//-----------------------------------------------------------------------------------------------------------
        for (int m = 0; m < 6; m ++){

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
            System.out.println("\n Block:"+(m + 1)+" Play archive block of "+currentHourCONVERTED+":00h. "+currentFirstMinuteCONVERTED+"min-"+currentLastMinuteCONVERTED+"min\n");
            testFile.write("\n Block:"+(m + 1)+" Play archive block of "+currentHourCONVERTED+":00h. "+currentFirstMinuteCONVERTED+"min-"+currentLastMinuteCONVERTED+"min\n");
//*Фантик

            //CamDrivePageObject.scrollWebPageTitleElementNotVisible("id:2019-"+currentMonthCONVERTED+"-"+currentDayCONVERTED+"-"+currentHourCONVERTED+"-"+currentFirstMinuteCONVERTED+"-00_2019-"+currentMonthCONVERTED+"-"+currentDayCONVERTED+"-"+currentHourCONVERTED+"-"+currentLastMinuteCONVERTED+"-59","WTF",5);
//TODO :  исправить !!!
            if (currentHour >11){
                CamDrivePageObject.scrollIntoView();
            }
//            CamDrivePageObject.checkLoadVideoPlayerForIosMW();

//-----------------------------------------------------------------------------------------------------------
            try{
                CamDrivePageObject.clickMinute(
                        currentDayCONVERTED,
                        currentMonthCONVERTED,
                        currentHourCONVERTED,
                        currentFirstMinuteCONVERTED,
                        currentLastMinuteCONVERTED);
            }catch (RuntimeException e){
//Фантик
                if((schedule == 1)||(schedule == 3)) {
                    System.out.println("No records. Block " + currentHourCONVERTED + ":00h. " + currentFirstMinuteCONVERTED + "min-" + currentLastMinuteCONVERTED + "min\n");
                    testFile.write("No records. Block: " + currentHourCONVERTED + ":00h. " + currentFirstMinuteCONVERTED + "min-" + currentLastMinuteCONVERTED + "min\n");
                }else{
                    errorFile.write("No records. Block: " + currentHourCONVERTED + ":00h. " + currentFirstMinuteCONVERTED + "min-" + currentLastMinuteCONVERTED + "min\n");
                    System.out.println("No records. Block " + currentHourCONVERTED + ":00h. " + currentFirstMinuteCONVERTED + "min-" + currentLastMinuteCONVERTED + "min\n");
                }
//*Фантик
                continue;
            }
//-----------------------------------------------------------------------------------------------------------------------
            CamDrivePageObject.loadArchiveVideoIOS(); //добавить трайклик

            try {
                CamDrivePageObject.checkLoadVideoPlayerForIosMW();
            }catch (RuntimeException e){
//Фантик
                if((schedule == 1)||(schedule == 3)) {
                    System.out.println("Error load archive video. Block: " + currentHourCONVERTED + ":00h. " + currentFirstMinuteCONVERTED + "min-" + currentLastMinuteCONVERTED + "min\n");
                    testFile.write("Error load archive video. Block: " + currentHourCONVERTED + ":00h. " + currentFirstMinuteCONVERTED + "min-" + currentLastMinuteCONVERTED + "min\n");
                }else{
                    System.out.println("Error load archive video. Block: " + currentHourCONVERTED + ":00h. " + currentFirstMinuteCONVERTED + "min-" + currentLastMinuteCONVERTED + "min\n");
                    errorFile.write("Error load archive video. Block: " + currentHourCONVERTED + ":00h. " + currentFirstMinuteCONVERTED + "min-" + currentLastMinuteCONVERTED + "min\n");
                }
//*Фантик
                CamDrivePageObject.clickBackOnMinuteScreenIOS();
                continue;
            }
//-----------------------------------------------------------------------------------------------------------------------
            //TODO : JSE в тесте
            Object attribute = CamDrivePageObject.getTimeDurationVideoForIOSArchive(); //допилить джаваскрипт

            int time = 0;

            String result = String.valueOf(attribute);
            int indexEndResult = result.lastIndexOf(".");

            if(indexEndResult == -1) {
                try {
                    time = Integer.parseInt(result);
                } catch (Exception e) {
                    System.out.println("Cannot format type int current element :" + attribute);
                    errorFile.write("JS return incorrect type variable: "+ attribute);
                }
            } else{
                time = Integer.parseInt(result.substring(0,indexEndResult));
            }
            if(((schedule == 0) & (time<590))){
                errorFile.write("Current block: " + currentHourCONVERTED + ":00h. " + currentFirstMinuteCONVERTED + "min-" + currentLastMinuteCONVERTED + "min\n"+"Record is less then 590 second. Current record is "+ time + "\n");
            }

            System.out.println(attribute+" sec ");
            System.out.println("RESULT!   "+result);
            System.out.println("Parse and cut. Result "+ time);
            testFile.write(attribute+" sec\n");
            CamDrivePageObject.clickBackOnMinuteScreenIOS();
        }
//Закрытие потока для фантика
        testFile.close();
        errorFile.close();
        SuperVisor operation = new SuperVisor();
        operation.checkerEmptyFile(keyWord, errorLogFile);

//*Закрытие потока для фантика
    }

    @Test
    public void testCD630_910D_MS6_IOS() throws Exception {
        String cameraName = "Camera: CD630_910D_MS6";
        String errorLogFile = "ErrorRecordIOSCD630_910D_MS6.txt";
        //Удаление репорта ошибок
        File errorLog = new File(errorLogFile);
        errorLog.delete();
        //Удаление репорта ошибок
        FileWriter cleanFile = new FileWriter("TestRecordIOSCD630_910D_MS6_DEV.txt",false);
        cleanFile.close();
//========================================================

        CamDrivePageObject CamDrivePageObject = CamDrivePageObjectFactory.get(driver);
        CamDrivePageObject.checkScheldue(currentHour,"81037196ecb69d5306abcb8f61fba33c");
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
            currentHourCONVERTED = "23";
            currentDay = currentDay -1;

            if (currentDay <10){
                currentDayCONVERTED = ""+(currentDay);
            }else currentDayCONVERTED = Integer.toString(currentDay);

            CamDrivePageObject.choiseDay(currentDayCONVERTED,currentMonthCONVERTED);
        }else {

            if (currentDay <10){
                currentDayCONVERTED = ""+(currentDay);
            }else currentDayCONVERTED = Integer.toString(currentDay);

            CamDrivePageObject.choiseTheCurrentDay(
                    errorLogFile,
                    cameraName,
                    currentYear,
                    currentMonth,
                    currentDay,
                    currentHour);
        }

// \обработка ситуации с полуночью
//Открытие потока для фантика
//======================================================Сделать красиво!
        System.out.println("\nData: "+currentYear+"/"+currentMonth+"/"+currentDay+"\nStart test in "+currentHour+" hour and "+currentMinute+" minutes ");
        FileWriter testFile = new FileWriter("TestRecordIOSCD630_910D_MS6_DEV.txt",false);
        testFile.write("Data: "+currentYear+"/"+currentMonth+"/"+currentDay+"\nStart test in "+currentHour+" hour and "+currentMinute+" minutes \n");
        System.out.println(cameraName+" \n");
        testFile.write(cameraName+" \n");
        System.out.println("Imitation web iPhone");
        testFile.write("Imitation web iPhone");


//*Открытие потока для фантика + определение  платформы


        int currentFirstMinute = -tick;
        int currentLastMinute = -1;

        //--------------------------------------------------------------------------------------------------------------------


        int schedule = CamDrivePageObject.returnCurrentScheldueStatus(currentHour,"81037196ecb69d5306abcb8f61fba33c");
//-----------------------------------------------------------------------------------------------------------
        //Открытие потока на запись Error файла
        FileWriter errorFile = new FileWriter(errorLogFile,true);
        errorFile.write("Data: "+currentYear+"/"+currentMonth+"/"+currentDay+"\nStart test in "+currentHour+" hour and 00 minutes \n"+"Camera: CD630_910D_MS6 \n"+"Imitation web iOS\n");
//-----------------------------------------------------------------------------------------------------------
        for (int m = 0; m < 6; m ++){

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
            System.out.println("\n Block:"+(m + 1)+" Play archive block of "+currentHourCONVERTED+":00h. "+currentFirstMinuteCONVERTED+"min-"+currentLastMinuteCONVERTED+"min\n");
            testFile.write("\n Block:"+(m + 1)+" Play archive block of "+currentHourCONVERTED+":00h. "+currentFirstMinuteCONVERTED+"min-"+currentLastMinuteCONVERTED+"min\n");
//*Фантик

            //CamDrivePageObject.scrollWebPageTitleElementNotVisible("id:2019-"+currentMonthCONVERTED+"-"+currentDayCONVERTED+"-"+currentHourCONVERTED+"-"+currentFirstMinuteCONVERTED+"-00_2019-"+currentMonthCONVERTED+"-"+currentDayCONVERTED+"-"+currentHourCONVERTED+"-"+currentLastMinuteCONVERTED+"-59","WTF",5);
//TODO :   исправить !!!
            if (currentHour >11){
                CamDrivePageObject.scrollIntoView();
            }
//            CamDrivePageObject.checkLoadVideoPlayerForIosMW();

//-----------------------------------------------------------------------------------------------------------
            try{
                CamDrivePageObject.clickMinute(
                        currentDayCONVERTED,
                        currentMonthCONVERTED,
                        currentHourCONVERTED,
                        currentFirstMinuteCONVERTED,
                        currentLastMinuteCONVERTED);
            }catch (RuntimeException e){
//Фантик
                if((schedule == 1)||(schedule == 3)) {
                    System.out.println("No records. Block " + currentHourCONVERTED + ":00h. " + currentFirstMinuteCONVERTED + "min-" + currentLastMinuteCONVERTED + "min\n");
                    testFile.write("No records. Block: " + currentHourCONVERTED + ":00h. " + currentFirstMinuteCONVERTED + "min-" + currentLastMinuteCONVERTED + "min\n");
                }else{
                    errorFile.write("No records. Block: " + currentHourCONVERTED + ":00h. " + currentFirstMinuteCONVERTED + "min-" + currentLastMinuteCONVERTED + "min\n");
                    System.out.println("No records. Block " + currentHourCONVERTED + ":00h. " + currentFirstMinuteCONVERTED + "min-" + currentLastMinuteCONVERTED + "min\n");
                }
//*Фантик
                continue;
            }
//-----------------------------------------------------------------------------------------------------------------------
            CamDrivePageObject.loadArchiveVideoIOS(); //добавить трайклик

            try {
                CamDrivePageObject.checkLoadVideoPlayerForIosMW();
            }catch (RuntimeException e){
//Фантик
                if((schedule == 1)||(schedule == 3)) {
                    System.out.println("Error load archive video. Block: " + currentHourCONVERTED + ":00h. " + currentFirstMinuteCONVERTED + "min-" + currentLastMinuteCONVERTED + "min\n");
                    testFile.write("Error load archive video. Block: " + currentHourCONVERTED + ":00h. " + currentFirstMinuteCONVERTED + "min-" + currentLastMinuteCONVERTED + "min\n");
                }else{
                    System.out.println("Error load archive video. Block: " + currentHourCONVERTED + ":00h. " + currentFirstMinuteCONVERTED + "min-" + currentLastMinuteCONVERTED + "min\n");
                    errorFile.write("Error load archive video. Block: " + currentHourCONVERTED + ":00h. " + currentFirstMinuteCONVERTED + "min-" + currentLastMinuteCONVERTED + "min\n");
                }
//*Фантик
                CamDrivePageObject.clickBackOnMinuteScreenIOS();
                continue;
            }
//-----------------------------------------------------------------------------------------------------------------------
            //TODO : JSE в тесте
            Object attribute = CamDrivePageObject.getTimeDurationVideoForIOSArchive(); //допилить джаваскрипт

            int time = 0;

            String result = String.valueOf(attribute);
            int indexEndResult = result.lastIndexOf(".");

            if(indexEndResult == -1) {
                try {
                    time = Integer.parseInt(result);
                } catch (Exception e) {
                    System.out.println("Cannot format type int current element :" + attribute);

                    errorFile.write("JS return incorrect type variable: "+ attribute);
                }
            } else{
                time = Integer.parseInt(result.substring(0,indexEndResult));
            }
            if(((schedule == 0) & (time<590))){
                errorFile.write("Current block: " + currentHourCONVERTED + ":00h. " + currentFirstMinuteCONVERTED + "min-" + currentLastMinuteCONVERTED + "min\n"+"Record is less then 590 second. Current record is "+ time + "\n");

            }

            System.out.println(attribute+" sec ");
            System.out.println("RESULT!   "+result);
            System.out.println("Parse and cut. Result "+ time);
            testFile.write(attribute+" sec\n");
            CamDrivePageObject.clickBackOnMinuteScreenIOS();
        }
//Закрытие потока для фантика
        testFile.close();
        errorFile.close();
        SuperVisor operation = new SuperVisor();
        operation.checkerEmptyFile(keyWord, errorLogFile);

//*Закрытие потока для фантика
    }

    @Test
    public void testN1001_3A00_bwd_IOS() throws Exception {
        String cameraName = "Camera: N1001_3A00_bwd";
        String errorLogFile = "ErrorRecordIOSN1001_3A00_bwd.txt";
        //Удаление репорта ошибок
        File errorLog = new File(errorLogFile);
        errorLog.delete();
        //Удаление репорта ошибок
        FileWriter cleanFile = new FileWriter("TestRecordIOSN1001_3A00_bwd.txt",false);
        cleanFile.close();

        CamDrivePageObject CamDrivePageObject = CamDrivePageObjectFactory.get(driver);
        CamDrivePageObject.checkScheldue(currentHour,"4ac35c97e26af54c55caa2b36ceab0ca");
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
            currentHourCONVERTED = "23";
            currentDay = currentDay -1;

            if (currentDay <10){
                currentDayCONVERTED = ""+(currentDay);
            }else currentDayCONVERTED = Integer.toString(currentDay);

            CamDrivePageObject.choiseDay(currentDayCONVERTED,currentMonthCONVERTED);
        }else {

            if (currentDay <10){
                currentDayCONVERTED = ""+(currentDay);
            }else currentDayCONVERTED = Integer.toString(currentDay);

            CamDrivePageObject.choiseTheCurrentDay(
                    errorLogFile,
                    cameraName,
                    currentYear,
                    currentMonth,
                    currentDay,
                    currentHour);
        }
//Открытие потока для фантика
//======================================================Сделать красиво!
        System.out.println("\nData: "+currentYear+"/"+currentMonth+"/"+currentDay+"\nStart test in "+currentHour+" hour and "+currentMinute+" minutes ");
        FileWriter testFile = new FileWriter("TestRecordIOSN1001_3A00_bwd.txt",false);
        testFile.write("Data: "+currentYear+"/"+currentMonth+"/"+currentDay+"\nStart test in "+currentHour+" hour and "+currentMinute+" minutes \n");
        System.out.println(cameraName+" \n");
        testFile.write(cameraName+" \n");
        System.out.println("Imitation web iPhone");
        testFile.write("Imitation web iPhone");


//*Открытие потока для фантика + определение  платформы


        int currentFirstMinute = -tick;
        int currentLastMinute = -1;

        //--------------------------------------------------------------------------------------------------------------------


        int schedule = CamDrivePageObject.returnCurrentScheldueStatus(currentHour,"4ac35c97e26af54c55caa2b36ceab0ca");
//-----------------------------------------------------------------------------------------------------------
        //Открытие потока на запись Error файла
        FileWriter errorFile = new FileWriter(errorLogFile,true);
        errorFile.write("Data: "+currentYear+"/"+currentMonth+"/"+currentDay+"\nStart test in "+currentHour+" hour and 00 minutes \n"+"Camera: N1001_3A00_bwd \n"+"Imitation web iOS\n");
//-----------------------------------------------------------------------------------------------------------
        for (int m = 0; m < 6; m ++){

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
            System.out.println("\n Block:"+(m + 1)+" Play archive block of "+currentHourCONVERTED+":00h. "+currentFirstMinuteCONVERTED+"min-"+currentLastMinuteCONVERTED+"min\n");
            testFile.write("\n Block:"+(m + 1)+" Play archive block of "+currentHourCONVERTED+":00h. "+currentFirstMinuteCONVERTED+"min-"+currentLastMinuteCONVERTED+"min\n");
//*Фантик

            //CamDrivePageObject.scrollWebPageTitleElementNotVisible("id:2019-"+currentMonthCONVERTED+"-"+currentDayCONVERTED+"-"+currentHourCONVERTED+"-"+currentFirstMinuteCONVERTED+"-00_2019-"+currentMonthCONVERTED+"-"+currentDayCONVERTED+"-"+currentHourCONVERTED+"-"+currentLastMinuteCONVERTED+"-59","WTF",5);
//TODO :     исправить !!!
            if (currentHour >11){
                CamDrivePageObject.scrollIntoView();
            }
//            CamDrivePageObject.checkLoadVideoPlayerForIosMW();

//-----------------------------------------------------------------------------------------------------------
            try{
                CamDrivePageObject.clickMinute(
                        currentDayCONVERTED,
                        currentMonthCONVERTED,
                        currentHourCONVERTED,
                        currentFirstMinuteCONVERTED,
                        currentLastMinuteCONVERTED);
            }catch (RuntimeException e){
//Фантик
                if((schedule == 1)||(schedule == 3)) {
                    System.out.println("No records. Block " + currentHourCONVERTED + ":00h. " + currentFirstMinuteCONVERTED + "min-" + currentLastMinuteCONVERTED + "min\n");
                    testFile.write("No records. Block: " + currentHourCONVERTED + ":00h. " + currentFirstMinuteCONVERTED + "min-" + currentLastMinuteCONVERTED + "min\n");
                }else{
                    errorFile.write("No records. Block: " + currentHourCONVERTED + ":00h. " + currentFirstMinuteCONVERTED + "min-" + currentLastMinuteCONVERTED + "min\n");
                    System.out.println("No records. Block " + currentHourCONVERTED + ":00h. " + currentFirstMinuteCONVERTED + "min-" + currentLastMinuteCONVERTED + "min\n");
                }
//*Фантик
                continue;
            }
//-----------------------------------------------------------------------------------------------------------------------
            CamDrivePageObject.loadArchiveVideoIOS(); //добавить трайклик

            try {
                CamDrivePageObject.checkLoadVideoPlayerForIosMW();
            }catch (RuntimeException e){
//Фантик
                if((schedule == 1)||(schedule == 3)) {
                    System.out.println("Error load archive video. Block: " + currentHourCONVERTED + ":00h. " + currentFirstMinuteCONVERTED + "min-" + currentLastMinuteCONVERTED + "min\n");
                    testFile.write("Error load archive video. Block: " + currentHourCONVERTED + ":00h. " + currentFirstMinuteCONVERTED + "min-" + currentLastMinuteCONVERTED + "min\n");
                }else{
                    System.out.println("Error load archive video. Block: " + currentHourCONVERTED + ":00h. " + currentFirstMinuteCONVERTED + "min-" + currentLastMinuteCONVERTED + "min\n");
                    errorFile.write("Error load archive video. Block: " + currentHourCONVERTED + ":00h. " + currentFirstMinuteCONVERTED + "min-" + currentLastMinuteCONVERTED + "min\n");
                }
//*Фантик
                CamDrivePageObject.clickBackOnMinuteScreenIOS();
                continue;
            }
//-----------------------------------------------------------------------------------------------------------------------
            //TODO : JSE в тесте
            Object attribute = CamDrivePageObject.getTimeDurationVideoForIOSArchive(); //допилить джаваскрипт

            int time = 0;

            String result = String.valueOf(attribute);
            int indexEndResult = result.lastIndexOf(".");

            if(indexEndResult == -1) {
                try {
                    time = Integer.parseInt(result);
                } catch (Exception e) {
                    System.out.println("Cannot format type int current element :" + attribute);
                    errorFile.write("JS return incorrect type variable: "+ attribute);
                }
            } else{
                time = Integer.parseInt(result.substring(0,indexEndResult));
            }
            if(((schedule == 0) & (time<590))){
                errorFile.write("Current block: " + currentHourCONVERTED + ":00h. " + currentFirstMinuteCONVERTED + "min-" + currentLastMinuteCONVERTED + "min\n"+"Record is less then 590 second. Current record is "+ time + "\n");
            }

            System.out.println(attribute+" sec ");
            System.out.println("RESULT!   "+result);
            System.out.println("Parse and cut. Result "+ time);
            testFile.write(attribute+" sec\n");
            CamDrivePageObject.clickBackOnMinuteScreenIOS();
        }
//Закрытие потока для фантика
        testFile.close();
        errorFile.close();
        SuperVisor operation = new SuperVisor();
        operation.checkerEmptyFile(keyWord, errorLogFile);
//*Закрытие потока для фантика
    }
}
