package com.example.pfi.Helper;

import com.example.pfi.Logger;

import java.util.concurrent.Callable;

/**
 * Useful for starting threads
 */
public abstract class ThreadHelper {
    public static void startThread(Runnable runnable) {

        // TODO : Threads management

        new Thread(runnable).start();
    }

    public static void startThread(Callable call) {
        startThread(() -> {
            try {
                call.call();
            } catch (Exception e) {
                Logger.error(e);
            }
        });
    }
}
