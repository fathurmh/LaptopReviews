package com.digitalent.laptopreviews.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.digitalent.laptopreviews.R;
import com.digitalent.laptopreviews.model.Laptop;

import java.util.ArrayList;

public class CardViewLaptopAdapter extends RecyclerView.Adapter<CardViewLaptopAdapter.CardViewHolder> {
    private ArrayList<Laptop> listLaptop;
    private OnItemClickCallback onItemClickCallback;

    public CardViewLaptopAdapter(ArrayList<Laptop> list) {
        this.listLaptop = list;
    }

    public void setOnItemClickCallback(OnItemClickCallback onItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback;
    }

    @NonNull
    @Override
    public CardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_card_laptop, parent, false);
        return new CardViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final CardViewHolder holder, int position) {
        Laptop laptop = listLaptop.get(position);
        Glide.with(holder.itemView.getContext())
                .load(laptop.getImage())
                .apply(new RequestOptions().override(350, 550))
                .into(holder.image);
        holder.name.setText(laptop.getName());
        holder.desc.setText(laptop.getDescription());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClickCallback.onItemClicked(listLaptop.get(holder.getAdapterPosition()));
            }
        });
    }

    @Override
    public int getItemCount() {
        return listLaptop.size();
    }

    class CardViewHolder extends RecyclerView.ViewHolder {
        ImageView image;
        TextView name, desc;

        CardViewHolder(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.img_item_image);
            name = itemView.findViewById(R.id.tv_item_name);
            desc = itemView.findViewById(R.id.tv_item_desc);
        }
    }

    public interface OnItemClickCallback {
        void onItemClicked(Laptop data);
    }
}
