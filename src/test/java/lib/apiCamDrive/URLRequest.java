package lib.apiCamDrive;


import com.google.gson.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.*;
import java.util.Calendar;
import java.util.List;

public class URLRequest {

    public static int getScheduleCamera(int hour, String cameraChannelID) throws Exception {
        URL url = new URL("https://camdrive.com/mobile/api_native/cameras/?action=schedule&camera_channel_id="+cameraChannelID);
        URLConnection conn = url.openConnection();
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
//        System.err.println( data);
//        System.err.println(data.size());
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
//                System.err.println(i + " : " + n);
//                System.err.println("value " + asJsonPrimitive.getAsInt());
                array[i][n] = asJsonPrimitive.getAsInt();
            }
            //System.err.println(jsonElement.isJsonArray());
            //System.err.println(jsonElement.isJsonObject());
        }
//        for (int [] anArr:array){
//            for (int anAnArr:anArr){
//                System.out.print(anAnArr + " ");
//            }
//        }
//        System.out.println("\nElement = "+array[0][23]+" End");

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
        Object obj = connection.getContent();
        url = new URL(urlString);
        connection = url.openConnection();
        obj = connection.getContent();

        CookieStore cookieJar = manager.getCookieStore();

        List<HttpCookie> cookies = cookieJar.getCookies();
//        for (HttpCookie cookie : cookies) {
//            System.out.println(cookie);
//        }

        return cookies;
    }
}

//https://camdrive.com/mobile/api_native/cameras/?action=schedule&camera_channel_id=2d9636b2bb3a06b4336adf481a30acb3