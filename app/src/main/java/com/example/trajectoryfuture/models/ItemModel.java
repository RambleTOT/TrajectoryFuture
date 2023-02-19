package com.example.trajectoryfuture.models;

import android.widget.ImageView;
import android.widget.TextView;

public class ItemModel {

    private String iconItem, textItem;

    public ItemModel(String iconItem, String textItem) {
        this.iconItem = iconItem;
        this.textItem = textItem;
    }

    public String getIconItem() {
        return iconItem;
    }

    public void setIconItem(String iconItem) {
        this.iconItem = iconItem;
    }

    public String getTextItem() {
        return textItem;
    }

    public void setTextItem(String textItem) {
        this.textItem = textItem;
    }
}
