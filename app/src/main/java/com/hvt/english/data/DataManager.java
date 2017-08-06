package com.hvt.english.data;

import com.activeandroid.query.Select;
import com.hvt.english.model.Category;
import com.hvt.english.model.Question;
import com.hvt.english.model.Section;
import com.hvt.english.network.ApiClient;
import com.hvt.english.util.SharedPrefUtil;
import com.hvt.english.util.TimeUtil;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class DataManager implements IDataManager {

    private ApiClient apiClient;

    public DataManager(ApiClient apiClient) {
        this.apiClient = apiClient;
    }

    @Override
    public Observable<List<Category>> getCategories() {
        return apiClient.getCategories()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public Observable<List<Question>> getQuestion(int categoryID) {
        return apiClient.getQuestions(categoryID)
                .map(questionResponse -> questionResponse.questions)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public int getTodayPoints() {
        StreakTable streakToday = new Select().from(StreakTable.class).where("day = ?", TimeUtil.generateStringTime()).executeSingle();
        if (streakToday != null) {
            return streakToday.points;
        }
        return 0;
    }

    @Override
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

    @Override
    public int getStreakDay() {
        createToday();
        List<StreakTable> streaks = new Select().from(StreakTable.class).where("points >= goals").orderBy("day DESC").execute();
        int streakDays = 0;
        for (int i = 0; i < streaks.size(); i++) {
            if ((i == 0 && streaks.get(0).completeGoals()) || (i == 1 && streaks.get(1).completeGoals())) {
                streakDays++;
            } else if (i > 1 && streaks.get(i).completeGoals() && streaks.get(i - 1).completeGoals()) {
                streakDays++;
            } else break;

        }
        return streakDays;
    }

    public void updateGoals(int newGoals) {
        StreakTable streakToday = new Select().from(StreakTable.class).where("day = ?", TimeUtil.generateStringTime()).executeSingle();
        if (streakToday == null) {
            createToday();
        } else {
            streakToday.goals = newGoals;
            streakToday.save();
        }
    }

    private void createToday() {
        StreakTable streakToday = new Select().from(StreakTable.class).where("day = ?", TimeUtil.generateStringTime()).executeSingle();
        if (streakToday == null) {
            streakToday = new StreakTable();
            streakToday.day = TimeUtil.generateStringTime();
            streakToday.points = 0;
            streakToday.goals = SharedPrefUtil.getInstance().getGoals();
            streakToday.save();
        }
    }

    @Override
    public Observable<Section> getDataSectionRemote(int categoryId) {
        return apiClient.getSection(categoryId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
