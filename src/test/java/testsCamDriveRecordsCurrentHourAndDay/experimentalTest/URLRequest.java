package testsCamDriveRecordsCurrentHourAndDay.experimentalTest;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.*;
import java.util.List;

public class URLRequest {
//    public static void main(String[] args) throws Exception {
//        URL login = new URL("https://camdrive.com/mobile/api_native/login?username=service&password=7ujm6yhn");
//        URL table = new URL("https://camdrive.com/mobile/api_native/cameras/?action=schedule&camera_channel_id=2d9636b2bb3a06b4336adf481a30acb3");
//        //==============================================
//        CookieManager manager = new CookieManager();
//        //==============================================
//        URLConnection loginConnection = login.openConnection();
//        URLConnection tableConnection = table.openConnection();
//        //==============================================
//        BufferedReader inLogin = new BufferedReader(
//                new InputStreamReader(
//                        loginConnection.getInputStream()));
//        String inputLineLogin;
//        //==============================================
//        while ((inputLineLogin = inLogin.readLine()) != null)
//            System.out.println(inputLineLogin);
//        inLogin.close();
//        //==============================================
//        BufferedReader inTable = new BufferedReader(
//                new InputStreamReader(
//                        tableConnection.getInputStream()));
//        String inputLineTable;
//        //==============================================
//        while ((inputLineTable = inTable.readLine()) != null)
//            System.out.println(inputLineTable);
//        inTable.close();
//        //==============================================
//        Object contains = loginConnection.getRequestProperties();
//        System.out.println("ВОТ - "+contains);
//        //==============================================
//        Object containsTable = tableConnection.getRequestProperties();
//        System.out.println("ВОТ - "+containsTable);
//    }
    public static void main(String args[]) throws Exception {
        URL url = new URL("https://camdrive.com/mobile/api_native/cameras/?action=schedule&camera_channel_id=2d9636b2bb3a06b4336adf481a30acb3");
        URLConnection conn = url.openConnection();

        conn.setRequestProperty("Cookie",
                "remember_code=367426806e656e8240a978a0b819883cf15a70ac; " +
                        "identity=service;" +
                        "session = wEwNfNPiFY0htDi4J7mU2XfUXdvE6dnbsD%2FSVtjx2o7%2Fmbo0c0J%2FhNBeadQBdse9KirLfaXCIMZuL4gbRMn4vgpmiza9eMWgxjDtrHAze1BD4cqG6CywCtyRYkiN%2BfbS60wPC5N7mp7pGzfhT9Uxc%2BlVc7lBjsvQbDNpt9lRvdsKXCJ%2BPiHzh276kuK9Zr%2BDadsTpLumnla4VqkUUqMlrlJyVF4KeewVQFYj9lS64xWG4u8xlvjrEtdZivjvDxI8McW4ujrghYbtUU%2BRF5aC4jJzyjmRfc9J3kxCvU76ho4%3D88aa41ad186f3bfd3ad13d5e0f2187c0317cc53e");
        conn.connect();

        BufferedReader inLogin = new BufferedReader(
                new InputStreamReader(
                        conn.getInputStream()));
        String inputLineLogin;
        //==============================================
        while ((inputLineLogin = inLogin.readLine()) != null)
            System.out.println("Вот "+inputLineLogin);
        inLogin.close();


    }

    public static void postRequest ()throws Exception{

        main();

    }

    private static List<HttpCookie> main() throws Exception{
        /*Получаем куки при авторизации*/
        String urlString = "https://camdrive.com/mobile/api_native/login?username=service&password=7ujm6yhn";
        CookieManager manager = new CookieManager();
        CookieHandler.setDefault(manager);
        URL url = new URL(urlString);
        URLConnection connection = url.openConnection();
        Object obj = connection.getContent();
        url = new URL(urlString);
        connection = url.openConnection();
        obj = connection.getContent();

        CookieStore cookieJar = manager.getCookieStore();

        List<HttpCookie> cookies = cookieJar.getCookies();

        for (HttpCookie cookie : cookies) {
            System.out.println(cookie);
        }
        return cookies;
    }
}

//https://camdrive.com/mobile/api_native/cameras/?action=schedule&camera_channel_id=2d9636b2bb3a06b4336adf481a30acb3