package com.hvt.english.data;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

/**
 * Created by Hado on 7/19/17.
 */

@Table(name = "StreakTable")
public class StreakTable extends Model {

    @Column(name = "day")
    public long day;

    @Column(name = "points")
    public int points;

    @Column(name = "goals")
    public int goals;

    public boolean completeGoals() {
        return points >= goals;
    }

}
