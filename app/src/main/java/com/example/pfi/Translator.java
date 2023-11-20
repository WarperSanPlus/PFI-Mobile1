package com.example.pfi;

import android.app.Activity;

import androidx.annotation.StringRes;

public class Translator {
    public static String formatString(Activity activity, @StringRes int resId, Object...args) {
        String text = activity.getString(resId); // Get text from id

        // Replace arguments
        // ${0} est con => Popo est con
        for (int i = 0; i < args.length; i++) {
            text = text.replace("${" + i + "}", args[i].toString());
        }
        return text;
    }
}
