package com.example.pfi;

import android.util.Log;

public abstract class Logger {
    public static void log(String message) {
        if (Config.ENABLE_LOG)
            Log.d("LOG-PFI", message);
    }

    public static void log(Object obj) { log(obj.toString()); }
}