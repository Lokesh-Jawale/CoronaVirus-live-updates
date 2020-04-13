package com.lokilabs.coronavirusnews.loaders;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.AsyncTaskLoader;
import android.support.v4.content.Loader;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;

public class LiveStatsLoader extends AsyncTaskLoader<String[]>{

    private final static String LIVE_GET_URL = "https://virusncov.com/";

    public LiveStatsLoader(@NonNull Context context) {
        super(context);
    }

    @Nullable
    @Override
    public String[] loadInBackground() {
        String a[] = new String[5];
        // Strings in the order total, deaths, recovered, dangerText, mild;
        // a[0] = total
        // a[1] = deaths
        // a[2] = recovered
        // a[3] = mild cases
        // a[4] = serious cases
        try {
            Document document = Jsoup.connect(LIVE_GET_URL).get();

            Elements data = document.select("div.first-count");
            a[0] = data.select("h2")
                    .select("span")
                    .text();

            Elements data1 = document.select("div.second-count.mt-large");
            a[1] = data1.select("h2")
                    .select("span.red-text")
                    .text();

            a[2] = data1.select("h2")
                    .select("span.green-text")
                    .text();

            Elements data2 = document.select("div.second-div");

            a[3] = data2.select("span.pupp-text")
                    .text();

            a[4] = data2.select("span.red-text")
                    .text();

        } catch (IOException e) {
            e.printStackTrace();
        }
        return a;
    }

}
