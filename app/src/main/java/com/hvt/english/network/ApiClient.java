package com.hvt.english.network;

import com.hvt.english.model.Category;
import com.hvt.english.model.Section;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ApiClient {
    @GET("categories")
    Observable<List<Category>> getCategories();

    @GET("categories/{id}")
    Observable<Section> getSection(@Path("id") int categoryId);


    @GET("categories/{id}/lesson")
    Observable<QuestionResponse> getQuestions(@Path("id") int categoryId);
}
