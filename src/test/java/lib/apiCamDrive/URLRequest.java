package lib.apiCamDrive;


import com.google.gson.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.*;
import java.util.*;

public class URLRequest {

//Метод проверки баланса через АПИ
    public static String getBallanceAPI() throws Exception {
        URL ballance = new URL("https://camdrive.com/mobile/api_native/login?username=service&password=7ujm6yhn");

//        URL ballance = new URL("https://www.camdrive.com/mobile/phone/ajax?action=get_state");

        URLConnection conn = ballance.openConnection();
        conn.setConnectTimeout(5000);
        conn.connect();

        BufferedReader getBallance = new BufferedReader(
                new InputStreamReader(
                        conn.getInputStream()));
        String inputLine = getBallance.readLine();

        //Закрываем чтение буфера
        getBallance.close();

        //Парсинг Json
        JsonParser jsonParser = new JsonParser();
        JsonElement parse = jsonParser.parse(inputLine);
        JsonObject asJsonObject = parse.getAsJsonObject();

        JsonObject data = asJsonObject.getAsJsonObject("data");
        JsonElement cameras = data.getAsJsonArray("cameras");
        JsonElement profile = data.getAsJsonObject("profile");


        //Результат АПИ
        JsonPrimitive pay_ballance = ((JsonObject) profile).getAsJsonPrimitive("pay_balance");

        return pay_ballance.getAsString();
    }





    public static int getScheduleCamera(int hour, String cameraChannelID) throws Exception {
        URL url = new URL("https://camdrive.com/mobile/api_native/cameras/?action=schedule&camera_channel_id="+cameraChannelID);
        URLConnection conn = url.openConnection();
        conn.setConnectTimeout(5000);
//TODO: Авторизация + парсинг куки + парсинг Json + запрос расписания камеры
//Вызов метода авторизации в ЛК
        List<HttpCookie> cookies = getRequestAuthorization();
        //remember_code - cookies.get(0)
        //identity - cookies.get(1)
        //session - cookies.get(2)
        conn.setRequestProperty("Cookie", cookies.get(0)+";"+cookies.get(1)+";"+cookies.get(2));
        conn.connect();
//Читаем расписание
        BufferedReader getSchedule = new BufferedReader(
                new InputStreamReader(
                        conn.getInputStream()));
        String inputLineLogin = getSchedule.readLine();
//Закрываем чтение буфера
        getSchedule.close();

        JsonParser jsonParser = new JsonParser();
        JsonElement parse = jsonParser.parse(inputLineLogin);
        JsonObject asJsonObject = parse.getAsJsonObject();
        JsonArray data = asJsonObject.getAsJsonArray("data");


        int[][] array= new int[8][24];
        for (int i = 0; i < data.size(); i++) {
            JsonElement jsonElement = data.get(i);
            JsonArray asJsonArray = jsonElement.getAsJsonArray();
            for (int n = 0; n < asJsonArray.size(); n++) {
                JsonElement jsonElement1 = asJsonArray.get(n);
                JsonObject asJsonObject1 = jsonElement1.getAsJsonObject();
                JsonElement asJsonPrimitive = null;
                JsonElement pull = new JsonPrimitive(10000);
                try {
                    asJsonPrimitive = asJsonObject1.getAsJsonPrimitive("value");
                }catch (ClassCastException ignored){
                    asJsonPrimitive = pull;
                }
                array[i][n] = asJsonPrimitive.getAsInt();
            }
        }

        //Переменная [Hour] будет принимать значение из теста возвращать значение по которому тест будет запускаться
        //Значения при которых тест запустится -
            // 0 - Постоянная запись
            // 1 - Запись по детекции
            // 10000 - Запись в расписании не выставленна
            // 3 - Запись по нажатию кнопки на домофоне
//TODO Расписание для камеры на текущий момент

        //TODO Синхронизация индексов массива с индексами дней недели
        int dayOfWeek = Calendar.getInstance().getTime().getDay() - 1;
        if (dayOfWeek < 0) dayOfWeek = 6;
//TODO Исключаем ошибку с Полуночью 00-00 для Парсера
        if (hour < 0){
            hour = 23;
            dayOfWeek = dayOfWeek -1;
            //синхронизация перехода по индексам от ПН на ВС
            if (dayOfWeek < 0) dayOfWeek = 6;
        }

        int CurrentSchedule = array[dayOfWeek][hour];
        return CurrentSchedule;
    }


    private static List<HttpCookie> getRequestAuthorization() throws Exception{
        /*Получаем куки при авторизации*/
        String urlString = "https://camdrive.com/mobile/api_native/login?username=service&password=7ujm6yhn";

        CookieManager manager = new CookieManager();
        CookieHandler.setDefault(manager);
        URL url = new URL(urlString);
        URLConnection connection = url.openConnection();
        connection.setConnectTimeout(5000);
        //Object obj = connection.getContent();
        url = new URL(urlString);
        connection = url.openConnection();
        Object obj = connection.getContent();

        CookieStore cookieJar = manager.getCookieStore();

        List<HttpCookie> cookies = cookieJar.getCookies();
//        for (HttpCookie cookie : cookies) {
//            System.out.println(cookie);
//        }

        return cookies;
    }


}

