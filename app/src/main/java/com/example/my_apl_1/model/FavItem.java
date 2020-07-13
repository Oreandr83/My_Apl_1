package com.example.my_apl_1.model;

public class FavItem {

    private String item_description;
    private  String id;
    private int item_image;

    public FavItem() {
    }

    public FavItem(String item_description, String id, int item_image) {
        this.item_description = item_description;
        this.id = id;
        this.item_image = item_image;
    }

    public String getItem_description() {
        return item_description;
    }

    public void setItem_description(String item_description) {
        this.item_description = item_description;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getItem_image() {
        return item_image;
    }

    public void setItem_image(int item_image) {
        this.item_image = item_image;
    }
}
