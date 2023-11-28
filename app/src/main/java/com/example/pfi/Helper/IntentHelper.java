package com.example.pfi.Helper;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.example.pfi.Activities.CardTransitionActivity;
import com.example.pfi.Config;
import com.example.pfi.Logger;
import com.example.pfi.R;

/**
 * Useful for move between activities
 */
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

    public static <T extends Activity> void moveToActivityWithTransition(Activity src, Class<T> destination, Bundle bundle) {
        if (bundle == null)
            bundle = new Bundle();

        bundle.putSerializable(CardTransitionActivity.DESTINATION_ACTIVITY, destination);

        moveToActivity(src, CardTransitionActivity.class, bundle);
        src.overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
    }

    /**
     * Moves to the next activity and closes the current one.
     */
    public static <T extends Activity> void closeAndMove(Activity src, Class<T> destination, Bundle bundle) {
        moveToActivity(src, destination, bundle);
        src.finish();
    }
}
