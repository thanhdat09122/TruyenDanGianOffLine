package com.example.mypc.truyenoffline.database.preferences;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.mypc.truyenoffline.common.Constant;
import com.example.mypc.truyenoffline.entity.Story;

public class MyPreferences {
    private static final String NAME_PRES = "preferences_contious";
    private static final String KEY_TITLE = "title";
    private static final String KEY_CONTENT = "content";
    private static final String KEY_TEXT_SIZE = "size";
    private static final String KEY_TEXT_FONTS = "fonts";
    private static final String KEY_SCROLL = "scroll";
    private static final String KEY_POSITION = "1";

    private SharedPreferences mSharedPreferences;
    private static SharedPreferences.Editor mEditor;

    private static MyPreferences myPreferences;

    public MyPreferences(Context context) {
        mSharedPreferences = context.getSharedPreferences(NAME_PRES, Context.MODE_PRIVATE);
        mEditor = mSharedPreferences.edit();
    }

    public static synchronized MyPreferences getInstance(Context context) {
        if (myPreferences == null) {
            myPreferences = new MyPreferences(context);
        }
        return myPreferences;
    }

    public void setReadContinues(Story story) {
        mEditor.putString(KEY_TITLE, story.getTitle());
        mEditor.putString(KEY_CONTENT, story.getContent());
        mEditor.commit();
    }

    public Story getReadContinues() {
        Story story = new Story();
        story.setTitle(mSharedPreferences.getString(KEY_TITLE, ""));
        story.setContent(mSharedPreferences.getString(KEY_CONTENT, ""));

        return story;
    }

    public void setTextSize(int size) {
        mEditor.putInt(KEY_TEXT_SIZE, size);
        mEditor.commit();
    }

    public int getTextSize() {
        return mSharedPreferences.getInt(KEY_TEXT_SIZE, Constant.MIN_TEXT);
    }

    public void setTextFonts(String fonts) {
        mEditor.putString(KEY_TEXT_FONTS, fonts);
        mEditor.commit();
    }

    public String getTextFonts() {
        return mSharedPreferences.getString(KEY_TEXT_FONTS, Constant.FONTS);
    }

    public boolean getAutoScroll() {
        return mSharedPreferences.getBoolean(KEY_SCROLL, false);
    }

    public void setAutoScroll(boolean scroll) {
        mEditor.putBoolean(KEY_SCROLL, scroll);
        mEditor.commit();
    }

    public void setFontsPosition(int position) {
        mEditor.putInt(KEY_POSITION, position);
        mEditor.commit();
    }

    public int getFontsPosition() {
        return mSharedPreferences.getInt(KEY_POSITION, Constant.POSITION);
    }
}
