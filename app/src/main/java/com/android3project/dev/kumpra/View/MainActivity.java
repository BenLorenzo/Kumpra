package com.android3project.dev.kumpra.View;

import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.afollestad.materialdialogs.MaterialDialog;
import com.android3project.dev.kumpra.Adapter.ItemAdapter;
import com.android3project.dev.kumpra.Entities.Item;
import com.android3project.dev.kumpra.Presenter.ItemPresenter;
import com.android3project.dev.kumpra.Presenter.OnFinishedDataChanged;
import com.android3project.dev.kumpra.Presenter.ShareItemListPresenter;
import com.android3project.dev.kumpra.PresenterImpl.ItemPresenterImpl;
import com.android3project.dev.kumpra.PresenterImpl.ShareItemListPresenterImpl;
import com.android3project.dev.kumpra.R;
import com.facebook.FacebookSdk;
import com.facebook.share.model.ShareContent;
import com.facebook.share.widget.ShareDialog;

import java.util.List;

import butterknife.ButterKnife;

import static com.android3project.dev.kumpra.R.drawable.ic_menu_white_24dp;


public class MainActivity extends AppCompatActivity implements OnFinishedDataChanged{

    DrawerLayout drawerLayout;
    RecyclerView recyclerView;
    List<Item> itemList = Item.findWithQuery(Item.class, "Select * from Item");
    ItemPresenter addItemPresenter;
    ShareItemListPresenter shareItemListPresenter;
    EditText edtItemName;
    EditText edtItemQty;
    ItemAdapter itemAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FacebookSdk.sdkInitialize(getApplicationContext());
        ButterKnife.bind(this);

        addItemPresenter = new ItemPresenterImpl(getApplicationContext());

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        final ActionBar ab = getSupportActionBar();
        ab.setHomeAsUpIndicator(ic_menu_white_24dp);
        ab.setDisplayHomeAsUpEnabled(true);

        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerview);

        if (Item.listAll(Item.class) != null) {
            RecyclerView.LayoutManager linearLayoutManager = new LinearLayoutManager(this);
            recyclerView.setLayoutManager(linearLayoutManager);
            itemAdapter = new ItemAdapter(itemList);
            recyclerView.setAdapter(itemAdapter);
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                drawerLayout.openDrawer(GravityCompat.START);
                return true;
            case R.id.addItem:
                showDialog();
                break;
            case R.id.shareList:
                shareItemListPresenter = new ShareItemListPresenterImpl();
                ShareContent content = shareItemListPresenter.shareList();
                ShareDialog.show(this,content);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    public void showDialog(){
        boolean wrapInScrollView = true;
        new MaterialDialog.Builder(this)
                .title("Add item for shopping")
                .customView(R.layout.add_item_dialog,wrapInScrollView)
                .positiveText("Add")
                .negativeText("Cancel")
                .cancelable(false)
                .callback(new MaterialDialog.ButtonCallback() {
                    @Override
                    public void onPositive(MaterialDialog dialog) {
                        super.onPositive(dialog);
                        View view = dialog.getCustomView();
                        edtItemName = (EditText) view.findViewById(R.id.edtItemName);
                        edtItemQty = (EditText) view.findViewById(R.id.edtItemQty);
                        String name = edtItemName.getText().toString();
                        String qty = edtItemQty.getText().toString();
                        addItemPresenter.addItem(name,qty);
                        updateRecyclerViewList(addItemPresenter.getItemList());
                    }
                    @Override
                    public void onNegative(MaterialDialog dialog) {
                        super.onNegative(dialog);
                    }
                })
                .show();
    }

    @Override
    public void updateRecyclerViewList(List<Item> list) {
        ItemAdapter itemAdapter = new ItemAdapter(list);
        recyclerView.setAdapter(itemAdapter);
    }
}
