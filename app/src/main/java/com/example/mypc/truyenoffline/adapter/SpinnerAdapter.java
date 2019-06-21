package com.example.mypc.truyenoffline.adapter;

import android.content.Context;
import android.graphics.Typeface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.mypc.truyenoffline.R;
import com.example.mypc.truyenoffline.common.Constant;
import com.example.mypc.truyenoffline.database.preferences.MyPreferences;
import com.example.mypc.truyenoffline.utils.AssetFontUtil;

import java.util.List;

public class SpinnerAdapter extends BaseAdapter {
    Context context;
    List<String> textFont;
    LayoutInflater inflter;
    MyPreferences myPreferences;

    public SpinnerAdapter(Context context, List<String> textFont) {
        this.context = context;
        this.textFont = textFont;
        inflter = (LayoutInflater.from(context));
        myPreferences = MyPreferences.getInstance(context);
    }

    @Override
    public int getCount() {
        return textFont.size();
    }

    @Override
    public Object getItem(int i) {
        return textFont.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = inflter.inflate(R.layout.custom_spinner_items, null);
        TextView names = (TextView) view.findViewById(R.id.txtSpinner);
        names.setText(textFont.get(i).replace(".ttf", ""));
        setFontsText(names, i);
//        myPreferences.setTextFonts(textFont.get(i));
        return view;
    }

    private void setFontsText(TextView tv, int position) {
        Typeface face = Typeface.createFromAsset(context.getAssets(), Constant.PATH_FONTS +"/" + textFont.get(position));
        tv.setTypeface(face);
    }
}
