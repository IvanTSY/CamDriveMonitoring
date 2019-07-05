package lib.apiCamDrive;

import lib.ui.factories.ConnectionFactory;

import java.net.CookieHandler;
import java.net.CookieManager;
import java.net.HttpCookie;
import java.util.List;

public class Example {
    public static List<HttpCookie> exp(){
        double version = 0.1;
        String url = "https://www.camdrive.com/";
        String[] fields = {
                "username:service",
                "password:7ujm6yhn"
        };
        ConnectionFactory connectionFactory = new ConnectionFactory(fields,url,version);

        //connectionFactory.setUserAgent("Задал агента");
        String response = connectionFactory.buildConnection();
        System.out.println(response);

        CookieManager manager = new CookieManager();
        CookieHandler.setDefault(manager);
        List<HttpCookie> cookies = manager.getCookieStore().getCookies();
        System.out.println(cookies);
        return cookies;
    }
}
