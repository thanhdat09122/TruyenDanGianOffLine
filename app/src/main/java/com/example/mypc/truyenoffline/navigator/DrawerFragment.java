package com.example.mypc.truyenoffline.navigator;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.mypc.truyenoffline.R;

public class DrawerFragment extends Fragment implements View.OnClickListener {
    DrawerLayout mDrawerLayout;
    ActionBarDrawerToggle actionBarDrawerToggle;
    View containerYeuThich;
    View containerDocTiep;
    View containerCaiDat;
    View containerThongTin;
    ImageView imgHome;

    private INavigationCallBack navigationCallBack;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View viewRoot = inflater.inflate(R.layout.fragment_drawer, container, false);
        initViews(viewRoot);
        initListener();
        return viewRoot;
    }

    private void initListener() {
        containerYeuThich.setOnClickListener(this);
        containerDocTiep.setOnClickListener(this);
        containerCaiDat.setOnClickListener(this);
        containerThongTin.setOnClickListener(this);
        imgHome.setOnClickListener(this);

    }

    private void initViews(View rootView) {
        imgHome = rootView.findViewById(R.id.imgHome);
        containerYeuThich = rootView.findViewById(R.id.containerYeuThich);
        containerDocTiep = rootView.findViewById(R.id.containerDocTiep);
        containerCaiDat = rootView.findViewById(R.id.containerCaiDat);
        containerThongTin = rootView.findViewById(R.id.containerThongTin);
    }

    public void setDrawer(final DrawerLayout drawerLayout, Toolbar toolbar) {
        mDrawerLayout = drawerLayout;
        actionBarDrawerToggle = new ActionBarDrawerToggle(getActivity(), mDrawerLayout, toolbar, R.string.drawer_open, R.string.drawer_close) {
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                Log.d("TAGGG", "opened");
                // todo
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                Log.d("TAGGG", "closed");
                //todo
            }

            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {
                super.onDrawerSlide(drawerView, slideOffset);
                Log.d("TAGGG", "slideOffset " + slideOffset);
                //todo
            }
        };
        mDrawerLayout.post(new Runnable() {
            @Override
            public void run() {
                actionBarDrawerToggle.syncState();
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.containerYeuThich:
                navigationCallBack.onClickItem(0);
                break;
            case R.id.containerDocTiep:
                navigationCallBack.onClickItem(1);
                break;
            case R.id.containerCaiDat:
                navigationCallBack.onClickItem(2);
                break;
            case R.id.containerThongTin:
                navigationCallBack.onClickItem(3);
                break;
            case R.id.imgHome:
                navigationCallBack.onClickItem(4);
                break;
            default:
                navigationCallBack.onClickItem(4);
                break;
        }
    }

    public void setNavigateCallBack(INavigationCallBack navigateCallBack) {
        this.navigationCallBack = navigateCallBack;
    }

    public interface INavigationCallBack {
        void onClickItem(int position);
    }
}
