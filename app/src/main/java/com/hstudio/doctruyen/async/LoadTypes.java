package com.hstudio.doctruyen.async;

import android.os.AsyncTask;

import com.hstudio.doctruyen.MainActivity;
import com.hstudio.doctruyen.object.Type;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.BufferedReader;
import java.io.IOException;
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
public class LoadTypes extends AsyncTask<String, Integer, List<Type>> {

    private MainActivity mMainActivity;
    public LoadTypes(MainActivity mainActivity) {
        mMainActivity = mainActivity;
    }
    @Override
    protected List<Type> doInBackground(String... urls) {
        String url = urls[0];
//        String html = "";
//        try {
//            HttpClient client = new DefaultHttpClient();
//            HttpGet request = new HttpGet(url);
//            HttpResponse response = client.execute(request);
//            InputStream in = response.getEntity().getContent();
//            BufferedReader reader = new BufferedReader(new InputStreamReader(in));
//            StringBuilder str = new StringBuilder();
//            String line = null;
//            while ((line = reader.readLine()) != null) {
//                str.append(line);
//            }
//            in.close();
//            html = str.toString();
//        } catch(Exception e) {
//            e.printStackTrace();
//        }
//
//        List<String> types = new ArrayList<>();
//        Pattern p = Pattern.compile("(?i)<li><a([^>]+)>(.+?)</a></li>");
//        Matcher m = p.matcher(html);
//        while(m.find()) {
//            String href = m.group(0);
//            String text = href.substring(href.indexOf("\">") + 2, href.indexOf("</a>"));
//            types.add(text);
//        }

        List<Type> types = new ArrayList<>();
        try {
            Document doc = Jsoup.connect(url).get();
            Elements li = doc.select("ul.dropdown-menu li a");
            for(Element e : li) {
                Type type = new Type();
                type.setTitle(e.text());
                type.setLink(e.attr("href"));
                types.add(type);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return types;
    }

    protected void onProgressUpdate(Integer... progress) {
        //setProgressPercent(progress[0]);
    }

    protected void onPostExecute(final List<Type> result) {
        mMainActivity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                mMainActivity.setTypes(result);
            }
        });
    }
}
