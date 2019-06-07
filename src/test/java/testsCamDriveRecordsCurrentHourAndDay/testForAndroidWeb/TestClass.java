package testsCamDriveRecordsCurrentHourAndDay.testForAndroidWeb;

import org.junit.Test;
import lib.apiCamDrive.URLRequest;

import java.util.Calendar;


public class TestClass {
    @Test
    public void Test() throws Exception {

        System.out.println(URLRequest.getScheduleCamera(0,"2e6fb75139e4226198f9f6c0786e8b8a"));
        System.out.println(Calendar.getInstance().getTime().getDay());

    }
}
