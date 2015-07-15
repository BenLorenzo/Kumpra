package com.android3project.dev.kumpra.Presenter;

import com.android3project.dev.kumpra.Entities.Item;

import java.util.List;

/**
 * Created by Dev on 7/14/2015.
 */
public interface OnFinishedDataChanged {
    public void updateRecyclerViewList(List<Item> itemList);
}