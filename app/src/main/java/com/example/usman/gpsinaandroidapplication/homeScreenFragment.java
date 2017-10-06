package com.example.usman.gpsinaandroidapplication;

import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import java.util.TimerTask;
import java.util.Timer;
import android.support.v7.widget.AppCompatEditText;

/**
 * Created by Usman on 9/29/17.
 */

public class homeScreenFragment extends Fragment {

    int currentPage = 0;
    Timer timer;
    final long DELAY_MS = 1000;//delay in milliseconds before task is to be executed
    final long PERIOD_MS = 5000; //

    ViewPager viewPager;
    viewPagerAdapter adapter;

    EditText vehicleNo, trackerNo;
    Button addDevice;


    View view;

    private String[] images = {
      "https://static.pexels.com/photos/56866/garden-rose-red-pink-56866.jpeg",
            "https://static.pexels.com/photos/45853/grey-crowned-crane-bird-crane-animal-45853.jpeg",
           "https://static.pexels.com/photos/67636/rose-blue-flower-rose-blooms-67636.jpeg"

    };

    public static homeScreenFragment newInstance() {
        homeScreenFragment fragment = new homeScreenFragment();
        return fragment;
    }


    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        viewPager = (ViewPager)view.findViewById(R.id.homeViewPager);

        vehicleNo = (EditText)view.findViewById(R.id.editText_VehicleNo);
        trackerNo = (EditText)view.findViewById(R.id.editText_TrackerNo);
        addDevice = (Button)view.findViewById(R.id.addDevice_Button);

        adapter = new viewPagerAdapter((fragmentMainActivity) getActivity(), images);

        viewPager.setAdapter(adapter);

      /* After setting the adapter use the timer */

        final Handler handler = new Handler();
        final Runnable Update = new Runnable() {
            public void run() {
                if (currentPage == images.length) {
                    currentPage = 0;
                }
                viewPager.setCurrentItem(currentPage++, true);

            }
        };

        timer = new Timer(); // This will create a new Thread
        timer .schedule(new TimerTask() { // task to be scheduled

            @Override
            public void run() {
                handler.post(Update);

            }
        }, DELAY_MS, PERIOD_MS);



    }

    /*
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    } */

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.home_screen, container, false);

        return view;
    }

    public void editTextVehicleNo(View v){

        vehicleNo.setCursorVisible(true);
        trackerNo.setCursorVisible(false);

    }

    public void editTextTrackerNo(View v){

        trackerNo.setCursorVisible(true);
        vehicleNo.setCursorVisible(false);
    }


}
