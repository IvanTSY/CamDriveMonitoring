package testsCamDriveMonitoring.experimentalTest;

public class Example {
    public static void exp(){
        double version = 1;
        String url = "https://www.camdrive.com/mobile/phone/ajax";
        String[] fields = {
                "username:autotest",
                "password:autotest"

                //"Body:controller=online&config={'balance':true}"
        };
        ConnectionFactory connectionFactory = new ConnectionFactory(fields,url,version);

//        connectionFactory.setUserAgent("Задал агента");
        String response = connectionFactory.buildConnection();
        System.out.println(response);
        System.out.println(connectionFactory.getEndpoints());

//        CookieManager manager = new CookieManager();
//        List<HttpCookie> cookies = manager.getCookieStore().getCookies();
//        return cookies;
    }
}
