package com.pesan.film3.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.provider.Settings;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toolbar;
import android.widget.VideoView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.pesan.film3.R;
import com.pesan.film3.fragment.MovieShowFragment;
import com.pesan.film3.fragment.TVShowFragment;



public class MainActivity extends AppCompatActivity {
//    //splas
//    private int waktu_loading=4000;
//    //4000=4 detik

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter (getSupportFragmentManager ());
        viewPagerAdapter.addFragement (new MovieShowFragment (),getResources ().getString (R.string.movie));
        viewPagerAdapter.addFragement (new TVShowFragment (),getResources ().getString (R.string.tv));
        ViewPager viewPager = findViewById (R.id.view_pager);
        viewPager.setAdapter (viewPagerAdapter);
        TabLayout tabs = findViewById (R.id.tabs);
        tabs.setupWithViewPager (viewPager);



    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_change_settings){
            Intent mIntent = new Intent (Settings.ACTION_LOCALE_SETTINGS);
            startActivity(mIntent);
        }
        return super.onOptionsItemSelected(item);
    }
    //splas
    private int waktu_loading=4000;
    //4000=4 detik

}