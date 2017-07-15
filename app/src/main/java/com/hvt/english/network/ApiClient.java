package com.hvt.english.network;

import com.hvt.english.model.Category;

import java.util.List;

import io.reactivex.Observable;

public interface ApiClient {
    Observable<List<Category>> getCategories();
}
