package com.example.pfi.Helper;

/**
 * Useful for starting threads
 */
public abstract class ThreadHelper {
    public static Thread startThread(Runnable runnable) {

        // TODO : Threads management

        Thread t = new Thread(runnable);

        t.start();

        return t;
    }
}
