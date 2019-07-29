package com.example.lesson_7_fedin.bridge;

import java.util.List;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Bridge implements Parcelable {

    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("description_eng")
    @Expose
    private String descriptionEng;
    @SerializedName("divorces")
    @Expose
    private List<Divorce> divorces = null;
    @SerializedName("id")
    @Expose
    private int id;
    @SerializedName("lat")
    @Expose
    private float lat;
    @SerializedName("lng")
    @Expose
    private float lng;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("name_eng")
    @Expose
    private String nameEng;
    @SerializedName("photo_close")
    @Expose
    private String photoClose;
    @SerializedName("photo_open")
    @Expose
    private String photoOpen;
    @SerializedName("public")
    @Expose
    private boolean _public;
    @SerializedName("resource_uri")
    @Expose
    private String resourceUri;

    /**
     * No args constructor for use in serialization
     */
    public Bridge() {
    }

    /**
     * @param id
     * @param divorces
     * @param photoOpen
     * @param _public
     * @param nameEng
     * @param description
     * @param photoClose
     * @param name
     * @param lng
     * @param resourceUri
     * @param lat
     * @param descriptionEng
     */
    public Bridge(String description, String descriptionEng, List<Divorce> divorces, int id, float lat, float lng, String name, String nameEng, String photoClose, String photoOpen, boolean _public, String resourceUri) {
        super();
        this.description = description;
        this.descriptionEng = descriptionEng;
        this.divorces = divorces;
        this.id = id;
        this.lat = lat;
        this.lng = lng;
        this.name = name;
        this.nameEng = nameEng;
        this.photoClose = photoClose;
        this.photoOpen = photoOpen;
        this._public = _public;
        this.resourceUri = resourceUri;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Bridge withDescription(String description) {
        this.description = description;
        return this;
    }

    public String getDescriptionEng() {
        return descriptionEng;
    }

    public void setDescriptionEng(String descriptionEng) {
        this.descriptionEng = descriptionEng;
    }

    public Bridge withDescriptionEng(String descriptionEng) {
        this.descriptionEng = descriptionEng;
        return this;
    }

    public List<Divorce> getDivorces() {
        return divorces;
    }

    public void setDivorces(List<Divorce> divorces) {
        this.divorces = divorces;
    }

    public Bridge withDivorces(List<Divorce> divorces) {
        this.divorces = divorces;
        return this;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Bridge withId(int id) {
        this.id = id;
        return this;
    }

    public float getLat() {
        return lat;
    }

    public void setLat(float lat) {
        this.lat = lat;
    }

    public Bridge withLat(float lat) {
        this.lat = lat;
        return this;
    }

    public float getLng() {
        return lng;
    }

    public void setLng(float lng) {
        this.lng = lng;
    }

    public Bridge withLng(float lng) {
        this.lng = lng;
        return this;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Bridge withName(String name) {
        this.name = name;
        return this;
    }

    public String getNameEng() {
        return nameEng;
    }

    public void setNameEng(String nameEng) {
        this.nameEng = nameEng;
    }

    public Bridge withNameEng(String nameEng) {
        this.nameEng = nameEng;
        return this;
    }

    public String getPhotoClose() {
        return photoClose;
    }

    public void setPhotoClose(String photoClose) {
        this.photoClose = photoClose;
    }

    public Bridge withPhotoClose(String photoClose) {
        this.photoClose = photoClose;
        return this;
    }

    public String getPhotoOpen() {
        return photoOpen;
    }

    public void setPhotoOpen(String photoOpen) {
        this.photoOpen = photoOpen;
    }

    public Bridge withPhotoOpen(String photoOpen) {
        this.photoOpen = photoOpen;
        return this;
    }

    public boolean isPublic() {
        return _public;
    }

    public void setPublic(boolean _public) {
        this._public = _public;
    }

    public Bridge withPublic(boolean _public) {
        this._public = _public;
        return this;
    }

    public String getResourceUri() {
        return resourceUri;
    }

    public void setResourceUri(String resourceUri) {
        this.resourceUri = resourceUri;
    }

    public Bridge withResourceUri(String resourceUri) {
        this.resourceUri = resourceUri;
        return this;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.description);
        dest.writeString(this.descriptionEng);
        dest.writeTypedList(this.divorces);
        dest.writeInt(this.id);
        dest.writeFloat(this.lat);
        dest.writeFloat(this.lng);
        dest.writeString(this.name);
        dest.writeString(this.nameEng);
        dest.writeString(this.photoClose);
        dest.writeString(this.photoOpen);
        dest.writeByte(this._public ? (byte) 1 : (byte) 0);
        dest.writeString(this.resourceUri);
    }

    protected Bridge(Parcel in) {
        this.description = in.readString();
        this.descriptionEng = in.readString();
        this.divorces = in.createTypedArrayList(Divorce.CREATOR);
        this.id = in.readInt();
        this.lat = in.readFloat();
        this.lng = in.readFloat();
        this.name = in.readString();
        this.nameEng = in.readString();
        this.photoClose = in.readString();
        this.photoOpen = in.readString();
        this._public = in.readByte() != 0;
        this.resourceUri = in.readString();
    }

    public static final Creator<Bridge> CREATOR = new Creator<Bridge>() {
        @Override
        public Bridge createFromParcel(Parcel source) {
            return new Bridge(source);
        }

        @Override
        public Bridge[] newArray(int size) {
            return new Bridge[size];
        }
    };
}