package com.hvt.english.network;

import com.google.gson.annotations.SerializedName;
import com.hvt.english.model.Question;

import java.util.List;

public class QuestionResponse {
    @SerializedName("questions")
    public List<Question> questions;
}
