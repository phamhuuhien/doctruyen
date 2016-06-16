package com.hstudio.doctruyen;

import android.app.Activity;
import android.media.Image;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.hstudio.doctruyen.adapter.ChapAdapter;
import com.hstudio.doctruyen.async.LoadStoryDetail;
import com.hstudio.doctruyen.object.StoryDetail;
import com.squareup.picasso.Picasso;

/**
 * Created by phhien on 6/14/2016.
 */
public class StoryActivity extends AppCompatActivity {

    private TextView title;
    private TextView description;
    private ImageView imageView;
    private RecyclerView listChap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.story_layout);

        title = (TextView) findViewById(R.id.title);
        description = (TextView) findViewById(R.id.description);
        imageView = (ImageView) findViewById(R.id.imageView);
        listChap = (RecyclerView) findViewById(R.id.listChap);
        listChap.setHasFixedSize(true);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        listChap.setLayoutManager(mLayoutManager);
        listChap.setItemAnimator(new DefaultItemAnimator());

        String link = getIntent().getStringExtra("LINK");

        new LoadStoryDetail(this).execute(link);
    }

    public void updateUI(StoryDetail storyDetail) {
        title.setText(storyDetail.getTitle());
        if(!TextUtils.isEmpty(storyDetail.getImage())) {
            Picasso.with(this).load(storyDetail.getImage()).into(imageView);
        }
        description.setText(storyDetail.getDescription());

        ChapAdapter chapAdapter = new ChapAdapter(this);
        chapAdapter.setChaps(storyDetail.getChaps());
        listChap.setAdapter(chapAdapter);
    }
}
