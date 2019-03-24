package com.example.mypc.truyenoffline.activity;

import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.mypc.truyenoffline.R;
import com.example.mypc.truyenoffline.adapter.PageStoreAdapter;
import com.example.mypc.truyenoffline.entity.Story;

import java.util.ArrayList;
import java.util.List;

public class ContentStoryActivity extends AppCompatActivity {
    Story mStory;
    Toolbar mToolBar;
    private ViewPager mViewPager;
    private List<Story> mStories = new ArrayList<>();
    private PageStoreAdapter mPageStoreAdapter;
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
        mPageStoreAdapter = new PageStoreAdapter(ContentStoryActivity.this, mStories);

        mViewPager.setAdapter(mPageStoreAdapter);
        mViewPager.setCurrentItem(mStory.getId() - 1);
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                getSupportActionBar().setTitle(mStories.get(position).getTitle());
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });

    }

    private void getDataFromIntent() {
        mStory = new Story();
        Intent intent = getIntent();
        if(intent != null) {
            mStory = intent.getParcelableExtra("content_story");
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
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.zoomIn:
                break;
            case R.id.zoomOut:
                break;
            case R.id.favorite:
                break;

        }
        return super.onOptionsItemSelected(item);
    }
}
