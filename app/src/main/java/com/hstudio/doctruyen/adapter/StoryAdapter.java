package com.hstudio.doctruyen.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.hstudio.doctruyen.R;

import java.util.List;

/**
 * Created by phhien on 6/8/2016.
 */
public class StoryAdapter extends RecyclerView.Adapter<StoryAdapter.MyViewHolder>{

    private List<String> storyList;

    public StoryAdapter(List<String> storyList) {
        this.storyList = storyList;
    }

    public List<String> getStoryList() {
        return storyList;
    }

    public void setStoryList(List<String> storyList) {
        this.storyList = storyList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.truyen, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.title.setText(storyList.get(position));
    }

    @Override
    public int getItemCount() {
        return storyList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title;
        public ImageView image;
        public TextView author;

        public MyViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.title);
            image = (ImageView) view.findViewById(R.id.image);
            author = (TextView) view.findViewById(R.id.author);
        }
    }
}
