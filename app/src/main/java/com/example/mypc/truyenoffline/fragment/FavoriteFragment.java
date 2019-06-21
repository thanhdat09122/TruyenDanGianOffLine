package com.example.mypc.truyenoffline.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mypc.truyenoffline.R;
import com.example.mypc.truyenoffline.adapter.FavoriteListAdapter;
import com.example.mypc.truyenoffline.database.MyDatabase;
import com.example.mypc.truyenoffline.entity.Story;
import com.example.mypc.truyenoffline.utils.Events;
import com.example.mypc.truyenoffline.utils.GlobalBus;
import com.squareup.otto.Bus;
import com.squareup.otto.Subscribe;
import com.squareup.otto.ThreadEnforcer;

import java.util.ArrayList;
import java.util.List;

public class FavoriteFragment extends Fragment {
    RecyclerView mRecyclerView;
    FavoriteListAdapter mFavoriteListAdapter;
    List<Story> mStories = new ArrayList<>();
    private MyDatabase myDatabase;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_favorite, container, false);
        getDataFromFavorite();
        initViews(view);
        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        GlobalBus.getsBus().register(this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        GlobalBus.getsBus().unregister(this);
    }

    private void getDataFromFavorite() {
        if (getActivity() != null) {
            myDatabase = new MyDatabase(getActivity().getApplicationContext());
            myDatabase.open();
            mStories = myDatabase.GetDataTruyen(15);
            myDatabase.close();
        }
    }

    private void initViews(View view) {
        mRecyclerView = view.findViewById(R.id.recycleViewListFavorite);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        mRecyclerView.setLayoutManager(layoutManager);

        // retrofit
        mFavoriteListAdapter = new FavoriteListAdapter(getActivity(), mStories);
        mRecyclerView.setAdapter(mFavoriteListAdapter);
    }

    @Subscribe
    public void getMessage(Events.ActivityFragmentMessage message) {
        mStories.remove(message.getMessage());
        mFavoriteListAdapter.notifyDataSetChanged();
    }
}
