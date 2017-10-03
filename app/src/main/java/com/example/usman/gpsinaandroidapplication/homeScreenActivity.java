package com.example.usman.gpsinaandroidapplication;


import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import java.util.TimerTask;
import java.util.Timer;
/**
 * Created by Usman on 9/29/17.
 */

public class homeScreenActivity extends AppCompatActivity {

    ViewPager viewPager;
    viewPagerAdapter adapter;

    private String[] images = {
      "https://static.pexels.com/photos/56866/garden-rose-red-pink-56866.jpeg",
            "https://static.pexels.com/photos/45853/grey-crowned-crane-bird-crane-animal-45853.jpeg",
           "https://static.pexels.com/photos/67636/rose-blue-flower-rose-blooms-67636.jpeg"

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_screen);

        viewPager = (ViewPager)findViewById(R.id.homeViewPager);
        adapter = new viewPagerAdapter(homeScreenActivity.this,images);
        viewPager.setAdapter(adapter);

        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new MyTimerTask(), 3000, 4000);


    }


    class MyTimerTask extends TimerTask {

        @Override
        public void run() {
           homeScreenActivity.this.runOnUiThread(new Runnable() {
               @Override
               public void run() {
                   if (viewPager.getCurrentItem() == 0){
                       viewPager.setCurrentItem(1);
                   }else if (viewPager.getCurrentItem() == 1){
                       viewPager.setCurrentItem(2);
                   }else {
                       viewPager.setCurrentItem(0);
                   }
               }
           });
        }

    }

}
