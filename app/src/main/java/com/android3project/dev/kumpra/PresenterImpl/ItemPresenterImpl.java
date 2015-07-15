package com.android3project.dev.kumpra.PresenterImpl;

import android.content.Context;
import android.text.TextUtils;
import android.widget.EditText;

import com.android3project.dev.kumpra.Entities.Item;
import com.android3project.dev.kumpra.Presenter.ItemPresenter;
import com.android3project.dev.kumpra.Presenter.OnFinishedDataChanged;

import java.util.List;

/**
 * Created by Dev on 7/13/2015.
 */
public class ItemPresenterImpl implements ItemPresenter {

    private Context context;
    EditText edtItemName;
    EditText edtItemQty;
    Item item;
    OnFinishedDataChanged onFinishedDataChanged;

    public ItemPresenterImpl(Context context) {
        this.context = context;
    }

    @Override
    public void addItem(String name, String qty) {
        if (!TextUtils.isEmpty(name) &&
                !TextUtils.isEmpty(qty)) {

            item = new Item(context, name, qty);
            item.save();
        } else {
//                            itemPresenter.onItemFieldError(getApplicationContext());
        }
    }

    @Override
    public void shareItemList() {

    }

    @Override
    public List<Item> getItemList() {
        List<Item> items = Item.findWithQuery(Item.class, "Select * from Item");
        return items;
    }
}
