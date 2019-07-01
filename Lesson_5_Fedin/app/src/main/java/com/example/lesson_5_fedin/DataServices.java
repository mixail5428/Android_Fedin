package com.example.lesson_5_fedin;

public class DataServices {
    private String drawable;
    private String title;
    private String description;
    private String address;

    public DataServices(String drawable, String title, String description, String address){
        this.drawable = drawable;
        this.title = title;
        this.description = description;
        this.address = address;
    }

    public String getDrawable() {
        return drawable;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getAddress() {
        return address;
    }
}
