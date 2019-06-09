package testsCamDriveRecordsCurrentHourAndDay.experimentalTest;

import org.apache.http.HttpClientConnection;
import org.apache.http.HttpConnection;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.DefaultHttpClient;

import javax.servlet.http.Cookie;
import java.io.IOException;
import java.util.List;

public class URLParser {
    public static void main(String... args) throws IOException {
        final HttpUriRequest request = new HttpGet("https://camdrive.com/mobile/api_native/login?username=service&password=7ujm6yhn");
        final DefaultHttpClient http = new DefaultHttpClient();
        http.execute(request);
        final List<org.apache.http.cookie.Cookie> cookies = http.getCookieStore().getCookies();
        System.out.println(cookies);
    }

}
