package lib.ui;

import org.openqa.selenium.JavascriptExecutor;
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
            DAY_TODAY,
            DAY,
            ClOSE_BTN,
            BACK_BTN_ON_MINUTE_SCREEN_IOS,
            BACK_BTN_ON_MINUTE_SCREEN_ANDROID,
            BACK_BTN_ON_HOUR_SCREEN,
            DYNAMIC_HOUR_XPATH,
            DYNAMIC_MINUTE_XPATH;



    public CamDrivePageObject(RemoteWebDriver driver) {super(driver);}

    public void authorizationOnCamdrive(){
        waitForElementAndSendKeys(LOGIN,"Service","Not find the login field",15);
        waitForElementAndSendKeys(PASSWORD,"7ujm6yhn","Not find the password field", 15);
        waitForElementAndClick(ENTER_BUTTON,"Not find the Login button",15);
    }
    public void choiseTheCurrentDay(){
        waitForElementAndClick(DAY_TODAY,"Current day not have the record",15);
    }
    //TODO: Проверка появления видео формы
    public void checkLoadVideoPlayerForAndroidMW(){  waitForElementPresent(ClOSE_BTN,"Not found close button on Archive play video after 25 seconds",25);}
    public void checkLoadVideoPlayerForIosMW(){  waitForElementNotPresent("xpath://video[contains(@class,'play play-loader')][contains(@id,'pl_va')]","Not found video on Archive play video after 25 seconds",25);}
    //public void checkLoadVideoPlayerForIosMW(){  waitForElementNotPresent("xpath://video[contains(@class,'video noPlayBtn')][contains(@id,'va')]","Not found video on Archive play video after 25 seconds",25);}

    //play-loader
    public void clickCloseButtonOnPlayArchiveScreen(){ waitForElementAndClick(ClOSE_BTN,"Not found CLOSE BUTTON on Archive play video after 10 seconds",15);}

    public void loadArchiveVideoAndroid(){
        waitForElementPresent("id:container_pl","Not found play button after 10 seconds wait",10);
        waitForElementAndClick("id:container_pl","Video is not load after 10 seconds wait",10);
    }

    public void loadArchiveVideoIOS() {
        waitForElementPresent("id:pl_va","Not found play button after 10 seconds wait",10);
        tryClickElementWithFewAttempts("id:pl_va","Video is not load after 10 seconds wait",30);
    }



    public void clickOnVideoForm(){
        waitForElementAndClick("xpath://div[@id='conteiner_vac']/video","Not found player form after 10 seconds wait",10);
    }
//TODO Тайм для веб андройда
    public String getTime(String attribute){
        attribute = this.waitForElementAndGetAtribute("xpath://*[contains(@class,'x-controlbar-Android-seek-loading-right')]","max","Time line is not visible",2);
        return attribute;
    }
//TODO Тайм для веб айоса

    //TODO Переделать данный метод , нужно разобраться с JavaScript callBack function и убрать НАФИГ ТРЕД СЛИП!!!!!
    public Object getTimeDurationVideoForIOSArchive() throws InterruptedException {
        JavascriptExecutor JSExecutor = (JavascriptExecutor)driver;
        Object js_result = null ;
        for(int i = 0; i <10; i++ ){
            if(js_result == null){
                Thread.sleep(1000);
                js_result = JSExecutor.executeScript("return document.getElementById('va').duration");
            }else{
                break;
            }
        }
        return js_result;


        //JSExecutor.executeAsyncScript("window.setTimeout(arguments[arguments.length - 1], 1500)");
        //        //Object js_result = JSExecutor.executeAsyncScript("return document.getElementById('va').duration");
//        JSExecutor.executeScript("function getDuration()" +
//                "    {" +
//                "        var vidos = document.getElementById('va');" +
//                "        if (vidos)" +
//                "        {" +
//                "            return vidos.duration ? vidos.duration : null;" +
//                "        }" +
//                "        return null;" +
//                "    }");

//        Object js_result = JSExecutor.executeScript("document.getElementById('va').oncanplay = function(){" +
//                "console.log(document.getElementById('va').duration);  " +
//                "return document.getElementById('va').duration}");

//        Object js_result = JSExecutor.executeScript("for(var i = 0; document.getElementById('va').duration == null || i < 10; i++){if(document.getElementById('va').duration == null){window.setTimeout(arguments[arguments.length - 1], 1500);}else{return document.getElementById('va').duration;}}");

    }
//
//    public void qwe() throws IOException {
//        FileWriter testFile = new FileWriter("TestRecordN1001_3A00_bwd.txt",false);
//        if (Platform.getInstance().isMWIos()){
//            System.out.println("Imitation web iPhone");
//            testFile.write("Imitation web iPhone");
//        }else if (Platform.getInstance().isMWAndroid()){
//            System.out.println("Imitation web Android");
//            testFile.write("Imitation web Android");
//        }else {System.out.println("Unknown WEB Platform");}
//        testFile.close();
//
//    }



    public String getTypeOfRecords(String attribute){
        attribute = this.waitForElementAndGetAtribute(DYNAMIC_MINUTE_XPATH,"max","Time line is not visible",2);
        return attribute;
    }

    //=======================================================================================================================================================
    public void choiseCD120MS5(){

        waitForElementAndClick(CD120_EAF9_SERV_MS5,"Not find camera",15);
    }

    public void clickBackOnMinuteScreenCD120MS5(){
        tryClickElementWithFewAttempts(BACK_BTN_ON_MINUTE_SCREEN_IOS,"Not find back button on minute screen for CD120_EAF9_SERV_MS5",10);
    }

    public void clickBackOnHourScreenCD120MS5(){
        tryClickElementWithFewAttempts(BACK_BTN_ON_HOUR_SCREEN,"Not find back button on hour screen for CD120_EAF9_SERV_MS5",10);
    }
    //************************************************************
    public void choiseCD100_E75A_MS3_DEV(){
        waitForElementAndClick(CD100_E75A_MS3_DEV,"Not find camera CD100_E75A_MS3_DEV",15);
    }
    public void clickBackOnMinuteScreenCD100_E75A(){
        waitForElementPresent(BACK_BTN_ON_MINUTE_SCREEN_IOS,"Not find back button on minute screen for CD120_EAF9_SERV_MS5",10);
        tryClickElementWithFewAttempts(BACK_BTN_ON_MINUTE_SCREEN_IOS,"Not find back button on minute screen for CD120_EAF9_SERV_MS5",50);
    }
    public void clickBackOnHourScreenCD100_E75A(){
        tryClickElementWithFewAttempts(BACK_BTN_ON_HOUR_SCREEN,"Not find back button on hour screen for CD120_EAF9_SERV_MS5",10);
    }
    //************************************************************
    public void choiseCD630_910D_MS6_DEV(){
        waitForElementAndClick(CD630_910D_MS6_DEV,"Not find camera CD630_910D_MS6_DEV",15);
    }
    public void clickBackOnMinuteScreenCD630_910D(){
        tryClickElementWithFewAttempts(BACK_BTN_ON_MINUTE_SCREEN_IOS,"Not find back button on minute screen for CD120_EAF9_SERV_MS5",10);
    }
    public void clickBackOnHourScreenCD630_910D(){
        tryClickElementWithFewAttempts(BACK_BTN_ON_HOUR_SCREEN,"Not find back button on hour screen for CD120_EAF9_SERV_MS5",10);
    }
    //************************************************************
    public void choiseCD320_AA06_MS3_DEV(){
        waitForElementAndClick(CD320_AA06_MS3_DEV,"Not find camera CD320_AA06_MS3_DEV",15);
    }
    public void clickBackOnMinuteScreenCD320_AA06(){
        tryClickElementWithFewAttempts(BACK_BTN_ON_MINUTE_SCREEN_IOS,"Not find back button on minute screen for CD120_EAF9_SERV_MS5",10);
    }
    public void clickBackOnHourScreenCD320_AA06(){
        tryClickElementWithFewAttempts(BACK_BTN_ON_HOUR_SCREEN,"Not find back button on hour screen for CD120_EAF9_SERV_MS5",10);
    }
    //************************************************************
    public void choiseCD100_E778_MS5(){
        waitForElementAndClick(CD100_E778_MS5,"Not find camera CD100_E778_MS5",15);
    }
    public void clickBackOnMinuteScreenCD100_E778(){
        tryClickElementWithFewAttempts(BACK_BTN_ON_MINUTE_SCREEN_IOS,"Not find back button on minute screen for CD120_EAF9_SERV_MS5",10);
    }
    public void clickBackOnHourScreenCD100_E778(){
        tryClickElementWithFewAttempts(BACK_BTN_ON_HOUR_SCREEN,"Not find back button on hour screen for CD120_EAF9_SERV_MS5",10);
    }
    //************************************************************
    public void choiseCD600_EF78_MS6_SERV(){
        waitForElementAndClick(CD600_EF78_MS6_SERV,"Not find camera CD600_EF78_MS6_SERV",15);
    }
    public void clickBackOnMinuteScreenCD600_EF78(){
        tryClickElementWithFewAttempts(BACK_BTN_ON_MINUTE_SCREEN_IOS,"Not find back button on minute screen for CD120_EAF9_SERV_MS5",10);
    }
    public void clickBackOnHourScreenCD600_EF78(){
        tryClickElementWithFewAttempts(BACK_BTN_ON_HOUR_SCREEN,"Not find back button on hour screen for CD120_EAF9_SERV_MS5",10);
    }
    //************************************************************
    public void choiseCD320_AA78_MS5(){
        waitForElementAndClick(CD320_AA78_MS5,"Not find camera CD320_AA78_MS5",15);
    }
    public void clickBackOnMinuteScreenCD320_AA78(){
        tryClickElementWithFewAttempts(BACK_BTN_ON_MINUTE_SCREEN_IOS,"Not find back button on minute screen for CD120_EAF9_SERV_MS5",10);
    }
    public void clickBackOnHourScreenCD320_AA78(){
        tryClickElementWithFewAttempts(BACK_BTN_ON_HOUR_SCREEN,"Not find back button on hour screen for CD120_EAF9_SERV_MS5",10);
    }
    //************************************************************
    public void choiseCD310_2E51_MS4_DEV(){
        waitForElementAndClick(CD310_2E51_MS4_DEV,"Not find camera CD310_2E51_MS4_DEV",15);
    }
    public void clickBackOnMinuteScreenCD310_2E51(){
        tryClickElementWithFewAttempts(BACK_BTN_ON_MINUTE_SCREEN_IOS,"Not find back button on minute screen for CD120_EAF9_SERV_MS5",10);
    }
    public void clickBackOnHourScreenCD310_2E51(){
        tryClickElementWithFewAttempts(BACK_BTN_ON_HOUR_SCREEN,"Not find back button on hour screen for CD120_EAF9_SERV_MS5",10);
    }
    //************************************************************
    public void choiseCD100_E772_MS4(){
        waitForElementAndClick(CD100_E772_MS4,"Not find camera CD100_E772_MS4",15);
    }
    public void clickBackOnMinuteScreenCD100_E772(){
        tryClickElementWithFewAttempts(BACK_BTN_ON_MINUTE_SCREEN_IOS,"Not find back button on minute screen for CD120_EAF9_SERV_MS5",10);
    }
    public void clickBackOnHourScreenCD100_E772(){
        tryClickElementWithFewAttempts(BACK_BTN_ON_HOUR_SCREEN,"Not find back button on hour screen for CD120_EAF9_SERV_MS5",10);
    }
    //************************************************************
    public void choiseN1001_3A00_bwd(){
        waitForElementAndClick(N1001_3A00_bwd,"Not find camera N1001_3A00_bwd_IOS",15);
    }
    public void clickBackOnMinuteScreenIOS(){
        waitForElementPresent(BACK_BTN_ON_MINUTE_SCREEN_IOS,"Not find back button on minute screen for CD120_EAF9_SERV_MS5",15);
        tryClickElementWithFewAttempts(BACK_BTN_ON_MINUTE_SCREEN_IOS,"Not find back button on minute screen for CD120_EAF9_SERV_MS5",10);
    }

    public void clickBackOnMinuteScreenAndroid() throws InterruptedException {
        Thread.sleep(2000); //TODO:: Исправить к хуям , на имплисити вэйт или что то еще ... сервак от нагрузки начинает тупить
        waitForElementPresent(BACK_BTN_ON_MINUTE_SCREEN_ANDROID,"Not find back button on minute screen for CD120_EAF9_SERV_MS5",15);
        tryClickElementWithFewAttempts(BACK_BTN_ON_MINUTE_SCREEN_ANDROID,"Not find back button on minute screen for CD120_EAF9_SERV_MS5",10);
    }


    public void clickBackOnHourScreenN1001_3A00(){
        tryClickElementWithFewAttempts(BACK_BTN_ON_HOUR_SCREEN,"Not find back button on hour screen for CD120_EAF9_SERV_MS5",10);
    }
    //************************************************************

    //======================================================================================================================
    public void clickHour(String currentDayCONVERTED, String currentMonthCONVERTED, String currentHourCONVERTED){
        String hour_element = getCurrentDayAndMonthAndHourForIDElements(
                currentDayCONVERTED,
                currentMonthCONVERTED,
                currentHourCONVERTED);
        //TODO Проверить нужно ли привязывать к веб элементу минуты
        this.waitForElementAndClick(hour_element,"Current hour not have records for this camera",10);
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
public void choiseDay(
        String currentDayCONVERTED,
        String currentMonthCONVERTED)
{
    String day_element = getCurrentDayAndMonth(
            currentDayCONVERTED,
            currentMonthCONVERTED);
    //TODO Проверить нужно ли привязывать к веб элементу часы
    this.waitForElementAndClick(day_element,"Current 5 minute not have records for this camera",10);
}

    private String getCurrentDayAndMonth(
            String Day,
            String Month)
    {
        return DAY
                .replace("{Day}", Day)
                .replace("{Month}", Month);
    }
//======================================================================================================================
}
