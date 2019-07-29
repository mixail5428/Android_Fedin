package com.example.lesson_7_fedin.bridge;

import java.util.List;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Bridges implements Parcelable {

    @SerializedName("meta")
    @Expose
    private Meta meta;
    @SerializedName("objects")
    @Expose
    private List<Bridge> bridges = null;
    public final static Parcelable.Creator<Bridges> CREATOR = new Creator<Bridges>() {


        @SuppressWarnings({
                "unchecked"
        })
        public Bridges createFromParcel(Parcel in) {
            return new Bridges(in);
        }

        public Bridges[] newArray(int size) {
            return (new Bridges[size]);
        }

    };

    protected Bridges(Parcel in) {
        this.meta = ((Meta) in.readValue((Meta.class.getClassLoader())));
        in.readList(this.bridges, (Bridge.class.getClassLoader()));
    }

    /**
     * No args constructor for use in serialization
     */
    public Bridges() {
    }

    /**
     * @param bridges
     * @param meta
     */
    public Bridges(Meta meta, List<Bridge> bridges) {
        super();
        this.meta = meta;
        this.bridges = bridges;
    }

    public Meta getMeta() {
        return meta;
    }

    public void setMeta(Meta meta) {
        this.meta = meta;
    }

    public Bridges withMeta(Meta meta) {
        this.meta = meta;
        return this;
    }

    public List<Bridge> getBridges() {
        return bridges;
    }

    public void setBridges(List<Bridge> bridges) {
        this.bridges = bridges;
    }

    public Bridges withObjects(List<Bridge> bridges) {
        this.bridges = bridges;
        return this;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(meta);
        dest.writeList(bridges);
    }

    public int describeContents() {
        return 0;
    }

}
