package com.example.covid19.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.covid19.R;
import com.example.covid19.adapter.StatewiseAdapter;
import com.example.covid19.model.Statewise;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {
    private RequestQueue mQueue;
    private String url = "https://api.covid19india.org/data.json";
    private TextView activeCase;
    private TextView recovercase;
    private TextView confirmCase;
    private TextView deceasedCase;
    private RecyclerView statewiseRecycler;
    private List<Statewise> statewiseList;
    private ExtendedFloatingActionButton trackerFab, essentials_fab;
    private ExtendedFloatingActionButton prevention_fab, symptoms_fab,about_fab;
    private boolean isOpen = false;
    private StatewiseAdapter customAdapter;
    private TextView lastUpdatedTv;
    private SwipeRefreshLayout swipeRefreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        activeCase = findViewById(R.id.activeTv);
        recovercase = findViewById(R.id.recoveredTv);
        confirmCase = findViewById(R.id.confirmedTv);
        deceasedCase = findViewById(R.id.deceasedTv);
        trackerFab = findViewById(R.id.Tracker_fab);
//        essentials_fab = findViewById(R.id.Essentials_fab);
        prevention_fab = findViewById(R.id.Prevention_fab);
        symptoms_fab = findViewById(R.id.Symptoms_fab);
        about_fab = findViewById(R.id.About_fab);
        lastUpdatedTv = findViewById(R.id.lastUpdatedTv);
        swipeRefreshLayout = findViewById(R.id.swipeToRefresh);
        statewiseList = new ArrayList<>();
        statewiseRecycler = findViewById(R.id.recyclerStatewise);
        statewiseRecycler.setNestedScrollingEnabled(true);
        statewiseRecycler.setLayoutManager(new LinearLayoutManager(this));
        getData();
        fabButtonFunction();
        funSwipeToRef();

    }

    @Override
    protected void onResume() {
        super.onResume();
        getData();
    }

    private void funSwipeToRef() {
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getData();
            }
        });
    }

    private void fabButtonFunction() {

        final Animation fabOpen = AnimationUtils.loadAnimation(this, R.anim.fab_open);
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
        trackerFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (isOpen) {
                    symptoms_fab.startAnimation(fabClose);
//                    essentials_fab.startAnimation(fabClose);
                    prevention_fab.startAnimation(fabClose);
                    about_fab.startAnimation(fabClose);

                    //visibility//
                    symptoms_fab.setVisibility(View.INVISIBLE);
//                    essentials_fab.setVisibility(View.INVISIBLE);
                    prevention_fab.setVisibility(View.INVISIBLE);
                    about_fab.setVisibility(View.INVISIBLE);

                    //clickable
                    symptoms_fab.setClickable(false);
//                    essentials_fab.setClickable(false);
                    prevention_fab.setClickable(false);
                    about_fab.setClickable(false);

                    //disabling when fab close
                    symptoms_fab.setEnabled(false);
//                    essentials_fab.setEnabled(false);
                    prevention_fab.setEnabled(false);
                    about_fab.setEnabled(false);

                    trackerFab.startAnimation(fabRClockwise);
                    isOpen = false;
                } else {
                    symptoms_fab.startAnimation(fabOpen);
//                    essentials_fab.startAnimation(fabOpen);
                    prevention_fab.startAnimation(fabOpen);
                    about_fab.startAnimation(fabOpen);
                    trackerFab.startAnimation(fabRAntiClockwise);


                    //visibility//
                    symptoms_fab.setVisibility(View.VISIBLE);
//                    essentials_fab.setVisibility(View.VISIBLE);
                    prevention_fab.setVisibility(View.VISIBLE);
                    about_fab.setVisibility(View.VISIBLE);

                    //clickable
                    symptoms_fab.setClickable(true);
//                    essentials_fab.setClickable(true);
                    prevention_fab.setClickable(true);
                    about_fab.setClickable(true);

                    //enbling when fab close
                    symptoms_fab.setEnabled(true);
//                    essentials_fab.setEnabled(true);
                    prevention_fab.setEnabled(true);
                    about_fab.setEnabled(true);


                    isOpen = true;

                }

            }
        });

        prevention_fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent prevetion = new Intent(MainActivity.this,PreventionActivity.class);
                startActivity(prevetion);
                finish();
            }
        });

        symptoms_fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent symtoms = new Intent(MainActivity.this,SymtomsActivity.class);
                startActivity(symtoms);
                finish();
            }
        });

//        essentials_fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent esse = new Intent(MainActivity.this,EssentialActivity.class);
//                startActivity(esse);
//            }
//        });
        about_fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent esse = new Intent(MainActivity.this,AboutCovidActivity.class);
                startActivity(esse);
                finish();
            }
        });
    }

    private void getData() {
        mQueue = Volley.newRequestQueue(this);
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                swipeRefreshLayout.setRefreshing(false);

                try {
                    JSONArray statewise = response.getJSONArray("statewise");
                    JSONObject data = statewise.getJSONObject(0);
                    activeCase.setText(data.getString("active"));
                    recovercase.setText(data.getString("recovered"));
                    confirmCase.setText(data.getString("confirmed"));
                    deceasedCase.setText(data.getString("deaths"));
                    String lastDate = data.getString("lastupdatedtime");

                    for (int i = 0; i < statewise.length(); i++) {
                        JSONObject country_india = statewise.getJSONObject(i);
                        Statewise statewiseData = new Statewise();
                        statewiseData.setActive(country_india.getString("active"));
                        statewiseData.setConfirmed(country_india.getString("confirmed"));
                        statewiseData.setDeaths(country_india.getString("deaths"));
                        statewiseData.setDeltaconfirmed(country_india.getString("deltaconfirmed"));
                        statewiseData.setDeltadeaths(country_india.getString("deltadeaths"));
                        statewiseData.setDeltarecovered(country_india.getString("deltarecovered"));
                        statewiseData.setLastupdatedtime(country_india.getString("lastupdatedtime"));
                        statewiseData.setMigratedother(country_india.getString("migratedother"));
                        statewiseData.setRecovered(country_india.getString("recovered"));
                        statewiseData.setState(country_india.getString("state"));
                        statewiseData.setStatecode(country_india.getString("statecode"));
                        statewiseData.setStatenotes(country_india.getString("statenotes"));
                        statewiseList.add(statewiseData);


                    }

                    try {
                        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
                        Date date = sdf.parse(lastDate);
                        lastUpdatedTv.setText("Last Updated" + "\n" + getTimeAgo(date));

                    } catch (ParseException e) {
                        e.printStackTrace();
                    }

                    customAdapter = new StatewiseAdapter(getApplicationContext(), statewiseList);
                    statewiseRecycler.setAdapter(customAdapter);

                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });
        mQueue.add(request);
    }

    private String getTimeAgo(Date lastDateUpdate) {
        Date now = new Date();
        long seconds = TimeUnit.MILLISECONDS.toSeconds(now.getTime() - lastDateUpdate.getTime());
        long minutes = TimeUnit.MILLISECONDS.toMinutes(now.getTime() - lastDateUpdate.getTime());
        long hours = TimeUnit.MILLISECONDS.toHours(now.getTime() - lastDateUpdate.getTime());
        long days = TimeUnit.MILLISECONDS.toDays(now.getTime() - lastDateUpdate.getTime());


        if (seconds < 60) {
            return seconds + " seconds ago";
        } else if (minutes < 60) {
            return minutes + " minutes ago";

        } else if (hours < 24) {
            return hours + " hours ago";

        } else {
            return days + " days ago";

        }
    }


}