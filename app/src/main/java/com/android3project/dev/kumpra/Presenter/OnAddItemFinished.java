package com.android3project.dev.kumpra.Presenter;

import com.android3project.dev.kumpra.Entities.Item;

import java.util.List;

/**
 * Created by Dev on 7/10/2015.
 */
public interface OnAddItemFinished {
    public void onAddSuccess(List<Item> itemList);
}
