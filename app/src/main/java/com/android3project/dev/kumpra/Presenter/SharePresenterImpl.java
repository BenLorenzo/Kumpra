package com.android3project.dev.kumpra.Presenter;

import android.net.Uri;

import com.android3project.dev.kumpra.Entities.Item;
import com.facebook.share.model.ShareContent;
import com.facebook.share.model.ShareLinkContent;

import java.util.List;

/**
 * Created by Dev on 7/10/2015.
 */
public class SharePresenterImpl implements SharePresenter {
    @Override
    public ShareContent shareList() {
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
        return content;
    }
}
