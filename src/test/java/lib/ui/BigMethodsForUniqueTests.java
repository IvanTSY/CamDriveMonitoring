package lib.ui;

import lib.CoreTestCase;
import lib.Platform;
import lib.apiSlack.SlackSendMessaages;
import org.apache.commons.lang3.text.WordUtils;

import java.text.SimpleDateFormat;
import java.time.Month;
import java.util.Calendar;
import java.util.Date;

public class BigMethodsForUniqueTests extends CoreTestCase {

    private String currentFirstMinuteCONVERTED;
    private String currentLastMinuteCONVERTED;
    private String currentHourCONVERTED;
    private String currentDayCONVERTED;
    private String currentMonthCONVERTED;
    private String textPlatform;
    private int currentYear = Calendar.getInstance().getWeekYear(); //текущий год
    private int currentHour = Calendar.getInstance().getTime().getHours() - 12; //-12часов
    private int currentDay = Calendar.getInstance().getTime().getDate(); //текущий календарный день
    private int currentMonth = Calendar.getInstance().getTime().getMonth() + 1; //текущий календарный месяц


    private String dateOfTestRun = WordUtils.capitalize("\""+new SimpleDateFormat("dd MMMM").format(new Date())+"\"");
    public void coreTestForMonitoring(CamDrivePageObject CamDrivePageObject, String errorLogFile, String cameraName, String channelID, int tick) throws Exception {

        //SlackSendMessaages ms = new SlackSendMessaages();


        if(Platform.getInstance().isMWIos()){
            textPlatform = "WEB IOS: ";
        }else if(Platform.getInstance().isMWAndroid()){
            textPlatform = "WEB Android: ";
        }

//        if (currentHour <10){
//            currentHourCONVERTED = "0"+currentHour;
//        }else currentHourCONVERTED = Integer.toString(currentHour);
//        //==========================================================
//        if (currentMonth <10){
//            currentMonthCONVERTED = "0"+currentMonth;
//        }else currentMonthCONVERTED = Integer.toString(currentMonth);


        //==========================================================
//обработка ситуации с переходом между днями (Вчера / Сегодня) Нужно завернуть в метод и гонять в цикле FOR
        //Проверка перехода на -12 часов
        if(currentHour <0){
            int colibrate = 24 + currentHour;

            currentHour = colibrate; // час предыдущего дня (Если был переход)
            //корректируем час в xpath
            if (currentHour <10){
                currentHourCONVERTED = "0"+currentHour;
            }else currentHourCONVERTED = Integer.toString(currentHour);
            //переходим на предыдущий день
            currentDay = currentDay -1;
            //Переход на предыдущий месяц если день не вхдит в текущий
            if (currentDay==0){
                //делаем переход на день предыдущего месяца
                currentDay = Month.of(currentMonth - 1).maxLength();
                currentMonth = currentMonth -1;
            }
////////////////////////////////////////////////////////////////////////////////////
            currentDayCONVERTED = Integer.toString(currentDay);
            if (currentHour <10){
                currentHourCONVERTED = "0"+currentHour;
            }else currentHourCONVERTED = Integer.toString(currentHour);
            //==========================================================
            if (currentMonth <10){
                currentMonthCONVERTED = "0"+currentMonth;
            }else currentMonthCONVERTED = Integer.toString(currentMonth);
////////////////////////////////////////////////////////////////////////////////////
            //CamDrivePageObject.choiseDay(currentDayCONVERTED,currentMonthCONVERTED);
            CamDrivePageObject.choiseTheCurrentDay(
                    errorLogFile,
                    cameraName,
                    currentYear,
                    currentMonth,
                    currentDay,
                    currentHour);

        }else if (currentHour>23){
            currentHour = 0;
            currentDayCONVERTED = Integer.toString(currentDay);
            if (currentHour <10){
                currentHourCONVERTED = "0"+currentHour;
            }else currentHourCONVERTED = Integer.toString(currentHour);
            //==========================================================
            if (currentMonth <10){
                currentMonthCONVERTED = "0"+currentMonth;
            }else currentMonthCONVERTED = Integer.toString(currentMonth);

            CamDrivePageObject.choiseTheCurrentDay(
                    errorLogFile,
                    cameraName,
                    currentYear,
                    currentMonth,
                    currentDay,
                    currentHour);
        }
        int schedule = CamDrivePageObject.returnCurrentScheldueStatus(currentHour,channelID);

        if(Platform.getInstance().isMWAndroid()){
            if ((schedule == 1)||(schedule == 3)){
                try{
                    CamDrivePageObject.clickHour(
                            currentDayCONVERTED,
                            currentMonthCONVERTED,
                            currentHourCONVERTED);

                }catch (RuntimeException e){
                    System.out.println("Current hour: "+currentHour+":00 not have records for this camera\n"+"Тип расписания -" + schedule +"\n" );
                }
            }else{
                try{
                    //Проверка Постоянная запись
                    CamDrivePageObject.clickHour(
                            currentDayCONVERTED,
                            currentMonthCONVERTED,
                            currentHourCONVERTED);

                }catch (RuntimeException e){
//                    errorFile.write("Current hour "+currentHour+":00 not have records!");
                    CamDrivePageObject.exitTest();
                }
            }
        }


        int currentFirstMinute = -tick;
        int currentLastMinute = -1;
        int msg = 0;
        int countContaineer = 0;
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
            if(Platform.getInstance().isMWIos()){
                if (currentHour >11){
                    CamDrivePageObject.scrollIntoView();
                }
            }

//-----------------------------------------------------------------------------------------------------------
            if(countContaineer < 2){
                try{
                    CamDrivePageObject.clickMinute(
                            currentDayCONVERTED,
                            currentMonthCONVERTED,
                            currentHourCONVERTED,
                            currentFirstMinuteCONVERTED,
                            currentLastMinuteCONVERTED);
                }catch (RuntimeException e){

                    //                0 постоянно
                    //                1 детекция
                    //                3 кнопка вызова
                    //                2 без записи

                    if((schedule == 1)||(schedule == 3)) {
                      //  ms.sendMSG(textPlatform + dateOfTestRun+" WARNING: Проверка воспроизведения архива для камеры "+cameraName+" - не выполнена.");
                        // Архива " + currentHourCONVERTED + ":" + currentFirstMinuteCONVERTED + "-"+ currentHourCONVERTED +":"+ currentLastMinuteCONVERTED +" нет по расписанию.\n
                        System.out.println("No records. Block " + currentHourCONVERTED + ":00h. " + currentFirstMinuteCONVERTED + "min-" + currentLastMinuteCONVERTED + "min\n");
                        countContaineer++;
                    }else{
                      //  ms.sendMSG(textPlatform + dateOfTestRun+" WARNING: Проверка воспроизведения архива для камеры "+cameraName+" - не выполнена.");
                        countContaineer++;
                        System.out.println("No records. Block " + currentHourCONVERTED + ":00h. " + currentFirstMinuteCONVERTED + "min-" + currentLastMinuteCONVERTED + "min\n");
                    }

                }

                //-----------------------------------------------------------------------------------------------------------------------

                if(Platform.getInstance().isMWIos()){
                    CamDrivePageObject.loadArchiveVideoIOS(); //добавить трайклик
                }else{
                    CamDrivePageObject.loadArchiveVideoAndroid();
                }
                try {

                    if(Platform.getInstance().isMWIos()){
                        CamDrivePageObject.checkLoadVideoPlayerForIosMW();
                    }else{
                        CamDrivePageObject.checkLoadVideoPlayerForAndroidMW();
                    }
                    if(msg < 2){
                      //  ms.sendMSG(textPlatform + dateOfTestRun+" INFO: Проверка воспроизведения архива для камеры "+cameraName+" - выполнена.");
                        msg++;
                        countContaineer++;
                    }

                }catch (RuntimeException e){
                    if(msg < 2){
                       // ms.sendMSG(textPlatform + dateOfTestRun+" WARNING: Проверка воспроизведения архива для камеры "+cameraName+" - не выполнена.");                            msg++;
                        countContaineer++;
                    }

                    if(Platform.getInstance().isMWIos()){
                        CamDrivePageObject.clickBackOnMinuteScreenIOS();
                    }else{
                        CamDrivePageObject.clickCloseButtonOnPlayArchiveScreen();
                        CamDrivePageObject.clickBackOnMinuteScreenAndroid();
                    }

                }
                if(Platform.getInstance().isMWIos()){
                    CamDrivePageObject.clickBackOnMinuteScreenIOS();
                }else{
                    CamDrivePageObject.clickCloseButtonOnPlayArchiveScreen();
                    CamDrivePageObject.clickBackOnMinuteScreenAndroid();
                }
            }
//-----------------------------------------------------------------------------------------------------------------------
            //CamDrivePageObject.clickBackOnMinuteScreenIOS();
        }
    }
}


//ms.sendMSG("IOS WEB: "+ dateOfTestRun+" Проверка архива камеры "+cameraName+" провалена.");