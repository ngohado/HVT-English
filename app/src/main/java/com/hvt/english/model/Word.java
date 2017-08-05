package com.hvt.english.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Hado on 7/15/17.
 */

public class Word extends Meaning implements Parcelable {

    @SerializedName("picture_url")
    public String image;

    public Word(int id, String content, String meaning, String audio, String image) {
        super(id, content, meaning, audio);
        this.image = image;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest, flags);
        dest.writeString(this.image);
    }


    public Word() {
    }

    protected Word(Parcel in) {
        super(in);
        this.image = in.readString();
    }

    public static final Creator<Word> CREATOR = new Creator<Word>() {
        @Override
        public Word createFromParcel(Parcel source) {
            return new Word(source);
        }

        @Override
        public Word[] newArray(int size) {
            return new Word[size];
        }
    };
}
