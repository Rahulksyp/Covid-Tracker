package com.example.covid19.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.covid19.R;
import com.example.covid19.adapter.PreventionViewPagerAdapter;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;

import java.util.Timer;
import java.util.TimerTask;

public class PreventionActivity extends AppCompatActivity {

    private ViewPager viewPager;
    private LinearLayout sliderDotspanel;
    private int dotscount;
    private ImageView[] dots;
    private ExtendedFloatingActionButton trackerFab, essentials_fab;
    private ExtendedFloatingActionButton prevention_fab, symptoms_fab,about_fab;
    private boolean isOpen = false;
    int currentPage = 0;
    Timer timer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prevention);

        viewPager = (ViewPager) findViewById(R.id.pViewPager);
        sliderDotspanel = (LinearLayout) findViewById(R.id.lin);
        PreventionViewPagerAdapter viewPagerAdapter = new PreventionViewPagerAdapter(this);
        viewPager.setAdapter(viewPagerAdapter);
        dotscount = viewPagerAdapter.getCount();
        dots = new ImageView[dotscount];

        trackerFab = findViewById(R.id.Tracker_fab);
//        essentials_fab = findViewById(R.id.Essentials_fab);
        prevention_fab = findViewById(R.id.Prevention_fab);
        symptoms_fab = findViewById(R.id.Symptoms_fab);
        about_fab = findViewById(R.id.About_fab);

        fabButtonFunction();



        if (dots!=null) {
            pagerFun();
        }


    }

    private void fabButtonFunction() {
        final Animation fabOpen = AnimationUtils.loadAnimation(
                this,
                R.anim.fab_open
        );
        final Animation fabClose = AnimationUtils.loadAnimation(
                this,
                R.anim.fab_close
        );
        final Animation fabRClockwise = AnimationUtils.loadAnimation(
                this,
                R.anim.rotate_clockwise
        );
        final Animation fabRAntiClockwise = AnimationUtils.loadAnimation(
                this,
                R.anim.rotate_anticlockwise
        );

        prevention_fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isOpen){
                    symptoms_fab.startAnimation(fabClose);
//                    essentials_fab.startAnimation(fabClose);
                    trackerFab.startAnimation(fabClose);
                    about_fab.startAnimation(fabClose);


                    //visibility//
                    symptoms_fab.setVisibility(View.INVISIBLE);
//                    essentials_fab.setVisibility(View.INVISIBLE);
                    trackerFab.setVisibility(View.INVISIBLE);
                    about_fab.setVisibility(View.INVISIBLE);

                    //clickable
                    symptoms_fab.setClickable(false);
//                    essentials_fab.setClickable(false);
                    trackerFab.setClickable(false);
                    about_fab.setClickable(false);

                    //disabling when fab close
                    symptoms_fab.setEnabled(false);
//                    essentials_fab.setEnabled(false);
                    trackerFab.setEnabled(false);
                    about_fab.setEnabled(false);


                    prevention_fab.startAnimation(fabRClockwise);
                    isOpen = false;
                }else {
                    symptoms_fab.startAnimation(fabOpen);
//                    essentials_fab.startAnimation(fabOpen);
                    prevention_fab.startAnimation(fabOpen);
                    trackerFab.startAnimation(fabOpen);
                    about_fab.startAnimation(fabOpen);

                    //visibility//
                    symptoms_fab.setVisibility(View.VISIBLE);
//                    essentials_fab.setVisibility(View.VISIBLE);
                    trackerFab.setVisibility(View.VISIBLE);
                    about_fab.setVisibility(View.VISIBLE);

                    //clickable
                    symptoms_fab.setClickable(true);
//                    essentials_fab.setClickable(true);
                    trackerFab.setClickable(true);
                    about_fab.setClickable(true);

                    //enbling when fab close
                    symptoms_fab.setEnabled(true);
//                    essentials_fab.setEnabled(true);
                    trackerFab.setEnabled(true);
                    about_fab.setEnabled(true);


                    isOpen = true;
                }
            }
        });

        symptoms_fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent symtoms = new Intent(PreventionActivity.this,SymtomsActivity.class);
                startActivity(symtoms);
                finish();
            }
        });

//        essentials_fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent esse = new Intent(PreventionActivity.this,EssentialActivity.class);
//                startActivity(esse);
//            }
//        });

        trackerFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent tra = new Intent(PreventionActivity.this,MainActivity.class);
                startActivity(tra);
                finish();
            }
        });
        about_fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent tra = new Intent(PreventionActivity.this,AboutCovidActivity.class);
                startActivity(tra);
                finish();
            }
        });
    }


    private void pagerFun() {

        for(int i = 0; i < dotscount; i++) {

            dots[i] = new ImageView(this);
            dots[i].setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.dark_dot));

            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);

            params.setMargins(8, 0, 8, 0);

            sliderDotspanel.addView(dots[i], params);
        }
        dots[0].setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.glowing_dot));

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

                for (int i = 0; i < dotscount; i++) {
                    dots[i].setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.dark_dot));
                }
                dots[position].setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.glowing_dot));

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });


        timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (currentPage == dotscount+1 - 1) {
                            currentPage = 0;
                        }
                        viewPager.setCurrentItem(currentPage++, true);
                    }
                });
            }
        }, 300, 3000);

    }




}