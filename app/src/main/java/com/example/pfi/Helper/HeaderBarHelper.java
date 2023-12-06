package com.example.pfi.Helper;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.media.Image;
import android.util.DisplayMetrics;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.PopupMenu;
import android.widget.TextView;

import androidx.annotation.StringRes;

import com.example.pfi.Activities.ActivityPanier;
import com.example.pfi.R;

import java.util.Locale;

/**
 * Useful for displaying the header bar
 */
public abstract class HeaderBarHelper {
    public static void setHeaderBar(Activity activity, @StringRes int title) {
        // Set title
        TextView headerContent = activity.findViewById(R.id.headerBar_textContent);
        headerContent.setText(title);

        // Set buttons
        ImageButton panierBtn = activity.findViewById(R.id.details_panierBtn);
        panierBtn.setOnClickListener(v -> {
            IntentHelper.moveToActivityWithTransition(activity, ActivityPanier.class, null);
        });

        ImageButton accountBtn = activity.findViewById(R.id.headerBar_btnAccount);
    }
}
