package com.example.mypc.truyenoffline.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.mypc.truyenoffline.R;
import com.example.mypc.truyenoffline.entity.Story;

import java.util.List;

public class PageStoreAdapter extends PagerAdapter {
    List<Story> stories;
    Context context;

    public PageStoreAdapter(Context context, List<Story> stories) {
        this.stories = stories;
        this.context = context;
    }

    // This is method used to set weight of page - default is 1.0f(full screen)
//    @Override
//    public float getPageWidth(int position) {
//        return 0.5f;
//    }

    @Override
    public int getCount() {
        return stories.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object o) {
        return view == o;//true de len nhau
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        Story story = stories.get(position);
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.page_item_layout, container, false);
        TextView tvTest = (TextView) view.findViewById(R.id.txtContent);
        tvTest.setText(story.getContent());
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((LinearLayout) object);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        // This method to set text for PagerTabStrip if have using
        return null;
    }
}
