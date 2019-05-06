//package lib.ui.mobile_web;
//
//import lib.ui.CamDrivePageObject;
//import org.openqa.selenium.remote.RemoteWebDriver;
//
//public class MWCamDriveTime extends CamDrivePageObject {
//
//    static {
//        //TODO: Часы и минуты нужно сделать динамическими!!! брать час и день из системы
//        HOUR_TEST = "id:2019-{Month}-{Day}-{Hour}-00-00_2019-{Month}-{Day}-{Hour}-59-59";
//        HOUR_0 = "id:2019-{Month}-{Day}-00-00-00_2019-{Month}-{Day}-00-59-59";
//        HOUR_1 = "id:2019-{Month}-{Day}-01-00-00_2019-{Month}-{Day}-01-59-59";
//        HOUR_2 = "id:2019-{Month}-{Day}-02-00-00_2019-{Month}-{Day}-02-59-59";
//        HOUR_3 = "id:2019-{Month}-{Day}-03-00-00_2019-{Month}-{Day}-03-59-59";
//        HOUR_4 = "id:2019-{Month}-{Day}-04-00-00_2019-{Month}-{Day}-04-59-59";
//        HOUR_5 = "id:2019-{Month}-{Day}-05-00-00_2019-{Month}-{Day}-05-59-59";
//        HOUR_6 = "id:2019-{Month}-{Day}-06-00-00_2019-{Month}-{Day}-06-59-59";
//        HOUR_7 = "id:2019-{Month}-{Day}-07-00-00_2019-{Month}-{Day}-07-59-59";
//        HOUR_8 = "id:2019-{Month}-{Day}-08-00-00_2019-{Month}-{Day}-08-59-59";
//        HOUR_9 = "id:2019-{Month}-{Day}-09-00-00_2019-{Month}-{Day}-09-59-59";
//        HOUR_10 = "id:2019-{Month}-{Day}-10-00-00_2019-{Month}-{Day}-10-59-59";
//        HOUR_11 = "id:2019-{Month}-{Day}-11-00-00_2019-{Month}-{Day}-11-59-59";
//        HOUR_12 = "id:2019-{Month}-{Day}-12-00-00_2019-{Month}-{Day}-12-59-59";
//        HOUR_13 = "id:2019-{Month}-{Day}-13-00-00_2019-{Month}-{Day}-13-59-59";
//        HOUR_14 = "id:2019-{Month}-{Day}-14-00-00_2019-{Month}-{Day}-14-59-59";
//        HOUR_15 = "id:2019-{Month}-{Day}-15-00-00_2019-{Month}-{Day}-15-59-59";
//        HOUR_16 = "id:2019-{Month}-{Day}-16-00-00_2019-{Month}-{Day}-16-59-59";
//        HOUR_17 = "id:2019-{Month}-{Day}-17-00-00_2019-{Month}-{Day}-17-59-59";
//        HOUR_18 = "id:2019-{Month}-{Day}-18-00-00_2019-{Month}-{Day}-18-59-59";
//        HOUR_19 = "id:2019-{Month}-{Day}-19-00-00_2019-{Month}-{Day}-19-59-59";
//        HOUR_20 = "id:2019-{Month}-{Day}-20-00-00_2019-{Month}-{Day}-20-59-59";
//        HOUR_21 = "id:2019-{Month}-{Day}-21-00-00_2019-{Month}-{Day}-21-59-59";
//        HOUR_22 = "id:2019-{Month}-{Day}-22-00-00_2019-{Month}-{Day}-22-59-59";
//        HOUR_23 = "id:2019-{Month}-{Day}-23-00-00_2019-{Month}-{Day}-23-59-59";
//
////==================================================================================
//        MINUTE_TEST = "id:2019-{Month}-{Day}-{Hour}-{MinuteFirst}-00_2019-{Month}-{Day}-{Hour}-{MinuteLast}-59";
//        MINUTE_0 = "id:2019-{Month}-{Day}-00-00-00_2019-{Month}-{Day}-00-04-59";
//        MINUTE_1 = "id:2019-{Month}-{Day}-00-05-00_2019-{Month}-{Day}-00-09-59";
//        MINUTE_2 = "id:2019-{Month}-{Day}-00-10-00_2019-{Month}-{Day}-00-14-59";
//        MINUTE_3 = "id:2019-{Month}-{Day}-00-15-00_2019-{Month}-{Day}-00-19-59";
//        MINUTE_4 = "id:2019-{Month}-{Day}-00-20-00_2019-{Month}-{Day}-00-24-59";
//        MINUTE_5 = "id:2019-{Month}-{Day}-00-25-00_2019-{Month}-{Day}-00-29-59";
//        MINUTE_6 = "id:2019-{Month}-{Day}-00-30-00_2019-{Month}-{Day}-00-34-59";
//        MINUTE_7 = "id:2019-{Month}-{Day}-00-35-00_2019-{Month}-{Day}-00-39-59";
//        MINUTE_8 = "id:2019-{Month}-{Day}-00-40-00_2019-{Month}-{Day}-00-44-59";
//        MINUTE_9 = "id:2019-{Month}-{Day}-00-45-00_2019-{Month}-{Day}-00-49-59";
//        MINUTE_10 = "id:2019-{Month}-{Day}-00-50-00_2019-{Month}-{Day}-00-54-59";
//        MINUTE_11 = "id:2019-{Month}-{Day}-00-55-00_2019-{Month}-{Day}-00-59-59";
//
////==================================================================================
//    }
//    public MWCamDriveTime(RemoteWebDriver driver)
//    {
//        super(driver);
//    }
//
//}
