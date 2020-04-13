package com.lokilabs.coronavirusnews.fragments;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;

import com.lokilabs.coronavirusnews.ListContentProvider;
import com.lokilabs.coronavirusnews.R;
import com.lokilabs.coronavirusnews.WebViewActivity;
import com.lokilabs.coronavirusnews.adapters.NewsUpdateAdapter;
import com.lokilabs.coronavirusnews.loaders.NewsUpdateLoader;

import java.util.ArrayList;
import java.util.Objects;

public class NewsUpdateFragment extends Fragment implements LoaderManager.LoaderCallbacks<ArrayList<ListContentProvider>> {

    String NEWS_GET_URL = "https://newsapi.org/v2/top-headlines?q=coronavirus&pageSize=100&language=en&sortBy=publishedAt&apiKey=e5aed15994b8446c895feefa974dad68";
    ListContentProvider listContentProvider;
    ArrayList<ListContentProvider> arrayList;
    ListView listView;
    ProgressBar progressBar, progressBarImage;
    View view;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.news_update, container, false);

        progressBar = (ProgressBar)view.findViewById(R.id.progressbar1);
        progressBar.setVisibility(View.VISIBLE);

        listView = (ListView)view.findViewById(R.id.list_view_news);
        LoaderManager loaderManager = getLoaderManager();
        loaderManager.initLoader(1, savedInstanceState, this).forceLoad();

        return view;
    }

    private void updateUi(ArrayList<ListContentProvider> arrayList) {
        progressBar.setVisibility(View.GONE);
        final NewsUpdateAdapter newsUpdateAdapter = new NewsUpdateAdapter(Objects.requireNonNull(getContext()), 0, arrayList);
        listView.setAdapter(newsUpdateAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ListContentProvider listContentProvider1 = newsUpdateAdapter.getItem(position);

                Uri uri = Objects.requireNonNull(listContentProvider1).getUri();

                Intent intent = new Intent(parent.getContext(), WebViewActivity.class);
                intent.putExtra("url",uri.toString());
                startActivity(intent);

            }
        });

    }

    @NonNull
    @Override
    public Loader<ArrayList<ListContentProvider>> onCreateLoader(int i, @Nullable Bundle bundle) {
        return new NewsUpdateLoader(Objects.requireNonNull(getContext()), NEWS_GET_URL);
    }

    @Override
    public void onLoadFinished(@NonNull Loader<ArrayList<ListContentProvider>> loader, ArrayList<ListContentProvider> arrayList) {
        updateUi(arrayList);
    }

    @Override
    public void onLoaderReset(@NonNull Loader<ArrayList<ListContentProvider>> loader) {
        loader.reset();
    }

}
