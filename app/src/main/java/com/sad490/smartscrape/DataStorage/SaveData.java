package com.sad490.smartscrape.DataStorage;

import android.app.Activity;
import android.content.SharedPreferences;
import android.preference.PreferenceActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sad490 on 1/31/18.
 */

public class SaveData {

    public static boolean SaveSharedPreference (SharedPreferences sharedPreferences, List<String> Keys, List<String> Values) {
        if (Keys.size() != Values.size()) {
            return false;
        }
        SharedPreferences.Editor editor = sharedPreferences.edit();
        for (int i = 0; i < Keys.size(); ++i) {
            editor.putString(Keys.get(i), Values.get(i));
        }
        editor.apply();
        return true;
    }
}
