package com.hvt.english.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Hado on 7/18/17.
 */

public class Answer {
    public Answer() {
    }

    public Answer(String content, boolean isCorrect) {
        this.content = content;
        this.isCorrect = isCorrect;
    }

    public String content;

    @SerializedName("correct")
    public boolean isCorrect;
}
