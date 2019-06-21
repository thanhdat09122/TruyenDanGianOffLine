package com.example.mypc.truyenoffline.activity;

import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import com.example.mypc.truyenoffline.R;
import com.example.mypc.truyenoffline.adapter.PageStoryAdapter;
import com.example.mypc.truyenoffline.common.Constant;
import com.example.mypc.truyenoffline.database.MyDatabase;
import com.example.mypc.truyenoffline.database.preferences.MyPreferences;
import com.example.mypc.truyenoffline.entity.Story;
import com.example.mypc.truyenoffline.utils.Events;
import com.example.mypc.truyenoffline.utils.GlobalBus;
import com.squareup.otto.Bus;

import java.util.ArrayList;
import java.util.List;

public class ContentStoryActivity extends AppCompatActivity {
    Story mStory;
    Toolbar mToolBar;
    private ViewPager mViewPager;
    private List<Story> mStories = new ArrayList<>();
    private PageStoryAdapter mPageStoreAdapter;
    private int textSize;
    private MyDatabase myDatabase;
    private MenuItem itemFavorite;
    MyPreferences myPreferences;
    private int mCurrentPos = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content_story);
        getDataFromIntent();
        initToolBar();
        initViews();
    }

    private void initViews() {
        mViewPager = findViewById(R.id.viewPager);
        mPageStoreAdapter = new PageStoryAdapter(ContentStoryActivity.this, mStories);

        mViewPager.setAdapter(mPageStoreAdapter);
        mViewPager.setCurrentItem(mStory.getId() - 1);
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                mCurrentPos = position;
                getSupportActionBar().setTitle(mStories.get(position).getTitle());
                mPageStoreAdapter.setAutoScroll();
                myPreferences.setReadContinues(mStories.get(position));
                myDatabase.open();
                if (myDatabase.kiemtraTitle(mStories.get(position).getTitle())) {
                    itemFavorite.setIcon(R.drawable.ic_fav_1);
                } else {
                    itemFavorite.setIcon(R.drawable.ic_un_fav_1);
                }
                myDatabase.close();

            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });


         textSize = myPreferences.getTextSize();
    }

    private void getDataFromIntent() {
        myDatabase = new MyDatabase(getApplication());
        myPreferences = MyPreferences.getInstance(getApplicationContext());
        mStory = new Story();
        Intent intent = getIntent();
        if(intent != null) {
            mStory = intent.getParcelableExtra("content_story");
            myPreferences.setReadContinues(mStory);
            mStories = intent.getParcelableArrayListExtra("stores");
        }
    }

    private void initToolBar() {
        mToolBar = findViewById(R.id.toolbar);
        setSupportActionBar(mToolBar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(mStory.getTitle());
        mToolBar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_action, menu);
        itemFavorite = menu.findItem(R.id.favorite);
        myDatabase.open();
        if (myDatabase.kiemtraTitle(mStory.getTitle())) {
            itemFavorite.setIcon(R.drawable.ic_fav_1);
        } else {
            itemFavorite.setIcon(R.drawable.ic_un_fav_1);
        }
        myDatabase.close();
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.zoomIn:
                zoomInText();
                myPreferences.setTextSize(textSize);
                break;
            case R.id.zoomOut:
                zoomOutText();
                myPreferences.setTextSize(textSize);
                break;
            case R.id.favorite:
                myDatabase.open();
                if (myDatabase.kiemtraTitle(mStories.get(mViewPager.getCurrentItem()).getTitle())) {
                    deleteDialog();
                } else {
                    boolean isAdd  = addToFavorite(mStories.get(mViewPager.getCurrentItem()));
                    if (isAdd) {
                        itemFavorite.setIcon(R.drawable.ic_fav_1);
                    }
                }
                myDatabase.close();

                break;

        }
        return super.onOptionsItemSelected(item);
    }

    private void deleteDialog() {
        final Dialog dialog = new Dialog(ContentStoryActivity.this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.custom_delete_dialog);
        dialog.setCanceledOnTouchOutside(false);

        Button btnDelete = dialog.findViewById(R.id.btnDelete);
        Button btnExit   = dialog.findViewById(R.id.btnExit);
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteFromFavorite(mStories.get(mCurrentPos).getId());
                sendMessageToFragment(mCurrentPos);
                dialog.dismiss();
                itemFavorite.setIcon(R.drawable.ic_un_fav_1);
            }
        });
        btnExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    private void sendMessageToFragment(int pos) {
        Events.ActivityFragmentMessage activityFragmentMessageEvent = new Events.ActivityFragmentMessage(pos);
        GlobalBus.getsBus().post(activityFragmentMessageEvent);
    }

    private void deleteFromFavorite(int id) {
        myDatabase.open();
        myDatabase.deleteFavorite(id);
        myDatabase.close();
    }

    private boolean addToFavorite(Story story) {
        long isAdd = 0;
        myDatabase.open();
        isAdd = myDatabase.favorite(story);
        myDatabase.close();

        return isAdd > 0;
    }

    private void zoomOutText() {
        if (textSize > Constant.MIN_TEXT) {
            textSize--;
            changeTextSize();
        }
    }

    private void zoomInText() {
        if (textSize < Constant.MAX_TEXT) {
            textSize++;
            changeTextSize();
        }
    }

    private void changeTextSize() {
        TextView textView = mViewPager.getRootView().findViewWithTag(mViewPager.getCurrentItem());
        textView.setTextSize(textSize);
    }

}
