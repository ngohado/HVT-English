package com.hvt.english.data;

import com.hvt.english.model.Category;
import com.hvt.english.model.Question;
import com.hvt.english.model.Section;
import com.hvt.english.model.Sentence;
import com.hvt.english.model.Word;

import java.util.List;

import io.reactivex.Observable;

public interface IDataManager {
    Observable<List<Category>> getCategories();

    Observable<List<Question>> getQuestion(int categoryID);

    int getTodayPoints();

    void savePoints(int points);

    int getStreakDay();

    Observable<Section> getDataSectionRemote(int categoryId);

    Observable<List<Word>> getWordsRemote(int categoryId);

    Observable<List<Sentence>> getSentencesRemote(int categoryId);
}
