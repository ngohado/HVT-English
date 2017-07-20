package com.hvt.english.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Hado on 7/15/17.
 */

public class Word extends Meaning implements Parcelable {

    public String image;

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
