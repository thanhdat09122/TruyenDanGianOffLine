package com.example.mypc.truyenoffline.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import com.example.mypc.truyenoffline.R;
import com.example.mypc.truyenoffline.adapter.ListStoryAdapter;
import com.example.mypc.truyenoffline.database.MyDatabase;
import com.example.mypc.truyenoffline.entity.Story;
import com.example.mypc.truyenoffline.entity.Total;

import java.util.ArrayList;
import java.util.List;

public class ListStoryActivity extends AppCompatActivity {

    private Total mTotal;
    private Toolbar mToolbar;
    private RecyclerView mRecyclerView;
    private ListStoryAdapter mListStoryAdapter;
    private List<Story> mStorys = new ArrayList<>();
    private MyDatabase mDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_story);
        getDataFromIntent();
        initToolBar();
        initViews();
    }

    private void getDataFromTruyen(final int id) {
        mDatabase = new MyDatabase(getApplicationContext());
        mDatabase.open();
        mStorys = mDatabase.GetDataTruyen(id);
        mDatabase.close();

    }

    private void getDataFromIntent() {
        mTotal = new Total();
        Intent intent = getIntent();
        if (intent != null) {
            mTotal = (Total) intent.getSerializableExtra("list_store");
            getDataFromTruyen(mTotal.getId());
        }
    }

    private void initViews() {
        mRecyclerView = findViewById(R.id.recycleListStory);

        LinearLayoutManager layoutManager = new LinearLayoutManager(ListStoryActivity.this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(layoutManager);

        mListStoryAdapter = new ListStoryAdapter(ListStoryActivity.this, (ArrayList<Story>) mStorys);
        mRecyclerView.setAdapter(mListStoryAdapter);
    }

    private void initToolBar() {
        mToolbar = findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(mTotal.getTitle());
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
