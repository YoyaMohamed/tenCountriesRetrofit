package com.example.tencountriesretrofit;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    private List<Worldpopulation> List;
    private Context context;

    public MyAdapter(List<Worldpopulation> List, Context context) {
        this.List = List;
        this.context = context;
    }

    @Override
    public MyAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent,
                                                     int viewType) {
        // create a new view
        View view = (View) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item, parent, false);
        MyViewHolder viewHolder = new MyViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Worldpopulation worldpopulation=List.get(position);

        holder.countryNameTextView.setText( worldpopulation.getCountry());
        holder.countryRankTextView.setText(Integer.toString(worldpopulation.getRank()));
        holder.countryPopulationTextView.setText(worldpopulation.getPopulation());
        Glide.with(context)
                .load(List.get(position).getFlag())
                .into(holder.countryFlagImageView);
    }

    @Override
    public int getItemCount() {
        return List.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView countryNameTextView;
        public TextView countryRankTextView;
        public TextView countryPopulationTextView;
        public ImageView countryFlagImageView;
        public MyViewHolder(View view) {
            super(view);
            countryFlagImageView = view.findViewById(R.id.countryFlag);
            countryNameTextView=view.findViewById(R.id.countryName);
            countryPopulationTextView=view.findViewById(R.id.countryPopulation);
            countryRankTextView=view.findViewById(R.id.countryRank);
        }

    }

}