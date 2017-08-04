package com.hvt.english.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Hado on 7/15/17.
 */

public class Sentence extends Meaning implements Parcelable {

    public String describe;

    public Sentence(int id, String content, String meaning, String audio, String describe) {
        super(id, content, meaning, audio);
        this.describe = describe;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest, flags);
        dest.writeString(this.describe);
    }

    public Sentence() {
    }

    protected Sentence(Parcel in) {
        super(in);
        this.describe = in.readString();
    }

    public static final Creator<Sentence> CREATOR = new Creator<Sentence>() {
        @Override
        public Sentence createFromParcel(Parcel source) {
            return new Sentence(source);
        }

        @Override
        public Sentence[] newArray(int size) {
            return new Sentence[size];
        }
    };
}
