package com.hvt.english.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Hado on 7/15/17.
 */

public class Category implements Parcelable {

    public Category(int id, String image, String name) {
        this.id = id;
        this.image = image;
        this.name = name;
    }

    public Category() {

    }

    @SerializedName("id")
    public int id;

    @SerializedName("background_url")
    public String image;

    @SerializedName("description")
    public String name;


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeString(this.image);
        dest.writeString(this.name);
    }

    protected Category(Parcel in) {
        this.id = in.readInt();
        this.image = in.readString();
        this.name = in.readString();
    }

    public static final Creator<Category> CREATOR = new Creator<Category>() {
        @Override
        public Category createFromParcel(Parcel source) {
            return new Category(source);
        }

        @Override
        public Category[] newArray(int size) {
            return new Category[size];
        }
    };
}
