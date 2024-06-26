package com.example.pfi.Adaptors;

import android.content.Context;
import android.view.View;

import com.example.pfi.Abstract.Adaptor;
import com.example.pfi.Classes.Article;
import com.example.pfi.R;
import com.example.pfi.ViewHolders.ArticleViewHolder;

import java.util.ArrayList;

/**
 * Pour le recyclerView du Panier
 */
public class ArticleAdaptateur extends Adaptor<Article, ArticleViewHolder> {
    public ArticleAdaptateur(Context ctx, ArrayList<Article> items) {
        super(ctx, items);
    }

    // region Override
    @Override
    protected int getLayout() {
        return R.layout.panier_item_layout;
    }

    @Override
    protected ArticleViewHolder getViewHolder(View itemView) {
        return new ArticleViewHolder(itemView);
    }
    // endregion
}
