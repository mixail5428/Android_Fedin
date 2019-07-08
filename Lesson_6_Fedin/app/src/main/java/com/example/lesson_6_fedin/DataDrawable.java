package com.example.lesson_6_fedin;

import android.os.Parcel;
import android.os.Parcelable;

public class DataDrawable implements Parcelable {
    private String drawable;
    private String text;

    public DataDrawable(String drawable, String text){
        this.drawable = drawable;
        this.text = text;
    }

    public String getDrawable() {
        return drawable;
    }

    public void setDrawable(String drawable) {
        this.drawable = drawable;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.drawable);
        dest.writeString(this.text);
    }

    public DataDrawable() {
    }

    protected DataDrawable(Parcel in) {
        this.drawable = in.readString();
        this.text = in.readString();
    }

    public static final Creator<DataDrawable> CREATOR = new Creator<DataDrawable>() {
        @Override
        public DataDrawable createFromParcel(Parcel source) {
            return new DataDrawable(source);
        }

        @Override
        public DataDrawable[] newArray(int size) {
            return new DataDrawable[size];
        }
    };
}
