package com.android3project.dev.kumpra.Presenter;

import android.content.Context;
import android.widget.ListView;

import com.android3project.dev.kumpra.Entities.Item;

import java.util.List;

/**
 * Created by Dev on 7/10/2015.
 */
public interface AddItemPresenter {
    public List<Item> addItem(String name, String quantity);
    public void getItemApplicationContext(Context context);
}
