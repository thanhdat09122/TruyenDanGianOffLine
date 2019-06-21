package com.example.mypc.truyenoffline.adapter;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.support.v4.view.PagerAdapter;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.example.mypc.truyenoffline.R;
import com.example.mypc.truyenoffline.common.Constant;
import com.example.mypc.truyenoffline.database.preferences.MyPreferences;
import com.example.mypc.truyenoffline.entity.Story;
import com.example.mypc.truyenoffline.utils.AssetFontUtil;
import com.example.mypc.truyenoffline.views.AutoScrollingText;

import java.util.List;

public class PageStoryAdapter extends PagerAdapter {
    List<Story> stories;
    Context context;
    MyPreferences sharedPreferences;
    Typeface typeface;
    AutoScrollingText autoScrollingText;

    public PageStoryAdapter(Context context, List<Story> stories) {
        this.stories = stories;
        this.context = context;
        sharedPreferences = MyPreferences.getInstance(context);
        typeface = AssetFontUtil.getTyface(context, sharedPreferences.getTextFonts());
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
        autoScrollingText = (AutoScrollingText) view.findViewById(R.id.txtContent);
//        ScrollView scrollView = view.findViewById(R.id.scrollView);
//        scrollView.fullScroll(View.FOCUS_DOWN);
        autoScrollingText.setText(story.getContent());
        autoScrollingText.setTextSize(sharedPreferences.getTextSize());
        autoScrollingText.setTag(position);
        autoScrollingText.setTypeface(typeface);
        container.addView(view);
        return view;
    }

    public void setAutoScroll() {
        autoScrollingText.scroll();
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
