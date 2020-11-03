package com.example.covid19.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.airbnb.lottie.LottieAnimationView;
import com.example.covid19.R;

public class EssentialViewPagerAdapter extends PagerAdapter {

    private Context context;
    private LayoutInflater layoutInflater;
    private Integer [] images = {R.raw.covid1_19,R.raw.hand_sanitizer,R.raw.hands_regularly,R.raw.covid_19};
//    private Integer [] description ={R.string.mask_card_description,R.string.handwash_card_description,R.string.socialdistancing_card_description,R.string.coverwhilesneezing_card_description,R.string.stayhome_card_description};
    private String [] titile = {"Cough","Fever","Headache","Runny Nose","Sore Throat","Weakness"};
    public EssentialViewPagerAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return images.length;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, final int position) {

        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.essential_custom_layout, null);
        LottieAnimationView imageView = view.findViewById(R.id.animationView);
        TextView tvDes = view.findViewById(R.id.preventionDescription);
        TextView tvtitle = view.findViewById(R.id.symtomTitile);

        imageView.setAnimation(images[position]);



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
