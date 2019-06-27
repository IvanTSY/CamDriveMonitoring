package testsCamDriveRecordsCurrentHourAndDay.testForAndroidWeb;

import lib.CoreTestCase;
import lib.logging.SuperVisor;
import lib.ui.CamDrivePageObject;
import lib.ui.factories.CamDrivePageObjectFactory;
import org.junit.Test;

import java.io.File;
import java.io.FileWriter;
import java.util.Calendar;

public class AllCameraAndroidArchiveTests extends CoreTestCase {
    int currentHour = Calendar.getInstance().getTime().getHours() - 1;
    int currentDay = Calendar.getInstance().getTime().getDate();
    int currentMonth = Calendar.getInstance().getTime().getMonth() + 1;
    int currentYear = Calendar.getInstance().getWeekYear();
    String currentFirstMinuteCONVERTED;
    String currentLastMinuteCONVERTED;
    String currentHourCONVERTED;
    String currentDayCONVERTED;
    String currentMonthCONVERTED;
    String keyWord ="Imitation web Android";


    @Test
    public void testCD100_E75A_MS3() throws Exception {
        SuperVisor operation = new SuperVisor();
//Чистим файл перед запуском теста
        int statisticOfError =0;
        String cameraName = "Camera: CD100_E75A_MS3";
        String errorLogFile = "ErrorRecordCD100_E75A_MS3.txt";
        File cleanFile = new File("TestRecordCD100_E75A_MS3.txt");
        cleanFile.delete();
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
            currentHourCONVERTED = "23";
            currentDay = currentDay -1;


            CamDrivePageObject.choiseDay(String.valueOf(currentDay),currentMonthCONVERTED);
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
        System.out.println("\nData: "+currentYear+"/"+currentMonth+"/"+currentDay+"\nStart test in "+currentHour+" hour and 00 minutes ");
        FileWriter testFile = new FileWriter("TestRecordCD100_E75A_MS3.txt",false);
        testFile.write("Data: "+currentYear+"/"+currentMonth+"/"+currentDay+"\nStart test in "+currentHour+" hour and 00 minutes \n");
        System.out.println(cameraName+" \n");
        testFile.write(cameraName+" \n");
        System.out.println("Imitation web Android");
        testFile.write("Imitation web Android");
//*Открытие потока для фантика

        int schedule = CamDrivePageObject.returnCurrentScheldueStatus(currentHour,"aee40e829262b7930f529c4fee6d326a");
//------------------------------------------------------------------------------------------------------------------------
        //Отчёт с ошибками



        FileWriter errorFile = new FileWriter(errorLogFile,true);
        errorFile.write("Data: "+currentYear+"/"+currentMonth+"/"+currentDay+"\nStart test in "+currentHour+" hour and 00 minutes \n"+"Camera: CD100_E75A_MS3 \n"+"Imitation web Android\n");
        if ((schedule == 1)||(schedule == 3)){
            try{
                CamDrivePageObject.clickHour(
                        currentDayCONVERTED,
                        currentMonthCONVERTED,
                        currentHourCONVERTED);

            }catch (RuntimeException e){
//Фантик
                System.out.println("Current hour: "+currentHour+":00 not have records for this camera\n"+"Тип расписания -" + schedule +"\n" );
                testFile.write("Current hour: "+currentHour+":00 not have records for this camera\n"+"Тип расписания -" + schedule +"\n" );
//*Фантик
            }
        }else{
            //TODO Проверить как лучше сделать
            try{
                //Проверка Постоянная запись
                CamDrivePageObject.clickHour(
                        currentDayCONVERTED,
                        currentMonthCONVERTED,
                        currentHourCONVERTED);

            }catch (RuntimeException e){
                errorFile.write("Current hour "+currentHour+":00 not have records!");
                errorFile.close();
                statisticOfError++;
                CamDrivePageObject.exitTest();
            }
        }
//------------------------------------------------------------------------------------------------------------------------

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
                if((schedule == 1)||(schedule == 3)) {
                    System.out.println("No records. Block " + currentHourCONVERTED + ":00h. " + currentFirstMinuteCONVERTED + "min-" + currentLastMinuteCONVERTED + "min\n");
                    testFile.write("No records. Block: " + currentHourCONVERTED + ":00h. " + currentFirstMinuteCONVERTED + "min-" + currentLastMinuteCONVERTED + "min\n");
                }else{
                    errorFile.write("No records. Block: " + currentHourCONVERTED + ":00h. " + currentFirstMinuteCONVERTED + "min-" + currentLastMinuteCONVERTED + "min\n");
                    System.out.println("No records. Block " + currentHourCONVERTED + ":00h. " + currentFirstMinuteCONVERTED + "min-" + currentLastMinuteCONVERTED + "min\n");
                    statisticOfError++;
                }
//*Фантик
                continue;
            }

            CamDrivePageObject.loadArchiveVideoAndroid();

            try {
                CamDrivePageObject.checkLoadVideoPlayerForAndroidMW();
            }catch (RuntimeException e){
//Фантик
                if((schedule == 1)||(schedule == 3)) {
                    System.out.println("Error load archive video. Block: " + currentHourCONVERTED + ":00h. " + currentFirstMinuteCONVERTED + "min-" + currentLastMinuteCONVERTED + "min\n");
                    testFile.write("Error load archive video. Block: " + currentHourCONVERTED + ":00h. " + currentFirstMinuteCONVERTED + "min-" + currentLastMinuteCONVERTED + "min\n");
                }else{
                    System.out.println("Error load archive video. Block: " + currentHourCONVERTED + ":00h. " + currentFirstMinuteCONVERTED + "min-" + currentLastMinuteCONVERTED + "min\n");
                    errorFile.write("Error load archive video. Block: " + currentHourCONVERTED + ":00h. " + currentFirstMinuteCONVERTED + "min-" + currentLastMinuteCONVERTED + "min\n");
                    statisticOfError++;
                }
//*Фантик
                CamDrivePageObject.clickBackOnMinuteScreenAndroid();
                continue;
            }
            CamDrivePageObject.clickOnVideoForm();
//---------------------------------------------
            int time = 0;

            Object attribute = CamDrivePageObject.getTime("max");

            String result = String.valueOf(attribute);

            int indexEndResult = result.lastIndexOf(".");

            if(indexEndResult == -1) {
                try {
                    time = Integer.parseInt(result);
                } catch (Exception e) {
                    System.out.println("Cannot format type int current element :" + attribute);
                    statisticOfError++;
                    errorFile.write("JS return incorrect type variable: "+ attribute);
                }
            } else{
                time = Integer.parseInt(result.substring(0,indexEndResult));
            }

            if(((schedule == 0) & (time<290))){
                errorFile.write("Current block: " + currentHourCONVERTED + ":00h. " + currentFirstMinuteCONVERTED + "min-" + currentLastMinuteCONVERTED + "min\n"+"Record is less then must be: "+attribute+"/290 seconds\n");
                statisticOfError++;
            }
//---------------------------------------------
//Фантик
            System.out.println(attribute+" sec");
            testFile.write(attribute+" sec\n");
//*Фантик
            CamDrivePageObject.clickCloseButtonOnPlayArchiveScreen();
            CamDrivePageObject.clickBackOnMinuteScreenAndroid();
        }
        //CamDrivePageObject.clickBackOnHourScreenCD100_E75A();
//Закрытие потока для фантика
        testFile.close();
        errorFile.close();
        operation.checkerEmptyFile(keyWord, errorLogFile);
        if (statisticOfError == 0) errorLog.delete();

//*Закрытие потока для фантика
    }

    @Test
    public void testCD100_E772_MS4() throws Exception {
        int statisticOfError =0;
        String cameraName = "Camera: CD100_E772_MS4";
        String errorLogFile = "ErrorRecordCD100_E772_MS4.txt";
        File cleanFile = new File("TestRecordCD100_E772_MS4.txt");
        cleanFile.delete();
        File errorLog = new File(errorLogFile);
        errorLog.delete();


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
        System.out.println("\nData: "+currentYear+"/"+currentMonth+"/"+currentDay+"\nStart test in "+currentHour+" hour and 00 minutes ");
        FileWriter testFile = new FileWriter("TestRecordCD100_E772_MS4.txt",false);
        testFile.write("Data: "+currentYear+"/"+currentMonth+"/"+currentDay+"\nStart test in "+currentHour+" hour and 00 minutes \n");
        System.out.println(cameraName+" \n");
        testFile.write(cameraName+" \n");
        System.out.println("Imitation web Android");
        testFile.write("Imitation web Android");
//*Открытие потока для фантика

        int schedule = CamDrivePageObject.returnCurrentScheldueStatus(currentHour,"95100e92696c2163bef3185cd29deff2");

        FileWriter errorFile = new FileWriter(errorLogFile,true);
        errorFile.write("Data: "+currentYear+"/"+currentMonth+"/"+currentDay+"\nStart test in "+currentHour+" hour and 00 minutes \n" + "Camera: CD100_E772_MS4\n"+"Imitation web Android\n");
        if ((schedule == 1)||(schedule == 3)){
            try{
                CamDrivePageObject.clickHour(
                        currentDayCONVERTED,
                        currentMonthCONVERTED,
                        currentHourCONVERTED);

            }catch (RuntimeException e){
//Фантик
                System.out.println("Current hour: "+currentHour+":00 not have records for this camera\n"+"Тип расписания -" + schedule +"\n" );
                testFile.write("Current hour: "+currentHour+":00 not have records for this camera\n"+"Тип расписания -" + schedule +"\n" );
//*Фантик
            }
        }else{
            //TODO Проверить как лучше сделать
            try{
                //Проверка Постоянная запись
                CamDrivePageObject.clickHour(
                        currentDayCONVERTED,
                        currentMonthCONVERTED,
                        currentHourCONVERTED);

            }catch (RuntimeException e){
                errorFile.write("Current hour "+currentHour+":00 not have records!");
                errorFile.close();
                statisticOfError++;
                CamDrivePageObject.exitTest();
            }
        }
//------------------------------------------------------------------------------------------------------------------------

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
                if((schedule == 1)||(schedule == 3)) {
                    System.out.println("No records. Block " + currentHourCONVERTED + ":00h. " + currentFirstMinuteCONVERTED + "min-" + currentLastMinuteCONVERTED + "min\n");
                    testFile.write("No records. Block: " + currentHourCONVERTED + ":00h. " + currentFirstMinuteCONVERTED + "min-" + currentLastMinuteCONVERTED + "min\n");
                }else{
                    errorFile.write("No records. Block: " + currentHourCONVERTED + ":00h. " + currentFirstMinuteCONVERTED + "min-" + currentLastMinuteCONVERTED + "min\n");
                    System.out.println("No records. Block " + currentHourCONVERTED + ":00h. " + currentFirstMinuteCONVERTED + "min-" + currentLastMinuteCONVERTED + "min\n");
                    statisticOfError++;
                }
//*Фантик
                continue;
            }

            CamDrivePageObject.loadArchiveVideoAndroid();

            try {
                CamDrivePageObject.checkLoadVideoPlayerForAndroidMW();
            }catch (RuntimeException e){
//Фантик
                if((schedule == 1)||(schedule == 3)) {
                    System.out.println("Error load archive video. Block: " + currentHourCONVERTED + ":00h. " + currentFirstMinuteCONVERTED + "min-" + currentLastMinuteCONVERTED + "min\n");
                    testFile.write("Error load archive video. Block: " + currentHourCONVERTED + ":00h. " + currentFirstMinuteCONVERTED + "min-" + currentLastMinuteCONVERTED + "min\n");
                }else{
                    System.out.println("Error load archive video. Block: " + currentHourCONVERTED + ":00h. " + currentFirstMinuteCONVERTED + "min-" + currentLastMinuteCONVERTED + "min\n");
                    errorFile.write("Error load archive video. Block: " + currentHourCONVERTED + ":00h. " + currentFirstMinuteCONVERTED + "min-" + currentLastMinuteCONVERTED + "min\n");
                    statisticOfError++;
                }
//*Фантик
                CamDrivePageObject.clickBackOnMinuteScreenAndroid();
                continue;
            }
            CamDrivePageObject.clickOnVideoForm();
            //---------------------------------------------
            int time = 0;

            Object attribute = CamDrivePageObject.getTime("max");

            String result = String.valueOf(attribute);

            int indexEndResult = result.lastIndexOf(".");

            if(indexEndResult == -1) {
                try {
                    time = Integer.parseInt(result);
                } catch (Exception e) {
                    System.out.println("Cannot format type int current element :" + attribute);
                    statisticOfError++;
                    errorFile.write("JS return incorrect type variable: "+ attribute);
                }
            } else{
                time = Integer.parseInt(result.substring(0,indexEndResult));
            }

            if(((schedule == 0) & (time<290))){
                errorFile.write("Current block: " + currentHourCONVERTED + ":00h. " + currentFirstMinuteCONVERTED + "min-" + currentLastMinuteCONVERTED + "min\n"+"Record is less then must be: "+attribute+"/290 seconds\n");
                statisticOfError++;
            }
//---------------------------------------------
//Фантик
            System.out.println(attribute+" sec");
            testFile.write(attribute+" sec\n");
//*Фантик
            CamDrivePageObject.clickCloseButtonOnPlayArchiveScreen();
            CamDrivePageObject.clickBackOnMinuteScreenAndroid();
        }
        //CamDrivePageObject.clickBackOnHourScreenCD100_E75A();
//Закрытие потока для фантика
        testFile.close();
        errorFile.close();
        if (statisticOfError == 0) errorLog.delete();

//*Закрытие потока для фантика
    }

    @Test
    public void testCD100_E778_MS5() throws Exception {
        int statisticOfError =0;
        String cameraName = "Camera: CD100_E778_MS5 ";
        String errorLogFile = "ErrorRecordCD100_E778_MS5.txt";
        File cleanFile = new File("TestRecordCD100_E778_MS5.txt");
        cleanFile.delete();
        File errorLog = new File(errorLogFile);
        errorLog.delete();

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
        System.out.println("\nData: "+currentYear+"/"+currentMonth+"/"+currentDay+"\nStart test in "+currentHour+" hour and 00 minutes ");
        FileWriter testFile = new FileWriter("TestRecordCD100_E778_MS5.txt",false);
        testFile.write("Data: "+currentYear+"/"+currentMonth+"/"+currentDay+"\nStart test in "+currentHour+" hour and 00 minutes \n");
        System.out.println(cameraName+" \n");
        testFile.write(cameraName+" \n");
        System.out.println("Imitation web Android");
        testFile.write("Imitation web Android");
//*Открытие потока для фантика



        int schedule = CamDrivePageObject.returnCurrentScheldueStatus(currentHour,"aef64c10d39975425e711014dcb8a061");


        //------------------------------------------------------------------------------------------------------------------------
        //Отчёт с ошибками




        FileWriter errorFile = new FileWriter(errorLogFile,true);
        errorFile.write("Data: "+currentYear+"/"+currentMonth+"/"+currentDay+"\nStart test in "+currentHour+" hour and 00 minutes \n"+"Camera: CD100_E778_MS5 \n"+"Imitation web Android\n");
        if ((schedule == 1)||(schedule == 3)){
            try{
                CamDrivePageObject.clickHour(
                        currentDayCONVERTED,
                        currentMonthCONVERTED,
                        currentHourCONVERTED);

            }catch (RuntimeException e){
//Фантик
                System.out.println("Current hour: "+currentHour+":00 not have records for this camera\n"+"Тип расписания -" + schedule +"\n" );
                testFile.write("Current hour: "+currentHour+":00 not have records for this camera\n"+"Тип расписания -" + schedule +"\n" );
//*Фантик
            }
        }else{
            //TODO Проверить как лучше сделать
            try{
                //Проверка Постоянная запись
                CamDrivePageObject.clickHour(
                        currentDayCONVERTED,
                        currentMonthCONVERTED,
                        currentHourCONVERTED);

            }catch (RuntimeException e){
                errorFile.write("Current hour "+currentHour+":00 not have records!");
                errorFile.close();
                statisticOfError++;
                CamDrivePageObject.exitTest();
            }
        }
//------------------------------------------------------------------------------------------------------------------------


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
                if((schedule == 1)||(schedule == 3)) {
                    System.out.println("No records. Block " + currentHourCONVERTED + ":00h. " + currentFirstMinuteCONVERTED + "min-" + currentLastMinuteCONVERTED + "min\n");
                    testFile.write("No records. Block: " + currentHourCONVERTED + ":00h. " + currentFirstMinuteCONVERTED + "min-" + currentLastMinuteCONVERTED + "min\n");
                }else{
                    errorFile.write("No records. Block: " + currentHourCONVERTED + ":00h. " + currentFirstMinuteCONVERTED + "min-" + currentLastMinuteCONVERTED + "min\n");
                    System.out.println("No records. Block " + currentHourCONVERTED + ":00h. " + currentFirstMinuteCONVERTED + "min-" + currentLastMinuteCONVERTED + "min\n");
                    statisticOfError++;
                }
//*Фантик
                continue;
            }

            CamDrivePageObject.loadArchiveVideoAndroid();

            try {
                CamDrivePageObject.checkLoadVideoPlayerForAndroidMW();
            }catch (RuntimeException e){
//Фантик
                if((schedule == 1)||(schedule == 3)) {
                    System.out.println("Error load archive video. Block: " + currentHourCONVERTED + ":00h. " + currentFirstMinuteCONVERTED + "min-" + currentLastMinuteCONVERTED + "min\n");
                    testFile.write("Error load archive video. Block: " + currentHourCONVERTED + ":00h. " + currentFirstMinuteCONVERTED + "min-" + currentLastMinuteCONVERTED + "min\n");
                }else{
                    System.out.println("Error load archive video. Block: " + currentHourCONVERTED + ":00h. " + currentFirstMinuteCONVERTED + "min-" + currentLastMinuteCONVERTED + "min\n");
                    errorFile.write("Error load archive video. Block: " + currentHourCONVERTED + ":00h. " + currentFirstMinuteCONVERTED + "min-" + currentLastMinuteCONVERTED + "min\n");
                    statisticOfError++;
                }
//*Фантик
                CamDrivePageObject.clickBackOnMinuteScreenAndroid();
                continue;
            }
            CamDrivePageObject.clickOnVideoForm();
//---------------------------------------------
            int time = 0;

            Object attribute = CamDrivePageObject.getTime("max");

            String result = String.valueOf(attribute);

            int indexEndResult = result.lastIndexOf(".");

            if(indexEndResult == -1) {
                try {
                    time = Integer.parseInt(result);
                } catch (Exception e) {
                    System.out.println("Cannot format type int current element :" + attribute);
                    statisticOfError++;
                    errorFile.write("JS return incorrect type variable: "+ attribute);
                }
            } else{
                time = Integer.parseInt(result.substring(0,indexEndResult));
            }

            if(((schedule == 0) & (time<290))){
                errorFile.write("Current block: " + currentHourCONVERTED + ":00h. " + currentFirstMinuteCONVERTED + "min-" + currentLastMinuteCONVERTED + "min\n"+"Record is less then must be: "+attribute+"/290 seconds\n");
                statisticOfError++;
            }


//Фантик
            System.out.println(attribute+" sec");
            testFile.write(attribute+" sec\n");
//*Фантик
            CamDrivePageObject.clickCloseButtonOnPlayArchiveScreen();
            CamDrivePageObject.clickBackOnMinuteScreenAndroid();
        }
        //CamDrivePageObject.clickBackOnHourScreenCD100_E75A();
//Закрытие потока для фантика
        testFile.close();
        errorFile.close();
        if (statisticOfError == 0) errorLog.delete();

//*Закрытие потока для фантика
    }

    @Test
    public void testCD310_2E51_MS4() throws Exception {
        int statisticOfError =0;
        String cameraName = "Camera: CD310_2E51_MS4";
        String errorLogFile = "ErrorRecordCD310_2E51_MS4.txt";
        File cleanFile = new File("TestRecordCD310_2E51_MS4.txt");
        cleanFile.delete();
        File errorLog = new File(errorLogFile);
        errorLog.delete();

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
        System.out.println("\nData: "+currentYear+"/"+currentMonth+"/"+currentDay+"\nStart test in "+currentHour+" hour and 00 minutes ");
        FileWriter testFile = new FileWriter("TestRecordCD310_2E51_MS4.txt",false);
        testFile.write("Data: "+currentYear+"/"+currentMonth+"/"+currentDay+"\nStart test in "+currentHour+" hour and 00 minutes \n");
        System.out.println(cameraName+" \n");
        testFile.write(cameraName+" \n");
        System.out.println("Imitation web Android");
        testFile.write("Imitation web Android");
//*Открытие потока для фантика



        int schedule = CamDrivePageObject.returnCurrentScheldueStatus(currentHour,"78cbbc49a31cdff1778023fc57e89f46");
//------------------------------------------------------------------------------------------------------------------------
        //Отчёт с ошибками




        FileWriter errorFile = new FileWriter(errorLogFile,true);
        errorFile.write("Data: "+currentYear+"/"+currentMonth+"/"+currentDay+"\nStart test in "+currentHour+" hour and 00 minutes \n"+"Camera: CD310_2E51_MS4 \n"+"Imitation web Android\n");
        if ((schedule == 1)||(schedule == 3)){
            try{
                CamDrivePageObject.clickHour(
                        currentDayCONVERTED,
                        currentMonthCONVERTED,
                        currentHourCONVERTED);

            }catch (RuntimeException e){
//Фантик
                System.out.println("Current hour: "+currentHour+":00 not have records for this camera\n"+"Тип расписания -" + schedule +"\n" );
                testFile.write("Current hour: "+currentHour+":00 not have records for this camera\n"+"Тип расписания -" + schedule +"\n" );
//*Фантик
            }
        }else{
            //TODO Проверить как лучше сделать
            try{
                //Проверка Постоянная запись
                CamDrivePageObject.clickHour(
                        currentDayCONVERTED,
                        currentMonthCONVERTED,
                        currentHourCONVERTED);

            }catch (RuntimeException e){
                errorFile.write("Current hour "+currentHour+":00 not have records!");
                errorFile.close();
                statisticOfError++;
                CamDrivePageObject.exitTest();
            }
        }
//------------------------------------------------------------------------------------------------------------------------

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
                if((schedule == 1)||(schedule == 3)) {
                    System.out.println("No records. Block " + currentHourCONVERTED + ":00h. " + currentFirstMinuteCONVERTED + "min-" + currentLastMinuteCONVERTED + "min\n");
                    testFile.write("No records. Block: " + currentHourCONVERTED + ":00h. " + currentFirstMinuteCONVERTED + "min-" + currentLastMinuteCONVERTED + "min\n");
                }else{
                    errorFile.write("No records. Block: " + currentHourCONVERTED + ":00h. " + currentFirstMinuteCONVERTED + "min-" + currentLastMinuteCONVERTED + "min\n");
                    System.out.println("No records. Block " + currentHourCONVERTED + ":00h. " + currentFirstMinuteCONVERTED + "min-" + currentLastMinuteCONVERTED + "min\n");
                    statisticOfError++;
                }
//*Фантик
                continue;
            }

            CamDrivePageObject.loadArchiveVideoAndroid();

            try {
                CamDrivePageObject.checkLoadVideoPlayerForAndroidMW();
            }catch (RuntimeException e){
//Фантик
                if((schedule == 1)||(schedule == 3)) {
                    System.out.println("Error load archive video. Block: " + currentHourCONVERTED + ":00h. " + currentFirstMinuteCONVERTED + "min-" + currentLastMinuteCONVERTED + "min\n");
                    testFile.write("Error load archive video. Block: " + currentHourCONVERTED + ":00h. " + currentFirstMinuteCONVERTED + "min-" + currentLastMinuteCONVERTED + "min\n");
                }else{
                    System.out.println("Error load archive video. Block: " + currentHourCONVERTED + ":00h. " + currentFirstMinuteCONVERTED + "min-" + currentLastMinuteCONVERTED + "min\n");
                    errorFile.write("Error load archive video. Block: " + currentHourCONVERTED + ":00h. " + currentFirstMinuteCONVERTED + "min-" + currentLastMinuteCONVERTED + "min\n");
                    statisticOfError++;
                }
//*Фантик
                CamDrivePageObject.clickBackOnMinuteScreenAndroid();
                continue;
            }
            CamDrivePageObject.clickOnVideoForm();
//---------------------------------------------
            int time = 0;

            Object attribute = CamDrivePageObject.getTime("max");

            String result = String.valueOf(attribute);

            int indexEndResult = result.lastIndexOf(".");

            if(indexEndResult == -1) {
                try {
                    time = Integer.parseInt(result);
                } catch (Exception e) {
                    System.out.println("Cannot format type int current element :" + attribute);
                    statisticOfError++;
                    errorFile.write("JS return incorrect type variable: "+ attribute);
                }
            } else{
                time = Integer.parseInt(result.substring(0,indexEndResult));
            }

            if(((schedule == 0) & (time<290))){
                errorFile.write("Current block: " + currentHourCONVERTED + ":00h. " + currentFirstMinuteCONVERTED + "min-" + currentLastMinuteCONVERTED + "min\n"+"Record is less then must be: "+attribute+"/290 seconds\n");
                statisticOfError++;
            }
//Фантик
            System.out.println(attribute+" sec");
            testFile.write(attribute+" sec\n");
//*Фантик
            CamDrivePageObject.clickCloseButtonOnPlayArchiveScreen();
            CamDrivePageObject.clickBackOnMinuteScreenAndroid();
        }
        //CamDrivePageObject.clickBackOnHourScreenCD100_E75A();
//Закрытие потока для фантика
        testFile.close();
        errorFile.close();
        if (statisticOfError == 0) errorLog.delete();

//*Закрытие потока для фантика
    }

    @Test
    public void testCD320_AA06_MS3() throws Exception {
        int statisticOfError =0;
        String cameraName = "Camera: CD320_AA06_MS3";
        String errorLogFile = "ErrorRecordCD320_AA06_MS3.txt";
        File cleanFile = new File("TestRecordCD320_AA06_MS3_DEV.txt");
        cleanFile.delete();
        File errorLog = new File(errorLogFile);
        errorLog.delete();

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
        System.out.println("\nData: "+currentYear+"/"+currentMonth+"/"+currentDay+"\nStart test in "+currentHour+" hour and 00 minutes ");
        FileWriter testFile = new FileWriter("TestRecordCD320_AA06_MS3_DEV.txt",false);
        testFile.write("Data: "+currentYear+"/"+currentMonth+"/"+currentDay+"\nStart test in "+currentHour+" hour and 00 minutes \n");
        System.out.println(cameraName+" \n");
        testFile.write(cameraName+" \n");
        System.out.println("Imitation web Android");
        testFile.write("Imitation web Android");
//*Открытие потока для фантика



        int schedule = CamDrivePageObject.returnCurrentScheldueStatus(currentHour,"2e6fb75139e4226198f9f6c0786e8b8a");
//------------------------------------------------------------------------------------------------------------------------
        //Отчёт с ошибками



        FileWriter errorFile = new FileWriter("ErrorRecordCCD320_AA06_MS3.txt",true);
        errorFile.write("Data: "+currentYear+"/"+currentMonth+"/"+currentDay+"\nStart test in "+currentHour+" hour and 00 minutes \n"+"Camera: CD320_AA06_MS3 \n"+"Imitation web Android\n");
        if ((schedule == 1)||(schedule == 3)){
            try{
                CamDrivePageObject.clickHour(
                        currentDayCONVERTED,
                        currentMonthCONVERTED,
                        currentHourCONVERTED);

            }catch (RuntimeException e){
//Фантик
                System.out.println("Current hour: "+currentHour+":00 not have records for this camera\n"+"Тип расписания -" + schedule +"\n" );
                testFile.write("Current hour: "+currentHour+":00 not have records for this camera\n"+"Тип расписания -" + schedule +"\n" );
//*Фантик
            }
        }else{
            //TODO Проверить как лучше сделать
            try{
                //Проверка Постоянная запись
                CamDrivePageObject.clickHour(
                        currentDayCONVERTED,
                        currentMonthCONVERTED,
                        currentHourCONVERTED);

            }catch (RuntimeException e){
                errorFile.write("Current hour "+currentHour+":00 not have records!");
                errorFile.close();
                statisticOfError++;
                CamDrivePageObject.exitTest();
            }
        }
//------------------------------------------------------------------------------------------------------------------------


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
                if((schedule == 1)||(schedule == 3)) {
                    System.out.println("No records. Block " + currentHourCONVERTED + ":00h. " + currentFirstMinuteCONVERTED + "min-" + currentLastMinuteCONVERTED + "min\n");
                    testFile.write("No records. Block: " + currentHourCONVERTED + ":00h. " + currentFirstMinuteCONVERTED + "min-" + currentLastMinuteCONVERTED + "min\n");
                }else{
                    errorFile.write("No records. Block: " + currentHourCONVERTED + ":00h. " + currentFirstMinuteCONVERTED + "min-" + currentLastMinuteCONVERTED + "min\n");
                    System.out.println("No records. Block " + currentHourCONVERTED + ":00h. " + currentFirstMinuteCONVERTED + "min-" + currentLastMinuteCONVERTED + "min\n");
                    statisticOfError++;
                }
//*Фантик
                continue;
            }

            CamDrivePageObject.loadArchiveVideoAndroid();

            try {
                CamDrivePageObject.checkLoadVideoPlayerForAndroidMW();
            }catch (RuntimeException e){
//Фантик
                if((schedule == 1)||(schedule == 3)) {
                    System.out.println("Error load archive video. Block: " + currentHourCONVERTED + ":00h. " + currentFirstMinuteCONVERTED + "min-" + currentLastMinuteCONVERTED + "min\n");
                    testFile.write("Error load archive video. Block: " + currentHourCONVERTED + ":00h. " + currentFirstMinuteCONVERTED + "min-" + currentLastMinuteCONVERTED + "min\n");
                }else{
                    System.out.println("Error load archive video. Block: " + currentHourCONVERTED + ":00h. " + currentFirstMinuteCONVERTED + "min-" + currentLastMinuteCONVERTED + "min\n");
                    errorFile.write("Error load archive video. Block: " + currentHourCONVERTED + ":00h. " + currentFirstMinuteCONVERTED + "min-" + currentLastMinuteCONVERTED + "min\n");
                    statisticOfError++;
                }
//*Фантик
                CamDrivePageObject.clickBackOnMinuteScreenAndroid();
                continue;
            }
            CamDrivePageObject.clickOnVideoForm();
//---------------------------------------------
            int time = 0;

            Object attribute = CamDrivePageObject.getTime("max");

            String result = String.valueOf(attribute);

            int indexEndResult = result.lastIndexOf(".");

            if(indexEndResult == -1) {
                try {
                    time = Integer.parseInt(result);
                } catch (Exception e) {
                    System.out.println("Cannot format type int current element :" + attribute);
                    statisticOfError++;
                    errorFile.write("JS return incorrect type variable: "+ attribute);
                }
            } else{
                time = Integer.parseInt(result.substring(0,indexEndResult));
            }

            if(((schedule == 0) & (time<290))){
                errorFile.write("Current block: " + currentHourCONVERTED + ":00h. " + currentFirstMinuteCONVERTED + "min-" + currentLastMinuteCONVERTED + "min\n"+"Record is less then must be: "+attribute+"/290 seconds\n");
                statisticOfError++;
            }
//---------------------------------------------
//Фантик
            System.out.println(attribute+" sec");
            testFile.write(attribute+" sec\n");
//*Фантик
            CamDrivePageObject.clickCloseButtonOnPlayArchiveScreen();
            CamDrivePageObject.clickBackOnMinuteScreenAndroid();
        }
        //CamDrivePageObject.clickBackOnHourScreenCD100_E75A();
//Закрытие потока для фантика
        testFile.close();
        errorFile.close();
        if (statisticOfError == 0) errorLog.delete();

//*Закрытие потока для фантика
    }

    @Test
    public void testCD320_AA78_MS5() throws Exception {
        int statisticOfError =0;
        String cameraName = "Camera: CD320_AA78_MS5";
        String errorLogFile = "ErrorRecordIOSCD320_AA78_MS5.txt";
        File cleanFile = new File("TestRecordCD320_AA78_MS5.txt");
        cleanFile.delete();
        File errorLog = new File(errorLogFile);
        errorLog.delete();
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
        System.out.println("\nData: "+currentYear+"/"+currentMonth+"/"+currentDay+"\nStart test in "+currentHour+" hour and 00 minutes ");
        FileWriter testFile = new FileWriter("TestRecordCD320_AA78_MS5.txt",false);
        testFile.write("Data: "+currentYear+"/"+currentMonth+"/"+currentDay+"\nStart test in "+currentHour+" hour and 00 minutes \n");
        System.out.println("Camera: CD320_AA78_MS5 \n");
        testFile.write("Camera: CD320_AA78_MS5 \n");
        System.out.println("Imitation web Android");
        testFile.write("Imitation web Android");
//*Открытие потока для фантика



        int schedule = CamDrivePageObject.returnCurrentScheldueStatus(currentHour,"b63c65eeaa4410befcac0a2e96281f5c");
//------------------------------------------------------------------------------------------------------------------------
        //Отчёт с ошибками




        FileWriter errorFile = new FileWriter(errorLogFile,true);
        errorFile.write("Data: "+currentYear+"/"+currentMonth+"/"+currentDay+"\nStart test in "+currentHour+" hour and 00 minutes \n"+"Camera: CD320_AA78_MS5 \n"+"Imitation web Android\n");
        if ((schedule == 1)||(schedule == 3)){
            try{
                CamDrivePageObject.clickHour(
                        currentDayCONVERTED,
                        currentMonthCONVERTED,
                        currentHourCONVERTED);

            }catch (RuntimeException e){
//Фантик
                System.out.println("Current hour: "+currentHour+":00 not have records for this camera\n"+"Тип расписания -" + schedule +"\n" );
                testFile.write("Current hour: "+currentHour+":00 not have records for this camera\n"+"Тип расписания -" + schedule +"\n" );
//*Фантик
            }
        }else{
            //TODO Проверить как лучше сделать
            try{
                //Проверка Постоянная запись
                CamDrivePageObject.clickHour(
                        currentDayCONVERTED,
                        currentMonthCONVERTED,
                        currentHourCONVERTED);

            }catch (RuntimeException e){
                errorFile.write("Current hour "+currentHour+":00 not have records!");
                errorFile.close();
                statisticOfError++;
                CamDrivePageObject.exitTest();
            }
        }
//------------------------------------------------------------------------------------------------------------------------


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
                if((schedule == 1)||(schedule == 3)) {
                    System.out.println("No records. Block " + currentHourCONVERTED + ":00h. " + currentFirstMinuteCONVERTED + "min-" + currentLastMinuteCONVERTED + "min\n");
                    testFile.write("No records. Block: " + currentHourCONVERTED + ":00h. " + currentFirstMinuteCONVERTED + "min-" + currentLastMinuteCONVERTED + "min\n");
                }else{
                    errorFile.write("No records. Block: " + currentHourCONVERTED + ":00h. " + currentFirstMinuteCONVERTED + "min-" + currentLastMinuteCONVERTED + "min\n");
                    System.out.println("No records. Block " + currentHourCONVERTED + ":00h. " + currentFirstMinuteCONVERTED + "min-" + currentLastMinuteCONVERTED + "min\n");
                    statisticOfError++;
                }
//*Фантик
                continue;
            }

            CamDrivePageObject.loadArchiveVideoAndroid();

            try {
                CamDrivePageObject.checkLoadVideoPlayerForAndroidMW();
            }catch (RuntimeException e){
//Фантик
                if((schedule == 1)||(schedule == 3)) {
                    System.out.println("Error load archive video. Block: " + currentHourCONVERTED + ":00h. " + currentFirstMinuteCONVERTED + "min-" + currentLastMinuteCONVERTED + "min\n");
                    testFile.write("Error load archive video. Block: " + currentHourCONVERTED + ":00h. " + currentFirstMinuteCONVERTED + "min-" + currentLastMinuteCONVERTED + "min\n");
                }else{
                    System.out.println("Error load archive video. Block: " + currentHourCONVERTED + ":00h. " + currentFirstMinuteCONVERTED + "min-" + currentLastMinuteCONVERTED + "min\n");
                    errorFile.write("Error load archive video. Block: " + currentHourCONVERTED + ":00h. " + currentFirstMinuteCONVERTED + "min-" + currentLastMinuteCONVERTED + "min\n");
                    statisticOfError++;
                }
//*Фантик
                CamDrivePageObject.clickBackOnMinuteScreenAndroid();
                continue;
            }
            CamDrivePageObject.clickOnVideoForm();
//---------------------------------------------

            int time = 0;

            Object attribute = CamDrivePageObject.getTime("max");

            String result = String.valueOf(attribute);

            int indexEndResult = result.lastIndexOf(".");

            if(indexEndResult == -1) {
                try {
                    time = Integer.parseInt(result);
                } catch (Exception e) {
                    System.out.println("Cannot format type int current element :" + attribute);
                    statisticOfError++;
                    errorFile.write("JS return incorrect type variable: "+ attribute);
                }
            } else{
                time = Integer.parseInt(result.substring(0,indexEndResult));
            }

            if(((schedule == 0) & (time<290))){
                errorFile.write("Current block: " + currentHourCONVERTED + ":00h. " + currentFirstMinuteCONVERTED + "min-" + currentLastMinuteCONVERTED + "min\n"+"Record is less then must be: "+attribute+"/290 seconds\n");
                statisticOfError++;
            }
//---------------------------------------------
//Фантик
            System.out.println(attribute+" sec");
            testFile.write(attribute+" sec\n");
//*Фантик
            CamDrivePageObject.clickCloseButtonOnPlayArchiveScreen();
            CamDrivePageObject.clickBackOnMinuteScreenAndroid();
        }
        //CamDrivePageObject.clickBackOnHourScreenCD100_E75A();
//Закрытие потока для фантика
        testFile.close();
        errorFile.close();
        if (statisticOfError == 0) errorLog.delete();

//*Закрытие потока для фантика
    }

    @Test
    public void testCD600_EF78_MS6() throws Exception {
        int statisticOfError =0;
        String cameraName = "Camera: CD600_EF78_MS6";
        String errorLogFile = "ErrorRecordCD600_EF78_MS6.txt";
        File cleanFile = new File("TestRecordCD600_EF78_MS6_SERV.txt");
        cleanFile.delete();
        File errorLog = new File(errorLogFile);
        errorLog.delete();

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
        System.out.println("\nData: "+currentYear+"/"+currentMonth+"/"+currentDay+"\nStart test in "+currentHour+" hour and 00 minutes ");
        FileWriter testFile = new FileWriter("TestRecordCD600_EF78_MS6_SERV.txt",false);
        testFile.write("Data: "+currentYear+"/"+currentMonth+"/"+currentDay+"\nStart test in "+currentHour+" hour and 00 minutes \n");
        System.out.println(cameraName+" \n");
        testFile.write(cameraName+" \n");
        System.out.println("Imitation web Android");
        testFile.write("Imitation web Android");
//*Открытие потока для фантика

        int schedule = CamDrivePageObject.returnCurrentScheldueStatus(currentHour,"23d6fb09e101dc587b8b16db3cf7b5dd");

//------------------------------------------------------------------------------------------------------------------------
        //Отчёт с ошибками




        FileWriter errorFile = new FileWriter(errorLogFile,true);
        errorFile.write("Data: "+currentYear+"/"+currentMonth+"/"+currentDay+"\nStart test in "+currentHour+" hour and 00 minutes \n"+"Camera: CD600_EF78_MS6 \n"+"Imitation web Android\n");
        if ((schedule == 1)||(schedule == 3)){
            try{
                CamDrivePageObject.clickHour(
                        currentDayCONVERTED,
                        currentMonthCONVERTED,
                        currentHourCONVERTED);

            }catch (RuntimeException e){
//Фантик
                System.out.println("Current hour: "+currentHour+":00 not have records for this camera\n"+"Тип расписания -" + schedule +"\n" );
                testFile.write("Current hour: "+currentHour+":00 not have records for this camera\n"+"Тип расписания -" + schedule +"\n" );
//*Фантик
            }
        }else{
            //TODO Проверить как лучше сделать
            try{
                //Проверка Постоянная запись
                CamDrivePageObject.clickHour(
                        currentDayCONVERTED,
                        currentMonthCONVERTED,
                        currentHourCONVERTED);

            }catch (RuntimeException e){
                errorFile.write("Current hour "+currentHour+":00 not have records!");
                errorFile.close();
                statisticOfError++;
                CamDrivePageObject.exitTest();
            }
        }
//------------------------------------------------------------------------------------------------------------------------
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
                if((schedule == 1)||(schedule == 3)) {
                    System.out.println("No records. Block " + currentHourCONVERTED + ":00h. " + currentFirstMinuteCONVERTED + "min-" + currentLastMinuteCONVERTED + "min\n");
                    testFile.write("No records. Block: " + currentHourCONVERTED + ":00h. " + currentFirstMinuteCONVERTED + "min-" + currentLastMinuteCONVERTED + "min\n");
                }else{
                    errorFile.write("No records. Block: " + currentHourCONVERTED + ":00h. " + currentFirstMinuteCONVERTED + "min-" + currentLastMinuteCONVERTED + "min\n");
                    System.out.println("No records. Block " + currentHourCONVERTED + ":00h. " + currentFirstMinuteCONVERTED + "min-" + currentLastMinuteCONVERTED + "min\n");
                    statisticOfError++;
                }
//*Фантик
                continue;
            }

            CamDrivePageObject.loadArchiveVideoAndroid();

            try {
                CamDrivePageObject.checkLoadVideoPlayerForAndroidMW();
            }catch (RuntimeException e){
//Фантик
                if((schedule == 1)||(schedule == 3)) {
                    System.out.println("Error load archive video. Block: " + currentHourCONVERTED + ":00h. " + currentFirstMinuteCONVERTED + "min-" + currentLastMinuteCONVERTED + "min\n");
                    testFile.write("Error load archive video. Block: " + currentHourCONVERTED + ":00h. " + currentFirstMinuteCONVERTED + "min-" + currentLastMinuteCONVERTED + "min\n");
                }else{
                    System.out.println("Error load archive video. Block: " + currentHourCONVERTED + ":00h. " + currentFirstMinuteCONVERTED + "min-" + currentLastMinuteCONVERTED + "min\n");
                    errorFile.write("Error load archive video. Block: " + currentHourCONVERTED + ":00h. " + currentFirstMinuteCONVERTED + "min-" + currentLastMinuteCONVERTED + "min\n");
                    statisticOfError++;
                }
//*Фантик
                CamDrivePageObject.clickBackOnMinuteScreenAndroid();
                continue;
            }
            CamDrivePageObject.clickOnVideoForm();
//---------------------------------------------
            int time = 0;

            Object attribute = CamDrivePageObject.getTime("max");

            String result = String.valueOf(attribute);

            int indexEndResult = result.lastIndexOf(".");

            if(indexEndResult == -1) {
                try {
                    time = Integer.parseInt(result);
                } catch (Exception e) {
                    System.out.println("Cannot format type int current element :" + attribute);
                    statisticOfError++;
                    errorFile.write("JS return incorrect type variable: "+ attribute);
                }
            } else{
                time = Integer.parseInt(result.substring(0,indexEndResult));
            }

            if(((schedule == 0) & (time<290))){
                errorFile.write("Current block: " + currentHourCONVERTED + ":00h. " + currentFirstMinuteCONVERTED + "min-" + currentLastMinuteCONVERTED + "min\n"+"Record is less then must be: "+attribute+"/290 seconds\n");
                statisticOfError++;
            }
//---------------------------------------------
//Фантик
            System.out.println(attribute+" sec");
            testFile.write(attribute+" sec\n");
//*Фантик
            CamDrivePageObject.clickCloseButtonOnPlayArchiveScreen();
            CamDrivePageObject.clickBackOnMinuteScreenAndroid();
        }
        //CamDrivePageObject.clickBackOnHourScreenCD100_E75A();
//Закрытие потока для фантика
        testFile.close();
        errorFile.close();
        if (statisticOfError == 0) errorLog.delete();

//*Закрытие потока для фантика
    }

    @Test
    public void testCD630_910D_MS6() throws Exception {
        int statisticOfError =0;
        String cameraName = "Camera: CD630_910D_MS6";
        String errorLogFile = "ErrorRecordCD630_910D_MS6.txt";
        File cleanFile = new File("TestRecordCD630_910D_MS6_DEV.txt");
        cleanFile.delete();
        File errorLog = new File(errorLogFile);
        errorLog.delete();


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
        System.out.println("\nData: "+currentYear+"/"+currentMonth+"/"+currentDay+"\nStart test in "+currentHour+" hour and 00 minutes ");
        FileWriter testFile = new FileWriter("TestRecordCD630_910D_MS6_DEV.txt",false);
        testFile.write("Data: "+currentYear+"/"+currentMonth+"/"+currentDay+"\nStart test in "+currentHour+" hour and 00 minutes \n");
        System.out.println(cameraName+" \n");
        testFile.write(cameraName+" \n");
        System.out.println("Imitation web Android");
        testFile.write("Imitation web Android");
//*Открытие потока для фантика



        int schedule = CamDrivePageObject.returnCurrentScheldueStatus(currentHour,"81037196ecb69d5306abcb8f61fba33c");
//------------------------------------------------------------------------------------------------------------------------
        //Отчёт с ошибками



        FileWriter errorFile = new FileWriter(errorLogFile,true);
        errorFile.write("Data: "+currentYear+"/"+currentMonth+"/"+currentDay+"\nStart test in "+currentHour+" hour and 00 minutes \n"+"Camera: CD630_910D_MS6 \n"+"Imitation web Android\n");
        if ((schedule == 1)||(schedule == 3)){
            try{
                CamDrivePageObject.clickHour(
                        currentDayCONVERTED,
                        currentMonthCONVERTED,
                        currentHourCONVERTED);

            }catch (RuntimeException e){
//Фантик
                System.out.println("Current hour: "+currentHour+":00 not have records for this camera\n"+"Тип расписания -" + schedule +"\n" );
                testFile.write("Current hour: "+currentHour+":00 not have records for this camera\n"+"Тип расписания -" + schedule +"\n" );
//*Фантик
            }
        }else{
            //TODO Проверить как лучше сделать
            try{
                //Проверка Постоянная запись
                CamDrivePageObject.clickHour(
                        currentDayCONVERTED,
                        currentMonthCONVERTED,
                        currentHourCONVERTED);

            }catch (RuntimeException e){
                errorFile.write("Current hour "+currentHour+":00 not have records!");
                errorFile.close();
                statisticOfError++;
                CamDrivePageObject.exitTest();
            }
        }
//------------------------------------------------------------------------------------------------------------------------
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
                if((schedule == 1)||(schedule == 3)) {
                    System.out.println("No records. Block " + currentHourCONVERTED + ":00h. " + currentFirstMinuteCONVERTED + "min-" + currentLastMinuteCONVERTED + "min\n");
                    testFile.write("No records. Block: " + currentHourCONVERTED + ":00h. " + currentFirstMinuteCONVERTED + "min-" + currentLastMinuteCONVERTED + "min\n");
                }else{
                    errorFile.write("No records. Block: " + currentHourCONVERTED + ":00h. " + currentFirstMinuteCONVERTED + "min-" + currentLastMinuteCONVERTED + "min\n");
                    System.out.println("No records. Block " + currentHourCONVERTED + ":00h. " + currentFirstMinuteCONVERTED + "min-" + currentLastMinuteCONVERTED + "min\n");
                    statisticOfError++;
                }
//*Фантик
                continue;
            }

            CamDrivePageObject.loadArchiveVideoAndroid();

            try {
                CamDrivePageObject.checkLoadVideoPlayerForAndroidMW();
            }catch (RuntimeException e){
//Фантик
                if((schedule == 1)||(schedule == 3)) {
                    System.out.println("Error load archive video. Block: " + currentHourCONVERTED + ":00h. " + currentFirstMinuteCONVERTED + "min-" + currentLastMinuteCONVERTED + "min\n");
                    testFile.write("Error load archive video. Block: " + currentHourCONVERTED + ":00h. " + currentFirstMinuteCONVERTED + "min-" + currentLastMinuteCONVERTED + "min\n");
                }else{
                    System.out.println("Error load archive video. Block: " + currentHourCONVERTED + ":00h. " + currentFirstMinuteCONVERTED + "min-" + currentLastMinuteCONVERTED + "min\n");
                    errorFile.write("Error load archive video. Block: " + currentHourCONVERTED + ":00h. " + currentFirstMinuteCONVERTED + "min-" + currentLastMinuteCONVERTED + "min\n");
                    statisticOfError++;
                }
//*Фантик
                CamDrivePageObject.clickBackOnMinuteScreenAndroid();
                continue;
            }
            CamDrivePageObject.clickOnVideoForm();
//---------------------------------------------
            int time = 0;

            Object attribute = CamDrivePageObject.getTime("max");

            String result = String.valueOf(attribute);

            int indexEndResult = result.lastIndexOf(".");

            if(indexEndResult == -1) {
                try {
                    time = Integer.parseInt(result);
                } catch (Exception e) {
                    System.out.println("Cannot format type int current element :" + attribute);
                    statisticOfError++;
                    errorFile.write("JS return incorrect type variable: "+ attribute);
                }
            } else{
                time = Integer.parseInt(result.substring(0,indexEndResult));
            }

            if(((schedule == 0) & (time<290))){
                errorFile.write("Current block: " + currentHourCONVERTED + ":00h. " + currentFirstMinuteCONVERTED + "min-" + currentLastMinuteCONVERTED + "min\n"+"Record is less then must be: "+attribute+"/290 seconds\n");
                statisticOfError++;
            }
//---------------------------------------------
//Фантик
            System.out.println(attribute+" sec");
            testFile.write(attribute+" sec\n");
//*Фантик
            CamDrivePageObject.clickCloseButtonOnPlayArchiveScreen();
            CamDrivePageObject.clickBackOnMinuteScreenAndroid();
        }
        //CamDrivePageObject.clickBackOnHourScreenCD100_E75A();
//Закрытие потока для фантика
        testFile.close();
        errorFile.close();
        if (statisticOfError == 0) errorLog.delete();

//*Закрытие потока для фантика
    }

    @Test
    public void testN1001_3A00_bwd() throws Exception {
        int statisticOfError =0;
        String cameraName = "Camera: N1001_3A00_bwd";
        String errorLogFile = "ErrorRecordN1001_3A00_bwd.txt";
        File cleanFile = new File("TestRecordN1001_3A00_bwd.txt");
        cleanFile.delete();
        File errorLog = new File(errorLogFile);
        errorLog.delete();
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
        System.out.println("\nData: "+currentYear+"/"+currentMonth+"/"+currentDay+"\nStart test in "+currentHour+" hour and 00 minutes ");
        FileWriter testFile = new FileWriter("TestRecordN1001_3A00_bwd.txt",false);
        testFile.write("Data: "+currentYear+"/"+currentMonth+"/"+currentDay+"\nStart test in "+currentHour+" hour and 00 minutes \n");
        System.out.println(cameraName+" \n");
        testFile.write(cameraName+" \n");
        System.out.println("Imitation web Android");
        testFile.write("Imitation web Android");
//*Открытие потока для фантика

        int schedule = CamDrivePageObject.returnCurrentScheldueStatus(currentHour,"4ac35c97e26af54c55caa2b36ceab0ca");
//------------------------------------------------------------------------------------------------------------------------
        //Отчёт с ошибками




        FileWriter errorFile = new FileWriter(errorLogFile,true);
        errorFile.write("Data: "+currentYear+"/"+currentMonth+"/"+currentDay+"\nStart test in "+currentHour+" hour and 00 minutes \n"+"Camera: N1001_3A00_bwd \n"+"Imitation web Android\n");
        if ((schedule == 1)||(schedule == 3)){
            try{
                CamDrivePageObject.clickHour(
                        currentDayCONVERTED,
                        currentMonthCONVERTED,
                        currentHourCONVERTED);

            }catch (RuntimeException e){
//Фантик
                System.out.println("Current hour: "+currentHour+":00 not have records for this camera\n"+"Тип расписания -" + schedule +"\n" );
                testFile.write("Current hour: "+currentHour+":00 not have records for this camera\n"+"Тип расписания -" + schedule +"\n" );
//*Фантик
            }
        }else{
            //TODO Проверить как лучше сделать
            try{
                //Проверка Постоянная запись
                CamDrivePageObject.clickHour(
                        currentDayCONVERTED,
                        currentMonthCONVERTED,
                        currentHourCONVERTED);

            }catch (RuntimeException e){
                errorFile.write("Current hour "+currentHour+":00 not have records!");
                errorFile.close();
                statisticOfError++;
                CamDrivePageObject.exitTest();
            }
        }
//------------------------------------------------------------------------------------------------------------------------

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
                if((schedule == 1)||(schedule == 3)) {
                    System.out.println("No records. Block " + currentHourCONVERTED + ":00h. " + currentFirstMinuteCONVERTED + "min-" + currentLastMinuteCONVERTED + "min\n");
                    testFile.write("No records. Block: " + currentHourCONVERTED + ":00h. " + currentFirstMinuteCONVERTED + "min-" + currentLastMinuteCONVERTED + "min\n");
                }else{
                    errorFile.write("No records. Block: " + currentHourCONVERTED + ":00h. " + currentFirstMinuteCONVERTED + "min-" + currentLastMinuteCONVERTED + "min\n");
                    System.out.println("No records. Block " + currentHourCONVERTED + ":00h. " + currentFirstMinuteCONVERTED + "min-" + currentLastMinuteCONVERTED + "min\n");
                    statisticOfError++;
                }
//*Фантик
                continue;
            }

            CamDrivePageObject.loadArchiveVideoAndroid();

            try {
                CamDrivePageObject.checkLoadVideoPlayerForAndroidMW();
            }catch (RuntimeException e){
//Фантик
                if((schedule == 1)||(schedule == 3)) {
                    System.out.println("Error load archive video. Block: " + currentHourCONVERTED + ":00h. " + currentFirstMinuteCONVERTED + "min-" + currentLastMinuteCONVERTED + "min\n");
                    testFile.write("Error load archive video. Block: " + currentHourCONVERTED + ":00h. " + currentFirstMinuteCONVERTED + "min-" + currentLastMinuteCONVERTED + "min\n");
                }else{
                    System.out.println("Error load archive video. Block: " + currentHourCONVERTED + ":00h. " + currentFirstMinuteCONVERTED + "min-" + currentLastMinuteCONVERTED + "min\n");
                    errorFile.write("Error load archive video. Block: " + currentHourCONVERTED + ":00h. " + currentFirstMinuteCONVERTED + "min-" + currentLastMinuteCONVERTED + "min\n");
                    statisticOfError++;
                }
//*Фантик
                CamDrivePageObject.clickBackOnMinuteScreenAndroid();
                continue;
            }
            CamDrivePageObject.clickOnVideoForm();
//---------------------------------------------
            int time = 0;

            Object attribute = CamDrivePageObject.getTime("max");

            String result = String.valueOf(attribute);

            int indexEndResult = result.lastIndexOf(".");

            if(indexEndResult == -1) {
                try {
                    time = Integer.parseInt(result);
                } catch (Exception e) {
                    System.out.println("Cannot format type int current element :" + attribute);
                    statisticOfError++;
                    errorFile.write("JS return incorrect type variable: "+ attribute);
                }
            } else{
                time = Integer.parseInt(result.substring(0,indexEndResult));
            }

            if(((schedule == 0) & (time<290))){
                errorFile.write("Current block: " + currentHourCONVERTED + ":00h. " + currentFirstMinuteCONVERTED + "min-" + currentLastMinuteCONVERTED + "min\n"+"Record is less then must be: "+attribute+"/290 seconds\n");
                statisticOfError++;
            }
//---------------------------------------------
//Фантик
            System.out.println(attribute+" sec");
            testFile.write(attribute+" sec\n");
//*Фантик
            CamDrivePageObject.clickCloseButtonOnPlayArchiveScreen();
            CamDrivePageObject.clickBackOnMinuteScreenAndroid();
        }
        //CamDrivePageObject.clickBackOnHourScreenCD100_E75A();
//Закрытие потока для фантика
        testFile.close();
        errorFile.close();
        if (statisticOfError == 0) errorLog.delete();

//*Закрытие потока для фантика
    }
}
