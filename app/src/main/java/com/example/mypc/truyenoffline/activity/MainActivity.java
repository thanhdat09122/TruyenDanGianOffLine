package com.example.mypc.truyenoffline.activity;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import com.example.mypc.truyenoffline.R;
import com.example.mypc.truyenoffline.database.DatabaseHelper;
import com.example.mypc.truyenoffline.fragment.FavoriteFragment;
import com.example.mypc.truyenoffline.fragment.HomeFragment;
import com.example.mypc.truyenoffline.navigator.DrawerFragment;

public class MainActivity extends AppCompatActivity implements DrawerFragment.INavigationCallBack {

    Toolbar toolbar;
    HomeFragment homeFragment;
    FavoriteFragment favoriteFragment;
    DrawerLayout mDrawerLayout;
    DrawerFragment mDrawerFragment;
    private DatabaseHelper mDatabaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initDatabase();
        initViews();
        initListener();
        initHomeFragment();
    }

    private void initDatabase() {
        mDatabaseHelper = DatabaseHelper.getInstance(MainActivity.this.getApplicationContext());
    }

    private void initListener() {
        // listner view click, callback
    }

    private void initViews() {
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        toolbar.setNavigationIcon(R.drawable.ic_menu);

        mDrawerLayout = findViewById(R.id.drawerLayout);
        mDrawerFragment = (DrawerFragment) getSupportFragmentManager().findFragmentById(R.id.drawerFragment);
        mDrawerFragment.setDrawer(mDrawerLayout, toolbar);
        mDrawerFragment.setNavigateCallBack(this);
    }

    private void initHomeFragment() {
        homeFragment = new HomeFragment();
        replaceFragment(homeFragment);
    }

    private void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.container, fragment);
        fragmentTransaction.commit();
    }

    @Override
    public void onClickItem(int position) {
        mDrawerLayout.closeDrawers();
        switch (position) {
            case 0:
                favoriteFragment = new FavoriteFragment();
                replaceFragment(favoriteFragment);
                break;
            case 4:
                homeFragment = new HomeFragment();
                replaceFragment(homeFragment);
                break;
            default:
                replaceFragment(homeFragment);
                break;
        }
        Log.d("TAGGG", position + "");
    }
}
