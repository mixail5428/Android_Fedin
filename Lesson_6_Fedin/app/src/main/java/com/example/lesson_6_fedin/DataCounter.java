package com.example.lesson_6_fedin;

import android.os.Parcel;
import android.os.Parcelable;

public class DataCounter implements Parcelable {
    private String title;
    private String icon;
    private String oneCounter;
    private String twoCounter;
    private String threeCounter;
    private int TypeCounter;


    public String getTitle() {
        return title;
    }

    public String getIcon() {
        return icon;
    }

    public String getOneCounter() {
        return oneCounter;
    }

    public String getTwoCounter() {
        return twoCounter;
    }

    public String getThreeCounter() {
        return threeCounter;
    }

    public int getTypeCounter() {
        return TypeCounter;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.title);
        dest.writeString(this.icon);
        dest.writeString(this.oneCounter);
        dest.writeString(this.twoCounter);
        dest.writeString(this.threeCounter);
        dest.writeInt(this.TypeCounter);
    }

    public DataCounter() {
    }

    protected DataCounter(Parcel in) {
        this.title = in.readString();
        this.icon = in.readString();
        this.oneCounter = in.readString();
        this.twoCounter = in.readString();
        this.threeCounter = in.readString();
        this.TypeCounter = in.readInt();
    }

    public static final Creator<DataCounter> CREATOR = new Creator<DataCounter>() {
        @Override
        public DataCounter createFromParcel(Parcel source) {
            return new DataCounter(source);
        }

        @Override
        public DataCounter[] newArray(int size) {
            return new DataCounter[size];
        }
    };
}
