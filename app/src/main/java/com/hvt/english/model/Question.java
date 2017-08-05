package com.hvt.english.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by doannh on 7/18/17.
 */

public class Question {
    public Question() {
    }

    public Question(int id, String question, Answer answerA, Answer answerB, Answer answerC, Answer answerD) {
        this.id = id;
        this.question = question;
        answers.add(answerA);
        answers.add(answerB);
        answers.add(answerC);
        answers.add(answerD);
    }

    public int id;

    @SerializedName("background_url")
    public String image;

    @SerializedName("content")
    public String question;

    @SerializedName("question_answers")
    public List<Answer> answers = new ArrayList<>();

    public Answer getAnswerA() {
        return answers.get(0);
    }

    public Answer getAnswerB() {
        return answers.get(1);
    }

    public Answer getAnswerC() {
        return answers.get(2);
    }

    public Answer getAnswerD() {
        return answers.get(3);
    }
}
