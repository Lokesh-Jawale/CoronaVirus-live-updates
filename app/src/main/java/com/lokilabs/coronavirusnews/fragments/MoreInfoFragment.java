package com.lokilabs.coronavirusnews.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.lokilabs.coronavirusnews.R;

public class MoreInfoFragment extends Fragment {

    Button button1, button2;
    ImageView imageView1, imageView2;
    int x = 0, y = 0;
    View view;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.more_info, container, false);

        button1 = (Button) view.findViewById(R.id.button1);
        button2 = (Button) view.findViewById(R.id.button2);
        imageView1 = (ImageView) view.findViewById(R.id.symptoms_image);
        imageView2 = (ImageView) view.findViewById(R.id.preventions_image);

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ViewGroup.LayoutParams layoutParams = imageView1.getLayoutParams();
                if (x == 0) {
                    imageView1.setImageResource(R.drawable.symptoms);
                    layoutParams.height = 1300;
                    imageView1.setLayoutParams(layoutParams);
                    //for other image to vanish
                    ViewGroup.LayoutParams params = imageView2.getLayoutParams();
                    params.height = 0;
                    imageView2.setLayoutParams(params);
                    y = 0;
                    x = 1;
                } else {
                    layoutParams.height = 0;
                    imageView1.setLayoutParams(layoutParams);
                    x = 0;
                }
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ViewGroup.LayoutParams layoutParams = imageView2.getLayoutParams();
                if (y == 0) {
                    imageView2.setImageResource(R.drawable.preventions);
                    layoutParams.height = 1300;
                    imageView2.setLayoutParams(layoutParams);
                    // for other image to vanish
                    ViewGroup.LayoutParams params = imageView1.getLayoutParams();
                    params.height = 0;
                    imageView1.setLayoutParams(params);
                    x = 0;
                    y = 1;
                } else {
                    layoutParams.height = 0;
                    imageView2.setLayoutParams(layoutParams);
                    y = 0;
                }
            }
        });

        imageView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ViewGroup.LayoutParams layoutParams = imageView1.getLayoutParams();
                imageView1.setImageResource(R.drawable.preventions);
                layoutParams.height = 0;
                imageView1.setLayoutParams(layoutParams);
            }
        });

        imageView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ViewGroup.LayoutParams layoutParams = imageView2.getLayoutParams();
                imageView2.setImageResource(R.drawable.preventions);
                layoutParams.height = 0;
                imageView2.setLayoutParams(layoutParams);
            }
        });

        return view;
    }
}

