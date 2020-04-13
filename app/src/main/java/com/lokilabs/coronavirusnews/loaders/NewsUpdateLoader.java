package com.lokilabs.coronavirusnews.loaders;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.AsyncTaskLoader;

import com.lokilabs.coronavirusnews.ListContentProvider;
import com.lokilabs.coronavirusnews.QuerryDetails;

import java.util.ArrayList;

public class NewsUpdateLoader extends AsyncTaskLoader<ArrayList<ListContentProvider>> {

    String url;
    ArrayList<ListContentProvider> arrayList;

    public NewsUpdateLoader(@NonNull Context context, String url) {
        super(context);
        this.url = url;
    }

    @Nullable
    @Override
    public ArrayList<ListContentProvider> loadInBackground() {

        arrayList = QuerryDetails.getNewsContent(url);

        return arrayList;
    }
}
