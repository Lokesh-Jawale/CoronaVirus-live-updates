package com.lokilabs.coronavirusnews.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.lokilabs.coronavirusnews.fragments.LiveUpdateFragment;
import com.lokilabs.coronavirusnews.fragments.MoreInfoFragment;
import com.lokilabs.coronavirusnews.fragments.NewsUpdateFragment;

public class ViewPagerAdapter extends FragmentStatePagerAdapter {

    private int tabNumber;

    public ViewPagerAdapter(FragmentManager fm, int tabNumber){
        super(fm);
        this.tabNumber = tabNumber;
    }

    @Override
    public Fragment getItem(int i) {
        switch (i){
            case 0:
                LiveUpdateFragment liveUpdateFragment = new LiveUpdateFragment();
                return liveUpdateFragment;
            case 1:
                NewsUpdateFragment newsUpdateFragment = new NewsUpdateFragment();
                return newsUpdateFragment;
            case 2:
                MoreInfoFragment moreInfoFragment = new MoreInfoFragment();
                return moreInfoFragment;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return tabNumber;
    }
}
