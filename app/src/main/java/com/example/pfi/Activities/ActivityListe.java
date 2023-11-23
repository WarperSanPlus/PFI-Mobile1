package com.example.pfi.Activities;

import androidx.annotation.IdRes;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.pfi.Classes.Article;
import com.example.pfi.Classes.Category;
import com.example.pfi.Dialogs.ProductPreviewDialog;
import com.example.pfi.Fragments.CategoryDisplay;
import com.example.pfi.Helper.DialogHelper;
import com.example.pfi.Helper.FragmentHelper;
import com.example.pfi.Helper.HeaderBarHelper;
import com.example.pfi.R;

import java.util.ArrayList;

public class ActivityListe extends AppCompatActivity {
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

    public void openDialog(Article article) {
        DialogHelper.openDialog(() -> new ProductPreviewDialog(article), getSupportFragmentManager(), this.toString());
    }
}