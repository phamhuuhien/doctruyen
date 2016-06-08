package com.hstudio.doctruyen.async;

import android.os.AsyncTask;

import com.hstudio.doctruyen.MainActivity;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by phhien on 6/7/2016.
 */
public class LoadTypes extends AsyncTask<String, Integer, List<String>> {

    private MainActivity mMainActivity;
    public LoadTypes(MainActivity mainActivity) {
        mMainActivity = mainActivity;
    }
    @Override
    protected List<String> doInBackground(String... urls) {
        String url = urls[0];
        String html = "";
        try {
            HttpClient client = new DefaultHttpClient();
            HttpGet request = new HttpGet(url);
            HttpResponse response = client.execute(request);
            InputStream in = response.getEntity().getContent();
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));
            StringBuilder str = new StringBuilder();
            String line = null;
            while ((line = reader.readLine()) != null) {
                str.append(line);
            }
            in.close();
            html = str.toString();
        } catch(Exception e) {
            e.printStackTrace();
        }

        List<String> types = new ArrayList<>();
        Pattern p = Pattern.compile("(?i)<li><a([^>]+)>(.+?)</a></li>");
        Matcher m = p.matcher(html);
        while(m.find()) {
            String href = m.group(0);
            String text = href.substring(href.indexOf("\">") + 2, href.indexOf("</a>"));
            System.out.println(text);
            types.add(text);
        }

        return types;
    }

    protected void onProgressUpdate(Integer... progress) {
        //setProgressPercent(progress[0]);
    }

    protected void onPostExecute(final List<String> result) {
        mMainActivity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                mMainActivity.setTypes(result);
            }
        });
    }
}
