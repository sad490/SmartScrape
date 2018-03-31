package com.sad490.smartscrape.DataStorage;

import android.content.SharedPreferences;

import java.security.Key;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by sad490 on 1/31/18.
 */

public class ExtractData {
    public static boolean ExtractSharedPreference (SharedPreferences sharedPreferences, List<String> Keys, List<String> Values) {
        if (!Keys.isEmpty() || !Values.isEmpty()) {
            return false;
        }
        Map<String, String> contain = (Map<String, String>)sharedPreferences.getAll();
        Keys.addAll(contain.keySet());
        Values.addAll(contain.values());
        return true;
    }
}
