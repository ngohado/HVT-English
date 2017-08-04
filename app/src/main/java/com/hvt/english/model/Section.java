package com.hvt.english.model;

import java.util.List;

/**
 * Created by doannh on 7/20/17.
 */

public class Section {

    public List<Word> words;
    public List<Sentence> sentences;

    public Section() {
    }

    public Section(List<Word> words, List<Sentence> sentences) {
        this.words = words;
        this.sentences = sentences;
    }
}
