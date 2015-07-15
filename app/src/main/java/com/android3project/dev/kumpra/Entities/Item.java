package com.android3project.dev.kumpra.Entities;

import android.content.Context;

import com.orm.SugarRecord;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dev on 7/3/2015.
 */
public class Item extends SugarRecord<Item>{
    String itemName;
    String itemQty;

    Item(String name, String qty) {
        this.itemName = name;
        this.itemQty = qty;
    }

    public Item() {
    }

    public Item(Context context,String itemName, String itemQty) {
        this.itemName = itemName;
        this.itemQty = itemQty;
    }

    public String getItemName() {
        return itemName;
    }

    public String getItemQty() {
        return itemQty;
    }
}
