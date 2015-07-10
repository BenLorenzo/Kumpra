package com.android3project.dev.kumpra.View;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;


import com.afollestad.materialdialogs.MaterialDialog;
import com.android3project.dev.kumpra.Adapter.ItemAdapter;
import com.android3project.dev.kumpra.Entities.Item;
import com.android3project.dev.kumpra.Presenter.AddItemPresenter;
import com.android3project.dev.kumpra.Presenter.AddItemPresenterImpl;
import com.android3project.dev.kumpra.Presenter.ItemPresenter;
import com.android3project.dev.kumpra.Presenter.OnAddItemFinished;
import com.android3project.dev.kumpra.Presenter.SharePresenter;
import com.android3project.dev.kumpra.Presenter.SharePresenterImpl;
import com.android3project.dev.kumpra.R;
import com.facebook.FacebookSdk;
import com.facebook.share.model.ShareContent;
import com.facebook.share.widget.ShareDialog;
import com.github.clans.fab.FloatingActionMenu;


import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class MainActivity extends AppCompatActivity implements OnAddItemFinished{

    ItemPresenter itemPresenter;
    List<Item> itemsList;
    @Bind(R.id.lvItemList)
    ListView lvItemList;
    @Bind(R.id.menu_down)
    FloatingActionMenu fabMenuDown;
    private SharePresenter sharePresenter;
    private AddItemPresenter addItemPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FacebookSdk.sdkInitialize(getApplicationContext());
        ButterKnife.bind(this);

        if (Item.listAll(Item.class) != null) {
            itemsList = Item.findWithQuery(Item.class, "Select * from Item");
        }

        ItemAdapter itemAdapter = new ItemAdapter(this, itemsList);
        lvItemList.setAdapter(itemAdapter);
        lvItemList.deferNotifyDataSetChanged();
//        itemPresenter = new ItemPresenterImpl();
    }

    @OnClick(R.id.btnFabAddItem)
    public void addItem() {
        boolean wrapInScrollView = true;
        new MaterialDialog.Builder(this)
                .title("Add item for shopping")
                .customView(R.layout.add_item_dialog, wrapInScrollView)
                .positiveText("Add")
                .negativeText("Cancel")
                .cancelable(false)
                .callback(new MaterialDialog.ButtonCallback() {
                    @Override
                    public void onPositive(MaterialDialog dialog) {
                        super.onPositive(dialog);
                        View view = dialog.getCustomView();
                        EditText name = (EditText) view.findViewById(R.id.edtItemName);
                        EditText qty = (EditText) view.findViewById(R.id.edtItemQty);

                        if (!TextUtils.isEmpty(name.getText().toString()) &&
                                !TextUtils.isEmpty(qty.getText().toString())) {

                            addItemPresenter = new AddItemPresenterImpl();
                            List<Item> itemList = addItemPresenter.addItem(name.getText().toString()
                                    , qty.getText().toString());
                            onAddSuccess(itemList);
                        } else {
//                            itemPresenter.onItemFieldError(getApplicationContext());
                        }
                        fabMenuDown.showMenuButton(true);
                    }
                    @Override
                    public void onNegative(MaterialDialog dialog) {
                        super.onNegative(dialog);
                        Toast.makeText(getApplicationContext(), "Sure diha :)", Toast.LENGTH_SHORT).show();
                        fabMenuDown.showMenuButton(true);
                    }
                })
                .show();
        fabMenuDown.hideMenuButton(true);
    }

    @OnClick(R.id.btnFabShare)
    public void share() {
        sharePresenter = new SharePresenterImpl();
        ShareContent content = sharePresenter.shareList();
        ShareDialog.show(this, content);
    }
    @Override
    public void onAddSuccess(List<Item> itemList) {
        ItemAdapter itemAdapter = new ItemAdapter(getApplicationContext(), itemList);
        lvItemList.setAdapter(itemAdapter);
    }
}
