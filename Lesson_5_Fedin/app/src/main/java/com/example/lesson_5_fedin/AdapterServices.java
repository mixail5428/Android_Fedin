package com.example.lesson_5_fedin;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class AdapterServices extends RecyclerView.Adapter<AdapterServices.HolderServices> {

    ArrayList<DataServices> data;
    private OnClickLister onClickLister;

    interface OnClickLister {
        void onClick(DataServices item);
    }

    public AdapterServices(ArrayList<DataServices> data){
        this.data = data;
    }

    @NonNull
    @Override
    public HolderServices onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new HolderServices(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_data_services, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull HolderServices holder, int position) {
        holder.bind(data.get(position),onClickLister);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public static class HolderServices extends RecyclerView.ViewHolder {
        View cardView;
        ImageView imageView;
        TextView title;
        TextView description;
        TextView address;


        public HolderServices(@NonNull View itemView) {
            super(itemView);
            cardView = itemView;
            imageView = itemView.findViewById(R.id.imageView);
            title = itemView.findViewById(R.id.title);
            description = itemView.findViewById(R.id.description);
            address = itemView.findViewById(R.id.address);
        }

        public void bind(final DataServices item, final OnClickLister onClickLister) {
            Glide.with(cardView)
                    .load(item.getDrawable())
                    .placeholder(R.drawable.drawable_white)
                    .centerCrop()
                    .into(imageView); //заполнил ImageView картинкой
            title.setText(item.getTitle());
            description.setText(item.getDescription());
            address.setText(item.getAddress());

            cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(onClickLister != null)
                    onClickLister.onClick(item);
                }
            });
        }
    }

    public void setClickLister(OnClickLister clickLister) {
        this.onClickLister = clickLister;
    }
    public OnClickLister getOnClickLister(){
        return onClickLister;
    }
}

