package com.example.a582java;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.imageview.ShapeableImageView;

import java.util.ArrayList;

public class StoryAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    Context context;
    ArrayList<Story> list;

    public StoryAdapter(Context context, ArrayList<Story> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.stories, parent, false);
        return new StoriesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Story story = list.get(position);
        if (holder instanceof StoriesViewHolder) {
            ((StoriesViewHolder) holder).pro.setImageResource(story.pro);
            ((StoriesViewHolder) holder).fullname.setText(story.fullname);
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class StoriesViewHolder extends RecyclerView.ViewHolder {
        ShapeableImageView pro;
        TextView fullname;

        public StoriesViewHolder(@NonNull View itemView) {
            super(itemView);
            pro = itemView.findViewById(R.id.stories_iv_profile_id);
            fullname = itemView.findViewById(R.id.stories_tv_fullname_id);
        }
    }
}
