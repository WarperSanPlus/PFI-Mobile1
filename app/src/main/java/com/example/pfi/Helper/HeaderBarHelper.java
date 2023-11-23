package com.example.pfi.Helper;

import android.app.Activity;
import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.PopupMenu;
import android.widget.TextView;

import androidx.annotation.StringRes;

import com.example.pfi.R;

import java.util.Locale;

public abstract class HeaderBarHelper {
    private static boolean isEnglish = false;

    public static void setHeaderBar(Activity activity, @StringRes int title) {
        // Set title
        TextView headerContent = activity.findViewById(R.id.headerBar_textContent);
        headerContent.setText(title);

        // Set buttons
        ImageButton panierBtn = activity.findViewById(R.id.headerBar_panierBtn);
        panierBtn.setOnClickListener(v -> {
            // ...
        });

        ImageButton menuBtn = activity.findViewById(R.id.headerBar_menuBtn);
        menuBtn.setOnClickListener(v -> showMenu(activity, v));
    }

    private static void showMenu(Context ctx, View anchor) {
        PopupMenu popupMenu = new PopupMenu(ctx, anchor);
        popupMenu.getMenuInflater().inflate(R.menu.more_options_menu, popupMenu.getMenu());

        popupMenu.getMenu()
                .findItem(R.id.more_options_lang_fr_en)
                .setTitle(getCurrentLang(ctx).getDisplayName());

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
    }

    private static Locale getCurrentLang(Context ctx) {
        return ctx.getResources().getConfiguration().getLocales().get(0);
    }

    private static void setLang(String languageCode, Context ctx) {
        Locale locale = new Locale(languageCode);
        Locale.setDefault(locale);

        Resources resources = ctx.getResources();
        Configuration config = resources.getConfiguration();
        config.setLocale(locale);
        resources.updateConfiguration(config, resources.getDisplayMetrics());
    }
}
