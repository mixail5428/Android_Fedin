package com.example.lesson_7_fedin.bridge;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Divorce implements Parcelable {

    @SerializedName("end")
    @Expose
    private String end;
    @SerializedName("id")
    @Expose
    private int id;
    @SerializedName("start")
    @Expose
    private String start;
    public final static Parcelable.Creator<Divorce> CREATOR = new Creator<Divorce>() {


        @SuppressWarnings({
                "unchecked"
        })
        public Divorce createFromParcel(Parcel in) {
            return new Divorce(in);
        }

        public Divorce[] newArray(int size) {
            return (new Divorce[size]);
        }

    };

    protected Divorce(Parcel in) {
        this.end = ((String) in.readValue((String.class.getClassLoader())));
        this.id = ((int) in.readValue((int.class.getClassLoader())));
        this.start = ((String) in.readValue((String.class.getClassLoader())));
    }

    /**
     * No args constructor for use in serialization
     */
    public Divorce() {
    }

    /**
     * @param id
     * @param start
     * @param end
     */
    public Divorce(String end, int id, String start) {
        super();
        this.end = end;
        this.id = id;
        this.start = start;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
    }

    public Divorce withEnd(String end) {
        this.end = end;
        return this;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Divorce withId(int id) {
        this.id = id;
        return this;
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public Divorce withStart(String start) {
        this.start = start;
        return this;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(end);
        dest.writeValue(id);
        dest.writeValue(start);
    }

    public int describeContents() {
        return 0;
    }

}