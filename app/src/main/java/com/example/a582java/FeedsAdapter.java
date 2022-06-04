package com.example.a582java;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.imageview.ShapeableImageView;

import java.util.ArrayList;

public class FeedsAdapter extends RecyclerView.Adapter<FeedsAdapter.FeedsViewHolder> {

    ArrayList<Uri> list;

    public FeedsAdapter(ArrayList<Uri> list) {

        this.list = list;
    }

    @NonNull
    @Override
    public FeedsAdapter.FeedsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.feeds, parent, false);
        return new FeedsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FeedsAdapter.FeedsViewHolder holder, int position) {
        holder.pro.setImageURI(list.get(position));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class FeedsViewHolder extends RecyclerView.ViewHolder {
        ShapeableImageView pro;

        public FeedsViewHolder(@NonNull View itemView) {
            super(itemView);
            pro = itemView.findViewById(R.id.feeds_posts_photo_id);


        }
    }
}

