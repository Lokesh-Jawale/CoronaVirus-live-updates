package com.lokilabs.coronavirusnews.fragments;

import android.annotation.SuppressLint;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.lokilabs.coronavirusnews.ListContentProvider;
import com.lokilabs.coronavirusnews.MainActivity;
import com.lokilabs.coronavirusnews.R;
import com.lokilabs.coronavirusnews.adapters.LiveUpdateArrayAdapter;
import com.lokilabs.coronavirusnews.loaders.LiveStatsLoader;
import com.lokilabs.coronavirusnews.loaders.LiveUpdateLoader;

import java.util.ArrayList;
import java.util.Objects;

public class LiveUpdateFragment extends Fragment
        implements LoaderManager.LoaderCallbacks<ArrayList<ListContentProvider>> {

    final static String LIVE_GET_URL = "https://virusncov.com/";
    RecyclerView recyclerView;
    ListView listView;
    ArrayList<ListContentProvider> mlist;
    ProgressBar progressBar;
    View view;

    TextView totalCases;
    TextView totalDeaths;
    TextView mildCases;
    TextView totalRecovered;
    TextView danger;
    TextView seriousCases;

    TextView emptyText;

    @SuppressLint("SetJavaScriptEnabled")
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.live_update_fragment, container, false);

        try {
            ConnectivityManager connMgr = (ConnectivityManager)
                    Objects.requireNonNull(getActivity()).getSystemService(Context.CONNECTIVITY_SERVICE);

            // Get details on the currently active default data network
            NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();

            // If there is a network connection, fetch data
            if (networkInfo != null && networkInfo.isConnected()) {

                GetStats getStats = new GetStats();
                getStats.startLoaderManager();
                getLoaderManager().initLoader(1, null, this).forceLoad();

            } else {
                emptyText = (TextView)view.findViewById(R.id.empty_text);
                emptyText.setText("No Internet Connection");
                progressBar.setVisibility(View.GONE);
            }
        }catch(Exception e){
            e.getMessage();
        }

        totalCases = (TextView) view.findViewById(R.id.total_cases_count_text);
        totalDeaths = (TextView) view.findViewById(R.id.deaths_count_text);
        totalRecovered = (TextView) view.findViewById(R.id.recovered_count_text);
        //just a text serious cases will be concatenated...
        danger = (TextView) view.findViewById(R.id.danger_text);
        mildCases = (TextView) view.findViewById(R.id.mild_cases_count);
        seriousCases = (TextView) view.findViewById(R.id.serious_cases_count);

        progressBar = (ProgressBar) view.findViewById(R.id.progressbar);
        progressBar.setVisibility(View.VISIBLE);

        listView = (ListView) view.findViewById(R.id.list_view_live);

        String START_URL = "https://virusncov.com/";

        return view;
    }

    public void updateUi(ArrayList<ListContentProvider> arrayList) {
        mlist = arrayList;
        progressBar.setVisibility(View.GONE);
        LiveUpdateArrayAdapter liveUpdateArrayAdapter = new LiveUpdateArrayAdapter(Objects.requireNonNull(getContext()), 0, arrayList);
        listView.setAdapter(liveUpdateArrayAdapter);

    }

    @NonNull
    @Override
    public Loader<ArrayList<ListContentProvider>> onCreateLoader(int i, @Nullable Bundle bundle) {
        return new LiveUpdateLoader(Objects.requireNonNull(getContext()), LIVE_GET_URL);
    }

    @Override
    public void onLoadFinished(@NonNull Loader<ArrayList<ListContentProvider>> loader, ArrayList<ListContentProvider> listContentProviders) {
        updateUi(listContentProviders);
    }

    @Override
    public void onLoaderReset(@NonNull Loader<ArrayList<ListContentProvider>> loader) {
        loader.reset();
    }

    private class GetStats implements LoaderManager.LoaderCallbacks<String[]> {

        GetStats() {
        }

        public void startLoaderManager() {
            getLoaderManager().initLoader(2, null, this).forceLoad();
        }

        @NonNull
        @Override
        public Loader<String[]> onCreateLoader(int i, @Nullable Bundle bundle) {
            return new LiveStatsLoader(Objects.requireNonNull(getContext()));
        }

        @Override
        public void onLoadFinished(@NonNull Loader<String[]> loader, String[] strings) {
            updateUi1(strings);
        }

        @Override
        public void onLoaderReset(@NonNull Loader<String[]> loader) {
            loader.reset();
        }

        private void updateUi1(String[] strings) {
            // a[0] = total
            // a[1] = deaths
            // a[2] = recovered
            // a[3] = mild cases
            // a[4] = serious cases
            String[] a = strings;

            totalCases.setText(a[0]);
            totalDeaths.setText(a[1]);
            totalRecovered.setText(a[2]);
            danger.setText("of which " + a[4] + " in mild/severe condition");
//        Log.d("aDebugTag", "-------------------------------" + a[3]);
            try {
                StringBuilder x = new StringBuilder();
                int i = 0;
                while (!(a[3].charAt(i) == '(')) {
                    x.append(a[3].charAt(i));
                    i++;
                    if(i >= a[3].length()){
                        break;
                    }
                }
                a[3] = x.toString();
//            Log.d("aDebugTag", "/////////////////////////////////////////////////" + a[3]);
                x = new StringBuilder();
                i = 0;
                while (!(a[4].charAt(i) == '(')) {
                    x.append(a[4].charAt(i));
                    i++;
                    if(i >= a[4].length()){
                        break;
                    }
                }
                a[4] = x.toString();
            } catch (NullPointerException e) {
                e.getMessage();
            }
            mildCases.setText(a[3]);
            seriousCases.setText(a[4]);

        }
    }

}
