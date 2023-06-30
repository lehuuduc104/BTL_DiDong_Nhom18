package com.example.finalproject;

public class DataModel {
    private String title;
    private int imageResource;
    private String description;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getImageResource() {
        return imageResource;
    }

    public void setImageResource(int imageResource) {
        this.imageResource = imageResource;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public DataModel(String title, int imageResource, String description) {
        this.title = title;
        this.imageResource = imageResource;
        this.description = description;
    }

}
