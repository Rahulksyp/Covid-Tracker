package com.example.covid19.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.example.covid19.R;

public class AboutCovidViewPagerAdapter extends PagerAdapter {

    private Context context;
    private LayoutInflater layoutInflater;
//    private Integer [] images = {R.drawable.ic_cough,R.drawable.ic_fever,R.drawable.ic_headache,R.drawable.ic_runny_nose,R.drawable.ic_sore_throat,R.drawable.ic_weakness};
    private Integer [] description ={R.string.what_is_covid,R.string.how_to_protect_yourself_from_covid19,R.string.cause_of_spread,R.string.treatment_for_covid};
    private String [] titile = {"What is Covid-19?","How to protect your from Covid-19?","What is the Couse of spread of Covid-19?","What is the treatment for covid-19?"};
    public AboutCovidViewPagerAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return description.length;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, final int position) {

        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.about_covid_custom_layout, null);
        TextView tvDes = view.findViewById(R.id.aboutcodinDes);
        TextView tvtitle = view.findViewById(R.id.aboutCoidTitile);
        tvDes.setText(description[position]);
        tvtitle.setText(titile[position]);


        ViewPager vp = (ViewPager) container;
        vp.addView(view, 0);
        return view;

    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {

        ViewPager vp = (ViewPager) container;
        View view = (View) object;
        vp.removeView(view);

    }
}
