package com.example.lesson_4_fedin;

import android.content.Context;

public class DataCard {


    private int idIcon;
    private String title;
    private String description;
    private int colorDescription;

    public DataCard(int idIcon, String title, String description, int colorDescription) {
        this.idIcon = idIcon;
        this.title = title;
        this.description = description;
        this.colorDescription = colorDescription;
    }

    public DataCard(int idIcon, String title, String description) {
        this(idIcon, title, description, R.color.warm_grey_two);
    }

    public DataCard(int idIcon, String title) {
        this(idIcon, title, null, R.color.warm_grey_two);
    }

    public int getIdIcon() {
        return idIcon;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public int getColorDescription() {
        return colorDescription;
    }
}
