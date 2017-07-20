package com.hvt.english.network;

import com.hvt.english.model.Category;
import com.hvt.english.model.Sentence;
import com.hvt.english.model.Word;

import java.util.List;

import io.reactivex.Observable;

public interface ApiClient {
    Observable<List<Category>> getCategories();

    Observable<List<Word>> getWords(int categoryId);

    Observable<List<Sentence>> getSentences(int categoryId);
}
