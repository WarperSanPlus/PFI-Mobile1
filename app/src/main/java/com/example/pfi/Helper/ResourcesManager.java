package com.example.pfi.Helper;

import android.content.Context;
import android.content.res.Resources;

import androidx.annotation.DrawableRes;
import androidx.annotation.StringRes;

public abstract class ResourcesManager {
    public static Context Context;

    public static Resources getResources() { return Context.getResources(); }


    public static @DrawableRes int getDrawableRes(String name) {
        return getResources().getIdentifier(name, "drawable", Context.getPackageName());
    }


    // region String
    public static String getString(@StringRes int id) {
        return id != 0 ? Context.getString(id) : "";
    }

    public static @StringRes int getStringIdentifier(String name) {
        return getResources().getIdentifier(name, "string", Context.getPackageName());
    }
    // endregion
}
