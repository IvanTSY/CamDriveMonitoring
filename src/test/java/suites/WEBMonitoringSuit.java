package suites;


import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import testsCamDriveMonitoring.testForAndroidWeb.AllCameraAndroidArchiveMonitoring;
import testsCamDriveMonitoring.testForIOSWeb.AllCameraIOSArchiveMonitoring;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        AllCameraAndroidArchiveMonitoring.class,
        AllCameraIOSArchiveMonitoring.class
})
public class WEBMonitoringSuit {
}
