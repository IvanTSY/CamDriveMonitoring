package lib.ui;

import org.openqa.selenium.remote.RemoteWebDriver;


public class CamDrivePageObject extends MainPageObject{

    protected static String
            LOGIN,
            PASSWORD,
            ENTER_BUTTON,
            CD120_EAF9_SERV_MS5,
            CD100_E75A_MS3_DEV,
            CD630_910D_MS6_DEV,
            CD320_AA06_MS3_DEV,
            CD100_E778_MS5,
            CD600_EF78_MS6_SERV,
            CD320_AA78_MS5,
            CD310_2E51_MS4_DEV,
            CD100_E772_MS4,
            N1001_3A00_bwd,
            BACK_BTN_ON_MINUTE_SCREEN_CD100_E75A,
            BACK_BTN_ON_HOUR_SCREEN_CD100_E75A,
            BACK_BTN_ON_MINUTE_SCREEN_CD630_910D,
            BACK_BTN_ON_HOUR_SCREEN_CD630_910D,
            BACK_BTN_ON_MINUTE_SCREEN_CD320_AA06,
            BACK_BTN_ON_HOUR_SCREEN_CD320_AA06,
            BACK_BTN_ON_MINUTE_SCREEN_CD100_E778,
            BACK_BTN_ON_HOUR_SCREEN_CD100_E778,
            BACK_BTN_ON_MINUTE_SCREEN_CD600_EF78,
            BACK_BTN_ON_HOUR_SCREEN_CD600_EF78,
            BACK_BTN_ON_MINUTE_SCREEN_CD320_AA78,
            BACK_BTN_ON_HOUR_SCREEN_CD320_AA78,
            BACK_BTN_ON_MINUTE_SCREEN_CD310_2E51,
            BACK_BTN_ON_HOUR_SCREEN_CD310_2E51,
            BACK_BTN_ON_MINUTE_SCREEN_CD100_E772,
            BACK_BTN_ON_HOUR_SCREEN_CD100_E772,
            BACK_BTN_ON_MINUTE_SCREEN_N1001_3A00,
            BACK_BTN_ON_HOUR_SCREEN_N1001_3A00,
            DAY,
            ClOSE_BTN,
            BACK_BTN_ON_MINUTE_SCREEN_CD120,
            BACK_BTN_ON_HOUR_SCREEN_CD120,
            DYNAMIC_HOUR_XPATH,
            DYNAMIC_MINUTE_XPATH;



    public CamDrivePageObject(RemoteWebDriver driver) {super(driver);}

    public void authorizationOnCamdrive(){
        waitForElementAndSendKeys(LOGIN,"Service","Not find the login field",15);
        waitForElementAndSendKeys(PASSWORD,"7ujm6yhn","Not find the password field", 15);
        waitForElementAndClick(ENTER_BUTTON,"Not find the Login button",15);
    }
    public void choiseTheCurrentDay(){
        waitForElementAndClick(DAY,"Current day not have the record",15);
    }
    //TODO: Проверка появления видео формы
    public void checkLoadVideoPlayer(){  waitForElementPresent(ClOSE_BTN,"Not found close button on Archive play video after 10 seconds",25);}
    public void clickCloseButtonOnPlayArchiveScreen(){ waitForElementAndClick(ClOSE_BTN,"Not found CLOSE BUTTON on Archive play video after 10 seconds",15);}

    public void loadArchiveVideo(){
        waitForElementPresent("id:container_pl","Not found play button after 10 seconds wait",10);
        waitForElementAndClick("id:container_pl","Video is not load after 10 seconds wait",10);
    }

    public void clickOnVideoForm(){
        waitForElementAndClick("xpath://div[@id='conteiner_vac']/video","Not found player form after 10 seconds wait",10);
    }

    public String getTime(String attribute){
        attribute = this.waitForElementAndGetAtribute("xpath://*[contains(@class,'x-controlbar-Android-seek-loading-right')]","max","Time line is not visible",2);
        return attribute;
    }
//************************************************************
    public void choiseCD120MS5(){

        waitForElementAndClick(CD120_EAF9_SERV_MS5,"Not find camera",15);
    }

    public void clickBackOnMinuteScreenCD120MS5(){
        tryClickElementWithFewAttempts(BACK_BTN_ON_MINUTE_SCREEN_CD120,"Not find back button on minute screen for CD120_EAF9_SERV_MS5",10);
    }

    public void clickBackOnHourScreenCD120MS5(){
        tryClickElementWithFewAttempts(BACK_BTN_ON_HOUR_SCREEN_CD120,"Not find back button on hour screen for CD120_EAF9_SERV_MS5",10);

    }
    //************************************************************
    public void choiseCD100_E75A_MS3_DEV(){
        waitForElementAndClick(CD100_E75A_MS3_DEV,"Not find camera CD100_E75A_MS3_DEV",15);
    }
    public void clickBackOnMinuteScreenCD100_E75A(){
        tryClickElementWithFewAttempts(BACK_BTN_ON_MINUTE_SCREEN_CD100_E75A,"Not find back button on minute screen for CD120_EAF9_SERV_MS5",10);
    }

    public void clickBackOnHourScreenCD100_E75A(){
        tryClickElementWithFewAttempts(BACK_BTN_ON_HOUR_SCREEN_CD100_E75A,"Not find back button on hour screen for CD120_EAF9_SERV_MS5",10);

    }
    //************************************************************
    public void choiseCD630_910D_MS6_DEV(){
        waitForElementAndClick(CD630_910D_MS6_DEV,"Not find camera CD630_910D_MS6_DEV",15);
    }
    public void clickBackOnMinuteScreenCD630_910D(){
        tryClickElementWithFewAttempts(BACK_BTN_ON_MINUTE_SCREEN_CD630_910D,"Not find back button on minute screen for CD120_EAF9_SERV_MS5",10);
    }

    public void clickBackOnHourScreenCD630_910D(){
        tryClickElementWithFewAttempts(BACK_BTN_ON_HOUR_SCREEN_CD630_910D,"Not find back button on hour screen for CD120_EAF9_SERV_MS5",10);

    }
    //************************************************************
    public void choiseCD320_AA06_MS3_DEV(){
        waitForElementAndClick(CD320_AA06_MS3_DEV,"Not find camera CD320_AA06_MS3_DEV",15);
    }
    public void clickBackOnMinuteScreenCD320_AA06(){
        tryClickElementWithFewAttempts(BACK_BTN_ON_MINUTE_SCREEN_CD320_AA06,"Not find back button on minute screen for CD120_EAF9_SERV_MS5",10);
    }

    public void clickBackOnHourScreenCD320_AA06(){
        tryClickElementWithFewAttempts(BACK_BTN_ON_HOUR_SCREEN_CD320_AA06,"Not find back button on hour screen for CD120_EAF9_SERV_MS5",10);

    }
    //************************************************************
    public void choiseCD100_E778_MS5(){

        waitForElementAndClick(CD100_E778_MS5,"Not find camera CD100_E778_MS5",15);
    }
    public void clickBackOnMinuteScreenCD100_E778(){
        tryClickElementWithFewAttempts(BACK_BTN_ON_MINUTE_SCREEN_CD100_E778,"Not find back button on minute screen for CD120_EAF9_SERV_MS5",10);
    }

    public void clickBackOnHourScreenCD100_E778(){
        tryClickElementWithFewAttempts(BACK_BTN_ON_HOUR_SCREEN_CD100_E778,"Not find back button on hour screen for CD120_EAF9_SERV_MS5",10);

    }
    //************************************************************
    public void choiseCD600_EF78_MS6_SERV(){
        waitForElementAndClick(CD600_EF78_MS6_SERV,"Not find camera CD600_EF78_MS6_SERV",15);
    }
    public void clickBackOnMinuteScreenCD600_EF78(){
        tryClickElementWithFewAttempts(BACK_BTN_ON_MINUTE_SCREEN_CD600_EF78,"Not find back button on minute screen for CD120_EAF9_SERV_MS5",10);
    }

    public void clickBackOnHourScreenCD600_EF78(){
        tryClickElementWithFewAttempts(BACK_BTN_ON_HOUR_SCREEN_CD600_EF78,"Not find back button on hour screen for CD120_EAF9_SERV_MS5",10);

    }
    //************************************************************
    public void choiseCD320_AA78_MS5(){

        waitForElementAndClick(CD320_AA78_MS5,"Not find camera CD320_AA78_MS5",15);
    }
    public void clickBackOnMinuteScreenCD320_AA78(){
        tryClickElementWithFewAttempts(BACK_BTN_ON_MINUTE_SCREEN_CD320_AA78,"Not find back button on minute screen for CD120_EAF9_SERV_MS5",10);
    }

    public void clickBackOnHourScreenCD320_AA78(){
        tryClickElementWithFewAttempts(BACK_BTN_ON_HOUR_SCREEN_CD320_AA78,"Not find back button on hour screen for CD120_EAF9_SERV_MS5",10);

    }
    //************************************************************
    public void choiseCD310_2E51_MS4_DEV(){
        waitForElementAndClick(CD310_2E51_MS4_DEV,"Not find camera CD310_2E51_MS4_DEV",15);
    }
    public void clickBackOnMinuteScreenCD310_2E51(){
        tryClickElementWithFewAttempts(BACK_BTN_ON_MINUTE_SCREEN_CD310_2E51,"Not find back button on minute screen for CD120_EAF9_SERV_MS5",10);
    }

    public void clickBackOnHourScreenCD310_2E51(){
        tryClickElementWithFewAttempts(BACK_BTN_ON_HOUR_SCREEN_CD310_2E51,"Not find back button on hour screen for CD120_EAF9_SERV_MS5",10);

    }
    //************************************************************
    public void choiseCD100_E772_MS4(){

        waitForElementAndClick(CD100_E772_MS4,"Not find camera CD100_E772_MS4",15);
    }
    public void clickBackOnMinuteScreenCD100_E772(){
        tryClickElementWithFewAttempts(BACK_BTN_ON_MINUTE_SCREEN_CD100_E772,"Not find back button on minute screen for CD120_EAF9_SERV_MS5",10);
    }

    public void clickBackOnHourScreenCD100_E772(){
        tryClickElementWithFewAttempts(BACK_BTN_ON_HOUR_SCREEN_CD100_E772,"Not find back button on hour screen for CD120_EAF9_SERV_MS5",10);

    }

    //************************************************************
    public void choiseN1001_3A00_bwd(){

        waitForElementAndClick(N1001_3A00_bwd,"Not find camera N1001_3A00_bwd",15);
    }
    public void clickBackOnMinuteScreenN1001_3A00(){
        tryClickElementWithFewAttempts(BACK_BTN_ON_MINUTE_SCREEN_N1001_3A00,"Not find back button on minute screen for CD120_EAF9_SERV_MS5",10);
    }

    public void clickBackOnHourScreenN1001_3A00(){
        tryClickElementWithFewAttempts(BACK_BTN_ON_HOUR_SCREEN_N1001_3A00,"Not find back button on hour screen for CD120_EAF9_SERV_MS5",10);

    }
    //************************************************************

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
        return DYNAMIC_HOUR_XPATH
                .replace("{Day}", currentDayCONVERTED)
                .replace("{Month}", currentMonthCONVERTED)
                .replace("{Hour}",currentHourCONVERTED);
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
        return DYNAMIC_MINUTE_XPATH
                .replace("{Day}", currentDayCONVERTED)
                .replace("{Month}", currentMonthCONVERTED)
                .replace("{Hour}",currentHourCONVERTED)
                .replace("{MinuteFirst}",minuteFirst)
                .replace("{MinuteLast}",minuteLast);
    }
//======================================================================================================================




}
