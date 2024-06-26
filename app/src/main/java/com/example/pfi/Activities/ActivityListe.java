package com.example.pfi.Activities;

import androidx.annotation.IdRes;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.example.pfi.Classes.Article;
import com.example.pfi.Classes.Category;
import com.example.pfi.Client;
import com.example.pfi.Dialogs.ProductPreviewDialog;
import com.example.pfi.Fragments.ArticleListeFragment;
import com.example.pfi.Fragments.CategoryListeFragment;
import com.example.pfi.Helper.DialogHelper;
import com.example.pfi.Helper.FragmentHelper;
import com.example.pfi.Helper.HeaderBarHelper;
import com.example.pfi.Helper.IntentHelper;
import com.example.pfi.Helper.SoundHelper;
import com.example.pfi.Logger;
import com.example.pfi.R;
import com.example.pfi.databinding.ActivityListeBinding;

import java.util.ArrayList;
import java.util.List;

// TODO : CONVERT CATEGORY DISPLAY TO RECYCLER VIEW
// TODO : FIX RELOAD DUPLICATES
public class ActivityListe extends AppCompatActivity {
    ActivityListeBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // setContentView(R.layout.activity_liste);
        binding = DataBindingUtil.setContentView (ActivityListe.this, R.layout.activity_liste);

        HeaderBarHelper.setHeaderBar(this, R.string.activity_liste_name);

        displayCategories(Client.categories, R.id.listeActivity_itemListe);

        ImageButton btn_account = findViewById(R.id.headerBar_btnAccount);
        btn_account.setOnClickListener(view ->{
            IntentHelper.closeAndMove(this, ActivityAccount.class, null);
        });
    }

    @Override
    protected void onStart() {
        super.onStart();

        List<Fragment> fragmentList = getSupportFragmentManager().getFragments();

        for (Fragment f1 : fragmentList) {
            if (f1 instanceof CategoryListeFragment) {
                List<Fragment> subFragList = ((CategoryListeFragment) f1).getChildFragmentManager().getFragments();

                for (Fragment f2 : subFragList) {
                    if (f2 instanceof ArticleListeFragment) {
                        ((ArticleListeFragment) f2).updateStockImage();
                    }
                }
            }
        }
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