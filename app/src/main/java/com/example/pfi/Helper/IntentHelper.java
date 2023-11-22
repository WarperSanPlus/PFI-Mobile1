package com.example.pfi.Helper;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.example.pfi.Config;
import com.example.pfi.Logger;

public abstract class IntentHelper {
    /**
     * Moves to the next activity and shares the given bundle to the destination.
     */
    public static <T extends Activity> void moveToActivity(Activity src, Class<T> destination, Bundle bundle) {
        Intent changeActivityIntent = new Intent(src, destination);

        if (bundle != null)
            changeActivityIntent.replaceExtras(bundle);

        src.startActivity(changeActivityIntent);

        if (Config.AUDIT_ACTIVITY_MOVEMENT)
            Logger.log("Moved from \"" + src.getClass().getName() + "\" to \"" + destination.getName() + "\".");
    }

    /**
     * Moves to the next activity.
     */
    public static <T extends Activity> void moveToActivity(Activity src, Class<T> destination) {
        moveToActivity(src, destination, null);
    }

    /**
     * Moves to the next activity and closes the current one.
     */
    public static <T extends Activity> void closeAndMove(Activity src, Class<T> destination) {
        moveToActivity(src, destination);
        src.finish();
    }
}
