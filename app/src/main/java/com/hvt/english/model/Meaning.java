package com.hvt.english.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Hado on 7/18/17.
 */

public class Meaning implements Parcelable {
    public int id;

    public String content;

    public String meaning;

    public String audio;

    public boolean correct = false;


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeString(this.content);
        dest.writeString(this.meaning);
        dest.writeString(this.audio);
        dest.writeByte(this.correct ? (byte) 1 : (byte) 0);
    }

    public Meaning() {
    }

    protected Meaning(Parcel in) {
        this.id = in.readInt();
        this.content = in.readString();
        this.meaning = in.readString();
        this.audio = in.readString();
        this.correct = in.readByte() != 0;
    }

}
