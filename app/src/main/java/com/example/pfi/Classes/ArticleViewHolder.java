package com.example.pfi.Classes;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.pfi.Abstract.AdaptorViewHolder;
import com.example.pfi.Logger;
import com.example.pfi.R;

public class ArticleViewHolder extends AdaptorViewHolder<Article> {
    ImageView icon;
    TextView txtNom;
    TextView txtPrix;

    public ArticleViewHolder(@NonNull View itemView) {
        super(itemView);

        icon = itemView.findViewById(R.id.item_panier_img);
        txtNom = itemView.findViewById(R.id.item_panier_nom);
        txtPrix = itemView.findViewById(R.id.item_panier_prix);
    }

    @Override
    public void setItem(Article item, int position) {
        icon.setImageResource(item.getIconId());
        txtNom.setText(item.getNom());
        txtPrix.setText(item.getPrix());
    }
}
