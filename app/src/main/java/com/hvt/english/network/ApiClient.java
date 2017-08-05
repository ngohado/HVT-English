package com.hvt.english.network;

import com.hvt.english.model.Category;
import com.hvt.english.model.Question;
import com.hvt.english.model.Section;
import com.hvt.english.model.Sentence;
import com.hvt.english.model.Word;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ApiClient {
    @GET("categories")
    Observable<List<Category>> getCategories();

    Observable<List<Word>> getWords(int categoryId);

    @GET("categories/{id}")
    Observable<Section> getSection(@Path("id") int categoryId);

    Observable<List<Sentence>> getSentences(int categoryId);

    @GET("categories/{id}/lesson")
    Observable<List<Question>> getQuestions(@Path("id") int categoryId);
}
