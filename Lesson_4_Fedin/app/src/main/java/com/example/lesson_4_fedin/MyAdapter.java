package com.example.lesson_4_fedin;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    public static final int SHORTTYPE = 0;
    public static final int LONGTYPE = 1;
    public static final int LONGTYPEDONTDESCRIPTION = 2;


    ArrayList<DataCard> data;
    private OnItemClickListener clickListener;

    public interface OnItemClickListener {
        void onItemClick(DataCard item);
    }

    public void setClickListener(OnItemClickListener listener) {
        this.clickListener = listener;
    }

    public MyAdapter(ArrayList<DataCard> data) {
        this.data = data;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        if (viewType == SHORTTYPE)
            return new MyViewHolderWithDescription(LayoutInflater.from(parent.getContext()).inflate(R.layout.card_first_type, parent, false));
        else if (viewType == LONGTYPE)
            return new MyViewHolderWithDescription(LayoutInflater.from(parent.getContext()).inflate(R.layout.card_second_type, parent, false));
        else
            return new MyViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.card_third_type, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.filling(data.get(position), clickListener);

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    @Override
    public int getItemViewType(int position) {
        if (data.get(position).getDescription() == null)
            return LONGTYPEDONTDESCRIPTION;
        else if (position % 2 == 0 && data.get(position + 1).getDescription() == null)
            return LONGTYPE;
            else
                return SHORTTYPE;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public static class MyViewHolder extends RecyclerView.ViewHolder { // holder для элементов без описания
        View cardView;
        ImageView icon;
        TextView title;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            cardView = itemView;
            icon = cardView.findViewById(R.id.icon);
            title = cardView.findViewById(R.id.title);
        }

        public void filling(final DataCard item, final OnItemClickListener listener) {
            icon.setImageResource(item.getIdIcon());
            title.setText(item.getTitle());

            cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null)
                        listener.onItemClick(item);
                }
            });
        }
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public static class MyViewHolderWithDescription extends MyViewHolder { // holder для элементов с описанием
        TextView description;

        public MyViewHolderWithDescription(@NonNull View itemView) {
            super(itemView);
            description = cardView.findViewById(R.id.description);
        }

        @Override
        public void filling(DataCard item, OnItemClickListener listener) {
            super.filling(item, listener);
            int textColor = cardView.getContext().getResources().getColor(item.getColorDescription());
            description.setText(item.getDescription());
            description.setTextColor(textColor);
        }
    }
}
