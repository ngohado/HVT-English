package com.hvt.english.model;

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

    public boolean isCorrect;
}
