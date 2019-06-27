package com.example.lesson_4_fedin;

import android.content.res.Resources;
import android.graphics.Color;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    public static final int shortType = 0;
    public static final int longType = 1;
    public static final int longTypeDontDescription = 2;


    ArrayList<DataCard> data;
    private OnItemClickListener clickListener;

    public interface OnItemClickListener{
        void onItemClick(DataCard item);
    }

    public void setClickListener(OnItemClickListener listener){
        this.clickListener = listener;
    }

    public MyAdapter(ArrayList<DataCard> data){
        this.data = data;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        if(viewType == shortType)
            return new MyViewHolderWithDescription(LayoutInflater.from(parent.getContext()).inflate(R.layout.card_first_type, parent, false));
        else if(viewType == longType)
            return new MyViewHolderWithDescription(LayoutInflater.from(parent.getContext()).inflate(R.layout.card_second_type, parent, false));
            else
                return new MyViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.card_third_type, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.filling(data.get(position),clickListener);

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    @Override
    public int getItemViewType(int position) {
        if(position < 6)
            return shortType;
        else if (position == 6)
                return longType;
            else
                return longTypeDontDescription;
    }
////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public static class MyViewHolder extends RecyclerView.ViewHolder{ // holder для элементов без описания
        View cardView;
        ImageView icon;
        TextView title;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            cardView = itemView;
            icon = cardView.findViewById(R.id.icon);
            title = cardView.findViewById(R.id.title);
        }

        public void filling(final DataCard item, final OnItemClickListener listener){
            icon.setImageResource(item.getIdIcon());
            title.setText(item.getTitle());

            cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(listener != null)
                    listener.onItemClick(item);
                }
            });
        }
    }
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public static class MyViewHolderWithDescription extends MyViewHolder{ // holder для элементов с описанием
        TextView description;

        public MyViewHolderWithDescription(@NonNull View itemView) {
            super(itemView);
            description = cardView.findViewById(R.id.description);
        }

        @Override
        public void filling(DataCard item,OnItemClickListener listener) {
            super.filling(item,listener);

            description.setText(item.getDescription());
            description.setTextColor(item.getColorDescription());
        }
    }
}
