package com.example.pfi.Activities;

import androidx.annotation.IdRes;
import androidx.appcompat.app.AppCompatActivity;
import android.media.MediaPlayer;
import android.os.Bundle;
import com.example.pfi.Classes.Article;
import com.example.pfi.Classes.Category;
import com.example.pfi.Classes.Client;
import com.example.pfi.Dialogs.ProductPreviewDialog;
import com.example.pfi.Fragments.CategoryListeFragment;
import com.example.pfi.Helper.DialogHelper;
import com.example.pfi.Helper.FragmentHelper;
import com.example.pfi.Helper.HeaderBarHelper;
import com.example.pfi.Helper.SoundHelper;
import com.example.pfi.R;

import java.util.ArrayList;

// TODO : CONVERT CATEGORY DISPLAY TO RECYCLER VIEW
// TODO : FIX RELOAD DUPLICATES
public class ActivityListe extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_liste);

        HeaderBarHelper.setHeaderBar(this, R.string.activity_liste_name);

        displayCategories(Client.categories, R.id.listeActivity_itemListe);
    }

    /**
     * Creates a CategoryDisplay for every category given.
     */
    private void displayCategories(ArrayList<Category> categories, @IdRes int parent) {
        if (categories == null)
            return;

        FragmentHelper.createFragments(parent, categories, CategoryListeFragment::newInstance, this);
    }

    MediaPlayer mp = new MediaPlayer();

    /**
     * Opens a ProductPreviewDialog for the given article.
     */
    public void openDialog(Article article) {
        DialogHelper.openDialog(() -> new ProductPreviewDialog(article), getSupportFragmentManager(), this.toString());

        mp = SoundHelper.playSound(mp, R.raw.liste_see_more_less);

    }
}