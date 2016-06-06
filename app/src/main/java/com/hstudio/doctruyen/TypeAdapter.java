package com.hstudio.doctruyen;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by phhien on 6/6/2016.
 */
public class TypeAdapter extends RecyclerView.Adapter<TypeAdapter.MyViewHolder> {

    private List<String> typeList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title;

        public MyViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.title);
        }
    }

    public TypeAdapter(List<String> typeList) {
        this.typeList = typeList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.type, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.title.setText(typeList.get(position));
    }

    @Override
    public int getItemCount() {
        return typeList.size();
    }
}
