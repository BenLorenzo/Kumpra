package com.android3project.dev.kumpra.Presenter;

import android.content.Context;

import com.android3project.dev.kumpra.Entities.Item;

import java.util.List;

/**
 * Created by Dev on 7/3/2015.
 */
public interface ItemPresenter {
    public List<Item> saveItem(String itemName, String itemQuantity);
    public void onItemFieldError(Context context);
}
