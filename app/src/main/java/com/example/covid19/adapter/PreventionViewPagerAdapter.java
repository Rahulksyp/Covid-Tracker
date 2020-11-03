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

public class PreventionViewPagerAdapter extends PagerAdapter {

    private Context context;
    private LayoutInflater layoutInflater;
    private Integer [] images = {R.drawable.ic_wear_mask_new,R.drawable.ic_wash_hands_new,R.drawable.ic_social_distancing_new,R.drawable.ic_cover_sneeze_new,R.drawable.ic_stay_home_new};
    private Integer [] description ={R.string.mask_card_description,R.string.handwash_card_description,R.string.socialdistancing_card_description,R.string.coverwhilesneezing_card_description,R.string.stayhome_card_description};
    private Integer [] titile = {R.string.mask_card_title,R.string.handwash_card_title,R.string.socialdistancing_card_title,R.string.coverwhilesneezing_card_title,R.string.stayhome_card_title};
    public PreventionViewPagerAdapter(Context context) {
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
        View view = layoutInflater.inflate(R.layout.prevention_custom_layout, null);
        ImageView imageView = view.findViewById(R.id.preventionImageview);
        TextView tvDes = view.findViewById(R.id.preventionDescription);
        TextView tvtitle = view.findViewById(R.id.prevetionTitle);
        imageView.setImageResource(images[position]);
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
