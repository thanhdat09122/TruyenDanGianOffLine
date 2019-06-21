package com.example.mypc.truyenoffline.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.mypc.truyenoffline.R;
import com.example.mypc.truyenoffline.adapter.HomeListAdapter;
import com.example.mypc.truyenoffline.database.MyDatabase;
import com.example.mypc.truyenoffline.entity.Total;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {
    RecyclerView recyclerView;
    HomeListAdapter homeListAdapter;
    List<Total> mTotals = new ArrayList<>();
    private MyDatabase mDatabase;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View viewRoot = inflater.inflate(R.layout.fragment_home, container, false);
        getDatabaseTotal();
        initViews(viewRoot);
        initListener();
        // hoc e oi
        return viewRoot;
    }

    private void getDatabaseTotal() {
        mDatabase = new MyDatabase(getActivity());
        mDatabase.open();
        mTotals = mDatabase.getDataFromTotal();
        Log.d("TAJJJ", mTotals.size() + " ");
        mDatabase.close();
    }

    private void initListener() {

    }

    private void initViews(View rootView) {
        recyclerView = rootView.findViewById(R.id.recycleViewListStore);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        recyclerView.setLayoutManager(layoutManager);

        // retrofit
        homeListAdapter = new HomeListAdapter(getActivity(), mTotals);
        recyclerView.setAdapter(homeListAdapter);
    }

}
