package com.example.lesson_8_fedin.localDatabase;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Note implements Parcelable {

    @PrimaryKey(autoGenerate = true)
    private int id;

    private String title;

    private String description;

    private int color;

    private int archived;

    public Note(){
        this("", "", 0);
    }

    public Note(String title, String description, int color){
        this.title = title;
        this.description = description;
        this.color = color;
        archived = 0;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public int getArchived() {
        return archived;
    }

    public void setArchived(int archived) {
        this.archived = archived;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeString(this.title);
        dest.writeString(this.description);
        dest.writeInt(this.color);
        dest.writeInt(this.archived);
    }

    protected Note(Parcel in) {
        this.id = in.readInt();
        this.title = in.readString();
        this.description = in.readString();
        this.color = in.readInt();
        this.archived = in.readInt();
    }

    public static final Parcelable.Creator<Note> CREATOR = new Parcelable.Creator<Note>() {
        @Override
        public Note createFromParcel(Parcel source) {
            return new Note(source);
        }

        @Override
        public Note[] newArray(int size) {
            return new Note[size];
        }
    };
}
