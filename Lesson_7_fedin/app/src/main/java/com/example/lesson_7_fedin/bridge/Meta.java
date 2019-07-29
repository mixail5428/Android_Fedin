package com.example.lesson_7_fedin.bridge;


import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Meta implements Parcelable
{

    @SerializedName("limit")
    @Expose
    private int limit;
    @SerializedName("next")
    @Expose
    private java.lang.Object next;
    @SerializedName("offset")
    @Expose
    private int offset;
    @SerializedName("previous")
    @Expose
    private java.lang.Object previous;
    @SerializedName("total_count")
    @Expose
    private int totalCount;
    public final static Parcelable.Creator<Meta> CREATOR = new Creator<Meta>() {


        @SuppressWarnings({
                "unchecked"
        })
        public Meta createFromParcel(Parcel in) {
            return new Meta(in);
        }

        public Meta[] newArray(int size) {
            return (new Meta[size]);
        }

    }
            ;

    protected Meta(Parcel in) {
        this.limit = ((int) in.readValue((int.class.getClassLoader())));
        this.next = ((java.lang.Object) in.readValue((Bridge.class.getClassLoader())));
        this.offset = ((int) in.readValue((int.class.getClassLoader())));
        this.previous = ((java.lang.Object) in.readValue((Bridge.class.getClassLoader())));
        this.totalCount = ((int) in.readValue((int.class.getClassLoader())));
    }

    /**
     * No args constructor for use in serialization
     *
     */
    public Meta() {
    }

    /**
     *
     * @param limit
     * @param previous
     * @param totalCount
     * @param next
     * @param offset
     */
    public Meta(int limit, java.lang.Object next, int offset, java.lang.Object previous, int totalCount) {
        super();
        this.limit = limit;
        this.next = next;
        this.offset = offset;
        this.previous = previous;
        this.totalCount = totalCount;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public Meta withLimit(int limit) {
        this.limit = limit;
        return this;
    }

    public java.lang.Object getNext() {
        return next;
    }

    public void setNext(java.lang.Object next) {
        this.next = next;
    }

    public Meta withNext(java.lang.Object next) {
        this.next = next;
        return this;
    }

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public Meta withOffset(int offset) {
        this.offset = offset;
        return this;
    }

    public java.lang.Object getPrevious() {
        return previous;
    }

    public void setPrevious(java.lang.Object previous) {
        this.previous = previous;
    }

    public Meta withPrevious(java.lang.Object previous) {
        this.previous = previous;
        return this;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public Meta withTotalCount(int totalCount) {
        this.totalCount = totalCount;
        return this;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(limit);
        dest.writeValue(next);
        dest.writeValue(offset);
        dest.writeValue(previous);
        dest.writeValue(totalCount);
    }

    public int describeContents() {
        return 0;
    }

}