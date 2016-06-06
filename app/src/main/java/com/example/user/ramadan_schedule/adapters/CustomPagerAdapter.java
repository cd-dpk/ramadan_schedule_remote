package com.example.user.ramadan_schedule.adapters;

import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;

import com.example.user.ramadan_schedule.datamodels.interfaces.OnPagerViewItemListener;


/**
 * Created by chandradasdipok on 5/29/2016.
 */
public class CustomPagerAdapter extends PagerAdapter {

    OnPagerViewItemListener onPagerViewItemListener;
    int size;

    public CustomPagerAdapter(OnPagerViewItemListener onPagerViewItemListener, int size) {
        this.onPagerViewItemListener = onPagerViewItemListener;
        this.size = size;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        return onPagerViewItemListener.objectInitiateView(container, position);
    }

    @Override
    public int getCount() {
        return size;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == ((View) object);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        ((ViewPager)container).removeView((View)(object));
    }
}
