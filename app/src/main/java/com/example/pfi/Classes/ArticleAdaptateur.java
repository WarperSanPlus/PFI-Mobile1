package com.example.pfi.Classes;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;

import com.example.pfi.Abstract.Adaptor;
import com.example.pfi.R;

import java.util.ArrayList;

public class ArticleAdaptateur extends Adaptor<Article, ArticleViewHolder> {
    public ArticleAdaptateur(Context ctx, ArrayList<Article> items) {
        super(ctx, items);
    }

    @Override
    public ArticleViewHolder createViewHolder(@NonNull ViewGroup parent, LayoutInflater inflator) {
        View itemView = inflator.inflate(R.layout.une_item_panier, parent, false);
        return new ArticleViewHolder(itemView);
    }
}
