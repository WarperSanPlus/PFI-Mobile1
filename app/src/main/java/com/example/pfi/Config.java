package com.example.pfi;

public abstract class Config {
    // region ENABLE
    public static final boolean ENABLE_LOG = true;
    public static final boolean ENABLE_ADS = true;
    public static final boolean ENABLE_AUDIO = false;
    public static final boolean ENABLE_AUTOMATIC_LOGIN = true;
    // endregion
    // region AUDIT
    public static final boolean AUDIT_ACTIVITY_MOVEMENT = false;
    public static final boolean AUDIT_DIALOG_OPEN = false;
    public static final boolean AUDIT_OBJECT_LOAD = false;
    // endregion
}
