package com.example.pfi.Classes;

import android.app.Activity;

import androidx.recyclerview.widget.RecyclerView;

import com.example.pfi.Abstract.Panier;
import com.example.pfi.Helper.RecyclerViewHelper;

public class PanierArticle extends Panier<Article> {

    // region ArticleAdapteur

    /**
     * Places its items in the given RecyclerView.
     */
    public void setArticleAdapteur(Activity src, RecyclerView recyclerView) {
        RecyclerViewHelper.setRecyclerView(
            src,
            recyclerView,
            items,
            ArticleAdaptateur::new
        );

        // Cette fonction sert à conserver 'items' privé pour l'extérieur.
    }

    // endregion
    // region Total

    public String getTotal() {
        double total = 0;

        for (Article a : items)
            total += a.getPrixDouble();

        return total + "$";
    }

    // endregion
}
