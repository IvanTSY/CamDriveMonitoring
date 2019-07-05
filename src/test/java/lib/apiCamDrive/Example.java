package lib.apiCamDrive;

import lib.ui.factories.ConnectionFactory;
import org.openqa.selenium.remote.http.HttpResponse;

import java.net.CookieHandler;
import java.net.CookieManager;
import java.net.HttpCookie;
import java.util.List;

public class Example {
    public static void exp(){
        double version = 0.1;
        String url = "https://www.camdrive.com/login";
        String[] fields = {
                "username:service",
                "password:7ujm6yhn"
        };
        ConnectionFactory connectionFactory = new ConnectionFactory(fields,url,version);

        connectionFactory.setUserAgent("Задал агента");
        String response = connectionFactory.buildConnection();
        System.out.println(response);

        CookieManager manager = new CookieManager();
        CookieHandler.setDefault(manager);
        List<HttpCookie> cookies = manager.getCookieStore().getCookies();
    }
}
