package com.hvt.english.data;

import com.activeandroid.query.Select;
import com.hvt.english.model.Category;
import com.hvt.english.model.Question;
import com.hvt.english.model.Section;
import com.hvt.english.model.Sentence;
import com.hvt.english.model.Word;
import com.hvt.english.network.ApiClient;
import com.hvt.english.util.SharedPrefUtil;
import com.hvt.english.util.TimeUtil;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class DataManager {

    private ApiClient apiClient;

    public DataManager(ApiClient apiClient) {
        this.apiClient = apiClient;
    }

    public Observable<List<Category>> getCategories() {
        return apiClient.getCategories()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public Observable<List<Question>> getQuestion(int categoryID) {
        return apiClient.getQuestions(categoryID)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public int getTodayPoints() {
        StreakTable streakToday = new Select().from(StreakTable.class).where("day = ?", TimeUtil.generateStringTime()).executeSingle();
        if (streakToday != null) {
            return streakToday.points;
        }
        return 0;
    }

    public void savePoints(int points) {
        StreakTable streakToday = new Select().from(StreakTable.class).where("day = ?", TimeUtil.generateStringTime()).executeSingle();
        if (streakToday == null) {
            streakToday = new StreakTable();
            streakToday.day = TimeUtil.generateStringTime();
            streakToday.points = points;
            streakToday.goals = SharedPrefUtil.getInstance().getGoals();
        } else {
            streakToday.points += points;
        }
        streakToday.save();
    }

    public int getStreakDay() {
        return 0;
    }

    public Observable<Section> getDataSectionRemote(int categoryId) {
        return Observable.zip(apiClient.getWords(categoryId), apiClient.getSentences(categoryId), Section::new)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public Observable<List<Word>> getWordsRemote(int categoryId) {
        return apiClient.getWords(categoryId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public Observable<List<Sentence>> getSentencesRemote(int categoryId) {
        return apiClient.getSentences(categoryId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
