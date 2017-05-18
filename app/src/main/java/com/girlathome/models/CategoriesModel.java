package com.girlathome.models;

/**
 * Created by steve on 5/16/17.
 */

public class CategoriesModel {
    public int imageId;
    private String name;

    public CategoriesModel(int imageId, String name) {

        this.imageId = imageId;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getimageId() {
        return imageId;
    }

    public void setimageId(int imageId) {
        this.imageId = imageId;
    }
}
