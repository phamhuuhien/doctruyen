package com.hstudio.doctruyen.async;

import android.content.Context;
import android.os.AsyncTask;

import com.hstudio.doctruyen.PrimaryFragment;

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
 * Created by phhien on 6/8/2016.
 */
public class LoadStories extends AsyncTask<String, Integer, List<String>> {

    private PrimaryFragment mFragment;
    public LoadStories(PrimaryFragment fragment) {
        mFragment = fragment;
    }
    @Override
    protected List<String> doInBackground(String... urls) {
        List<String> result = new ArrayList<>();
        try {
            Document doc = Jsoup.connect("http://truyenfull.vn/danh-sach/truyen-moi/").get();
            Elements news = doc.select("div[itemtype=\"http://schema.org/Book\"]");
            for(Element element: news) {
                Elements a = element.select("a[href]").not("[itemprop=\"genre\"]");
                //System.out.println(a.get(0).text());
                result.add(a.get(0).text());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return result;
    }

    protected void onProgressUpdate(Integer... progress) {
        //setProgressPercent(progress[0]);
    }

    protected void onPostExecute(final List<String> result) {
        mFragment.getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                mFragment.setStory(result);
            }
        });
    }
}
