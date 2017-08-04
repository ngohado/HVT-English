package com.hvt.english.model;

/**
 * Created by doannh on 7/18/17.
 */

public class Question {
    public Question() {
    }

    public Question(int id, String question, Answer answerA, Answer answerB, Answer answerC, Answer answerD) {
        this.id = id;
        this.question = question;
        this.answerA = answerA;
        this.answerB = answerB;
        this.answerC = answerC;
        this.answerD = answerD;
    }

    public int id;

    public String question;

    public Answer answerA;

    public Answer answerB;

    public Answer answerC;

    public Answer answerD;
}
