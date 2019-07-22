package lib.apiCamDrive;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import javafx.application.Application;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.client.LaxRedirectStrategy;
import org.apache.http.message.BasicNameValuePair;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class URLRequestNotApiMethod {

//    private CloseableHttpClient httpClient;
//    private ApplicationManager app;
//
//    public HttpSession(ApplicationManager app){
//        this.app = app;
//        httpClient = HttpClients.custom().setRedirectStrategy(new LaxRedirectStrategy()).build();
//    }
//
//    public boolean login (String username, String password) throws IOException{
//        HttpPost post = new HttpPost(app.getProperty("web.baseUrl")+"/login.php");
//        List<NameValuePair> params = new ArrayList<>();
//        params.add(new BasicNameValuePair("username",username));
//        params.add(new BasicNameValuePair("password",password));
//        params.add(new BasicNameValuePair("return","index.php"));
//        post.setEntity(new UrlEncodedFormEntity(params));
//        CloseableHttpResponse response = httpClient.execute(post);
//        String body = getTextFrom(response);
//        return body.contains(String.format("",username));
//    }
//
//    private String getTextFrom(CloseableHttpResponse response) throws IOException{
//
//    }











//    public static String getRequestAuthorization() throws IOException {
//
//        URL url = new URL("https://www.camdrive.com/mobile/phone/login");
//        HttpURLConnection con = (HttpURLConnection) url.openConnection();
//        con.setRequestMethod("POST");
//
//        con.setRequestProperty("User-Agent", "Mozilla/5.0 (Linux; Android 8.0; Pixel 2 Build/OPD3.170816.012) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/75.0.3770.100 Mobile Safari/537.36");
//        con.setRequestProperty("Content-Type", "application/json");
//        con.setRequestProperty("Accept", "application/json");
//        con.setDoOutput(true);
//
//        String jsonInputString = "{username:service, password:7ujm6yhn}";
//
//        try (OutputStream os = con.getOutputStream()) {
//            byte[] input = jsonInputString.getBytes("utf-8");
//            os.write(input, 0, input.length);
//        }
//
//        try (BufferedReader br = new BufferedReader(
//                new InputStreamReader(con.getInputStream(), "utf-8"))) {
//            StringBuilder response = new StringBuilder();
//            String responseLine = null;
//            while ((responseLine = br.readLine()) != null) {
//                response.append(responseLine.trim());
//            }
//        }
//
//        //con.setConnectTimeout(5000);
//
//        String cookie = con.getHeaderField("Set-Cookie");
//        List<HttpCookie> cookies = HttpCookie.parse(cookie);
//        System.err.println(cookies.size());
//        System.err.println(cookies);
//
//        String asd = String.valueOf(cookies.get(0));
//
//
//        String[] exploded_cookie = asd.split(Pattern.quote("]"), 2);
//        String split_cookie = exploded_cookie[0];
//
//        String[] exploded_split_cookie = split_cookie.split(Pattern.quote("["), 2);
//        String split_split_cookie = exploded_split_cookie[0];
//
//        System.out.println("New cookie " + split_split_cookie);
//
//        return split_split_cookie;
//
//    }
//
//    public static JsonElement getBalance() throws IOException {
//
//        String cookie = getRequestAuthorization();
//
//        String urlParametrs = "action=get_state";
//        byte[] postData = urlParametrs.getBytes(StandardCharsets.UTF_8);
//        int postDataLength = postData.length;
//        String request = "https://www.camdrive.com/mobile/phone/ajax";
//        URL url = new URL(request);
//        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
//        conn.setDoOutput(true);
//        conn.setInstanceFollowRedirects(false);
//        conn.setRequestMethod("POST");
//        conn.setRequestProperty("User-Agent","Mozilla/5.0 (Linux; Android 8.0; Pixel 2 Build/OPD3.170816.012) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/75.0.3770.100 Mobile Safari/537.36");
//        conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
//        conn.setRequestProperty("charset", "utf-8");
//        conn.setRequestProperty("Content-Length", Integer.toString(postDataLength));
//        conn.setRequestProperty("Cookie", cookie+"");
//        conn.setUseCaches(false);
//        try (DataOutputStream wr = new DataOutputStream(conn.getOutputStream())) {
//            wr.write(postData);
//        }
//        BufferedReader getSchedule = new BufferedReader(
//                new InputStreamReader(
//                        conn.getInputStream()));
//        String inputLineLogin = getSchedule.readLine();
//        JsonParser jsonParser = new JsonParser();
//        JsonElement parse = jsonParser.parse(inputLineLogin);
//        JsonObject asJsonObject = parse.getAsJsonObject();
//        JsonArray data = asJsonObject.getAsJsonArray("result");



        //        URL url = new URL("https://www.camdrive.com/mobile/phone/ajax");
//        HttpURLConnection conn =  (HttpURLConnection) url.openConnection();
//
//        conn.setConnectTimeout(5000);
//        conn.setRequestMethod("POST");
//        conn.setRequestProperty("User-Agent", "Mozilla/5.0 (Linux; Android 8.0; Pixel 2 Build/OPD3.170816.012) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/75.0.3770.100 Mobile Safari/537.36");
////        conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
////        conn.setRequestProperty("Accept", "application/json");
//        conn.setRequestProperty("Cookie", cookie+"");
//        conn.setRequestProperty("text","action=get_state");
//
//
//            return data;
//    }
 }

