package com.hstudio.doctruyen;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.MotionEvent;

import com.hstudio.doctruyen.adapter.TypeAdapter;
import com.hstudio.doctruyen.async.LoadTypes;
import com.hstudio.doctruyen.object.Type;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    DrawerLayout mDrawerLayout;
    NavigationView mNavigationView;
    FragmentManager mFragmentManager;
    FragmentTransaction mFragmentTransaction;
    RecyclerView mRecyclerView;
    TypeAdapter mTypeAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
        mNavigationView = (NavigationView) findViewById(R.id.shitstuff);
        initRecyclerView();
        /**
         * Lets inflate the very first fragment
         * Here , we are inflating the TabFragment as the first Fragment
         */

        mFragmentManager = getSupportFragmentManager();
        mFragmentTransaction = mFragmentManager.beginTransaction();
        mFragmentTransaction.replace(R.id.containerView, new TabFragment()).commit();
        /**
         * Setup click events on the Navigation View Items.
         */

//        mNavigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
//            @Override
//            public boolean onNavigationItemSelected(MenuItem menuItem) {
//                mDrawerLayout.closeDrawers();
//                System.out.println("click " + menuItem.getItemId());
//
////                if (menuItem.getItemId() == R.id.nav_item_sent) {
////                    FragmentTransaction fragmentTransaction = mFragmentManager.beginTransaction();
////                    fragmentTransaction.replace(R.id.containerView, new SentFragment()).commit();
////
////                }
////
////                if (menuItem.getItemId() == R.id.nav_item_inbox) {
////                    FragmentTransaction xfragmentTransaction = mFragmentManager.beginTransaction();
////                    xfragmentTransaction.replace(R.id.containerView, new TabFragment()).commit();
////                }
//
//                return false;
//            }
//
//        });

        /**
         * Setup Drawer Toggle of the Toolbar
         */

        android.support.v7.widget.Toolbar toolbar = (android.support.v7.widget.Toolbar) findViewById(R.id.toolbar);
        ActionBarDrawerToggle mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, toolbar, R.string.app_name,
                R.string.app_name);

        mDrawerLayout.setDrawerListener(mDrawerToggle);

        mDrawerToggle.syncState();

    }

    public void setTypes(List<Type> types) {
        mTypeAdapter.setTypeList(types);
        mTypeAdapter.notifyDataSetChanged();
    }

    private void initRecyclerView() {
        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        mRecyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        mRecyclerView.setLayoutManager(mLayoutManager);
        //mRecyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());

        List<Type> types = new ArrayList<>();
        mTypeAdapter = new TypeAdapter(MainActivity.this);
        mTypeAdapter.setTypeList(types);
        mRecyclerView.setAdapter(mTypeAdapter);

        new LoadTypes(MainActivity.this).execute("http://truyenfull.vn");
    }

    public void onTypeClick(Type type) {
        mDrawerLayout.closeDrawers();
    }
}