package com.lokilabs.coronavirusnews;

import android.net.Uri;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class QuerryDetails {

    public static ArrayList<ListContentProvider> getLiveUpdateContent(String url) {
        ArrayList<ListContentProvider> arraylist = new ArrayList<>();
        ListContentProvider listContentProvider;
        //fetching the url in the form of doc
        try {
            Document doc = Jsoup.connect(url).get();
            String countryName;
            String cases;
            String deaths;
            String ncases;

            Elements data1 = doc.select("div.table-scroll tr");

            for (Element data : data1) {
//                Log.d("aDebugTag", "}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}" + data.html());
                countryName = data.select("td:nth-of-type(2")
                        .select("a.country-detail")
                        .select("strong")
                        .text();
                cases = data.select("td:nth-of-type(3)")
                        .text();
                ncases = data.select("td.orage-bg")
                        .text();
                deaths = data.select("td:nth-of-type(5)")
                        .text();

                if (countryName.length() > 17) {
                    countryName = countryName.substring(0, 17);
                }
                if (countryName.equals("")){
                    continue;
                }
                Log.d("aDebugTag", "-------------------------------" + ncases);
                try {
                    if((ncases != null) && (ncases.length() >=1 )){
                        int i = 1;
                        while (!(ncases.charAt(i) == '+')) {
                            i++;
                            if (ncases.length() <= i) {
                                break;
                            }
                        }
                        ncases = ncases.substring(0, i-1);
                    }
                }catch (NullPointerException e){
                    e.getMessage();
                }
                Log.d("aDebugTag", "/////////////////////////////////////" + ncases);
//                Log.d("aDebugTag", "-------------------------------" + countryName);
                listContentProvider = new ListContentProvider(countryName, cases, deaths, ncases);
                arraylist.add(listContentProvider);

            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return arraylist;
    }


    public static ArrayList<ListContentProvider> getNewsContent(String url) {
        ArrayList<ListContentProvider> arrayList = new ArrayList<>();
        String jsonResponse;
        StringBuilder jsonResponse0 = new StringBuilder();

        URL murl = null;
        try {
            murl = new URL(url);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        HttpURLConnection httpURLConnection = null;
        InputStream inputStream = null;

        try {
            httpURLConnection = (HttpURLConnection) murl.openConnection();
            httpURLConnection.setRequestMethod("GET");
            httpURLConnection.setConnectTimeout(25000);
            httpURLConnection.setReadTimeout(30000);
            httpURLConnection.connect();

            inputStream = httpURLConnection.getInputStream();
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

            String line = bufferedReader.readLine();

            while (line != null) {
                jsonResponse0.append(line);
                line = bufferedReader.readLine();
            }

            jsonResponse = jsonResponse0.toString();

            arrayList = getData(jsonResponse);

        } catch (IOException e) {
            e.printStackTrace();
        }

        return arrayList;
    }

    private static ArrayList<ListContentProvider> getData(String jsonResponse) {
        ArrayList<ListContentProvider> plist = new ArrayList<>();

        try {
            JSONObject jsonRootObject = new JSONObject(jsonResponse);
            JSONArray articlesArray = jsonRootObject.getJSONArray("articles");

            for (int i = 0; i < articlesArray.length(); i++) {
                JSONObject jsonObject = articlesArray.getJSONObject(i);

                String title = jsonObject.getString("title");
                String des = jsonObject.getString("description");
                String image = jsonObject.getString("urlToImage");
                Uri uri = Uri.parse(jsonObject.getString("url"));

                if ((des.equals("")) && (image.equals(""))) {
                    continue;
                } else {
                    ListContentProvider listContentProvider = new ListContentProvider(title, des, image, uri);
                    plist.add(listContentProvider);
                }
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return plist;
    }
}

