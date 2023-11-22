package com.example.pfi;

import android.util.Log;

public abstract class Logger {
    private static final String TAG = "LOG-PFI";

    // region Log
    public static void log(String message) {
        if (Config.ENABLE_LOG)
            Log.d(TAG, message);
    }

    public static void log(Object obj) { log(obj.toString()); }
    // endregion
    // region Error
    public static void error(String message) {
        if (Config.ENABLE_LOG)
            Log.e(TAG, message);
    }

    public static void error(Object obj) { error(obj.toString()); }
    // endregion
}