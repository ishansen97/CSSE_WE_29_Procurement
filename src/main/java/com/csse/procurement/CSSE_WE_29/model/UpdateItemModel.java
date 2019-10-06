package com.csse.procurement.CSSE_WE_29.model;

public class UpdateItemModel {
    private int itemId;
    private int value;

    public UpdateItemModel() {
    }

    public UpdateItemModel(int itemId, int value) {
        this.itemId = itemId;
        this.value = value;
    }

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
