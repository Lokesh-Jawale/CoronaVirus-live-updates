package com.lokilabs.coronavirusnews.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.lokilabs.coronavirusnews.ListContentProvider;
import com.lokilabs.coronavirusnews.R;

import java.util.ArrayList;

public class LiveUpdateArrayAdapter extends ArrayAdapter<ListContentProvider> {

    private ListContentProvider listContentProvider;
    private TextView countryTextView;
    private TextView totalCasesTextView;
    private TextView totalDeaths;
    private TextView newCases;
    
    public LiveUpdateArrayAdapter(@NonNull Context context, int resource, @NonNull ArrayList<ListContentProvider> list) {
        super(context, resource, list);
    }
    
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        
        listContentProvider = getItem(position);
        
        if(convertView == null){
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_view_live, parent, false);
        }
        
        countryTextView = (TextView)convertView.findViewById(R.id.country_name_text1);
        totalCasesTextView = (TextView)convertView.findViewById(R.id.total_cases_text1);
        totalDeaths = (TextView)convertView.findViewById(R.id.total_deaths_text1);
        newCases = (TextView)convertView.findViewById(R.id.total_newcases_text1);

        countryTextView.setText(listContentProvider.getCountry());
        totalCasesTextView.setText(listContentProvider.getCases());
        totalDeaths.setText(listContentProvider.getDeaths());
        newCases.setText(listContentProvider.getNewCases());

        return convertView;
    }
}
