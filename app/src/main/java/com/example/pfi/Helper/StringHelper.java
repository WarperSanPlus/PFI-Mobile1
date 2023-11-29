package com.example.pfi.Helper;

import androidx.annotation.StringRes;

public abstract class StringHelper {
    public static @StringRes int checkStringId(@StringRes int value, @StringRes int defaultValue) {
        return value == 0 ? defaultValue : value;
    }

    public static String formatString(@StringRes int resId, Object...args) {
        if (resId == 0)
            return "";

        String text = ResourcesManager.getString(resId); // Get text from id

        // Replace arguments
        // ${0} est con => Popo est con
        for (int i = 0; i < args.length; i++) {
            text = text.replace("${" + i + "}", args[i].toString());
        }
        return text;
    }
}
