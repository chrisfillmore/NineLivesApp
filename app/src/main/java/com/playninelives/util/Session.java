package com.playninelives.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

/**
 * Created by computer on 2016-03-29.
 */
public class Session {

    public static final String USER_ID_PROP = "userId";
    public static final String USER_NAME_PROP = "userName";

    private SharedPreferences prefs;

    public Session(Context c) {
        prefs = PreferenceManager.getDefaultSharedPreferences(c);
    }

    public int getUserId() {
        return prefs.getInt(USER_ID_PROP, -1);
    }

    public void setUserId(int id) {
        prefs.edit().putInt(USER_ID_PROP, id).apply();
    }

    public void signOut() {
        SharedPreferences.Editor editor = prefs.edit();
        editor.putInt(USER_ID_PROP, -1).apply();
        editor.putString(USER_NAME_PROP, null);
    }

    public void setUserName(String name) {
        prefs.edit().putString(USER_NAME_PROP, name).apply();
    }

    public String getUserName() {
        return prefs.getString(USER_NAME_PROP, null);
    }
}
