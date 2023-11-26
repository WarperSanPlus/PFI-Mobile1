package com.example.pfi.Classes;

import android.content.Context;
import android.view.View;

import com.example.pfi.Abstract.Adaptor;
import com.example.pfi.R;

import java.util.ArrayList;

public class ArticleAdaptateur extends Adaptor<Article, ArticleViewHolder> {
    public ArticleAdaptateur(Context ctx, ArrayList<Article> items) {
        super(ctx, items);
    }

    @Override
    protected int getLayout() {
        return R.layout.panier_item_layout;
    }

    @Override
    protected ArticleViewHolder getViewHolder(View itemView) {
        return new ArticleViewHolder(itemView);
    }
}
