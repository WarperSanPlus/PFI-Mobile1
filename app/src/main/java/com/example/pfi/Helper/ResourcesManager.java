package com.example.pfi.Helper;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.Resources;

import androidx.annotation.DrawableRes;
import androidx.annotation.StringRes;

public abstract class ResourcesManager {
    // region Context
    public static Context Context;

    private static Context getContext() { return Context; }
    // endregion
    // region Resources

    public static Resources getResources() { return getContext().getResources(); }

    @SuppressLint("DiscouragedApi")
    private static int getResourceIdentifier(String name, String defType) {
        return getResources().getIdentifier(name, defType, getContext().getPackageName());
    }

    public static @DrawableRes int getDrawableRes(String name) { return getResourceIdentifier(name, "drawable"); }
    public static @StringRes int getStringIdentifier(String name) { return getResourceIdentifier(name, "string"); }

    // endregion
    // region String

    public static String getString(@StringRes int id) {
        return id != 0 ? getContext().getString(id) : null;
    }

    public static String getString(@StringRes int id, @StringRes int defaultValue) {
        return getContext().getString(StringHelper.checkStringId(id, defaultValue));
    }

    // endregion
}