package com.hstudio.doctruyen;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hstudio.doctruyen.adapter.StoryAdapter;
import com.hstudio.doctruyen.adapter.TypeAdapter;
import com.hstudio.doctruyen.async.LoadStories;
import com.hstudio.doctruyen.async.LoadTypes;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ratan on 7/29/2015.
 */
public class PrimaryFragment extends Fragment {

    RecyclerView mRecyclerView;
    StoryAdapter mStoryAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.primary_layout,null);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        initRecyclerView(view);
        new LoadStories(PrimaryFragment.this).execute("123");
        return view;
    }

    private void initRecyclerView(View view) {
        mRecyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        mRecyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(view.getContext());
        mRecyclerView.setLayoutManager(mLayoutManager);
        //mRecyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());

        List<String> storys = new ArrayList<>();
        storys.add("Test1");
        storys.add("Test2");
        storys.add("Test3");
        storys.add("Test4");
        mStoryAdapter = new StoryAdapter(storys);
        mRecyclerView.setAdapter(mStoryAdapter);

        //new LoadTypes(MainActivity.this).execute("http://truyenfull.vn");
    }

    public void setStory(List<String> stories) {
        mStoryAdapter.setStoryList(stories);
        mStoryAdapter.notifyDataSetChanged();
    }
}
