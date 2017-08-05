package com.hvt.english.data;

import com.hvt.english.model.Category;
import com.hvt.english.model.Question;
import com.hvt.english.model.Section;

import java.util.List;

import io.reactivex.Observable;

public interface IDataManager {
    Observable<List<Category>> getCategories();

    Observable<List<Question>> getQuestion(int categoryID);

    int getTodayPoints();

    void savePoints(int points);

    int getStreakDay();

    Observable<Section> getDataSectionRemote(int categoryId);
}
