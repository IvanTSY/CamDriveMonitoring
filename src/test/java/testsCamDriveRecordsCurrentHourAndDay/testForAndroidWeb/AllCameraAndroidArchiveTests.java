package testsCamDriveRecordsCurrentHourAndDay.testForAndroidWeb;

import lib.CoreTestCase;
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
    public void testCD100_E75A_MS3() throws Exception {
//Чистим файл перед запуском теста
        File cleanFile = new File("TestRecordCD100_E75A_MS3.txt");
        cleanFile.delete();

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

        int schedule = CamDrivePageObject.returnCurrentScheldueStatus(currentHour,"aee40e829262b7930f529c4fee6d326a");
//------------------------------------------------------------------------------------------------------------------------
        //Отчёт с ошибками
        File errorLog = new File("ErrorRecordCD100_E75A_MS3.txt");
        errorLog.delete();



        FileWriter errorFile = new FileWriter("ErrorRecordCD100_E75A_MS3.txt",true);
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
                }
//*Фантик
                CamDrivePageObject.clickBackOnMinuteScreenAndroid();
                continue;
            }
            CamDrivePageObject.clickOnVideoForm();
//---------------------------------------------
            double attribute = Double.parseDouble(CamDrivePageObject.getTime("max"));
            if(attribute<290.00){
                errorFile.write("Current block: " + currentHourCONVERTED + ":00h. " + currentFirstMinuteCONVERTED + "min-" + currentLastMinuteCONVERTED + "min\n"+"Record is less then must be: "+attribute+"/300 seconds\n");
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
//*Закрытие потока для фантика
    }

    @Test
    public void testCD100_E772_MS4() throws Exception {


        File cleanFile = new File("TestRecordCD100_E772_MS4.txt");
        cleanFile.delete();

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
        File errorLog = new File("ErrorRecordCD100_E772_MS4.txt");
        errorLog.delete();

        int schedule = CamDrivePageObject.returnCurrentScheldueStatus(currentHour,"95100e92696c2163bef3185cd29deff2");

        FileWriter errorFile = new FileWriter("ErrorRecordCD100_E772_MS4.txt",true);
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
                }
//*Фантик
                CamDrivePageObject.clickBackOnMinuteScreenAndroid();
                continue;
            }
            CamDrivePageObject.clickOnVideoForm();
            //---------------------------------------------
            double attribute = Double.parseDouble(CamDrivePageObject.getTime("max"));
            if(attribute<290.00){
                errorFile.write("Current block: " + currentHourCONVERTED + ":00h. " + currentFirstMinuteCONVERTED + "min-" + currentLastMinuteCONVERTED + "min\n"+"Record is less then must be: "+attribute+"/300 seconds\n");
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
//*Закрытие потока для фантика
    }

    @Test
    public void testCD100_E778_MS5() throws Exception {

        File cleanFile = new File("TestRecordCD100_E778_MS5.txt");
        cleanFile.delete();

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



        int schedule = CamDrivePageObject.returnCurrentScheldueStatus(currentHour,"aef64c10d39975425e711014dcb8a061");


        //------------------------------------------------------------------------------------------------------------------------
        //Отчёт с ошибками
        File errorLog = new File("ErrorRecordCD100_E778_MS5.txt");
        errorLog.delete();



        FileWriter errorFile = new FileWriter("ErrorRecordCD100_E778_MS5.txt",true);
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
                }
//*Фантик
                CamDrivePageObject.clickBackOnMinuteScreenAndroid();
                continue;
            }
            CamDrivePageObject.clickOnVideoForm();
//---------------------------------------------
            double attribute = Double.parseDouble(CamDrivePageObject.getTime("max"));
            if(attribute<290.00){
                errorFile.write("Current block: " + currentHourCONVERTED + ":00h. " + currentFirstMinuteCONVERTED + "min-" + currentLastMinuteCONVERTED + "min\n"+"Record is less then must be: "+attribute+"/300 seconds\n");
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
//*Закрытие потока для фантика
    }

    @Test
    public void testCD310_2E51_MS4() throws Exception {

        File cleanFile = new File("TestRecordCD310_2E51_MS4.txt");
        cleanFile.delete();
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



        int schedule = CamDrivePageObject.returnCurrentScheldueStatus(currentHour,"78cbbc49a31cdff1778023fc57e89f46");
//------------------------------------------------------------------------------------------------------------------------
        //Отчёт с ошибками
        File errorLog = new File("ErrorRecordCD310_2E51_MS4.txt");
        errorLog.delete();



        FileWriter errorFile = new FileWriter("ErrorRecordCD310_2E51_MS4.txt",true);
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
                }
//*Фантик
                CamDrivePageObject.clickBackOnMinuteScreenAndroid();
                continue;
            }
            CamDrivePageObject.clickOnVideoForm();
//---------------------------------------------
            double attribute = Double.parseDouble(CamDrivePageObject.getTime("max"));
            if(attribute<290.00){
                errorFile.write("Current block: " + currentHourCONVERTED + ":00h. " + currentFirstMinuteCONVERTED + "min-" + currentLastMinuteCONVERTED + "min\n"+"Record is less then must be: "+attribute+"/300 seconds\n");
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
//*Закрытие потока для фантика
    }

    @Test
    public void testCD320_AA06_MS3() throws Exception {

        File cleanFile = new File("TestRecordCD320_AA06_MS3_DEV.txt");
        cleanFile.delete();

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



        int schedule = CamDrivePageObject.returnCurrentScheldueStatus(currentHour,"2e6fb75139e4226198f9f6c0786e8b8a");
//------------------------------------------------------------------------------------------------------------------------
        //Отчёт с ошибками
        File errorLog = new File("ErrorRecordCD320_AA06_MS3.txt");
        errorLog.delete();



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
                }
//*Фантик
                CamDrivePageObject.clickBackOnMinuteScreenAndroid();
                continue;
            }
            CamDrivePageObject.clickOnVideoForm();
//---------------------------------------------
            double attribute = Double.parseDouble(CamDrivePageObject.getTime("max"));
            if(attribute<290.00){
                errorFile.write("Current block: " + currentHourCONVERTED + ":00h. " + currentFirstMinuteCONVERTED + "min-" + currentLastMinuteCONVERTED + "min\n"+"Record is less then must be: "+attribute+"/300 seconds\n");
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
//*Закрытие потока для фантика
    }

    @Test
    public void testCD320_AA78_MS5() throws Exception {

        File cleanFile = new File("TestRecordCD320_AA78_MS5.txt");
        cleanFile.delete();

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



        int schedule = CamDrivePageObject.returnCurrentScheldueStatus(currentHour,"b63c65eeaa4410befcac0a2e96281f5c");
//------------------------------------------------------------------------------------------------------------------------
        //Отчёт с ошибками
        File errorLog = new File("ErrorRecordCD320_AA78_MS5.txt");
        errorLog.delete();



        FileWriter errorFile = new FileWriter("ErrorRecordCD320_AA78_MS5.txt",true);
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
                }
//*Фантик
                CamDrivePageObject.clickBackOnMinuteScreenAndroid();
                continue;
            }
            CamDrivePageObject.clickOnVideoForm();
//---------------------------------------------
            double attribute = Double.parseDouble(CamDrivePageObject.getTime("max"));
            if(attribute<290.00){
                errorFile.write("Current block: " + currentHourCONVERTED + ":00h. " + currentFirstMinuteCONVERTED + "min-" + currentLastMinuteCONVERTED + "min\n"+"Record is less then must be: "+attribute+"/300 seconds\n");
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

//*Закрытие потока для фантика
    }

    @Test
    public void testCD600_EF78_MS6() throws Exception {

        File cleanFile = new File("TestRecordCD600_EF78_MS6_SERV.txt");
        cleanFile.delete();

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

        int schedule = CamDrivePageObject.returnCurrentScheldueStatus(currentHour,"23d6fb09e101dc587b8b16db3cf7b5dd");

//------------------------------------------------------------------------------------------------------------------------
        //Отчёт с ошибками
        File errorLog = new File("ErrorRecordCD600_EF78_MS6.txt");
        errorLog.delete();



        FileWriter errorFile = new FileWriter("ErrorRecordCD600_EF78_MS6.txt",true);
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
                }
//*Фантик
                CamDrivePageObject.clickBackOnMinuteScreenAndroid();
                continue;
            }
            CamDrivePageObject.clickOnVideoForm();
//---------------------------------------------
            double attribute = Double.parseDouble(CamDrivePageObject.getTime("max"));
            if(attribute<290.00){
                errorFile.write("Current block: " + currentHourCONVERTED + ":00h. " + currentFirstMinuteCONVERTED + "min-" + currentLastMinuteCONVERTED + "min\n"+"Record is less then must be: "+attribute+"/300 seconds\n");
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
//*Закрытие потока для фантика
    }

    @Test
    public void testCD630_910D_MS6() throws Exception {

        File cleanFile = new File("TestRecordCD630_910D_MS6_DEV.txt");
        cleanFile.delete();

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



        int schedule = CamDrivePageObject.returnCurrentScheldueStatus(currentHour,"81037196ecb69d5306abcb8f61fba33c");
//------------------------------------------------------------------------------------------------------------------------
        //Отчёт с ошибками
        File errorLog = new File("ErrorRecordCD630_910D_MS6.txt");
        errorLog.delete();



        FileWriter errorFile = new FileWriter("ErrorRecordCD630_910D_MS6.txt",true);
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
                }
//*Фантик
                CamDrivePageObject.clickBackOnMinuteScreenAndroid();
                continue;
            }
            CamDrivePageObject.clickOnVideoForm();
//---------------------------------------------
            double attribute = Double.parseDouble(CamDrivePageObject.getTime("max"));
            if(attribute<290.00){
                errorFile.write("Current block: " + currentHourCONVERTED + ":00h. " + currentFirstMinuteCONVERTED + "min-" + currentLastMinuteCONVERTED + "min\n"+"Record is less then must be: "+attribute+"/300 seconds\n");
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
//*Закрытие потока для фантика
    }

    @Test
    public void testN1001_3A00_bwd() throws Exception {
        File cleanFile = new File("TestRecordN1001_3A00_bwd.txt");
        cleanFile.delete();

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

        int schedule = CamDrivePageObject.returnCurrentScheldueStatus(currentHour,"4ac35c97e26af54c55caa2b36ceab0ca");
//------------------------------------------------------------------------------------------------------------------------
        //Отчёт с ошибками
        File errorLog = new File("ErrorRecordN1001_3A00_bwd.txt");
        errorLog.delete();



        FileWriter errorFile = new FileWriter("ErrorRecordN1001_3A00_bwd.txt",true);
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
                }
//*Фантик
                CamDrivePageObject.clickBackOnMinuteScreenAndroid();
                continue;
            }
            CamDrivePageObject.clickOnVideoForm();
//---------------------------------------------
            double attribute = Double.parseDouble(CamDrivePageObject.getTime("max"));
            if(attribute<290.00){
                errorFile.write("Current block: " + currentHourCONVERTED + ":00h. " + currentFirstMinuteCONVERTED + "min-" + currentLastMinuteCONVERTED + "min\n"+"Record is less then must be: "+attribute+"/300 seconds\n");
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
//*Закрытие потока для фантика
    }
}
