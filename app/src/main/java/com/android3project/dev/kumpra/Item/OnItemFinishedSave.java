package com.android3project.dev.kumpra.Item;

import com.android3project.dev.kumpra.Entities.Item;

import java.util.List;

/**
 * Created by Dev on 7/3/2015.
 */
public interface OnItemFinishedSave {
    public void onSuccess(List<Item> itemList);

}
