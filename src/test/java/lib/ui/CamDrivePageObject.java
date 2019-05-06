package lib.ui;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;


public class CamDrivePageObject extends MainPageObject{

    protected static String
            LOGIN,
            PASSWORD,
            ENTER_BUTTON,
            CD120_EAF9_SERV_MS5,
            DAY,
            ClOSE_BTN,
            BACK_BTN,
            HOUR_TEST,HOUR_0, HOUR_1, HOUR_2, HOUR_3, HOUR_4, HOUR_5, HOUR_6, HOUR_7, HOUR_8, HOUR_9, HOUR_10, HOUR_11, HOUR_12, HOUR_13, HOUR_14, HOUR_15, HOUR_16, HOUR_17, HOUR_18, HOUR_19, HOUR_20, HOUR_21, HOUR_22, HOUR_23,
            MINUTE_TEST,MINUTE_0, MINUTE_1, MINUTE_2, MINUTE_3, MINUTE_4, MINUTE_5, MINUTE_6, MINUTE_7, MINUTE_8, MINUTE_9, MINUTE_10, MINUTE_11;



    public CamDrivePageObject(RemoteWebDriver driver) {super(driver);}

    public void authorizationOnCamdrive(){
        waitForElementAndSendKeys(LOGIN,"Service","Not find the login field",15);
        waitForElementAndSendKeys(PASSWORD,"7ujm6yhn","Not find the password field", 15);
        waitForElementAndClick(ENTER_BUTTON,"Not find the Login button",15);
    }
    public void choiseCD120MS5(){
        waitForElementAndClick(CD120_EAF9_SERV_MS5,"Not find camera",15);
    }
    public void choiseTheCurrentDay(){
        waitForElementAndClick(DAY,"Current day not have the record",15);
    }
    public void checkLoadVideo(){ waitForElementPresent(ClOSE_BTN,"Not fund close button on Archive play video after 10 seconds",25); }
    public void clickCloseButtonOnPlayArchiveScreen(){ waitForElementAndClick(ClOSE_BTN,"Not fund close button on Archive play video after 10 seconds",25);}
    public void clickBackButtonOnArchiveScreen(){ waitForElementAndClick(BACK_BTN,"Not fund back button on white Archive screen after 10 seconds",15);}

    //======================================================================================================================
    public void clickHour(String currentDayCONVERTED, String currentMonthCONVERTED, String currentHourCONVERTED){
        String hour_element = getCurrentDayAndMonthAndHourForIDElements(
                currentDayCONVERTED,
                currentMonthCONVERTED,
                currentHourCONVERTED);
        //TODO Проверить нужно ли привязывать к веб элементу минуты
        this.waitForElementAndClick(hour_element,"Current hour not have records for this camera",30);
    }

    private String getCurrentDayAndMonthAndHourForIDElements(
            String currentDayCONVERTED,
            String currentMonthCONVERTED,
            String currentHourCONVERTED)
    {
        return HOUR_TEST
                .replace("{Day}", currentDayCONVERTED)
                .replace("{Month}", currentMonthCONVERTED)
                .replace("{Hour}",currentHourCONVERTED)
                .replace("{Day1}", currentDayCONVERTED)
                .replace("{Month1}", currentMonthCONVERTED)
                .replace("{Hour1}",currentHourCONVERTED);
    }
//======================================================================================================================
    public void clickMinute(
            String currentDayCONVERTED,
            String currentMonthCONVERTED,
            String currentHourCONVERTED,
            String minuteFirst,
            String minuteLast)
    {
        String minute_element = getCurrentDayAndMonthAndMinuteForIDElements(
                currentDayCONVERTED,
                currentMonthCONVERTED,
                currentHourCONVERTED,
                minuteFirst,
                minuteLast);
        //TODO Проверить нужно ли привязывать к веб элементу часы
        this.waitForElementAndClick(minute_element,"Current 5 minute not have records for this camera",10);
    }

    private String getCurrentDayAndMonthAndMinuteForIDElements(
            String currentDayCONVERTED,
            String currentMonthCONVERTED,
            String currentHourCONVERTED,
            String minuteFirst,
            String minuteLast)
    {
        return MINUTE_TEST
                .replace("{Day}", currentDayCONVERTED)
                .replace("{Month}", currentMonthCONVERTED)
                .replace("{Hour}",currentHourCONVERTED)
                .replace("{MinuteFirst}",minuteFirst)
                .replace("{MinuteLast}",minuteLast);
    }
//======================================================================================================================




}
