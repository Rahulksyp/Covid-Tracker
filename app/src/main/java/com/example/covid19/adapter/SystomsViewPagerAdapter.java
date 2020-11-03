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

public class SystomsViewPagerAdapter extends PagerAdapter {

    private Context context;
    private LayoutInflater layoutInflater;
    private Integer [] images = {R.drawable.ic_cough,R.drawable.ic_fever,R.drawable.ic_headache,R.drawable.ic_runny_nose,R.drawable.ic_sore_throat,R.drawable.ic_weakness};
//    private Integer [] description ={R.string.mask_card_description,R.string.handwash_card_description,R.string.socialdistancing_card_description,R.string.coverwhilesneezing_card_description,R.string.stayhome_card_description};
    private String [] titile = {"Cough","Fever","Headache","Runny Nose","Sore Throat","Weakness"};
    public SystomsViewPagerAdapter(Context context) {
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
        View view = layoutInflater.inflate(R.layout.symtoms_custom_layout, null);
        ImageView imageView = view.findViewById(R.id.symtomImageview);
        TextView tvDes = view.findViewById(R.id.preventionDescription);
        TextView tvtitle = view.findViewById(R.id.symtomTitile);
        imageView.setImageResource(images[position]);
//        tvDes.setText(description[position]);
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
