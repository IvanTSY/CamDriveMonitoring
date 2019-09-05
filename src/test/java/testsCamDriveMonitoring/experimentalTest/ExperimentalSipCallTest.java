package testsCamDriveMonitoring.experimentalTest;

import lib.CoreTestCase;
import lib.apiCamDrive.URLRequest;
import org.junit.Test;

public class ExperimentalSipCallTest extends CoreTestCase {

    @Test
    public void testInfinityCall() throws Exception{

        URLRequest URLRequest = new URLRequest();


        for (int i = 0; i < 100; i++){
            URLRequest.callSimulate(
                    "push-for-test.beward.ru",
                    "186882304CDE",
                    "8_872_363",
                    "pbx.dev.ktotam.info",
                    "admin",
                    "qweQWE123",
                    "192.168.28.159"
            );
            Thread.sleep(500);
        }

    }


}
