package com.android3project.dev.kumpra.Presenter;

import com.android3project.dev.kumpra.Entities.Item;

import java.util.List;

/**
 * Created by Dev on 7/13/2015.
 */
public interface ItemPresenter {
    public void addItem(String name, String qty);
    public void shareItemList();
    public List<Item> getItemList();
}

