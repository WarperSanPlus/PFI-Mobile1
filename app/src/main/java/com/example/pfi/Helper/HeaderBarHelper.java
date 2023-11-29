package com.example.pfi.Helper;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
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

        ImageButton menuBtn = activity.findViewById(R.id.headerBar_menuBtn);
        menuBtn.setOnClickListener(v -> showMenu(activity, v));
    }

    private static void showMenu(Context ctx, View anchor) {
        PopupMenu popupMenu = new PopupMenu(ctx, anchor);
        popupMenu.getMenuInflater().inflate(R.menu.more_options_menu, popupMenu.getMenu());

        popupMenu.getMenu()
                .findItem(R.id.more_options_lang_fr_en)
                .setTitle(getCurrentLang().toLanguageTag());

        popupMenu.setOnMenuItemClickListener(item -> {
            int itemId = item.getItemId();

            if (itemId == R.id.more_options_lang_fr_en) {
                switchLang(ctx, item);
                return true;
            }
            return false;
        });

        popupMenu.show();
    }

    private static void switchLang(Context ctx, MenuItem item) {
        // ...
        //setLang("en-rCA", ctx);
    }

    private static Locale getCurrentLang() {
        return ResourcesManager.getResources().getConfiguration().getLocales().get(0);
    }

    // idk if it works
    private static void setLang(String languageCode, Context ctx) {
        Locale myLocale = new Locale(languageCode);
        Resources res = ctx.getResources();
        DisplayMetrics dm = res.getDisplayMetrics();
        Configuration conf = res.getConfiguration();
        conf.setLocale(myLocale);
        res.updateConfiguration(conf, dm);


    }
}
