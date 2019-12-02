package lib.apiSlack;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class SlackSendMessaages {
    public void sendMSG(String text) throws IOException {
        HttpClient httpclient = HttpClients.createDefault();
        HttpPost httppost = new HttpPost("https://slack.com/api/chat.postMessage");

// Request parameters and other properties.
        List<NameValuePair> params = new ArrayList<NameValuePair>(2);
        params.add(new BasicNameValuePair("token", "xoxb-646834705505-810587823079-fKsn3miCDrsdiHk2frr5ZElH"));
        params.add(new BasicNameValuePair("channel", "мониторинг_camdrive"));
        params.add(new BasicNameValuePair("text", "_"+text+"_"));
        params.add(new BasicNameValuePair("username", "Мониторинг"));
        httppost.setEntity(new UrlEncodedFormEntity(params, "UTF-8"));

//Execute and get the response.
        HttpResponse response = httpclient.execute(httppost);
        HttpEntity entity = response.getEntity();

        if (entity != null) {
            InputStream instream = entity.getContent();
            try {
                System.out.println(instream);
            } finally {
                instream.close();
            }
        }


    }

    public void sendTestMSG(String text) throws IOException {
        HttpClient httpclient = HttpClients.createDefault();
        HttpPost httppost = new HttpPost("https://slack.com/api/chat.postMessage");

// Request parameters and other properties.
        List<NameValuePair> params = new ArrayList<NameValuePair>(2);
        params.add(new BasicNameValuePair("token", "xxx"));
        params.add(new BasicNameValuePair("channel", "тестовый_канал"));
        params.add(new BasicNameValuePair("text", "_"+text+"_"));
        params.add(new BasicNameValuePair("username", "Биба"));
        httppost.setEntity(new UrlEncodedFormEntity(params, "UTF-8"));

//Execute and get the response.
        HttpResponse response = httpclient.execute(httppost);
        HttpEntity entity = response.getEntity();

        if (entity != null) {
            InputStream instream = entity.getContent();
            try {
                System.out.println(instream);
            } finally {
                instream.close();
            }
        }


    }


}
