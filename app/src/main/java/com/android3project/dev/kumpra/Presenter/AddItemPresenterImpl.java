package com.android3project.dev.kumpra.Presenter;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.afollestad.materialdialogs.MaterialDialog;
import com.android3project.dev.kumpra.Adapter.ItemAdapter;
import com.android3project.dev.kumpra.Entities.Item;
import com.android3project.dev.kumpra.R;

import java.util.List;

/**
 * Created by Dev on 7/10/2015.
 */
public class AddItemPresenterImpl implements AddItemPresenter{

    private Context context;
    private ItemPresenter itemPresenter;
    private OnAddItemFinished onAddItemFinished;

    public AddItemPresenterImpl(){
        itemPresenter = new ItemPresenterImpl();
    }

    @Override
    public void getItemApplicationContext(Context context) {
        this.context = context;
    }

    @Override
    public List<Item> addItem(String name, String quantity) {
        List<Item> itemsList = itemPresenter.saveItem(name,quantity);
//        onSuccess(itemsList, listView);
        return itemsList;
    }
}
