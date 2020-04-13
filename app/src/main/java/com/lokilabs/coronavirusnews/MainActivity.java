package com.lokilabs.coronavirusnews;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.lokilabs.coronavirusnews.adapters.ViewPagerAdapter;

public class MainActivity extends AppCompatActivity {

    TabLayout tabLayout;
    ViewPager viewPager;

    TextView emptyView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try {
            ConnectivityManager connMgr = (ConnectivityManager)
                    this.getSystemService(Context.CONNECTIVITY_SERVICE);

            // Get details on the currently active default data network
            NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();

            // If there is a network connection, fetch data
            if (networkInfo != null && networkInfo.isConnected()) {

                tabLayout = (TabLayout)findViewById(R.id.tab_layout);
                viewPager = (ViewPager)findViewById(R.id.view_pager);

                tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.ic_poll_black_24dp));
                tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.ic_airplay_black_24dp));
                tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.ic_more_black_24dp));

                ViewPagerAdapter viewPageAdapter = new ViewPagerAdapter(getSupportFragmentManager(), tabLayout.getTabCount());

                viewPager.setAdapter(viewPageAdapter);
                viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

                tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
                    @Override
                    public void onTabSelected(TabLayout.Tab tab) {
                        viewPager.setCurrentItem(tab.getPosition());
                    }

                    @Override
                    public void onTabUnselected(TabLayout.Tab tab) {

                    }

                    @Override
                    public void onTabReselected(TabLayout.Tab tab) {

                    }
                });

            } else {
                noInternetConnection();
            }
        }catch(Exception e){
            e.getMessage();
        }

    }

    public void noInternetConnection(){
        emptyView = (TextView)findViewById(R.id.empty_text);
        emptyView.setText(R.string.no_internet_connection_string);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        //noinspection SwitchStatementWithoutDefaultBranch
        switch (item.getItemId()){
            case R.id.refresh_menu_item:
                finish();
                overridePendingTransition(0, 0);
                startActivity(getIntent());
                overridePendingTransition(0, 0);
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}


