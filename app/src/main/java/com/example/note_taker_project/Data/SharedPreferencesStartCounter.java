package com.example.note_taker_project.Data;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPreferencesStartCounter {
    private SharedPreferences sharedPreferences;
    private final String SHARED_PREFERENCE_NAME = "SHARED_PREFERENCE_NAME";
    private int counter;

    public SharedPreferencesStartCounter(Context context) {
        this.sharedPreferences = context.getSharedPreferences(SHARED_PREFERENCE_NAME, Context.MODE_PRIVATE);
        counter = sharedPreferences.getInt(SHARED_PREFERENCE_NAME, 0);
    }

    public int getCounter() {
        return counter;
    }

    public void incrementCounter() {
        counter++;
        sharedPreferences.edit().putInt(SHARED_PREFERENCE_NAME, counter).commit();
    }

}
