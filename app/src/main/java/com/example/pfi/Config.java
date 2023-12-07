package com.example.pfi;

public abstract class Config {
    // region ENABLE
    /**
     * Enables to log message to the logcat
     */
    public static final boolean ENABLE_LOG = false;

    /**
     * Enables to play the audios
     */
    public static final boolean ENABLE_AUDIO = true;

    /**
     * Allows to skip the login
     */
    public static final boolean ENABLE_AUTOMATIC_LOGIN = false;

    /**
     * Simulates the time needed to fetch to a database if the login is valid or not
     */
    public static final boolean ENABLE_ARTIFICIAL_FETCH_TIME = true;
    // endregion
    // region AUDIT
    /**
     * Logs the movement between activities
     */
    public static final boolean AUDIT_ACTIVITY_MOVEMENT = false;

    /**
     * Logs the opening of a dialog from DialogHelper
     */
    public static final boolean AUDIT_DIALOG_OPEN = false;

    /**
     * Logs the loading of objects from the assets
     */
    public static final boolean AUDIT_OBJECT_LOAD = false;
    // endregion
}