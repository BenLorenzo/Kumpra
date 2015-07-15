package com.android3project.dev.kumpra.Adapter;

import android.os.Handler;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android3project.dev.kumpra.Entities.Item;
import com.android3project.dev.kumpra.R;

import java.util.List;

/**
 * Created by Dev on 7/13/2015.
 */
public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ItemViewHolder> {

    List<Item> items;

    public ItemAdapter(List<Item> items) {
        this.items = items;
    }

    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_row, parent, false);
        ItemViewHolder itemViewHolder = new ItemViewHolder(v);
        return itemViewHolder;
    }

    @Override
    public void onBindViewHolder(ItemViewHolder holder, int position) {
        holder.itemName.setText(items.get(position).getItemName());
        holder.itemQty.setText(items.get(position).getItemQty());
    }


    @Override
    public int getItemCount() {
        return items.size();
    }

    public static class ItemViewHolder extends RecyclerView.ViewHolder {
        CardView cardView;
        TextView itemName;
        TextView itemQty;

        public ItemViewHolder(View itemView) {
            super(itemView);
            cardView = (CardView) itemView.findViewById(R.id.cardview);
            itemName = (TextView) itemView.findViewById(R.id.itemName);
            itemQty = (TextView) itemView.findViewById(R.id.itemQty);
        }
    }
}
