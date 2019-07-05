package lib;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class Platform {

    private static final String PLATFORM_IOS = "ios";
    private static final String PLATFORM_ANDROID = "android";
    private static final String PLATFORM_MOBILE_WEB_ANDROID = "mobile_web_android";
    private static final String PLATFORM_MOBILE_WEB_IOS = "mobile_web_ios";
    private static final String PLATFORM_TEST = "mobile_web_test";

    private static final String APPIUM_URL = "http://0.0.0.0:4723/wd/hub";

    private static Platform instance;

    private Platform(){} // приватный конструктор

    public static Platform getInstance()
    {
        if (instance == null) {
            instance = new Platform();
        }
        return instance;

    }

    public RemoteWebDriver getDriver() throws Exception // метод который отвечает за выбраный драйвер
    {
        URL URL = new URL(APPIUM_URL);
        if(this.isAndroid()) {
            return new AndroidDriver(URL,this.getAndroidDesiredcCapabilities());
        } else if(this.isIOS()){
            return new IOSDriver(URL,this.getIOSDesiredcCapabilities());
        } else if(this.isMWAndroid()){
            return new ChromeDriver(this.getMWChromeOptionsForAndroid());
        } else if(this.isMWIos()){
            return new ChromeDriver(this.getMWChromeOptionsForIOS());
        } else if(this.isMWTest()) {
            return new ChromeDriver(this.getMWChromeOptionsForAndroid());
        } else{
            throw new Exception("Cannot detect type of the Driver. Platform value:" + this.getPlatformVar());
        }
    }


    public boolean isAndroid() // определяет является ли платформа Android
    {
        return isPlatform(PLATFORM_ANDROID);
    }

    public boolean isIOS()  // определяет является ли платформа IOS
    {
        return isPlatform(PLATFORM_IOS);
    }

    public boolean isMWAndroid()  // определяет является ли платформа WEB_Android
    {
        return isPlatform(PLATFORM_MOBILE_WEB_ANDROID);
    }
    public boolean isMWIos()  // определяет является ли платформа WEB_Android
    {
        return isPlatform(PLATFORM_MOBILE_WEB_IOS);
    }
    public boolean isMWTest(){
        return isPlatform(PLATFORM_TEST);
    }



    private DesiredCapabilities getAndroidDesiredcCapabilities()  // capabilities для Android
    {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("platformName","Android");
        capabilities.setCapability("deviceName","LG X power");
        capabilities.setCapability("platformVersion","6.0.1");
        capabilities.setCapability("automationName","Appium");
//        capabilities.setCapability("browserName","Chrome");
        capabilities.setCapability("unicodeKeyboard", true);
        capabilities.setCapability("resetKeyboard", true);
        capabilities.setCapability("connectHardwareKeyboard", false);
        capabilities.setCapability("app","http://intercom.mobile.test/camdrive/camdrive_2019-05-27_1.2.0.505_release.apk");
        capabilities.setCapability("appPackage","com.camdrive");
        capabilities.setCapability("appActivity","presentation.gui.authorization.StartActivity");


        //для апиум сервера 10
        //capabilities.setCapability("automationName", "Appium");
        //Капа для апиум сервера 12.1
        //capabilities.setCapability("automationName", AppiumDriverLocalService);

        return capabilities;
    }

    private DesiredCapabilities getIOSDesiredcCapabilities() // capabilities для IOS
    {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("platformName","iOS");
        capabilities.setCapability("deviceName","iPhone SE");
        capabilities.setCapability("platformVersion","11.3");
        capabilities.setCapability("app","/Users/testers/Desktop/JavaAppiumAutomation/apks/wikipedia.app");
        return capabilities;
    }

    private ChromeOptions getMWChromeOptionsForAndroid() // запускает браузер Chrome
    {
//        Map<String, Object> deviceMetrics = new HashMap<String, Object>();
//        deviceMetrics.put("width", 360); //высота девайса
//        deviceMetrics.put("height", 640); //ширина девайса
//        deviceMetrics.put("pixelRation", 3.0); //плотность пикселей девайса
//        Map<String, Object> mobileEmulation = new HashMap<String, Object>(); // параметры юзер агента
//        mobileEmulation.put("deviceMetrics", deviceMetrics);
//        mobileEmulation.put("userAgent", "Mozilla/5.0 (Linux; Android 4.2.1; en-us; Nexus 5 Build/JOP40D) AppleWebKit/535.19 (KHTML, like Gecko) Chrome/18.0.1025.166 Mobile Safari/535.19");
//
//        ChromeOptions chromeOptions = new ChromeOptions();
//        chromeOptions.addArguments("window-size=340,640");
//============================================================
        Map<String, String> mobileEmulation = new HashMap<>();

        mobileEmulation.put("deviceName", "Nexus 5");//iPhone X
        //mobileEmulation.put("deviceName", "iPhone X");


        ChromeOptions chromeOptionsForAndroid = new ChromeOptions();
        chromeOptionsForAndroid.setExperimentalOption("mobileEmulation", mobileEmulation);
        //WebDriver driver = new ChromeDriver(chromeOptions);
//============================================================
        return chromeOptionsForAndroid;
    }
    private ChromeOptions getMWChromeOptionsForIOS() // запускает браузер Chrome
    {
        Map<String, String> mobileEmulation = new HashMap<>();

        mobileEmulation.put("deviceName", "iPhone X"); //"iPhone X" изменить интерфейс видеопроигрывателя

        ChromeOptions chromeOptionsForIOS = new ChromeOptions();
        chromeOptionsForIOS.setExperimentalOption("mobileEmulation", mobileEmulation);
//============================================================
        return chromeOptionsForIOS;
    }

    private boolean isPlatform(String my_platform)// сравнивание с переменной которая приходит на вход
    {
        String platform = this.getPlatformVar();
        return my_platform.equals(platform);
    }

    public String getPlatformVar() // получение переменной окружения
    {
        return System.getenv("PLATFORM");
    }


}
