package com.hvt.english.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Hado on 7/18/17.
 */

public class Meaning implements Parcelable {


    public int id;

    @SerializedName("content")
    public String content;

    @SerializedName("meaning")
    public String meaning;

    @SerializedName("audio_")
    public String audio;

    public boolean correct = false;

    public Meaning(int id, String content, String meaning, String audio) {
        this.id = id;
        this.content = content;
        this.meaning = meaning;
        this.audio = audio;
    }

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

    public static final Creator<Meaning> CREATOR = new Creator<Meaning>() {
        @Override
        public Meaning createFromParcel(Parcel source) {
            return new Meaning(source);
        }

        @Override
        public Meaning[] newArray(int size) {
            return new Meaning[size];
        }
    };
}
