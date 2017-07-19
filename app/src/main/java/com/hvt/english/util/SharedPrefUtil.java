package com.hvt.english.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.hvt.english.Constant;
import com.hvt.english.MyApplication;

/**
 * Created by Hado on 7/19/17.
 */

public class SharedPrefUtil {
    private SharedPreferences.Editor editor;
    private SharedPreferences sharedPreferences;
    private static SharedPrefUtil sharedPrefUtil;

    private SharedPrefUtil(Context context) {
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context.getApplicationContext());
        editor = sharedPreferences.edit();
    }

    public static SharedPrefUtil getInstance() {
        if (sharedPrefUtil == null) {
            sharedPrefUtil = new SharedPrefUtil(MyApplication.getApplication().getApplicationContext());
        }
        return sharedPrefUtil;
    }

    public void putString(String key, String value) {
        editor.putString(key, value);
        editor.commit();
    }

    public void putInt(String key, int value) {
        editor.putInt(key, value);
        editor.commit();
    }

    public void putBoolean(String key, boolean value) {
        editor.putBoolean(key, value);
        editor.commit();
    }

    public String getString(String key) {
        return sharedPreferences.getString(key, "");
    }

    public int getInt(String key) {
        return sharedPreferences.getInt(key, 0);
    }

    public boolean getBoolean(String key) {
        return sharedPreferences.getBoolean(key, false);
    }

    public int getGoals() {
        return sharedPreferences.getInt(Constant.GOALS_SCORE_DATA, Constant.GOALS_SCORE_DEF);
    }

    public void setGoals(int newGoals) {
        editor.putInt(Constant.GOALS_SCORE_DATA, newGoals).commit();
    }

}
