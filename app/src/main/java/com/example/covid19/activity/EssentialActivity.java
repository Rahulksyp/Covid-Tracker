package com.example.covid19.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.covid19.R;
import com.example.covid19.adapter.EssentialViewPagerAdapter;
import com.example.covid19.adapter.SystomsViewPagerAdapter;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;

public class EssentialActivity extends AppCompatActivity {

    private ViewPager viewPager;
    private LinearLayout sliderDotspanel;
    private int dotscount;
    private ImageView[] dots;
    private ExtendedFloatingActionButton trackerFab, essentials_fab;
    private ExtendedFloatingActionButton prevention_fab, symptoms_fab,about_fab;
    private boolean isOpen = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_essential);

        viewPager = (ViewPager) findViewById(R.id.pViewPager);
        sliderDotspanel = (LinearLayout) findViewById(R.id.lin);
        EssentialViewPagerAdapter viewPagerAdapter = new EssentialViewPagerAdapter(this);
        viewPager.setAdapter(viewPagerAdapter);
        dotscount = viewPagerAdapter.getCount();
        dots = new ImageView[dotscount];

        trackerFab = findViewById(R.id.Tracker_fab);
        essentials_fab = findViewById(R.id.Essentials_fab);
        prevention_fab = findViewById(R.id.Prevention_fab);
        symptoms_fab = findViewById(R.id.Symptoms_fab);
        about_fab = findViewById(R.id.About_fab);

        if (dots!=null) {
            pagerFun();
        }
        fabButtonFunction();
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

        essentials_fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isOpen){
                    prevention_fab.startAnimation(fabClose);
                    symptoms_fab.startAnimation(fabClose);
                    trackerFab.startAnimation(fabClose);
                    about_fab.startAnimation(fabClose);


                    //visibility//
                    prevention_fab.setVisibility(View.INVISIBLE);
                    symptoms_fab.setVisibility(View.INVISIBLE);
                    trackerFab.setVisibility(View.INVISIBLE);
                    about_fab.setVisibility(View.INVISIBLE);

                    //clickable
                    prevention_fab.setClickable(false);
                    symptoms_fab.setClickable(false);
                    trackerFab.setClickable(false);
                    about_fab.setClickable(false);

                    //disabling when fab close
                    prevention_fab.setEnabled(false);
                    symptoms_fab.setEnabled(false);
                    trackerFab.setEnabled(false);
                    about_fab.setEnabled(false);


                    essentials_fab.startAnimation(fabRClockwise);
                    isOpen = false;
                }else {
                    symptoms_fab.startAnimation(fabOpen);
                    prevention_fab.startAnimation(fabOpen);
                    trackerFab.startAnimation(fabOpen);
                    about_fab.startAnimation(fabOpen);
                    essentials_fab.startAnimation(fabRAntiClockwise);

                    //visibility//
                    prevention_fab.setVisibility(View.VISIBLE);
                    symptoms_fab.setVisibility(View.VISIBLE);
                    trackerFab.setVisibility(View.VISIBLE);
                    about_fab.setVisibility(View.VISIBLE);

                    //clickable
                    prevention_fab.setClickable(true);
                    symptoms_fab.setClickable(true);
                    trackerFab.setClickable(true);
                    about_fab.setClickable(true);

                    //enbling when fab close
                    prevention_fab.setEnabled(true);
                    symptoms_fab.setEnabled(true);
                    trackerFab.setEnabled(true);
                    about_fab.setEnabled(true);

                    isOpen = true;
                }
            }
        });

        prevention_fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent symtoms = new Intent(EssentialActivity.this,PreventionActivity.class);
                startActivity(symtoms);
            }
        });

        symptoms_fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent esse = new Intent(EssentialActivity.this,SymtomsActivity.class);
                startActivity(esse);
            }
        });

        trackerFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent tra = new Intent(EssentialActivity.this,MainActivity.class);
                startActivity(tra);
            }
        });
        about_fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent tra = new Intent(EssentialActivity.this,AboutCovidActivity.class);
                startActivity(tra);
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
    }
}