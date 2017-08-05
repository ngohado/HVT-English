package com.hvt.english.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by doannh on 7/20/17.
 */

public class Section {
    public int id;

    @SerializedName("background")
    public String background;

    @SerializedName("words")
    public List<Word> words;

    @SerializedName("sentences")
    public List<Sentence> sentences;

    public Section() {
    }

    public Section(List<Word> words, List<Sentence> sentences) {
        this.words = words;
        this.sentences = sentences;
    }
}
