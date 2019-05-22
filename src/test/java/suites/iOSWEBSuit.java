package suites;


import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import testsCamDriveRecordsCurrentHourAndDay.testForIosWeb.*;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        CD100_E75A_ms3.class,
        CD100_E772_ms4.class,
        CD100_E778_ms5.class,
        CD310_2E51_ms4.class,
        CD320_AA06_ms3.class,
        CD320_AA78_ms5.class,
        CD600_EF78_ms6.class,
        CD630_910D_ms6.class,
        N1001_3A00_bwd.class
})
public class iOSWEBSuit {
}
