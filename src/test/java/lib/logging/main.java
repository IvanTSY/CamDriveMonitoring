package lib.logging;

import lib.apiSlack.SlackSendMessaages;
import org.junit.Test;

import java.io.IOException;

public class main {

    @Test
    public void testMSG() throws IOException {

        SlackSendMessaages ms = new SlackSendMessaages();
        ms.sendMSG("test for Michail");

    }

}
