package com.android3project.dev.kumpra;

import android.app.DownloadManager;
import android.content.Context;
import android.net.Uri;
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
import com.android3project.dev.kumpra.Item.ItemPresenter;
import com.android3project.dev.kumpra.Item.ItemPresenterImpl;
import com.android3project.dev.kumpra.Item.OnItemFinishedSave;
import com.facebook.FacebookSdk;
import com.facebook.internal.WebDialog;
import com.facebook.share.model.ShareContent;
import com.facebook.share.model.ShareLinkContent;
import com.facebook.share.model.ShareOpenGraphAction;
import com.facebook.share.model.ShareOpenGraphContent;
import com.facebook.share.model.ShareOpenGraphObject;
import com.facebook.share.widget.ShareButton;
import com.facebook.share.widget.ShareDialog;
import com.github.clans.fab.FloatingActionMenu;


import org.w3c.dom.Text;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnItemClick;


public class MainActivity extends AppCompatActivity implements OnItemFinishedSave {

    ItemPresenter itemPresenter;
    List<Item> itemsList;
    @Bind(R.id.lvItemList)
    ListView lvItemList;
    @Bind(R.id.menu_down)
    FloatingActionMenu fabMenuDown;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FacebookSdk.sdkInitialize(getApplicationContext());
        // Initialize the SDK before executing any other operations,
        // especially, if you're using Facebook UI elements.
        ButterKnife.bind(this);
        itemsList = Item.findWithQuery(Item.class, "Select * from Item");
        ItemAdapter itemAdapter = new ItemAdapter(this, itemsList);
        lvItemList.setAdapter(itemAdapter);
        lvItemList.deferNotifyDataSetChanged();
        itemPresenter = new ItemPresenterImpl(this);
    }

    @OnClick(R.id.btnFabAddItem)
    public void addItem() {
        fabMenuDown.hideMenuButton(true);
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

                        if (!TextUtils.isEmpty(name.getText().toString()) && !TextUtils.isEmpty(qty.getText().toString())) {
                            itemsList = itemPresenter.saveItem(name.getText().toString(), qty.getText().toString());
                            onSuccess(itemsList);
                        } else {
                            itemPresenter.onItemFieldError(getApplicationContext());
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
    }

    @OnClick(R.id.btnFabShare)
    public void share() {

        String string = "";
        List<Item> items = Item.findWithQuery(Item.class, "Select * from Item");
        // retrieving data from string list array in for loop
        for (Item i : items) {
            string += String.valueOf(i.getItemName()) + " - " + String.valueOf(i.getItemQty() + ", ");
        }
        ShareContent content = new ShareLinkContent.Builder()
        .setContentUrl(Uri.parse("https://www.facebook.com/anon2012030234"))
                .setContentDescription(string)
                .setContentTitle("Kumprahunon")
                .build();
        ShareDialog.show(this, content);
//
    }

    private void shareContent() {
        Bundle bundle = new Bundle();
        bundle.putString("caption", "Harlem Shake Launcher for Android");
        bundle.putString("description", "Your android can do the Harlem Shake. Download it from google play");
        bundle.putString("link", "https://play.google.com/store/apps/details?id=mobi.shush.harlemlauncher");
        bundle.putString("name", "Harlem Shake Launcher");
        bundle.putString("picture", "http://shush.mobi/bla.png");
//        new WebDialog.Builder(this, mySession, bundle).build().show();
    }


    @Override
    public void onSuccess(List<Item> itemList) {
        ItemAdapter itemAdapter = new ItemAdapter(this, itemList);
        lvItemList.setAdapter(itemAdapter);
    }
}
