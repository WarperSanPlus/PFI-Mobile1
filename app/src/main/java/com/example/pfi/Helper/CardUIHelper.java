package com.example.pfi.Helper;

import android.app.Activity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.example.pfi.R;

/**
 * Useful to move to an activity compose of a "Card"
 */
public abstract class CardUIHelper {
    public static void endActivity(Activity src, View card) {
        Animation aniSlide = AnimationUtils.loadAnimation(src.getApplicationContext(), R.anim.slide_out_card);
        card.startAnimation(aniSlide);

        ThreadHelper.startThread(() -> {
            try {
                Thread.sleep(350);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            src.finish();
            src.overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
        });
    }
}
