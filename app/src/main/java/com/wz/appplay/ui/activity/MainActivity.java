package com.wz.appplay.ui.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.wz.appplay.R;
import com.wz.appplay.di.component.AppComponent;
import com.wz.appplay.ui.adapter.ViewPagerAdapter;

import butterknife.BindView;

public class MainActivity extends BaseActivity {

    @BindView(R.id.navigation_view)
    NavigationView mNavigationView;
    @BindView(R.id.drawer_layout)
    DrawerLayout mDrawerLayout;
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.tab_layout)
    TabLayout mTabLayout;
    @BindView(R.id.view_pager)
    ViewPager mViewPager;

    private View mHeadView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public int setLayoutResID() {
        return R.layout.activity_main;
    }

    @Override
    public void setupActivityComponent(AppComponent appComponent) {

    }

    @Override
    public void init() {

        initDrawerLayout();
        initAdapter();
    }

    private void initAdapter() {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        mViewPager.setAdapter(adapter);
        mTabLayout.setupWithViewPager(mViewPager);
    }

    private void initDrawerLayout() {
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mHeadView = mNavigationView.getHeaderView(0);
        mHeadView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "head", Toast.LENGTH_SHORT).show();
            }
        });

        mNavigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.app_update:
                        Toast.makeText(MainActivity.this, "update", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.app_message:
                        Toast.makeText(MainActivity.this, "message", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.app_setting:
                        Toast.makeText(MainActivity.this, "setting", Toast.LENGTH_SHORT).show();
                        break;
                }
                return false;
            }
        });

        mToolbar.inflateMenu(R.menu.toolbar_menu);
        ActionBarDrawerToggle barDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, mToolbar, R.string.open, R.string.close);
        barDrawerToggle.syncState();
        mDrawerLayout.addDrawerListener(barDrawerToggle);

    }

}
