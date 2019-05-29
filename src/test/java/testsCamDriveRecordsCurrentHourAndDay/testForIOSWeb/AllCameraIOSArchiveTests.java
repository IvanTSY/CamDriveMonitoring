package testsCamDriveRecordsCurrentHourAndDay.testForIOSWeb;

import lib.CoreTestCase;
import lib.ui.CamDrivePageObject;
import lib.ui.factories.CamDrivePageObjectFactory;
import org.junit.Test;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Calendar;

public class AllCameraIOSArchiveTests extends CoreTestCase {
    String currentFirstMinuteCONVERTED;
    String currentLastMinuteCONVERTED;
    String currentMinuteCONVERTED;
    String currentHourCONVERTED;
    String currentDayCONVERTED;
    String currentMonthCONVERTED;
    int currentYear = Calendar.getInstance().getWeekYear();
    int currentHour = Calendar.getInstance().getTime().getHours();
    int currentDay = Calendar.getInstance().getTime().getDate();
    int currentMonth = Calendar.getInstance().getTime().getMonth() + 1;
    int currentMinute = Calendar.getInstance().getTime().getMinutes();
//Определяем шаг интервала в зависимости от WEB платформы
    int tick = 10;

    @Test
    public void testCD100_E75A_MS3_IOS() throws InterruptedException, IOException {
        if (currentMinute < tick) {
            currentMinute = 59;
            currentHour = currentHour - 1;
        }
        CamDrivePageObject CamDrivePageObject = CamDrivePageObjectFactory.get(driver);
        CamDrivePageObject.authorizationOnCamdrive();

        CamDrivePageObject.choiseCD100_E75A_MS3_DEV();
        CamDrivePageObject.choiseTheCurrentDay();
//Открытие потока для фантика
//======================================================Сделать красиво!
        System.out.println("\nData: "+currentYear+"/"+currentMonth+"/"+currentDay+"\nStart test in "+currentHour+" hour and "+currentMinute+" minutes ");
        FileWriter testFile = new FileWriter("TestRecordIOSCD100_E75A_MS3.txt",false);
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

        for (int m = 0; m < (currentMinute )/tick; m ++){

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
//            CamDrivePageObject.checkLoadVideoPlayerForIosMW();
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
                CamDrivePageObject.clickBackOnMinuteScreenIOS();
                continue;
            }
            //TODO : JSE в тесте
            Object attribute = CamDrivePageObject.getTimeDurationVideoForIOSArchive(); //допилить джаваскрипт
            System.out.println(attribute+" sec");
            testFile.write(attribute+" sec\n");
            CamDrivePageObject.clickBackOnMinuteScreenIOS();
        }
//Закрытие потока для фантика
        testFile.close();
//*Закрытие потока для фантика
    }

    @Test
    public void testCD100_E772_MS4_IOS() throws InterruptedException, IOException {

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

        for (int m = 0; m < (currentMinute )/tick; m ++){

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
                CamDrivePageObject.clickBackOnMinuteScreenIOS();
                continue;
            }
            //TODO : JSE в тесте
            Object attribute = CamDrivePageObject.getTimeDurationVideoForIOSArchive(); //допилить джаваскрипт
            System.out.println(attribute+" sec");
            testFile.write(attribute+" sec\n");
            CamDrivePageObject.clickBackOnMinuteScreenIOS();
        }
//Закрытие потока для фантика
        testFile.close();
//*Закрытие потока для фантика
    }

    @Test
    public void testCD100_E778_MS5_IOS() throws InterruptedException, IOException {
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

        CamDrivePageObject.choiseCD100_E778_MS5();
        CamDrivePageObject.choiseTheCurrentDay();
//Открытие потока для фантика
//======================================================Сделать красиво!
        System.out.println("\nData: "+currentYear+"/"+currentMonth+"/"+currentDay+"\nStart test in "+currentHour+" hour and "+currentMinute+" minutes ");
        FileWriter testFile = new FileWriter("TestRecordIOSCD100_E778_MS5.txt",false);
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

        for (int m = 0; m < (currentMinute )/tick; m ++){

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
                CamDrivePageObject.clickBackOnMinuteScreenIOS();
                continue;
            }
            //TODO : JSE в тесте
            Object attribute = CamDrivePageObject.getTimeDurationVideoForIOSArchive(); //допилить джаваскрипт
            System.out.println(attribute+" sec");
            testFile.write(attribute+" sec\n");
            CamDrivePageObject.clickBackOnMinuteScreenIOS();
        }
//Закрытие потока для фантика
        testFile.close();
//*Закрытие потока для фантика
    }
    @Test
    public void testCD310_2E51_MS4_IOS() throws InterruptedException, IOException {

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

        CamDrivePageObject.choiseCD310_2E51_MS4_DEV();
        CamDrivePageObject.choiseTheCurrentDay();
//Открытие потока для фантика
//======================================================Сделать красиво!
        System.out.println("\nData: "+currentYear+"/"+currentMonth+"/"+currentDay+"\nStart test in "+currentHour+" hour and "+currentMinute+" minutes ");
        FileWriter testFile = new FileWriter("TestRecordIOSCD310_2E51_MS4.txt",false);
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

        for (int m = 0; m < (currentMinute )/tick; m ++){

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
                CamDrivePageObject.clickBackOnMinuteScreenIOS();
                continue;
            }
            //TODO : JSE в тесте
            Object attribute = CamDrivePageObject.getTimeDurationVideoForIOSArchive(); //допилить джаваскрипт
            System.out.println(attribute+" sec");
            testFile.write(attribute+" sec\n");
            CamDrivePageObject.clickBackOnMinuteScreenIOS();
        }
//Закрытие потока для фантика
        testFile.close();
//*Закрытие потока для фантика
    }

    @Test
    public void testCD320_AA06_MS3_IOS() throws InterruptedException, IOException {

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

        CamDrivePageObject.choiseCD320_AA06_MS3_DEV();
        CamDrivePageObject.choiseTheCurrentDay();
//Открытие потока для фантика
//======================================================Сделать красиво!
        System.out.println("\nData: "+currentYear+"/"+currentMonth+"/"+currentDay+"\nStart test in "+currentHour+" hour and "+currentMinute+" minutes ");
        FileWriter testFile = new FileWriter("TestRecordIOSCD320_AA06_MS3_DEV.txt",false);
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

        for (int m = 0; m < (currentMinute )/tick; m ++){

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
                CamDrivePageObject.clickBackOnMinuteScreenIOS();
                continue;
            }
            //TODO : JSE в тесте
            Object attribute = CamDrivePageObject.getTimeDurationVideoForIOSArchive(); //допилить джаваскрипт
            System.out.println(attribute+" sec");
            testFile.write(attribute+" sec\n");
            CamDrivePageObject.clickBackOnMinuteScreenIOS();
        }
//Закрытие потока для фантика
        testFile.close();
//*Закрытие потока для фантика
    }

    @Test
    public void testCD320_AA78_MS5_IOS() throws InterruptedException, IOException {

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

        CamDrivePageObject.choiseCD320_AA78_MS5();
        CamDrivePageObject.choiseTheCurrentDay();
//Открытие потока для фантика
//======================================================Сделать красиво!
        System.out.println("\nData: "+currentYear+"/"+currentMonth+"/"+currentDay+"\nStart test in "+currentHour+" hour and "+currentMinute+" minutes ");
        FileWriter testFile = new FileWriter("TestRecordIOSCD320_AA78_MS5.txt",false);
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

        for (int m = 0; m < (currentMinute )/tick; m ++){

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
                CamDrivePageObject.clickBackOnMinuteScreenIOS();
                continue;
            }
            //TODO : JSE в тесте
            Object attribute = CamDrivePageObject.getTimeDurationVideoForIOSArchive(); //допилить джаваскрипт
            System.out.println(attribute+" sec");
            testFile.write(attribute+" sec\n");
            CamDrivePageObject.clickBackOnMinuteScreenIOS();
        }
//Закрытие потока для фантика
        testFile.close();
//*Закрытие потока для фантика
    }
    @Test
    public void testCD600_EF78_MS6_IOS() throws InterruptedException, IOException {

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

        CamDrivePageObject.choiseCD600_EF78_MS6_SERV();
        CamDrivePageObject.choiseTheCurrentDay();
//Открытие потока для фантика
//======================================================Сделать красиво!
        System.out.println("\nData: "+currentYear+"/"+currentMonth+"/"+currentDay+"\nStart test in "+currentHour+" hour and "+currentMinute+" minutes ");
        FileWriter testFile = new FileWriter("TestRecordIOSCD600_EF78_MS6_SERV.txt",false);
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

        for (int m = 0; m < (currentMinute )/tick; m ++){

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
                CamDrivePageObject.clickBackOnMinuteScreenIOS();
                continue;
            }
            //TODO : JSE в тесте
            Object attribute = CamDrivePageObject.getTimeDurationVideoForIOSArchive(); //допилить джаваскрипт
            System.out.println(attribute+" sec");
            testFile.write(attribute+" sec\n");
            CamDrivePageObject.clickBackOnMinuteScreenIOS();
        }
//Закрытие потока для фантика
        testFile.close();
//*Закрытие потока для фантика
    }

    @Test
    public void testCD630_910D_MS6_IOS() throws InterruptedException, IOException {

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

        CamDrivePageObject.choiseCD630_910D_MS6_DEV();
        CamDrivePageObject.choiseTheCurrentDay();
//Открытие потока для фантика
//======================================================Сделать красиво!
        System.out.println("\nData: "+currentYear+"/"+currentMonth+"/"+currentDay+"\nStart test in "+currentHour+" hour and "+currentMinute+" minutes ");
        FileWriter testFile = new FileWriter("TestRecordIOSCD630_910D_MS6_DEV.txt",false);
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

        for (int m = 0; m < (currentMinute )/tick; m ++){

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
                CamDrivePageObject.clickBackOnMinuteScreenCD630_910D();
                continue;
            }
            //TODO : JSE в тесте
            Object attribute = CamDrivePageObject.getTimeDurationVideoForIOSArchive(); //допилить джаваскрипт
            System.out.println(attribute+" sec");
            testFile.write(attribute+" sec\n");
            CamDrivePageObject.clickBackOnMinuteScreenCD630_910D();
        }
//Закрытие потока для фантика
        testFile.close();
//*Закрытие потока для фантика
    }

    @Test
    public void testN1001_3A00_bwd_IOS() throws IOException, InterruptedException {

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
        FileWriter testFile = new FileWriter("TestRecordIOSN1001_3A00_bwd.txt",false);
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

        for (int m = 0; m < (currentMinute )/tick; m ++){

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
                CamDrivePageObject.clickBackOnMinuteScreenIOS();
                continue;
            }
            //TODO : JSE в тесте
            Object attribute = CamDrivePageObject.getTimeDurationVideoForIOSArchive(); //допилить джаваскрипт
            System.out.println(attribute+" sec");
            testFile.write(attribute+" sec\n");
            CamDrivePageObject.clickBackOnMinuteScreenIOS();
        }
//Закрытие потока для фантика
        testFile.close();
//*Закрытие потока для фантика
    }



}