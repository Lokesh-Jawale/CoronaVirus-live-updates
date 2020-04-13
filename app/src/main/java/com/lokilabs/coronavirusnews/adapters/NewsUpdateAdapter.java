package com.lokilabs.coronavirusnews.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.lokilabs.coronavirusnews.ListContentProvider;
import com.lokilabs.coronavirusnews.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class NewsUpdateAdapter extends ArrayAdapter<ListContentProvider> {

    Context mContext;
    ArrayList<ListContentProvider> mlist;
    ListContentProvider listContentProvider;

    public NewsUpdateAdapter(@NonNull Context context, int resource, @NonNull ArrayList<ListContentProvider> mlist) {
        super(context, resource, mlist);
        this.mlist = mlist;
        this.mContext = context;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if(convertView == null){
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_view_news, parent, false);
        }

        listContentProvider = getItem(position);

        ImageView newsImage = (ImageView)convertView.findViewById(R.id.news_image);
        TextView titleText = (TextView)convertView.findViewById(R.id.news_title);
        TextView desTitle = (TextView)convertView.findViewById(R.id.news_des);

        try {
            Picasso.with(mContext).load(listContentProvider.getNewsImage()).into(newsImage);
            titleText.setText(listContentProvider.getNewsTitle());
            desTitle.setText(listContentProvider.getNewsDes());
        }catch (Exception e){
            e.getMessage();
            Log.d("aDebugTag", "The image fell to load since the url was empty");
        }

        ProgressBar progressBarImage = (ProgressBar) convertView.findViewById(R.id.progressbar_image);
        progressBarImage.setVisibility(View.GONE);

        return convertView;
    }

}
