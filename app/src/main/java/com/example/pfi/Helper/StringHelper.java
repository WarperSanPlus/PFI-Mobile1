package com.example.pfi.Helper;

import androidx.annotation.StringRes;

public abstract class StringHelper {
    public static @StringRes int checkStringId(@StringRes int value, @StringRes int defaultValue) {
        return value == 0 ? defaultValue : value;
    }
}
