package com.example.pfi.Activities;

import androidx.annotation.IdRes;
import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;

import com.example.pfi.Classes.Article;
import com.example.pfi.Classes.Category;
import com.example.pfi.Dialogs.ProductPreviewDialog;
import com.example.pfi.Fragments.CategoryDisplay;
import com.example.pfi.Helper.DialogHelper;
import com.example.pfi.Helper.FragmentHelper;
import com.example.pfi.Helper.HeaderBarHelper;
import com.example.pfi.Helper.SoundHelper;
import com.example.pfi.R;

import java.io.IOException;
import java.util.ArrayList;

public class ActivityListe extends AppCompatActivity {
    MediaPlayer mp = new MediaPlayer();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_liste);

        HeaderBarHelper.setHeaderBar(this, R.string.activity_liste_name);

        displayCategories(Category.loadCategories(), R.id.listeActivity_itemListe);
    }

    /**
     * Creates a CategoryDisplay for every category given.
     */
    private void displayCategories(ArrayList<Category> categories, @IdRes int parent) {
        FragmentHelper.createFragments(parent, categories, CategoryDisplay::newInstance, this);
    }

    /**
     * Opens a ProductPreviewDialog for the given article.
     */
    public void openDialog(Article article) {
        DialogHelper.openDialog(() -> new ProductPreviewDialog(article), getSupportFragmentManager(), this.toString());

        //mp = SoundHelper.playSound(mp, R.raw.liste_see_more_less);
        //playSound(Uri.parse("android.resource://" + this.getPackageName()+ "/raw/liste_see_more_less"));
    }
}