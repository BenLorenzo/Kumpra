package com.android3project.dev.kumpra.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.android3project.dev.kumpra.Entities.Item;
import com.android3project.dev.kumpra.R;

import java.util.List;

/**
 * Created by Dev on 7/3/2015.
 */
public class ItemAdapter extends BaseAdapter {

    private Context context;
    private List<Item> itemsList;

    public ItemAdapter(Context context, List<Item> list) {
        this.context = context;
        this.itemsList = list;
    }

    @Override
    public int getCount() {
        return itemsList.size();
    }

    @Override
    public Object getItem(int position) {
        return itemsList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {
        Item itemEntry = itemsList.get(position);
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.row_item, null);
        }

        TextView tvItemName = (TextView) convertView.findViewById(R.id.tvItemName);
        tvItemName.setText(itemEntry.getItemName());

        TextView tvItemQty = (TextView) convertView.findViewById(R.id.tvItemQty);
        tvItemQty.setText(itemEntry.getItemQty());

        return convertView;
    }
}
