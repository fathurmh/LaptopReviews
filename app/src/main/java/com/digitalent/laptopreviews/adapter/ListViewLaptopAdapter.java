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

public class ListViewLaptopAdapter extends RecyclerView.Adapter<ListViewLaptopAdapter.ListViewHolder> {
    private ArrayList<Laptop> listLaptop;
    private OnItemClickCallback onItemClickCallback;

    public ListViewLaptopAdapter(ArrayList<Laptop> listLaptop) {
        this.listLaptop = listLaptop;
    }

    public void setOnItemClickCallback(OnItemClickCallback onItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback;
    }

    @NonNull
    @Override
    public ListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_laptop, parent, false);
        return new ListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ListViewHolder holder, int position) {
        Laptop hero = listLaptop.get(position);
        Glide.with(holder.itemView.getContext())
                .load(hero.getImage())
                .apply(new RequestOptions().override(55, 55))
                .into(holder.image);
        holder.name.setText(hero.getName());
        holder.desc.setText(hero.getDescription());

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

    class ListViewHolder extends RecyclerView.ViewHolder {
        ImageView image;
        TextView name, desc;

        ListViewHolder(@NonNull View itemView) {
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
