package com.example.lesson_6_fedin;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Date;

public class DataCounter implements Parcelable {

    static final int FIRST_TYPE = 1;
    static final int TWO_TYPE = 2;
    static final int THREE_TYPE = 3;

    private String title;
    private int icon;
    private int idUser;
    private int oneCounter;
    private int twoCounter;
    private int threeCounter;
    private int typeCounter;
    private Date lastIndication;
    private Date nextIndication;

    public DataCounter(String title, int icon, int idUser, int oneCounter, Date lastIndication, Date nextIndication){
        this.title = title;
        this.icon = icon;
        this.idUser = idUser;
        this.oneCounter = oneCounter;
        this.lastIndication = lastIndication;
        this.nextIndication = nextIndication;
        typeCounter = FIRST_TYPE;
    }

    public DataCounter(String title, int icon, int idUser, int oneCounter, int twoCounter, Date lastIndication, Date nextIndication){
        this(title, icon, idUser, oneCounter, lastIndication, nextIndication);
        this.twoCounter = twoCounter;
        typeCounter = TWO_TYPE;
    }

    public DataCounter(String title, int icon, int idUser, int oneCounter, int twoCounter, int threeCounter,
                       Date lastIndication, Date nextIndication){
        this(title, icon, idUser, oneCounter, twoCounter, lastIndication, nextIndication);
        this.threeCounter = threeCounter;
        typeCounter = THREE_TYPE;
    }

    public static int getFirstType() {
        return FIRST_TYPE;
    }

    public static int getTwoType() {
        return TWO_TYPE;
    }

    public static int getThreeType() {
        return THREE_TYPE;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public int getOneCounter() {
        return oneCounter;
    }

    public void setOneCounter(int oneCounter) {
        this.oneCounter = oneCounter;
    }

    public int getTwoCounter() {
        return twoCounter;
    }

    public void setTwoCounter(int twoCounter) {
        this.twoCounter = twoCounter;
    }

    public int getThreeCounter() {
        return threeCounter;
    }

    public void setThreeCounter(int threeCounter) {
        this.threeCounter = threeCounter;
    }

    public int getTypeCounter() {
        return typeCounter;
    }

    public void setTypeCounter(int typeCounter) {
        this.typeCounter = typeCounter;
    }

    public Date getLastIndication() {
        return lastIndication;
    }

    public void setLastIndication(Date lastIndication) {
        this.lastIndication = lastIndication;
    }

    public Date getNextIndication() {
        return nextIndication;
    }

    public void setNextIndication(Date nextIndication) {
        this.nextIndication = nextIndication;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.title);
        dest.writeInt(this.icon);
        dest.writeInt(this.idUser);
        dest.writeInt(this.oneCounter);
        dest.writeInt(this.twoCounter);
        dest.writeInt(this.threeCounter);
        dest.writeInt(this.typeCounter);
        dest.writeLong(this.lastIndication != null ? this.lastIndication.getTime() : -1);
        dest.writeLong(this.nextIndication != null ? this.nextIndication.getTime() : -1);
    }

    protected DataCounter(Parcel in) {
        this.title = in.readString();
        this.icon = in.readInt();
        this.idUser = in.readInt();
        this.oneCounter = in.readInt();
        this.twoCounter = in.readInt();
        this.threeCounter = in.readInt();
        this.typeCounter = in.readInt();
        long tmpLastIndication = in.readLong();
        this.lastIndication = tmpLastIndication == -1 ? null : new Date(tmpLastIndication);
        long tmpNextIndication = in.readLong();
        this.nextIndication = tmpNextIndication == -1 ? null : new Date(tmpNextIndication);
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
