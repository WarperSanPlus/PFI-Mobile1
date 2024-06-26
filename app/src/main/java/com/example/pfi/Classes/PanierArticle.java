package com.example.pfi.Classes;

import android.app.Activity;

import androidx.recyclerview.widget.RecyclerView;

import com.example.pfi.Abstract.Panier;
import com.example.pfi.Adaptors.ArticleAdaptateur;
import com.example.pfi.Client;
import com.example.pfi.Helper.RecyclerViewHelper;

import java.util.ArrayList;

public class PanierArticle extends Panier<Article> {

    // region ArticleAdapteur

    /**
     * Places its items in the given RecyclerView.
     */
    public void setArticleAdapteur(Activity src, RecyclerView recyclerView) {
        RecyclerViewHelper.setRecyclerView(
            src,
            recyclerView,
            getItems(),
            ArticleAdaptateur::new
        );

        // Cette fonction sert à conserver 'items' privé pour l'extérieur.
    }

    // endregion

    @Override
    protected void onConsume(Article item, int amount) {
        for (Category c : Client.categories)
        {
            Article a = c.getArticle(item, (article, article2) -> article.compareTo(article2) == 0);

            if (a == null)
                continue;

            a.removeStockAmount(amount);
            break;
        }
    }

    // region Total

    /**
     *
     * @return prix total des articles
     */
    public String getTotal() {
        double total = 0;

        ArrayList<Article> items = getItems();

        for (Article a : items)
            total += a.getPrixDouble() * Client.getPanier().getItemAmount(a);

        return Article.convertPrix(total);
    }

    // endregion
}
