package com.android3project.dev.kumpra.Presenter;


import android.content.Context;
import android.widget.Toast;

import com.android3project.dev.kumpra.Adapter.ItemAdapter;
import com.android3project.dev.kumpra.Entities.Item;

import java.util.List;

/**
 * Created by Dev on 7/3/2015.
 */
public class ItemPresenterImpl implements ItemPresenter{


    private Context context;
    private Item item;

    @Override
    public void onItemFieldError(Context context) {

        Toast.makeText(context,"Please Complete fields",Toast.LENGTH_LONG).show();
    }

    @Override
    public List<Item> saveItem(String itemName, String itemQuantity) {
        item = new Item(context,itemName,itemQuantity);
        item.save();
        List<Item> items = Item.findWithQuery(Item.class, "Select * from Item");
        return items;
    }
}
