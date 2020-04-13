package com.lokilabs.coronavirusnews.loaders;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.AsyncTaskLoader;

import com.lokilabs.coronavirusnews.ListContentProvider;
import com.lokilabs.coronavirusnews.QuerryDetails;

import java.util.ArrayList;

public class LiveUpdateLoader extends AsyncTaskLoader<ArrayList<ListContentProvider>> {

    String url;

    public LiveUpdateLoader(@NonNull Context context, String url) {
        super(context);
        this.url = url;
    }

    @Nullable
    @Override
    public ArrayList<ListContentProvider> loadInBackground() {
        ArrayList<ListContentProvider> arrayList;

        arrayList = QuerryDetails.getLiveUpdateContent(url);

        return arrayList;
    }

}
